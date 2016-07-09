package object2;

class Main{
	
	public static void main( String[] args ){
		Sub< String > s = new Sub<>();
		s.put( "tsuruchoff" );
		System.out.println( s.get() );
	}
}


/*
	
	Sub	
	
*/
package object2;

public class Sub< E >{
	private E data;
	
	public void put( E d ){
		this.data = d;
	}
	
	public E get(){
		return this.data;
	}
}
