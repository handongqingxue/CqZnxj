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
	private String name;//�ƻ�����
	private Integer type;//�ƻ����� 1.�ճ� 2.��ʱ
	private Integer ptId;//Ѳ������id
	private String ptName;//Ѳ��������
	private String psIds;//Ѳ��������Աid
	private String psNames;//Ѳ��������Ա��
	private String plIds;//Ѳ��·��id
	private String plNames;//Ѳ��·����
	private String createTime;//����ʱ��
	private String startDate;//��ʼ����
	private String endDate;//��������
	private Integer state;//״̬ 1.δ��ʼ 2.������ 3.�ѽ��� 4.��ͣ��
}
