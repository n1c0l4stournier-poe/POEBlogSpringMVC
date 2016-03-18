<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="/template/header.jsp" />

		<h1><spring:message code="categorie.create.titre" text="default text" /></h1>
		<c:url var="saveCategorie" value="/categories/save.do" />
		<form:form action="${saveCategorie}" commandName="categorie" method="POST">
		<form:hidden path="id" />
		<div class="form-group">
			<label for="nom"><spring:message code="categorie.create.form.nom" text="default text" /></label>
			<form:input type="text" class="form-control" id="nom" path="nom" placeholder="Nom de la catégorie"/>
			<form:errors path="nom" class="alert alert-danger" element="div" />
		</div>
		<div class="form-group">
			<label for="url"><spring:message code="categorie.create.form.url" text="default text" /></label>
			<form:input type="text" class="form-control" id="url" path="url" placeholder="URL de la catégorie" />
			<form:errors path="url" class="alert alert-danger" element="div" />
		</div>
		<button type="submit" class="btn btn-default"><spring:message code="form.envoyer" text="default text" /></button>
		</form:form>

<jsp:include page="/template/footer.jsp" />