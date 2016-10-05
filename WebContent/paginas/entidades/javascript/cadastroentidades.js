/**
 * Scripts gerais da página de cadastro de entidades
 */

// Oculta/Exibe/Renomeia os campos de acordo com o tipo de pessoa selecionado
function ajustaTela() {
	if (document.getElementById("cmbTipoPessoa").value == 'Jurídica') {
		document.getElementById("lblRazaoSocial").innerText = "Razão Social";
		document.getElementById("lblNomeFantasia").innerText = "Nome Fantasia";
		document.getElementById("lblCPFCNPJ").innerText = "CNPJ";
		document.getElementById("lblRGIE").innerText = "IE";
		document.getElementById("divNascimento").style.display = "none";
		document.getElementById("divFax").style.display = "block";
	} else {
		document.getElementById("lblRazaoSocial").innerText = "Nome";
		document.getElementById("lblNomeFantasia").innerText = "Apelido";
		document.getElementById("lblCPFCNPJ").innerText = "CPF";
		document.getElementById("lblRGIE").innerText = "RG";
		document.getElementById("divNascimento").style.display = "block";
		document.getElementById("divFax").style.display = "none";
	}
}

// Oculta os campos com dados específicos
window.onload = function() {
	ajustaTela();
	document.getElementById("clienteDiv").style.display = "none";
	document.getElementById("colaboradorDiv").style.display = "none";
	document.getElementById("fornecedorDiv").style.display = "none";
	document.getElementById("usuarioDiv").style.display = "none";
	document.getElementById("outrosDocumentosDiv").style.display = "none";
	document.getElementById("contatosDiv").style.display = "none";
	document.getElementById("enderecosDiv").style.display = "none";
	document.getElementById("dadosBancariosDiv").style.display = "none";
}