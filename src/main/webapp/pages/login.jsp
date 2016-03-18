<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="../template/header.jsp" />
<c:url var="createAuteur" value="/auteurs/create.do" />
		<h1><spring:message code="login.titre" text="default text" /></h1>
		<form action="j_spring_security_check" method="POST">
			<div class="form-group">
				<label><spring:message code="form.mail" text="default text" /></label> 
				<input type="text" placeholder="exemple@mail.com" class="form-control" name="j_username">
			</div>
			<div class="form-group">
				<label><spring:message code="form.pass" text="default text" /></label> 
				<input type="password" placeholder="Mot de passe" class="form-control" name="j_password" id="inputPassword">
			</div>
			<button type="submit" class="btn btn-success"><spring:message code="form.connexion" text="default text" /></button>
			<a href="${createAuteur}" class="btn btn-info"><i class="fa fa-user-plus"></i> <spring:message code="form.inscription" text="default text" /></a>
		</form>
</body>
</html>