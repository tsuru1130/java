<?php

        trait Trait1{
                public function testMethod(){
                        static $num = 0;
                        $num++;
                        echo __METHOD__ . $num . "<br>";
                }
        }

        trait Trait2{
                public function testMethodA(){
                        static $num = 0;
                        $num++;
                        echo __METHOD__ . $num . "<br>";
                }
        }

        class UserA{
                use Trait1;
                use Trait2;
        }

        $a1 = new UserA();
        $a2 = new UserA();
        $a1->testMethod();
        $a2->testMethod();
        $a1->testMethod();
        $a2->testMethod();
        echo "休憩<br>";
        $a1->testMethodA();
        $a2->testMethodA();
        $a1->testMethodA();
        $a2->testMethodA();

        /*
        * Trait1::testMethod1
        Trait1::testMethod2
        Trait1::testMethod3
        Trait1::testMethod4
        休憩
        Trait2::testMethodA1
        Trait2::testMethodA2
        Trait2::testMethodA3
        Trait2::testMethodA4
        *
        */