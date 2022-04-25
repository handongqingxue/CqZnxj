package com.cqZnxj.entity;

public class PatrolDeviceAccount {

	private Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
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
	public Integer getPaId() {
		return paId;
	}
	public void setPaId(Integer paId) {
		this.paId = paId;
	}
	public String getPaName() {
		return paName;
	}
	public void setPaName(String paName) {
		this.paName = paName;
	}
	public String getPlName() {
		return plName;
	}
	public void setPlName(String plName) {
		this.plName = plName;
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getCreateStaffId() {
		return createStaffId;
	}
	public void setCreateStaffId(Integer createStaffId) {
		this.createStaffId = createStaffId;
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
	public String getStartTimeHi() {
		return startTimeHi;
	}
	public void setStartTimeHi(String startTimeHi) {
		this.startTimeHi = startTimeHi;
	}
	public String getEndTimeHi() {
		return endTimeHi;
	}
	public void setEndTimeHi(String endTimeHi) {
		this.endTimeHi = endTimeHi;
	}
	public String getQrcodeUrl() {
		return qrcodeUrl;
	}
	public void setQrcodeUrl(String qrcodeUrl) {
		this.qrcodeUrl = qrcodeUrl;
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
	public Boolean getFinish() {
		return finish;
	}
	public void setFinish(Boolean finish) {
		this.finish = finish;
	}
	private String no;
	private Integer pdId;
	private String pdName;
	private Integer paId;
	private String paName;
	private String plName;
	private Integer deptId;
	private String deptName;
	private Integer secondDeptId;
	private String secondDeptName;
	private Integer firstDeptId;
	private String firstDeptName;
	private String createTime;
	private Integer createStaffId;
	private String startTime;
	private String endTime;
	private String startTimeHi;
	private String endTimeHi;
	private String qrcodeUrl;
	private Integer patParCount=0;
	private Integer finishParCount=0;
	private Boolean finish;
}
