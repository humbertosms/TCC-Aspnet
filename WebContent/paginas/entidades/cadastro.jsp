<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang=pt>
	<head>		
		<meta charset="UTF-8"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Cadastro de Entidades</title>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/paginas/css/bootstrap-3.3.7-dist/css/bootstrap.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/paginas/css/estilo.css">
	</head>
	<body>
		<%@include file="/paginas/menu.jsp" %>
		<div class="container">
			<h3>Cadastro de Entidades</h3><br>
		
			<form class="form-horizontal" action="${pageContext.request.contextPath}/entidades" method=post>
				<div class="form-group">
					<label class="control-label col-sm-2" for='id'>ID</label>
					<div class="col-sm-10">
						<input class="form-control" type='text' readonly id='id' name='id' value="${objEntidade.id}" size="15">
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-sm-2" for='cmbTipoPessoa'>Tipo</label>
					<div class="col-sm-10">
						<select class="form-control" onchange="ajustaTela(this)" id="cmbTipoPessoa" name="tipopessoa" autofocus>
							<option value="Física" ${objEntidade.tipoPessoa == 'Física' ? 'selected' : ''}>Física</option>
							<option value="Jurídica" ${objEntidade.tipoPessoa == 'Jurídica' ? 'selected' : ''}>Jurídica</option>
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-sm-2" for='nome' id='lblRazaoSocial'>Razão Social</label>
					<div class="col-sm-10">
						<input class="form-control" type='text' id='nome' id='nome' name='nome' value="${objEntidade.nome}" size="50" maxlength="100" required>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-sm-2" for='apelido' id='lblNomeFantasia'>Nome Fantasia</label>
					<div class="col-sm-10">
						<input class="form-control" type='text' id='apelido' name='apelido' value="${objEntidade.apelido}" size="50" maxlength="100" required>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-sm-2" for='cpf_cnpj' id='lblCPFCNPJ'>CPF/CNPJ</label>
					<div class="col-sm-10">
						<input class="form-control" type='text' id='cpf_cnpj' name='cpf_cnpj' value="${objEntidade.cpf_cnpj}" size="15" maxlength="20">
					</div>
				</div>
				
				<div class="form-group">				
					<label class="control-label col-sm-2" for='rg_ie' id='lblRGIE'>RG/IE</label>
					<div class="col-sm-10">
						<input class="form-control" type='text' id='rg_ie' name='rg_ie' value="${objEntidade.rg_ie}" size="15" maxlength="20">
					</div>
				</div>
				
				<div class="form-group">
					<div id='divNascimento'>
						<label class="control-label col-sm-2" for='nascimento' id='lblNascimento'>Nascimento</label>
						<div class="col-sm-10">
							<input class="form-control" type=date id='nascimento' name='nascimento' value="${objEntidade.nascimento}">
						</div>
					</div>				
				</div>
				
				<div class="form-group">
					<label class="control-label col-sm-2" for='telefone'>Telefone</label>
					<div class="col-sm-10">
						<input class="form-control" type=text id='telefone' name='telefone' value="${objEntidade.telefone}" size="15" maxlength="20">
					</div>
				</div>
				
				<div class="form-group">
					<div id='divFax'>
						<label class="control-label col-sm-2" for='fax'>Fax</label>
						<div class="col-sm-10">
							<input class="form-control" type=text id='fax' name='fax' value="${objEntidade.fax}" size="15" maxlength="20">
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-sm-2" for='celular'>Celular</label>
					<div class="col-sm-10">
						<input class="form-control" type=text id='celular' name='celular' value="${objEntidade.celular}" size="15" maxlength="20">
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-sm-2" for='email'>Email</label>
					<div class="col-sm-10">
						<input class="form-control" type=mail id='email' name='email' value="${objEntidade.email}" size="50" maxlength="100">
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-sm-2" for='site'>Site</label>
					<div class="col-sm-10">
						<input class="form-control" type=site id='site' name='site' value="${objEntidade.site}" size="50" maxlength="100">
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-sm-2" for='cadastro'>Cadastro</label>
					<div class="col-sm-10">
						<input class="form-control" type=date id='cadastro' name='cadastro' value="${objEntidade.cadastro}">
					</div>
				</div>
				
				<div class="checkbox_person">				
					<input type="checkbox" name="cliente" id="cliente" ${objCliente == null ? '':  'checked'}>
					<label for="cliente" title="Marque, se esta entidade for um cliente">Cliente</label>
					<a href="#" onClick="mudarEstadoDiv('clienteDiv',event);">
		            	<img width="20" height="20" src="<%=request.getContextPath()%>/paginas/icones/motrar_ocultar.png"/>
		            </a>
	            </div>
	      	            
				<div style="background-color:#F5F5F5" id="clienteDiv">
					<input type="hidden" id='idcliente' name='idcliente' value="${objCliente.id}">				
					<label class="control-label col-sm-2" for='credito'>Crédito</label>
					<input class="form-control" type=number id='credito' name='credito' value="${objCliente.credito}" maxlength="14">
					<br>			
				</div>
				
				<div class="checkbox_person">
					<input class="form-control" type="checkbox" name="colaborador" id="colaborador"${objColaborador == null ? '':  'checked'}>
					<label class="control-label col-sm-2" for="colaborador" title="Marque, se esta entidade for um colaborador">Colaborador</label>
					<a href="#" onClick="mudarEstadoDiv('colaboradorDiv',event);">
		            	<img width="20" height="20" src="<%=request.getContextPath()%>/paginas/icones/motrar_ocultar.png"/>
		            </a>
	            </div>
	            
	            <div class="form-group">
					<div style="background-color:#F5F5F5" id="colaboradorDiv">
						<input class="form-control" type="hidden" id='idcolaborador' name='idcolaborador' value="${objColaborador.id}">				
						<label class="control-label col-sm-2" for='icomissao'>Comissão</label>
						<input class="form-control" type=number id='comissao' name='comissao' value="${objColaborador.comissao}" maxlength="14">
						<br>
					</div>
				</div>
				
				<div class="form-group">
					<div class="checkbox_person">
						<input type="checkbox" name="fornecedor" id="fornecedor"${objFornecedor == null ? '':  'checked'}>
						<label class="control-label col-sm-2" for="fornecedor" title="Marque, se esta entidade for um fornecedor">Fornecedor</label>
						<a href="#" onClick="mudarEstadoDiv('fornecedorDiv',event);">
			            	<img width="20" height="20" src="<%=request.getContextPath()%>/paginas/icones/motrar_ocultar.png"/>
			            </a>
					</div>
				</div>
				
				<div style="background-color:#F5F5F5" id="fornecedorDiv">
					<input class="form-control" type="hidden" id='idfornecedor' name='idfornecedor' value="${objFornecedor.id}">
				</div><br>
				
				<div class="form-group">
					<div class="checkbox_person">
						<input type="checkbox" name="usuario" id="usuario"${objUsuario == null ? '':  'checked'}>
						<label class="control-label col-sm-2" for="usuario" title="Marque, se esta entidade for um usuário do sistema">Usuário do Sistema<br></label>
						<a href="#" onClick="mudarEstadoDiv('usuarioDiv',event);">
			            	<img width="20" height="20" src="<%=request.getContextPath()%>/paginas/icones/motrar_ocultar.png"/>
			            </a>
					</div>
				</div>
				
				<div class="form-group">
					<div style="background-color:#F5F5F5" id="usuarioDiv">
						<input class="form-control" type="hidden" id='idusuario' name='idusuario' value="${objUsuario.id}">
		
						<label class="control-label col-sm-2" for='ilogin'>Login</label>
						<input class="form-control" type=text id='login' name='login' value="${objUsuario.login}" >
						<br>
		
						<label class="control-label col-sm-2" for='isenha'>Senha</label>
						<input class="form-control" type=password id='senha' name='senha' value="${objUsuario.senha}" maxlength="14">
						<br>
					</div>
				</div>

