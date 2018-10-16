package pl.sda.javawwa9.dto;

import javax.persistence.*;

/**
 * Created by Grzesiek on 2018-10-13
 */

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@GeneratedValue
    private Long id;
    private String name;
    private String surname;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Adress adress;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Person() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person(String name, String surname, Adress adress) {
        this.name = name;
        this.surname = surname;
        this.adress = adress;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", adress=" + adress +
                '}';
    }
}
