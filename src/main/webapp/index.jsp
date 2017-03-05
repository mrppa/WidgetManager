<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%><tiles:insertDefinition name="defaultTemplate">
<tiles:putAttribute name="body">
	<h2></h2>
	<h1>Widget manager</h1>
	<button type="button" class="btn btn-default" onclick="location.href='admin/WidgetCenter'" >Widget Admin Center</button>
</tiles:putAttribute>
</tiles:insertDefinition>