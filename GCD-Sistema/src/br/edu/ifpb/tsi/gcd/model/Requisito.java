package br.edu.ifpb.tsi.gcd.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Requisito {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private boolean status;
	private String descricao;
	@ManyToOne
	private Desbravador desbravador;
	
	public Requisito(){}
	
	public Requisito(String descricao){
		this.descricao = descricao;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Desbravador getDesbravador() {
		return desbravador;
	}

	public void setDesbravador(Desbravador desbravador) {
		this.desbravador = desbravador;
	}
	
}
