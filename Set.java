package object;

import java.util.LinkedHashSet;
import java.util.Set;

class Main{
	
	public static void main( String[] args ){
		// Setという曖昧なインターフェイスにTreeSetという具体的なクラスを格納
		// TreeSetで格納すると自然順序
		// LinkedHashSetは格納順
		// Set型でそのまま格納するとランダムに出てくる
		// Set< String > words = new TreeSet<>();
		Set< String > words = new LinkedHashSet<>();
		words.add( "dog" );
		words.add( "cat" );
		words.add( "apples" );
		words.add( "wolf" );
		words.add( "panda" );
		for( String s : words ) {
			System.out.println( s );
		}
	}
}
