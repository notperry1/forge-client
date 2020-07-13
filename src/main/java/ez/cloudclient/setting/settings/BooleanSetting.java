package ez.cloudclient.setting.settings;

import com.google.gson.annotations.SerializedName;
import ez.cloudclient.setting.Setting;

public class BooleanSetting extends Setting {
    @SerializedName("value")
    private Boolean value;

    public BooleanSetting(Boolean defaultValue) {
        value = defaultValue;
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }
}
