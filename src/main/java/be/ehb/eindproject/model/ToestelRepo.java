package be.ehb.eindproject.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ToestelRepo extends CrudRepository<Toestellen, Long> {

    //hibernate queries with functionnames
    List<Toestellen> findAllByCategory(String audio);
}