package pl.sda.javawwa9.dao;

import org.springframework.data.repository.CrudRepository;
import pl.sda.javawwa9.dto.Person;

/**
 * Created by Grzesiek on 2018-10-13
 */

public interface PersonDao extends CrudRepository<Person, Long> {

}
