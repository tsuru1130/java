package collection;

import java.util.ArrayList;
import java.util.Iterator;

public class Main{
	public static void main( String[] args ){
		String[] names = new String[3];
		names[0] = "たなか";
		names[1] = "すずき";
		names[2] = "さいとう";
		System.out.println( names[1] );
		
		// ArrayListを確保
		// <>はジェネリクスと呼ばれるJavaの文法
		// String[]の[]が<>になった。そして型をジェネリクスで囲う
		// 配列と違い、コレクションクラスは最初に箱をいくつ用意するかを決める必要がない = 自動的に追加される(可変)
		// 配列の方がメモリ効率がいい
		
		ArrayList< String > aaa = new ArrayList< String >();
		aaa.add( "つるた" );
		aaa.add( "まえぼり" );
		aaa.add( "まるやま" );
		System.out.println( aaa.get( 0 ) );
		
		// コレクションクラスはインスタンスはどんな型で格納可能。ただし基本データ型は不可
		// つまりただのint型は格納できない。integerインスタンス化して格納
		// ただし、Javaのオートボクシング機能で自動的にint型からinteger型へ変換
		// 配列は数を調べる場合はlenght()
		// コレクションクラスはsize()。戻り値が0であるかを調べる場合はisEmpty()
		// コレクションに『ある値』があるかを調べる場合はcontains()
		// 何番目の位置に含まれているかまで調べる場合はindexOf()
		// 削除の場合はclear() もしくはremove()
		
		ArrayList< Integer > points = new ArrayList< Integer >();
		points.add( 10 );
		points.add( 80 );
		points.add( 75 );
		for( int i : points ) {
			System.out.println( i );
		}
		System.out.println( "閑話休題" );
		for( int i = 0; i < points.size(); i++ ) {
			System.out.println( points.get( i ) );
		}
		System.out.println( points.get( 0 ) );
		
		// iterator(イテレーター) ...リストに含まれる１つの箱を「ここ！」と
		// 指している矢印のようなもの！
		// イテレーターを使うにはインスタンス化しないといけないがnewはしない
		// Iterator< リスト変数の型 > it = リスト変数の型.iterator();
		// iteratorのメソッドはhasNext()かnext()のみ
		// 初期状態ではイテレーターは-1番目を指す(0番目じゃない)
		ArrayList< String > firstName = new ArrayList< String >();
		firstName.add( "渚" );
		// firstName.set( 0, "楓" ); ←は0番目の渚を上書き
		firstName.add( "楓" );
		firstName.add( "カルマ" );
		Iterator< String > it = firstName.iterator();
		
		while( it.hasNext() ) { // 矢印を右に進められるのであればtrue
			String e = it.next(); // 矢印を右に進め、内容を取り出す
			System.out.println( e );
		}
		
	}
	
}
