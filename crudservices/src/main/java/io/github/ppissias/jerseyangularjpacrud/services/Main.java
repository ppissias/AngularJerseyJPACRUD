package io.github.ppissias.jerseyangularjpacrud.services;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import io.github.ppissias.jerseyangularjpacrud.services.model.TestItem;

import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Main class.
 *
 */
public class Main { 
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8080/myapp/";

    //JPA persistence unit name - defined in persistence.xml
    private static final String PERSISTENCE_UNIT_NAME = "testitem";
    
    //JPA stuff
    private static EntityManagerFactory factory;       
    public static EntityManagerFactory getEntityManagerFactory() {
    	return factory;
    }
    
    public static final Logger logger = Logger.getLogger("services");
    
    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        final ResourceConfig rc = new ResourceConfig().packages("io.github.ppissias.jerseyangularjpacrud.services.service");

        //create JPA factory
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void performJPAAction() {
        EntityManager em = getEntityManagerFactory().createEntityManager();
        // read the existing entries and write to console
        Query q = em.createQuery("select t from TestItem t");
        List<TestItem> expenseList = q.getResultList();
        for (TestItem expense : expenseList) {
            Main.logger.log(Level.FINE,expense.toString());
        }
        Main.logger.log(Level.FINE,"Size: " + expenseList.size());

        //insert one
    	em.getTransaction().begin();
    	em.persist(new TestItem(new Date().toString(),"description","detailed description", 100.1234, "category"));
        em.getTransaction().commit();

        em.close();    	
    }
    
    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
    	final ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.FINEST);
        consoleHandler.setFormatter(new SimpleFormatter());

        logger.setLevel(Level.FINEST);
        logger.addHandler(consoleHandler);
        
    	final HttpServer server = startServer();
        
        Main.logger.log(Level.FINE,"performing JPA action");
        performJPAAction();
        Main.logger.log(Level.FINE,String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        System.in.read();
        server.stop();
    }
}

