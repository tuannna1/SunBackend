package me.sunrise.repository;
import me.sunrise.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
    User findById(Long id);
    Collection<User> findAllByRole(String role);
    Page<User> findAll(Pageable pageable);

}
