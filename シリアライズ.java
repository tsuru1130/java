package aaa;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/*
 * 【Javaの直列化機構】
 * 直列化(並ばせる/Serialize/シリアライズ)
 * →インスタンスの内容(全フィールド(メソッドは?)←必要ない)を１つの命令で、バイト列に変換する
 *  逆に、バイト列からインスタンスに戻す事も可能
 * java.io.Serializableを実装しなければならない
 * 
 * transient staticがついたフィールドは直列化されない( transient : シリアライズの対象外にする修飾子 )
 * 
 * クラス型のフィールド(Sword型)も、直列化が可能であれば連鎖的に直列化する
 * ↑この場合SwordもSerializeを実装している必要がある
 * 
 * 直列化は落とし穴が色々とあるため注意深く使う事
 * →シリアルバージョンUIDを定義する( 値はなんでもいいがクラス設計が変わった時は適宜修正する事 )
 *  
 * */

class Main{
	public static void main( String[] args ) throws IOException, ClassNotFoundException{
		Sub2 s1 = new Sub2();
		s1.money = 99999999;
		s1.hp = 99999999;
		s1.name = "tsuruchoff";
		
		// ① 直列化と保存
		FileOutputStream fos = new FileOutputStream( "/Users/tsuru/Desktop/test.txt" );
		ObjectOutputStream oos = new ObjectOutputStream( fos );
		
		oos.writeObject( s1 ); // インスタンス→バイト列
		oos.flush();
		oos.close();
		
		// ② ファイルからインスタンスを復元
		FileInputStream fis = new FileInputStream( "/Users/tsuru/Desktop/test.txt" );
		ObjectInputStream ois = new ObjectInputStream( fis );
		Sub2 s2 = ( Sub2 ) ois.readObject(); // バイト列→インスタンス
		ois.close();
		System.out.println( s2.name );
		System.out.println( s1.name );
	}
}

/*
	
	Sub2	
	
*/

package aaa;

import java.io.Serializable;

public class Sub2 extends Sub implements Serializable{
	int money;
	
	private void run(){
		System.out.println( "走った！" );
	}
}

/*
	
	Sub(なんとなく親クラスに指定)
	
*/

package aaa;

public class Sub{
	String name;
	int hp;
	
}


