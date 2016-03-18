<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<jsp:include page="/template/header.jsp" />

		<h1><spring:message code="image.create.titre" text="ajouter une image" /></h1>
		<c:url var="save" value="/images/save.do" />
		<form:form action="${save}" commandName="image" method="POST">
			<form:hidden path="id" />
			<div class="form-group">
				<label for="url"><spring:message code="categorie.create.form.url" text="default text" /></label>
				<form:input type="text" class="form-control" id="url" path="url" placeholder="URL de l'image"/>
				<form:errors path="url" class="alert alert-danger" element="div" />
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