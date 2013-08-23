package org.samcrow.colonydata;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Embeddable;

/**
 * Stores a colony's position in local coordinates.
 * 
 * These coordinates have an origin at the southwest corner of the site
 * and increase going north and east.
 * 
 * @author Sam Crow
 */
@Embeddable
public class ColonyPosition implements Serializable {
    
    private static final long serialVersionUID = 1;
    
    /**
     * X-axis position
     */
    private int x;
    
    /**
     * Y-axis position
     */
    private int y;
    
    public ColonyPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public ColonyPosition() {
        
    }

    @Basic
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Basic
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
}
