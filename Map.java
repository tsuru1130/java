package object;

import java.util.HashMap;
import java.util.Map;

class Main{
	
	public static void main( String[] args ){
		// 値は重複してもいいが、キーの重複は上書きされる
		// Setと同じようにTreeMapやLinkedHashMapで順序を指定(※ Setの場合は語尾がSetになる)
		// String, Integerリスト要素の型
		Map< String, Integer > p = new HashMap<>();
		p.put( "京都府", 255 );
		p.put( "東京都", 1261 );
		p.put( "熊本県", 181 );
		p.put( "三重県", 281 );
		int tokyo = p.get( "東京都" );
		System.out.println( "東京都の人口は、" + tokyo );
		
		p.remove( "京都府" );
		System.out.println( p.get( "京都府" ) ); // 出力結果 null
		p.put( "熊本県", 182 );
		int kumamoto = p.get( "熊本県" );
		System.out.println( "熊本県の人口は、" + kumamoto );
		System.out.println( "閑話休題" );
		
		// p.keySet()はキーを配列として格納する
		// Set aaa = p.keySet(); → [東京都, 熊本県, 三重県]
		// ↑
		for( String key : p.keySet() ) {
			System.out.println( key + " : " + p.get( key ) );
		}
	}
}
