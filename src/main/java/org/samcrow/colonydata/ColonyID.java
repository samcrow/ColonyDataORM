package org.samcrow.colonydata;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

/**
 * Stores information that identifies a colony
 * @author Sam Crow
 */
@Embeddable
public class ColonyID implements Serializable {
    
    private static final long serialVersionUID = 1;
    
    /**
     * This colony's prefix
     */
    private Prefix prefix = Prefix.Main;
    
    /**
     * This colony's identifier within its prefix
     */
    private int localId;
    
    /**
     * This colony's global identifier, which is unique among all colonies
     * in all prefixes
     */
    private int globalId;
    
    public ColonyID(int globalId) {
        this.globalId = globalId;
    }
    
    public ColonyID() {
        
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "id_prefix")
    public Prefix getPrefix() {
        return prefix;
    }

    public void setPrefix(Prefix prefix) {
        this.prefix = prefix;
    }

    @Basic
    public int getLocalId() {
        return localId;
    }

    public void setLocalId(int localId) {
        this.localId = localId;
    }

    //Marked as transient so that the ORM does not confuse this
    //as a duplicate of Colony.getGlobalId()
    @Transient
    public int getGlobalId() {
        return globalId;
    }

    public void setGlobalId(int globalId) {
        this.globalId = globalId;
    }
    
    /**
     * Returns a string representation of this colony ID. This will include
     * the prefix and the local identifier.
     * @return 
     */
    @Override
    public String toString() {
        return prefix.getPrefix() + String.valueOf(localId);
    }
    
    public static enum Prefix {
        /**
         * Main colony series, no prefix text
         */
        Main (""),
        /**
         * N-series, prefix "N"
         */
        NSeries ("N"),
        ;
        
        private String prefixText;
        
        private Prefix(String prefixText) {
            this.prefixText = prefixText;
        }
        
        public String getPrefix() {
            return prefixText;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.globalId;
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
        final ColonyID other = (ColonyID) obj;
        if (this.globalId != other.globalId) {
            return false;
        }
        return true;
    }
}
