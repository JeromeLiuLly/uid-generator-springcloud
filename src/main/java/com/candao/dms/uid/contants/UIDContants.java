package com.candao.dms.uid.contants;

/**
 * @author jeromeLiu
 */
public interface UIDContants {

	/**
	 * 单次操作产生1000个uid
	 */
	public final static int STEP_SIZE = 100;

	/**
	 * 批量的uid ==> key
	 */
	public final static String UID_GENERATOR_LIST = "uid:generator:list";

	/**
	 * 分布式锁,flag位
	 */
	public final static String FLAG_BIT = "uid:generator";

	/**
	 * 分布式锁,flag值
	 */
	public final static String FLAG_BIT_VALUE = "1";
	
	/**
	 * 设置 uid ==> key list的失效时间：10 分钟 
	 */
	public final static Integer UID_GENERATOR_LIST_EXPIRE = 60 * 10 ;

}
