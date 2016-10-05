<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<tr>
	<input type=hidden name='idDocumento' value="${item.id}">
	<td><input type="text" readonly class="transparente"
		name='tipoDocumento' value="${item.tipo}"></td>
	<td><input type="text" readonly class="transparente"
		name='numeroDocumento' value="${item.numero}"></td>
	<td><a href="#" data-toggle="modal" data-target="#ModalDocumentos" onClick="javascript:editaDocumento(this);"> <img
			width="20" height="20"
			src="<%=request.getContextPath()%>/paginas/icones/editar_grades.png" />
	</a></td>
	<td><a href="#"
		onClick="javascript:deletaDocumento('<%=request.getContextPath()%>/documentos/${item.id}',this);">
			<img width="20" height="20"
			src="<%=request.getContextPath()%>/paginas/icones/deletar_grades.png" />
	</a></td>
</tr>