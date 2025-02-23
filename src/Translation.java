/*
The idea behind making this project is to use my knowledge of protein synthesis from m-RNA using r-RNA and t-RNA.
The program will generate result based on given m-RNA sequence
The program might expand to: DNA-> RNA -> m-RNA -> Protein but for now it's just m-RNA -> Protein

Author: Ritesh Shah
Date: Feb 19, 2025
 */


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Translation {

    public HashMap<String , String> populateHashMap(String filePath) throws IOException {
        HashMap<String, String> codonToAminoAcid= new HashMap<>();
        BufferedReader br= new BufferedReader(new FileReader(filePath));
        String line;
        while((line= br.readLine())!=null){
            String[] arr = line.split(" ");
            codonToAminoAcid.put(arr[0], arr[1]);
        }
        br.close();

        return codonToAminoAcid;
    }

    public boolean isValid(String codon){

        return true;
    }

    public String findAminoAcid(String codon) throws IOException {
        if (codon.length() == 3 & isValid(codon)) {
            String filePath = "src/codonToAminoAcid.txt";
            HashMap<String, String> storedValues;
            storedValues= populateHashMap(filePath);
            return storedValues.get(codon);
        } else{
            return "Invalid codon";
         }
    }




    public static void main(String[] args) throws IOException {
        Translation t = new Translation();
        System.out.println(t.findAminoAcid("AUG"));
    }

}
