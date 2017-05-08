package com.candao.dms.uid.enums;

/**
 * @author jeromeLiu
 * 
 * 业务枚举
 */
public enum ServiceEnum {
	
	McDonald("Service-0","MCDONALD","麦当劳"),
	Kungfu("Service-1","KUNGFU","真功夫"),
	Yoshinoya("Service-2","YOSHINOYA","吉野家");
	
	private String serviceId;
	
	private String serviceName;
	
	private String desc;
	
	private ServiceEnum(String serviceId, String serviceName, String desc) {
		this.serviceId = serviceId;
		this.serviceName = serviceName;
		this.desc = desc;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
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
	
	/**
	 * @param serviceId
	 * 
	 * 根据 serviceId 获取 serviceEnum对象
	 * @return
	 */
	public static ServiceEnum getServiceById(String serviceId){
		ServiceEnum[] serviceEnums = ServiceEnum.values();
		for(ServiceEnum serviceEnum:serviceEnums){
			if(serviceId.equals(serviceEnum.serviceId)){
				return serviceEnum;
			}
		}
		return null;
	}
	
	/**
	 * @param serviceId
	 * 
	 * 根据 serviceName 获取 serviceEnum对象
	 * @return
	 */
	public static ServiceEnum getServiceByName(String serviceName){
		ServiceEnum[] serviceEnums = ServiceEnum.values();
		for(ServiceEnum serviceEnum:serviceEnums){
			if(serviceName.equals(serviceEnum.serviceName)){
				return serviceEnum;
			}
		}
		return null;
	}

}
