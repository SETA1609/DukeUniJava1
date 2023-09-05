import edu.duke.FileResource;

import java.util.*;


public class CodonCount {
    private HashMap<String, Integer> codonCounter;

    public CodonCount() {
        codonCounter = new HashMap<>();
    }

    public void buildCodonMap(int start, String dna) {
        codonCounter.clear();
        if (start < 0 || start > 2) {
            System.out.println("Please enter a number between 0 and 2");
            return;
        }

        for (int i = start; i + 2 < dna.length(); i += 3) {
            dna=dna.trim();
            String codon = dna.substring(i, i + 3);
            codonCounter.put(codon, codonCounter.getOrDefault(codon, 0) + 1);

        }
    }

    public String getMostCommonCodon() {
        String mostCommonCodon = "";
        int tmp = 0;
        for (Map.Entry<String, Integer> codon : codonCounter.entrySet()) {
            if (codon.getValue() > tmp) {
                mostCommonCodon = codon.getKey();
                tmp = codon.getValue();
            }
        }
        return mostCommonCodon;
    }

    public void printCodonCounts(int start, int end) {
        for (Map.Entry<String, Integer> codon : codonCounter.entrySet()) {
            if (codon.getValue() >= start && end >= codon.getValue()) {
                System.out.println(codon.getKey() + " has a count of: " + codon.getValue());
            }
        }
    }

    public void tester() {
        FileResource fr = new FileResource();
        String dna = fr.asString().toUpperCase();
        //case 0
        buildCodonMap(0,dna);
        System.out.println("The most common codon is: "+getMostCommonCodon());
        printCodonCounts(1,5);
        //case1
        buildCodonMap(1,dna);
        System.out.println("The most common codon is: "+getMostCommonCodon());
        printCodonCounts(1,5);
        //case2
        buildCodonMap(2,dna);
        System.out.println("The most common codon is: "+getMostCommonCodon());
        printCodonCounts(1,5);
    }


}
