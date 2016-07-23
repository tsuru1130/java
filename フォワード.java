package web_test_1;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet( "/ForwardServlet" )
public class ForwardServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ForwardServlet(){
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	protected void doGet( HttpServletRequest request, HttpServletResponse response )
			throws ServletException, IOException{
		
		// 【フォワード】
		/*
		 * MVCモデルの場合、コントローラが処理結果の表示をビューに依頼する際に、 サーブレットからjspファイルを利用する
		 * →この時にフォワードを使って、jspファイル(またはサーブレットクラス)に処理を任せることができる。
		 * ・他にも処理を転送させる方法は"リダイレクト"がある
		 * →response.sendRedirect("リダイレクト先のURL" ); →リダイレクト先はURLで指定
		 */
		RequestDispatcher dispatcher = request.getRequestDispatcher( "/WEB-INF/jsp/forward.jsp" );
		dispatcher.forward( request, response );
		
		/*
		 * 【リダイレクト】・・・ただ転送するだけ
		 *  使い分け : フォワードはリクエスト/レスポンスが１往復で、リダイレクトは２往復
		 *  	    :  フォワードのURLはそのまま。リダイレクトは転送先のURL
		 * 		→同じアプリ内であればフォワードを使う
		 * 		→別アプリに転送する場合はリダイレクト(しかない。フォワードは同じアプリ内でしか使えないので)
		 * 		→例外として同じアプリ内でもリダイレクトを使うことがある
		 * 		→フォワードの場合はURLがそのままなので、処理内容とURLの意味があまりにも異なる場合はリダイレクト
		 * */
		// response.sendRedirect( "/WEB-INF/jsp/forward.jsp" );
		
	}
	
}


/*
	
	ここからjsp (↑の処理が↓に任される)
		
*/

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    
/*
	・フォワード先としてしかリクエストされないjspファイルは、通常のurlからリクエストされると、
		エラーや不具合のへんインとなる
		→従ってWEB-INFディレクトリ内に入れることによってリクエスト禁止にする
*/

/*
	サーブレットに送る場合は「/アプリ名/URLパターン」
		例 : /web_test_1/SampleServlet
	JSPファイルに送る場合は「/アプリ名/WebContentからのパス」	
		例 : /web_test_1/Receive_TestTsuru2.jsp
*/
    
    
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	forward.jspだよ

</body>
</html>
