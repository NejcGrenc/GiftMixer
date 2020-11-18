package grenc.giftmixer.backend.service.email.content;

import org.springframework.stereotype.Component;

@Component
public class VerificationContentGenerator {

	private static final String newLine = "<br />";
	
	private static final String subject = "Skrivni Božiček!";
	private static final String body = 
				"Pozdravljen/a, %s! <br />" +
				"<br />" + 
				"Organizator/ka: %s (%s) <br />" +
				"te je povabil/a na sodelovanje v igri Skrivni Božiček. <br />" +
				"<br />" + 
				"Če poznaš to osebo in želiš sodelovati v igri, prosimo klikni na naslednjo povezavo <br />" +
				"in s tem potrdi svoje sodelovanje in svoj e-poštni naslov. <br />" +
				"<br />" + 
				"%s <br />" +
				"<br />" + 
				"Po potrditvi, prosimo počakaj na nadaljna navodila, ki bodo prišla na ta e-poštni nalov. <br />" + 
				"<br />" + 
				"V kolikor imaš dodatna vprašanja ali pa želis spremeniti svoje ime ali e-poštni naslov <br />" +
				"prosimo piši organizatorju/ki na naslov (%s).";

	
	public String generateSubject() {
		return subject;
	}
	
	public String generateContent(String receiver, String verificationLink, String organizer, String organizerEmail) {
		StringBuffer content = new StringBuffer();
		content.append(String.format(body, receiver, organizer, organizerEmail, verificationLink, organizerEmail));
		return content.toString();
	}
}
