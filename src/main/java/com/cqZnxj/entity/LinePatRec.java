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
	public Integer getFinishPercent() {
		return finishPercent;
	}
	public void setFinishPercent(Integer finishPercent) {
		this.finishPercent = finishPercent;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
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
	private Integer finishPercent;
	private String createTime;
	private String startTime;
	private String endTime;
	private Integer plId;
	private String plName;
	private Integer ptId;
	private Boolean finish;
}
