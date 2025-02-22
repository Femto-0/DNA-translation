/*
The idea behind making this project is to use my knowledge of protein synthesis from m-RNA using r-RNA and t-RNA.
The program will generate result based on given m-RNA sequence
The program might expand to: DNA-> RNA -> m-RNA -> Protein but for now it's just m-RNA -> Protein

Author: Ritesh Shah
Date: Feb 19, 2025
 */


import java.util.ArrayList;
import java.util.HashMap;

public class Translation {

/*
need to change the method such that:
1. read an array that holds all the value for codons and their corresponding amino acid.
2. Find the correct codon by using a for loop (or maybe something even better).
3. Return the Amino acid
 */
    public String findAminoAcid(String codon){
        String aminoAcid= " ";
        char firstNucleotide;
        char secondNucleotide;
        char thirdNucleotide;

        HashMap<Character, HashMap<Character, HashMap<Character, String>>> firstNucleotideToSecond = linkNucleotides();
        System.out.println(firstNucleotideToSecond);
        if(codon.length()==3){
            firstNucleotide=codon.charAt(0);
            secondNucleotide=codon.charAt(1);
            thirdNucleotide=codon.charAt(2);
            if(firstNucleotideToSecond.containsKey(firstNucleotide)){
                HashMap<Character, HashMap< Character, String> > secondMap= firstNucleotideToSecond.get(firstNucleotide);
                if(secondMap.containsKey(secondNucleotide)){
                    HashMap<Character, String> thirdMap= secondMap.get(secondNucleotide);
                    if(thirdMap.containsKey(thirdNucleotide)){
                        aminoAcid= thirdMap.get(thirdNucleotide);
                    }
                }
            }
        }else{
            return "Invalid Codon";
        }




        return aminoAcid;
    }


    /*
    need to change this method such that:
     1. We can populate the HashMap using a BufferReader and store all the HashMaps in an array
     2. Change the method such that the array storing HashMaps is returned.
     */
    private static HashMap<Character, HashMap<Character, HashMap<Character, String>>> linkNucleotides() {
        HashMap<Character, String> lastNucleotideToAmino = new HashMap<>();
        HashMap<Character, HashMap< Character, String> >secondNucleotideToLast= new HashMap<>();
        HashMap<Character,HashMap<Character, HashMap<Character, String>> > firstNucleotideToSecond= new HashMap<>();
        ArrayList<HashMap<Character,HashMap<Character, HashMap<Character, String>>>> arrStoreHashMaps= new ArrayList<>();

        firstNucleotideToSecond.put('A', secondNucleotideToLast);
        secondNucleotideToLast.put('U', lastNucleotideToAmino);
        lastNucleotideToAmino.put('G', "Methionine");
        arrStoreHashMaps.add(firstNucleotideToSecond);
        return firstNucleotideToSecond;
    }


    public static void main(String[] args) {
        System.out.println("test");
        Translation t= new Translation();
       System.out.println( t.findAminoAcid("AUG"));
    }

}
