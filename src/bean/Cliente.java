package bean;

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

import org.jboss.jandex.Main;

import controllerJpa.ClienteJpaController;

@XmlRootElement
@XmlType(namespace = "http://mballem.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@NamedQueries({ @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c") })
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	private String nome;
	private String CPF;
	private String Endereco;
	private String email;
	private String rg;
	private String telefone;
	private String celular;
	private Cidade cidade;
	@OneToMany(fetch = FetchType.LAZY)
	@XmlElementWrapper(name = "OrdemDeServico")
	@XmlElement(name = "Os")
	private List<OrdemDeServico> listaOrdemServico;

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

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public String getEndereco() {
		return Endereco;
	}

	public void setEndereco(String endereco) {
		Endereco = endereco;
	}

	@Override
	public String toString() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public static void main(String[] args) {
		ClienteJpaController clienteJpa = new ClienteJpaController();

		Cliente c = new Cliente();
		c.setNome("Loreena");

		System.out.println(clienteJpa.create(c));

	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

}
