package br.com.simplifiqueerp.service;

import br.com.simplifiqueerp.entidade.Documento;
import br.com.simplifiqueerp.persistencia.DocumentoDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DocumentoService{
	private DocumentoDAO db = new DocumentoDAO();

	// Lista todos os documentos de um id entidade
	public List<Documento>getDocumentos(Long idEntidade){
		try {
			List<Documento> Documentos = db.list(idEntidade);
			return Documentos;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Documento>();
		}
	}

	// Deleta os documentos de um id entidade
	public boolean deleteFromIdEntidade(Long idEntidade){
		try {
			return db.deleteByIdEntidade(idEntidade);
		} catch (SQLException e) {
			return false;
		}
	}

	// Deleta um documento
	public boolean delete(Documento documento){
		try {
			return db.delete(documento);
		} catch (SQLException e) {
			return false;
		}
	}

	// Salva ou atualiza o documento
	public boolean save(Documento documento){
		try {
			db.save(documento);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
}
