<?php

        class Test{
                private $name;
                private $hp;
                static private $money = 1000;

                public function setName( $name ){
                        $this->name = $name;
                }

                public function getName(){
                        return $this->name;
                }

                public function setHp( $hp ){
                        $this->hp = $hp;
                }

                public function getHp(){
                        return $this->hp;
                }

                public static function setMoney( $money ){
                        self::$money -= $money;
                }

                // クラスプロパティにクラスメソッドにアクセスする場合はself::を使う
                // Javaと違うのはJavaはself::を使わなくても大丈夫
                // Javaはメソッドの中にstaticは書けない
                public function getMoney(){
                        return self::$money;
                }

                public function damage( $int ){
                        $this->hp -= $int;
                }
        }

        $t1 = new Test();
        $t2 = new Test();
        $t1->setName( "tsuruchoff" );
        $t2->setName( "tully's" );
        $t1->setHp( 100 );
        $t2->setHp( 30 );

        echo $t1->getHp();
        echo "<br>";
        echo $t2->getHp();
        echo "<br>";
        $t1->damage( 14 );
        $t2->damage( 6 );
        echo "<br>";
        echo $t1->getHp();
        echo "<br>";
        echo $t2->getHp();


        echo "<br>";
        echo "<br>";


        echo " money<br>";
        $t1->setMoney( 100 );
        echo $t1->getMoney();
        echo "<br>";
        echo $t2->getMoney();

/*
	出力結果
	100
	30
	
	86
	24
	
	money
	900
	900	
	
*/



 class Test{
                public function aaa(){
                        static $int = 1;
                        echo $int . '<br>';
                        $int++;
                }
        }

        // staticは明示された場所に所属するクラス内、もしくは関数内のメモリ内のフィールドを指す
        $t1 = new Test();
        $t1->aaa();
        $t1->aaa();
	  
	  /*
		  出力結果
		  1
		  2
		  
	  */








