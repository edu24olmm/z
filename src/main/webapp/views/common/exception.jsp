<%@ page language="java"  pageEncoding="UTF-8"%>
<%@page import="org.apache.log4j.Logger"%>
<meta http-equiv="Content-Type" content="text/javascript;charset=UTF-8">
<s:if test="%{exception.message != null}">
	{"exception":"<s:property value="%{getText(exception.message)}"/>"}
</s:if>
<s:else>
	{"exception":"<s:property value="%{getText('program.error')}"/>"}
</s:else>
<%
	String es = request.getAttribute("exceptionStack").toString();
	Logger log = Logger.getLogger("error_");
	log.error(es);
	System.out.println(es);
%>