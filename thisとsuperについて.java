package object;

class Main{
	
	public static void main( String[] args ){
		Sub2 s2 = new Sub2();
		System.out.println( s2.name + ":" + s2.age );
	}
}



/*
	
	super
		
*/
package object;

public class Sub2 extends Sub{
	Sub2(){
		super( "名無しの権兵衛", 26 );
	}
	
	Sub2( int age ){
		super( "名無しの権兵衛", 26 );
	}
	
	Sub2( String name ){
		super( name, 99 );
		
	}
	
	Sub2( String name, int age ){
		super.name = name;
		super.age = age;
		
	}
	
}

/*
	
	this
		
*/

package object;

public class Sub{
	String	name;
	int	age;
	
	Sub(){
		this( "名無しの権兵衛", 26 );
	}
	
	Sub( int age ){
		this( "名無しの権兵衛", 26 );
	}
	
	Sub( String name ){
		this( name, 99 );
		
	}
	
	Sub( String name, int age ){
		this.name = name;
		this.age = age;
		
	}
	
}

