<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>WidgetManager</title>
<spring:url value="/resources/bower_components/bootstrap/dist/css/bootstrap.min.css" var="bootstrapCss" />
<spring:url value="/resources/bower_components/bootstrap/dist/js/bootstrap.min.js" var="bootstrapJs" />
<spring:url value="/resources/js/modernizr-custom.js" var="modernizrJs" />
<spring:url value="/resources/bower_components/jquery/dist/jquery.min.js" var="jQueryJs" />

<script src="${jQueryJs}"></script>

<script src="${bootstrapJs}"></script>
<link href="${bootstrapCss}" rel="stylesheet" />

<script src="${modernizrJs}"></script>

</head>
<body>

	<div class="page-header">
		<h1><spring:message code="uploadwidget.header"/></h1>
	</div>
	<c:if test="${not empty errorMsg}">
	<div class="alert alert-danger alert-dismissible" role="alert">
		<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<strong><c:out value="${errorMsg}"></c:out></strong>
	</div>
	</c:if>
	<div class="btn-group" role="group" aria-label="...">
  		<button type="button" class="btn btn-default" onclick="location.href='WidgetCenter'" ><spring:message code="widgetcenter.widgetcenterbtn"/></button>
	</div>
	
	<div class="panel panel-default">
  		<div class="panel-heading"><spring:message code="uploadwidget.widget" arguments="${widget.name}"/></div>
  		<div class="panel-body">
    	<div class="list-group">
  			<form method="POST" action="postUploadWidget" enctype="multipart/form-data">
  				<input type="hidden" name="widgetName" value="${widget.name}">
   				<input type="file" name="file" /><br/>
    			<input type="submit" value='<spring:message code="uploadwidget.submit"/>'  class="btn btn-default" />
			</form>
		</div>
  		</div>
  	  	<div class="panel-footer"></div>
  	</div>
	
		
		
</body>
</html>