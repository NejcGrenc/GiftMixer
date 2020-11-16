package grenc.giftmixer.backend.controller.wish.model;

import lombok.Data;

@Data
public class WishRequest {
	
	private long participantId;
	private String wishContent;
}
