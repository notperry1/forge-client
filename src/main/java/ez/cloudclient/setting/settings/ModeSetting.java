package ez.cloudclient.setting.settings;


import java.util.Arrays;

public class ModeSetting {
    private final String[] modes;
    private int currentMode;

    public ModeSetting(String... modes) {
        this.modes = modes;
        this.currentMode = 0;
    }

    public String currentMode() {
        return modes[currentMode];
    }

    public void incrementMode() {
        currentMode++;
        if (currentMode > modes.length - 1) {
            currentMode = 0;
        }
    }

    public void setMode(String mode) {
        currentMode = Arrays.asList(modes).indexOf(mode);
    }
}
