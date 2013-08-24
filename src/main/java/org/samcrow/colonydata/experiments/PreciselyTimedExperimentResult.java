package org.samcrow.colonydata.experiments;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * An experiment with precisely defined start and end times
 * @author Sam Crow
 * @param <T> The type of experiment that this is a result of
 */
@Entity
public class PreciselyTimedExperimentResult <T extends Experiment> extends ExperimentResult <T> {
    
    private Date timeRecorded;

    @Temporal(TemporalType.TIMESTAMP)
    public Date getTimeRecorded() {
        return timeRecorded;
    }

    public void setTimeRecorded(Date timeRecorded) {
        this.timeRecorded = timeRecorded;
    }
    
    
    
}
