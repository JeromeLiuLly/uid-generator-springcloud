package com.candao.dms.uid.enums;

/**
 * 
 * @author jeromeLiu
 */
public enum ServiceCodeEnum {
	
	MCDONALD_ORDER("1000","MCDONALD-ORDER","MCDONALD","麦当劳下单业务ID"),
	KUNGFU_ORDER("1000","KUNGFU-ORDER","KUNGFU","真功夫下单业务ID"),
	YOSHINOYA_ORDER("1000","YOSHINOYA-ORDER","YOSHINOYA","吉野家下单业务ID"),	
	DEFAULT_ORDER("1000","DEFAULT-ORDER","DEFAULT","默认下单业务ID");
	
	
	private String serviceCode;
	
	private String servicePrefix;
	
	private String serviceName;
	
	private String desc;
	
	private ServiceCodeEnum(String serviceCode, String servicePrefix, String serviceName, String desc) {
		this.serviceCode = serviceCode;
		this.servicePrefix = servicePrefix;
		this.serviceName = serviceName;
		this.desc = desc;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getServicePrefix() {
		return servicePrefix;
	}

	public void setServicePrefix(String servicePrefix) {
		this.servicePrefix = servicePrefix;
	}

	/**
	 * @param prefix
	 * 
	 * 根据 prefix 获取 serviceCodeEnum对象
	 * @return
	 */
	public static ServiceCodeEnum getServiceByPrefix(String prefix){
		ServiceCodeEnum[] serviceCodeEnums = ServiceCodeEnum.values();
		for(ServiceCodeEnum serviceCodeEnum:serviceCodeEnums){
			if(prefix.equals(serviceCodeEnum.servicePrefix)){
				return serviceCodeEnum;
			}
		}
		return DEFAULT_ORDER;
	}

}
