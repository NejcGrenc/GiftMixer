package grenc.giftmixer.backend.service.email.content;

import org.springframework.stereotype.Component;

@Component
public class TargetGiftContentGenerator {

	private static final String newLine = "<br />";
	
	private static final String subject = "Skrivni Božiček!";
	private static final String contentPreword = "Izbran/a si bil/a kot skrivni božiček za osebo: %s!" + newLine + "(Okvirna cena daril je v okolici 50€)";
	private static final String contentMain = "Ta oseba si je za Božič zaželela:";
	
	private static final String letterCore = "<h3>Dragi Božiček!</h3>" + 
			"			<br />" + 
			"			<p>Za Božič si letos želim:</p>" + 
			"			%s" +
			"			<br />" +
			"           <p>Ter še kakšno manjše presenečenje!</p>" + 
			"           <br />" + 
			"			<p>Z najlepšimi željami, %s</p>";

	
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
		content.append(String.format(letterCore, wishContent, receiver));
		content.append(newLine);
		
		return content.toString();
	}
}
