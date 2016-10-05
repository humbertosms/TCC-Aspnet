// Variáveis globais
var btnAdicionarDadoBancario = document.querySelector("#btnAdicionarDadoBancario");
var IdDadoBancario = document.querySelector("input[name='IdDadoBancario']");
var BancoDadoBancario = document.querySelector("select[name='BancoDadoBancario']");
var AgenciaDadoBancario = document.querySelector("input[name='AgenciaDadoBancario']");
var AgenciaDigitoDadoBancario = document.querySelector("input[name='AgenciaDigitoDadoBancario']");
var ContaDadoBancario = document.querySelector("input[name='ContaDadoBancario']");
var ContaDigitoDadoBancario = document.querySelector("input[name='ContaDigitoDadoBancario']");
var CorpoTabelaDadosBancarios = document.getElementById("CorpoTabelaDadosBancarios");
var AlertaDadosBancarios = document.getElementById("AlertaDadosBancarios");

// Limpa os campos da linha de edição/inclusão de dados bancários
function limpaDadosDadosBancarios() {
	BancoDadoBancario.selectedIndex = 0;
	AgenciaDadoBancario.value = "";
	AgenciaDigitoDadoBancario.value = "";
	ContaDadoBancario.value = "";
	ContaDigitoDadoBancario.value = "";
	AlertaDadosBancarios.innerText = "";
}

// Objeto DadoBancario
function DadoBancario(id, banco, agencia, agenciaDigito, conta, contaDigito) {
	this.id = id;
	this.banco = banco;
	this.agencia = agencia;
	this.agenciaDigito = agenciaDigito;
	this.conta = conta;
	this.contaDigito = contaDigito;

	// Cria uma nova linha na tabela de dados bancários
	this.criarLinha = function() {

		// Obtem uma referência para o template da linha de dados bancários
		var template = document.querySelector("#templateDadosBancarios");

		// Seta os dados do dado bancário no template de referência
		template.content.querySelector("input[name='bancoDadoBancario']").value = this.banco;
		template.content.querySelector("input[name='agenciaDadoBancario']").value = this.agencia;
		template.content
				.querySelector("input[name='agenciaDigitoDadoBancario']").value = this.agenciaDigito;
		template.content.querySelector("input[name='contaDadoBancario']").value = this.conta;
		template.content.querySelector("input[name='contaDigitoDadoBancario']").value = this.contaDigito;

		// Cria uma nova linha com base no modelo estabelecido no template
		var nova_linha = document.importNode(template.content, true);

		// Adiciona a nova linha ao corpo da tabela
		CorpoTabelaDadosBancarios.appendChild(nova_linha);
	}

	// Atualiza uma linha na tabela de dados bancários com os dados atuais deste
	// objeto
	this.atualizalinha = function(obj) {
		// Captura a referência da TR (linha) pai do objeto td (Parâmetro "obj")
		var objTR = obj.parentNode.parentNode;

		// Seta os dados do dado bancário na linha clicada
		objTR.querySelector("input[name='bancoDadoBancario']").value = this.banco;
		objTR.querySelector("input[name='agenciaDadoBancario']").value = this.agencia;
		objTR.querySelector("input[name='agenciaDigitoDadoBancario']").value = this.agenciaDigito;
		objTR.querySelector("input[name='contaDadoBancario']").value = this.conta;
		objTR.querySelector("input[name='contaDigitoDadoBancario']").value = this.contaDigito;
	}
}

// Inclusão de dados bancários ---------------------------------------- INÍCIO
// ----------------------------------------
function adicionaDadoBancario(event) {
	event.preventDefault();

	if (AgenciaDadoBancario.value.trim()) {
		novo_dadobancario = new DadoBancario("", BancoDadoBancario.value,
				AgenciaDadoBancario.value, AgenciaDigitoDadoBancario.value,
				ContaDadoBancario.value, ContaDigitoDadoBancario.value);
		novo_dadobancario.criarLinha();
		limpaDadosDadosBancarios();
	} else {
		AlertaDadosBancarios.innerText = "Número do dado bancário não preenchido";
	}
}

// Atribui ao btnAdicionarDadoBancario a rotina de inclusão dos dados bancários
btnAdicionarDadoBancario.onclick = adicionaDadoBancario;
// Inclusão de dados bancários ---------------------------------------- FIM
// ------------------------------------------

// Cancela Inclusão/Edição de dados bancários ------------------------- INÍCIO
// ----------------------------------------
function cancelaEdicaoDadoBancario(event) {
	event.preventDefault();

	limpaDadosDadosBancarios();

	// Volta a rotina padrão "btnAdicionarDadoBancario"
	btnAdicionarDadoBancario.textContent = "Adicionar";
	btnAdicionarDadoBancario.onclick = adicionaDadoBancario;
}

// Atribui ao btnCancelarDadoBancario, a rotina de cancelamento de inclusão dos
// dados bancários
btnCancelarDadoBancario.onclick = cancelaEdicaoDadoBancario;
// Cancela Inclusão/Edição de dados bancários --------------------------- FIM
// -----------------------------------------

// Exclusão de dados bancários ---------------------------------------- INÍCIO
// ---------------------------------------
function deletaDadoBancario(url, obj) {
	event.preventDefault();

	// Captura a referência da TR (linha) pai do objeto td (Parâmetro "obj")
	var objTR = obj.parentNode.parentNode;
	var idDoc = objTR.querySelector("input[name='idDadoBancario']").value;

	var r = confirm("Confirma a exclusão da conta?");
	if (r == false) {
		return;
	}

	// Se o dado bancário não está gravado no banco ainda, deleta a linha atual
	// sem fazer nenhuma requisição
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

// Exclusão de dados bancários ---------------------------------------- FIM
// ------------------------------------------

// Edição de dados bancários ---------------------------------------- INÍCIO
// -----------------------------------------
function editaDadoBancario(obj) {
	event.preventDefault();

	// Captura a linha clicada
	var tr = obj.parentNode.parentNode;

	// Atribui os valores capurados logo acima, nos campos da linha de edição de
	// dados bancários
	BancoDadoBancario.value = tr
			.querySelector("input[name='bancoDadoBancario']").value;
	AgenciaDadoBancario.value = tr
			.querySelector("input[name='agenciaDadoBancario']").value;
	AgenciaDigitoDadoBancario.value = tr
			.querySelector("input[name='agenciaDigitoDadoBancario']").value;
	ContaDadoBancario.value = tr
			.querySelector("input[name='contaDadoBancario']").value;
	ContaDigitoDadoBancario.value = tr
			.querySelector("input[name='contaDigitoDadoBancario']").value;

	// Atribui ao btnAdicionarDadoBancario o evento de atualização de dados
	// bancários
	btnAdicionarDadoBancario.textContent = "Atualizar";
	btnAdicionarDadoBancario.onclick = function() {
		atualizaDadoBancario(obj)
	};
}

function atualizaDadoBancario(obj) {
	event.preventDefault();

	if (ContaDadoBancario.value.trim()) {
		novo_dadobancario = new DadoBancario("", BancoDadoBancario.value,
				AgenciaDadoBancario.value, AgenciaDigitoDadoBancario.value,
				ContaDadoBancario.value, ContaDigitoDadoBancario.value);
		novo_dadobancario.atualizalinha(obj);
		limpaDadosDadosBancarios();
	} else {
		AlertaDadosBancarios.innerText = "Número da conta não preenchido";
	}
	;

	// Volta a rotina padrão "btnAdicionarDadoBancario"
	btnAdicionarDadoBancario.textContent = "Adicionar";
	btnAdicionarDadoBancario.onclick = adicionaDadoBancario;
}