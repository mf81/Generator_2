package eu.maciejfijalkowski.Generator.Kont.Bankowych2.controllers;

import eu.maciejfijalkowski.Generator.Kont.Bankowych2.CsvReader;
import eu.maciejfijalkowski.Generator.Kont.Bankowych2.models.FinalBankAcount;
import eu.maciejfijalkowski.Generator.Kont.Bankowych2.models.services.BankDropListCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class MainController {

    private FinalBankAcount finalBankAcount;

    @Autowired
    BankDropListCollection bankDropListCollection;

    @GetMapping("/")
    public String index (ModelMap modelMap, BankDropListCollection bankDropListCollection){
        finalBankAcount = new FinalBankAcount();
        modelMap.addAttribute("finalBankAcount",finalBankAcount);
        modelMap.addAttribute("bankDropListCollection",bankDropListCollection.getBankList());

        System.out.println("1"+finalBankAcount);
        return "index";
    }

    @GetMapping
    public String generate(ModelMap modelMap){
        return "generate";
    }

    @PostMapping("/generate")
    public String generate(@ModelAttribute @Valid FinalBankAcount finalBankAcount, BindingResult bindingResult, ModelMap modelMap){

//        modelMap.addAttribute("info", "Poprawnie dodałeś/aś nowe ostrzeżenie!");


        //modelMap.addAttribute("admDropListCollection",admDropListCollection.getAdmy());
        modelMap.addAttribute("admDropListCollection", CsvReader.CsvReader("src/main/resources/adm.csv").entrySet());
        modelMap.addAttribute("acountDropListCollection",CsvReader.CsvReader("src/main/resources/type.csv").entrySet());

        System.out.println("Bank:"+finalBankAcount.getBankNumber());
        if (finalBankAcount.getBankNumber().equals("1320 0022"))
            modelMap.addAttribute("swrkDropListCollection",CsvReader.CsvReader("src/main/resources/swrkPocztowy.csv").entrySet());
        else
            if (finalBankAcount.getBankNumber().equals("1020 4274"))
                modelMap.addAttribute("swrkDropListCollection",CsvReader.CsvReader("src/main/resources/swrkPKO.csv").entrySet());
            else
                modelMap.addAttribute("swrkDropListCollection",CsvReader.CsvReader("src/main/resources/swrkNord.csv").entrySet());

//        modelMap.addAttribute("finalBankAcount",finalBankAcount);
        System.out.println("Obiekt " + finalBankAcount.getName()+finalBankAcount.getBankNumber());
        System.out.println(finalBankAcount);
        return "generate";
    }

    @PostMapping("/finalresault")
    public String finalGenerate(@ModelAttribute @Valid FinalBankAcount finalBankAcount, BindingResult bindingResult, ModelMap modelMap){
        if (bindingResult.hasErrors()){
            modelMap.addAttribute("admDropListCollection", CsvReader.CsvReader("src/main/resources/adm.csv").entrySet());
            modelMap.addAttribute("acountDropListCollection",CsvReader.CsvReader("src/main/resources/type.csv").entrySet());

            System.out.println("Bank:"+finalBankAcount.getBankNumber());
            if (finalBankAcount.getBankNumber().equals("1320 0022"))
                modelMap.addAttribute("swrkDropListCollection",CsvReader.CsvReader("src/main/resources/swrkPocztowy.csv").entrySet());
            else
            if (finalBankAcount.getBankNumber().equals("1020 4274"))
                modelMap.addAttribute("swrkDropListCollection",CsvReader.CsvReader("src/main/resources/swrkPKO.csv").entrySet());
            else
                modelMap.addAttribute("swrkDropListCollection",CsvReader.CsvReader("src/main/resources/swrkNord.csv").entrySet());
            return "generate";
        }
        modelMap.addAttribute("finalBankAcount",finalBankAcount);
        finalBankAcount.setNRB();
        System.out.println("Obiekt " + finalBankAcount.getName());
        return "finalresault";
    }


}
