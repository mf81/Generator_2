package eu.maciejfijalkowski.Generator.Kont.Bankowych2.models.services;

import eu.maciejfijalkowski.Generator.Kont.Bankowych2.models.BankDropList;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Scope(value="session", proxyMode= ScopedProxyMode.TARGET_CLASS)
public class BankDropListCollection {
    private List<BankDropList> bankList;

    public BankDropListCollection(){
        bankList = new ArrayList<BankDropList>();
        bankList.add(new BankDropList("1320 0022","Pocztowy"));
        bankList.add(new BankDropList("1020 4274","PKO BP"));
        bankList.add(new BankDropList("1370 1398","Nord Bank"));
    }

    public List<BankDropList> getBankList() {
        return bankList;
    }

    public void setBankList(List<BankDropList> bankList) {
        this.bankList = bankList;
    }

    public BankDropList getBankSelection (int id){
        return bankList.get(id);
    }
}