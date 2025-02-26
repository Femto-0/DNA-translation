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
import java.util.Objects;

public class Translation {

    public HashMap<String , String> populateHashMap(String filePath) throws IOException {
        HashMap<String, String> codonToAminoAcid= new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] arr = line.split(" ");
                codonToAminoAcid.put(arr[0], arr[1]);
            }
        } catch (FileNotFoundException fife) {
            System.out.println(fife.getMessage());
            return null;
        }
        return codonToAminoAcid;
    }

    public boolean isValid(String codon){
        return codon.length() == 3;
    }
    public int findStartingCodon(String sequence){
        for(int i=0; i<sequence.length()-1; i++){
            if(sequence.substring(i, i+3).equals("AUG")){ //begin--> inclusive end-->exclusive
            return i;
            }
        }
        return -1;
    }
    public int findStopCodon(String sequence){
        String correctFormatForSequence= sequence+" "; //had to do this because the way substrings are formed in Java doesn't support how I want this method to act.
        for(int i = correctFormatForSequence.length()-1; i>=2; i--){
            if(correctFormatForSequence.substring(i-2, i+1).equals("UGA")){ //begin--> inclusive end-->exclusive
                return i-2;
            }
        }
        return -1;
    }


    public ArrayList<String> aminoAcidChain(String sequence) throws IOException {
        String filePath = "src/codonToAminoAcid.txt";
        HashMap<String, String> storedValues= populateHashMap(filePath);
        if (storedValues != null) {
            ArrayList<String> protein = new ArrayList<>();
            int startFrom = findStartingCodon(sequence);
            int stopAt = findStopCodon(sequence);

            if (startFrom != -1 && stopAt != -1) {
                for (int i = startFrom; i <= stopAt - 3; i += 3) {
                    String codon = sequence.substring(i, i + 3);
                    if (isValid(codon)) {
                        String aminoAcid = storedValues.get(codon);
                        protein.add(aminoAcid);
                    } else {
                        protein.add("invalidCodon");
                    }
                }
                protein.add("STOP");
            } else {
                System.out.println("Start or end codon not found " + startFrom + " " + stopAt);
                System.exit(0);
            }
            return protein;
        }else{
             return new ArrayList<>();
        }
    }




    public static void main(String[] args) throws IOException {
        Translation t = new Translation();
        String sequence= "AUGGCUAAGUUCCCGUGA";
        System.out.println(t.aminoAcidChain(sequence));
    }

}
