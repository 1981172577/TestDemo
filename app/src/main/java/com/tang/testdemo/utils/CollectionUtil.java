package com.tang.testdemo.utils;

import java.util.Collection;


/**
 * 工具类 - 公用
 */

public class CollectionUtil {
	/**
	 * 
	 *@author Administrator
	 *@date 2014-9-12
	 *@description  判断一个集合是否为空
	 *@param cols
	 *@return
	 *@return
	 */
	public static boolean  isEmpty(Collection cols){
		if(cols==null||cols.size()==0){
			return true;
		}
		return false;
	} 
	/**
	 * 
	 *@author Administrator
	 *@date 2014-9-12
	 *@description 判断一个集合不为空
	 *@param cols
	 *@return
	 *@return
	 */
	public static boolean  isNotEmpty(Collection cols){
		if(cols!=null&&cols.size()>0){
			return true;
		}
		return false;
	} 
	/**
	 * 
	 *@author Administrator
	 *@date 2014-9-12
	 *@description 判断一个集合是否是null
	 *@param cols
	 *@return
	 *@return
	 */
	public static boolean  isNull(Collection cols){
		if(cols==null){
			return true;
		}
		return false;
	}
	/**
	 * 
	 *@author Administrator
	 *@date 2014-9-12
	 *@description 判断一个集合是否非NULL
	 *@param cols
	 *@return
	 *@return
	 */
	public static boolean  isNotNull(Collection cols){
		if(cols!=null){
			return true;
		}
		return false;
	}
	
	public  static Integer  getIntCollectionSum(Collection<Integer>inters){
		if(CollectionUtil.isEmpty(inters)){
			return null;
		}
		Integer result=0;
		for(Integer i:inters){
			result=result+i;
		}
		return result;
	}
}