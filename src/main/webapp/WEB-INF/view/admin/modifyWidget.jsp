<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertDefinition name="defaultTemplate">
<tiles:putAttribute name="body">

	<div class="btn-group" role="group" aria-label="...">
  		<button type="button" class="btn btn-default" onclick="location.href='WidgetCenter'" ><spring:message code="widgetcenter.widgetcenterbtn"/></button>
  		<button type="button" class="btn btn-default" onclick="location.href='viewUploadWidget?widgetName=${widget.name}'" ><spring:message code="modifywidget.fileupldbtn"/></button>
		<button type="button" class="btn btn-default" onclick="location.href='viewManageWidgetKey?widgetName=${widget.name}'" ><spring:message code="modifywidget.keyMgtbtn"/></button>
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
		
</tiles:putAttribute>
</tiles:insertDefinition>