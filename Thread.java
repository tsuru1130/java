/*
 * 【スレッド(並列処理)】
 * JVM君を増やす(プログラムを同時に処理できる)
 * もしThreadクラスを使いたくない場合はRunnableインターフェイスを使う
 * Runnableインターフェイスはrun()をオーバーライドし、Thread()のコンストラクタを呼ぶ際に引数にあてる
 * 実務ではConcurrency Utilities(java.util.concurrentパッケージを使う)
 * ↑これなら簡単かつ安全にスレッド制御を行える
 * スレッド系のAPIは他のAPIと異なる存在(他は１つのプログラムの外を出ることはない)
 * 高度なことができるが、可能な限り使わない方法を考える
 * 熟練した開発者ほどスレッドを使ったプログラミングをできるだけ回避しようとする
 * →OS依存性があり、時と場合によっては再現性がでない
 * →開発工程全体に多くの影響を与える
 * 
 * 
 * 別スレッドの終了を待つ場合はjoin()メソッドを使う(原則各スレッドは他を待たず個別に終了し、すべてのスレッドが完了した時点でJVMは終了する)
 * sybchronized(this){}を使うと優先順序を決められる
 * wait()を使うと後続のスレッドを優先させることも可能
 * 永遠に待ち続けるデッドロックに注意する
 * 
 * 重要ポイント
 * ① : OSによって動作に違いが出る(OSに備えるスレッド機能を利用する為)
 * ② : 例外はmainメソッドに伝播しない
 * ③ : 同時に１つのクラス、メソッド、変数を利用するとデータが壊れる(☆スレッドの競合という)
 * 
 * */
import java.util.Scanner;

class PrintingThread extends Thread{
	
	// Threadクラスを継承し、run()メソッドをオーバーライドする
	// run()には処理したい内容を書き込む
	@Override
	public void run(){
		for( int i = 0; i < 10; i++ ) {
			System.out.print( i );
		}
	}
}

class Main{
	public static void main( String[] args ){
		System.out.println( "何か入力してください" );
		
		// 別スレッドの実行を開始したい場所でThreadを継承したクラスをインスタンス化
		Thread t = new PrintingThread();
		
		// start()で別スレッドを開始
		// stop(),suspend(),destroy()はJVM内部で異常な状態になる可能性がある為、利用しない
		t.start();
		new Scanner( System.in ).nextLine();
	}
}


/*
	
	Runnable()の場合	
	
*/


import java.util.Scanner;

class PrintingThread implements Runnable{
	@Override
	public void run(){
		for( int i = 0; i < 10; i++ ) {
			System.out.print( i );
		}
	}
}

class Main{
	public static void main( String[] args ){
		System.out.println( "何か入力してください" );
		
		// Thread()コンストラクタの引数にRunnable()を実装したクラスのコンストラクタを入れる
		Thread t = new Thread( new PrintingThread() );
		
		t.start();
		new Scanner( System.in ).nextLine();
	}
}


