package com.huzz.designpattern;

/*
 * 双重校验锁
 */
public class SingletonClass2 {
	
	private static volatile SingletonClass2  instance;//声明成 volatile
	
	private SingletonClass2(){
		
	}
	
	public static SingletonClass2 getInstance(){
		if(instance == null){
			synchronized (SingletonClass2.class) {
				if(instance == null){
					instance = new SingletonClass2();
				}
			}
		}
		return instance;
	}
}