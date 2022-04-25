package com.cqZnxj.entity;

public class AreaPatRec {
	
	private Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Integer getPaId() {
		return paId;
	}
	public void setPaId(Integer paId) {
		this.paId = paId;
	}
	public Integer getPtId() {
		return ptId;
	}
	public void setPtId(Integer ptId) {
		this.ptId = ptId;
	}
	public Integer getLprId() {
		return lprId;
	}
	public void setLprId(Integer lprId) {
		this.lprId = lprId;
	}
	public Boolean getFinish() {
		return finish;
	}
	public void setFinish(Boolean finish) {
		this.finish = finish;
	}
	private Integer patAccCount;
	private Integer finishAccCount;
	private String createTime;
	private String startTime;
	private String endTime;
	private Integer plId;
	private Integer paId;
	private Integer ptId;
	private Integer lprId;
	private Boolean finish;
}
