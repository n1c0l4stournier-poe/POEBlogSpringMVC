<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<jsp:include page="/template/header.jsp" />

<h1><spring:message code="auteur.liste.titre" text="default text" /></h1>
<c:url var="urlCreate" value="/auteurs/create.do" />

<table class="table table-striped">
	<c:forEach items="${auteurs}" var="auteur">
		<c:url var="urlDelete" value="/auteurs/delete.do?id=${auteur.id}" />
		<c:url var="urlUpdate" value="/auteurs/update.do?id=${auteur.id}" />
		<tr>
			<td>${auteur.id}</td>
			<td>${auteur.nom}</td>
			<td>${auteur.prenom}</td>
			<td>${auteur.mail}</td>
			<sec:authorize ifAllGranted="ROLE_ADMIN">
				<td><a href="${urlUpdate}" class="label label-primary"><i class="fa fa-pencil-square-o"></i> <spring:message code="action.update" text="default text" /></a></td>
				<td><a href="${urlDelete}" class="label label-danger"><i class="fa fa-trash-o"></i> <spring:message code="action.delete" text="default text" /></a></td>
			</sec:authorize>
		</tr>
	</c:forEach>
</table>
<hr />

<jsp:include page="/template/footer.jsp" />