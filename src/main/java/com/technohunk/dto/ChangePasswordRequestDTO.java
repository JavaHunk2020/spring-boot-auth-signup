package com.technohunk.dto;

public class ChangePasswordRequestDTO {
	
	private String newPassword;
	private String confirmPassword;
	private String email;
	private String code;

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "ChangePasswordRequestDTO [newPassword=" + newPassword + ", confirmPassword=" + confirmPassword
				+ ", email=" + email + ", code=" + code + "]";
	}

}
