package controllerJpa;

import java.util.List;

import javax.persistence.EntityManager;

import bean.Usuario;
public class UsuarioJpaController {
	private EntityManager em = null;

	public UsuarioJpaController() {
		EntityManagerFactory ent = new EntityManagerFactory();
		em = ent.getEntityManager();
	}
	
	public List<Usuario> findAll() {
		try {
			return em.createNamedQuery("Usuario.findAll", Usuario.class)
					.getResultList();
		} finally {
			em.close();
		}
	}
	public void create(Usuario usuario) {
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
		em.close();
	}
}
