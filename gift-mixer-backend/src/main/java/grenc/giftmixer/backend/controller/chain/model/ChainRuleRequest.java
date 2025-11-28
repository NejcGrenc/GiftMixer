package grenc.giftmixer.backend.controller.chain.model;

import lombok.Data;

@Data
public class ChainRuleRequest {
    private long giverId;
    private long receiverId;
}
