package org.samcrow.colonydata.experiments;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * A record of a marked ant entering or leaving an area
 * <p/>
 * @author Sam Crow
 */
@Entity
public class MarkedAntInOutRecord extends MarkedAntRecord {

    /**
     * The time that this record was recorded
     */
    private Date timeRecorded;

    /**
     * The direction that the ant was traveling
     */
    private InOut direction;

    @Temporal(TemporalType.TIMESTAMP)
    public Date getTimeRecorded() {
        return timeRecorded;
    }

    public void setTimeRecorded(Date timeRecorded) {
        this.timeRecorded = timeRecorded;
    }

    @Enumerated(EnumType.STRING)
    public InOut getDirection() {
        return direction;
    }

    public void setDirection(InOut direction) {
        this.direction = direction;
    }
    
    

    public static enum InOut {

        In,
        Out,

    }

}
