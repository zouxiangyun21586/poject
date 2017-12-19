package com.yr.pojo;

public class Mune {
	private Integer id;//菜单表id
	private Integer muneId;//菜单表级联id
	private String fatherName;//菜单名字
	private Integer state;//状态  修改前
	private String staStr;//状态   修改后
	
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMuneId() {
		return muneId;
	}
	public void setMuneId(Integer muneId) {
		this.muneId = muneId;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	
}
