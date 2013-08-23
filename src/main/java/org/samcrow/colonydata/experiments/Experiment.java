
package org.samcrow.colonydata.experiments;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
import org.samcrow.colonydata.Colony;

/**
 * An experiment
 * @author Sam Crow
 */
@Entity
public abstract class Experiment implements Serializable {
    
    private static final long serialVersionUID = 1;
    
    private int id;
    
    private String name;
    
    private String description;
    
    /**
     * The colonies on which this experiment was performed
     */
    private List<Colony> coloniesTested = new LinkedList<>();
    

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Lob
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToMany(
        targetEntity = Colony.class,
        cascade={CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinTable(
        name="colony_experiment",
        joinColumns=@JoinColumn(name = "experiment_id"),
        inverseJoinColumns=@JoinColumn(name = "colony_id")
    )
    public List<Colony> getColoniesTested() {
        return coloniesTested;
    }

    protected void setColoniesTested(List<Colony> coloniesTested) {
        this.coloniesTested = coloniesTested;
    }
    
    /**
     * 
     * @return The results of this experiment
     */
    @Transient
    public abstract List<? extends ExperimentResult> getResults();
    
    /**
     * Sets the results of this experiment
     * @param newResults 
     */
    public abstract void setResults(List<? extends ExperimentResult> newResults);
    
}