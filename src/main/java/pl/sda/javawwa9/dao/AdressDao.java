package pl.sda.javawwa9.dao;

import org.springframework.data.repository.CrudRepository;
import pl.sda.javawwa9.dto.Adress;

/**
 * Created by Grzesiek on 2018-10-13
 */


public interface AdressDao extends CrudRepository<Adress, Long> {
}
