
package org.samcrow.colonydata.experiments;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * An experiment that has precise timing information associated with it
 * @author Sam Crow
 * @param <T> 
 */
@Entity
public class PreciselyTimedExperiment<T extends ExperimentResult> extends Experiment<T> {
    
    private Date startTime;
    
    private Date endTime;

    @Temporal(TemporalType.TIMESTAMP)
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    
    
}
