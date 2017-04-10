package com.github.miicroo.handleplugin;

import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;

public class ConsoleService {
    private final ConsoleView consoleView;

    public ConsoleService(Project project) {
        consoleView = TextConsoleBuilderFactory.getInstance().createBuilder(project).getConsole();
    }

    public ConsoleView getConsoleView() {
        return consoleView;
    }

    public static ConsoleService getConsoleService(Project project) {
        return ServiceManager.getService(project, ConsoleService.class);
    }
}
