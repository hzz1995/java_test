package com.huzz.designpattern;

/*
 * 双重校验锁测试类
 */
public class SingletonClassTest2 {
	public static void instantiation(){	
		SingletonClass2 sc1 = SingletonClass2.getInstance();
		System.out.println("第一次取得实例sc1");		
		SingletonClass2 sc2 = SingletonClass2.getInstance();
		System.out.println("第二次取得实例sc2");
		if(sc1 == sc2){
			System.out.println("sc1和sc2是同一个实例(双重校验锁模式)");
		}
	}
	public static void main(String[] args){
		instantiation();
	}
}