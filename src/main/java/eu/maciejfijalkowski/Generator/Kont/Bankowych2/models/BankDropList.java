package eu.maciejfijalkowski.Generator.Kont.Bankowych2.models;

public class BankDropList {

    private String name;
    private String bankNumber;

    public BankDropList(String bankNumber, String name) {
        this.name = name;
        this.bankNumber = bankNumber;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
