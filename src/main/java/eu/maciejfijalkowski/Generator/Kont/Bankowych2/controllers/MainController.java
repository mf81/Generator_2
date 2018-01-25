package eu.maciejfijalkowski.Generator.Kont.Bankowych2.controllers;

import eu.maciejfijalkowski.Generator.Kont.Bankowych2.models.FinalBankAcount;
import eu.maciejfijalkowski.Generator.Kont.Bankowych2.models.services.BankDropListCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    @Autowired
    BankDropListCollection bankDropListCollection;

    @GetMapping("/")
    public String index (ModelMap modelMap, BankDropListCollection bankDropListCollection){
        modelMap.addAttribute("bankDropListCollection",bankDropListCollection.getBankList());
        return "index";
    }

    @PostMapping("/generate")
    public String generate(ModelMap modelMap, FinalBankAcount finalBankAcount){
        //modelMap.addAttribute("admDropListCollection",admDropListCollection.getAdmy());
        modelMap.addAttribute("admDropListCollection", CsvReader.CsvReader("src/main/resources/adm.csv").entrySet());
        modelMap.addAttribute("acountDropListCollection",CsvReader.CsvReader("src/main/resources/type.csv").entrySet());
        if (finalBankAcount.getBankNumber().equals("1320 0022"))
            modelMap.addAttribute("swrkDropListCollection",CsvReader.CsvReader("src/main/resources/swrkPocztowy.csv").entrySet());
        else
            if (finalBankAcount.getBankNumber().equals("1020 4274"))
                modelMap.addAttribute("swrkDropListCollection",CsvReader.CsvReader("src/main/resources/swrkPKO.csv").entrySet());
            else
                modelMap.addAttribute("swrkDropListCollection",CsvReader.CsvReader("src/main/resources/swrkNord.csv").entrySet());

        modelMap.addAttribute("finalBankAcount",finalBankAcount);
        System.out.println("Obiekt " + finalBankAcount.getName()+finalBankAcount.getBankNumber());
        return "generate";
    }

    @PostMapping("/finalresault")
    public String finalGenerate(ModelMap modelMap, FinalBankAcount finalBankAcount){
        modelMap.addAttribute("finalBankAcount",finalBankAcount);
        finalBankAcount.setNRB();
        System.out.println("Obiekt " + finalBankAcount.getName());
        return "finalresault";
    }


}
