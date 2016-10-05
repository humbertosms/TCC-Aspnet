// Variáveis globais
var btnAdicionarEndereco = document.querySelector("#btnAdicionarEndereco");
var IdEndereco = document.querySelector("input[name='IdEndereco']");
var PrincipalEndereco = document.getElementById("PrincipalEndereco");
var CEPEndereco = document.querySelector("input[name='CEPEndereco']");
var LogradouroEndereco = document.querySelector("input[name='LogradouroEndereco']");
var NumeroEndereco = document.querySelector("input[name='NumeroEndereco']");
var ComplementoEndereco = document.querySelector("input[name='ComplementoEndereco']");
var BairroEndereco = document.querySelector("input[name='BairroEndereco']");
var UFEndereco = document.querySelector("select[name='UFEndereco']");
var CidadeEndereco = document.querySelector("select[name='CidadeEndereco']");
var IDCidadeEndereco = document.querySelector("input[name='IDCidadeEndereco']");
var ReferenciaEndereco = document.querySelector("input[name='ReferenciaEndereco']");
var CorpoTabelaEnderecos = document.getElementById("CorpoTabelaEnderecos");
var AlertaEnderecos = document.getElementById("AlertaEnderecos");

// Limpa os campos da linha de edição/inclusão de endereços
function limpaDadosEnderecos() {
	PrincipalEndereco.checked = false;
	CEPEndereco.value = "";
	LogradouroEndereco.value = "";
	NumeroEndereco.value = "";
	ComplementoEndereco.value = "";
	BairroEndereco.value = "";
	UFEndereco.value = 'Selecione';
	UFEndereco.selectedIndex = 0;
	CidadeEndereco.value = '';
	CidadeEndereco.options.length = 0;
	CidadeEndereco.selectedIndex = 0;
	IDCidadeEndereco.value = "";
	ReferenciaEndereco.value = "";
	AlertaEnderecos.innerText = "";
}

function testaEndereco() {
	if (!LogradouroEndereco.value.trim()) {
		AlertaEnderecos.innerText = "Logradouro do endereço não preenchido";
		LogradouroEndereco.focus();
		return false;
	}
	if (UFEndereco.selectedIndex == 0) {
		AlertaEnderecos.innerText = "UF do endereço não preenchida";
		UFEndereco.focus();
		return false;
	}
	if (CidadeEndereco.selectedIndex == 0) {
		AlertaEnderecos.innerText = "Cidade do endereço não preenchida";
		CidadeEndereco.focus();
		return false;
	}
	return true;
}

// Busca e seta a lista de cidades por uf na select CidadeEndereco
function setListaCidades(uf, event, cidadepadrao) {
	event.preventDefault();

	CidadeEndereco.options.length = 0;

	if (UFEndereco.selectedIndex == 0) {
		return;
	}

	var req = getHttpRequest();
	var url = "http://" + window.location.host + '/Simplifique_ERP/cidades/'
			+ uf;

	req.onreadystatechange = function() {
		if (req.readyState == 4) {
			if (req.status == 200) {
				// Converte o retorno em JSON para um objeto válido
				var cidades = JSON.parse(req.responseText);

				var option = document.createElement("option");
				option.text = 'Selecione';
				option.value = '';
				CidadeEndereco.add(option);

				for (var i = 0; i < cidades.length; i++) {

					var option = document.createElement("option");
					option.text = cidades[i].nome;
					option.value = cidades[i].id;
					CidadeEndereco.add(option);
				}
				if (cidadepadrao) {
					setIndexCidade(cidadepadrao);
				}
			}
		}
	}
	req.open("GET", url);
	req.send(null);
}

// Busca endereço baseado no CEP
function preencheEndereco(event) {
	event.preventDefault();

	var req = getHttpRequest();
	var cep = document.querySelector("input[name='CEPEndereco']");
	var url = "https://viacep.com.br/ws/" + cep.value + "/json/";
	req.onreadystatechange = function() {
		if (req.readyState == 4) {
			if (req.status == 200) {
				// Converte o retorno em JSON para um objeto válido
				var retorno = JSON.parse(req.responseText);

				LogradouroEndereco.value = retorno.logradouro;
				BairroEndereco.value = retorno.bairro;
				UFEndereco.value = retorno.uf;

				setListaCidades(UFEndereco.value, event, retorno.localidade);

				IDCidadeEndereco.value = retorno.ibge;
			}
		}
	}

	req.open("GET", url, true);
	req.send(null);
}

