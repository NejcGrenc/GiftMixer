package grenc.giftmixer.backend.model.chain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiverRecieverPairRepository extends JpaRepository<GiverRecieverPair, Long> {

}
