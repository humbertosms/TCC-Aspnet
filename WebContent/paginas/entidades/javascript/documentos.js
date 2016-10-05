// Variáveis globais
var btnAdicionarDocumento = document.querySelector("#btnAdicionarDocumento");
var IdDocumento = document.querySelector("input[name='IdDocumento']");
var TipoDocumento = document.querySelector("select[name='TipoDocumento']");
var NumeroDocumento = document.querySelector("input[name='NumeroDocumento']");
var CorpoTabelaDocumentos = document.getElementById("CorpoTabelaDocumentos");
var AlertaDocumentos = document.getElementById("AlertaDocumentos");

// Limpa os campos da linha de edição/inclusão de documentos
function limpaDadosDocumentos() {
	TipoDocumento.selectedIndex = 0;
	NumeroDocumento.value = "";
	AlertaDocumentos.innerText = "";
}

// Objeto Documento
function Documento(id, tipo, numero) {
	this.id = id;
	this.tipo = tipo;
	this.numero = numero;

	// Cria uma nova linha na tabela de documentos
	this.criarLinha = function() {

		// Obtem uma referência para o template da linha de documentos
		var template = document.querySelector("#templateDocumentos");

		// Seta os dados do documento no template de referência
		template.content.querySelector("input[name='tipoDocumento']").value = this.tipo;
		template.content.querySelector("input[name='numeroDocumento']").value = this.numero;

		// Cria uma nova linha com base no modelo estabelecido no template
		var nova_linha = document.importNode(template.content, true);

		// Adiciona a nova linha ao corpo da tabela
		CorpoTabelaDocumentos.appendChild(nova_linha);
	}

	// Atualiza uma linha na tabela de documentos com os dados atuais deste
	// objeto
	this.atualizalinha = function(obj) {
		// Captura a referência da TR (linha) pai do objeto td (Parâmetro "obj")
		var objTR = obj.parentNode.parentNode;

		// Seta os dados do documento na linha clicada
		objTR.querySelector("input[name='tipoDocumento']").value = this.tipo;
		objTR.querySelector("input[name='numeroDocumento']").value = this.numero;
	}
}

// Inclusão de documentos ---------------------------------------- INÍCIO
// ----------------------------------------
function adicionaDocumento(event) {
	event.preventDefault();

	if (NumeroDocumento.value.trim()) {
		novo_documento = new Documento("", TipoDocumento.value,
				NumeroDocumento.value);
		novo_documento.criarLinha();
		limpaDadosDocumentos();
	} else {
		AlertaDocumentos.innerText = "Número do documento não preenchido";
	}
}

// Atribui ao btnAdicionarDocumento a rotina de inclusão dos documentos
btnAdicionarDocumento.onclick = adicionaDocumento;
// Inclusão de documentos ---------------------------------------- FIM
// ------------------------------------------

// Cancela Inclusão/Edição de documentos ------------------------- INÍCIO
// ----------------------------------------
function cancelaEdicaoDocumento(event) {
	event.preventDefault();

	limpaDadosDocumentos();

	// Volta a rotina padrão "btnAdicionarDocumento"
	btnAdicionarDocumento.textContent = "Adicionar";
	btnAdicionarDocumento.onclick = adicionaDocumento;
}

// Atribui ao btnCancelarDocumento, a rotina de cancelamento de inclusão dos
// documentos
btnCancelarDocumento.onclick = cancelaEdicaoDocumento;
// Cancela Inclusão/Edição de documentos --------------------------- FIM
// -----------------------------------------

// Exclusão de documentos ---------------------------------------- INÍCIO
// ---------------------------------------
function deletaDocumento(url, obj) {
	event.preventDefault();

	// Captura a referência da TR (linha) pai do objeto td (Parâmetro "obj")
	var objTR = obj.parentNode.parentNode;
	var idDoc = objTR.querySelector("input[name='idDocumento']").value;

	var r = confirm("Confirma a exclusão do documento?");
	if (r == false) {
		return;
	}

	// Se o documento não está gravado no banco ainda, deleta a linha atual sem
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

// Exclusão de documentos ---------------------------------------- FIM
// ------------------------------------------

// Edição de documentos ---------------------------------------- INÍCIO
// -----------------------------------------
function editaDocumento(obj) {
	event.preventDefault();

	// Captura a linha clicada
	var tr = obj.parentNode.parentNode;

	// Atribui os valores capurados logo acima, nos campos da linha de edição de
	// documentos
	TipoDocumento.value = tr.querySelector("input[name='tipoDocumento']").value;
	NumeroDocumento.value = tr.querySelector("input[name='numeroDocumento']").value;

	// Atribui ao btnAdicionarDocumento o evento de atualização de documentos
	btnAdicionarDocumento.textContent = "Atualizar";
	btnAdicionarDocumento.onclick = function() {
		atualizaDocumento(obj)
	};
}

function atualizaDocumento(obj) {
	event.preventDefault();

	if (NumeroDocumento.value.trim()) {
		novo_documento = new Documento("", TipoDocumento.value,
				NumeroDocumento.value);
		novo_documento.atualizalinha(obj);
		limpaDadosDocumentos();
	} else {
		AlertaDocumentos.innerText = "Número do documento não preenchido";
	}
	;

	// Volta a rotina padrão "btnAdicionarDocumento"
	btnAdicionarDocumento.textContent = "Adicionar";
	btnAdicionarDocumento.onclick = adicionaDocumento;
}
