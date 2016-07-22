package web_test_1;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SampleServlet
 */
// アノテーション
// URLパターン : ブラウザからこのURLにアクセスするとgetDo()が実行され、htmlが生成されレスポンスされる
@WebServlet( "/SampleServlet" )

// サーブレットクラス : HttpServletクラスを継承したクラス
// 上記URLにリクエストがあったタイミングで、Webアプリサーバで自動的にインスタンス化される
// サーブレットクラスが実行された時に、実際に動くのはSampleServletのインスタンス
public class SampleServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	// コンストラクタ
	public SampleServlet(){
		super();
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	// ブラウザからGETでリクエストがあった時に実行される関数 doGet()
	//
	protected void doGet( HttpServletRequest request, HttpServletResponse response )
			throws ServletException, IOException{
		/*
		 * ・requestとresponseはインスタンス
		 * ・Webアプリ特有の処理のほとんどは、↑２つのインスタンスを用いて実現できる
		 * ・requestに格納れている情報を取り出し、計算や加工などを処理をし、responseを用いてブラウザに結果を表示する
		 */
		
		// 運勢をランダムで決定
		String[] luckyArray = { "超スッキリ", "スッキリ", "最悪" };
		
		// 0以上3未満の整数を乱数で生成
		// Mathクラスのrandom()は0~1未満の値の乱数を返す
		// その返り値をN倍してint型にキャストすると、0~N-1の整数の乱数を取得できる
		int index = ( int ) ( Math.random() * 3 );
		String luck = luckyArray[index];
		
		// 実行日を取得
		// java.util.Dateクラス(なんか他にもDate系があるみたい)
		// Dateインスタンスを生成すると、実行日時の情報が格納される
		Date date = new Date();
		// SimpleDateFormatクラスを使用すると、Dateが持つ情報を指定したフォーマットで取得できる
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy/MM/dd" );
		String today = sdf.format( date );
		
		// HTMLを出力
		// httpレスポンスのヘッダー部分
		response.setContentType( "text/html; charset=utf-8" );
		
		// httpレスポンスのボディ部分
		PrintWriter out = response.getWriter();
		out.println( "<html>" );
		out.println( "<head>" );
		out.println( "<title>スッキリ占い！</title>" );
		out.println( "</head>" );
		out.println( "<body>" );
		out.println( "<p>" + today + "の運勢は" + luck + "です</p>" );
		out.println( "</body>" );
		out.println( "</html>" );
		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	// ブラウザからPOSTでリクエストがあった時に実行される関数 doPost()
	protected void doPost( HttpServletRequest request, HttpServletResponse response )
			throws ServletException, IOException{
		doGet( request, response );
	}
	
}
