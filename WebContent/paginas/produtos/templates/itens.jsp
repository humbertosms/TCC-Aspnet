<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<tr>
	<input type=hidden name='idItemProduto' value="${item.id}">
	<td><input type="text" readonly class="transparente"
		name='corNomeItemProduto' value="${item.cor.nome}"></td>
	<td><input type="text" readonly class="transparente"
		name='nomeItemProduto' value="${item.tamanho.nome}"></td>
	<td><input type="text" readonly class="transparente"
		name='estoqueItemProduto' value="${item.estoque}">
	</td>
	<td><input type="text" readonly class="transparente"
		name='referenciaFabricaItemProduto' value="${item.referenciaFabrica}"></td>
	<td><input type="text" readonly class="transparente"
		name='referenciaInternaItemProduto' value="${item.referenciaInterna}"></td>
	<td><input type="text" readonly class="transparente"
		name='codigoBarrasFabricaItemProduto' value="${item.codigoBarrasFabrica}"></td>
	<td><input type="text" readonly class="transparente"
		name='codigoBarrasInternoItemProduto' value="${item.codigoBarrasInterno}"></td>
	<td><a href="#" onClick="javascript:editaItemProduto(this);">
			<img width="20" height="20"
			src="<%=request.getContextPath()%>/paginas/icones/editar_grades.png" />
	</a></td>
	<td><a href="#"
		onClick="javascript:deletaItemProduto('<%=request.getContextPath()%>/produtos/${item.id}',this);">
			<img width="20" height="20"
			src="<%=request.getContextPath()%>/paginas/icones/deletar_grades.png" />
	</a></td>
</tr>