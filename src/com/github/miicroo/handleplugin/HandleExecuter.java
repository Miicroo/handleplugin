package com.github.miicroo.handleplugin;

import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.ui.ConsoleView;

import java.io.IOException;

import static com.intellij.execution.ui.ConsoleViewContentType.ERROR_OUTPUT;
import static com.intellij.execution.ui.ConsoleViewContentType.SYSTEM_OUTPUT;

public class HandleExecuter extends Thread {

    private String handlePath, projectFile;
    private ConsoleView output;

    public HandleExecuter(String handlePath, String projectFile, ConsoleView output) {
        this.handlePath = handlePath;
        this.projectFile = projectFile;
        this.output = output;
    }

    @Override
    public void run() {
        try {
            output.print("Running command: \"" + handlePath + "\" " + projectFile + "\n", SYSTEM_OUTPUT);

            Process p = new ProcessBuilder("\"" + handlePath + "\"", projectFile).start();
            ProcessHandler processHandler = new OSProcessHandler(p, "Handle");
            processHandler.startNotify();
            output.attachToProcess(processHandler);
        } catch (IOException e1) {
            output.print(e1.getMessage(), ERROR_OUTPUT);
        }
    }
}
