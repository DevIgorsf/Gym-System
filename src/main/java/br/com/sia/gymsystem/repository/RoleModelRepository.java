package br.com.sia.gymsystem.repository;

import br.com.sia.gymsystem.enums.RoleName;
import br.com.sia.gymsystem.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleModelRepository extends JpaRepository<RoleModel, Long> {
    Optional<RoleModel> findByRoleName(RoleName roleName);
}
