package com.cqZnxj.entity;

import java.util.List;

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
	public Boolean getFinish() {
		return finish;
	}
	public void setFinish(Boolean finish) {
		this.finish = finish;
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
	public List<PatrolDeviceAccount> getPdaList() {
		return pdaList;
	}
	public void setPdaList(List<PatrolDeviceAccount> pdaList) {
		this.pdaList = pdaList;
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
	private Boolean finish;
	private Integer patParCount=0;
	private Integer finishParCount=0;
	private List<PatrolDeviceAccount> pdaList;
}
