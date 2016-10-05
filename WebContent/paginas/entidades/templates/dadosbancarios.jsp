<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<tr>
	<input type=hidden name='idDadoBancario' value="${item.id}">
	<td><input type="text" readonly class="transparente"
		name='bancoDadoBancario' value="${item.banco}"></td>
	<td><input type="text" readonly class="transparente"
		name='agenciaDadoBancario' value="${item.agencia}"></td>
	<td><input type="text" readonly class="transparente"
		name='agenciaDigitoDadoBancario' value="${item.agenciaDigito}">
	</td>
	<td><input type="text" readonly class="transparente"
		name='contaDadoBancario' value="${item.conta}"></td>
	<td><input type="text" readonly class="transparente"
		name='contaDigitoDadoBancario' value="${item.contaDigito}"></td>
	<td><a href="#" data-toggle="modal" data-target="#ModalDadosBancarios" 
			onClick="javascript:editaDadoBancario(this);">
			<img width="20" height="20"
			src="<%=request.getContextPath()%>/paginas/icones/editar_grades.png" />
	</a></td>
	<td><a href="#" 
		onClick="javascript:deletaDadoBancario('<%=request.getContextPath()%>/dadosbancarios/${item.id}',this);">
			<img width="20" height="20"
			src="<%=request.getContextPath()%>/paginas/icones/deletar_grades.png" />
	</a></td>
</tr>