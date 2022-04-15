package com.cqZnxj.entity;

public class DevParPatRec {

	private Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDaprId() {
		return daprId;
	}
	public void setDaprId(Integer daprId) {
		this.daprId = daprId;
	}
	public Integer getPdpId() {
		return pdpId;
	}
	public void setPdpId(Integer pdpId) {
		this.pdpId = pdpId;
	}
	public Boolean getParamIfExce() {
		return paramIfExce;
	}
	public void setParamIfExce(Boolean paramIfExce) {
		this.paramIfExce = paramIfExce;
	}
	public Float getParamValue() {
		return paramValue;
	}
	public void setParamValue(Float paramValue) {
		this.paramValue = paramValue;
	}
	public String getParamMemo() {
		return paramMemo;
	}
	public void setParamMemo(String paramMemo) {
		this.paramMemo = paramMemo;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getPlId() {
		return plId;
	}
	public void setPlId(Integer plId) {
		this.plId = plId;
	}
	public Integer getPtId() {
		return ptId;
	}
	public void setPtId(Integer ptId) {
		this.ptId = ptId;
	}
	public Integer getPsId() {
		return psId;
	}
	public void setPsId(Integer psId) {
		this.psId = psId;
	}
	public Boolean getFinish() {
		return finish;
	}
	public void setFinish(Boolean finish) {
		this.finish = finish;
	}
	private Integer daprId;
	private Integer pdpId;
	private Boolean paramIfExce;
	private Float paramValue;
	private String paramMemo;
	private String createTime;
	private Integer plId;
	private Integer ptId;
	private Integer psId;
	private Boolean finish;
}
