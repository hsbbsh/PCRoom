package model.admin;

public class AdminDTO {
	private String adminId;
	private String adminName;
	private String adminGender;
	private String adminPhone;
	private String adminBirth;
	private String adminAuthority;
	private String adminPw;
	
	public AdminDTO() {}
	
	public AdminDTO(String adminId, String adminName, String adminGender, String adminPhone, 
			String adminBirth, String adminAuthority, String adminCleanArea, String adminPw) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminGender = adminGender;
		this.adminPhone = adminPhone;
		this.adminAuthority = adminAuthority;
		this.adminBirth = adminBirth;
		this.adminPw = adminPw;
	}
	
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminGender() {
		return adminGender;
	}
	public void setAdminGender(String adminGender) {
		this.adminGender = adminGender;
	}
	public String getAdminPhone() {
		return adminPhone;
	}
	public void setAdminPhone(String adminPhone) {
		this.adminPhone = adminPhone;
	}
	public String getAdminBirth() {
		return adminBirth;
	}
	public void setAdminBirth(String adminBirth) {
		this.adminBirth = adminBirth;
	}
	public String getAdminAuthority() {
		return adminAuthority;
	}
	public void setAdminAuthority(String adminAuthority) {
		this.adminAuthority = adminAuthority;
	}
	public String getAdminPw() {
		return adminPw;
	}
	public void setAdminPw(String adminPw) {
		this.adminPw = adminPw;
	}
	
	
}
