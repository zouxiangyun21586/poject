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
    /**
     * 供应商Id.
     */
    private int id;
    /**
     * 商品Id.
     */
    private int merId;
    /**
     *  供应商商品id.
     */
    private int suptId;
    /**
     * 商品类型id.
     */
    private int nameTypeId;
    /**
     * 商品规格id.
     */
    private int specificationId;
    /**
     * 商品规格中月份id.
     */
    private int month_tableId;
    /**
     * 供应商发布表id.
     */
    private int release_supplierId; 
    /**
     * 商品名称.
     */
    private String commo;
    /**
     * 供应商商品名.
     */
    private String supMerName;
    /**
     * 商品价格.
     */
    private double money;
    /**
     *  商品描述.
     */
    private String describe;
    /**
     * 商品数量.
     */
    private int number;
    /**
     * 商品上架时间.
     */
    private String upFrameTime;
    /**
     * 商品状态.
     */
    private int state; 
    /**
     * 商品产地.
     */
    private String origin; 
    /**
     * 商品净含量.
     */
    private String netContent;
    /**
     * 商品包装.
     */
    private String packingMethod;
    /**
     * 商品品牌.
     */
    private String brand;
    /**
     * 商品保质期.
     */
    private String qGp;
    /**
     * 商品类型名.
     */
    private String typeName;
    /**
     * 商品储藏方法.
     */
    private String storageMethod;
    /**
     * 判断是否通过审核,并且是否上/下架
     */
    private String auditStatus;
    /**
     * 
     * @author zxy
     * @return
     * 2018年1月2日  下午5:04:49
     */
    public String getAuditStatus() {
        return auditStatus;
    }
    /**
     * 
     * @author zxy
     * @param auditStatus
     * 2018年1月2日  下午5:04:52
     */
    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }
    /**
     *
     * @author zxy
     * @return
     * 2017年12月28日  下午8:29:01
     */
    public int getId() {
        return id;
    }
    /**
     *
     * @author zxy
     * @param id
     * 2017年12月28日  下午8:29:13
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     *
     * @author zxy
     * @return
     * 2017年12月28日  下午8:29:15
     */
    public int getMerId() {
        return merId;
    }
    /**
     *
     * @author zxy
     * @param merId
     * 2017年12月28日  下午8:29:20
     */
    public void setMerId(int merId) {
        this.merId = merId;
    }
    /**
     *
     * @author zxy
     * @return
     * 2017年12月28日  下午8:29:23
     */
    public int getSuptId() {
        return suptId;
    }
    /**
     *
     * @author zxy
     * @param suptId
     * 2017年12月28日  下午8:29:25
     */
    public void setSuptId(int suptId) {
        this.suptId = suptId;
    }
    /**
     *
     * @author zxy
     * @return
     * 2017年12月28日  下午8:29:29
     */
    public int getNameTypeId() {
        return nameTypeId;
    }
    /**
     *
     * @author zxy
     * @param nameTypeId
     * 2017年12月28日  下午8:29:31
     */
    public void setNameTypeId(int nameTypeId) {
        this.nameTypeId = nameTypeId;
    }
    /**
     *
     * @author zxy
     * @return
     * 2017年12月28日  下午8:29:34
     */
    public int getSpecificationId() {
        return specificationId;
    }
    /**
     *
     * @author zxy
     * @param specificationId
     * 2017年12月28日  下午8:29:37
     */
    public void setSpecificationId(int specificationId) {
        this.specificationId = specificationId;
    }
    /**
     *
     * @author zxy
     * @return
     * 2017年12月28日  下午8:29:41
     */
    public int getRelease_supplierId() {
        return release_supplierId;
    }
    /**
     *
     * @author zxy
     * @return
     * 2017年12月28日  下午8:29:44
     */
    public int getMonth_tableId() {
        return month_tableId;
    }
    /**
     *
     * @author zxy
     * @param month_tableId
     * 2017年12月28日  下午8:29:47
     */
    public void setMonth_tableId(int month_tableId) {
        this.month_tableId = month_tableId;
    }
    /**
     *
     * @author zxy
     * @param release_supplierId
     * 2017年12月28日  下午8:29:50
     */
    public void setRelease_supplierId(int release_supplierId) {
        this.release_supplierId = release_supplierId;
    }
    /**
     *
     * @author zxy
     * @return
     * 2017年12月28日  下午8:29:57
     */
    public String getCommo() {
        return commo;
    }
    /**
     *
     * @author zxy
     * @param commo
     * 2017年12月28日  下午8:30:01
     */
    public void setCommo(String commo) {
        this.commo = commo;
    }
    /**
     *
     * @author zxy
     * @return
     * 2017年12月28日  下午8:31:16
     */
    public String getSupMerName() {
        return supMerName;
    }
    /**
     *
     * @author zxy
     * @param supMerName
     * 2017年12月28日  下午8:31:14
     */
    public void setSupMerName(String supMerName) {
        this.supMerName = supMerName;
    }
    /**
     *
     * @author zxy
     * @return
     * 2017年12月28日  下午8:31:09
     */
    public double getMoney() {
        return money;
    }
    /**
     *
     * @author zxy
     * @param money
     * 2017年12月28日  下午8:31:07
     */
    public void setMoney(double money) {
        this.money = money;
    }
    /**
     *
     * @author zxy
     * @return
     * 2017年12月28日  下午8:31:04
     */
    public String getDescribe() {
        return describe;
    }
    /**
     *
     * @author zxy
     * @param describe
     * 2017年12月28日  下午8:31:02
     */
    public void setDescribe(String describe) {
        this.describe = describe;
    }
    /**
     *
     * @author zxy
     * @return
     * 2017年12月28日  下午8:31:00
     */
    public int getNumber() {
        return number;
    }
    /**
     *
     * @author zxy
     * @param number
     * 2017年12月28日  下午8:30:56
     */
    public void setNumber(int number) {
        this.number = number;
    }
    /**
     *
     * @author zxy
     * @return
     * 2017年12月28日  下午8:30:53
     */
    public String getUpFrameTime() {
        return upFrameTime;
    }
    /**
     *
     * @author zxy
     * @param upFrameTime
     * 2017年12月28日  下午8:30:52
     */
    public void setUpFrameTime(String upFrameTime) {
        this.upFrameTime = upFrameTime;
    }
    /**
     *
     * @author zxy
     * @return
     * 2017年12月28日  下午8:30:49
     */
    public int getState() {
        return state;
    }
    /**
     *
     * @author zxy
     * @param state
     * 2017年12月28日  下午8:30:46
     */
    public void setState(int state) {
        this.state = state;
    }
    /**
     *
     * @author zxy
     * @return
     * 2017年12月28日  下午8:30:42
     */
    public String getOrigin() {
        return origin;
    }
    /**
     *
     * @author zxy
     * @param origin
     * 2017年12月28日  下午8:30:40
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }
    /**
     *
     * @author zxy
     * @return
     * 2017年12月28日  下午8:30:38
     */
    public String getNetContent() {
        return netContent;
    }
    /**
     *
     * @author zxy
     * @param netContent
     * 2017年12月28日  下午8:30:34
     */
    public void setNetContent(String netContent) {
        this.netContent = netContent;
    }
    /**
     *
     * @author zxy
     * @return
     * 2017年12月28日  下午8:30:27
     */
    public String getPackingMethod() {
        return packingMethod;
    }
    /**
     *
     * @author zxy
     * @param packingMethod
     * 2017年12月28日  下午8:30:26
     */
    public void setPackingMethod(String packingMethod) {
        this.packingMethod = packingMethod;
    }
    /**
     *
     * @author zxy
     * @return
     * 2017年12月28日  下午8:30:24
     */
    public String getBrand() {
        return brand;
    }
    /**
     *
     * @author zxy
     * @param brand
     * 2017年12月28日  下午8:30:22
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }
    /**
     *
     * @author zxy
     * @return
     * 2017年12月28日  下午8:30:20
     */
    public String getqGp() {
        return qGp;
    }
    /**
     *
     * @author zxy
     * @param qGp
     * 2017年12月28日  下午8:30:18
     */
    public void setqGp(String qGp) {
        this.qGp = qGp;
    }
    /**
     *
     * @author zxy
     * @return
     * 2017年12月28日  下午8:30:16
     */
    public String getTypeName() {
        return typeName;
    }
    /**
     *
     * @author zxy
     * @param typeName
     * 2017年12月28日  下午8:30:14
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    /**
     *
     * @author zxy
     * @return
     * 2017年12月28日  下午8:30:12
     */
    public String getStorageMethod() {
        return storageMethod;
    }
    /**
     *
     * @author zxy
     * @param storageMethod
     * 2017年12月28日  下午8:30:07
     */
    public void setStorageMethod(String storageMethod) {
        this.storageMethod = storageMethod;
    }
}
