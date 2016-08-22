package br.edu.ifpb.tsi.gcd.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;

import br.edu.ifpb.tsi.gcd.dao.ClasseDAO;
import br.edu.ifpb.tsi.gcd.dao.ClubeDAO;
import br.edu.ifpb.tsi.gcd.dao.DesbravadorDAO;
import br.edu.ifpb.tsi.gcd.dao.DistritalDAO;
import br.edu.ifpb.tsi.gcd.dao.PersistenceUtil;
import br.edu.ifpb.tsi.gcd.dao.UnidadeDAO;
import br.edu.ifpb.tsi.gcd.model.Classe;
import br.edu.ifpb.tsi.gcd.model.Clube;
import br.edu.ifpb.tsi.gcd.model.Desbravador;
import br.edu.ifpb.tsi.gcd.model.Distrital;
import br.edu.ifpb.tsi.gcd.model.Requisito;
import br.edu.ifpb.tsi.gcd.model.Unidade;


@ManagedBean(name="testeBean")
@SessionScoped
public class TesteBean {

	private boolean rodou=false;

	public void inserDadosTeste(){
		try {
			if(!this.rodou){
				EntityManager em = PersistenceUtil.getEntityManager();
				DesbravadorDAO daoDesb = new DesbravadorDAO(em);
				ClubeDAO daoClube = new ClubeDAO(em);
				DistritalDAO dao = new DistritalDAO(em);
				UnidadeDAO daoUnidade = new UnidadeDAO(em);
				ClasseDAO daoClasse = new ClasseDAO(em);

				Classe agrupada = new Classe("AGRUPADA");

				daoClasse.beginTransaction();
				daoClasse.insert(agrupada);
				daoClasse.commit();

				if(dao.findAll().isEmpty()){
					Distrital d = new Distrital("Administrador", "email@teste", "00000-0000", "ADMIN", "0");

					d.setClasseAgrupada(agrupada);

					dao.beginTransaction();
					dao.insert(d);
					dao.commit();
					System.out.println("Admin Cadastrado...");
				}

				Desbravador diretor = new Desbravador("Diretor teste", true, "diretor@gmail.com", "988888888", "DIRETOR", "teste1", "1");
				Desbravador diretor2 = new Desbravador("Diretor teste2", true, "diretor2@gmail.com", "988888888", "DIRETOR", "teste2", "2");
				Clube clube = new Clube("Clube Teste1", "Distrito Teste1", diretor);
				Clube clube2 = new Clube("Clube Teste2", "Distrito Teste1", diretor2);

				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				Date data1 = format.parse("01/07/1993");
				Date data2 = format.parse("20/12/1999");
				Date data3 = format.parse("15/08/1992");
				Date data4 = format.parse("05/07/1990");
				
				Desbravador d1 = new Desbravador("Desbravador 01", true, "Pai D01", "Mae D01", "DESBRAVADOR");
				d1.setDataNascimento(data1);
				d1.addRequisito(new Requisito("Vender biscoitos"));
				d1.addRequisito(new Requisito("Trabalho voluntário"));
				d1.addRequisito(new Requisito("Capturar pokemon"));
				d1.addRequisito(new Requisito("Correr 10km"));
				d1.addRequisito(new Requisito("Criar um site para o clube"));
				
				Desbravador d2 = new Desbravador("Desbravador 02", false, "Pai D02", "Mae D02", "CONSELHEIRO");
				d2.setDataNascimento(data2);
				d2.addRequisito(new Requisito("Vender biscoitos"));
				d2.addRequisito(new Requisito("Trabalho voluntário"));
				d2.addRequisito(new Requisito("Capturar pokemon"));
				d2.addRequisito(new Requisito("Correr 10km"));
				d2.addRequisito(new Requisito("Criar um site para o clube"));
				
				Desbravador d3 = new Desbravador("Desbravador 03", true, "Pai D03", "Mae D03", "DESBRAVADOR");
				d3.setDataNascimento(data3);
				d3.addRequisito(new Requisito("Vender biscoitos"));
				d3.addRequisito(new Requisito("Trabalho voluntário"));
				d3.addRequisito(new Requisito("Capturar pokemon"));
				d3.addRequisito(new Requisito("Correr 10km"));
				d3.addRequisito(new Requisito("Criar um site para o clube"));
				
				Desbravador d4 = new Desbravador("Desbravador 04", false, "Pai D04", "Mae D04", "DESBRAVADOR");
				d4.setDataNascimento(data4);
				d4.addRequisito(new Requisito("Vender biscoitos"));
				d4.addRequisito(new Requisito("Trabalho voluntário"));
				d4.addRequisito(new Requisito("Capturar pokemon"));
				d4.addRequisito(new Requisito("Correr 10km"));
				d4.addRequisito(new Requisito("Criar um site para o clube"));

				//GregorianCalendar nasc = new GregorianCalendar(1993, 2, 1);
				//d4.setDataNascimento(nasc.getTime());

				Unidade u = new Unidade("UNIDADE_TESTE");

				Classe amigo = new Classe("AMIGO");
				Classe companheiro = new Classe("COMPANHEIRO");
				Classe pesquisador = new Classe("PESQUISADOR");
				Classe pioneiro = new Classe("PIONEIRO");
				Classe excursionista = new Classe("EXCURSIONISTA");
				Classe guia = new Classe("GUIA");

				amigo.addMembro(d2);
				amigo.addMembro(d3);
				pioneiro.addMembro(d4);
				guia.addMembro(d1);

				daoClasse.beginTransaction();
				daoClasse.insert(amigo);
				daoClasse.insert(companheiro);
				daoClasse.insert(pesquisador);
				daoClasse.insert(pioneiro);
				daoClasse.insert(excursionista);
				daoClasse.insert(guia);
				daoClasse.insert(agrupada);
				daoClasse.commit();

				daoDesb.beginTransaction();
				daoDesb.insert(d1);
				daoDesb.insert(d2);
				daoDesb.insert(d3);
				daoDesb.insert(d4);
				daoDesb.commit();

				u.addMembro(d4);
				daoUnidade.beginTransaction();
				daoUnidade.insert(u);
				daoUnidade.commit();

				clube.addMembro(d1);
				clube.addMembro(d2);
				clube.addMembro(d3);
				clube.addMembro(d4);
				clube.addUnidade(u);
				clube.addClasse(amigo);
				clube.addClasse(companheiro);
				clube.addClasse(pesquisador);
				clube.addClasse(pioneiro);
				clube.addClasse(excursionista);
				clube.addClasse(guia);
				daoClube.beginTransaction();
				daoClube.insert(clube);
				daoClube.insert(clube2);
				daoClube.commit();

				this.rodou=true;
				System.out.println("Membros de teste inseridos!");

			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
}

