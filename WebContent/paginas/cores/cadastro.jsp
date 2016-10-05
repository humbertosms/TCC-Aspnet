<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang=pt>
	<head>
		<meta charset="UTF-8"/>
		<title>Cadastro de Cores</title>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/paginas/css/bootstrap-3.3.7-dist/css/bootstrap.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/paginas/css/estilo.css">
	</head>
	<body>	
		<%@include file="/paginas/menu.jsp" %>		
		<form class="form-horizontal" action="${pageContext.request.contextPath}/cores" method=post>
			<div class="container">
				<h3>Cadastro de Cores</h3><br>
				
			</div>
		</form>
		<fieldset>${msg}</fieldset>
	</body>
</html>