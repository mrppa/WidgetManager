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
<script>
$(document).ready(function() {
	$('[data-toggle="confirmation"]').confirmation();
});
</script>
</head>
<body>

	<div class="page-header">
		<h1><spring:message code="modifywidget.header"/></h1>
	</div>
	<c:if test="${not empty errorMsg}">
	<div class="alert alert-danger alert-dismissible" role="alert">
		<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<strong><c:out value="${errorMsg}"></c:out></strong>
	</div>
	</c:if>
	<div class="btn-group" role="group" aria-label="...">
  		<button type="button" class="btn btn-default" onclick="location.href='WidgetCenter'" ><spring:message code="widgetcenter.widgetcenterbtn"/></button>
  		<button type="button" class="btn btn-default" onclick="location.href='viewUploadWidget?widgetName=${widget.name}'" ><spring:message code="modifywidget.fileupldbtn"/></button>
		<a class="btn btn-large btn-primary" data-toggle="confirmation"
	        data-btn-ok-label='<spring:message code="widgetcenter.deleteconfirm.ok"/>'  data-btn-ok-icon="glyphicon glyphicon-share-alt"
	        data-btn-ok-class="btn-success"
	        data-btn-cancel-label='<spring:message code="widgetcenter.deleteconfirm.cancel"/>'  data-btn-cancel-icon="glyphicon glyphicon-ban-circle"
	        data-btn-cancel-class="btn-danger"
	        data-title='<spring:message code="widgetcenter.deleteconfirm.header"/>' 
	        data-content='<spring:message code="widgetcenter.deleteconfirm.content" arguments="${widget.name}"/>' 
	        data-placement="bottom"
	        href ='postDeleteWidget?widgetName=${widget.name}'
	        /><spring:message code="widgetcenter.deleteconfirmbtn"/></a>
	</div>
	
	<div class="panel panel-default">
  		<div class="panel-heading"><spring:message code="uploadwidget.widget" arguments="${widget.name}"/></div>
  		<div class="panel-body">   	
		<form:form commandName="widget" action="postModifyWidget" method="POST" class="form-horizontal">
			<div class="form-group">
				<form:label path="name" class="col-sm-1 control-label"><spring:message code="modifywidget.name"/></form:label>
				<div class="col-sm-6">
					<form:input path="name" class="form-control"  readonly="true"/>
				</div>
				<form:errors path="name"  class="col-sm-3 control-label"/>
			</div>
			<div class="form-group">
				<form:label path="description" class="col-sm-1 control-label"><spring:message code="modifywidget.description"/></form:label>
				<div class="col-sm-6">
					<form:textarea path="description" class="form-control"/>
				</div>
				<form:errors path="description"  class="col-sm-3 control-label"/>
			</div>
			<div class="form-group">
				<form:label path="widgetID" class="col-sm-1 control-label"><spring:message code="modifywidget.widgetID"/></form:label>
				<div class="col-sm-6">
					<form:input path="widgetID" class="form-control"  readonly="true"/>
				</div>
				<form:errors path="widgetID"  class="col-sm-3 control-label"/>
			</div>
			 <button type="submit" class="btn btn-default"><spring:message code="modifywidget.submit"/></button>
		</form:form>
  		</div>
  	  	<div class="panel-footer"></div>
  	</div>	
		
</body>
</html>