<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertDefinition name="defaultTemplate">
<tiles:putAttribute name="body">

	<button type="button" class="btn btn-default" onclick="location.href='viewAddWidget'" ><spring:message code="widgetcenter.addwidget"/></button>
	
	<div class="panel panel-default">
  		<div class="panel-heading"><spring:message code="widgetcenter.widgetlist"/></div>
  		<div class="panel-body">
    	<div class="list-group">
  			<c:forEach items="${widgetList}" var="widget">
        		<a href="viewModifyWidget?widgetName=${widget.name}" class="list-group-item">${widget.name}</a>
      		</c:forEach>
		</div>
  		</div>
  	  	<div class="panel-footer"></div>
  	</div>
</tiles:putAttribute>
</tiles:insertDefinition>