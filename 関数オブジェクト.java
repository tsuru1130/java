package object3;

import java.util.function.IntBinaryOperator;

class Main{
	public static int sub( int a, int b ){
		return a - b;
	}
	
	public static void main( String[] args ){
		// subメソッドの処理ロジックを、変数funcに代入する
		// 実際に代入されているのは、sub()メソッドへの参照(参照型と同じ)
		// sub()メソッドが直接入っているわけでは無い
		// JVMのメモリ空間のどこかにsub()メソッドの実態があり、そのメモリ番地を指す情報が入っているイメージ
		// IntBinaryOperatorはSAMインターフェイス
		// single-abstract-method-Interface
		// インターフェイス名はなんでもok
		// ただし、格納したい関数オブジェクトと①同じ引数や②戻り値を持った③メソッドを④１つだけ(only)含んだ⑤インターフェイス
		IntBinaryOperator func = Main::sub;
		// 変数funcに格納されている処理ロジックを、引数5と3で実行する
		int a = func.applyAsInt( 5, 3 );
		System.out.println( a );
	}
}
