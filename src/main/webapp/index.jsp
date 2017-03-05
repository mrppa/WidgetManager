<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%><tiles:insertDefinition name="defaultTemplate">
<tiles:putAttribute name="body">

<div class="jumbotron">
  <h1>Hello,</h1>
  <h2>Welcome to Widget Manager</h2>
  <p><strong>Kindly delete/change this page in production</strong></p>
  <p>In order to create, upload and manage new widgets please refer to <button type="button" class="btn btn-primary btn-lg" onclick="location.href='admin/WidgetCenter'" >Widget Admin Center</button></p>
  <p> Ones new Widget is uploaded. You may can aceess the content via following url format</p>
  <p> <code>http://&lt;IP&gt;:&lt;PORT&gt/WidgetManager/widget/&lt;Widget Name&gt/<strong>&lt;Resource Url&gt</strong> </code></p>
</div>
</tiles:putAttribute>
</tiles:insertDefinition>