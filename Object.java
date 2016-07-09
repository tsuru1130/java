package object;

class Main{
	
	public static void main( String[] args ){
		int a = 12;
		Integer a2 = new Integer( a );
		// calc( a );
		int x = calc( a2 );
		System.out.println( x );
	}
	
	// Object型が引数の場合、
	// (原則)基本データ型は祖先にObject型を持たないので(is-not-aの関係)、引数に渡す前にクラス型(ラッパークラス)に変換する必要がある
	// ただし、実際はおそらくオートボクシング機能が働いて"受け取る"ことは可能
	// returnで返す際にObject型→？型に変換(キャスト)しないとエラーが出る
	public static Integer calc( Object a ){
		return ( Integer ) a;
	}
}
