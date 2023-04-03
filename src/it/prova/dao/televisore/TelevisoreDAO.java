package it.prova.dao.televisore;

import java.time.LocalDate;

import it.prova.dao.IBaseDAO;
import it.prova.model.Televisore;

public interface TelevisoreDAO extends IBaseDAO<Televisore> {

	public Televisore ilTelevisorePiuGrande() throws Exception;

	public int quantiProdottiInUnIntervalloDiDate(LocalDate before, LocalDate after) throws Exception;

	public String[] MarcheDiTelevisoriUltimiSeiM() throws Exception;
}
