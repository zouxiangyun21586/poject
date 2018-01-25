package com.yr.util;

public class SuppliePage {
	/**
	 * 显示页码数
	 * 
	 * @author zxy
	 * @param pageNowStr 当前页
	 * @param pageCountStr 总页数
	 * @return
	 */
	public String getPageCode(Integer pageNowStr, Integer pageCountStr) {
		StringBuffer sb = new StringBuffer();
		int pageNow = pageNowStr;// 当前
		int pageCount = pageCountStr;// 总的
		int pageSize = 5;// 显示的页码数量
		// 当是首页时,并且总页面小于页码数
		if (pageNow < 0) {
			pageNow = 1;
		}
		if (pageNow == 1) {// 当前页==1的时候的情况
			if (pageCount < pageSize && pageNow != pageCount) {// 当总页数小于显示页码数时的情况
				for (int i = 1; i <= pageCount; i++) {
					sb.append(i).append(",");
				}
			} else if (pageNow == pageCount) {
				sb.append(1).append(",");
			} else if (pageCount == pageSize) {// 当总页数等于显示页码数时的情况
				for (int i = 1; i <= pageSize; i++) {
					sb.append(i).append(",");
				}
			} else {// 当总页数大于显示页码数是的情况
				for (int i = pageNow; i < pageNow + pageSize; i++) {
					sb.append(i).append(",");
				}
			}
		} else if (pageNow >= (pageCount - pageSize) + 1) {// 总页数-显示页数+1 =
															// 最后要显示的页码数
															// 当 当前页大于等于
															// 最后要显示的页码数 时的情况
			for (int i = pageCount - pageSize + 1; i <= pageCount; i++) {
				if (i > 0) {
					sb.append(i).append(",");
				}
			}
		} else {// 正常情况
			for (int i = pageNow; i < pageNow + pageSize; i++) {
				sb.append(i).append(",");
			}
		}
		return sb.toString();
	}
}
