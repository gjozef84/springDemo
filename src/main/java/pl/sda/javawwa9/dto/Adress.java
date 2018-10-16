package pl.sda.javawwa9.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Grzesiek on 2018-10-13
 */

@Entity
public class Adress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    private String city;
    private String street;
    private String strNumber;

    public Adress(String city, String street, String strNumber) {
        this.city = city;
        this.street = street;
        this.strNumber = strNumber;
    }

    public Adress() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStrNumber() {
        return strNumber;
    }

    public void setStrNumber(String strNumber) {
        this.strNumber = strNumber;
    }

    @Override
    public String toString() {
        return city + ' ' + street + ' '+ strNumber;
    }
}
