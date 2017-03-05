<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertDefinition name="defaultTemplate">
<tiles:putAttribute name="body">
	
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
</tiles:putAttribute>
</tiles:insertDefinition>