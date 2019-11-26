package grenc.giftmixer.backend.model;

public class UserVerificationRequest {
	
	public UserVerificationRequest() {}
	public UserVerificationRequest(String userName, String userCode) {
		this.userName = userName;
		this.userCode = userCode;
	}

	public String userName;
	public String userCode;
}
