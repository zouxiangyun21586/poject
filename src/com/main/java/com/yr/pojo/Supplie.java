package com.yr.pojo;

public class Supplie {
    private int id; // 供应商id
    private String commo; // 商品名称
    private String merType; // 商品类型
    private double money; // 商品价格
    private String describe; // 商品描述
    private String origin; // 商品产地
    private String netContent; // 商品净含量
    private String packingMethod; // 商品包装
    private String brand; // 商品品牌
    private String qGp; // 商品保质期
    private String storageMethod; // 商品储藏方法
    private int number; // 商品数量
    private int upFrameTime; // 商品上架时间
    private int merId; // 商品ID 
    private int specificationID; // 商品规格Id
    private int suptID; // 供应商规格字段Id
    private int state; // 商品状态

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommo() {
        return commo;
    }

    public void setCommo(String commo) {
        this.commo = commo;
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

    public String getStorageMethod() {
        return storageMethod;
    }

    public void setStorageMethod(String storageMethod) {
        this.storageMethod = storageMethod;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getUpFrameTime() {
        return upFrameTime;
    }

    public void setUpFrameTime(int upFrameTime) {
        this.upFrameTime = upFrameTime;
    }

    public String getMerType() {
        return merType;
    }

    public void setMerType(String merType) {
        this.merType = merType;
    }

    public int getMerId() {
        return merId;
    }

    public void setMerId(int merId) {
        this.merId = merId;
    }

    public int getSpecificationID() {
        return specificationID;
    }

    public void setSpecificationID(int specificationID) {
        this.specificationID = specificationID;
    }

    public int getSuptID() {
        return suptID;
    }

    public void setSuptID(int suptID) {
        this.suptID = suptID;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

}
