package pl.sda.javawwa9.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.javawwa9.dto.Adress;
import pl.sda.javawwa9.dto.Person;
import pl.sda.javawwa9.services.AdressService;
import pl.sda.javawwa9.services.PersonService;

import java.util.List;
import java.util.Optional;

/**
 * Created by Grzesiek on 2018-10-13
 */

@Controller
public class PersonController {

    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);


    private AdressService adressService;
    private PersonService personService;

    /*@Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }*/

    @Autowired
    public PersonController(AdressService adressService, PersonService personService) {
        this.adressService = adressService;
        this.personService = personService;
    }

    @GetMapping("/person")
    public String showPersonData(Model model) {

        logger.info("showPersonData()");

        // 1 zapis
        Adress adress = new Adress("Warsaw", "Batalionu Ak Karpaty", "5a");
        Person toSave = new Person("Grzesiek", "Jozefowicz");
        toSave.setAdress(adress);

        logger.info("Person before save: {}", toSave); //logujemy Person przed zapisem do bazy

        personService.savePerson(toSave);



        /*adressDao.save(adress); //mozemy to ogarnac Cascade w adnotacji w Person
        personDao.save(toSave);*/

        logger.info("Person after save: {}", toSave); //logujemy Person po zapisie

        Optional<Person> maybePerson = personService.findPersonById(toSave.getId());
        maybePerson.ifPresent(person -> {
            logger.info("Person read from db: {}", person);
            model.addAttribute("data", person);
        });

        // 2 odczyt

        return "persons/person";
    }

    @GetMapping("/persons")
    public String showAllPersonData(Model model) {
        /*Adress myAdress = new Adress("Warsaw", "Batalionu Ak Karpaty", "5a");
        Adress myAdress1 = new Adress("Warsaw", "Batalionu Ak Karpaty", "3");
        Adress myAdress2 = new Adress("Warsaw", "Prosta", "33");

        Person me = new Person("Grzesiek", "Jozefowicz", myAdress);
        Person p1 = new Person("Iwona", "Zielinska", myAdress1);
        Person p2 = new Person("Zbigniew", "Kowalski", myAdress2);*/

        //List<Person> personList = new ArrayList<>();
        List<Person> personList = personService.findAllPerson();
        /*personList.add(me);
        personList.add(p1);
        personList.add(p2);*/

        model.addAttribute("personsList", personList);

        return "persons/persons";
    }

    @GetMapping("/persons/edit/{id}")
    public String editPerson(@PathVariable Long id, Model personToEdit) {
        logger.info("editPerson by id:{}", id);

        Optional<Person> personById = personService.findPersonById(id);
        personById.ifPresent(person -> {
            personToEdit.addAttribute("operationTitle", "Edit person id " + id);
            personToEdit.addAttribute("personToEdit", person);
        });

        return "persons/edit-person";
    }

    @PostMapping("/persons/save")
    public String savePerson(@ModelAttribute Person person) {
        logger.info("Saved person {}", person.getId());

        personService.savePerson(person);

        return "redirect:/persons/";
    }

    @GetMapping("/persons/add-person")
    public String addPerson(Model model) {
        logger.info("addPerson()");

        model.addAttribute("operationTitle", "add new Person");
        model.addAttribute("personToEdit", new Person());
        List<Adress> allAdresses = adressService.findAllAdresses();
        model.addAttribute("adresses", allAdresses);

        return "persons/edit-person";
    }

    @GetMapping("/persons/delete-person-confirmation/{id}")
    public String deletePersonConfirmation(@PathVariable Long id, Model model) {
        logger.info("deletePersonConfirmation() id:{}", id);

        Optional<Person> personToDeleteAsk = personService.findPersonById(id);
        personToDeleteAsk.ifPresent(person -> model.addAttribute("personToDelete", person));

        return "persons/delete-person-confirm";
    }

    @GetMapping("/persons/delete/{id}")
    public String deletePerson(@PathVariable Long id, Model model) {
        logger.info("deletePerson() id:{}", id);

        personService.deletePersonById(id);

        return "redirect:/persons/";
    }
}
