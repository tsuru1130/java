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
	・ほとんどのデータ操作(テーブルやカラムじゃないよ)はSELECT, UPDATE, DELETE, INSERTで実現される
	・DML(Data Manipulation Language)と呼ばれる
	・最低限のSQL文として完成しているものをSQL文という
	・from 000 や where 000 をfrom句やwhere句という
	・LIKEは"文字列"に対して使うものwhere句の後に使う(select文にしかつかえない)
	・↑LIKEはあいまい"検索=select"とも呼ばれる
	・select文でもfromを必要としないものもある
		→計算式や関数は、リテラルなどの具体的な値を材料にすれば、どのテーブルかを指定しなくてもselect文として成り立つ
		→ただしOracleDBとDB2は必ずfromが必要
	
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

/*
	
	【式の種類】
	・「出金額 > 2000」のような真偽が返ってくる式を条件式
	・↑以外の値が返ってくるものを計算式(便宜的に)という
	・select文の選択列中でよく使われる
	
	
*/

mysql> select 出金額, --列の内容がそのまま出てくる
    -> 出金額 + 100, --計算式の評価結果が出力
    -> 'SQL' --固定値
    -> from 家計簿;
+-----------+-----------------+-----+
| 出金額    | 出金額 + 100    | SQL |
+-----------+-----------------+-----+
|         0 |             100 | SQL |
|      4200 |            4300 | SQL |
|      5000 |            5100 | SQL |
|     80000 |           80100 | SQL |
|         0 |             100 | SQL |
|      2800 |            2900 | SQL |
|      5000 |            5100 | SQL |
|     80000 |           80100 | SQL |
+-----------+-----------------+-----+


mysql> select 出金額,  出金額 + 100 as 百円増しの出金額,  'SQL' from 家計簿;
+-----------+--------------------------+-----+
| 出金額    | 百円増しの出金額         | SQL |
+-----------+--------------------------+-----+
|         0 |                      100 | SQL |
|      4200 |                     4300 | SQL |
|      5000 |                     5100 | SQL |
|     80000 |                    80100 | SQL |
|         0 |                      100 | SQL |
|      2800 |                     2900 | SQL |
|      5000 |                     5100 | SQL |
|     80000 |                    80100 | SQL |
+-----------+--------------------------+-----+



/*
	
	【CASE演算子】
	・列の値や条件式を評価し、その結果に応じて好きな値に変換することができる
	
	
*/

/*費目の値に応じて変換する*/
-- case~end文は１つの選択列(下の例では、費目、出金額と同じ)

SELECT 費目, 出金額,
	CASE 費目 WHEN '居住費' THEN '固定費' --カンマを付けないように注意！
		    WHEN '水道光熱費' THEN '固定費'
		    ELSE '変動費'
	END AS 出費の分類
	FROM 家計簿 WHERE 出金額 > 0;
+-----------------+-----------+-----------------+
| 費目            | 出金額    | 出費の分類      |
+-----------------+-----------+-----------------+
| 水道光熱費      |      4200 | 固定費          |
| 食費            |      5000 | 変動費          |
| 居住費          |     80000 | 固定費          |
| 教養娯楽費      |      2800 | 変動費          |
| 食費            |      5000 | 変動費          |
| 居住費          |     80000 | 固定費          |
+-----------------+-----------+-----------------+	


	
/*条件に応じた値に変換する*/
SELECT 費目, 入金額,
	CASE WHEN 入金額 < 5000 THEN 'お小遣い'
		WHEN 入金額 < 100000 THEN '一時収入' --条件に一致しない場合はELSEとして次の条件に進む
		WHEN 入金額 < 300000 THEN '給料出たー！'
		ELSE '想定外の収入です！'
	END AS 収入の分類
	FROM 家計簿
	WHERE 入金額 > 0;
+--------+-----------+--------------------+
| 費目   | 入金額    | 収入の分類         |
+--------+-----------+--------------------+
| 給料   |    280000 | 給料出たー！       |
| 給料   |    280000 | 給料出たー！       |
+--------+-----------+--------------------+




/*
	
	【関数】
	・あらかじめDBには多くの関数が定義されている
	・使える関数はDBMSによってかなり異なる
	・使用する前に『名前、引数、戻り値』の３つを確認する	
	・選択列リストでも、where句中の条件式内でも利用可能
	・ユーザー定義関数も使える(ただしDBMS製品ごとに定められた記述(プログラミング)をする)
	・実行するSQL文をDBMS内に保存し、外部から実行を呼び出すものを『ストアドプロシージャ』という
	・ストアドプロシージャに処理をまとめることで、DBとアプリとのやりとりを少なくし、ネットワークの負荷を軽減できる
	・関数でDBMSに負荷が結構かかるから注意
	
*/

mysql> select メモ, length(メモ) as メモの長さ 
    -> from 家計簿;
+--------------------------+-----------------+
| メモ                     | メモの長さ      |
+--------------------------+-----------------+
| １１月の給料             |              18 |
| 水道代                   |               9 |
| レストラン雅             |              18 |
| １月の家賃支払い         |              24 |
| １２月の給料             |              18 |
| スッキリシネマズ         |              24 |
| 新年会                   |               9 |
| ２月の家賃支払い         |              24 |
+--------------------------+-----------------+



/*
	【文字にまつわる関数】
	・LENGTH(), TRIM(), LTRIM(), RTRIM()
		LENGTH() : 長さ取得
		TRIM(), RTRIM(), LTRIM() : 空白を削除
	・TRIM()はCHAR型の余白を除去するときに使える
	・REPLACE() : 文字の置換
		REPLATE( 列名, 置換前文字, 置換後文字 )
	・SUBSTRING(), SUBSTR() : 文字列を部分的に取得する  : DBMS製品ごとにどちらを使うかは異なる
		SUBSTR(列名, 開始位置, 終了位置);
		MySQLではどちらも使える
		
*/
mysql> update 家計簿
    -> set メモ = relace(メモ, '雅','みやび');


mysql> SELECT * FROM 家計簿
    -> WHERE SUBSTR(費目, 1, 3) LIKE '%費%';
+------------+-----------+--------------------------+-----------+-----------+
| 日付       | 費目      | メモ                     | 入金額    | 出金額    |
+------------+-----------+--------------------------+-----------+-----------+
| 2012-12-24 | 食費      | レストランみやび         |         0 |      5000 |
| 2012-12-25 | 居住費    | １月の家賃支払い         |         0 |     80000 |
| 2013-01-13 | 食費      | 新年会                   |         0 |      5000 |
| 2013-01-25 | 居住費    | ２月の家賃支払い         |         0 |     80000 |
+------------+-----------+--------------------------+-----------+-----------+

















