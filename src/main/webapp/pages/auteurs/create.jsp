<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<jsp:include page="/template/header.jsp" />

		<h1><spring:message code="auteur.create.titre" text="default text" /></h1>
		<c:url var="saveAuteur" value="/auteurs/save.do" />
		<form:form action="${saveAuteur}" commandName="auteur" method="POST">
		<form:hidden path="id" />
		<div class="form-group">
			<label for="nom"><spring:message code="auteur.create.form.nom" text="default text" /></label>
			<form:input type="text" class="form-control" id="nom" path="nom" placeholder="Nom de l'auteur" />
			
			<label for="prenom"><spring:message code="auteur.create.form.prenom" text="default text" /></label>
			<form:input type="text" class="form-control" id="prenom" path="prenom" placeholder="Prénom de l'auteur" />
			<form:errors path="nom" class="alert alert-danger" element="div" />
		</div>
		<div class="form-group">
			<label for="password"><spring:message code="auteur.create.form.password" text="default text" /></label>
			<form:input type="password" class="form-control" id="password" path="password" placeholder="Mot de passe" />
			<form:errors path="password" class="alert alert-danger" element="div" />
		</div>
		<div class="form-group">
			<label for="mail"><spring:message code="auteur.create.form.mail" text="default text" /></label>
			<form:input type="mail" class="form-control" id="mail" path="mail" placeholder="Mail de l'auteur" />
			<form:errors path="mail" class="alert alert-danger" element="div" />
		</div>
		<div class="form-group">
			<label for="url"><spring:message code="auteur.create.form.url" text="default text" /></label>
			<form:input type="text" class="form-control" id="url" path="url" placeholder="URL de l'auteur" />
			<form:errors path="url" class="alert alert-danger" element="div" />
		</div>
		<button type="submit" class="btn btn-default"><spring:message code="form.envoyer" text="default text" /></button>
		</form:form>

<jsp:include page="/template/footer.jsp" />