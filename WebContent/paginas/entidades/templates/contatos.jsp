<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<tr>
	<input type=hidden name='idContato' value="${item.id}">
	<td><input type="text" readonly class="transparente"
		name='nomeContato' value="${item.nome}"></td>
	<td><input type="text" readonly class="transparente"
		name='telefoneContato' value="${item.telefone}"></td>
	<td><input type="text" readonly class="transparente"
		name='faxContato' value="${item.fax}"></td>
	<td><input type="text" readonly class="transparente"
		name='celularContato' value="${item.celular}"></td>
	<td><input type="text" readonly class="transparente"
		name='emailContato' value="${item.email}"></td>
	<td><a href="#" data-toggle="modal" data-target="#ModalContatos" onClick="javascript:editaContato(this);"> <img
			width="20" height="20"
			src="<%=request.getContextPath()%>/paginas/icones/editar_grades.png" />
	</a></td>
	<td><a href="#"
		onClick="javascript:deletaContato('<%=request.getContextPath()%>/contatos/${item.id}',this);">
			<img width="20" height="20"
			src="<%=request.getContextPath()%>/paginas/icones/deletar_grades.png" />
	</a></td>
</tr>