<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang=pt>

<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Cadastro de Produtos</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/paginas/css/estilo.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/paginas/css/bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<script src="<%=request.getContextPath()%>/paginas/javascript/jquery/1.12.4/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/paginas/css/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>

<body>
	<div id="custom-bootstrap-menu" class="navbar navbar-inverse" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="<%=request.getContextPath()%>">Simplifique
				MEI</a>
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-menubuilder">
				<span class="sr-only">Toggle navigation</span><span
					class="icon-bar"></span><span class="icon-bar"></span><span
					class="icon-bar"></span>
			</button>
			</div>
			<div class="collapse navbar-collapse navbar-menubuilder">
				<ul class="nav navbar-nav navbar-left">
					<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Cadastros<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a title="Clientes, fornecedores, prestadores e usuários do sistema" href="<%=request.getContextPath()%>/entidades/listagem">Entidades
						</a></li>
							<li><a href="<%=request.getContextPath()%>/produtos">Produtos</a></li>
						</ul>
					</li>
					<li><a href="#">Comercial</a></li>
					<li><a href="#">Financeiro</a></li>
					<li><a href="#">Ralatórios</a></li>
					<li><a href="#">Gráficos</a></li>
					<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Sistema<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="#">Opções</a></li>
							<li><a href="#">Dados da Empresa</a></li>
						</ul>
					</li>
					<li><a href="#">Ajuda</a></li>
				</ul>
			</div>
		</div>
	</div>
</body>

</html>