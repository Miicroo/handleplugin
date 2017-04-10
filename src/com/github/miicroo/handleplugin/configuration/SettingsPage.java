package com.github.miicroo.handleplugin.configuration;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.ui.JBColor;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;

public class SettingsPage implements Configurable {

    private Settings settings = Settings.getInstance();
    private boolean isModified;
    private JTextField input;

    @Nls
    @Override
    public String getDisplayName() {
        return "Handle plugin";
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return "Settings page for the handle plugin";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        JPanel component = new JPanel();
        component.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.SOUTH;
        c.weightx = 1;

        JLabel label = new JLabel("Path to handle: ");
        c.gridx = 0;
        c.gridy = 0;
        component.add(label, c);

        input = new JTextField(30);
        input.setText(settings.getHandlePath());
        input.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                isModified = true;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                isModified = true;
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                isModified = true;
            }
        });

        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 3;

        component.add(input, c);

        final File currentPath =
            settings.getHandlePath() != null ? new File(settings.getHandlePath()).getParentFile() : null;

        JButton searcher = new JButton("...");
        searcher.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser(currentPath);
            FileFilter filter = new FileFilter() {
                @Override
                public boolean accept(File f) {
                    return f.getName().endsWith("handle.exe") || f.getName().endsWith("handle64.exe") || f.isDirectory();
                }

                @Override
                public String getDescription() {
                    return "Handle executable";
                }
            };
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                input.setText(chooser.getSelectedFile().getAbsolutePath());
            }
        });

        c.gridx = 4;
        c.gridy = 0;
        c.gridwidth = 1;
        component.add(searcher, c);

        component.setBorder(new LineBorder(JBColor.RED, 10));

        return component;
    }

    @Override
    public boolean isModified() {
        return isModified;
    }

    @Override
    public void apply() throws ConfigurationException {
        settings.setHandlePath(input.getText());
        isModified = false;
    }

    @Override
    public void reset() {
        input.setText(settings.getHandlePath());
        isModified = false;
    }

    @Override
    public void disposeUIResources() {
    }
}
