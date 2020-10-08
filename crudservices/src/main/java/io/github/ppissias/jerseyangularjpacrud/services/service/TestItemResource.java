package io.github.ppissias.jerseyangularjpacrud.services.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.github.ppissias.jerseyangularjpacrud.services.Main;
import io.github.ppissias.jerseyangularjpacrud.services.model.TestItem;

/**
 * This is the main resource that is exposed via the interfaces below
 * 
 * @author Petros Pissias
 *
 */
@Path("testitem")
public class TestItemResource {


	/**
	 * the GET request returns the list of items "/"
	 * @return
	 */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TestItem> getItems() {
        Main.logger.info("Received REST GET request for test items");

        EntityManager em = Main.getEntityManager();
        TypedQuery<TestItem> q = em.createQuery("select t from TestItem t",TestItem.class);
        List<TestItem> expenseList = q.getResultList();
        StringBuffer sb = new StringBuffer();
        for (TestItem expense : expenseList) {
        	sb.append(expense+"\n");
        }       
        Main.logger.info("items:\n"+sb.toString());
        em.close();
        
        return expenseList;
    }
    
    /**
     * Get the details of a single item "/id"
     * @param id
     * @return the list of items
     */
    @GET
    @Path("{id}")    
    @Produces(MediaType.APPLICATION_JSON)
    public TestItem getItem(@PathParam("id") int id) {
    	Main.logger.info("will return test item with id:"+id);
        
        //create new entity manager
        EntityManager em = Main.getEntityManager();
        
        TypedQuery<TestItem> q = em.createQuery("select t from TestItem t where t.id="+id, TestItem.class);        
        List<TestItem> itemList = q.getResultList();
        TestItem testItem;
        if (itemList.size() == 0) {
            testItem = new TestItem("");
            em.close();    
            Main.logger.warning("Test item with id:"+id+" does not exist");
            return testItem;        	
        } else {
            testItem = itemList.get(0);     
            em.close();    
            Main.logger.info("Item:"+testItem);                   
            return testItem;
        }

    }
    
    /**
     * Deletes an item with the specified id
     * @param id
     * @return the new list of items
     */
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TestItem> deleteItem(@PathParam("id") int id) {
    	Main.logger.info("will delete test item with id:"+id);

        //create new entity manager
        EntityManager em = Main.getEntityManager();

    	TestItem ti = em.find(TestItem.class, id);
    	if (ti != null) {
	    	em.getTransaction().begin();
	    	em.remove(ti);
	    	em.getTransaction().commit();
    	}
    	em.close();
    	
        return getItems();
    }
    
    /**
     * Updates an item
     * @param testItem
     * @return the new list of items
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateTestItem(TestItem testItem) {
    	Main.logger.info("will update test item:"+testItem);
    	//create new entity manager instance
        EntityManager em = Main.getEntityManager();
        
        if (testItem.getId() != null) {
        	TestItem expenseToUpdate = em.find(TestItem.class, testItem.getId());
        	if (expenseToUpdate != null) {
	        	em.getTransaction().begin();
	        	expenseToUpdate.setDescrpition(testItem.getDescrpition());        
	        	em.getTransaction().commit();
        	} else {
        		 Main.logger.warning("Received update for expense:"+testItem+" which is not in the database");
        	}
        } else {
        	 Main.logger.warning("Received update for expense:"+testItem+" with null id");
        }       
        em.close();
        
        //return getItems();
    }    
    
    /**
     * Used to insert a new item
     * @param testItem
     * @return the new list of items
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<TestItem> addTestItem(TestItem testItem) {
        Main.logger.info("will add testItem "+testItem);
    	
        EntityManager em = Main.getEntityManager();
      	em.getTransaction().begin();
       	em.persist(testItem);
       	em.getTransaction().commit();
        em.close();
        
        return getItems();
    } 
    

}
