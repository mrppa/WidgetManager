<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>WidgetManager</title>
<spring:url value="/resources/img/favicon.ico" var="faviconIco" />
<spring:url value="/resources/bower_components/jquery/dist/jquery.min.js" var="jQueryJs" />
<spring:url value="/resources/bower_components/bootstrap/dist/css/bootstrap.min.css" var="bootstrapCss" />
<spring:url value="/resources/bower_components/bootstrap/dist/js/bootstrap.min.js" var="bootstrapJs" />
<spring:url value="/resources/bower_components/bootstrap-confirmation2/bootstrap-confirmation.min.js" var="bootstrapConfimation2Js" />
<spring:url value="/resources/js/modernizr-custom.js" var="modernizrJs" />
<script src="${jQueryJs}"></script>
<script src="${bootstrapJs}"></script>
<script src="${bootstrapConfimation2Js}"></script>
<link href="${bootstrapCss}" rel="stylesheet" />
<script src="${modernizrJs}"></script>
<link href="${faviconIco}" rel="shortcut icon" />
<script>
$(document).ready(function() {
	$('[data-toggle="confirmation"]').confirmation();
});
</script>
</head>
<body>
	<div class="page">
		<tiles:insertAttribute name="header" />
		<div class="content">
			<tiles:insertAttribute name="menu" />
			<tiles:insertAttribute name="body" />
		</div>
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>