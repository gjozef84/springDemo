package pl.sda.javawwa9.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.javawwa9.dao.AdressDao;
import pl.sda.javawwa9.dto.Adress;
import pl.sda.javawwa9.services.AdressService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created by Grzesiek on 2018-10-14
 */

@Service
public class AdressServiceImpl implements AdressService {

    private static final Logger logger = LoggerFactory.getLogger(AdressServiceImpl.class);

    private AdressDao adressDao;

    @Autowired
    public AdressServiceImpl(AdressDao adressDao) {
        this.adressDao = adressDao;
    }

    @Override
    public List<Adress> findAllAdresses() {
        logger.info("findAllAdresses()");

        List<Adress> adressList = new ArrayList<>();
        adressDao.findAll().forEach(adress -> adressList.add(adress));

        return adressList;
    }

    @Override
    public Optional<Adress> findAdressById(Long id) {
        logger.info("findAdressById() with id: {}", id);

        Optional<Adress> result = adressDao.findById(id);
        result.ifPresent(adress -> logger.info("Adress read from dao: {}", adress));

        return result;
    }

    @Override
    public void saveAdress(Adress adress) {
        logger.info("Saved adress id:{}", adress.getId());
        adressDao.save(adress);
    }

    @Override
    public void deleteAdressById(Long id) {
        logger.info("Deleted adress by id:{}", id);
        adressDao.deleteById(id);
    }
}
