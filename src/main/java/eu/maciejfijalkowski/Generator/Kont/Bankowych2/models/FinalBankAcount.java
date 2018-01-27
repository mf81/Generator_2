package eu.maciejfijalkowski.Generator.Kont.Bankowych2.models;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigInteger;

public class FinalBankAcount {
    private String checkSum;
    private String name;
    private String bankNumber;
    private String swrk;
    private String numberEF;
    private String admNumber;
    @Pattern(regexp = "^\\d{4}$", message = "Wpisz 4 cyfry dla kodu WM")
    private String WMname;
    @Pattern(regexp = "^\\d{4}$", message = "Wpisz 4 cyfry dla kodu kontrahenta")
    private String clientName;

    public FinalBankAcount(){
            //String name, String bankNumber, String swrk, String numberEF, String admNumber, String WMname, String clientName) {
//        this.name = name;
//        this.bankNumber = bankNumber;
//        this.swrk = swrk;
//        this.numberEF = numberEF;
//        this.admNumber = admNumber;
//        this.WMname = WMname;
//        this.clientName = clientName;
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
        if (bankNumber.equals("1320 0022"))this.name="Pocztowy";
        if (bankNumber.equals("1020 4274"))this.name="PKO BP";
        if (bankNumber.equals("1370 1398"))this.name="Nord Bank";
        this.bankNumber = bankNumber;
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

    private static String nrbGenerator (String nr){
        nr=nr.replaceAll("\\s+","");
        BigInteger nrAcount, modulo, subFinal,number;
        nrAcount = new BigInteger(nr+"252100");
        modulo = new BigInteger("97");
        number = new BigInteger("98");
        subFinal = nrAcount.mod(modulo);
        subFinal=number.subtract(subFinal);
        String finalNumber = ""+subFinal;
        if (finalNumber.length()==1)finalNumber="0" + finalNumber;
        return finalNumber;
    }
}