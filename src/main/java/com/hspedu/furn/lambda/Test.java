package com.hspedu.furn.lambda;

import sun.security.krb5.internal.crypto.Des;

/**
 * @author Zexi He.
 * @date 2023/5/11 20:48
 * @description:    定义一个函数式接口
 */
public class Test {

    public static void main(String[] args) {

        //传统方法实现接口并得到一个实现接口的对象，可以使用
//        HspFunction<Desk, String> hfImplement = new HspFunction<Desk, String>() {
//            @Override
//            public String apply(Desk desk) {
//                return "Hello! Desk!";
//            }
//        };
//
//        String val = hfImplement.apply(new Desk());
//        System.out.println("val:" + val);

        HspFunction<Desk, String> hf2 = Desk::getName;
        String val = hf2.apply(new Desk());
        System.out.println("val:" + val);
    }

}


//定义一个函数式接口: 有且仅有一个抽象方法的接口
//我们可以使用 @FunctionInterface 来标识一个函数式接口
// HspFunction 是一个函数式接口、自定义泛型接口

@FunctionalInterface
interface HspFunction<T, R> {
    R apply(T t);  //抽象方法，表示根据类型 T 参数获取到类型 R 的结果

    //函数式接口，依然可以有多个默认实现方法
    default void ok() {
        System.out.println("OK~~~~~");
    }
}

class Desk {

    private String name = "AAA";

    public String getName() {
        return name;
    }
}
