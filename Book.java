package object;

import java.util.Date;

// < E >の使い方はジェネリクスという
// インスタンス化する際に型を決めクラスを作る
public class Book implements Comparable< Book >, Cloneable{
	private String	title;
	private Date	publishDate;
	private String	comment;
	
	public String getTitle(){
		return title;
	}
	
	private Date getDate(){
		return publishDate;
	}
	
	public String getComment(){
		return comment;
	}
	
	public void setName( String title ){
		this.title = title;
	}
	
	private void setDate( Date publishDate ){
		this.publishDate = publishDate;
	}
	
	public void setComment( String comment ){
		this.comment = comment;
	}
	
	// hashcode()のオーバーライド
	// ハッシュ系の比較をする場合は、最初にハッシュコードの比較を行う
	// ここでハッシュコードをクラスようにオーバーライドしないと挙動がおかしくなる
	@Override
	public int hashCode(){
		int r = 1;
		r = 31 * r + publishDate.hashCode();
		r = 31 + r + title.hashCode();
		return r;
	}
	
	// equals()のオーバーライド(今回はタイトルと出版日が同じであれば投下と判定される仕様)
	@Override
	public boolean equals( Object o ){
		if( this == o ) {
			return true;
		}
		if( o == null ) {
			return false;
		}
		if( !( o instanceof Book ) ) {
			return false;
		}
		Book b = ( Book ) o;
		if( !publishDate.equals( b.publishDate ) ) {
			return false;
		}
		if( !title.equals( b.title ) ) {
			return false;
		}
		return true;
	}
	
	@Override
	public int compareTo( Book o ){
		return this.publishDate.compareTo( o.publishDate );
	}
	
	// clone()のオーバーライド
	// (クラス名宣言のところでimplements Cloneableが必要)
	public Book clone(){
		Book b = new Book();
		b.title = this.title;
		b.comment = this.comment;
		b.publishDate = ( Date ) this.publishDate.clone();// 参照値もコピーする場合はclone()を使う
		return b;
	}
	
}
