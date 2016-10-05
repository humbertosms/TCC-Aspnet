<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<tr>
	<input type=hidden name='idEndereco' value="${item.id}">
	<td style="visibility: none;"><input type="text" readonly class="transparente" name='principalEndereco' value="${item.principal == true ? 'Sim' : 'Não'}"></td>
	<td style="visibility: none;"><input type="text" readonly class="transparente" name='cepEndereco' value="${item.cep}"></td>
	<td><input type="text" readonly class="transparente" name='logradouroEndereco' value="${item.logradouro}"></td>
	<td><input type="text" readonly class="transparente" name='numeroEndereco' value="${item.numero}"></td>
	<td><input type="text" readonly class="transparente" name='complementoEndereco' value="${item.complemento}"></td>
	<td style="visibility: none;"><input type="text" readonly class="transparente" name='bairroEndereco' value="${item.bairro}"></td>
	<td style="visibility: none;"><input type="text" readonly class="transparente" name='ufEndereco' value="${item.cidade.uf}"></td>
	<td style="visibility: none;"><input type="text" readonly class="transparente"name='cidadeEndereco' value="${item.cidade.nome}"> <input
		type=hidden name='idCidadeEndereco' value="${item.cidade.id}">
	</td>
	<td style="visibility: none;"><input type="hidden" readonly class="transparente" name='referenciaEndereco' value="${item.referencia}"></td>
	<td><a href="#" data-toggle="modal" data-target="#ModalEnderecos" onClick="javascript:editaEndereco(this);"> <img
			width="20" height="20"
			src="<%=request.getContextPath()%>/paginas/icones/editar_grades.png" />
	</a></td>
	<td><a href="#"
		onClick="javascript:deletaEndereco('<%=request.getContextPath()%>/enderecos/${item.id}',this);">
			<img width="20" height="20"
			src="<%=request.getContextPath()%>/paginas/icones/deletar_grades.png" />
	</a></td>
</tr>