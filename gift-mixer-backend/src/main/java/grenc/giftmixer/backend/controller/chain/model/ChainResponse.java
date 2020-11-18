package grenc.giftmixer.backend.controller.chain.model;

import java.util.List;

import lombok.Data;

@Data
public class ChainResponse {

	private List<PairResponse> pairs;
	
	@Data
	public static class PairResponse {
		private long giverId;
		private String giverName;
		private long receiverId;
		private String receiverName;
	}
}
 