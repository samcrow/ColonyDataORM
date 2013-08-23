package org.samcrow.colonydata;

import java.util.Date;
import java.util.Random;
import junit.framework.TestCase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.samcrow.colonydata.experiments.Census;
import org.samcrow.colonydata.experiments.CensusColonyState;
import org.samcrow.colonydata.experiments.Experiment;
import org.samcrow.colonydata.experiments.MarkedAntRecordingExperiment;

/**
 *
 * @author samcrow
 */
public class ColonyTest extends TestCase {
    
    private SessionFactory sessionFactory;
    private Session session;
    
    public ColonyTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        final Configuration configuration = new Configuration();
        configuration.configure();
        
        final ServiceRegistry registry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        
        sessionFactory = configuration.buildSessionFactory(registry);
        session = sessionFactory.openSession();
    }
    
    @Override
    protected void tearDown() throws Exception {
        session.close();
    }

    
    public void testSomething() throws Exception {
        
        Integer key = 1;
        
        Colony colony = (Colony) session.get(Colony.class, key);
        if(colony == null) {
            System.out.println("Creating new colony");
            colony = new Colony();
        }
        //This must be set, or an exception will be thrown because
        //the global ID will be 0.
        colony.getId().setGlobalId(key);
        
        
        colony.getId().setLocalId(99);
        //Increment X
        colony.getPosition().setX(colony.getPosition().getX() + 1 );
        colony.getPosition().setY(123);
        
        //Add a note
        ColonyNote note = new ColonyNote();
        note.setAuthor("Sam Crow");
        note.setDateAdded(new Date());
        note.setDateModified(new Date());
        note.setNoteText("This is a note!");
        
        colony.getNotes().add(note);
        note.setColony(colony);
        
        //Add an experiment
        Experiment experiment = new MarkedAntRecordingExperiment();
        experiment.setName("Experiment "+new Random().nextInt());
        experiment.setDescription("This is an experiment.");
        
        experiment.getColoniesTested().add(colony);
        colony.getExperimentsPerformed().add(experiment);
        
        //Save things
        session.beginTransaction();
        session.save(note);
        session.save(experiment);
        session.save(colony);
        session.getTransaction().commit();
        
        assertTrue(true);
    }
    
    public void testCensus() throws Exception {
        
        Integer key = 1;
        Colony colony = (Colony) session.get(Colony.class, key);
        if(colony == null) {
            System.out.println("Creating new colony");
            colony = new Colony();
        }
        //This must be set, or an exception will be thrown because
        //the global ID will be 0.
        colony.getId().setGlobalId(key);
        
        Census census = new Census();
        census.setStartDate(new Date());
        census.setEndDate(new Date(census.getStartDate().getTime() + 100000));
        
        CensusColonyState state = new CensusColonyState(true, false);
        state.setColony(colony);
        colony.getExperimentResults().add(state);
        census.getResults().add(state);
        state.setExperiment(census);
        
        session.beginTransaction();
        session.save(colony);
        session.save(census);
        session.save(state);
        session.getTransaction().commit();
    }
}
