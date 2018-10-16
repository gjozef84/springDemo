package pl.sda.javawwa9.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.javawwa9.dto.Adress;
import pl.sda.javawwa9.dto.Person;

/**
 * Created by Grzesiek on 2018-10-14
 */

@RestController
@RequestMapping("/api/")
public class PersonRestController {

    @GetMapping("person-as-rest")
    public Person getPerson(){

        Adress adress = new Adress("Warsaw", "Batalionu AK Karpaty", "5a/2");
        Person person = new Person("Grzesiek", "Jozefowicz");
        person.setAdress(adress);

        return person;
    }

}
