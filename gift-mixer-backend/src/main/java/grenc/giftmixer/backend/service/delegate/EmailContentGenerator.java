package grenc.giftmixer.backend.service.delegate;

import org.springframework.stereotype.Component;

@Component
public class EmailContentGenerator {

	private static final String subject = "Skrivni Božiček!";
	private static final String contentPreword = "Izbran/a si bil/a kot skrivni božiček za osebo: %s!";
	private static final String contentMain = "Ta oseba si je za Božič zaželela:";

	private static final String newLine = "<br />";
	
	public String generateSubject() {
		return subject;
	}
	
	public String generateContent(String receiver, String wishContent) {
		StringBuffer content = new StringBuffer();
		
		content.append(newLine);
		content.append(String.format(contentPreword, receiver));
		content.append(newLine);
		content.append(newLine);
		content.append(contentMain);
		content.append(newLine);
		content.append(wishContent);

		return content.toString();
	}
}
