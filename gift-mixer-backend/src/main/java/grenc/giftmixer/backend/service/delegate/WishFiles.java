package grenc.giftmixer.backend.service.delegate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WishFiles {

	@Value("${storage.wish.folder}")
	String wishPath;
	
	public List<String> findAllWishFiles() {
		try (Stream<Path> walk = Files.walk(Paths.get(wishPath))) {
			List<String> result = walk.filter(Files::isRegularFile)
					.map(x -> x.getFileName().toString())
					.map(x -> removeSuffix(x))
					.collect(Collectors.toList());
			return result;
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private String removeSuffix(String original) {
		return original.substring(0, original.indexOf(".txt"));
	}
}
