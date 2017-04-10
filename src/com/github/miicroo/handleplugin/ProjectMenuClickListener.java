package com.github.miicroo.handleplugin;

import com.github.miicroo.handleplugin.configuration.Settings;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;

import static com.intellij.openapi.actionSystem.CommonDataKeys.VIRTUAL_FILE;

public class ProjectMenuClickListener extends AnAction {

    private Logger logger = Logger.getInstance(ProjectMenuClickListener.class);
    private Settings settings = Settings.getInstance();

    @Override
    public void actionPerformed(AnActionEvent e) {
        if (!clickedOnFile(e)) {
            logger.debug("User did not click on file, ignoring event");
            return;
        }

        Project project = e.getProject();

        ToolWindow toolWindow = getToolWindow(project);
        toolWindow.activate(null);

        String projectFile = getClickedFile(e);
        ConsoleView output = ConsoleService.getConsoleService(project).getConsoleView();

        new HandleExecuter(settings.getHandlePath(), projectFile, output).start();
    }

    private ToolWindow getToolWindow(Project project) {
        return ToolWindowManager.getInstance(project).getToolWindow("Handle plugin tool window");
    }

    private boolean clickedOnFile(AnActionEvent e) {
        return getClickedFile(e) != null;
    }

    private String getClickedFile(AnActionEvent e) {
        final DataContext dataContext = e.getDataContext();
        Object filename = dataContext.getData(VIRTUAL_FILE.getName());

        return filename == null ? null : filename.toString();
    }
}
