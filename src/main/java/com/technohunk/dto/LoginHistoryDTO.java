package com.technohunk.dto;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

public class LoginHistoryDTO {
	
	private int lhid;
	private Timestamp login_time;
	private Timestamp logout_time;
	private long duration;
	
	private SignupDTO signup;
	
	
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
	public int getLhid() {
		return lhid;
	}
	public void setLhid(int lhid) {
		this.lhid = lhid;
	}
	public Timestamp getLogin_time() {
		return login_time;
	}
	public void setLogin_time(Timestamp login_time) {
		this.login_time = login_time;
	}
	public Timestamp getLogout_time() {
		return logout_time;
	}
	public void setLogout_time(Timestamp logout_time) {
		this.logout_time = logout_time;
	}
	public SignupDTO getSignup() {
		return signup;
	}
	public void setSignup(SignupDTO signup) {
		this.signup = signup;
	}
	@Override
	public String toString() {
		return "LoginHistoryEntity [lhid=" + lhid + ", login_time=" + login_time + ", logout_time=" + logout_time
				+ ", signup=" + signup + "]";
	}
	

}
