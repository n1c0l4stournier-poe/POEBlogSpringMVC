<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<jsp:include page="/template/header.jsp" />

	<h1><spring:message code="article.liste.titre" text="default text" /></h1>
	<table class="table table-striped">
	<sec:authorize ifAllGranted="ROLE_ADMIN">
	<col width="80%">
	<col width="10%">
	<col width="10%">
	</sec:authorize>
		<c:forEach items="${articles}" var="article">
			<c:url var="urlDelete" value="/articles/delete.do?id=${article.id}" />
			<c:url var="urlUpdate" value="/articles/update.do?id=${article.id}" />
			<c:url var="urlShow" value="/articles/show.do?id=${article.id}" />
			<tr>
				<td><a href="${urlShow}">${article.titre}</a></td>
				<sec:authorize ifAllGranted="ROLE_ADMIN">
				<td><a href="${urlUpdate}" class="label label-primary"><i class="fa fa-pencil-square-o"></i> <spring:message code="action.update" text="default text" /></a></td>
				<td><a href="${urlDelete}" class="label label-danger"><i class="fa fa-trash-o"></i> <spring:message code="action.delete" text="default text" /></a></td>
				</sec:authorize>
			</tr>
		</c:forEach>
	</table>
	<hr />

	<c:url var="urlCreate" value="/articles/create.do" />
	<sec:authorize access="hasAnyRole('ROLE_AUTEUR', 'ROLE_ADMIN')">
		<a class="btn btn-default btn-sm" href="${urlCreate}">
		<i class="glyphicon glyphicon-plus-sign"></i> <spring:message code="article.create.titre" text="default text" /></a>
	</sec:authorize>
	
<jsp:include page="/template/footer.jsp" />