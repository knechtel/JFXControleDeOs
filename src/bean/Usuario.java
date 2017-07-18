package model;

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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "http://mballem.com/")
@Entity
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")})
public class Usuario implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	 @XmlElement(name = "id", required = true)
	@Column(name = "id")
	private Integer id;
	 @XmlElement(name = "nome", required = true)
	private String nome;
	 @XmlElement(name = "senha", required = true)
	private String senha;
	@XmlElementWrapper(name = "OrdemDeServicos")
	@XmlElement(name = "OrdemDeServico")
	@OneToMany(fetch=FetchType.LAZY)
	private List<OrdemDeServico> listaOsCadastrada;
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
	public String getPassword() {
		return senha;
	}
	public void setPassword(String password) {
		this.senha = password;
	}
	public List<OrdemDeServico> getListaOsCadastrada() {
		return listaOsCadastrada;
	}
	public void setListaOsCadastrada(List<OrdemDeServico> listaOsCadastrada) {
		this.listaOsCadastrada = listaOsCadastrada;
	}
	
	
}
