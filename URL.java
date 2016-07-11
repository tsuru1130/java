package aaa;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/*
 * 【ネットワーク】
 * 基本クラスは java.netパッケージにある
 * 高水準API : URL ・・・簡単に使えるが細かい事はできない(java.net.URLクラス)
 * 低水準API : InetAddress, Proxy, Socket・・・細かい事はできるが複雑で手間がかかる(IPアドレス、暗号化通信...etc)
 * 
 * */

class Main{
	public static void main( String[] args ) throws Exception{
		// ① URLクラスをインスタンス化する
		URL url = new URL( "http://dokojava.jp" );
		// ② openStream()を呼び出して、データを取り出すストリームを取得する
		// 基本はファイル内に記述された文字列を１文字ずつ取り出すのと同じ(取得先がWebページ上のHTMLなだけ)
		// 取得するものが文字だけの場合は InputStreamReaderを組み合わせて使用されることがよくある
		InputStream is = url.openStream();
		InputStreamReader isr = new InputStreamReader( is );
		int i = isr.read();
		
		while( i != -1 ) {
			System.out.print( ( char ) i );
			i = isr.read();
		}
	}
}