package org.samcrow.colonydata.experiments;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * A record of a marked ant doing something
 * <p/>
 * @author Sam Crow
 */
@Entity
public class MarkedAntRecord extends ExperimentResult <MarkedAntRecordingExperiment> {

    private AntColor headColor;
    
    private AntColor thoraxColor;
    
    private AntColor abdomenColor;

    @Enumerated(EnumType.STRING)
    public AntColor getHeadColor() {
        return headColor;
    }

    public void setHeadColor(AntColor headColor) {
        this.headColor = headColor;
    }
    
    @Enumerated(EnumType.STRING)
    public AntColor getThoraxColor() {
        return thoraxColor;
    }

    public void setThoraxColor(AntColor thoraxColor) {
        this.thoraxColor = thoraxColor;
    }

    @Enumerated(EnumType.STRING)
    public AntColor getAbdomenColor() {
        return abdomenColor;
    }

    public void setAbdomenColor(AntColor abdomenColor) {
        this.abdomenColor = abdomenColor;
    }
    
    
    
    
    public static enum AntColor {

        Green,
        Blue,
        /**
         * Either white, yellow, or silver
         */
        WhiteYellowSilver,
        Pink,
        Orange,
        Red,
        /**
         * An unpainted ant section
         */
        NoPaint,

    }

}
