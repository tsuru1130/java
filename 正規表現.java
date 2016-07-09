package object;

class Main{
	// 同じクラスでもstaticを使わないといけなかったっけ？？
	// main()の中で関数は定義できなかったはず
	// [A-Z][A-Z0-9]{7}の部分は文字パターンという
	// . ピリオド なんでもいい
	// * アスタリスク 直前の0回以上の文字列
	// [] 範囲
	// {n}直前のn回以上の文字列
	// + 直前の1回以上
	// ハット(^ : 始め)とダラー($ : 終わり) matche()はなくても動作するが、
	// split()やreplaceAll()は^~~~$がないと動作しない
	// ¥d : いずれかの数字 [0-9]と同じ
	// ¥w : 英字・数字・アンダーバー[a-zA-Z_0-9]と同じ
	// ¥s : 空白文字(スペース、タブ文字、改行、文字)
	static boolean idValidName( String name ){
		return name.matches( "[A-Z0-9a-z]{3}" );
	}
	
	public static void main( String[] args ){
		String str = "a4a";
		if( idValidName( str ) ) {
			System.out.println( "ok" );
		} else {
			System.out.println( "ng" );
		}
	}
}
