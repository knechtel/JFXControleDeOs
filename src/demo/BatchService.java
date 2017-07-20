package demo;

import java.util.List;

import javax.persistence.EntityManager;

import bean.Cliente;
import controllerJpa.EntityManagerFactory;


public class BatchService {
	
	 private EntityManager em = null;
	 public BatchService(){
			EntityManagerFactory ent = new EntityManagerFactory();
			em = ent.getEntityManager();
	 }
	
	 public List<Cliente> findByName(String name) {
	        return em.createQuery("select c FROM Cliente c WHERE c.nome LIKE :nome").setParameter("nome", "%"+  name + "%").getResultList();
	    }
	 
	 public static void main(String[] args) {
		BatchService b1 = new BatchService();
		System.out.println(b1.findByName("Vanessa"));
	}
}
