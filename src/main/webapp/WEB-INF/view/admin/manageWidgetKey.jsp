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
  		<div class="panel-heading"><spring:message code="managewidgetkey.widgetKeyList" arguments="${widgetName}"/></div>
  		<div class="panel-body">
    	<div class="list-group">
  			<c:forEach items="${widgetKeyList}" var="widgetKey">
        		
        		<a class="btn btn-large btn-primary" data-toggle="confirmation"
			        data-btn-ok-label='<spring:message code="managewidgetkey.deleteconfirm.ok"/>'  data-btn-ok-icon="glyphicon glyphicon-share-alt"
			        data-btn-ok-class="btn-success"
			        data-btn-cancel-label='<spring:message code="managewidgetkey.deleteconfirm.cancel"/>'  data-btn-cancel-icon="glyphicon glyphicon-ban-circle"
			        data-btn-cancel-class="btn-danger"
			        data-title='<spring:message code="managewidgetkey.deleteconfirm.header"/>' 
			        data-content='<spring:message code="managewidgetkey.deleteconfirm.content" arguments="${widgetKey.key}"/>' 
			        data-placement="bottom"
			        href ='postDeleteWidgetKey?widgetName=${widgetKey.widgetName}&key=${widgetKey.key}'
			        />${widgetKey.key}</a>
	        
      		</c:forEach>
      		<button type="button" class="btn btn-default" onclick="location.href='postAddWidgetKey?widgetName=${widgetName}'" ><spring:message code="managewidgetkey.addwidgetKey"/></button>
		</div>
  		</div>
  	  	<div class="panel-footer"></div>
  	</div>
</tiles:putAttribute>
</tiles:insertDefinition>