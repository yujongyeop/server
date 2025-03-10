package com.enssel.server;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Metric {
    @JsonProperty("cpu_usage")
    private double cpuUsage;

    @JsonProperty("memory_usage")
    private double memoryUsage;

    @Override
    public String toString() {
        return "Metric{" +
                "cpuUsage=" + cpuUsage +
                ", memoryUsage=" + memoryUsage +
                '}';
    }

    public double getCpuUsage() {
        return cpuUsage;
    }

    public double getMemoryUsage() {
        return memoryUsage;
    }
}
