package io.github.ppissias.jerseyangularjpacrud.services.service;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.github.ppissias.jerseyangularjpacrud.services.Main;
import io.github.ppissias.jerseyangularjpacrud.services.model.TestItem;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("testitem")
public class TestItemResource {

    /**
     * Method handling HTTP GET JSON requests.     *
     * @return String that will be returned as a JSON response.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TestItem> getItems() {
        Main.logger.log(Level.FINE,"Received REST GET request for test items new");

        EntityManager em = Main.getEntityManagerFactory().createEntityManager();

        Query q = em.createQuery("select t from TestItem t");
        List<TestItem> expenseList = q.getResultList();
        for (TestItem expense : expenseList) {
            Main.logger.log(Level.FINE,expense.toString());
        }       
        em.close();
        
        return expenseList;
    }
    
    @GET
    @Path("{id}")    
    @Produces(MediaType.APPLICATION_JSON)
    public TestItem getItem(@PathParam("id") int id) {
        Main.logger.log(Level.FINE,"Received REST GET request for test item "+id);

        EntityManager em = Main.getEntityManagerFactory().createEntityManager();

        Query q = em.createQuery("select t from TestItem t where t.id="+id);
        List<TestItem> expenseList = q.getResultList();
        for (TestItem expense : expenseList) {
            Main.logger.log(Level.FINE,expense.toString());
        }       
        em.close();
        
        return expenseList.get(0);
    }
    
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TestItem> deleteItem(@PathParam("id") int id) {
        Main.logger.log(Level.INFO,"deleting id"+id);

        EntityManager em = Main.getEntityManagerFactory().createEntityManager();

    	TestItem ti = em.find(TestItem.class, id);
    	if (ti != null) {
	    	em.getTransaction().begin();
	    	em.remove(ti);
	    	em.getTransaction().commit();
    	}
    	
        Query q = em.createQuery("select t from TestItem t");
        List<TestItem> expenseList = q.getResultList();
        for (TestItem expense : expenseList) {
            Main.logger.log(Level.FINE,expense.toString());
        }       
        em.close();
        
        return getItems();
    }
    

    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public List<TestItem> updateTestItem(TestItem testItem) {
        EntityManager em = Main.getEntityManagerFactory().createEntityManager();
        if (testItem.getId() != null) {
        	TestItem expenseToUpdate = em.find(TestItem.class, testItem.getId());
        	em.getTransaction().begin();
        	
        	expenseToUpdate.setAmount(testItem.getAmount());
        	expenseToUpdate.setCategory(testItem.getCategory());
        	expenseToUpdate.setDescrpition(testItem.getDescrpition());
        	expenseToUpdate.setDetailedDescription(testItem.getDetailedDescription());
        	expenseToUpdate.setTransactionDate(testItem.getTransactionDate());
        	
        	em.getTransaction().commit();
        } else {
        	Main.logger.log(Level.WARNING, "Received update for expense:"+testItem+" but could not find itin the database");
        }       
        em.close();
        
        return getItems();
    }    
}
