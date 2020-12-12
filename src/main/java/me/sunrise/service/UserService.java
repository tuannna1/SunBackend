package me.sunrise.service;



import me.sunrise.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface UserService {
    User findOne(String email);

    Collection<User> findByRole(String role);

    User save(User user);

    User update(User user);

    void delete(Long id);

    Page<User> findAll(Pageable pageable);


}
