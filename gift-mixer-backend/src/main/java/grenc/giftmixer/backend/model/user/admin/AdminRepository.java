package grenc.giftmixer.backend.model.user.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

	List<Admin> findByUsername(String username);
}
