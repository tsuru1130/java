<?php

        class TestParent{
                public function testMethod(){
                        static $num = 0;
                        $num++;
                        echo __METHOD__ . $num . "<br>";
                }
        }

        class TestChildA extends TestParent{
        }

        class TestChildB extends TestParent{
        }

        class TestChildC extends TestChildA{
        }

        echo "TestParent" . "<br>";
        $p1 = new TestParent();
        $p2 = new TestParent();
        $p1->testMethod();
        $p2->testMethod();
        $p1->testMethod();
        $p2->testMethod();
        $p1->testMethod();
        $p2->testMethod();

        echo "TestChildA" . "<br>";
        $a1 = new TestChildA();
        $a2 = new TestChildA();
        $a1->testMethod();
        $a2->testMethod();
        $a1->testMethod();
        $a2->testMethod();
        $a1->testMethod();
        $a2->testMethod();

        echo "TestChildB" . "<br>";
        $b1 = new TestChildB();
        $b2 = new TestChildB();
        $b1->testMethod();
        $b2->testMethod();
        $b1->testMethod();
        $b2->testMethod();
        $b1->testMethod();
        $b2->testMethod();

        echo "TestChildC" . "<br>";
        $b1 = new TestChildC();
        $b2 = new TestChildC();
        $b1->testMethod();
        $b2->testMethod();
        $b1->testMethod();
        $b2->testMethod();
        $b1->testMethod();
        $b2->testMethod();

        /*
         * 出力結果
         * クラス毎で独立
         * インスタンス間で共通
         *
        TestParent
        TestParent::testMethod1
        TestParent::testMethod2
        TestParent::testMethod3
        TestParent::testMethod4
        TestParent::testMethod5
        TestParent::testMethod6

        TestChildA
        TestParent::testMethod1
        TestParent::testMethod2
        TestParent::testMethod3
        TestParent::testMethod4
        TestParent::testMethod5
        TestParent::testMethod6

        TestChildB
        TestParent::testMethod1
        TestParent::testMethod2
        TestParent::testMethod3
        TestParent::testMethod4
        TestParent::testMethod5
        TestParent::testMethod6

        TestChildC
        TestParent::testMethod1
        TestParent::testMethod2
        TestParent::testMethod3
        TestParent::testMethod4
        TestParent::testMethod5
        TestParent::testMethod6
         */