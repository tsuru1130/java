package aaa;

import java.io.FileWriter;
import java.io.IOException;

class Main{
	
	// ファイルに書き込みする方法
	// ・ランダムアクセス : 自由/遅い
	// ・シーケンシャルアクセス : 先頭から順番/早い/基本
	// JVMの外部ファイルは容量がどれだけあるかが不明なためシーケンシャルアクセス(少しずつ読み込む)が基本
	
	public static void main( String[] args ){
		// tryブロック外でnullで初期化しないとfinallyブロックでclose()を呼べない
		FileWriter fw = null;
		try {
			// FileWriterをインスタンス化する際にファイルを開く
			// ↓ true追記 false上書き
			fw = new FileWriter( "/Users/tsuru/Desktop/test.txt", false );
			fw.write( "XXXX" );
			// flush()は強制書き込み
			// ↑は必ず実行させる/ファイルに正しくデータが書き込みされない場合がある
			// write()は書き込みの予約(バッファで溜めてる)をするだけで、必ずしも実行されるとは限らない
			fw.flush();
			
		} catch ( IOException e ) {
			e.getMessage();
			
		} finally { //ファイルを閉じるためにfinally{}
			if( fw != null ) {
				try {
					fw.close();
					
				} catch ( IOException e2 ) {
					
				}
			}
		}
	}
}

/*
	
	Java7以降のエレガントな書き方
	
*/
package aaa;

import java.io.FileWriter;
import java.io.IOException;

class Main{
	public static void main( String[] args ){
		// Java7以降はtry(){}の()の中でファイルをインスタンス化すると自動的にclose()処理が行われる
		try( FileWriter fw = new FileWriter( "/Users/tsuru/Desktop/test.txt", true ) ) {
			fw.write( "aaaa" );
			fw.flush();
			
		} catch ( IOException e ) {
			e.getMessage();
		}
	}
}