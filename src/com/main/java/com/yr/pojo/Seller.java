package com.yr.pojo;
/**
 * @���� ��ˮ��
 * 2017��12��28������6:02:53
 */
public class Seller {
    private int wares_id; // ��ƷID
    private String name;  // ��Ʒ����
    private String money; // ��Ʒ�۸�
    private String describe;  // ��Ʒ����
    private int speciID;  // ��Ʒ���ID
    private int number;   // ��Ʒ����
    private String upFrameTime;   // ��Ʒ��Ϣ����ʱ��
    private int id;       // ���ҷ���ID
    private String origin; // ��ʳ���� 
    private String netContent; // ��ʳ������
    private String packingMethod; // ��װ��ʽ
    private String brand; // ��ƷƷ��
    private int qGP; // ��Ʒ������ID
    private String storageMethod; // ��Ʒ���ط�ʽ
    private int seller_id; // ����ID
    private String nameType; // ��Ʒ����
    private int auditStatus; // ������Ʒ״̬ 0�Ǳ��� 1��������� 2����˳ɹ�
    private String time; // ������Ʒ�ϼ�ʱ��
    private String downtime; // ������Ʒ�¼�ʱ��
    private int nameTypeID; // ��Ʒ����ID
    private String month; // ��ʳ������
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
