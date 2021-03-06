package br.edu.ifpb.tsi.gcd.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Desbravador{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String nome;
	private String endereco;
	private String email;
	private String telefone;
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	private Boolean sexo;
	private String nomePai;
	private String nomeMae;
	
	private String cargo;
	private String login;
	private String senha;
	
	@ManyToOne
	private Unidade unidadeAtual;
	@ManyToOne
	private Clube clubeAtual;
	@ManyToOne
	private Classe classeAtual;
	
//	private List<Classe> classes;
	//especialidades
	
	@OneToMany(mappedBy="desbravador", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Requisito> requisitos;
	
	public Desbravador(){}

	public Desbravador(String nome, Boolean sexo, String email, String telefone, String cargo, String login, String senha) {
		super();
		this.nome = nome;
		this.sexo = sexo;
		this.email = email;
		this.telefone = telefone;
		this.cargo = cargo.toUpperCase();
		this.requisitos = new ArrayList<Requisito>();
		
		if(!this.cargo.equals("DIRETOR")){
			this.login = null;
			this.senha = null;
		}else{
			this.login = login;
			this.senha = senha;
		}
		
	}
	
	public Desbravador(String nome, Boolean sexo, String nomePai, String nomeMae, String cargo) {
		super();
		this.nome = nome;
		this.sexo = sexo;
		this.nomePai = nomePai;
		this.nomeMae = nomeMae;
		this.cargo = cargo.toUpperCase();
		this.requisitos = new ArrayList<Requisito>();
		
		if(!this.cargo.equals("DIRETOR")){
			this.login = null;
			this.senha = null;
		}
	}
	
	public void addRequisito(Requisito r){
		r.setDesbravador(this);
		this.requisitos.add(r);
	}
	
	public void updateRequisito(Requisito r){
		r.setDesbravador(this);
		for (Requisito requisito : this.requisitos) {
			if(requisito.getId() == r.getId())
				this.requisitos.set(this.requisitos.indexOf(requisito), r);
		}
	}
	
	public List<Requisito> listaRequisitoPendentes(){
		List<Requisito> pendentes = new ArrayList<Requisito>();
		for (Requisito r : this.requisitos) {
			if(!r.isStatus())
				pendentes.add(r);
		}
		return pendentes;
	}
	
	public List<Requisito> listaRequisitoCumprido(){
		List<Requisito> cumpridos = new ArrayList<Requisito>();
		for (Requisito r : this.requisitos) {
			if(r.isStatus())
				cumpridos.add(r);
		}
		return cumpridos;
	}
	
	public int verIdade(){
		
		Date hoje = new Date();    
		Calendar cal = Calendar.getInstance();    

		cal.setTime(hoje);    
		int day1 = cal.get(Calendar.DAY_OF_YEAR);    
		int ano1 = cal.get(Calendar.YEAR);    

		cal.setTime(this.dataNascimento);    
		int day2 = cal.get(Calendar.DAY_OF_YEAR);    
		int ano2 = cal.get(Calendar.YEAR);    

		int nAno = ano1 - ano2; 
		
		return nAno;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Boolean getSexo() {
		return sexo;
	}

	public void setSexo(Boolean sexo) {
		this.sexo = sexo;
	}

	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Unidade getUnidadeAtual() {
		return unidadeAtual;
	}

	public void setUnidadeAtual(Unidade unidadeAtual) {
		this.unidadeAtual = unidadeAtual;
	}

	public Clube getClubeAtual() {
		return clubeAtual;
	}

	public void setClubeAtual(Clube clubeAtual) {
		this.clubeAtual = clubeAtual;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Classe getClasseAtual() {
		return classeAtual;
	}

	public void setClasseAtual(Classe classeAtual) {
		this.classeAtual = classeAtual;
	}

	public List<Requisito> getRequisitos() {
		return requisitos;
	}

	public void setRequisitos(List<Requisito> requisitos) {
		this.requisitos = requisitos;
	}
	
}
