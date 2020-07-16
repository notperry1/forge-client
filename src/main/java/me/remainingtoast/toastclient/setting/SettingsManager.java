package me.remainingtoast.toastclient.setting;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import me.remainingtoast.toastclient.ToastClient;
import me.remainingtoast.toastclient.module.Module;
import me.remainingtoast.toastclient.module.ModuleManager;
import me.remainingtoast.toastclient.setting.settings.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static me.remainingtoast.toastclient.ToastClient.CLOUDCLIENT_CONFIGFILE;

public class SettingsManager {
    final RuntimeTypeAdapterFactory<Setting> typeFactory = RuntimeTypeAdapterFactory
            .of(Setting.class, "type")
            .registerSubtype(BooleanSetting.class, "toggle")
            .registerSubtype(ModeSetting.class, "mode")
            .registerSubtype(ValueSetting.class, "value")
            .registerSubtype(KeybindSetting.class, "keybind")
            .registerSubtype(NumberValueSetting.class, "number");
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(typeFactory).setPrettyPrinting().create();
    File configFile = new File(CLOUDCLIENT_CONFIGFILE);

    public Map<String, ModuleSettings> readSettings() {
        Map<String, ModuleSettings> settingsArray = new HashMap<>();
        if (configFile.exists() && configFile.isFile()) {
            try {
                ToastClient.log.info("Reading config file...");
                settingsArray = gson.fromJson(new FileReader(configFile), new TypeToken<Map<String, ModuleSettings>>() {
                }.getType());
                ToastClient.log.info("Successfully read config file.");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                ToastClient.log.fatal("Could not read config file.");
            }
        } else {
            try {
                ToastClient.log.info("Creating config file...");
                FileWriter fw = new FileWriter(configFile);
                gson.toJson(settingsArray, fw);
                fw.flush();
                fw.close();
                ToastClient.log.info("Created config file.");
            } catch (IOException e) {
                e.printStackTrace();
                ToastClient.log.fatal("Could not create config file.");
            }
        }
        return settingsArray;
    }

    public void writeSettings(Map<String, ModuleSettings> settingsArray) {
        try {
            ToastClient.log.info("Writing config file");
            FileWriter fw = new FileWriter(configFile);
            gson.toJson(settingsArray, fw);
            fw.flush();
            fw.close();
            ToastClient.log.info("Wrote config file.");
        } catch (IOException e) {
            e.printStackTrace();
            ToastClient.log.fatal("Could not write config file.");
        }
    }

    public void updateSettings() {
        Map<String, ModuleSettings> settingsArray = new HashMap<>();
        for (Module module : ModuleManager.modules) {
            settingsArray.put(module.getName(), module.getSettings());
        }
        writeSettings(settingsArray);
    }

    public void loadSettings() {
        Map<String, ModuleSettings> settingsArray = readSettings();
        for (Module module : ModuleManager.modules) {
            String moduleName = module.getName();
            try {
                if (settingsArray.containsKey(moduleName)) {
                    module.setSettings(settingsArray.get(moduleName));
                } else {
                    module.registerSettings();
                }
            } catch (NullPointerException npe) {
                module.registerSettings();
            }
        }
        updateSettings();
    }
}
