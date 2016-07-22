<!--
	
	Hero.java
	
-->

package hero;

public class Hero{
	private String	id;
	private String	name;
	
	public Hero( String id, String name ){
		this.id = id;
		this.name = name;
	}
	
	public String getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
}



<!--
	
	sample.jsp
	
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="hero.*" %>
<%
	Hero h = new Hero("0001","tsuruchoff");
	String id = h.getId( );
	String name = h.getName(  );
	
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<% for(int i = 0; i < 10; i++ ){%>
		
			<!-- 1,4,7,10の場合はred -->
			<% if (i % 3 == 0){ %>
				<p style="color: red">
			<% } else { %>
				<p>
			<% } %>
			
			IDは<%= id %>、名前は<%= name %>です</p>
		
	<% } %>


</body>
</html>