<% /* Outros documentos ----- Início */ %>
				<hr>
				<h4 style="cursor:pointer;" onClick="mudarEstadoDiv('outrosDocumentosDiv',event);">Outros documentos</h4>
				<div id="outrosDocumentosDiv">
					<template style="display:none;" id ="templateDocumentos">
						<%@include file="templates/documentos.jsp" %>
					</template>
					
					<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#ModalDocumentos">Novo</button>
					
					<!-- Modal -->
 					<div class="modal fade" id="ModalDocumentos" role="dialog">
						<div class="modal-dialog">

							<!-- Modal content-->
      						<div class="modal-content">
								<div class="modal-body">
									<label for='TipoDocumento'>Tipo</label>
									<select class="form-control" id="TipoDocumento" name="TipoDocumento">
										<option value="Passaporte">Passaporte</option>
										<option value="PIS">PIS</option>
									</select>
								
									<label for='NumeroDocumento'>Número</label>
									<input class="form-control" type='text' id='NumeroDocumento' name='NumeroDocumento' size="20" maxlength="30">
									<button id="btnAdicionarDocumento">Adicionar</button>
									<button id="btnCancelarDocumento">Cancelar</button>
									
									<div style="color:red" id="AlertaDocumentos"></div>
								</div>
								<div class="modal-footer">
						          <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
						        </div>
							</div>

						</div>
					</div>
					<table class="table table-striped" id="TabelaDocumentos">
						<thead>
							<tr>
								<th>Tipo</th>
								<th>Número</th>
							</tr>
						</thead>
						<tbody id="CorpoTabelaDocumentos">
							<c:forEach items="${objEntidade.documentos}" var="item">
				                <%@include file="templates/documentos.jsp" %>
				            </c:forEach>
				    	</tbody>
					</table>				
				</div><br>
				<hr>
