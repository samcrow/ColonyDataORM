package org.samcrow.colonydata.experiments;

import javax.persistence.Entity;

@Entity
public class HumidityDataPoint extends PreciselyTimedExperimentResult<HumidityRecordingExperiment> {
    
    /**
     * Relative humidity, in percent
     */
    private double humidity;

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }
    
    
}
