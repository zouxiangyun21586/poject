package com.yr.pojo;

import java.sql.Date;

/**
 * 
 * 2017
 * @author zxy
 * Administrator
 * 2017年12月28日 下午8:23:41
 */
public class Supplie {
    private int id; // 商品id
    private int suptId; // 供应商id
    private int nameTypeId; // 类型id
    private int specificationId; // 规格id
    private int month_tableId; // 保质期id
    private int release_supplierId; //供应商发布id
    private int number; // 数量
    private String commo; // 商品名
    private int money; // 价格
    private String Month;
    private String describe; // 描述
    private String upFrameTime; // 上架时间
    private String origin;  // 产地
    private String netContent; // 净含量 
    private String packingMethod; // 包装
    private String brand; // 品牌
    private String qGp; // 日期
    private String typeName; // 商品类型名
    private String storageMethod; // 储藏方式
    private int auditStatus; // 供应商状态
    private int speId; // 规格表id
    private int merId; // 商品表id
    private int account_id; //账户id
    private String account; // 账户名
    private String releaseTime;
    
    public String getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public int getAccount_id() {
		return account_id;
	}
	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}
	public int getSpeId() {
        return speId;
    }
    public void setSpeId(int speId) {
        this.speId = speId;
    }
    public int getMerId() {
        return merId;
    }
    public void setMerId(int merId) {
        this.merId = merId;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getSuptId() {
        return suptId;
    }
    public void setSuptId(int suptId) {
        this.suptId = suptId;
    }
    public int getNameTypeId() {
        return nameTypeId;
    }
    public void setNameTypeId(int nameTypeId) {
        this.nameTypeId = nameTypeId;
    }
    public int getSpecificationId() {
        return specificationId;
    }
    public void setSpecificationId(int specificationId) {
        this.specificationId = specificationId;
    }
    public int getMonth_tableId() {
        return month_tableId;
    }
    public void setMonth_tableId(int month_tableId) {
        this.month_tableId = month_tableId;
    }
    public int getRelease_supplierId() {
        return release_supplierId;
    }
    public void setRelease_supplierId(int release_supplierId) {
        this.release_supplierId = release_supplierId;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public String getCommo() {
        return commo;
    }
    public void setCommo(String commo) {
        this.commo = commo;
    }
    public int getMoney() {
        return money;
    }
    public void setMoney(int money) {
        this.money = money;
    }
    public String getMonth() {
        return Month;
    }
    public void setMonth(String month) {
        Month = month;
    }
    public String getDescribe() {
        return describe;
    }
    public void setDescribe(String describe) {
        this.describe = describe;
    }
    public String getUpFrameTime() {
        return upFrameTime;
    }
    public void setUpFrameTime(String upFrameTime) {
        this.upFrameTime = upFrameTime;
    }
    public String getOrigin() {
        return origin;
    }
    public void setOrigin(String origin) {
        this.origin = origin;
    }
    public String getNetContent() {
        return netContent;
    }
    public void setNetContent(String netContent) {
        this.netContent = netContent;
    }
    public String getPackingMethod() {
        return packingMethod;
    }
    public void setPackingMethod(String packingMethod) {
        this.packingMethod = packingMethod;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getqGp() {
        return qGp;
    }
    public void setqGp(String qGp) {
        this.qGp = qGp;
    }
    public String getTypeName() {
        return typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    public String getStorageMethod() {
        return storageMethod;
    }
    public void setStorageMethod(String storageMethod) {
        this.storageMethod = storageMethod;
    }
    public int getAuditStatus() {
        return auditStatus;
    }
    public void setAuditStatus(int auditStatus) {
        this.auditStatus = auditStatus;
    }  
    
}
