package cleric;

public class Main{
	public static void main( String[] args ){
		Hero h1 = new Hero();
		Sword s1 = new Sword();
		h1.setName( "tsuruchoff" );
		h1.setHp( 999999 );
		s1.setName( "政宗" );
		s1.setDamage( 9999 );
		h1.s = s1;
		Hero h2 = h1.clone();
		h2.setName( "puntakun" );
		h2.s.setName( "アルテマウエポン" );
		System.out.println( h1.getName() + "の武器は" + h1.s.getName() );
		System.out.println( h2.getName() + "の武器は" + h2.s.getName() );
		
	}
}


/*
	
	Hero	
	
*/
package cleric;

public class Hero extends Monster{
	//Swordクラスでprivateフィールドを設定しているから、ここではprivateを設定しなくてもいいと思う
	Sword		s;
	private int	money;
	
	public void setMoney( int money ){
		this.money = money;
	}
	
	public int getMoney(){
		return money;
	}
	
	@Override
	public void run(){
		System.out.println( this.getName() + "は戦った！" );
	}
	
	@Override
	public Hero clone(){
		// 親クラスでゲッターセッターしてる場合はsetHp( getHp() )で行う
		Hero h = ( Hero ) super.clone();
		h.setHp( this.getHp() );
		h.setName( this.getName() );
		h.s = s.clone();
		return h;
	}
	
}

/*
	
	Monster	
	
*/
package cleric;

class Monster implements KoMonster, Cloneable{
	// protectedは子クラスのみ
	protected String	name;
	protected int	  hp;
	
	public void setName( String name ){
		this.name = name;
	}
	
	public void setHp( int hp ){
		this.hp = hp;
	}
	
	public String getName(){
		return name;
	}
	
	public int getHp(){
		return hp;
	}
	
	@Override
	public void run(){
		System.out.println( this.name + "は走って逃げた!" );
		
	}
	
	@Override
	public Monster clone(){
		Monster m = null;
		try {
			m = ( Monster ) super.clone();
			m.hp = this.hp;
			m.name = this.name;
			
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
	private String	name;
	private int	damage;
	
	public void setName( String name ){
		this.name = name;
	}
	
	public void setDamage( int damage ){
		this.damage = damage;
	}
	
	public String getName(){
		return name;
	}
	
	public int getDamage(){
		return damage;
	}
	
	@Override
	public Sword clone(){
		Sword s = null;
		try {
			s = ( Sword ) super.clone();
			s.damage = this.damage;
			s.name = this.name;
			
		} catch ( CloneNotSupportedException e ) {
			e.printStackTrace();
		}
		return s;
	}
}
