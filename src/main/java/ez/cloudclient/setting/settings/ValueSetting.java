package ez.cloudclient.setting.settings;

import ez.cloudclient.setting.Setting;

public class ValueSetting<T> extends Setting {

    public T value;

    private final String name;

    private final T defaultValue;

    public ValueSetting(String name, T defaultValue) {

        this.name = name;
        this.defaultValue = defaultValue;
        this.value = defaultValue;
    }

    public String getName() {

        return name;
    }

    public T getDefaultValue() {

        return defaultValue;
    }

    public T getValue() {

        return value;
    }

    public void setValue(T value) {

        this.value = value;
    }
}
