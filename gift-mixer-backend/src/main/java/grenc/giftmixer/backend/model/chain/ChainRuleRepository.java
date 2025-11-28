package grenc.giftmixer.backend.model.chain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChainRuleRepository extends JpaRepository<ChainRule, Long> {

	List<ChainRule> findByAdminId(long adminId);
}