function setIndexCidade(cidade) {

	var x = CidadeEndereco.options.length;
	for (var i = 0; i < x; i++) {
		if (CidadeEndereco.options[i].text == cidade) {
			CidadeEndereco.selectedIndex = i;
			return;
		}
	}
}

// Objeto Endereço
function Endereco(id, principal, cep, logradouro, numero, complemento, bairro,
		uf, cidade, idcidade, referencia) {
	this.id = id;
	this.principal = principal;
	this.cep = cep;
	this.logradouro = logradouro;
	this.numero = numero;
	this.complemento = complemento;
	this.bairro = bairro;
	this.uf = uf;
	this.cidade = cidade;
	this.idcidade = idcidade;
	this.referencia = referencia;

	// Cria uma nova linha na tabela de endereços
	this.criarLinha = function() {

		// Obtem uma referência para o template da linha de endereços
		var template = document.querySelector("#templateEnderecos");

		// Seta os dados do endereco no template de referência
		template.content.querySelector("input[name='principalEndereco']").value = (this.principal ? 'Sim'
				: "Não");
		template.content.querySelector("input[name='cepEndereco']").value = this.cep;
		template.content.querySelector("input[name='logradouroEndereco']").value = this.logradouro;
		template.content.querySelector("input[name='numeroEndereco']").value = this.numero;
		template.content.querySelector("input[name='complementoEndereco']").value = this.complemento;
		template.content.querySelector("input[name='bairroEndereco']").value = this.bairro;
		template.content.querySelector("input[name='ufEndereco']").value = this.uf;
		template.content.querySelector("input[name='cidadeEndereco']").value = this.cidade;
		template.content.querySelector("input[name='idCidadeEndereco']").value = this.idcidade;
		template.content.querySelector("input[name='referenciaEndereco']").value = this.referencia;

		// Cria uma nova linha com base no modelo estabelecido no template
		var nova_linha = document.importNode(template.content, true);

		// Adiciona a nova linha ao corpo da tabela
		CorpoTabelaEnderecos.appendChild(nova_linha);

	}

	// Atualiza uma linha na tabela de endereços com os dados atuais deste
	// objeto
	this.atualizalinha = function(obj) {
		// Captura a referência da TR (linha) pai do objeto td (Parâmetro "obj")
		var objTR = obj.parentNode.parentNode;

		// Seta os dados do endereco na linha clicada
		objTR.querySelector("input[name='principalEndereco']").value = (this.principal ? 'Sim'
				: "Não");
		objTR.querySelector("input[name='cepEndereco']").value = this.cep;
		objTR.querySelector("input[name='logradouroEndereco']").value = this.logradouro;
		objTR.querySelector("input[name='numeroEndereco']").value = this.numero;
		objTR.querySelector("input[name='complementoEndereco']").value = this.complemento;
		objTR.querySelector("input[name='bairroEndereco']").value = this.bairro;
		objTR.querySelector("input[name='ufEndereco']").value = this.uf;
		objTR.querySelector("input[name='cidadeEndereco']").value = this.cidade;
		objTR.querySelector("input[name='idCidadeEndereco']").value = this.idcidade;
		objTR.querySelector("input[name='referenciaEndereco']").value = this.referencia;
	}
}

// Inclusão de endereços ---------------------------------------- INÍCIO
// ----------------------------------------
function adicionaEndereco(event) {
	event.preventDefault();

	if (!testaEndereco()) {
		return;
	}

	novo_endereco = new Endereco("", PrincipalEndereco.checked,
			CEPEndereco.value, LogradouroEndereco.value, NumeroEndereco.value,
			ComplementoEndereco.value, BairroEndereco.value, UFEndereco.value,
			CidadeEndereco.options[CidadeEndereco.selectedIndex].text,
			IDCidadeEndereco.value, ReferenciaEndereco.value);
	novo_endereco.criarLinha();
	limpaDadosEnderecos();

}

