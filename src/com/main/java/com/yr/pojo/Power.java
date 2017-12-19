package com.yr.pojo;

/**
 * 权限表实体类
 * @author 周业好
 * 2017年12月19日 下午9:35:00
 */
public class Power {
	private Integer id;//权限id
	private String name;//菜单名字
	private String url;//菜单url
	private Integer state;//状态  改变前
	private String staStr;//状态  改变后
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getStaStr() {
		return staStr;
	}
	public void setStaStr(String staStr) {
		this.staStr = staStr;
	}
}
