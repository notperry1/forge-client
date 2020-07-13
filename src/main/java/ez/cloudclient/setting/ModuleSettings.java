package ez.cloudclient.setting;

import ez.cloudclient.setting.settings.BooleanSetting;

import java.util.HashMap;
import java.util.Map;

public class ModuleSettings {

    public Map<String, Setting> settings = new HashMap<>();

    public void addSetting(String settingName, Setting defaultValue) {
        settings.put(settingName, defaultValue);
    }

    public void addBoolean(String settingName, Boolean defaultValue) {
        settings.put(settingName, new BooleanSetting(defaultValue));
    }

    public Boolean getBoolean(String settingName) {
        Setting setting = settings.get(settingName);
        if (setting instanceof BooleanSetting) {
            return ((BooleanSetting) setting).getValue();
        }
        return null;
    }

    public void setBoolean(String settingName, Boolean newValue) {
        Setting setting = settings.get(settingName);
        if (setting instanceof BooleanSetting) {
            ((BooleanSetting) setting).setValue(newValue);
        }
    }

    public void setSetting(String settingName, Setting newValue) {
        if (newValue.getClass() == settings.get(settingName).getClass()) {
            settings.replace(settingName, newValue);
        }
    }

    public Setting getSetting(String settingName, Setting type) {
        Setting setting = settings.get(settingName);
        if (setting.getClass() == type.getClass()) {
            return setting;
        }
        return null;
    }
}
