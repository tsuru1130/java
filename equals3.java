/*
	
	Main
		
*/

class Main{
	public static void main( String[] args ){
		Sub s = new Sub();
		Sub2 s2 = new Sub2();
		s.name = "tsuruchoff";
		s2.name = "tsuruchoff";
		s.hp = 100;
		s2.hp = 100;
		
		if( s.name.equals( s2.name ) ) {
			System.out.println( "同じ" );
		} else {
			System.out.println( "違う" );
		}
		
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