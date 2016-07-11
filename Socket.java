package aaa;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/*
 * 通信の接続をする場合は「127.0.0.1というIPアドレスを持つサーバーの80番ポートにつないでください」的な感じ
 * 【Socket / javaプログラムからサーバーへ接続】
 *	Socketクラスはサーバーへ接続 / ServerSocketクラスはサーバーで受信(つまりサーバー側)
 *   TCP/IP(プロトコル)を使って接続を行う場合は、java.netパッケージのSocketクラスを使う
 *   
 *   手順
 *   ① : IPアドレスまたはURLとポーと番号を指定してSocketインスタンス化(new)
 *   ② : Socketから入力出力(両方)ストリームを取得(getInputStream(),getOutputStream())
 *   ③ : 2つのストリームを読み書きする(read(),write())
 *   ④ : ソケットを閉じる(clone())
 *   
 * */

class Main{
	public static void main( String[] args ) throws Exception{
		// ① : IPアドレスまたはURLとポーと番号を指定してSocketインスタンス化(new)
		Socket sock = new Socket( "dokojava.jp", 80 );
		
		// Socketから入力出力(両方)ストリームを取得
		// １つの接続で２つのストリームを同時に行う
		// InputはPCへ Outputはサーバーへ
		// この接続が基本で、あとはメールを送ったりと使用法はたくさんある
		InputStream is = sock.getInputStream();
		OutputStream os = sock.getOutputStream();
		
		// HTTP1.0仕様では【GET ファイル名HTTP/1.0(改行) (改行２つ目)】を
		// webサーバーに送れば、そのWebページが取得できる
		// ２つのストリームを読み書きする
		// HTTP要求を送信
		os.write( "GET /index.html HTTP/1.0\r\n".getBytes() );
		os.write( "\r\n".getBytes() );
		os.flush();
		
		InputStreamReader isr = new InputStreamReader( is );
		int i = isr.read();
		while( i != -1 ) {
			System.out.print( ( char ) i );
			i = isr.read();
		}
		
		// ここのclose()で入出力２つのストリームは自動的に閉じる
		sock.close();
		
	}
}
