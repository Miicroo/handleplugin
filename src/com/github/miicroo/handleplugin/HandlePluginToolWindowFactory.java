package com.github.miicroo.handleplugin;

import com.intellij.execution.ui.ConsoleView;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import org.jetbrains.annotations.NotNull;

public class HandlePluginToolWindowFactory implements ToolWindowFactory {

    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        ConsoleView consoleView = ConsoleService.getConsoleService(project).getConsoleView();
        Content content = toolWindow.getContentManager().getFactory()
            .createContent(consoleView.getComponent(), "", true);
        toolWindow.getContentManager().addContent(content);
    }
}
