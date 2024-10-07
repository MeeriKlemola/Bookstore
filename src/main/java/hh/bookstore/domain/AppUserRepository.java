package hh.bookstore.domain;

import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {
    org.springframework.security.core.userdetails.User findByUsername(String username);
}