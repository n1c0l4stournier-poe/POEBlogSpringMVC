<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<jsp:include page="/template/header.jsp" />

	
	<div class="row">
		<div class="col-lg-3">
		
			<c:if test="${not empty articles_l}">
			<div class="row">
				<h4><i class="fa fa-folder-open"></i> <spring:message code="articles.lies" text="default text" /></h4>
				<ul>
				<c:forEach items="${articles_l}" var="article_l">
					<c:url var="urlShow" value="/articles/show.do?id=${article_l.id}" />
					<li><a href="${urlShow}">${article_l.titre}</a></li>
				</c:forEach>
				</ul>
			</div>
			<hr />
			</c:if>

			<c:if test="${not empty images}">
			<div class="row">
				<h4><i class="fa fa-picture-o"></i> <spring:message code="gallerie" text="default text" /></h4>
				<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
				  <!-- Indicators -->
				  <ol class="carousel-indicators">
				    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
				    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
				    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
				  </ol>
				
				  <!-- Wrapper for slides -->
				  <c:url var="urlResources" value="/" />
				  <div class="carousel-inner" role="listbox">
				  <c:set var="first" value="true" />
					<c:forEach items="${images}" var="image">
						<c:if test="${first eq false}">
						    <div class="item">
						      <img src="${image.url}" width="100%" alt="...">
						    </div>
					    </c:if>
						<c:if test="${first eq true}">
						    <div class="item active">
						      <img src="${image.url}" width="100%" alt="...">
					  		  <c:set var="first" value="false" />
						    </div>
					    </c:if>
					</c:forEach>
				  </div>
				
				  <!-- Controls -->
				  <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
				    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
				    <span class="sr-only">Previous</span>
				  </a>
				  <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
				    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				    <span class="sr-only">Next</span>
				  </a>
				</div>
			</div>
			<hr />
			</c:if>
			<div class="row">
				<h4><i class="fa fa-youtube"></i> <spring:message code="videos" text="default text" /></h4>
				<div>
				  <!-- Nav tabs -->
				  <ul class="nav nav-tabs" role="tablist">
				    <li role="presentation" class="active"><a href="#video1" aria-controls="video1" role="tab" data-toggle="tab">Video 1</a></li>
				    <li role="presentation"><a href="#video2" aria-controls="video2" role="tab" data-toggle="tab">Video 2</a></li>
				    <li role="presentation"><a href="#video3" aria-controls="video3" role="tab" data-toggle="tab">Video 3</a></li>
				  </ul>
				
				  <!-- Tab panes -->
				  <div class="tab-content">
				    <div role="tabpanel" class="tab-pane active" id="video1">
						<iframe src="https://www.youtube.com/embed/oOLR2-HE-oI" frameborder="0" allowfullscreen></iframe>
					</div>
				    <div role="tabpanel" class="tab-pane" id="video2">
				    	<iframe src="https://www.youtube.com/embed/wesUO81YX0U" frameborder="0" allowfullscreen></iframe>
				    </div>
				    <div role="tabpanel" class="tab-pane" id="video3">
						<iframe src="https://www.youtube.com/embed/nug1pMke-y4" frameborder="0" allowfullscreen></iframe>
					</div>
				  </div>
				</div>
			</div>
		</div>
		
		<div class="col-lg-8 col-lg-offset-1">
			<div class="row">
				<div class="page-header">
					<h3>${article.titre}<br /><small>par ${article.auteur.nomComplet}, le ${article.dateCreationJsp} (<a href="#commentaires"><span class="badge">${commentaires_nb}</span> commentaires</a>)</small></h3>
				</div>
				<p class="lead">${article.chapeau}</p>
				${article.contenu}
			</div>
		</div>
	</div>
	
	<hr />
	
	<div class="row">
		<h4 id="commentaires"><i class="fa fa-comments-o"></i> <spring:message code="commentaires" text="default text" /></h4>
		<c:forEach items="${commentaires}" var="commentaire">
			<c:url var="urlDelete" value="/commentaires/delete.do?id=${commentaire.id}" />
			<c:url var="urlUpdate" value="/commentaires/update.do?id=${commentaire.id}" />
			
			<h5><strong>${commentaire.nom}</strong><br /><small>par ${commentaire.auteur.nomComplet}, le ${commentaire.dateCreationJsp}</small></h5>
			<div class="bs-callout bs-callout-info">
				<p>${commentaire.text}</p>
			</div>
			<sec:authorize ifAllGranted="ROLE_ADMIN">
				<p class="text-right">
					<a href="${urlUpdate}" class="label label-primary"><i class="fa fa-pencil-square-o"></i> <spring:message code="action.update" text="default text" /></a>
					<a href="${urlDelete}" class="label label-danger"><i class="fa fa-trash-o"></i> <spring:message code="action.delete" text="default text" /></a>
				</p>
			</sec:authorize>
			
		</c:forEach>
	<hr />
	</div>
	
	<!-- Button trigger modal -->
	<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#modal">
	  <spring:message code="commentaire.action" text="default text" />
	</button>
	
	<div class="modal fade" tabindex="-1" role="dialog" id="modal">
	  <div class="modal-dialog modal-lg">
	    <div class="modal-content">
	    	<c:url var="saveCommentaire" value="/commentaires/save.do" />
			<form:form action="${saveCommentaire}" commandName="commentaire" method="POST">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<h4><i class="fa fa-commenting-o"></i> <spring:message code="commentaire.create.titre" text="default text" /></h4>
	      </div>
	      <div class="modal-body">	
				<form:hidden path="id" />
				<form:hidden path="article.id" />
				<form:hidden path="auteur.id" />
				<div class="form-group">
					<label for="nom"><spring:message code="commentaire.create.form.titre" text="default text" /></label>
					<form:input type="text" class="form-control" id="nom" path="nom" placeholder="Titre du commentaire" />
					<form:errors path="nom" class="alert alert-danger" element="div" />
				</div>
				<div class="form-group">
					<label for="text"><spring:message code="commentaire.create.form.contenu" text="default text" /></label>
					<form:textarea class="form-control" id="text" path="text"></form:textarea>
					<form:errors path="text" class="alert alert-danger" element="div" />
				</div>
	      </div>
	      <div class="modal-footer">
			<button type="submit" class="btn btn-success"><spring:message code="form.envoyer" text="default text" /></button>
	      </div>
			</form:form>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->



<jsp:include page="/template/footer.jsp" />