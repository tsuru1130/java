/*
	
	【集計関数の特徴】
	・SELECT文の選択列、HAVING句でしかつかえない(WHERE句のなかでは使えない)
	・他の関数と違い(１行ずつ処理するのではなく)、対象となる行に対して１度だけ計算を行い、１行の答えを出す
	・count(*)とcount(列名)はNULL扱い方が違う
		→ count(*)はNULLもカウントする
	・単に検索結果の行数を得るだけならcount(*)がいい
	・count( distinct 列名 )で重複した値を除外した集計ができる
	・NULLを含む計算や比較は結果的にNULL(UNKNOWN)になる
		↑なぜか集計関数の場合はNULLの扱い方が関数によってまちまち
	・NULLを0とみなし集計する場合は、coalresce()と併用する
	・数万件程度のデータであればDBMSは一瞬で処理してしまう
	
*/

mysql> select 日付, sum(出金額) as 出金額合計 from 家計簿;
+------------+-----------------+
| 日付       | 出金額合計      |
+------------+-----------------+
| 2012-12-10 |          177000 |
+------------+-----------------+

/*↑ 『日付』が全て表示されない
	・本来は上記のSQL文を実行した場合は『凸凹型』の結果表になるが
		DBでは凸凹型の表は認められていない→通常はエラーになる(※なぜかMySQLでは整形されてる)
	
*/


/*NULLを0に変換した↑で平均する*/
SELECT AVG( COALESCE(出金額, 0 ) ) as 出金額の平均
	FROM 家計簿;


/*
	
	【グループ化】
	・グループ集計の流れ(３ステップ)
		① : 列名リストやwhere句で行列が絞り込まれる
		② : group by句で指定された列に同じ値を持つ行ごとに分類
		③ : 各グループで集計関数の処理が行われる
	・ポイント
		① : group by句で基準の列名を指定する
		② : グループ化されたグループごとに集計関数で処理される
		③ : 結果表の行数は、必ずグループの数と一致する
			→指定した列名の値数分(重複除外)
			→通常の集計関数は１行にまとめる
			→見方によっては、group by句を付けずに集計関数を利用する場合は、
				全ての行を１つのグループとみなしたグループ集計と考えることもできる
	
	
*/

mysql> select 費目, sum(出金額) as 費目別の出金額合計
    -> from 家計簿
    -> group by 費目; --グループ化の基準列名
+-----------------+-----------------------------+
| 費目            | 費目別の出金額合計          |
+-----------------+-----------------------------+
| 居住費          |                      160000 |
| 教養娯楽費      |                        2800 |
| 水道光熱費      |                        4200 |
| 給料            |                           0 |
| 食費            |                       10000 |
+-----------------+-----------------------------+



select 費目, sum(出金額) as 合計額  from 家計簿 where sum(出金額) > 0  group by 費目;
/*
	↑ではエラーが出る
	・where句が処理される①検索の時点では、③集計で行われるsum(出金額)の部分がまだ未確定のため
	・つまり集計関数とwhere句は併用できない
	・この場合はHAVING句を使う
	
*/

/*
	【HAVING句】
	・グループ化してから絞り込む
	・HEVING句とWHERE句は記述法などは同じ(ただしhaving句はgourp by句の後に書く)
	・絞込みを実行するタイミングが異なるだけの違い
		→WHERE句は"検索時"に絞込み
		→HAVING句は"集計後"に絞込み
	
	
*/

mysql> select 費目, sum(出金額) as 合計額 
    -> from 家計簿
    -> group by 費目
    -> having 合計額 > 0; -- grourp byの後に書く
+-----------------+-----------+
| 費目            | 合計額    |
+-----------------+-----------+
| 居住費          |    160000 |
| 教養娯楽費      |      2800 |
| 水道光熱費      |      4200 |
| 食費            |     10000 |
+-----------------+-----------+


/*
	
	【集計テーブルの活用】
	・あるテーブルで集計処理をした後に、処理結果を格納(INSERT)する為のテーブル
	・集計結果が必要な場合は、すでに作った集計テーブルに格納されている計算済みの集計結果を利用する
	・集計テーブルに格納されている結果は、最新の集計よりも古くなってしまう可能性がある(デメリット)
		→通常は、毎日、毎月、毎年などの一定のタイミングで集計処理を再実行し、最新の状態に更新する
		→つまり、集計テーブルは定期的な更新作業が必要
	
	
*/















