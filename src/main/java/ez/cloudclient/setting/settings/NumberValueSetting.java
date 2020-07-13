package ez.cloudclient.setting.settings;

import ez.cloudclient.setting.settings.ValueSetting;

public class NumberValueSetting extends ValueSetting<Double> {

    protected Double min, max;

    public NumberValueSetting(String name, Double defaultValue, Double min, Double max) {

        super(name, defaultValue);
        this.min = min;
        this.max = max;
    }

    public Double getMin() {

        return min;
    }

    public Double getMax() {

        return max;
    }
}