<% /* Outros documentos ----- Fim */ %>

<% /* Contatos ----- Início */ %>
				<h4 style="cursor:pointer;" onClick="mudarEstadoDiv('contatosDiv',event);">Contatos</h4>
				<div id="contatosDiv">
					<template style="display:none;" id ="templateContatos">
						<%@include file="templates/contatos.jsp"%>
					</template>
					
					<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#ModalContatos">Novo</button>
					
					<!-- Modal -->
 					<div class="modal fade" id="ModalContatos" role="dialog">
						<div class="modal-dialog">

							<!-- Modal content-->
      						<div class="modal-content">
								<div class="modal-body">
					
									<label for='NomeContato'>Nome</label>
									<input class="form-control" type='text' id='NomeContato' name='NomeContato' size="50" maxlength="100">
									
									<label for='TelefoneContato'>Telefone</label>
									<input class="form-control" type='text' id='TelefoneContato' name='TelefoneContato' size="15" maxlength="20">
									
									<label for='FaxContato'>Fax</label>
									<input class="form-control" type='text' id='FaxContato' name='FaxContato' size="15" maxlength="20">
									
									<label for='CelularContato'>Celular</label>
									<input class="form-control" type='text' id='CelularContato' name='CelularContato' size="15" maxlength="20"><br>
									
									<label for='EmailContato'>Email</label>
									<input class="form-control" type='text' id='EmailContato' name='EmailContato' size="50" maxlength="100">
									
									<button id="btnAdicionarContato">Adicionar</button>
									<button id="btnCancelarContato">Cancelar</button>
					
									<div style="color:red" id="AlertaContatos"></div>
								</div>
								<div class="modal-footer">
						          <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
						        </div>
							</div>
						</div>
					</div>
					<table class="table table-striped" id="TabelaContatos">
						<thead>
							<tr>
								<th>Nome</th>
								<th>Telefone</th>
								<th>Fax</th>
								<th>Celular</th>
								<th>Email</th>
							</tr>
						</thead>
						<tbody id="CorpoTabelaContatos">
							<c:forEach items="${objEntidade.contatos}" var="item">
				                <%@include file="templates/contatos.jsp"%>
				            </c:forEach>
				    	</tbody>
					</table>				
				</div><br>
				<hr>
