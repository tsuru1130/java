package aaa;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

// リフレクション・・・実行中に他クラスの型情報を取得、利用すること
// リフレクションAPIを利用すると、クラスやインターフェイスに関する情報をJVMに調べさせることができる
/*
 * 主な利用法
 * ① テストや解析のため、privateメンバを操作したい場合
 * ② メンバ名を用いた特殊な処理をつくり込みたい場合
 * ③ 利用するクラスを動的に追加・変更できるようにしたい場合
 * 	→クラスをnewして利用する必要があるが、どのクラスを利用するかは実行時に決めたいという状況で活躍
 *	→デザインパターンStrategyと組み合わせた用法で、拡張性を意識したフレームワークの開発でよく用いられる
 * 
 * */
class Main{
	
	public static void main( String[] args ) throws Exception{
		Class clazz = Sub.class;
		
		// 引数１つのコンストラクタを取得し、インスタンスを生成する
		Constructor< ? > cons = clazz.getConstructor( int.class );
		Sub rs = ( Sub ) cons.newInstance( 256 );
		
		// pubフィールドに関するFieldを取得して読み書き
		Field f = clazz.getField( "times" );
		f.set( rs, 2 ); // rsのtimesに代入
		System.out.println( f.get( rs ) ); // rsのtimesを取得
		
		// 引数２つのhello()を取得して呼び出す
		Method m = clazz.getMethod( "hello", String.class, int.class );
		m.invoke( rs, "reflection!", 128 );
		
		// クラスやメソッドへの修飾( publicやfinalの有無 )を調べる
		boolean pubc = Modifier.isPublic( clazz.getModifiers() );
		boolean finm = Modifier.isFinal( m.getModifiers() );
		
	}
}