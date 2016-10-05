<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang=pt>	
	<head>
		<meta charset="UTF-8"/>
		<title>Cadastro de Produtos</title>
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	</head>
	<body>
		<%@include file="/paginas/menu.jsp" %><br><br>
		<h3>Cadastro de Produtos</h3><br>
        <table class="table table-striped">
            <tr style="font-weight: bold; color: navy; background-color: silver;">
                <td>ID</td>
                <td>Nome</td>
                <td>Descrição</td>
                <td>Editar</td>
                <td>Excluir</td>
            </tr>
            <c:forEach items="${lstProdutos}" var="item">
                <tr>
                    <td id="idProduto">${item.id}</td>
                    <td>${item.nome}</td>
                    <td>${item.descricao}</td>
                    <td>
                    	<a href="<%=request.getContextPath()%>/produtos/${item.id}">
                    		<img width="20" height="20" src="<%=request.getContextPath()%>/paginas/icones/editar_grades.png"/>
                    	</a>
                    </td>
					<td>
						<a href="#" onClick="javascript:deletaProduto('<%=request.getContextPath()%>/produtos/${item.id}',this);">
                    		<img width="20" height="20" src="<%=request.getContextPath()%>/paginas/icones/deletar_grades.png"/>
                    	</a>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="10">
                    <input class="btn btn-default" type="button" value="Incluir" name="btn" onclick="window.location.href='/Simplifique_ERP/produtos/novo'"/>
                </td>
            </tr>
        </table>
	</body>
	<script type="text/javascript" src="<%=request.getContextPath()%>/paginas/javascript/sistema.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/paginas/javascript/listaprodutos/listaprodutos.js"></script>
</html>