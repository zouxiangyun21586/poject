package com.yr.pojo;
/**
 * 分页
 * 
 * @author 周业好
 * 2017年12月13日 下午9:33:03
 */
public class Paging {
	private Integer pageNow;//当前页
	private Integer pageCount; //总页数
	private static final Integer PAGE_SIZE = 5;//显示码
	private static final Integer PAGE_NUMBER = 10;//每页显示多少条数据
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
	/**
	 * 获取页码数量
	 * @return 页码数
	 */
	public static Integer getPageSize() {
		return PAGE_SIZE;
	}
	/**
	 * 获取每页显示多少条数据
	 * @return 每页显示数
	 */
	public static Integer getPageNumber() {
		return PAGE_NUMBER;
	}
	
}