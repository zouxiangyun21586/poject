package com.yr.pojo;
/**
 * ��ҳ
 * 
 * @author ��ҵ��
 * 2017��12��13�� ����9:33:03
 */
public class Paging {
	private Integer pageNow;//��ǰҳ
	private Integer pageCount; //��ҳ��
	private static final Integer PAGE_SIZE = 5;//��ʾ��
	private static final Integer PAGE_NUMBER = 10;//ÿҳ��ʾ����������
	public Integer getPageNow() {
		return pageNow;
	}
	public void setPageNow(Integer pageNow) {
		this.pageNow = pageNow;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public static Integer getPageSize() {
		return PAGE_SIZE;
	}
	public static Integer getPageNumber() {
		return PAGE_NUMBER;
	}
	
}