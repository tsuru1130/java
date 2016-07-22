<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date" %>

<!-- コンパイルエラーは500 -->

<%-- htmlは<meta>で指定した文字コードで出力される
	↓で指定した文字コードはこのjspファイルの文字コード --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%
	// 運勢リスト
	String[] luckArray = {"超スッキリ","スッキリ","最悪"};
	
	// 0以上3未満の整数(0,1,2)を乱数で生成
	int index = (int) (Math.random() *3);
	String luck = luckArray[index];
	
	// 実行日を取得
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	String today = sdf.format( date );
		

	
%>

<!-- ↓は出力されるhtmlのDOCTYPEと文字コードの指定 -->
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<p><%= today %>の運勢は<%= luck %>です。</p>

</body>
</html>