package com.cqZnxj.entity;

public class PatrolPlan {
	
	public static final int USUAL=1;
	public static final int TEMP=2;

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
	public Integer getPtId() {
		return ptId;
	}
	public void setPtId(Integer ptId) {
		this.ptId = ptId;
	}
	public String getPtName() {
		return ptName;
	}
	public void setPtName(String ptName) {
		this.ptName = ptName;
	}
	public String getPsIds() {
		return psIds;
	}
	public void setPsIds(String psIds) {
		this.psIds = psIds;
	}
	public String getPsNames() {
		return psNames;
	}
	public void setPsNames(String psNames) {
		this.psNames = psNames;
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
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	private String name;//计划名称
	private Integer type;//计划类型 1.日常 2.临时
	private Integer ptId;//巡更班组id
	private String ptName;//巡更班组名
	private String psIds;//巡更班组人员id
	private String psNames;//巡更班组人员名
	private String plIds;//巡检路线id
	private String plNames;//巡检路线名
	private String createTime;//创建时间
	private String startDate;//开始日期
	private String endDate;//结束日期
	private Integer state;//状态 1.未开始 2.进行中 3.已结束 4.暂停中
}
