package com.project.page.model;

import java.util.Date;

public class Member {
    private String userId;  
    private String pwd;  
    private String name;  
    private String email;  
    private String phone;  
    private Date regDt;                     
    private Date mdfDt;
    
    
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getRegDt() {
		return regDt;
	}
	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}
	public Date getMdfDt() {
		return mdfDt;
	}
	public void setMdfDt(Date mdfDt) {
		this.mdfDt = mdfDt;
	}                     
    
    
	 
}
