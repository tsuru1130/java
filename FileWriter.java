package aaa;

import java.io.FileWriter;
import java.io.IOException;

class Main{
	
	// ファイルに書き込みする方法
	// ・ランダムアクセス : 自由/遅い
	// ・シーケンシャルアクセス : 先頭から順番/早い/基本
	
	public static void main( String[] args ) throws IOException{
		// FileWriterをインスタンス化する際にファイルを開く
		// ↓ true追記 false上書き
		FileWriter fw = new FileWriter( "/Users/tsuru/Desktop/test.txt", false );
		fw.write( "XXXX" );
		// flush()は強制書き込み
		// ↑は必ず実行させる/ファイルに正しくデータが書き込みされない場合がある
		// write()は書き込みの予約(バッファで溜めてる)をするだけで、必ずしも実行されるとは限らない
		fw.flush();
		fw.close();
	}
}