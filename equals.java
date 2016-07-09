package calc;

class Hero{
	public String name;
	
	/*
	 * ①自分自身が引数として渡されてきた場合、無条件でtrueを返す ②nullが引数として渡されてきた場合、無条件でfalseを返す
	 * ③比較し型が異なるならば、falseを返す(同じなら④に備え、比較できるように適切にキャストする)
	 * ④２つンスタンスが持つしかるべきフィールド同士を比較して等価か判定し、trueかfalseを返す 等価 equals
	 * 同じアドレスでなくてもOK 等値 == 同じアドレス
	 * equals()は２つの変数に入っているインスタンスを比較して等価であることを判定するメソッド
	 */
	public boolean equals( Object obj ){
		// ①
		if( obj == this )
			return true;
		// ②
		if( obj == null )
			return false;
		// ③
		if( !( obj instanceof Hero ) )
			return false;
		Hero h = ( Hero ) obj;
		// ④
		if( !this.name.equals( h ) ) {
			return false;
		}
		return true;
	}
}

class Calc{
	public static void main( String[] args ){
		
	}
	
}