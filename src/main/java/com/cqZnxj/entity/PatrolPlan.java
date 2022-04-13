package com.cqZnxj.entity;

public class PatrolPlan {

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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getPtIds() {
		return ptIds;
	}
	public void setPtIds(String ptIds) {
		this.ptIds = ptIds;
	}
	public String getPtNames() {
		return ptNames;
	}
	public void setPtNames(String ptNames) {
		this.ptNames = ptNames;
	}
	public String getPlIds() {
		return plIds;
	}
	public void setPlIds(String plIds) {
		this.plIds = plIds;
	}
	public String getPlNames() {
		return plNames;
	}
	public void setPlNames(String plNames) {
		this.plNames = plNames;
	}
	public Integer getFrequency() {
		return frequency;
	}
	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Integer getLeastPopulation() {
		return leastPopulation;
	}
	public void setLeastPopulation(Integer leastPopulation) {
		this.leastPopulation = leastPopulation;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	private String name;//计划名称
	private Integer type;//计划类型 1.日常 2.临时
	private String ptIds;//巡更班组id
	private String ptNames;//巡更班组名
	private String plIds;//巡检路线id
	private String plNames;//巡检路线名
	private Integer frequency;//巡更频次
	private String createTime;//创建时间
	private String startDate;//开始日期
	private String endDate;//结束日期
	private Integer leastPopulation;//最少巡更人数
	private Integer state;//状态 1.未开始 2.进行中 3.已结束 4.暂停中
}
