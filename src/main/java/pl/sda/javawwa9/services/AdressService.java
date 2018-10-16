package pl.sda.javawwa9.services;

import pl.sda.javawwa9.dto.Adress;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Grzesiek on 2018-10-14
 */
public interface AdressService {

    List<Adress> findAllAdresses();

    Optional<Adress> findAdressById(Long id);

    void saveAdress(Adress adress);

    void deleteAdressById(Long id);
}
