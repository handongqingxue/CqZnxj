package com.cqZnxj.entity;

public class PatrolDevice {

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
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getSpecs() {
		return specs;
	}
	public void setSpecs(String specs) {
		this.specs = specs;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public String getMakeDate() {
		return makeDate;
	}
	public void setMakeDate(String makeDate) {
		this.makeDate = makeDate;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	private String name;
	private String deptName;
	private String specs;
	private Integer deptId;
	private String makeDate;
	private Integer level;
}
