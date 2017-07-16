package controllerJpa;


import javax.persistence.EntityManager;

import model.Peca;
public class PecaJpaController {

	private EntityManager em = null;

	public PecaJpaController() {
		EntityManagerFactory ent = new EntityManagerFactory();
		em = ent.getEntityManager();
	}

	

	public void create(Peca peca) {
		em.getTransaction().begin();
		em.persist(peca);
		em.getTransaction().commit();
		em.close();
		
	}

}
