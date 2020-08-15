package com.in28minutes.microservices.limitservice.bean;

public class LimitsConfiguration {
    private int maximum;

    public LimitsConfiguration() {
    }

    private int minimum;

    public LimitsConfiguration(int maximum, int minimum) {
        this.maximum = maximum;
        this.minimum = minimum;
    }

    public int getMaximum() {
        return maximum;
    }

    public int getMinimum() {
        return minimum;
    }


}
