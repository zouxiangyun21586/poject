package com.yr.pojo;

public class Order {
	/**
	 * id
	 */
	private Integer id;
	/**
	 * ��Ʒ����ID
	 */
	private Integer wares_id;
	/**
	 * ������(ʹ�������)
	 */
	private String orderNo;
	/**
	 * ����ʱ��(����ʱ��)
	 */
	private String orderTime;
	/**
	 * ��������
	 */
	private Integer orderNumber;
	/**
	 * ��ҵ�ַ
	 */
	private String buyersAddr;
	/**
	 * ��ҵ绰
	 */
	private String buyersTell;
	/**
	 * �������
	 */
	private String buyersName;
	/**
	 * ������Ϣ(��Ʒ����id)
	 */
	private Integer specificationId;
	/**
	 * ��������
	 */
	private String orderMessage;
	/**
	 * ����ʧЧ(Ĭ��ȡ��)����״̬
	 */
	private String orderlapse;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getWares_id() {
		return wares_id;
	}
	public void setWares_id(Integer wares_id) {
		this.wares_id = wares_id;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public Integer getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getBuyersAddr() {
		return buyersAddr;
	}
	public void setBuyersAddr(String buyersAddr) {
		this.buyersAddr = buyersAddr;
	}
	public String getBuyersTell() {
		return buyersTell;
	}
	public void setBuyersTell(String buyersTell) {
		this.buyersTell = buyersTell;
	}
	public String getBuyersName() {
		return buyersName;
	}
	public void setBuyersName(String buyersName) {
		this.buyersName = buyersName;
	}
	public Integer getSpecificationId() {
		return specificationId;
	}
	public void setSpecificationId(Integer specificationId) {
		this.specificationId = specificationId;
	}
	public String getOrderMessage() {
		return orderMessage;
	}
	public void setOrderMessage(String orderMessage) {
		this.orderMessage = orderMessage;
	}
	public String getOrderlapse() {
		return orderlapse;
	}
	public void setOrderlapse(String orderlapse) {
		this.orderlapse = orderlapse;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", wares_id=" + wares_id + ", orderNo=" + orderNo + ", orderTime=" + orderTime
				+ ", orderNumber=" + orderNumber + ", buyersAddr=" + buyersAddr + ", buyersTell=" + buyersTell
				+ ", buyersName=" + buyersName + ", specificationId=" + specificationId + ", orderMessage="
				+ orderMessage + ", orderlapse=" + orderlapse + "]";
	}
	
}
