package com.github.miicroo.handleplugin.configuration;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;

import static com.intellij.openapi.components.StoragePathMacros.ROOT_CONFIG;
import static com.intellij.openapi.components.StorageScheme.DIRECTORY_BASED;

@State(name = "Settings", storages = {
    @Storage(id = "dir", file = ROOT_CONFIG + "/settings.xml", scheme = DIRECTORY_BASED) })
public class Settings implements PersistentStateComponent<Settings> {

    private String handlePath;

    public String getHandlePath() {
        return handlePath;
    }

    public void setHandlePath(String handlePath) {
        this.handlePath = handlePath;
    }

    public Settings getState() {
        return this;
    }

    @Override
    public void loadState(Settings settings) {
        XmlSerializerUtil.copyBean(settings, this);
    }

    public static Settings getInstance() {
        return ServiceManager.getService(Settings.class);
    }
}
