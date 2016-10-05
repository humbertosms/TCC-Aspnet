//Obtem o objeto httpRequest para fazer requisições AJAX, de acordo com o browser
function getHttpRequest(){
	if (window.XMLHttpRequest) {
		// Outros browsers
		req = new XMLHttpRequest();
	}else if (window.ActiveXObject) {
		// Internet Explorer
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}
	return req;
}

//Oculta/Exibe a div passada no parâmetro "el"
function mudarEstadoDiv(el, event) {
	event.preventDefault();

    var display = document.getElementById(el).style.display;
    if(display == "none")
        document.getElementById(el).style.display = 'block';
    else
        document.getElementById(el).style.display = 'none';
}

// Exclui uma linha de uma tabela qualquer
function removerLinha(obj){
	event.preventDefault();
	
	// Captura a referência da TR (linha) pai do objeto td (Parâmetro "obj")
	var objTR = obj.parentNode.parentNode;
	// Captura a referência da TABLE (tabela) pai da linha
	var objTable = objTR.parentNode;
	// Captura o índice da linha
	var indexTR = objTR.sectionRowIndex;
	// Chama o método de remoção de linha nativo do JavaScript, passando como parâmetro o índice da linha  
	objTable.deleteRow(indexTR);
 }