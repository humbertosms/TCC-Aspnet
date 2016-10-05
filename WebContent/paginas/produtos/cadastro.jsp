<% /* 
	Quero implantar este layout em todo o sistema
	http://www.w3schools.com/bootstrap/tryit.asp?filename=trybs_form_horizontal_static&stacked=h
*/ %>


<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang=pt>
	<head>
		<meta charset="UTF-8"/>
		<title>Cadastro de Produtos</title>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/paginas/css/bootstrap-3.3.7-dist/css/bootstrap.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/paginas/css/estilo.css">
	</head>
	<body>	
		<%@include file="/paginas/menu.jsp" %><br><br>		
		<form class="form-horizontal" action="${pageContext.request.contextPath}/produtos" method=post>
			<div class="container">
				<h3>Cadastro de Produtos</h3><br>
				<ul class="nav nav-tabs">
				    <li class="active"><a href="#dadosbasicos">Dados básicos</a></li>
				    <li><a href="#estoquevalor">Estoques e valores</a></li>
					<li><a href="#tributacao">Tributação</a></li>
					<li><a href="#fotos">Fotos</a></li>
				</ul>
				<div class="tab-content">
					<div id="dadosbasicos" class="tab-pane fade in active">
						
						<div class="form-group">
							<label class="control-label col-sm-2" for='id'>ID</label>
							<div class="col-sm-10">
								<input class="form-control" type='text' readonly id='id' name='id' value="${objProduto.id}" size="15">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for='nome'>Nome</label>
							<div class="col-sm-10">
								<input class="form-control" type='text' id='nome' id='nome' name='nome' value="${objProduto.nome}" size="50" maxlength="100" required>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for='descricao'>Descrição</label>
							<div class="col-sm-10">
								<input class="form-control" type='text' id='descricao' name='descricao' value="${objProduto.descricao}" size="50" maxlength="100" required>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for='localizacao'>Localização</label>
							<div class="col-sm-10">
								<input class="form-control" type='text' id='localizacao' name='localizacao' value="${objProduto.localizacao}" size="15" maxlength="20">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for='comissao'>Comissão</label>
							<div class="col-sm-10">
								<input class="form-control" type=text id='comissao' name='comissao' value="${objProduto.comissao}" size="15" maxlength="20">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for='validade'>Validade</label>
							<div class="col-sm-10">
								<input class="form-control" type=site id='validade' name='validade' value="${objProduto.validade}" size="15" maxlength="11">
							</div>
						</div>		
						<div class="form-group">
							<label class="control-label col-sm-2" for='cadastro'>Cadastro</label>
							<div class="col-sm-10">
								<input class="form-control" type=date id='cadastro' name='cadastro' value="${objProduto.cadastro}">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for='observacao'>Observação</label>
							<div class="col-sm-10">
								<textarea class="form-control" rows="4" cols="51" id='observacao' name='observacao'>${objProduto.observacao}</textarea>
							</div> 
						</div>
					</div>

					<div id="estoquevalor" class="tab-pane fade">
						<template style="display:none;" id="templateEnderecos">
							<%@include file="templates/itens.jsp"%>
						</template>
						<div class="form-group">
							<label class="control-label col-sm-2" for='Cor'>Cor</label>
							<div class="col-sm-10">
								<select class="form-control" id="Cor" name="Cor"></select>
							</div>
							<input type=hidden name='IDCor'>
						</div>

						<div class="form-group">
							<label class="control-label col-sm-2" for='Tamanho'>Tamanho</label>
							<div class="col-sm-10">
								<select class="form-control" id="Tamanho" name="Tamanho"></select>
							</div>
							<input type=hidden name='IDTamanho'>
						</div>

						<div class="form-group">
							<label class="control-label col-sm-2" for='Estoque'>Estoque</label>
							<div class="col-sm-10">
								<input class="form-control" type='text' id='Estoque' name='Estoque' size="25" maxlength="50">
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-sm-2" for='ReferenciaFabrica'>Referência de Fábrica</label>
							<div class="col-sm-10">
								<input class="form-control" type='text' id='ReferenciaFabrica' name='ReferenciaFabrica' size="25" maxlength="80">
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-sm-2" for='UFEndereco'>Referência Interna</label>
							<div class="col-sm-10">
								<input class="form-control" type='text' id='ReferenciaInterna' name='ReferenciaInterna' size="25" maxlength="80">
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-sm-2" for='CidadeEndereco'>Código de Barras da Fábrica</label>
							<div class="col-sm-10">
								<input class="form-control" type='text' id='CodigoBarrasFabrica' name='CodigoBarrasFabrica' size="80" maxlength="80">
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-sm-2" for='CodigoBarrasInterno'>Código Barras Interno</label>
							<div class="col-sm-10">
								<input class="form-control" type='text' id='CodigoBarrasInterno' name='CodigoBarrasInterno' size="25">
							</div>
						</div>					
					</div>

					<div id="tributacao" class="tab-pane fade">
						<p>Tributação do produto</p>
					</div>

					<div id="fotos" class="tab-pane fade">
						<p>Fotos do produto</p>
					</div>
				</div>
				<table>			
					<tr>
						<td><input class="btn btn-default" type="submit" value="Salvar" name="btn"/>
							<input class="btn btn-default" type="reset" value="Cancelar" name="btn"/>
							<input class="btn btn-default" type="button" value="Listar" name="btn" onclick="window.location.href='/Simplifique_ERP/produtos'"/>
							<input class="btn btn-default" type="button" value="Home" name="btn" onclick="window.location.href='/Simplifique_ERP/'"/>
						</td>
					</tr>
				</table>
			</div>
			<script>
				$(document).ready(function(){
				    $(".nav-tabs a").click(function(){
				        $(this).tab('show');
				    });
				});
			</script>						
		</form>
		<fieldset>${msg}</fieldset>
	</body>
</html>