/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerJpa;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;


/**
 *
 * @author Maiquel
 */
public class EntityManagerFactory {

    private EntityManager em = null;

    public EntityManagerFactory() {
        javax.persistence.EntityManagerFactory emf = Persistence.createEntityManagerFactory("BD2Test");
        this.em = emf.createEntityManager();
    }

    public EntityManager getEntityManager() {
        return em;
    }
    
   public static void main(String[] args) {
	EntityManagerFactory em = new EntityManagerFactory();
	
}
}
