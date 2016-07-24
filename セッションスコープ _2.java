/*
 * 【会員登録フロー】
 * 	RegisteUser.java : リクエストを処理
 * 		↓
 * 	registerForm.jsp : 登録画面を出力するビュー
 * 		↓
 * 	RegisterUser.java : リクエストパラメータが返ってきたら、getSession()をしてセッションスコープを作り、登録
 * 		↓
 * 	registerConfirm.jsp : 確認画面に出力するビュー(１回目の取得)
 * 		↓
 * 	RegisteUser.java : 仮DBに登録するために２回目の取得
 * 		↓
 * 	registeDone.jsp : 完了画面を出力するビュー
 * 
 *  ・↑のフローではすべてのリクエストをRegisteUser.javaに集中させているが、
 *  	開発するアプリの規模や複雑さに応じて、リクエスト毎に別のサーブレットクラスを作成する場合もある
 * 
 * */

package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.RegisterUserLogic;
import model.User;

/**
 * Servlet implementation class RegisterUser
 */
@WebServlet( "/RegisterUser" )
public class RegisterUser extends HttpServlet{ // HttpSessionと似てる
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterUser(){
		super();
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	protected void doGet( HttpServletRequest request, HttpServletResponse response )
			throws ServletException, IOException{ //セッションはrequestから使う
		
		// フォワード先
		// if分の外でメモリスペースを確保
		String forwardPath = null;
		
		/*
			サーブレットクラスの動作を決定する「action」の値を
		 		リクエストパラメータから取得
		 */
		String action = request.getParameter( "action" );
		
		// 「登録の開始」をリクエストされた時の処理
		if( action == null ) {
			// フォワード先の設定(会員情報入力画面へ)
			forwardPath = "/WEB-INF/jsp/registerForm.jsp";
			
		} else if( action.equals( "done" ) ) { //文字列の比較だからequals()を使う
			// 登録確認画面から「登録実行」をリクエストされた時の処理
			// ↓でsession変数にセッションスコープが格納される。このsessionを使ってセッションを管理する
			HttpSession session = request.getSession(); // ←のrequestはHttpServletRequestインスタンス
			// ↑ getSession()でセッションIDを生成するが、二回目のため確認になる
			
			User registeUser = ( User ) session.getAttribute( "registerUser" );
			
			// 登録処理の呼び出し
			RegisterUserLogic logic = new RegisterUserLogic();
			logic.execute( registeUser );
			
			// 不要になったセッションスコープ内のインスタンスを削除
			session.removeAttribute( "registerUser" );
			
			// 登録後のフォワード先の設定
			forwardPath = "/WEB-INF/jsp/registerDone.jsp";
			
		}
		
		// 設定されたフォワード先にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher( forwardPath );
		dispatcher.forward( request, response );
		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	protected void doPost( HttpServletRequest request, HttpServletResponse response )
			throws ServletException, IOException{
		
		// リクエストパラメータの取得
		request.setCharacterEncoding( "UTF-8" ); // URLエンコードされたカオスな文字を元に戻す
		String id = request.getParameter( "id" );
		String name = request.getParameter( "name" );
		String pass = request.getParameter( "pass" );
		
		// 登録するユーザーの情報を設定
		// JavaBeansをインスタンス化
		// 元で(ここでいうUser)フィールド定義されていないと、セッション保存ができない
		User registerUser = new User( id, name, pass );
		
		// セッションスコープに登録ユーザーを保存
		// ここが１回目のgetSession()。つまり、ここでセッションIDが生成される
		// セッションインスタンスはガベージコレクションの対象外
		HttpSession session = request.getSession();
		session.setAttribute( "registerUser", registerUser );
		// ↑でJavaBeansをセッションに登録
		
		
		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher( "/WEB-INF/jsp/registerConfirm.jsp" );
		dispatcher.forward( request, response );
		
	}
	
}

/*
	
	registerForm.jsp
	
*/
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー登録</title>
</head>
<body>

	<form action="/member/RegisterUser" method="post">
		ログイン :<input type="text" name="id" /><br>
		パスワード :<input type="password" name="pass" /><br>
		名前 :<input type="text" name="name"/><br>
		<input type="submit" value="確認"/>
	</form>

</body>
</html>



/*
	
	RegisterUserLogic.java
	
*/
package model;

public class RegisterUserLogic{
	public boolean execute( User user ){
		// 登録処理(サンプルでは登録処理を行わない)
		return true;
		
	}
}

/*
	
	User.java
	
*/

package model;

/*
 * 	JavaBeans
 * 
 * */

import java.io.Serializable;

public class User implements Serializable{
	private String	id;
	private String	name;
	private String	pass;
	
	// JavaBeansに必要な引数なしのコンストラクタ
	public User(){
	}
	
	// setterの代わりに、コンストラクタで一気に入れる
	public User( String id, String name, String pass ){
		this.id = id;
		this.name = name;
		this.pass = pass;
	}
	
	public String getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public String getPass(){
		return pass;
	}
	
}



/*
	
	registerConfirm.jsp
	
*/

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %> // JavaBeansをインポート
<%
	// sessionに登録されたJavaBeans(registerUser)をインスタンス化
	// Object型で登録されているからキャストが必要
	User registerUser = (User) session.getAttribute( "registerUser" );
%>

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー登録</title>
</head>
<body>

<p>ユーザーを登録します</p>
<p>
ログインID : <%= registerUser.getId() %><br>
名前 : <%= registerUser.getName() %><br>
</p>
<a href="/member/RegisterUser">戻る</a>
<a href="/member/RegisterUser?action=done">登録</a>

</body>
</html>

/*
	
	registeDone.jsp
	
*/
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー登録</title>
</head>
<body>
<p>登録完了しました</p>
<a href="/member/RegisterUser">戻る</a>
</body>
</html>

