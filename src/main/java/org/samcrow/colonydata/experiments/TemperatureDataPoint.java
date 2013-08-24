package org.samcrow.colonydata.experiments;

import javax.persistence.Entity;

/**
 * A data point with a known timestamp when the temperature was recorded
 * @author Sam Crow
 */
@Entity
public class TemperatureDataPoint extends PreciselyTimedExperimentResult <TemperatureRecordingExperiment> {
    
    /**
     * The temperature, in degrees Celsius
     */
    private double temperature;

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
    
}
