package aaa;

// システムプロパティ
// hashMap形式でJVMに関する情報を格納している
// 情報を"取得"する場合はSystem.getProperty( "~" );を使う
// 適切に使用する事によって、OSに依存しないシステムを作ることができる(特に改行コードなど)
// 情報を"設定"する場合はSystem.setProperty( "~" );
// どのクラスからも直接読み書きでき格納領域だが、不具合のものになるため濫用は禁物
// OSの環境変数を取得する場合はSystem.getenv()を使う

class Main{
	
	public static void main( String[] args ){
		// 定数にする場合は大文字
		final String BR = System.getProperty( "line.separator" );
		System.out.println( "本日は" + BR + "晴天なり" );
		
		// long型 単位はバイト
		// "/ 1024 / 1024"をすることでMBに直せる
		// totalMemory()はJVM起動時にOSから割り当てされたメモリ容量
		// totalMemory()が不足しそうになった場合、自動的にOSからメモリが追加される
		// 従ってtotalMemory()の返す値は頻繁に変わる
		// maxMemory()は追加割り当ての限界
		long m = Runtime.getRuntime().maxMemory() / 1024 / 1024;
		long t = Runtime.getRuntime().totalMemory() / 1024 / 1024;
		long f = Runtime.getRuntime().freeMemory() / 1024 / 1024;
		
		System.out.println( "メモリ総容量 : " + m + "MB" );
		System.out.println( "現在の使用容量 :" + t + "MB" );
		System.out.println( "残りのメモリ容量 : " + f + "MB" );
		
	}
}