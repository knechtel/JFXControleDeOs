package controllerJpa;

import java.util.List;

import javax.persistence.EntityManager;

import bean.Cliente;

public class ClienteJpaController {

	private EntityManager em = null;

	public Cliente findById(Integer id) {
		try {
			return em.find(Cliente.class, id);
		} finally {
			em.close();
		}

	}

	public ClienteJpaController() {
		EntityManagerFactory ent = new EntityManagerFactory();
		em = ent.getEntityManager();
	}

	public List<Cliente> findAll() {
		try {
			return em.createQuery("from Cliente", Cliente.class).getResultList();
		} finally {
			em.close();
		}
	}

	public Cliente create(Cliente cliente) {
		em.getTransaction().begin();
		em.persist(cliente);
		em.getTransaction().commit();
		em.close();
		
		return cliente;
	}

	public void edit(Cliente cliente) {
		em.getTransaction().begin();
		em.merge(cliente);
		em.getTransaction().commit();
		em.close();
	}

	public List<Cliente> findByName(String name) {
		return em.createQuery("select c FROM Cliente c WHERE c.nome LIKE :nome").setParameter("nome", name + "%")
				.getResultList();
	}

	public static void main(String[] args) {
		ClienteJpaController c = new ClienteJpaController();

		System.out.println("aqui aqui aqui aqui aqui");
		// System.out.println(c.findAll());
		Cliente cl = c.findAll().get(0);

		System.out.println("test  " + cl.getNome());
	}

}
