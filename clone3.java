package cleric;

public class Main{
	public static void main( String[] args ){
		Hero h1 = new Hero();
		Hero h2 = new Hero();
		Sword s1 = new Sword();
		
		h1.name = "tsuruchoff";
		h1.hp = 99999;
		h1.money = 9999999;
		s1.damage = 999;
		s1.name = "アルテマウエポン";
		h1.s = s1;
		
		h2 = h1.clone();
		h2.name = "puntakun";
		h2.s.name = "政宗";
		System.out.println( h1.s.name );
		System.out.println( h2.s.name );
		
	}
}

/*
	
	Hero	
	
*/
package cleric;

public class Hero extends Monster{
	Sword		s;
	static int	money;
	
	public void run(){
		System.out.println( "戦った！" );
	};
	
	@Override
	public Hero clone(){
		Hero h = ( Hero ) super.clone();
		h.money = this.money;
		h.s = s.clone();
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
		// 親クラスがclone()をサポートしている場合はsuper.clone()が必要
		// super.clone()の場合は例外処理が必要
		// new Monster()で新しくnewする場合はtry{}構文は必要ない
		// 自分自身のクローンを返す
		Monster m = null;
		try {
			m = ( Monster ) super.clone();
			m.hp = this.hp;
			m.name = this.name;
			
		} catch ( Exception e ) {
			// TODO: handle exception
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
	public Sword clone(){
		Sword s = null;
		try {
			s = ( Sword ) super.clone();
			s.damage = this.damage;
			s.name = this.name;
			
		} catch ( Exception e ) {
			e.getMessage();
		}
		return s;
	}
}
