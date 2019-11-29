package grenc.giftmixer.backend.model;

public class Pair {
	
	public Pair() {}
	public Pair(String receiver, String wisher) {
		this.receiver = receiver;
		this.wisher = wisher;
	}

	public String receiver;
	public String wisher;
	
	
	@Override
	public String toString() {
		return "Pair [receiver=" + receiver + ", wisher=" + wisher + "]";
	}
}
