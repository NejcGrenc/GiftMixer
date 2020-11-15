package grenc.giftmixer.backend.model.chain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChainRepository extends JpaRepository<Chain, Long> {

	List<Chain> findByAdminId(long adminId);
}
