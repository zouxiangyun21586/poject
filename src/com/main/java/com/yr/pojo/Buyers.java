package com.yr.pojo;
/**
 * ���ʵ����
 * @author ��ҵ��
 * 2018��1��23�� ����10:55:42
 */
public class Buyers {
	private Integer fbId;//������id
	private Integer accountId;//�û�id
	private Integer wares_id; // ��ƷID
	private String fbTime;//����ʱ��
    private String name;  // ��Ʒ����
    private String money; // ��Ʒ�۸�
    private String describe;  // ��Ʒ����
    private Integer speciID;  // ��Ʒ���ID
    private Integer number;   // ��Ʒ����
    
    private String nameType; // ��Ʒ����
    private Integer auditStatus; // ������Ʒ״̬ 0�Ǳ��� 1��������� 2����˳ɹ�
	public Integer getFbId() {
		return fbId;
	}
	public void setFbId(Integer fbId) {
		this.fbId = fbId;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	
	public String getFbTime() {
		return fbTime;
	}
	public void setFbTime(String fbTime) {
		this.fbTime = fbTime;
	}
	public Integer getWares_id() {
		return wares_id;
	}
	public void setWares_id(Integer wares_id) {
		this.wares_id = wares_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public Integer getSpeciID() {
		return speciID;
	}
	public void setSpeciID(Integer speciID) {
		this.speciID = speciID;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getNameType() {
		return nameType;
	}
	public void setNameType(String nameType) {
		this.nameType = nameType;
	}
	public Integer getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
}
