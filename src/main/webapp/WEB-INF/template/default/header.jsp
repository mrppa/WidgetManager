<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<div class="header">

	<c:if test="${not empty pageHeader}">
	<div class="page-header">
		<h1><c:out value="${pageHeader}"></c:out></h1>
	</div>
	</c:if>
	
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
</div>