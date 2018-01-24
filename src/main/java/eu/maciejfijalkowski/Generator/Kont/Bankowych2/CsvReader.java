package eu.maciejfijalkowski.Generator.Kont.Bankowych2;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CsvReader {
    public static void main(String[] args) {

//        for (Map.Entry<String, String> entry : CsvReader("src/main/resources/country.csv").entrySet())
//        {
//            System.out.println(entry.getKey() + "/" + entry.getValue());
//        }

        CsvReader("src/main/resources/adm.csv").entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + "/" + entry.getValue());
        });
    }

    public static Map <String,String> CsvReader(String path){
        //"src/main/resources/country.csv"
        String csvFile=new File(path).getAbsolutePath();
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        Map <String,String> swrkData = new HashMap<String,String>();
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] country = line.split(cvsSplitBy);
                swrkData.put(country[0],country[1]);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return swrkData;
    }

    }
