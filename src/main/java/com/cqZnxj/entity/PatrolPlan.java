package com.cqZnxj.entity;

public class PatrolPlan {

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
	public String getPtIds() {
		return ptIds;
	}
	public void setPtIds(String ptIds) {
		this.ptIds = ptIds;
	}
	public String getPtNames() {
		return ptNames;
	}
	public void setPtNames(String ptNames) {
		this.ptNames = ptNames;
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
	public Integer getFrequency() {
		return frequency;
	}
	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
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
	public Integer getLeastPopulation() {
		return leastPopulation;
	}
	public void setLeastPopulation(Integer leastPopulation) {
		this.leastPopulation = leastPopulation;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	private String name;//�ƻ�����
	private Integer type;//�ƻ����� 1.�ճ� 2.��ʱ
	private String ptIds;//Ѳ������id
	private String ptNames;//Ѳ��������
	private String plIds;//Ѳ��·��id
	private String plNames;//Ѳ��·����
	private Integer frequency;//Ѳ��Ƶ��
	private String createTime;//����ʱ��
	private String startDate;//��ʼ����
	private String endDate;//��������
	private Integer leastPopulation;//����Ѳ������
	private Integer state;//״̬ 1.δ��ʼ 2.������ 3.�ѽ��� 4.��ͣ��
}
