package com.rosedine.rosedine;

import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class DataImportRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        // Initial run when the application starts
        //runDataImportProcess();
    }

    @Scheduled(cron = "0 0 1 * * ?") // Run at 1:00 AM every day
    public void scheduledDataImport() {
        runDataImportProcess();
    }

    private void runDataImportProcess() {
        try {
            String script1Path = "PyScraping/main.py";
            runPythonScript(script1Path);
            sendPostRequest();
        } catch (Exception e) {
            System.err.println("Error in scheduled data import: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void runPythonScript(String scriptPath) throws Exception {
        System.out.println("Running Python script: " + scriptPath);

        // Determine the correct activation script and python executable based on OS
        String os = System.getProperty("os.name").toLowerCase();
        String activateCommand;
        String pythonCommand;

        if (os.contains("win")) {
            activateCommand = "cmd.exe /c ";
            pythonCommand = "python";
        } else {
            activateCommand = "source ";
            pythonCommand = "python3";
        }

        String venvPath = "PyScraping/venv_RoseDine";
        String fullCommand = activateCommand + venvPath +
                (os.contains("win") ? "\\Scripts\\activate.bat && " : "/bin/activate && ") +
                pythonCommand + " " + scriptPath;

        ProcessBuilder processBuilder = new ProcessBuilder();
        if (os.contains("win")) {
            processBuilder.command("cmd.exe", "/c", fullCommand);
        } else {
            processBuilder.command("bash", "-c", fullCommand);
        }

        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();
        StringBuilder output = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            System.err.println("Python script " + scriptPath + " failed with exit code " + exitCode);
            System.err.println("Output:\n" + output);
            throw new RuntimeException("Python script " + scriptPath + " exited with code " + exitCode);
        } else {
            System.out.println("Python script " + scriptPath + " executed successfully.");
            System.out.println("Output:\n" + output);
        }
    }

    private void sendPostRequest() {
        System.out.println("Sending POST request");
        String url = "http://localhost:8081/api/menus/import";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        System.out.println("Data import status: " + response.getBody());
    }
}