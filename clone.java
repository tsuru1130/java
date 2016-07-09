package object;

class Main{
	
	public static void main( String[] args ){
		Sub s1 = new Sub();
		s1.name = "tsuruchoff";
		Sub s2 = s1.clone();
		System.out.println( s1.name );
		System.out.println( s2.name );
		
	}
}

/*
	
	Sub	
	
*/

package object;

// clone()による複製をサポートしている事を外部し明示するために、Cloneableインターフェイスを実装
public class Sub implements Cloneable{
	String	name;
	int	hp;
	
	// 通常の流れ
	// ① newで新しいインスタンスを生成
	// ② 元のインスタンスの全フィールドを、新しいインスタンスにコピー
	
	// clone()での流れ
	// Cloneableを実装→clone()を定義(フィールドを==でコピー)
	// ↑注意点・・・あくまでも==でコピーしている為、参照渡しには注意(参照値の場合はさらにclone()をやる←深いコピー)
	
	// Java5以降では戻り値の型は自身のクラス名
	// 親クラスがclone()をサポートしている場合は、今回のようにnewをせずに、super.clone()でインスタンスを呼ぶ
	// アクセス修飾子をpublicなどにオーバーライドする(親要素と同じかそれより緩いのであればok)
	// Objectクラスで定義際れているclone()はprotectedな為、そのままでは使えない
	// Cloneableクラスはclone()をオーバーライドさせる為だけに存在するクラス( clone()は未定義 )
	// ↑のようにオーバーライドを目的としたクラス(インターフェイス)を『マーカーインターフェイス』という
	
	@Override
	public Sub clone(){
		Sub res = new Sub(); 
		res.name = this.name;
		res.hp = this.hp;
		return res;
	}
}



/*
	
	
	
	
*/
public class Example implements Cloneable{ //Clonableインターフェイスを実装
    private static Node u; //クラス型static変数(static変数は元々インスタンスの中身がひとつしかないので、浅いコピーで良い)
    private Node s; //クラス型変数
    private int p; //プリミティブ型変数


    /*何らかのメソッドやコンストラクタが入る*/
    @Override
    public Example clone() { //基本的にはpublic修飾子を付け、自分自身の型を返り値とする
        Example b=null;

        /*ObjectクラスのcloneメソッドはCloneNotSupportedExceptionを投げる可能性があるので、try-catch文で記述(呼び出し元に投げても良い)*/
        try {
            b=(Example)super.clone(); //親クラスのcloneメソッドを呼び出す(親クラスの型で返ってくるので、自分自身の型でのキャストを忘れないようにする)
            b.s=this.s.clone(); //親クラスのcloneメソッドで深いコピー(複製先のクラス型変数と複製元のクラス型変数で指しているインスタンスの中身が違うコピー)がなされていないクラス型変数をその変数のcloneメソッドで複製し、複製先のクラス型変数に代入
        }catch (Exception e){
            e.printStackTrace();
        }
        return b;
    }
