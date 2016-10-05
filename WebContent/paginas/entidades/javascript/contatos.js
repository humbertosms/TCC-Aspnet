// Variáveis globais
var btnAdicionarContato = document.querySelector("#btnAdicionarContato");
var IdContato = document.querySelector("input[name='IdContato']");
var NomeContato = document.querySelector("input[name='NomeContato']");
var TelefoneContato = document.querySelector("input[name='TelefoneContato']");
var FaxContato = document.querySelector("input[name='FaxContato']");
var CelularContato = document.querySelector("input[name='CelularContato']");
var EmailContato = document.querySelector("input[name='EmailContato']");
var CorpoTabelaContatos = document.getElementById("CorpoTabelaContatos");
var AlertaContatos = document.getElementById("AlertaContatos");

// Limpa os campos da linha de edição/inclusão de contatos
function limpaDadosContatos() {
	NomeContato.value = "";
	TelefoneContato.value = "";
	FaxContato.value = "";
	CelularContato.value = "";
	EmailContato.value = "";
	AlertaContatos.innerText = "";
}

// Objeto Contato
function Contato(id, nome, telefone, fax, celular, email) {
	this.id = id;
	this.nome = nome;
	this.telefone = telefone;
	this.fax = fax;
	this.celular = celular;
	this.email = email;

	// Cria uma nova linha na tabela de contatos
	this.criarLinha = function() {

		// Obtem uma referência para o template da linha de contatos
		var template = document.querySelector("#templateContatos");

		// Seta os dados do contato no template de referência
		template.content.querySelector("input[name='nomeContato']").value = this.nome;
		template.content.querySelector("input[name='telefoneContato']").value = this.telefone;
		template.content.querySelector("input[name='faxContato']").value = this.fax;
		template.content.querySelector("input[name='celularContato']").value = this.celular;
		template.content.querySelector("input[name='emailContato']").value = this.email;

		// Cria uma nova linha com base no modelo estabelecido no template
		var nova_linha = document.importNode(template.content, true);

		// Adiciona a nova linha ao corpo da tabela
		CorpoTabelaContatos.appendChild(nova_linha);

	}

	// Atualiza uma linha na tabela de contatos com os dados atuais deste objeto
	this.atualizalinha = function(obj) {
		// Captura a referência da TR (linha) pai do objeto td (Parâmetro "obj")
		var objTR = obj.parentNode.parentNode;

		// Seta os dados do contato na linha clicada
		objTR.querySelector("input[name='nomeContato']").value = this.nome;
		objTR.querySelector("input[name='telefoneContato']").value = this.telefone;
		objTR.querySelector("input[name='faxContato']").value = this.fax;
		objTR.querySelector("input[name='celularContato']").value = this.celular;
		objTR.querySelector("input[name='emailContato']").value = this.email;
	}
}

// Inclusão de contatos ---------------------------------------- INÍCIO
// ----------------------------------------
function adicionaContato(event) {
	event.preventDefault();

	if (NomeContato.value.trim()) {
		novo_contato = new Contato("", NomeContato.value,
				TelefoneContato.value, FaxContato.value, CelularContato.value,
				EmailContato.value);
		novo_contato.criarLinha();
		limpaDadosContatos();
	} else {
		AlertaContatos.innerText = "Nome do contato não preenchido";
	}
}

// Atribui ao btnAdicionarContato a rotina de inclusão dos contatos
btnAdicionarContato.onclick = adicionaContato;
// Inclusão de contatos ---------------------------------------- FIM
// ------------------------------------------

// Cancela Inclusão/Edição de contatos ------------------------- INÍCIO
// ----------------------------------------
function cancelaEdicaoContato(event) {
	event.preventDefault();

	limpaDadosContatos();

	// Volta a rotina padrão "btnAdicionarContato"
	btnAdicionarContato.textContent = "Adicionar";
	btnAdicionarContato.onclick = adicionaContato;
}

// Atribui ao btnCancelarContato, a rotina de cancelamento de inclusão dos
// contatos
btnCancelarContato.onclick = cancelaEdicaoContato;
// Cancela Inclusão/Edição de contatos --------------------------- FIM
// -----------------------------------------

// Exclusão de contatos ---------------------------------------- INÍCIO
// ---------------------------------------
function deletaContato(url, obj) {
	event.preventDefault();

	// Captura a referência da TR (linha) pai do objeto td (Parâmetro "obj")
	var objTR = obj.parentNode.parentNode;
	var idDoc = objTR.querySelector("input[name='idContato']").value;

	var r = confirm("Confirma a exclusão do contato?");
	if (r == false) {
		return;
	}

	// Se o contato não está gravado no banco ainda, deleta a linha atual sem
	// fazer nenhuma requisição
	if (idDoc == '') {
		removerLinha(obj);
		return;
	}

	var req = getHttpRequest();

	req.onreadystatechange = function() {
		if (req.readyState == 4) {
			if (req.status == 200) {
				// Se excluiu o registro do banco, remove a linha da tabela
				removerLinha(obj);
			}
		}
	}
	req.open("DELETE", url, true);
	req.send(null);
}

// Exclusão de contatos ---------------------------------------- FIM
// ------------------------------------------

// Edição de contatos ---------------------------------------- INÍCIO
// -----------------------------------------
function editaContato(obj) {
	event.preventDefault();

	// Captura a linha clicada
	var tr = obj.parentNode.parentNode;

	// Atribui os valores capurados, nos campos da linha de edição de contatos
	NomeContato.value = tr.querySelector("input[name='nomeContato']").value;
	TelefoneContato.value = tr.querySelector("input[name='telefoneContato']").value;
	FaxContato.value = tr.querySelector("input[name='faxContato']").value;
	CelularContato.value = tr.querySelector("input[name='celularContato']").value;
	EmailContato.value = tr.querySelector("input[name='emailContato']").value;

	// Atribui ao btnAdicionarContato o evento de atualização de contatos
	btnAdicionarContato.textContent = "Atualizar";
	btnAdicionarContato.onclick = function() {
		atualizaContato(obj)
	};
}

function atualizaContato(obj) {
	event.preventDefault();

	if (NomeContato.value.trim()) {
		novo_contato = new Contato("", NomeContato.value,
				TelefoneContato.value, FaxContato.value, CelularContato.value,
				EmailContato.value);
		novo_contato.atualizalinha(obj);
		limpaDadosContatos();
	} else {
		AlertaContatos.innerText = "Número do contato não preenchido";
	}
	;

	// Volta a rotina padrão "btnAdicionarContato"
	btnAdicionarContato.textContent = "Adicionar";
	btnAdicionarContato.onclick = adicionaContato;
}