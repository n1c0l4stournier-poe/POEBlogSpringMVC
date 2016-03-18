<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<jsp:include page="/template/header.jsp" />


<h1><spring:message code="categorie.liste.titre" text="default text" /></h1>
<c:url var="urlCreate" value="/categories/create.do" />

<table class="table table-striped">
	<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
		<col width="80%">
		<col width="10%">
		<col width="10%">
	</sec:authorize>
	<c:forEach items="${categories}" var="categorie">
		<c:url var="urlDelete"
			value="/categories/delete.do?id=${categorie.id}" />
		<c:url var="urlUpdate"
			value="/categories/update.do?id=${categorie.id}" />
		<tr>
			<td><a href="${urlShow}">${categorie.nom}</a></td>
			<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
				<td><a href="${urlUpdate}" class="label label-primary"><i class="fa fa-pencil-square-o"></i> <spring:message code="action.update" text="default text" /></a></td>
				<td><a href="${urlDelete}" class="label label-danger"><i class="fa fa-trash-o"></i> <spring:message code="action.delete" text="default text" /></a></td>
			</sec:authorize>
		</tr>
	</c:forEach>
</table>
<hr />

<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
	<a class="btn btn-default" href="${urlCreate}"> <i
		class="glyphicon glyphicon-plus-sign"></i> <spring:message code="categorie.create.titre" text="default text" />
	</a>
</sec:authorize>

<jsp:include page="/template/footer.jsp" />