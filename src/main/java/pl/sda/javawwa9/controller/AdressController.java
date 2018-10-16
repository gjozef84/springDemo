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
import pl.sda.javawwa9.services.AdressService;

import java.util.List;
import java.util.Optional;

/**
 * Created by Grzesiek on 2018-10-14
 */

@Controller
public class AdressController {

    private static final Logger logger = LoggerFactory.getLogger(AdressController.class);

    private AdressService adressService;

    @Autowired
    public AdressController(AdressService adressService) {
        this.adressService = adressService;
    }

    @GetMapping("/all-addresses")
    public String showAllAddresses(Model model) {
        logger.info("showAllAdress()");
        List<Adress> allAdr = adressService.findAllAdresses();
        model.addAttribute("adresses", allAdr);

        return "adresses/all";
    }

    @GetMapping("/address/edit/{id}")
    public String editAdress(@PathVariable Long id, Model model) {
        logger.info("editAdress(), id: {}", id);

        Optional<Adress> adressEdit = adressService.findAdressById(id);

        adressEdit.ifPresent(adress -> {
            model.addAttribute("adressToEdit", adress);
            model.addAttribute("operationTitle", "Edit adress id " + id);
        });

        return "adresses/edit-adress";
    }

    @PostMapping("/address/save")
    public String saveAdress(@ModelAttribute Adress adress) {
        logger.info("saveAdress() id:{}", adress);

        adressService.saveAdress(adress);

        return "redirect:/all-addresses";
    }

    @GetMapping("/address/add-adress")
    public String addAdress(Model newAdress) {
        logger.info("addAdress()");

        newAdress.addAttribute("operationTitle", "add new Adress");
        newAdress.addAttribute("adressToEdit", new Adress());

        return "adresses/edit-adress";
    }

    @GetMapping("/address/delete/{id}")
    public String deleteAdress(@PathVariable Long id, Model model) {
        logger.info("deleteAdress(), id: {}", id);

        adressService.deleteAdressById(id);

        return "redirect:/all-addresses";
    }

    @GetMapping("/address/delete-confirmation/{id}")
    public String deleteAdressConfirmation(@PathVariable Long id, Model model) {
        logger.info("deleteAdressConfirmation() id:{}", id);

        Optional<Adress> adressToDeleteAsk = adressService.findAdressById(id);
        adressToDeleteAsk.ifPresent(adress -> model.addAttribute("adressToDelete", adress));

        return "adresses/delete-confirm";
    }
}