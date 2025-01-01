package be.ehb.eindproject.model;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepo extends CrudRepository<Users, Long> {
    Optional<Users> findByEmail(String email);

}