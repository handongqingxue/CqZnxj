package com.cqZnxj.entity;

public class PatLineAreaAccSet {

	private Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPlId() {
		return plId;
	}
	public void setPlId(Integer plId) {
		this.plId = plId;
	}
	public String getPlName() {
		return plName;
	}
	public void setPlName(String plName) {
		this.plName = plName;
	}
	public Integer getPaId() {
		return paId;
	}
	public void setPaId(Integer paId) {
		this.paId = paId;
	}
	public String getPaName() {
		return paName;
	}
	public void setPaName(String paName) {
		this.paName = paName;
	}
	public String getPdaIds() {
		return pdaIds;
	}
	public void setPdaIds(String pdaIds) {
		this.pdaIds = pdaIds;
	}
	public String getPdaNos() {
		return pdaNos;
	}
	public void setPdaNos(String pdaNos) {
		this.pdaNos = pdaNos;
	}
	private Integer plId;
	private String plName;
	private Integer paId;
	private String paName;
	private String pdaIds;
	private String pdaNos;
}
