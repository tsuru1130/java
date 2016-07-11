/*
	ストリーム(１文字ずつ読み込む)概念があるため、フィルターが理解しやすい
	フィルターは"データを暗号化してファイルに書き込む"という事もできる
	Java標準APIにもフィルタは多くあるが、オリジナルのフィルタも開発可能
	フィルターは必ず ①Filter~クラスを継承 ②単独で存在できず、他に接続する形で生成する
	つまりnewができない
	すでに存在するストリームを接続先として、コンストラクタで指定して生成する
	フィルターを複数連結する事もできる
*/

// ①まずは通常のファイル出力ストリームfosを生成
FileOutputStream fos = new FileOutputStream("data.txt");

// ②このストリームを下流に持つ暗号化ストリームcosを生成し接続(コンストラクタで指定)
CipherOutputStream cos = new CipherOutputStream(fos, algo);

// ③ cosに書き込めば、暗号化された上でファイルに流れていく
cos.wrire(65);

// ④さらに別のフィルターを追加(文字バイトを変換するストリームoswを接続)
OutputStreamWriter osw = new OutputStreamWriter(cos);

// ⑤ owsに文字を書き込めば、バイト変換&暗号化されたファイルに流れていく
osw.write("AB");

// ⑥ ows.close()をすれば連鎖的にcosもfosもclose()される
osw.close();

/*
	使用頻度の高いのはバッファリングフィルタ
	→上流から流れてきたデータを溜め込み、まとまった量になったところで下流に流す役割	
	
	文字情報用 : BufferedReader, BufferedWriter
	バイト情報用 : BufferedInputStream, BufferedOutputStream
	
	バッファリングフィルタを使う(データをまとめて処理する)メリット
	① 処理が早い
	② まとまった単位でデータが読める( BufferedReaderを挟むと、１行分を一気に読み込むreadLine()が使えるようになる )
	
*/






