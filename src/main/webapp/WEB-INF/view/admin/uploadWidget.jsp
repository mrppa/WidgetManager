<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertDefinition name="defaultTemplate">
<tiles:putAttribute name="body">

	<div class="btn-group" role="group" aria-label="...">
  		<button type="button" class="btn btn-default" onclick="location.href='WidgetCenter'" ><spring:message code="widgetcenter.widgetcenterbtn"/></button>
	</div>
	
	<div class="panel panel-default">
  		<div class="panel-heading"><spring:message code="uploadwidget.widget" arguments="${widget.name}"/></div>
  		<div class="panel-body">
    	<div class="list-group">
  			<form method="POST" action="postUploadWidget?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data">
  				<input type="hidden" name="widgetName" value="${widget.name}">
   				<input type="file" name="file" /><br/>
    			<input type="submit" value='<spring:message code="uploadwidget.submit"/>'  class="btn btn-default" />
			</form>
		</div>
  		</div>
  	  	<div class="panel-footer"></div>
  	</div>
</tiles:putAttribute>
</tiles:insertDefinition>