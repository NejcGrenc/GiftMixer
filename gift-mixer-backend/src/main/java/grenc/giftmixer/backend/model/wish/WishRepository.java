package grenc.giftmixer.backend.model.wish;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {

	Optional<Wish> findByParticipantId(long participantId);
}
