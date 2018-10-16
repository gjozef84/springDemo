package pl.sda.javawwa9.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.javawwa9.dao.AdressDao;
import pl.sda.javawwa9.dao.PersonDao;
import pl.sda.javawwa9.dto.Person;
import pl.sda.javawwa9.services.PersonService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Grzesiek on 2018-10-13
 */

@Service
public class PersonServiceImpl implements PersonService {

    private static final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

    private PersonDao personDao;
    private AdressDao adressDao; //powinno byc w oddzielnym service

    @Autowired
    public PersonServiceImpl(PersonDao personDao, AdressDao adressDao){
        this.personDao = personDao;
        this.adressDao = adressDao;
    }

    @Override
    public void savePerson(Person personToSave) {
        logger.info("savePerson() with argument: {}", personToSave);
        personDao.save(personToSave);
    }

    @Override
    public Optional<Person> findPersonById(Long personId) {
        logger.info("Trying to find person of id: {}", personId);

        return personDao.findById(personId);
    }

    @Override
    public List<Person> findAllPerson() {
        logger.info("findAllPerson()");
        List<Person> personList = new ArrayList<>();

        personDao.findAll().forEach(person -> personList.add(person));
        return personList;
    }

    @Override
    public void deletePersonById(Long id) {
        logger.info("Deleted person by id:{}", id);

        personDao.deleteById(id);
    }
}
