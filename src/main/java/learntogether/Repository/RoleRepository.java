package learntogether.Repository;

import learntogether.Entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
  Created by Luvbert
*/
@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    public RoleEntity findByRoleName(String roleName);
    public RoleEntity findById(Long id);
}
