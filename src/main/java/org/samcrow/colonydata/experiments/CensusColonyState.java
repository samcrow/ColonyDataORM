package org.samcrow.colonydata.experiments;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The recorded state of a colony during a certain census
 * @author Sam Crow
 */
@Entity
@XmlRootElement
public class CensusColonyState extends ExperimentResult <Census> {
    
    //State parameters
    /**
     * If the colony was active
     */
    private boolean active = false;
    
    /**
     * If the colony appears abandoned
     */
    private boolean abandoned = false;

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
    
}
