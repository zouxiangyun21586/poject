package com.yr.pojo;
/**
 * @作者 林水桥
 * 2017年12月28日下午6:02:53
 */
public class Seller {
    /**
     * 商品ID
     */
    private int wares_id;
    /**
     * 商品名称
     */
    private String name; 
    /**
     * 商品价格
     */
    private String money; 
    /**
     * 商品描述
     */
    private String describe;
    /**
     * 商品规格ID
     */
    private int speciID;
    /**
     * 商品数量
     */
    private int number; 
    /**
     * 商品信息保存时间
     */
    private String upFrameTime;
    /**
     * 卖家发布ID
     */
    private int id;  
    /**
     * 卖家审核表ID
     */
    private int auditID;
    /**
     * 审核用户名称
     */
    private String auditName; 
    /**
     * 申请审核时间
     */
    private String addTime;
    /**
     * 美食产地 
     */
    private String origin;
    /**
     * 美食净含量
     */
    private String netContent;
    /**
     * 包装方式
     */
    private String packingMethod;
    /**
     * 商品品牌
     */
    private String brand; 
    /**
     * 商品保质期ID
     */
    private int qGP;
    /**
     * 商品储藏方式
     */
    private String storageMethod;
    /**
     * 卖家ID
     */
    private int seller_id;
    /**
     * 商品类型
     */
    private String nameType;
    /**
     * 卖家商品状态 0是保存 1是正在审核 2是审核成功
     */
    private int auditStatus; 
    /**
     * 卖家商品上架时间
     */
    private String time;
    /**
     * 卖家商品下架时间
     */
    private String downtime;
    /**
     * 商品类型ID
     */
    private int nameTypeID;
    /**
     * 美食保质期
     */
    private String month;
    public String getMonth() {
        return month;
    }
    public void setMonth(String month) {
        this.month = month;
    }
    public Seller(int id,String name,String brand){
        this.id=id;
        this.name=name;
        this.brand=brand;
    }
    public Seller(){
        
    }
    public int getAuditID() {
        return auditID;
    }
    public void setAuditID(int auditID) {
        this.auditID = auditID;
    }
    public String getAuditName() {
        return auditName;
    }
    public void setAuditName(String auditName) {
        this.auditName = auditName;
    }
    public String getAddTime() {
        return addTime;
    }
    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }
    public int getNameTypeID() {
        return nameTypeID;
    }
    public void setNameTypeID(int nameTypeID) {
        this.nameTypeID = nameTypeID;
    }
    public int getWares_id() {
        return wares_id;
    }
    public void setWares_id(int wares_id) {
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
    public int getSpeciID() {
        return speciID;
    }
    public void setSpeciID(int speciID) {
        this.speciID = speciID;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public String getUpFrameTime() {
        return upFrameTime;
    }
    public void setUpFrameTime(String upFrameTime) {
        this.upFrameTime = upFrameTime;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
    public int getqGP() {
        return qGP;
    }
    public void setqGP(int qGP) {
        this.qGP = qGP;
    }
    public String getStorageMethod() {
        return storageMethod;
    }
    public void setStorageMethod(String storageMethod) {
        this.storageMethod = storageMethod;
    }
    public int getSeller_id() {
        return seller_id;
    }
    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
    }
    public String getNameType() {
        return nameType;
    }
    public void setNameType(String nameType) {
        this.nameType = nameType;
    }
    public int getAuditStatus() {
        return auditStatus;
    }
    public void setAuditStatus(int auditStatus) {
        this.auditStatus = auditStatus;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getDowntime() {
        return downtime;
    }
    public void setDowntime(String downtime) {
        this.downtime = downtime;
    }
}
