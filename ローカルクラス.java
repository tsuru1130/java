package object3;

class Main{
	public static void main( String[] args ){
		Sub s = new Sub();
		s.outerMethod();
	}
}


/*
		
	Sub
	
*/
package object3;

public class Sub{
	int outerMember;
	
	void outerMethod(){
		
		// ローカルクラスはfinalとabstract以外の修飾子が使えない
		// 自分を取り囲むメソッド内のローカル変数についてはfinalがついたもののみアクセスが可能
		// メソッド内で定義したクラスは、メソッド終了までに利用する(ローカル変数と同じ性質)
		// あるメソッドの中で、複数回のみ使うクラスを一時的に作りたい場合に有効(使用頻度は多くない)
		class Inner{
			public void innerMethod(){
				System.out.println( "innerMethodです" );
			}
		}
		Inner ic = new Inner();
		ic.innerMethod();
	}
}
