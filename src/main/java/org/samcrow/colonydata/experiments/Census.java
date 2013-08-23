package org.samcrow.colonydata.experiments;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * A census of colonies
 * @author Sam Crow
 */
@Entity
public class Census extends Experiment {
    
    
    /**
     * The day that this census started
     */
    private Date startDate;
    
    /**
     * The day that this census ended
     */
    private Date endDate;
    
    /**
     * The data recorded in this census
     */
    private List<CensusColonyState> results = new LinkedList<>();
    
    public Census() {
        
    }

    @Temporal(TemporalType.DATE)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Temporal(TemporalType.DATE)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    /**
     * @return The year of this census's start date
     */
    @Transient
    public int getYear() {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);
        return calendar.get(GregorianCalendar.YEAR);
    }

    @OneToMany(mappedBy = "census")
    public List<CensusColonyState> getCensusResults() {
        return results;
    }

    public void setCensusResults(List<CensusColonyState> results) {
        this.results = results;
    }

    
    @Override
    public void setResults(List<? extends ExperimentResult> newResults) {
        results.clear();
        for(ExperimentResult result : newResults) {
            if(result instanceof CensusColonyState) {
                results.add((CensusColonyState) result);
            }
        }
    }

    @Transient
    @Override
    public List<? extends ExperimentResult> getResults() {
        return results;
    }
    
    
}
