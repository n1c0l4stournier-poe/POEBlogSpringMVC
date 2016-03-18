<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<c:url var="urlResources" value="/" />
<link rel="stylesheet" href="${urlResources}/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${urlResources}/bootstrap/css/perso.css">
<link rel="stylesheet" href="${urlResources}/font-awesome/css/font-awesome.min.css">
<script src="${urlResources}/plugins/jquery-1.11.1.min.js"></script>
<script src="${urlResources}/bootstrap/js/bootstrap.min.js"></script>

<sec:authentication var="principal" property="principal" />

<title><spring:message code="titre" text="default text" /></title>
</head>
<body>
	<c:url var="home" value="/" />

	<c:url var="indexCategorie" value="/categories/index.do" />
	<c:url var="createCategorie" value="/categories/create.do" />

	<c:url var="indexAuteur" value="/auteurs/index.do" />
	<c:url var="createAuteur" value="/auteurs/create.do" />

	<c:url var="indexArticle" value="/articles/index.do" />
	<c:url var="createArticle" value="/articles/create.do" />

	<c:url var="indexCommentaire" value="/commentaires/index.do" />
	<c:url var="createCommentaire" value="/commentaires/create.do" />

	<c:url var="login" value="/pages/login.jsp" />
	<c:url var="logout" value="/logout" />

	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">

		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
				<span class="sr-only"><spring:message code="nav" text="default text" /></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${indexArticle}">
				<spring:message code="titre" text="default text" />
			</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li class="active">
					<a href="${indexArticle}">
						<i class="fa fa-home"></i> <spring:message code="nav.accueil" text="default text" />
					</a>
				</li>

				<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
					<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
					<spring:message code="nav.categorie" text="default text" /> <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="${indexCategorie}"><spring:message code="nav.liste" text="default text" /></a></li>
							<sec:authorize ifAllGranted="ROLE_ADMIN">
								<li><a href="${createCategorie}"><spring:message code="nav.ajouter" text="default text" /></a></li>
							</sec:authorize>
						</ul>
					</li>
				</sec:authorize>
				
				<sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_AUTEUR', 'ROLE_ADMIN')">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
						<spring:message code="nav.membre" text="default text" /> <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="${indexAuteur}"><spring:message code="nav.liste" text="default text" /></a></li>
						</ul>
					</li>
				</sec:authorize>
				
				<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
					<spring:message code="nav.commentaire" text="default text" /> <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="${indexCommentaire}"><spring:message code="nav.liste" text="default text" /></a></li>
						</ul>
					</li>
				</sec:authorize>
				
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
					<spring:message code="nav.langue" text="default text" /> <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="?lang=en"><spring:message code="nav.langue.en" text="default text" /></a></li>
						<li><a href="?lang=fr"><spring:message code="nav.langue.fr" text="default text" /></a></li>
					</ul>
				</li>

				<sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_AUTEUR', 'ROLE_ADMIN')">
					<li><a href="#"> 
						<sec:authorize access="hasAnyRole('ROLE_USER')">[<spring:message code="nav.user" text="default text" />]</sec:authorize> 
						<sec:authorize access="hasAnyRole('ROLE_AUTEUR')">[<spring:message code="nav.auteur" text="default text" />]</sec:authorize> 
						<sec:authorize access="hasAnyRole('ROLE_ADMIN')">[<spring:message code="nav.admin" text="default text" />]</sec:authorize>
						${principal.username}
					</a></li>
					<li><a href="${logout}"><i class="fa fa-sign-out"></i>[<spring:message code="nav.deconnexion" text="default text" />]</a></li>
				</sec:authorize>

			</ul>
		</div>
		<!--/.navbar-collapse -->
	</div>
	</nav>

		<div class="row" style="margin-top: 80px">
			<div class="container">
	
	