<% /* Contatos ----- Fim */ %>

<% /* Endereços ----- Início */ %>
				<h4 style="cursor:pointer;" onClick="mudarEstadoDiv('enderecosDiv',event);">Endereços</h4>
				<div id="enderecosDiv">
					<template style="display:none;" id ="templateEnderecos">		
						<%@include file="templates/enderecos.jsp"%>
					</template>
					
					<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#ModalEnderecos">Novo</button>
					
					<!-- Modal -->
 					<div class="modal fade" id="ModalEnderecos" role="dialog">
						<div class="modal-dialog">

							<!-- Modal content-->
      						<div class="modal-content">
								<div class="modal-body">
									
									<div class="checkbox_person">
										<input class="form-control" type="checkbox" name="PrincipalEndereco" id="PrincipalEndereco"}>
										<label for="PrincipalEndereco">Principal<br></label>
										<a href="#"></a>
									</div>				
					
									<label for='CEPEndereco'>CEP</label>
									<input class="form-control" type='text' id='CEPEndereco' name='CEPEndereco' size="10" maxlength="9">				
									<a class="btn btn-default" href="#" onClick="javascript:preencheEndereco(event);">
										<img width="20" height="20" src="<%=request.getContextPath()%>/paginas/icones/pesquisa.png"/>
									</a><br>
									
									<label for='LogradouroEndereco'>Logradouro</label>
									<input class="form-control" type='text' id='LogradouroEndereco' name='LogradouroEndereco' size="80" maxlength="80">
									
									<label for='NumeroEndereco'>Número</label>
									<input class="form-control" type='text' id='NumeroEndereco' name='NumeroEndereco' size="15" maxlength="15"><br>
									
									<label for='ComplementoEndereco'>Complemento</label>
									<input class="form-control" type='text' id='ComplementoEndereco' name='ComplementoEndereco' size="25" maxlength="50">
									
									<label for='BairroEndereco'>Bairro</label>
									<input class="form-control" type='text' id='BairroEndereco' name='BairroEndereco' size="25" maxlength="80">
									
									<label for='UFEndereco'>UF</label>
									<select class="form-control" onchange="setListaCidades(this.value, event)" id="UFEndereco" name="UFEndereco">
										<%@include file="/paginas/plugins/uf.jsp" %>
									</select>				
									
									<label for='CidadeEndereco'>Cidade</label>
									<select class="form-control" id="CidadeEndereco" name="CidadeEndereco"></select>
									<input type=hidden name='IDCidadeEndereco'><br>
									
									<label for='ReferenciaEndereco'>Referencia</label>
									<input class="form-control" type='text' id='ReferenciaEndereco' name='ReferenciaEndereco' size="25">		
									
									<button id="btnAdicionarEndereco">Adicionar</button>
									<button id="btnCancelarEndereco">Cancelar</button>
					
									<div style="color:red" id="AlertaEnderecos"></div>
					
								</div>
								<div class="modal-footer">
						          <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
						        </div>
							</div>
						</div>
					</div>
					
					<table class="table table-striped" id="TabelaEnderecos">
						<thead>
							<tr>
								<th style="visibility: none;">Principal</th>
								<th style="visibility: none;">CEP</th>
								<th>Logradouro</th>
								<th>Numero</th>
								<th>Complemento</th>
								<th style="visibility: none;">Bairro</th>
								<th style="visibility: none;">UF</th>
								<th style="visibility: none;">Cidade</th>
								<th style="visibility: none;">Referência</th>
							</tr>
						</thead>
						<tbody id="CorpoTabelaEnderecos">
							<c:forEach items="${objEntidade.enderecos}" var="item">
				                <%@include file="templates/enderecos.jsp"%>
				            </c:forEach>
				    	</tbody>
					</table>				
				</div><br>
				<hr>
<% /* Enderecos ----- Fim */ %>

