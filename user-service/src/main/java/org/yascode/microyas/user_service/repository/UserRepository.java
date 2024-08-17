package org.yascode.microyas.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yascode.microyas.user_service.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
