package grenc.giftmixer.backend.service.delegate;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EmailContentGeneratorTest {
	
	private EmailContentGenerator generator = new EmailContentGenerator();
	
	@Test
	public void shouldGenerateSubject() {
		assertEquals("Skrivni Božiček!", generator.generateSubject());
	}

	@Test
	public void shouldGenerateContent() {
		assertEquals("\nIzbran/a si bil/a kot skrivni božiček za osebo: Arti!"
					+ "\n\nTa oseba si je za Božič zaželela:"
					+ "\nA big bone!", 
					generator.generateContent("Arti", "A big bone!"));
	}
}
