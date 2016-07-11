package aaa;

import java.io.FileReader;
import java.io.IOException;

class Main{
	public static void main( String[] args ) throws IOException{
		FileReader fw = new FileReader( "/Users/tsuru/Desktop/test.txt" );
		
		// FileReaderは複数の文字を一気に"読み取る"メソッドが存在しない(ストリーム概念)
		// FileReaderは本当はFileNotFoundExceptionという例外が発生する可能性があるため、その対策も必要
		
		// 基本的には1文字ずつ読み込む
		// read()の返り値はint型(文字コードとして返す/キャストで文字に変換できる)
		// read()・・・次の１文字を正しく読み込めたら、その文字情報を返す
		// "read"メソッドは"IOException"と言う例外を処理しなければいけない
		// 最後まで読み込みが終わった場合は-1を返す
		int i = fw.read(); // 1文字読む ①
		
		// これ以上読めるデータがない場合は-1
		while( i != -1 ) {
			char c = ( char ) i;
			System.out.print( c );
			
			// read()は一文字読み込んだら、自動的に次の文字へ移動する
			// 従って↓で次の１文字をキャッチしてあげないとwhile()で常に①で代入された一文字が判定され、無限ループになる
			i = fw.read();
		}
		fw.close();
	}
}

/*
	
	別の書き方(tryとか書いただけ)
	
*/
package aaa;

import java.io.FileNotFoundException;
import java.io.FileReader;

class Main{
	public static void main( String[] args ){
		try {
			FileReader fr = new FileReader( "/Users/tsuru/Desktop/test.txt" );
			try {
				int i = fr.read();
				while( i != -1 ) {
					char c = ( char ) i;
					System.out.print( c );
					i = fr.read();
				}
				
			} catch ( Exception e ) {
				e.getMessage();
			}
			
		} catch ( FileNotFoundException e ) {
			e.getMessage();
		}
	}
}