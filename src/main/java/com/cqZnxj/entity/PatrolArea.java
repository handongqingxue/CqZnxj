package com.cqZnxj.entity;

public class PatrolArea {

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
	public Integer getSecondDeptId() {
		return secondDeptId;
	}
	public void setSecondDeptId(Integer secondDeptId) {
		this.secondDeptId = secondDeptId;
	}
	public String getSecondDeptName() {
		return secondDeptName;
	}
	public void setSecondDeptName(String secondDeptName) {
		this.secondDeptName = secondDeptName;
	}
	public Integer getFirstDeptId() {
		return firstDeptId;
	}
	public void setFirstDeptId(Integer firstDeptId) {
		this.firstDeptId = firstDeptId;
	}
	public String getFirstDeptName() {
		return firstDeptName;
	}
	public void setFirstDeptName(String firstDeptName) {
		this.firstDeptName = firstDeptName;
	}
	private String name;
	private String createTime;
	private String pdaIds;
	private String pdaNos;
	private Integer deptId;
	private String deptName;
	private Integer secondDeptId;
	private String secondDeptName;
	private Integer firstDeptId;
	private String firstDeptName;
}
