// Exclusão de entidades ---------------------------------------- INÍCIO ---------------------------------------
function deletaEntidade(url, obj){
	event.preventDefault();	
	
	// Captura a referência da TR (linha) pai do objeto td (Parâmetro "obj")
	var objTR = obj.parentNode.parentNode;
	var idDoc = objTR.querySelector("td[id='idEntidade']").textContent;

	var r = confirm("Confirma a exclusão da entidade?");
	if (r == false) {
	    return;
	}
	
	var req = getHttpRequest();

	req.onreadystatechange = function(){
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

// Exclusão de contatos ---------------------------------------- FIM ------------------------------------------