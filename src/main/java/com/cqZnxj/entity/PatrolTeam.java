package com.cqZnxj.entity;

public class PatrolTeam {

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
	public String getLeader() {
		return leader;
	}
	public void setLeader(String leader) {
		this.leader = leader;
	}
	public String getWorkDay() {
		return workDay;
	}
	public void setWorkDay(String workDay) {
		this.workDay = workDay;
	}
	public String getStaffIds() {
		return staffIds;
	}
	public void setStaffIds(String staffIds) {
		this.staffIds = staffIds;
	}
	public String getStaffNames() {
		return staffNames;
	}
	public void setStaffNames(String staffNames) {
		this.staffNames = staffNames;
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getUpdateStaffId() {
		return updateStaffId;
	}
	public void setUpdateStaffId(Integer updateStaffId) {
		this.updateStaffId = updateStaffId;
	}
	private String name;
	private String startTime;
	private String endTime;
	private String leader;
	private String workDay;
	private String staffIds;
	private String staffNames;
	private Integer deptId;
	private String deptName;
	private Integer firstDeptId;
	private String firstDeptName;
	private Integer secondDeptId;
	private String secondDeptName;
	private String createTime;
	private String updateTime;
	private Integer updateStaffId;
}
