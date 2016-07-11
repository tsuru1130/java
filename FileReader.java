package aaa;

import java.io.FileReader;
import java.io.IOException;

class Main{
	public static void main( String[] args ) throws IOException{
		FileReader fw = new FileReader( "/Users/tsuru/Desktop/test.txt" );
		
		// FileReaderは複数の文字を一気に"読み取る"メソッドが存在しない(ストリーム概念)
		// 基本的には1文字ずつ読み込む
		// read()の返り値はint型
		// read()・・・次の１文字を正しく読み込めたら、その文字情報を返す
		// 最後まで読み込みが終わった場合は-1を返す
		int i = fw.read(); // 1文字読む
		
		// これ以上読めるデータがない場合は-1
		while( i != -1 ) {
			char c = ( char ) i;
			System.out.print( c );
			i = fw.read();
		}
		fw.close();
	}
}