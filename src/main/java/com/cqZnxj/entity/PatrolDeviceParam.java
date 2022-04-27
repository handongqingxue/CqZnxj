package com.cqZnxj.entity;

public class PatrolDeviceParam {

	public static final int SHU_ZHI_LEI=1;
	public static final int GUAN_CHA_LEI=2;

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
	public Integer getPlId() {
		return plId;
	}
	public void setPlId(Integer plId) {
		this.plId = plId;
	}
	public String getPlName() {
		return plName;
	}
	public void setPlName(String plName) {
		this.plName = plName;
	}
	public Boolean getParamIfExce() {
		return paramIfExce;
	}
	public void setParamIfExce(Boolean paramIfExce) {
		this.paramIfExce = paramIfExce;
	}
	public Float getParamValue() {
		return paramValue;
	}
	public void setParamValue(Float paramValue) {
		this.paramValue = paramValue;
	}
	private String name;
	private Integer type;
	private Float warnDown;
	private Float warnUp;
	private String unit;
	private String createTime;
	private Integer deptId;
	private String deptName;
	private Integer secondDeptId;
	private String secondDeptName;
	private Integer firstDeptId;
	private String firstDeptName;
	private Integer pdId;
	private String pdName;
	private Integer pdaId;
	private String pdaNo;
	private Integer paId;
	private String paName;
	private Integer plId;
	private String plName;
	private Boolean paramIfExce;
	private Float paramValue;
}
