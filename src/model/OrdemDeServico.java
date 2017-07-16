package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
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
    @NamedQuery(name = "OrdemDeServico.findAll", query = "SELECT o FROM OrdemDeServico o")
    ,@NamedQuery(name = "OrdemDeServico.findByCliente", query = "FROM  OrdemDeServico o  JOIN FETCH  o.cliente WHERE  o.id = :id ")})
public class OrdemDeServico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	@XmlElement(name = "id", required = true)
	private Integer id;
	@XmlElement(name = "totalOrcamento", required = true)
	private float totalOrcamento;
	@XmlElement(name = "maoDeObra", required = true)
	private float maoDeObra;
	
	
	private Date dataEntrada;
	
	private Date dataSaida;
	
	private Date dataRetornoEntrada;
	private Date dataRetornoSaida;

	@ManyToOne(fetch = FetchType.LAZY)
	 @XmlElement(name = "Usuario", required = true)
	private Usuario usuario;

	
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.EAGER)
	@XmlElement(name = "cliente")
	private Cliente cliente;
	@JoinTable(name = "os_has_aparelho", 
			joinColumns = { @JoinColumn(name = "ordemDeServico_id", referencedColumnName = "id") 
	}, inverseJoinColumns = {
			@JoinColumn(name = "aparelho_id", referencedColumnName = "id") })
	@ManyToMany(fetch = FetchType.LAZY)
	@XmlElementWrapper(name = "Aparelhos")
	@XmlElement(name = "Aparelho")
	private List<Aparelho> listaAparelho;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public float getTotalOrcamento() {
		return totalOrcamento;
	}

	public void setTotalOrcamento(float totalOrcamento) {
		this.totalOrcamento = totalOrcamento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Aparelho> getListaAparelho() {
		return listaAparelho;
	}

	public void setListaAparelho(List<Aparelho> listaAparelho) {
		this.listaAparelho = listaAparelho;
	}

	public float getMaoDeObra() {
		return maoDeObra;
	}

	public void setMaoDeObra(float maoDeObra) {
		this.maoDeObra = maoDeObra;
	}

	@Override
	public String toString() {
		return id.toString() ;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public Date getDataRetornoEntrada() {
		return dataRetornoEntrada;
	}

	public void setDataRetornoEntrada(Date dataRetornoEntrada) {
		this.dataRetornoEntrada = dataRetornoEntrada;
	}

	public Date getDataRetornoSaida() {
		return dataRetornoSaida;
	}

	public void setDataRetornoSaida(Date dataRetornoSaida) {
		this.dataRetornoSaida = dataRetornoSaida;
	}

}
