<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="/template/header.jsp" />

		<h1><spring:message code="commentaire.create.titre" text="default text" /></h1>
		<c:url var="saveCommentaire" value="/commentaires/save.do" />
		<form:form action="${saveCommentaire}" commandName="commentaire" method="POST">
			<form:hidden path="id" />
			<div class="form-group">
				<label for="nom"><spring:message code="commentaire.create.form.nom" text="default text" /></label>
				<form:input type="text" class="form-control" id="nom" path="nom"
					placeholder="Titre du commentaire" />
				<form:errors path="nom" class="alert alert-danger" element="div" />
			</div>
			<div class="form-group">
				<label for="mail"><spring:message code="commentaire.create.form.mail" text="default text" /></label>
				<form:input type="text" class="form-control" id="mail" path="mail"
					placeholder="Mail de l'auteur" />
				<form:errors path="mail" class="alert alert-danger" element="div" />
			</div>
			<div class="form-group">
				<label for="text"><spring:message code="commentaire.create.form.contenu" text="default text" /></label>
				<form:textarea class="form-control" id="text" path="text"></form:textarea>
				<form:errors path="text" class="alert alert-danger" element="div" />
			</div>
			<div class="form-group">
				<spring:message code="commentaire.create.form.auteur" text="default text" />
				<form:select id="auteur" name="auteur" path="auteur.id">
					<form:option value="" label="------" />
					<form:options items="${auteurs}" itemValue="id" itemLabel="nomComplet" />
				</form:select>
			</div>
			<div class="form-group">
				<spring:message code="commentaire.create.form.article" text="default text" />
				<form:select id="article" name="article" path="article.id">
					<form:option value="" label="------" />
					<form:options items="${articles}" itemValue="id" itemLabel="titre" />
				</form:select>
			</div>
			<button type="submit" class="btn btn-default"><spring:message code="form.envoyer" text="default text" /></button>
		</form:form>

<jsp:include page="/template/footer.jsp" />