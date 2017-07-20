package controllerJpa;

import java.util.List;

import javax.persistence.EntityManager;

import bean.Cidade;

public class CidadeJpaController {
	 private EntityManager em = null;
	
	public CidadeJpaController(){
		 EntityManagerFactory ent = new EntityManagerFactory();
	        em = ent.getEntityManager();
	}
	
	public void create(Cidade cidade) {
		em.getTransaction().begin();
		em.persist(cidade);
		em.getTransaction().commit();
		em.close();
	}
	
	public void edit(Cidade cidade) {
		em.getTransaction().begin();
		em.merge(cidade);
		em.getTransaction().commit();
		em.close();
	}
	
    public void delete(Cidade c) {
        em.getTransaction().begin();
        c = em.merge(c);
        em.remove(c);
        em.getTransaction().commit();
        em.close();

    }
	
	public List<Cidade> findAll() {
		try {
			return em.createNamedQuery("Cidade.findAll", Cidade.class)
					.getResultList();
		} finally {
			em.close();
		}
	}
	
	
}
