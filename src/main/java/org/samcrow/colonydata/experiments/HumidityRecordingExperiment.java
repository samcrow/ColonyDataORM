package org.samcrow.colonydata.experiments;

import javax.persistence.Entity;

/**
 * An experiment in which humidity was recorded
 * @author Sam Crow
 */
@Entity
public class HumidityRecordingExperiment extends PreciselyTimedExperiment<HumidityDataPoint> {
    
}
