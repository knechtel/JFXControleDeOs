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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
    @NamedQuery(name = "Aparelho.findAll", query = "SELECT a FROM Aparelho a"),
    @NamedQuery(name = "Aparelho.findById", query = "SELECT a FROM Aparelho a WHERE a.id = :id"),
    @NamedQuery(name = "Aparelho.findByListaPeca", query = "SELECT a FROM Aparelho a WHERE a.id = :id")})
public class Aparelho implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
    @XmlElement(name = "id", required = true)
	private Integer id;
	@XmlElement(name = "marca", required = true)
	private String marca;
	
	@XmlElement(name = "modelo", required = true)
	private String modelo;
	@XmlElement(name = "serial", required = true)
	private String serial;
	private boolean pronto;
	private boolean autorizado;
	private Double maoDeObra;
	@XmlElementWrapper(name = "Pecas")
	@XmlElement(name = "Peca")
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "aparelho_has_peca",
     joinColumns = {
    @JoinColumn(name = "aparelho_id", referencedColumnName = "id")},
	inverseJoinColumns = {
    @JoinColumn(name = "peca_id", referencedColumnName = "id")})
	private List<Peca> listaPeca;
	@XmlElementWrapper(name = "OrdemDeServicos")
	 @XmlElement(name = "OrdemDeServico")
	@ManyToMany(fetch = FetchType.LAZY)
	private List<OrdemDeServico> listaOs;
	
	
	
	public List<OrdemDeServico> getListaOs() {
		return listaOs;
	}
	public void setListaOs(List<OrdemDeServico> listaOs) {
		this.listaOs = listaOs;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public List<Peca> getListaPeca() {
		return listaPeca;
	}
	public void setListaPeca(List<Peca> listaPeca) {
		this.listaPeca = listaPeca;
	}
	
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public boolean isPronto() {
		return pronto;
	}
	public void setPronto(boolean pronto) {
		this.pronto = pronto;
	}
	public boolean isAutorizado() {
		return autorizado;
	}
	public void setAutorizado(boolean autorizado) {
		this.autorizado = autorizado;
	}
	public Double getMaoDeObra() {
		return maoDeObra;
	}
	public void setMaoDeObra(Double maoDeObra) {
		this.maoDeObra = maoDeObra;
	}

}
