package pl.sda.javawwa9.services;

import pl.sda.javawwa9.dto.Person;

import java.util.List;
import java.util.Optional;

/**
 * Created by Grzesiek on 2018-10-13
 */

public interface PersonService {

        void savePerson(Person personToSave);

    Optional<Person> findPersonById(Long id);

    List<Person> findAllPerson();

    void deletePersonById(Long id);
}
