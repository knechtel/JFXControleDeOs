package bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
@Entity
@NamedQueries({ @NamedQuery(name = "Cidade.findAll", query = "SELECT c FROM Cidade c") })
public class Cidade implements Serializable {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	private String nome;
	@OneToMany(fetch = FetchType.LAZY)
	private List<Cliente> listCliente;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Cliente> getListCliente() {
		return listCliente;
	}
	public void setListCliente(List<Cliente> listCliente) {
		this.listCliente = listCliente;
	}
	@Override
	public String toString() {
		return   id.toString() ;
	}
	
	
	

}
