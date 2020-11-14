package grenc.giftmixer.backend.model.user.participant;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {

	List<Participant> findByAdminId(long adminId);
}
