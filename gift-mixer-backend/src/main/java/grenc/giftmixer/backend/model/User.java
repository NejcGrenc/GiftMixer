package grenc.giftmixer.backend.model;

public class User {
	
	public User() {}
	public User(String name) {
		this.name = name;
	}
	
	public String name;
	public String securityCode;
	public String email;
	
	
	@Override
	public String toString() {
		return "User [name=" + name + ", securityCode=" + securityCode + ", email=" + email + "]";
	}
}
