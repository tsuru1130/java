package mojiretsu;

/*
 * 
 * 文字列操作 
 * 1:検索 返り値はboolean系かint系
 * 2:切り出す
 * 3:変換
 * 4:結合
 * 
 * 
 * */

class Main{
	public static void main( String[] args ){
		
		String s1 = "すっきりJava";
		String s2 = "Java";
		String s3 = "java";
		String s4 = "Java and JavaScript    ";
		
		/*
		 * 1:検索 返り値はboolean系かint系
		 */
		
		// 内容が等しいか調べる
		if( s2.equals( s3 ) ) {
			System.out.println( "同じ！" );
		} else {
			System.out.println( "違う！" );
		} // 出力結果 違う！
		
		// 大文字、小文字区別せずに比べる
		if( s2.equalsIgnoreCase( s3 ) ) {
			System.out.println( "同じ！" );
		} else {
			System.out.println( "違う！" );
		} // 出力結果 同じ！
		
		System.out.println( s1.length() );
		if( s1.isEmpty() ) { // .length() == 0 よりも直感的
			System.out.println( "空白です" );
		} else {
			System.out.println( "文字が入っています" );
		} // 出力結果 文字が入っています！
		
		// String s4 = "Java and JavaScript";
		if( s4.contains( "Java" ) ) { // 含まれているか否か boolean系
			System.out.println( "Javaが含まれています" );
		} // Javaが含まれています
		
		if( s4.startsWith( "Java" ) ) {// 含まれているか否か boolean系
			System.out.println( s4 + "はJavaで始まります" );
		}
		
		/*
		 * 2:切り出す系
		 */
		System.out.println( s4.indexOf( "and" ) ); // 0番目から数えるつからなかった場合は-1
		
		/*
		 * 3:変換系
		 */
		System.out.println( "トリムを使わず" + s4 + "終了" );
		System.out.println( "トリムを利用" + s4.trim() + "終了" );
		// 出力結果 trim()は全角は除去されない
		// トリムを使わずJava and JavaScript 終了
		// トリムを利用Java and JavaScript終了
		System.out.println( s1.replace( "すっきり", "しっかり" ) );
		// 出力結果 しっかりJava (元 : すっきりJava)
		System.out.println( s2.toLowerCase() );
		// 出力結果 java (元 : Java)
		
		/*
		 * 4:結合系 StringBuilder >>>>String>>>>> + 約4000倍違う
		 * StringBuilderクラスは内部に結合した文字列を蓄えるメモリ領域(バッファ)が存在する
		 * StringBuilderクラスを使う場合は①apeend() ②toString()の手順が一般的
		 * append()でバッファに文字列を追加して、toString()で出力
		 * たいていの文字列結合はStringBuilderで大丈夫だが、スレッドを利用する場合は不具合が発生することがある
		 * スレッド・・・複数の処理を同時に行うためのJavaのしくみ
		 * ↑の場合はStringBufferクラスを使う。使い方はStringBuilderと全く同じ
		 * String型は不変のため、一度生成した変数は変更ができない。
		 * つまり、+演算子は内部では結合するたびにString型がnewされている
		 */
		
		StringBuilder sb = new StringBuilder();
		for( int i = 0; i < 10; i++ ) {
			sb.append( "Java" ); // バッファにJavaを追加
			// append()はStringBuilderクラスのメンバ(戻り値はStringBuilder)
			// 戻り値が自分自身なのでメソッドチェーンが可能
		}
		System.out.println( sb );
		String s = sb.toString(); // 完成し結合済み文字列を取り出す
		System.out.println( s );
		// sbもsも出力結果はJavaJavaJavaJavaJavaJavaJavaJavaJavaJava
		
	}
}
