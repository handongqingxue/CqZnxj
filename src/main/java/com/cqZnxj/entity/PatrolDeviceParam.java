package com.cqZnxj.entity;

public class PatrolDeviceParam {

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
	public Float getWarnDown() {
		return warnDown;
	}
	public void setWarnDown(Float warnDown) {
		this.warnDown = warnDown;
	}
	public Float getWarnUp() {
		return warnUp;
	}
	public void setWarnUp(Float warnUp) {
		this.warnUp = warnUp;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Integer getPdId() {
		return pdId;
	}
	public void setPdId(Integer pdId) {
		this.pdId = pdId;
	}
	public String getPdName() {
		return pdName;
	}
	public void setPdName(String pdName) {
		this.pdName = pdName;
	}
	public Integer getPdaId() {
		return pdaId;
	}
	public void setPdaId(Integer pdaId) {
		this.pdaId = pdaId;
	}
	public String getPdaNo() {
		return pdaNo;
	}
	public void setPdaNo(String pdaNo) {
		this.pdaNo = pdaNo;
	}
	private String name;
	private Integer type;
	private Float warnDown;
	private Float warnUp;
	private String unit;
	private String createTime;
	private Integer deptId;
	private String deptName;
	private Integer pdId;
	private String pdName;
	private Integer pdaId;
	private String pdaNo;
}