<% /* Dados bancários ----- Início */ %>
				<h4 style="cursor:pointer;" onClick="mudarEstadoDiv('dadosBancariosDiv',event);">Dados Bancários</h4>
				<div id="dadosBancariosDiv">
					<template style="display:none;" id ="templateDadosBancarios">
						<%@include file="templates/dadosbancarios.jsp"%>						
					</template>
					<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#ModalDadosBancarios">Novo</button>
					
					<!-- Modal -->
 					<div class="modal fade" id="ModalDadosBancarios" role="dialog">
						<div class="modal-dialog">

							<!-- Modal content-->
      						<div class="modal-content">
								<div class="modal-body">
					
									<label for='BancoDadoBancario'>Banco</label>
									<select class="form-control" id="BancoDadoBancario" name="BancoDadoBancario">				
										<%@include file="/paginas/plugins/bancos.jsp" %>aqui
									</select>
									<label for='AgenciaDadoBancario'>Agência</label>
									<input class="form-control" type='text' id='AgenciaDadoBancario' name='AgenciaDadoBancario' size="5" maxlength="20">
					
									<label for='AgenciaDigitoDadoBancario'>Dígito Agência</label>
									<input class="form-control" type='text' id='AgenciaDigitoDadoBancario' name='AgenciaDigitoDadoBancario' size="1" maxlength="1">
									<br>
					
									<label for='ContaDadoBancario'>Conta</label>
									<input class="form-control" type='text' id='ContaDadoBancario' name='ContaDadoBancario' size="10" maxlength="20">
					
									<label for='ContaDigitoDadoBancario'>Dígito Conta</label>
									<input class="form-control" type='text' id='ContaDigitoDadoBancario' name='ContaDigitoDadoBancario' size="1" maxlength="1">
					
									<button id="btnAdicionarDadoBancario">Adicionar</button>
									<button id="btnCancelarDadoBancario">Cancelar</button>
									
									<div style="color:red" id="AlertaDadosBancarios"></div>
								</div>
								<div class="modal-footer">
						          <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
						        </div>
							</div>
						</div>
					</div>
					
					<table class="table table-striped" id="TabelaDadosBancarios">
						<thead>
							<tr>
								<th>Banco</th>
								<th>Agência</th>
								<th>Dígito Agência</th>
								<th>Conta</th>
								<th>Dígito Conta</th>
							</tr>
						</thead>
						<tbody id="CorpoTabelaDadosBancarios">
							<c:forEach items="${objEntidade.dadosBancarios}" var="item">
				                <%@include file="templates/dadosbancarios.jsp"%>
				            </c:forEach>
				    	</tbody>
					</table>				
				</div><br>
				<hr>
<% /* Dados bancários ----- Fim  */ %>
		
				<h4>Imagens</h4><br>
				<hr>
				
				<div class="form-group">
					<label class="control-label col-sm-2" for='observacao'>Observação</label>
					<div class="col-sm-10">
						<textarea class="form-control" rows="4" cols="51" id='observacao' name='observacao'>${objEntidade.observacao}</textarea>
					</div> 
				</div>
				
				<table">			
					<tr>
						<td><input class="btn btn-default" type="submit" value="Salvar" name="btn"/>
							<input class="btn btn-default" type="reset" value="Cancelar" name="btn"/>
							<input class="btn btn-default" type="button" value="Listar" name="btn" onclick="window.location.href='/Simplifique_ERP/entidades'"/>
							<input class="btn btn-default" type="button" value="Home" name="btn" onclick="window.location.href='/Simplifique_ERP/'"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<fieldset>${msg}</fieldset>		

	</body>
	<script type="text/javascript" src="<%=request.getContextPath()%>/paginas/javascript/sistema.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/paginas/entidades/javascript/cadastroentidades.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/paginas/entidades/javascript/documentos.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/paginas/entidades/javascript/contatos.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/paginas/entidades/javascript/enderecos.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/paginas/entidades/javascript/dadosbancarios.js"></script>
</html>