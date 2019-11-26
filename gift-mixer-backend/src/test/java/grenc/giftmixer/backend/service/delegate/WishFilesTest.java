package grenc.giftmixer.backend.service.delegate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

public class WishFilesTest {
		
	private WishFiles wishFiles = new WishFiles();
	
	@Before
	public void setup() throws IOException {
		wishFiles.wishPath = new ClassPathResource("grenc/giftmixer/backend/service/wishes").getFile().getAbsolutePath();
	}
	
	@Test
	public void shouldReadAllNames() {
		List<String> allFiles = wishFiles.findAllWishFiles();
		assertEquals(2, allFiles.size());
		assertTrue(allFiles.contains("samo"));
		assertTrue(allFiles.contains("uros"));
	}
}
