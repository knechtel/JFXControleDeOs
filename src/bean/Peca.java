package bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.annotations.Cascade;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "http://mballem.com/")
@Entity

public class Peca implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2139326607058405027L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	@XmlElement(name = "id", required = true)
	private Integer id;
	@XmlElement(name = "nome", required = true)
	private String nome;
	@XmlElement(name = "preco", required = true)
	private float preco;	
	//nao dispara o cascade
	 @ManyToMany(mappedBy="listaPeca",fetch = FetchType.LAZY,cascade =CascadeType.ALL)
	 @XmlElementWrapper(name = "Aparelhos")
		@XmlElement(name = "Aparelho")
	private List<Aparelho> listaAparelho;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Aparelho> getListaAparelho() {
		return listaAparelho;
	}
	public void setListaAparelho(List<Aparelho> listaAparelho) {
		this.listaAparelho = listaAparelho;
	}
	@Override
	public String toString() {
		return "" + id + "";
	}
	
	

}
