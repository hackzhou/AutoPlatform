package com.auto.test.socket.data;

import java.util.Map;
import com.auto.test.utils.JSONUtil;

public final  class ProtocolData {
	private short  serviceId;
    private int   protocolId;
    private int seq;
    private Map<String, Object> data;
    
    public ProtocolData(){
    }
    
    public ProtocolData(int   protocolId,Map<String, Object> data){
    	this.protocolId = protocolId;
    	this.data = data;
    }
    
    public ProtocolData(short  serviceId,int   protocolId,
    		int seq,Map<String, Object> data){
    	this.serviceId = serviceId;
    	this.protocolId = protocolId;
    	this.seq = seq;
    	this.data = data;
    }
    
    public ProtocolData(short  serviceId,int   protocolId,
    		int seq){
    	this.serviceId = serviceId;
    	this.protocolId = protocolId;
    	this.seq = seq;
    }
    
    public String toJson(){
    	return JSONUtil.bean2JSON(this, false);
    }
    
	public short getServiceId() {
		return serviceId;
	}
	public void setServiceId(short serviceId) {
		this.serviceId = serviceId;
	}
	public int getProtocolId() {
		return protocolId;
	}
	public void setProtocolId(int protocolId) {
		this.protocolId = protocolId;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
}
