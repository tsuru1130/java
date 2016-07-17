/*
	
	Main
		
*/

/*
 * /**
 * Objectクラスのequalsメソッド
 * public boolean equals(Object obj) {
 * 	// 自分と同じインスタンスだったらtrue
 * 	// 自分と同じアドレスかしか見ていない
 *   	return (this == obj);
 * }
 */

class Main{
	public static void main( String[] args ){
		Sub s1 = new Sub();
		Sub2 s2 = new Sub2();
		Sub s3 = new Sub();
		Sub s4 = s1;
		s1.name = "tsuru";
		s2.name = "tsuru";
		s1.hp = 999;
		s2.hp = 1000;
		
		// 【文字列型の比較】
		// 文字列(参照型)比較の場合はequals()はStringクラスでfinalでオーバーライドされているため、
		// 確か==(等値:同じアドレス)で比較でも良かった気がする(本当はequals()でやる)
		// ↑文字列の場合は文字列を定義(暗黙にnewらしい)したとしても、同じものがあれば使い回される
		if( s1.name == s2.name ) {
			System.out.println( "同じ" );
		} else
			System.out.println( "違う" );
		// 出力結果 : 同じ
		
		if( s1.name.equals( s2.name ) ) {
			System.out.println( "同じ" );
		} else
			System.out.println( "違う" );
		// 出力結果 : 同じ
		
		// 【基本データ型の比較】
		// 基本データ型はObjectクラスを継承していないため、equals()が使えない
		if( s1.hp == s2.hp ) {
			System.out.println( "同じ" );
		} else
			System.out.println( "違う" );
		// 出力結果 : 違う
		
		// 【オブジェクトの比較(別々の型)】
		// s1 == s2はできない(データ型が違うと==すらできないらしい)
		// というよりs1とs2はデータ型が違う
		// 別々の型でも"何をもって同じとみなすか"をequals()で定義すれば同じに出来ると思う
		// 途中でキャストするからis-aの関係じゃないといけないのか？(その部分を端折ればいい気がする)
		if( s1.equals( s2 ) ) {
			System.out.println( "同じ" );
		} else
			System.out.println( "違う" );
		// 出力結果 : 違う
		
		// 【オブジェクトの比較(同じ型、ただし別々にnew)】
		// 別々にnewしたから同じメモリ空間を示すとは思えない
		if( s1 == s3 ) {
			System.out.println( "同じ" );
		} else
			System.out.println( "違う" );
		// 出力結果 : 違う
		
		// Stringクラスやラッパークラスを比較しているわけではないから、equals()を定義しないと内容が同じでも、別物として扱われる
		if( s1.equals( s3 ) ) {
			System.out.println( "同じ" );
		} else
			System.out.println( "違う" );
		// 出力結果 : 違う
		
		// 【オブジェクトの比較(同じ型、参照渡しをした)】
		// Sub s4 = s1;なので同じアドレスを示す
		if( s1 == s4 ) {
			System.out.println( "同じ" );
		} else
			System.out.println( "違う" );
		// 出力結果 : 同じ
		
		if( s1.equals( s4 ) ) {
			System.out.println( "同じ" );
		} else
			System.out.println( "違う" );
		// 出力結果 : 同じ
		
	}
}


/*
	
	Sub
		
*/

public class Sub{
	String	name;
	int	hp;
	
	@Override
	public boolean equals( Object obj ){
		// 自分自身が引数できた場合
		if( obj == this )
			return true;
		
		// 中身がnullの場合はfalse
		if( obj == null )
			return false;
		
		// 型が異なる場合はfalse
		if( !( obj instanceof Sub ) )
			return false;
		
		// Object型として引数を受け取ったので、Sub型にキャスト
		Sub s = ( Sub ) obj;
		
		// 2つのインスタンスの特定のフィールドを比較して、等価(equals())であることを判定
		if( !this.name.equals( s ) )
			return false;
		
		return true;
	}
}