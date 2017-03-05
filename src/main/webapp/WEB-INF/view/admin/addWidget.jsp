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
  		<div class="panel-heading"></div>
  		<div class="panel-body">   	
		<form:form commandName="widget" action="postAddWidget" method="POST" class="form-horizontal">
			<div class="form-group">
				<form:label path="name" class="col-sm-1 control-label"><spring:message code="addwidget.name"/></form:label>
				<div class="col-sm-6">
					<form:input path="name" class="form-control"/>
				</div>
				<form:errors path="name"  class="col-sm-3 control-label"/>
			</div>
			<div class="form-group">
				<form:label path="description" class="col-sm-1 control-label"><spring:message code="addwidget.description"/></form:label>
				<div class="col-sm-6">
					<form:textarea path="description" class="form-control"/>
				</div>
				<form:errors path="description"  class="col-sm-3 control-label"/>
			</div>
			 <button type="submit" class="btn btn-default"><spring:message code="addwidget.submit"/></button>
		</form:form>
  		</div>
  	  	<div class="panel-footer"></div>
  	</div>
</tiles:putAttribute>
</tiles:insertDefinition>