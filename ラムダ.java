package object2;

class Main{
	public static void main( String[] args ){
		// Java8~
		// ラムダ式(lambda expression)
		// 関数の定義と、その実体の即時生成
		// funcに代入しているのはcall()をどのようにP(process)O(output)するかの処理ロジック
		// funcは関数が格納されているメモリ空間を示す変数
		// 代入する処理ロジックは引数の型と数を合わせれば、処理はどのように書いてもOK
		// 下の例では(int a, int b)はcall()に合わせないといけないが、処理ブロック{~}の中身は自由に決めてOK!
		
		Sub func = ( int a, int b ) -> {
			return a - b;
		};
		int a = func.call( 5, 3 );
		System.out.println( a );
	}
}


/*

	Sub
	
*/
package object2;

public interface Sub{
	int call( int x, int y );
}


/*
	
	デフォルト
	
*/

package object2;

import java.util.function.IntBinaryOperator;

class Main{
	public static void main( String[] args ){
		
		IntBinaryOperator func = ( int a, int b ) -> {
			return a - b;
		};
		int a = func.applyAsInt( 5, 3 );
		System.out.println( a );
	}
}


/*

	応用

*/
package object2;

import java.util.function.IntToDoubleFunction;

class Main{
	public static void main( String[] args ){
		// 省略系ルール
		// ① 引数は型を省略できる
		// ② 引数が１つであれば(int x)を x と()なしで書ける
		// ③ 単一のreturn文の場合、波カッコとreturnを省略できる
		IntToDoubleFunction func = ( int x ) -> {
			return x * x * 3.14;
			// returnで帰ってきているのは計算された内容ではなく、ラムダオブジェクト(?)
		};
		System.out.println( func.applyAsDouble( 13 ) );
		
		// ↓超省略形
		IntToDoubleFunction func_2 = x -> x * x * 3.14;
		System.out.println( func_2.applyAsDouble( 10 ) );
		
		// ラムダ式は式外(ブロック外)で宣言された変数を参照できる(他のメソッドもできなかったっけ、、？)
		// ただしブロック内では変数の中身を書き換える事ができないため、実質fanalと同じ
		
	}
}
