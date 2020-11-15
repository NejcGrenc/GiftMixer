package grenc.giftmixer.backend.service.delegate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import grenc.giftmixer.backend.service.delegate.email.content.TargetGiftContentGenerator;

public class EmailContentGeneratorTest {
	
	private TargetGiftContentGenerator generator = new TargetGiftContentGenerator();
	
	@Test
	public void shouldGenerateSubject() {
		assertEquals("Skrivni Božiček!", generator.generateSubject());
	}

	@Test
	public void shouldGenerateContent() {
		String messageContent = generator.generateContent("Arti", "A big bone!");
		assertTrue(messageContent.contains("Arti"));
		assertTrue(messageContent.contains("A big bone!"));
	}
}
