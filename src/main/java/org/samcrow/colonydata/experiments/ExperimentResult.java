package org.samcrow.colonydata.experiments;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.GenericGenerator;
import org.samcrow.colonydata.Colony;

/**
 * A base class for an experiment result
 * @param <E> The type of experiment that this is a result of
 * @author samcrow
 */
@Entity
public class ExperimentResult <E extends Experiment> implements Serializable {

    protected static final long serialVersionUID = 1;

    
    /**
     * The colony that this result concerns
     */
    protected Colony colony;
    
    /**
     * The experiment that produced this result
     */
    protected E experiment;

    protected int id;

    /**
     * Notes about this result
     */
    protected String notes;

    @ManyToOne
    @JoinColumn(name = "colony_fk")
    public Colony getColony() {
        return colony;
    }
    
    @ManyToOne(targetEntity = Experiment.class)
    @JoinColumn(name = "experiment_fk")
    public E getExperiment() {
        return experiment;
    }

    public void setExperiment(E experiment) {
        this.experiment = experiment;
    }
    
    
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public int getId() {
        return id;
    }

    @Lob
    public String getNotes() {
        return notes;
    }

    public void setColony(Colony colony) {
        this.colony = colony;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
