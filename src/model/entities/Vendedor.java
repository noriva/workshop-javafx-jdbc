package model.entities;

import java.io.Serializable;
import java.util.Date;

public class Vendedor implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer idVendedor;
	private String nomeVendedor;
	private String emailVendedor;
	private Date dataDeNascimento;
	private Double salarioBase;
	
	private Departamento departamento;
	
	public Vendedor() {
		// TODO Auto-generated constructor stub
	}

	public Vendedor(Integer idVendedor, String nomeVendedor, String emailVendedor, Date dataDeNascimento,
			Double salarioBase, Departamento departamento) {
		
		this.idVendedor = idVendedor;
		this.nomeVendedor = nomeVendedor;
		this.emailVendedor = emailVendedor;
		this.dataDeNascimento = dataDeNascimento;
		this.salarioBase = salarioBase;
		this.departamento = departamento;
	}

	public Integer getIdVendedor() {
		return idVendedor;
	}

	public void setIdVendedor(Integer idVendedor) {
		this.idVendedor = idVendedor;
	}

	public String getNomeVendedor() {
		return nomeVendedor;
	}

	public void setNomeVendedor(String nomeVendedor) {
		this.nomeVendedor = nomeVendedor;
	}

	public String getEmailVendedor() {
		return emailVendedor;
	}

	public void setEmailVendedor(String emailVendedor) {
		this.emailVendedor = emailVendedor;
	}

	public Date getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(Date dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public Double getSalarioBase() {
		return salarioBase;
	}

	public void setSalarioBase(Double salarioBase) {
		this.salarioBase = salarioBase;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idVendedor == null) ? 0 : idVendedor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vendedor other = (Vendedor) obj;
		if (idVendedor == null) {
			if (other.idVendedor != null)
				return false;
		} else if (!idVendedor.equals(other.idVendedor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vendedor [idVendedor=" + idVendedor + ", nomeVendedor=" + nomeVendedor + ", emailVendedor="
				+ emailVendedor + ", dataDeNascimento=" + dataDeNascimento + ", salarioBase=" + salarioBase
				+ ", departamento=" + departamento + "]";
	}
	
	
	
}
