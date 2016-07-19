/*
	【SQLの共通ルール】
	① : 文の途中に開業を入れられる
	② : 行の先頭や行の途中に半角の空白を入れられる
	
	改行を適切に入れて見やすくする(しっかりと整形する)
	予約語を大文字、小文字どちらでも同じ意味になるが、それを区別するかはOSや設定によって異なる
	
	SQL文はプログラムからドライバを通してDBMSへネットワークと通して送られる
	DBMSでSQL文を解釈し、中にあるデータベースファイル(単なるファイル)の中身を書き換える
	
	【４大命令】
	・構造と修飾語(whereなど)をしっかり把握する
	・ほとんどのデータ操作(テーブルやカラムじゃないよ)はSELECT, UPDATE, DALETE, INSERTで実現される
	・DML(Data Manipulation Language)と呼ばれる
	・最低限のSQL文として完成しているものをSQL文という
	・from 000 や where 000 をfrom句やwhere句という
	
	【覚え方１】
	・検索系(SELECT)、更新系(INSERT, DELETE, UPDATE)を理解すると覚えやすい
	・更新系は実行結果が常に成功か失敗の２通りで、表が返されることはない
	・更新系はp53の「検索結果の加工(一番右)」に当たる処理ができない(表が返ってくるわけではないので)
	
	【覚え方２】
	・既存系(SELECT, UPDATE, DELETE)、新規系(INSERT)
	・INSERTは新規なので、WHEREが使えない
	
	【覚え方３】
	・4大命令は全て処理対象とするテーブル名を指定する必要がある
	・テーブル名より先に記述があるのはSELECT文のみ
	
	
*/


/*家計簿
+------------+-----------------+--------------------------+--------+--------+
| date_time  | items           | memos                    | input  | output |
+------------+-----------------+--------------------------+--------+--------+
| 2013-02-03 | 食費            | カフェラテを購入         |      0 |    380 |
| 2013-02-10 | 給料            | １月の給料               | 280000 |      0 |
| 2013-02-11 | 教養娯楽費      | 書籍を購入               |      0 |   2800 |
| 2013-02-14 | 交際費          | 同期会の会費             |      0 |   5000 |
| 2013-02-18 | 水道光熱費      | 1月の電気代              |      0 |   7500 |
| 2013-02-25 | 居住費          | 3月の家賃                |      0 |  90000 |
+------------+-----------------+--------------------------+--------+--------+

*/

/*
	【NULLの判定】
	・データが何も入っていない(NULLが格納されている)
	・未定義という意味で何もデータが入っていないことを示す
	・ = や　<> が使えない
	・列の値がNULLかどうかは IS NULL もしくは IS NOT NULLを使う
	・SQLの条件式は３値論理(true, false, unknown)を採用している
	・= や　<> は値と値を評価するもの
	・NULLのような値ではないものを比較すると、不明な結果としてunknownが出る
	
*/

SELECT *
	FROM kakeibo
	WHERE output IS NULL;


/*
	【WHERE句】///SQL文で一番使われる修飾語
	・処理対象行の絞込みに用いる→指定しないと全行が対象になる
	・SELECT, UPDATE, DELETE文で使用可能→INSERT文では使用できない
	・WHEREの後ろは条件式→条件式は結果が"trueかfalse"になる式のこと
		↑例えば「出金額＋10000」だと、出金額という列に格納されている値が10000未満の場合はtrueという意味
	・DBMSは１行目から順に条件をチェックするから真偽の２択となる
	・条件式の結果が文字列や日付、数値なのはダメ
	
*/



























