package controllerJpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import model.Aparelho;
import model.Peca;

public class AparelhoJpaController {
	
	 private EntityManager em = null;
	 
	 public AparelhoJpaController(){
		   EntityManagerFactory ent = new EntityManagerFactory();
	        em = ent.getEntityManager();
	 }
	
	public Aparelho findById(Integer id){
		   try {
	            return em.find(Aparelho.class, id);
	        } finally {
	            em.close();
	        }
	
	}
	
	public Aparelho findPecas(Aparelho a){
	try{
		return	 em.createQuery("FROM Aparelho a join FETCH a.listaPeca peca where a.id =?",Aparelho.class)
			.setParameter(1, a.getId()).getSingleResult();	
	  } finally {
          em.close();
      }
		
	} 
	public void create(Aparelho aparelho) {
		em.getTransaction().begin();
		em.persist(aparelho);
		em.getTransaction().commit();
		em.close();
	}
	
	public void merge(Aparelho aparelho) {
		em.getTransaction().begin();
		em.merge(aparelho);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Aparelho> findAll() {
        try {
            return em.createNamedQuery("Aparelho.findAll",Aparelho.class).getResultList();
        } finally {
            em.close();
        }
    }
	
	public static void main(String[] args) {
		AparelhoJpaController apJpa1= new AparelhoJpaController();
		Aparelho a = apJpa1.findById(29);
		AparelhoJpaController apJpa= new AparelhoJpaController();
		System.out.println(apJpa.findPecas(a).getListaPeca().size());
		

		
		
		
		
//		Peca peca = new Peca();
//		peca.setNome("Flay back");
//		peca.setPreco((float) 50.0);
//		PecaJpaController pecaJpa = new PecaJpaController();
//		pecaJpa.create(peca);
//		List<Peca> listaPeca= new ArrayList<Peca>();
//		listaPeca.add(peca);
//		a.setListaPeca(listaPeca);
//		AparelhoJpaController apJpa2= new AparelhoJpaController();
//		apJpa2.merge(a);
	}

}
