package com.yr.pojo;

import java.sql.Date;

/**
 * 
 * 2017
 * @author zxy
 * Administrator
 * 2017��12��28�� ����8:23:41
 */
public class Supplie {
    /**
     * ��Ӧ��Id.
     */
    private int id;
    /**
     * ��ƷId.
     */
    private int merId;
    /**
     *  ��Ӧ����Ʒid.
     */
    private int suptId;
    /**
     * ��Ʒ����id.
     */
    private int nameTypeId;
    /**
     * ��Ʒ���id.
     */
    private int specificationId;
    /**
     * ��Ʒ������·�id.
     */
    private int month_tableId;
    /**
     * ��Ӧ�̷�����id.
     */
    private int release_supplierId; 
    /**
     * ��Ʒ����.
     */
    private String commo;
    /**
     * ��Ӧ����Ʒ��.
     */
    private String supMerName;
    /**
     * ��Ʒ�۸�.
     */
    private double money;
    /**
     *  ��Ʒ����.
     */
    private String describe;
    /**
     * ��Ʒ����.
     */
    private int number;
    /**
     * ��Ʒ�ϼ�ʱ��.
     */
    private String upFrameTime;
    /**
     * ��Ʒ״̬.
     */
    private int state; 
    /**
     * ��Ʒ����.
     */
    private String origin; 
    /**
     * ��Ʒ������.
     */
    private String netContent;
    /**
     * ��Ʒ��װ.
     */
    private String packingMethod;
    /**
     * ��ƷƷ��.
     */
    private String brand;
    /**
     * ��Ʒ������.
     */
    private String qGp;
    /**
     * ��Ʒ������.
     */
    private String typeName;
    /**
     * ��Ʒ���ط���.
     */
    private String storageMethod;
    /**
     * �ж��Ƿ�ͨ�����,�����Ƿ���/�¼�
     */
    private String auditStatus;
    /**
     * 
     * @author zxy
     * @return
     * 2018��1��2��  ����5:04:49
     */
    public String getAuditStatus() {
        return auditStatus;
    }
    /**
     * 
     * @author zxy
     * @param auditStatus
     * 2018��1��2��  ����5:04:52
     */
    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }
    /**
     *
     * @author zxy
     * @return
     * 2017��12��28��  ����8:29:01
     */
    public int getId() {
        return id;
    }
    /**
     *
     * @author zxy
     * @param id
     * 2017��12��28��  ����8:29:13
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     *
     * @author zxy
     * @return
     * 2017��12��28��  ����8:29:15
     */
    public int getMerId() {
        return merId;
    }
    /**
     *
     * @author zxy
     * @param merId
     * 2017��12��28��  ����8:29:20
     */
    public void setMerId(int merId) {
        this.merId = merId;
    }
    /**
     *
     * @author zxy
     * @return
     * 2017��12��28��  ����8:29:23
     */
    public int getSuptId() {
        return suptId;
    }
    /**
     *
     * @author zxy
     * @param suptId
     * 2017��12��28��  ����8:29:25
     */
    public void setSuptId(int suptId) {
        this.suptId = suptId;
    }
    /**
     *
     * @author zxy
     * @return
     * 2017��12��28��  ����8:29:29
     */
    public int getNameTypeId() {
        return nameTypeId;
    }
    /**
     *
     * @author zxy
     * @param nameTypeId
     * 2017��12��28��  ����8:29:31
     */
    public void setNameTypeId(int nameTypeId) {
        this.nameTypeId = nameTypeId;
    }
    /**
     *
     * @author zxy
     * @return
     * 2017��12��28��  ����8:29:34
     */
    public int getSpecificationId() {
        return specificationId;
    }
    /**
     *
     * @author zxy
     * @param specificationId
     * 2017��12��28��  ����8:29:37
     */
    public void setSpecificationId(int specificationId) {
        this.specificationId = specificationId;
    }
    /**
     *
     * @author zxy
     * @return
     * 2017��12��28��  ����8:29:41
     */
    public int getRelease_supplierId() {
        return release_supplierId;
    }
    /**
     *
     * @author zxy
     * @return
     * 2017��12��28��  ����8:29:44
     */
    public int getMonth_tableId() {
        return month_tableId;
    }
    /**
     *
     * @author zxy
     * @param month_tableId
     * 2017��12��28��  ����8:29:47
     */
    public void setMonth_tableId(int month_tableId) {
        this.month_tableId = month_tableId;
    }
    /**
     *
     * @author zxy
     * @param release_supplierId
     * 2017��12��28��  ����8:29:50
     */
    public void setRelease_supplierId(int release_supplierId) {
        this.release_supplierId = release_supplierId;
    }
    /**
     *
     * @author zxy
     * @return
     * 2017��12��28��  ����8:29:57
     */
    public String getCommo() {
        return commo;
    }
    /**
     *
     * @author zxy
     * @param commo
     * 2017��12��28��  ����8:30:01
     */
    public void setCommo(String commo) {
        this.commo = commo;
    }
    /**
     *
     * @author zxy
     * @return
     * 2017��12��28��  ����8:31:16
     */
    public String getSupMerName() {
        return supMerName;
    }
    /**
     *
     * @author zxy
     * @param supMerName
     * 2017��12��28��  ����8:31:14
     */
    public void setSupMerName(String supMerName) {
        this.supMerName = supMerName;
    }
    /**
     *
     * @author zxy
     * @return
     * 2017��12��28��  ����8:31:09
     */
    public double getMoney() {
        return money;
    }
    /**
     *
     * @author zxy
     * @param money
     * 2017��12��28��  ����8:31:07
     */
    public void setMoney(double money) {
        this.money = money;
    }
    /**
     *
     * @author zxy
     * @return
     * 2017��12��28��  ����8:31:04
     */
    public String getDescribe() {
        return describe;
    }
    /**
     *
     * @author zxy
     * @param describe
     * 2017��12��28��  ����8:31:02
     */
    public void setDescribe(String describe) {
        this.describe = describe;
    }
    /**
     *
     * @author zxy
     * @return
     * 2017��12��28��  ����8:31:00
     */
    public int getNumber() {
        return number;
    }
    /**
     *
     * @author zxy
     * @param number
     * 2017��12��28��  ����8:30:56
     */
    public void setNumber(int number) {
        this.number = number;
    }
    /**
     *
     * @author zxy
     * @return
     * 2017��12��28��  ����8:30:53
     */
    public String getUpFrameTime() {
        return upFrameTime;
    }
    /**
     *
     * @author zxy
     * @param upFrameTime
     * 2017��12��28��  ����8:30:52
     */
    public void setUpFrameTime(String upFrameTime) {
        this.upFrameTime = upFrameTime;
    }
    /**
     *
     * @author zxy
     * @return
     * 2017��12��28��  ����8:30:49
     */
    public int getState() {
        return state;
    }
    /**
     *
     * @author zxy
     * @param state
     * 2017��12��28��  ����8:30:46
     */
    public void setState(int state) {
        this.state = state;
    }
    /**
     *
     * @author zxy
     * @return
     * 2017��12��28��  ����8:30:42
     */
    public String getOrigin() {
        return origin;
    }
    /**
     *
     * @author zxy
     * @param origin
     * 2017��12��28��  ����8:30:40
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }
    /**
     *
     * @author zxy
     * @return
     * 2017��12��28��  ����8:30:38
     */
    public String getNetContent() {
        return netContent;
    }
    /**
     *
     * @author zxy
     * @param netContent
     * 2017��12��28��  ����8:30:34
     */
    public void setNetContent(String netContent) {
        this.netContent = netContent;
    }
    /**
     *
     * @author zxy
     * @return
     * 2017��12��28��  ����8:30:27
     */
    public String getPackingMethod() {
        return packingMethod;
    }
    /**
     *
     * @author zxy
     * @param packingMethod
     * 2017��12��28��  ����8:30:26
     */
    public void setPackingMethod(String packingMethod) {
        this.packingMethod = packingMethod;
    }
    /**
     *
     * @author zxy
     * @return
     * 2017��12��28��  ����8:30:24
     */
    public String getBrand() {
        return brand;
    }
    /**
     *
     * @author zxy
     * @param brand
     * 2017��12��28��  ����8:30:22
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }
    /**
     *
     * @author zxy
     * @return
     * 2017��12��28��  ����8:30:20
     */
    public String getqGp() {
        return qGp;
    }
    /**
     *
     * @author zxy
     * @param qGp
     * 2017��12��28��  ����8:30:18
     */
    public void setqGp(String qGp) {
        this.qGp = qGp;
    }
    /**
     *
     * @author zxy
     * @return
     * 2017��12��28��  ����8:30:16
     */
    public String getTypeName() {
        return typeName;
    }
    /**
     *
     * @author zxy
     * @param typeName
     * 2017��12��28��  ����8:30:14
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    /**
     *
     * @author zxy
     * @return
     * 2017��12��28��  ����8:30:12
     */
    public String getStorageMethod() {
        return storageMethod;
    }
    /**
     *
     * @author zxy
     * @param storageMethod
     * 2017��12��28��  ����8:30:07
     */
    public void setStorageMethod(String storageMethod) {
        this.storageMethod = storageMethod;
    }
}
