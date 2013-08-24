package org.samcrow.colonydata.experiments;

import javax.persistence.Entity;

/**
 * An experiment in which temperature was recorded
 * @author Sam Crow
 */
@Entity
public class TemperatureRecordingExperiment extends PreciselyTimedExperiment <TemperatureDataPoint> {
    
}
