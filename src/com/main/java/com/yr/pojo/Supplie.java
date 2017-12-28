package com.yr.pojo;

import java.sql.Date;

public class Supplie {
    private int id; // 供应商id
    private int merId; // 商品id
    private int suptId; // 供应商商品id
    private int nameTypeId; // 商品类型id
    private int specificationId; // 商品规格id
    private int month_tableId; // 商品规格中月份id
    private int release_supplierId; // 供应商发布表id
    private String commo; // 商品名称
    private String supMerName; // 供应商商品名
    private double money; // 商品价格
    private String describe; // 商品描述
    private int number; // 商品数量
    private String upFrameTime; // 商品上架时间
    private int state; // 商品状态
    private String origin; // 商品产地
    private String netContent; // 商品净含量
    private String packingMethod; // 商品包装
    private String brand; // 商品品牌
    private String qGp; // 商品保质期
    private String typeName; // 商品类型名
    private String storageMethod; // 商品储藏方法
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getMerId() {
        return merId;
    }
    public void setMerId(int merId) {
        this.merId = merId;
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
    public int getRelease_supplierId() {
        return release_supplierId;
    }
    public int getMonth_tableId() {
        return month_tableId;
    }
    public void setMonth_tableId(int month_tableId) {
        this.month_tableId = month_tableId;
    }
    public void setRelease_supplierId(int release_supplierId) {
        this.release_supplierId = release_supplierId;
    }
    public String getCommo() {
        return commo;
    }
    public void setCommo(String commo) {
        this.commo = commo;
    }
    public String getSupMerName() {
        return supMerName;
    }
    public void setSupMerName(String supMerName) {
        this.supMerName = supMerName;
    }
    public double getMoney() {
        return money;
    }
    public void setMoney(double money) {
        this.money = money;
    }
    public String getDescribe() {
        return describe;
    }
    public void setDescribe(String describe) {
        this.describe = describe;
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
    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
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
    
}
