package aaa;

/*
 * 【トランザクション】
 *  Javaプログラムからデータベースに送信する1つ以上のSQL要求を１つのグループとして扱う考え方
 *  ①宣言 ②全てが成功したら処理結果をコミット ③途中で失敗したらトランザクション前に戻す(ロールバック)
 *  
 * */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

class Main{
	public static void main( String[] args ){
		try {
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			e.printStackTrace();
			
		}
		
		Connection con = null;
		
		try {
			con = DriverManager.getConnection( "jdbc:mysql://localhost:8889/test_01", "root", "root" );
			System.out.println( "接続...OK" );
			// デフォルトではSQL文を１つ送る事に自動的にcommit()を確定するようになっているが、
			// これを手動に切り替える
			con.setAutoCommit( false );
			
			// SQL文作成(例では検索系)
			java.sql.PreparedStatement pstmt = con.prepareStatement( "SELECT * FROM member" );
			
			// 検索結果はResultSetオブジェクトに格納されて返ってくる
			// 更新系の場合はSQL文実行はexecuteUpdate()を使いint型で返ってくる
			ResultSet rs = pstmt.executeQuery();
			
			// 検索が複数件数の場合はwhile()を使う
			// 表の検索の仕方はイテレーターと同じ
			while( rs.next() ) {
				System.out.println( rs.getString( "last_name" ) );
			}
			
			// 単一行の場合はif()
			if( rs.next() ) {
				System.out.println( "発見！" );
			} else {
				System.out.println( "ng" );
			}
			
			// 一連の作業が終わったらcommit()をし、完了させる
			con.commit();
			
		} catch ( SQLException e ) {
			// ロールバックをするにもtry{}内でやるのか..??
			try {
				con.rollback();
				
			} catch ( Exception e2 ) {
				e.printStackTrace();
				System.out.println( "接続失敗" );
				
			}
			
		} finally {
			if( con != null ) {
				try {
					con.close();
					
				} catch ( SQLException e ) {
					e.printStackTrace();
					System.out.println( "切断失敗" );
					
				}
			}
		}
	}
}
