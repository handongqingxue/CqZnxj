package com.cqZnxj.entity;

public class PatrolLine {

	private Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getPatParCount() {
		return patParCount;
	}
	public void setPatParCount(Integer patParCount) {
		this.patParCount = patParCount;
	}
	public Integer getFinishParCount() {
		return finishParCount;
	}
	public void setFinishParCount(Integer finishParCount) {
		this.finishParCount = finishParCount;
	}
	public Integer getPatAccCount() {
		return patAccCount;
	}
	public void setPatAccCount(Integer patAccCount) {
		this.patAccCount = patAccCount;
	}
	public Integer getFinishAccCount() {
		return finishAccCount;
	}
	public void setFinishAccCount(Integer finishAccCount) {
		this.finishAccCount = finishAccCount;
	}
	public Integer getFinishAccPercent() {
		return finishAccPercent;
	}
	public void setFinishAccPercent(Integer finishAccPercent) {
		this.finishAccPercent = finishAccPercent;
	}
	private String name;
	private String createTime;
	private Integer patParCount=0;
	private Integer finishParCount=0;
	private Integer patAccCount=0;
	private Integer finishAccCount=0;
	private Integer finishAccPercent=0;
}
