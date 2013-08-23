package org.samcrow.colonydata;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

/**
 * Represents a note associated with a colony
 * @author Sam Crow
 */
@Entity
public class ColonyNote implements Serializable {
    
    private static final long serialVersionUID = 1;
    
    /**
     * The text of this note
     */
    private String noteText;
    /**
     * When this note was created
     */
    private Date dateAdded;
    /**
     * When this date was last modified
     */
    private Date dateModified;
    /**
     * The name of the person who wrote this note
     */
    private String author;
    
    /**
     * The ID of this note
     */
    private int id;
    
    /**
     * The colony that this note is associated with
     */
    private Colony colony;
    

    /**
     * 
     * @return The text of this note
     */
    @Lob
    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }
    
    /**
     * 
     * @return The colony that this note is associated with
     */
    @ManyToOne
    @JoinColumn(name = "colony_fk")
    public Colony getColony() {
        return colony;
    }
    
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    
    protected void setColony(Colony newColony) {
        colony = newColony;
    }

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }
    
    
    
}
