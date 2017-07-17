package controllerJpa;

import java.util.List;

import javax.persistence.EntityManager;

import org.jboss.jandex.Main;

import model.OrdemDeServico;

public class OrdemDeServicoJpaController {
	private EntityManager em = null;

	public OrdemDeServicoJpaController() {
		EntityManagerFactory ent = new EntityManagerFactory();
		em = ent.getEntityManager();
	}
	
	public List<OrdemDeServico> findAll() {
		try {
			return em.createNamedQuery("OrdemDeServico.findAll", OrdemDeServico.class)
					.getResultList();
		} finally {
			em.close();
		}
	}
	
	public OrdemDeServico findByCliente(Integer id) {
		try {
			return em.createNamedQuery("OrdemDeServico.findByCliente",OrdemDeServico.class).setParameter("id", id).getSingleResult();
					
		} finally {
			em.close();
		}
	}
	public OrdemDeServico findByAparelho(Integer id) {
		try {
			return em.createNamedQuery("OrdemDeServico.findByAparelho",OrdemDeServico.class).setParameter("id", id).getSingleResult();
					
		} finally {
			em.close();
		}
	}
	public void create(OrdemDeServico os) {
		em.getTransaction().begin();
		em.persist(os);
		em.getTransaction().commit();
		em.close();
	}
	
	
	public void edit(OrdemDeServico os) {
		em.getTransaction().begin();
		em.merge(os);
		em.getTransaction().commit();
		em.close();
	}
	
	public static void main(String[] args) {
		OrdemDeServicoJpaController osJpa = new OrdemDeServicoJpaController();
		
		System.out.println(osJpa.findAll().size());
	}
	
	

}
