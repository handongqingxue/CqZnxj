package com.cqZnxj.entity;

public class LinePatRec {

	private Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPatAreaCount() {
		return patAreaCount;
	}
	public void setPatAreaCount(Integer patAreaCount) {
		this.patAreaCount = patAreaCount;
	}
	public Integer getFinishAreaCount() {
		return finishAreaCount;
	}
	public void setFinishAreaCount(Integer finishAreaCount) {
		this.finishAreaCount = finishAreaCount;
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
	public Boolean getFinish() {
		return finish;
	}
	public void setFinish(Boolean finish) {
		this.finish = finish;
	}
	private Integer patAreaCount;
	private Integer finishAreaCount;
	private String createTime;
	private Integer plId;
	private Integer ptId;
	private Boolean finish;
}
