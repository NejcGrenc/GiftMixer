package grenc.giftmixer.backend.service.delegate;

import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class ParticipantCodeService {
  public String generateParticipantCode() {
    String uuid = UUID.randomUUID().toString();
    return uuid.replaceAll("-", "");
  }
}