// Atribui ao btnAdicionarEndereco a rotina de inclusão dos endereços
btnAdicionarEndereco.onclick = adicionaEndereco;
// Inclusão de enderecos ---------------------------------------- FIM
// ------------------------------------------

// Cancela Inclusão/Edição de endereços ------------------------- INÍCIO
// ----------------------------------------
function cancelaEdicaoEndereco(event) {
	event.preventDefault();

	limpaDadosEnderecos();

	// Volta a rotina padrão "btnAdicionarEndereco"
	btnAdicionarEndereco.textContent = "Adicionar";
	btnAdicionarEndereco.onclick = adicionaEndereco;
}

// Atribui ao btnCancelarEndereco, a rotina de cancelamento de inclusão dos
// endereços
btnCancelarEndereco.onclick = cancelaEdicaoEndereco;
// Cancela Inclusão/Edição de endereços --------------------------- FIM
// -----------------------------------------

// Exclusão de enderecos ---------------------------------------- INÍCIO
// ---------------------------------------
function deletaEndereco(url, obj) {
	event.preventDefault();

	// Captura a referência da TR (linha) pai do objeto td (Parâmetro "obj")
	var objTR = obj.parentNode.parentNode;
	var idDoc = objTR.querySelector("input[name='idEndereco']").value;

	var r = confirm("Confirma a exclusão do endereco?");
	if (r == false) {
		return;
	}

	// Se o endereço não está gravado no banco ainda, deleta a linha atual sem
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

// Exclusão de endereços ---------------------------------------- FIM
// ------------------------------------------

// Edição de endereços ---------------------------------------- INÍCIO
// -----------------------------------------
function editaEndereco(obj) {
	event.preventDefault();

	// Captura a linha clicada
	var tr = obj.parentNode.parentNode;

	// Atribui os valores capurados, nos campos da linha de edição de endereços
	PrincipalEndereco.checked = tr
			.querySelector("input[name='principalEndereco").value == "Sim" ? true
			: false;
	CEPEndereco.value = tr.querySelector("input[name='cepEndereco']").value;
	LogradouroEndereco.value = tr
			.querySelector("input[name='logradouroEndereco']").value;
	NumeroEndereco.value = tr.querySelector("input[name='numeroEndereco']").value;
	ComplementoEndereco.value = tr
			.querySelector("input[name='complementoEndereco']").value;
	BairroEndereco.value = tr.querySelector("input[name='bairroEndereco']").value;
	UFEndereco.value = tr.querySelector("input[name='ufEndereco']").value;
	setListaCidades(UFEndereco.value, event, tr
			.querySelector("input[name='cidadeEndereco']").value)
	CidadeEndereco.value = tr.querySelector("input[name='cidadeEndereco']").value;
	IDCidadeEndereco.value = tr.querySelector("input[name='idCidadeEndereco']").value;
	ReferenciaEndereco.value = tr
			.querySelector("input[name='referenciaEndereco']").value;

	// Atribui ao btnAdicionarEndereco o evento de atualização de endereços
	btnAdicionarEndereco.textContent = "Atualizar";
	btnAdicionarEndereco.onclick = function() {
		atualizaEndereco(obj)
	};
}

function atualizaEndereco(obj) {
	event.preventDefault();

	if (testaEndereco()) {
		novo_endereco = new Endereco("", PrincipalEndereco.checked,
				CEPEndereco.value, LogradouroEndereco.value,
				NumeroEndereco.value, ComplementoEndereco.value,
				BairroEndereco.value, UFEndereco.value,
				CidadeEndereco.options[CidadeEndereco.selectedIndex].text,
				IDCidadeEndereco.value, ReferenciaEndereco.value);
		novo_endereco.atualizalinha(obj);
		limpaDadosEnderecos();
	} else {
		return;
	}
	;

	// Volta a rotina padrão "btnAdicionarEndereco"
	btnAdicionarEndereco.textContent = "Adicionar";
	btnAdicionarEndereco.onclick = adicionaEndereco;
}