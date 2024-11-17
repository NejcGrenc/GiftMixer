package grenc.giftmixer.backend.controller.email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateResponse {
  public String subject;
  public String content;
}
