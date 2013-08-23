package org.samcrow.colonydata;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.GenericGenerator;
import org.samcrow.colonydata.experiments.Census;
import org.samcrow.colonydata.experiments.CensusColonyState;
import org.samcrow.colonydata.experiments.Experiment;
import org.samcrow.colonydata.experiments.ExperimentResult;

/**
 * A colony
 * @author Sam Crow
 */
@Entity
@XmlRootElement
public class Colony implements Serializable {
    
    private static final long serialVersionUID = 2;
    
    /**
     * The colony's identifying information
     */
    private ColonyID id;
    
    /**
     * The colony's position
     */
    private ColonyPosition position;
    
    /**
     * The notes associated with this colony
     */
    private List<ColonyNote> notes = new LinkedList<>();
    
    /**
     * The experiments that were performed on this colony
     */
    private List<Experiment> experimentsPerformed = new LinkedList<>();
    
    private List<ExperimentResult> experimentResults = new LinkedList<>();
    
    public Colony() {
        id = new ColonyID();
        position = new ColonyPosition();
    }
    
    @Embedded
    public ColonyID getId() {
        return id;
    }

    @Embedded
    public ColonyPosition getPosition() {
        return position;
    }
    
    @OneToMany(mappedBy = "colony")
    public List<ColonyNote> getNotes() {
        return notes;
    }
    
    @ManyToMany(
        cascade = {CascadeType.PERSIST, CascadeType.MERGE},
        mappedBy = "coloniesTested",
        targetEntity = Experiment.class
    )
    public List<Experiment> getExperimentsPerformed() {
        return experimentsPerformed;
    }
    
    @OneToMany(mappedBy = "colony")
    public List<ExperimentResult> getExperimentResults() {
        return experimentResults;
    }
    
    protected void setPosition(ColonyPosition newPosition) {
        position = newPosition;
    }
    
    protected void setId(ColonyID newId) {
        id = newId;
    }
    
    protected void setNotes(List<ColonyNote> notes) {
        this.notes = notes;
    }
    
    protected void setExperimentsPerformed(List<Experiment> experiments) {
        experimentsPerformed = experiments;
    }
    
    protected void setExperimentResults(List<ExperimentResult> newResults) {
        experimentResults = newResults;
    }
    
    //Persistence-only methods
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    protected int getGlobalId() {
        return id.getGlobalId();
    }
    
    protected void setGlobalId(int newId) {
        id.setGlobalId(newId);
    }
    
    //End
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Colony other = (Colony) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
    //Transient methods
    
    /**
     * 
     * @return The most recent census that recorded this colony, or null
     * if no census has recorded this colony
     */
    @Transient
    public Census getLatestCensus() {
        Census latestCensus = null;
        
        for(Experiment experiment : getExperimentsPerformed()) {
            //Find an experiment that is a census
            if(experiment instanceof Census) {
                Census census = (Census) experiment;
                //If the latest census is not set, 
                if(latestCensus == null) {
                    latestCensus = census;
                }
                else {
                    //Replace the latest census with this one if this one is later
                    if(census.getStartDate().after(latestCensus.getStartDate())) {
                        latestCensus = census;
                    }
                }
                
            }
        }
        
        return latestCensus;
    }
    
    /**
     * Gets the census that recorded this colony in a given year
     * @param year The year to find a census for
     * @return The census that recorded this colony in the given year, or null
     * if no such census was found
     */
    public Census getCensusForYear(int year) {
        for(Experiment experiment : getExperimentsPerformed()) {
            if(experiment instanceof Census) {
                if(((Census) experiment).getYear() == year) {
                    return (Census) experiment;
                }
            }
        }
        
        return null;
    }
    
    /**
     * Gets the state of this colony for the census of a given year
     * @param year The year to get the state in
     * @return 
     */
    public CensusColonyState getStateForYear(int year) {
        Census census = getCensusForYear(year);
        if(census == null) {
            return null;
        }
        for(CensusColonyState state : census.getCensusResults()) {
            if(state.getColony().equals(this)) {
                return state;
            }
        }
        
        return null;
    }
    
    /**
     * 
     * @return The state of this colony at the most recent census that recorded it,
     * or null of this colony does not appear in any censuses
     */
    @Transient
    public CensusColonyState getLatestState() {
        Census census = getLatestCensus();
        if(census == null) {
            return null;
        }
        
        for(CensusColonyState state : census.getCensusResults()) {
            if(state.getColony().equals(this)) {
                return state;
            }
        }
        
        return null;
    }
    
    
    public boolean wasActiveIn(int year) {
        Census census = getCensusForYear(year);
        if(census == null) {
            return false;
        }
        
        return false;
    }
    
    //end
}
