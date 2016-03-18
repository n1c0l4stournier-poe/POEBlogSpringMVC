<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<jsp:include page="/template/header.jsp" />

		<h1><spring:message code="article.create.titre" text="default text" /></h1>
		<c:url var="saveArticle" value="/articles/save.do" />
		<form:form action="${saveArticle}" commandName="article" method="POST">
			<form:hidden path="id" />
			<form:hidden path="auteur.id" />
			<div class="form-group">
				<label for="titre"><spring:message code="article.create.form.titre" text="default text" /></label>
				<form:input type="text" class="form-control" id="titre" path="titre"
					placeholder="Titre de l'article" />
				<form:errors path="titre" class="alert alert-danger" element="div" />
			</div>
			<div class="form-group">
				<label for="chapeau"><spring:message code="article.create.form.chapeau" text="default text" /></label>
				<form:textarea class="form-control" id="chapeau" path="chapeau"></form:textarea>
				<form:errors path="chapeau" class="alert alert-danger" element="div" />
			</div>
			<div class="form-group">
				<label for="contenu"><spring:message code="article.create.form.contenu" text="default text" /></label>
				<form:textarea class="form-control" id="contenu" path="contenu"></form:textarea>
				<form:errors path="contenu" class="alert alert-danger" element="div" />
			</div>
			<div class="form-group">
				<label for="categorie"><spring:message code="article.create.form.categories" text="default text" /></label>
				<form:select id="categorie" name="categorie" path="categorie.id">
					<form:option value="" label="-----" />
					<form:options items="${categories}" itemValue="id" itemLabel="nom" />
				</form:select>
			</div>
			<button type="submit" class="btn btn-default"><spring:message code="form.envoyer" text="default text" /></button>
		</form:form>
		
<jsp:include page="/template/footer.jsp" />