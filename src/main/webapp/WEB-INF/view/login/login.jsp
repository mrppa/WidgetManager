<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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

	<c:if test="${not empty successMsg}">
	<div class="alert alert-success alert-dismissible" role="alert">
		<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<strong><c:out value="${successMsg}"></c:out></strong>
	</div>
	</c:if>
	<c:if test="${not empty errorMsg}">
	<div class="alert alert-danger alert-dismissible" role="alert">
		<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<strong><c:out value="${errorMsg}"></c:out></strong>
	</div>
	</c:if>
	
	<div class="panel panel-default">
  		<div class="panel-heading"></div>
  		<div class="panel-body">   	
		<form name='loginForm'  action="<c:url value='j_spring_security_check'/>" method="POST" class="form-horizontal" >
			<div class="form-group">
				<label path="name" class="col-sm-1 control-label"><spring:message code="login.username"/></label>
				<div class="col-sm-6">
					<input type='text' name='username' value='' placeholder="username">
				</div>
			</div>
			<div class="form-group">
				<label path="name" class="col-sm-1 control-label"><spring:message code="login.password"/></label>
				<div class="col-sm-6">
					<input type='password' name='password' value=''  placeholder="password">
				</div>
			</div>
			 <button type="submit" class="btn btn-default"><spring:message code="login.submit"/></button>
			 <input type="hidden" name="${_csrf.parameterName}"	value="${_csrf.token}" />
		</form>
  		</div>
  	  	<div class="panel-footer"></div>
  	</div>
</body>
</html>