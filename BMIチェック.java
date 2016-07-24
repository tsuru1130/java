/*
	【BMIチェック】	
	
	HealthCheack.java(C) : リクエストを処理するコントローラ
		↓
	HealthCheack.jsp(V) : 健康診断画面を出力するビュー
		↓
	HealthCheack.java(C/親) ←HealthCheackLogic.java(M/子)←HealthCheack.java(M/子子)
		↓
	HealthCheackResult.jsp(V) : 健康診断結果画面を出力するビュー
	
	
	※HealthCheackLogic.java(M/子) : 健康診断に関する処理を行うモデル
	※ealthCheack.java(M/子子) : 健康診断に関する情報を持つJavaBeansのモデル
	
	
*/

/*
	【JavaBeans】
	・jspで受け取る場合は暗黙オブジェクトで受け取る(宣言が必要ない)
	・Javaクラスの独立性を高める為のインスタンス(HttpServletRequestのインスタンス)
	・関連する情報をひとかたまりにして保持または使い回すことも目的としている
	・JavaBeans(というインスタンス)を、スコープに保存するとjspで共有ができる
		→ request.setAttribute("String型", Object型); で登録
	・スコープに保存できるのはインスタンスのみで、int型のような基本データ型変数は入れられない
	・そしてスコープに入れるインスタンスはJavaBeans(実体はただのルールがいくつか決まっているクラス)
	・スコープは４種類あり、種類によって有効期限が異なる
		①ページスコープ : ???
		②リクエストスコープ : リクエスト同時(かつリクエスト毎)に生成され、レスポンス(ブラウザに返すこと)と同時に破棄される
					 : リクエストをまたいでインスタンスを共有できない
		③セッションスコープ : 
		④アプリケーションスコープ : 

	
*/


/*
	HealthCheack.java(C)	
	
*/
package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Health;
import model.HealthCheckLogic;

/**
 * Servlet implementation class HealthCheck
 */

// ブラウザからアクセスする際は「http://localhost:8080/health/HealthCheck」
// ↑ アプリ名を忘れないように！
@WebServlet( "/HealthCheck" )
public class HealthCheck extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HealthCheck(){
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
		RequestDispatcher dispatcher = request.getRequestDispatcher( "/WEB-INF/jsp/healthCheck.jsp" );
		dispatcher.forward( request, response );
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	protected void doPost( HttpServletRequest request, HttpServletResponse response )
			throws ServletException, IOException{
		
		// jsp側で入力されたリクエストパラメータを取得
		String weight = request.getParameter( "weight" ); // 体重
		String height = request.getParameter( "height" ); // 身長
		
		// 入力値をプロパティに設定
		Health health = new Health(); // importは自動でされる
		health.setHeight( Double.parseDouble( height ) );
		health.setWeight( Double.parseDouble( weight ) );
		
		// 健康診断を実行し、結果を設定
		HealthCheckLogic healthCheckLogic = new HealthCheckLogic();
		healthCheckLogic.execute( health );
		
		// リクエストスコープに保存
		request.setAttribute( "health", health );
		
		// 【フォワード】
		RequestDispatcher dispatcher = request.getRequestDispatcher( "/WEB-INF/jsp/healthCheckResult.jsp" );
		dispatcher.forward( request, response );
		
	}
	
}

/*
	HealthCheack.jsp(V)
	
*/
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BMI Cheak!</title>
</head>
<body>

<form action="/health/HealthCheck" method="post">
	身長 : <input type="text" name="height" />(cm)<br />
	体重 : <input type="text" name="weight" />(kg)<br />
	<input type="submit" value="送信！"/>
</form>

</body>
</html>



/*
	HealthCheackLogic.java(M/子)	
	
*/
package model;

/*
 * 
 * 	model
 * 
 * */

public class HealthCheckLogic{
	public void execute( Health health ){
		// BMIを算出して設定
		double weight = health.getWeight();
		double height = health.getHeight();
		double bmi = weight / ( height / 100.0 * height / 100.0 );
		health.setBmi( bmi );
		
		// BMI指数から体型を判定して設定
		String bodyType;
		if( bmi < 18.5 ) {
			bodyType = "瘦せ型";
		} else if( bmi < 25 ) {
			bodyType = "普通";
		} else {
			bodyType = "肥満";
		}
		health.setBodyType( bodyType );
		
	}
}



/*
	HealthCheack.java(M/子子)
	
*/
package model;

import java.io.Serializable;

/*
 * 
 * 	model
 * 
 * */

/*  
 * JavaBeansの条件
 * ① : 直列化(シリアライズ)可能
 * ② : クラスはpublic
 * ③ : publicで引数なしのコンストラクタを持つ
 * ④ : フィールドはカプセル化
 * ⑤ : 命名規則に従ったgetter/setterを持つ(戻り値がbooleanの場合はis__から始まる)
 * */
public class Health implements Serializable{
	private double	height, weight, bmi;
	private String	bodyType;
	
	public double getHeight(){
		return height;
	}
	
	public double getWeight(){
		return weight;
	}
	
	public double getBmi(){
		return bmi;
	}
	
	public String getBodyType(){
		return bodyType;
	}
	
	public void setHeight( double height ){
		this.height = height;
	}
	
	public void setWeight( double weight ){
		this.weight = weight;
	}
	
	public void setBmi( double bmi ){
		this.bmi = bmi;
	}
	
	public void setBodyType( String bodyType ){
		this.bodyType = bodyType;
	}
	
}

/*
	HealthCheackResult.jsp(V)
	
*/

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="model.Health" %>
<% 
	// リクエストスコープに保存されたHealthインスタンスを取得
	Health health = (Health) request.getAttribute( "health" );
	
%>   

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>
		身長 : <%= health.getHeight(  ) %><br>
		体重 : <%= health.getWeight(  ) %><br>
		BMI : <%= health.getBmi(  ) %><br>
	</p>
	<a href="/health/HealthCheck">戻る</a>

</body>
</html>


