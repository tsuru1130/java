package aaa;

/*
 * 【データベース】
 *  実際の運用はJavaプログラムが動作するPCとDBがあるPCは別々のため、DBへの接続は通信が基本
 *  JDBC(java database connectivity)・・・DB専用のAPI
 *  JDBC APIはJDKに標準搭載されているが、各々のDBに接続するために必要なJDBCドライバはダウンロードして適所に配置する
 *  ドライバはしっかりクラスパスを通した状態にしておく
 *  よく利用されるクラスは4つ( DriverManager, Connection, PreparedStatement, Results )
 *  
 * */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class Main{
	public static void main( String[] args ){
		// ソースコードの先頭付近で、ドライバクラスを読み込んで、ドライバを有効化する
		// JARをクラスパスに通しただけでは有効化にならない
		// Class.forName("JDBCのFQCD")で有効化
		// JDBCのFQCDは各DBで決まっている
		try {
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			e.printStackTrace();
			
		}
		
		// finally{}でconを使うためにブロック外で宣言
		// 本来はjava.sql.Connectionだからimportしておくと楽
		Connection con = null;
		
		try {
			// 第1引数はJDBC URL(DB接続先を指定する文字列)
			// 指定先にデータベースファイルがなければ作成されるらしいけど、デスクトップでは作成されなかった
			con = DriverManager.getConnection( "jdbc:mysql://localhost:8889/", "root", "root" );
			System.out.println( "接続成功！" );
			
		} catch ( SQLException e ) {
			// ここでのエラーは con = null の場合
			e.printStackTrace();
			System.out.println( "接続失敗" );
			
		} finally {
			/*
			 * ResultSetをcloseする時はNULL判定すべし
			 * 何らかの理由でDBに繋がらない場合、ConnectionはNULLの可能性があり、
			 * NULL判定を入れないとNullPointerExceptionで落ちる
			 * (Web系だと最悪画面にStackTraceが表示されてしまう)
			 */
			if( con != null ) {
				try {
					// Connectionクラスのclone()を呼ぶだけ
					// DBは接続できるプログラム数に制限があるため、接続を切らないと他のプログラムが接続できない
					// もしconがnullだった場合、NullPointerExceptionで落ちる(たぶん)
					con.close();
					
				} catch ( SQLException e ) {
					e.printStackTrace();
					System.out.println( "切断失敗" );
					
				}
			}
		}
	}
}
