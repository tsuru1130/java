package object;

class Super{
	int a;
	
	// コンストラクタ
	Super( int x ){
		a = x;
	}
	
	public int getValue(){
		return a;
	}
	
	// equals()をオーバーライド
	@Override
	// 下記equals()の引数に変数tを受け取る
	public boolean equals( Object o ){
		// 『 o(変数t)はSuperクラスである』かつ『( Super ) oのgetValueとthis.aの値は同じである』
		// 『 o(変数t)はSuperクラスである』がtrueであることから引数oをキャストできる
		// Objectクラス(引数o)はgetValueメソッドを持っていない為、( Super )oでキャストする
		// thisはequals()を呼び出した変数sのSuperインスタンス
		if( ( o instanceof Super ) && ( ( ( Super ) o ).getValue() == this.a ) ) {
			return true;
		} else {
			return false;
		}
		
		// equals()メソッドとhashCode()メソッドは、「2つのオブジェクトがequals()メソッドで等しいと判定された場合、両者のハッシュコードは等しくなければならない。」という規定がある
		// なのでequals()をオーバーライドする場合はhashCode()もオーバーライドしないといけない
		public int hashCode(){
			return (a * 17) ;
		}
	}
}

class Main{
	
	public static void main( String[] args ){
		// s,tそれぞれメモリを確保し、それぞれのaフィールドに3を入れる
		Super s = new Super( 3 );
		Super t = new Super( 3 );
		// sがequals()を呼ぶ(オーバーライド済み)
		if( s.equals( t ) ) {
			System.out.println( "sとtが指す値は一緒です。" );
		} else {
			System.out.println( "sとtが指す値は違います。" );
		}
		
		System.out.println(s) ;
		System.out.println(t) ;
	}
}

/*
	
	出力結果 : sとtが指す値は一緒です。	
	
*/
