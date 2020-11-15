package grenc.giftmixer.backend.service.delegate.email.content;

import org.springframework.stereotype.Component;

@Component
public class WishInvitationContentGenerator {

	private static final String newLine = "<br />";
	
	private static final String subject = "Skrivni Božiček!";
	private static final String body = 
			"Pozdravljen/a, %s! <br />" +
			"<br />" +
			"Povabljen/a si na sodelovanje v Božičnem žrebu za igro Skrivni Božiček. <br />" +
			"Izpolni svojo željo na tem naslovu (klikni na povezavo): <br />" +
			"<br />" +
			"%s <br />" +
			"<br />" +
			"<br />" +
			"Skupna cena želja naj bo v okolici 50€. <br />" +
			"Prosim odpri to povezavo v Firefox ali Chromu na računalniku (ostalo morda ne bo delovalo pravilno)! <br />" +
			"Ko bodo vsi izpolnili svoje želje, bodo le-te naključno razdeljene med skrivne božičke. <br />" +
			"V primeru težav se obrni na svojega organizatorja/ico: %s (%s)";
	
	public String generateSubject() {
		return subject;
	}
	
	public String generateContent(String receiver, String linkToWish, String organizer, String organizerEmail) {
		StringBuffer content = new StringBuffer();
		content.append(String.format(body, receiver, linkToWish, organizer, organizerEmail));
		return content.toString();
	}
}
