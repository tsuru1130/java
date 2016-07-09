// 参照(Sword)のコピーができないーーーーーー！！

package cleric;

public class Main{
	public static void main( String[] args ){
		Hero h1 = new Hero();
		h1.name = "tsuruchoff";
		Hero h2 = h1.clone(); // clone()実行
		h2.name = "puntakun";
		System.out.println( h1.name );
		System.out.println( h2.name );
		
		Sword s = new Sword();
		s.name = "マサムネ";
		s.damage = 99999;
		h1.s = s;
		h2 = h1.clone();
		h2.s.name = "アルテマウェポン";
		System.out.println( h1.s.name );
	}
}

/*
	
	Hero

*/
package cleric;

public class Hero extends Monster{
	Sword s;
	
	public void run(){
		System.out.println( "戦った！" );
	}
	
	@Override
	public Hero clone(){
		// super.clone()で親クラスのオブジェクトが返ってくるのでキャストして返す
		Hero h = ( Hero ) super.clone();
		return h;
	}
}

/*
	
	Monster

*/
package cleric;

class Monster implements KoMonster, Cloneable{
	String	name;
	int	hp;
	
	public void run(){
		System.out.println( "走って逃げた！" );
	}
	
	@Override
	public Monster clone(){
		Monster m = null; // メソッド内での定義だから明示的に初期化が必要
		/*
		 * Objectクラスのcloneメソッドは
		 * CloneNotSupportedExceptionを投げる可能性があるので、try-catch文で記述
		 * 呼び出し元に投げても良い
		 */
		try {
			m = ( Monster ) super.clone();
			m.name = this.name;
			m.hp = this.hp;
			
		} catch ( CloneNotSupportedException e ) {
			e.printStackTrace();
			
		}
		return m;
	}
}

/*
	
	Sword

*/

package cleric;

public class Sword implements Cloneable{
	String	name;
	int	damage;
	
	@Override
	public Sword clone() throws CloneNotSupportedException{
		Sword s = ( Sword ) super.clone();
		s.name = this.name;
		s.damage = this.damage;
		return s;
	}
	
}




