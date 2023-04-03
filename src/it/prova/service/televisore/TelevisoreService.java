package it.prova.service.televisore;

import java.time.LocalDate;
import java.util.List;

import it.prova.dao.televisore.TelevisoreDAO;
import it.prova.model.Televisore;

public interface TelevisoreService {

	// questo mi serve per injection
	public void setTelevisoreDao(TelevisoreDAO userDao);

	public List<Televisore> listAll() throws Exception;

	public Televisore findById(Long idInput) throws Exception;

	public int aggiorna(Televisore input) throws Exception;

	public int inserisciNuovo(Televisore input) throws Exception;

	public int rimuovi(Long idDaRimouovere) throws Exception;

	public List<Televisore> findByExample(Televisore input) throws Exception;
	
	public Televisore ilTelevisorePiuGrande() throws Exception;

	public int quantiProdottiInUnIntervalloDiDate(LocalDate before, LocalDate after)throws Exception;
	
	public String[] marcheDiTelevisoriUltimiSeiM()throws Exception;
	
	//##########################################################################################
	//DA FARE PER ESERCIZIO: OVVIAMENTE BISOGNA RICREARE LA CONTROPARTE IN UserDAO e UserDAOImpl
//	public List<User> cercaTuttiQuelliCheUsernameIniziaCon(String iniziale) throws Exception; //testato
//	
//	public List<User> cercaTuttiQuelliCreatiPrimaDi(LocalDate dataConfronto) throws Exception; //testato
//	
//	public List<User> cercaPerCognomeENomeCheInziaCon(String cognomeInput, String inzialeNomeInput) throws Exception; //testato
//
//	public User accedi(String loginInput, String passwordInput) throws Exception;
}
