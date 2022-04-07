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
	private String name;
	private String createTime;
	private String pdaIds;
	private String pdaNos;
	private Integer deptId;
	private String deptName;
}
