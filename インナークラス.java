package object3;

/*
 * インナークラスの特徴
 * ・他のクラスの内部で宣言される
 * ・外側クラスのメンバや変数に対して特別にアクセスが出来る
 * ・メンバクラスに対してstaticをつけるか否かで利用法が大きく異なる(別物として考える)
 * ・protectedやprivateなどのアクセス修飾子が利用可能
 * 
 * */
class Outer{
	int		outerField;
	static int	outerStaticField;
	
	static class Inner{
		int innerMethod(){
			outerStaticField = 10;
			return outerStaticField;
		}
	}
	
	void outerMethod(){
		Inner ic = new Inner();
	}
}

class Main{
	
	public static void main( String[] args ){
		// OuterクラスのInnerクラス(クラスメンバ)
		Outer.Inner ic = new Outer.Inner();
		int ic_2 = ic.innerMethod();
		System.out.println( ic_2 );
	}
}


/*
	
	こちらのソースコードの方が分かりやすい	
	
*/
package object3;

import object3.Sub.SubInner;
import object3.Sub.SubStaticInner;

/*
 * 『エンクロージング・インスタンス』
 * →内部クラスにとっての「外部クラスのインスタンス」。 
 * 内部クラスのインスタンスは、「外部クラスのインスタンスの中に含まれる」という特殊なインスタンスとなる。
 * そのため、内部クラスのインスタンスを作るためには、必ず外部クラスのインスタンスが必要となる。 
 * その「外部クラスのインスタンス」が「エンクロージングインスタンス」である。
 * 内部クラスのインスタンスを「enclosing」つまり「囲い込む」インスタンスである。
 * 
 * ・内部クラスは必ず外部クラスと繋がっており、外部クラスをインスタンス化しないと内部クラスをインスタンス化できない
 * ・↑は内部クラスが非staticの場合(statciの場合は普通にインスタンス化出来る)
 * 
 * 
 * */

class Main{
	
	public static void main( String[] args ){
		Sub s1 = new Sub(); // SubInnerクラスをインスタンス化する為、必ず先にSubクラスをインスタンス化する
		s1.name = "tsuruchoff";
		SubInner s2 = s1.new SubInner();
		s2.hp = 100000;
		System.out.println( s2.hp );
		
		// 内部クラスにstaticがあると下記のように直接インスタンス化が出来る
		// SubStaticInnerクラスは非static
		SubStaticInner sInner = new Sub.SubStaticInner();
		sInner.hp = 100;
		System.out.println( sInner.hp );
	}
}


/*
	
	Sub
		
*/
package object3;

public class Sub{
	
	String name;
	
	class SubInner{
		int hp;
	}
	
	static class SubStaticInner{
		int hp;
	}
}














