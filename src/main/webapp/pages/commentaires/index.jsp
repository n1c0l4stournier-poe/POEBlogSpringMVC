<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<jsp:include page="/template/header.jsp" />

		<h1><spring:message code="commentaire.liste.titre" text="default text" /></h1>
		<c:forEach items="${commentaires}" var="commentaire">
			<c:url var="urlDelete" value="/commentaires/delete.do?id=${commentaire.id}" />
			<c:url var="urlUpdate" value="/commentaires/update.do?id=${commentaire.id}" />
				<p><strong>${commentaire.nom}</strong><br />${commentaire.text}</p>
				<a href="${urlUpdate}" class="label label-primary"><i class="fa fa-pencil-square-o"></i> <spring:message code="action.update" text="default text" /></a>
				<a href="${urlDelete}" class="label label-danger"><i class="fa fa-trash-o"></i> <spring:message code="action.delete" text="default text" /></a>
				<hr />
		</c:forEach>

<jsp:include page="/template/footer.jsp" />