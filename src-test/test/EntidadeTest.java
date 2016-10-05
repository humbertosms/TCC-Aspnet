package test;

import br.com.simplifiqueerp.entidade.Entidade;
import br.com.simplifiqueerp.service.EntidadeService;
import junit.framework.TestCase;

import java.time.LocalDate;
import java.util.List;

public class EntidadeTest extends TestCase {

	private EntidadeService entidadeService = new EntidadeService();

	public void testListaEntidades() {

		List<Entidade> entidades = entidadeService.list();

		// Valida se encontrou algo
		assertTrue(entidades.size() > 1);
	}

	public void testSalvarDeletarEntidade() {

		// Cria uma nova entidade
		Entidade entidade = new Entidade();
		entidade.setObservacao("Observa��o da entidade inserida pelo JUNIT");
		entidade.setCadastro(LocalDate.now());

		// Salva a entidade criada
		entidadeService.save(entidade);

		// Id da entidade salva
		Long id = entidade.getId();
		assertNotNull(id);

		// Busca a entidade no banco para confirmar que foi salva
		entidade = entidadeService.getById(id);
		assertEquals("Observa��o da entidade inserida pelo JUNIT", entidade.getObservacao());

		// Atualiza a observação da entidade
		entidade.setObservacao("Novo texto da observa��o da entidade inserido pelo JUNIT");
		entidadeService.save(entidade);

		// Busca a entidade no banco novamente para confirmar a atualização
		entidade = entidadeService.getById(id);
		assertEquals("Novo texto da observa��o da entidade inserido pelo JUNIT", entidade.getObservacao());

		// Deleta a entidade
		entidadeService.delete(entidade);

		// Busca a entidade no banco novamente para confirmar a exclusão
		entidade = entidadeService.getById(id);
		assertNull(entidade);
	}

}
