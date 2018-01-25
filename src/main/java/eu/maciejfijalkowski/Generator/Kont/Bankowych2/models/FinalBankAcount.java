package eu.maciejfijalkowski.Generator.Kont.Bankowych2.models;

import java.math.BigInteger;

public class FinalBankAcount {
    private String checkSum;
    private String name;
    private String bankNumber;
    private String swrk;
    private String numberEF;
    private String admNumber;
    private String WMname;
    private String clientName;

    public FinalBankAcount(String name, String bankNumber, String swrk, String numberEF, String admNumber, String WMname, String clientName) {
        this.name = name;
        this.bankNumber = bankNumber;
        this.swrk = swrk;
        this.numberEF = numberEF;
        this.admNumber = admNumber;
        this.WMname = WMname;
        this.clientName = clientName;
    }

    public String getCheckSum() {
        return checkSum;
    }

    public void setCheckSum(String checkSum) {
        this.checkSum = checkSum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        if (this.bankNumber.equals("1320 0022"))this.name="Pocztowy";
        if (this.bankNumber.equals("1020 4274"))this.name="PKO BP";
        if (this.bankNumber.equals("1370 1398"))this.name="Nord Bank";
        //this.bankNumber = bankNumber;
    }

    public String getSwrk() {
        return swrk;
    }

    public void setSwrk(String swrk) {
        this.swrk = swrk;
    }

    public String getNumberEF() {
        return numberEF;
    }

    public void setNumberEF(String numberEF) {
        this.numberEF = numberEF;
    }

    public String getAdmNumber() {
        return admNumber;
    }

    public void setAdmNumber(String admNumber) {
        this.admNumber = admNumber;
    }

    public String getWMname() {
        return WMname;
    }

    public void setWMname(String WMname) {
        this.WMname = WMname;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setNRB (){
        this.checkSum = nrbGenerator(bankNumber+swrk+numberEF+admNumber+WMname+clientName);
    }

    public static String nrbGenerator (String nr){
        nr=nr.replaceAll("\\s+","");
        BigInteger nrKonta, modulo, wynik,liczba;
        nrKonta = new BigInteger(nr+"252100");
        modulo = new BigInteger("97");
        liczba = new BigInteger("98");
        wynik = nrKonta.mod(modulo);
        wynik=liczba.subtract(wynik);
        String y = ""+wynik;
        if (y.length()==1)y="0" + y;
        return y;
    }
}