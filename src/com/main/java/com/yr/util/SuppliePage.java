package com.yr.util;

public class SuppliePage {
	/**
	 * ��ʾҳ����
	 * 
	 * @author zxy
	 * @param pageNowStr ��ǰҳ
	 * @param pageCountStr ��ҳ��
	 * @return
	 */
	public String getPageCode(Integer pageNowStr, Integer pageCountStr) {
		StringBuffer sb = new StringBuffer();
		int pageNow = pageNowStr;// ��ǰ
		int pageCount = pageCountStr;// �ܵ�
		int pageSize = 5;// ��ʾ��ҳ������
		// ������ҳʱ,������ҳ��С��ҳ����
		if (pageNow < 0) {
			pageNow = 1;
		}
		if (pageNow == 1) {// ��ǰҳ==1��ʱ������
			if (pageCount < pageSize && pageNow != pageCount) {// ����ҳ��С����ʾҳ����ʱ�����
				for (int i = 1; i <= pageCount; i++) {
					sb.append(i).append(",");
				}
			} else if (pageNow == pageCount) {
				sb.append(1).append(",");
			} else if (pageCount == pageSize) {// ����ҳ��������ʾҳ����ʱ�����
				for (int i = 1; i <= pageSize; i++) {
					sb.append(i).append(",");
				}
			} else {// ����ҳ��������ʾҳ�����ǵ����
				for (int i = pageNow; i < pageNow + pageSize; i++) {
					sb.append(i).append(",");
				}
			}
		} else if (pageNow >= (pageCount - pageSize) + 1) {// ��ҳ��-��ʾҳ��+1 =
															// ���Ҫ��ʾ��ҳ����
															// �� ��ǰҳ���ڵ���
															// ���Ҫ��ʾ��ҳ���� ʱ�����
			for (int i = pageCount - pageSize + 1; i <= pageCount; i++) {
				if (i > 0) {
					sb.append(i).append(",");
				}
			}
		} else {// �������
			for (int i = pageNow; i < pageNow + pageSize; i++) {
				sb.append(i).append(",");
			}
		}
		return sb.toString();
	}
}
