package org.samcrow.colonydata.experiments;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * The recorded state of a colony during a certain census
 * @author Sam Crow
 */
@Entity
public class CensusColonyState extends ExperimentResult {
    
    //State parameters
    /**
     * If the colony was active
     */
    private boolean active = false;
    
    /**
     * If the colony appears abandoned
     */
    private boolean abandoned = false;
    
    /**
     * The census that this state is part of
     */
    private Census census;

    public CensusColonyState(boolean active, boolean abandoned) {
        this.active = active;
        this.abandoned = abandoned;
    }
    
    public CensusColonyState() {
        
    }
    

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isAbandoned() {
        return abandoned;
    }

    public void setAbandoned(boolean abandoned) {
        this.abandoned = abandoned;
    }

    @ManyToOne
    @JoinColumn(name="census_fk")
    public Census getCensus() {
        return census;
    }

    public void setCensus(Census census) {
        this.census = census;
    }
    
    
    
}
