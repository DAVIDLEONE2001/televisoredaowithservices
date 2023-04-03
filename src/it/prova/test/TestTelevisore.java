package it.prova.test;

import java.time.LocalDate;
import java.util.List;

import it.prova.model.Televisore;
import it.prova.service.MyServiceFactory;
import it.prova.service.televisore.TelevisoreService;

public class TestTelevisore {

	public static void main(String[] args) throws Exception {
		
		TelevisoreService televisoreService = MyServiceFactory.getUserServiceImpl();
	
		System.out.println("In tabella ci sono " + televisoreService.listAll().size() + " elementi.");
//		testInserimentoNuovoTelevisore(televisoreService);
//		testFindById(televisoreService);
//		testAggiorna(televisoreService);
//		testDeleteNuovoTelevisore(televisoreService);
//		testIlTelevisorePiuGrande(televisoreService);
		
		System.out.println("In tabella ci sono " + televisoreService.listAll().size() + " elementi.");


	}

	private static void testInserimentoNuovoTelevisore(TelevisoreService televisoreService) throws Exception {
		System.out.println(".......testInserimentoNuovoTelevisore inizio.............");
		Televisore newTelevisoreInstance = new Televisore("Philips", "XJR2", 60, LocalDate.parse("2023-01-01"));
		
		if (televisoreService.inserisciNuovo(newTelevisoreInstance) != 1)
			throw new RuntimeException("testInserimentoNuovoTelevisore FAILED, televisore non inserito ");
		if (televisoreService.rimuovi(newTelevisoreInstance.getId()) != 1)
			throw new RuntimeException("testInserimentoNuovoTelevisore FAILED, televisore non rimosso ");

		System.out.println(".......testInserimentoNuovoTelevisore PASSED.............");
	}
	
	private static void testFindById(TelevisoreService televisoreService) throws Exception {
		System.out.println(".......testFindById inizio.............");
	
		if (televisoreService.findById(1L).getId()!= 1L)
			throw new RuntimeException("testFindById FAILED, televisore non ottenuto ");

		System.out.println(".......testFindById PASSED.............");
	}
	
	private static void testAggiorna(TelevisoreService televisoreService) throws Exception {
		System.out.println(".......testAggiorna inizio.............");
		Televisore newTelevisoreInstance = new Televisore(1L,"Philips", "XJRHH2", 60, LocalDate.parse("2023-01-01"));
		
		if (televisoreService.aggiorna(newTelevisoreInstance)!=1)
			throw new RuntimeException("testAggiorna FAILED, televisore non inserito ");

		System.out.println(".......testAggiorna PASSED.............");
		
	}
	
	private static void testDeleteNuovoTelevisore(TelevisoreService televisoreService) throws Exception {
		System.out.println(".......testInserimentoNuovoTelevisore inizio.............");
		Televisore newTelevisoreInstance = new Televisore("Philips", "XJR2", 60, LocalDate.parse("2023-01-01"));
		
		if (televisoreService.inserisciNuovo(newTelevisoreInstance) != 1)
			throw new RuntimeException("testInserimentoNuovoTelevisore FAILED, televisore non inserito ");
		List<Televisore>presentiSulDb = televisoreService.listAll();
		Televisore ultimoInserito = presentiSulDb.get(presentiSulDb.size()-1);
		if (televisoreService.rimuovi(ultimoInserito.getId())!=1)
			throw new RuntimeException("testInserimentoNuovoTelevisore FAILED, televisore non rimosso ");

		System.out.println(".......testInserimentoNuovoTelevisore PASSED.............");
	}
	
	private static void testIlTelevisorePiuGrande(TelevisoreService televisoreService) throws Exception {
		System.out.println(".......testInserimentoNuovoTelevisore inizio.............");
		Televisore newTelevisoreInstance = new Televisore("Philips", "XJR2", 600, LocalDate.parse("2023-01-01"));
		
		if (televisoreService.inserisciNuovo(newTelevisoreInstance) != 1)
			throw new RuntimeException("testInserimentoNuovoTelevisore FAILED, televisore non inserito ");
//		List<Televisore>presentiSulDb = televisoreService.listAll();
		Televisore piuGrande = televisoreService.ilTelevisorePiuGrande();
		System.out.println(piuGrande);
		if (televisoreService.rimuovi(piuGrande.getId())!=1)
			throw new RuntimeException("testInserimentoNuovoTelevisore FAILED, televisore non rimosso ");

		System.out.println(".......testInserimentoNuovoTelevisore PASSED.............");
	}
}
