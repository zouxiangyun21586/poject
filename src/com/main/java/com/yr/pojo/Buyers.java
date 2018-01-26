package com.yr.pojo;
/**
 * 买家实体类
 * @author 周业好
 * 2018年1月23日 上午10:55:42
 */
public class Buyers {
	private Integer fbId;//发布表id
	private Integer accountId;//用户id
	private Integer wares_id; // 商品ID
	private String fbTime;//发布时间
    private String name;  // 商品名称
    private String money; // 商品价格
    private String describe;  // 商品描述
    private Integer speciID;  // 商品规格ID
    private Integer number;   // 商品数量
    
    private String nameType; // 商品类型
    private Integer auditStatus; // 卖家商品状态 0是保存 1是正在审核 2是审核成功
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
