package io.github.ppissias.jerseyangularjpacrud.services;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import io.github.ppissias.jerseyangularjpacrud.services.model.TestItem;


/**
 * Main class that starts the embedded HTTP server
 * @author Petros Pissias
 *
 */
public class Main { 
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8080/myapp/";

    //JPA persistence unit name - defined in persistence.xml
    private static final String PERSISTENCE_UNIT_NAME = "testitem";
    
    //JPA stuff
    public static final EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    
    public static EntityManager getEntityManager() {
    	return factory.createEntityManager();
    }
    
    //logger
    public static final Logger logger = Logger.getLogger("services");
    
    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        final ResourceConfig rc = new ResourceConfig().packages("io.github.ppissias.jerseyangularjpacrud.services.service");
        
        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Inserts lists records and inserts a test record
     */
    public static void testJPA() {
        // read the existing entries and write to console
    	EntityManager em = Main.getEntityManager();
        TypedQuery<TestItem> q = em.createQuery("select t from TestItem t",TestItem.class);
        List<TestItem> expenseList = q.getResultList();
        for (TestItem expense : expenseList) {
            Main.logger.info(expense.toString());
        }
        Main.logger.info("Size: " + expenseList.size());

        //insert one item
    	em.getTransaction().begin();
    	em.persist(new TestItem("This is a random number:"+new Random().nextInt()));
        em.getTransaction().commit();
        em.close();    	
    }
    

    /**
     * Main method that starts the application
     * @param args
     * @throws IOException
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        
    	final HttpServer server = startServer();
        
    	Main.logger.info("performing test JPA action");
        testJPA();
        
        Main.logger.info("Jersey app started with WADL available at "+BASE_URI);
       
    }
}

