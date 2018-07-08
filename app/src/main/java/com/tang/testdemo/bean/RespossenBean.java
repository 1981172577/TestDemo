package com.tang.testdemo.bean;

//请求返回类

public class RespossenBean {
	
	public static final int SUCCESS = 10;
	
	private int returnCode;//10 正确
	private String returnMsg;
	private int dataCount;
	private String returnObject;
	private String returnData;
	
	public RespossenBean(){
		
	}
	public RespossenBean(int returnCode, String returnMsg){
		this.returnCode = returnCode;
		this.returnMsg = returnMsg;
	}
	
	public int getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public int getDataCount() {
		return dataCount;
	}

	public void setDataCount(int dataCount) {
		this.dataCount = dataCount;
	}

	public String getReturnObject() {
		return returnObject;
	}

	public void setReturnObject(String returnObject) {
		this.returnObject = returnObject;
	}

	public String getReturnData() {
		return returnData;
	}

	public void setReturnData(String returnData) {
		this.returnData = returnData;
	}

	@Override
	public String toString() {
		return "ReturnBean [returnCode=" + returnCode + ", returnMsg="
				+ returnMsg + ", dataCount=" + dataCount + ", returnObject="
				+ returnObject + ", returnData=" + returnData + "]";
	}

}
