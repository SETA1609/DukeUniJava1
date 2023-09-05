import edu.duke.FileResource;
import edu.duke.URLResource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class GladLibMap {
    private HashMap<String,ArrayList<String>>myMap;

    private HashSet<String> usedWords;

    private Random myRandom;

    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "datalong";

    public GladLibMap(){
        initializeFromSource(dataSourceDirectory);
        usedWords=new HashSet<>();
        myRandom = new Random();
    }

    public GladLibMap(String source){
        initializeFromSource(source);
        usedWords=new HashSet<>();
        myRandom = new Random();
    }

    private void initializeFromSource(String source) {
        myMap= new HashMap<>();
        String[] categories={
                "adjective","noun","color","country","name","animal","timeframe",
        "verb","fruit"
        };

        for (String category:categories) {
            myMap.put(category,readIt(source+"/"+category+".txt"));
        }
    }

    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        String randomWord= source.get(index);
        return randomWord;
    }

    private String getSubstitute(String label) {
        if (label.equals("country")) {
            return randomFrom(countryList);
        }
        if (label.equals("color")){
            return randomFrom(colorList);
        }
        if (label.equals("noun")){
            return randomFrom(nounList);
        }
        if (label.equals("name")){
            return randomFrom(nameList);
        }
        if (label.equals("adjective")){
            return randomFrom(adjectiveList);
        }
        if (label.equals("animal")){
            return randomFrom(animalList);
        }
        if (label.equals("timeframe")){
            return randomFrom(timeList);
        }
        if (label.equals("verb")){
            return randomFrom(verbList);
        }
        if (label.equals("fruit")){
            return randomFrom(fruitList);
        }
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        return "**UNKNOWN**";
    }

    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        if (usedWords.contains(sub)){
            do {
                sub = getSubstitute(w.substring(first+1,last));
            } while (usedWords.contains(sub));
        }
        usedWords.add(sub); // Add the word to the usedWords set
        return prefix+sub+suffix;
    }

    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }

    public void makeStory(){
        usedWords.clear();
        System.out.println("\n");
        String story = fromTemplate("datalong/madtemplate2.txt");
        printOut(story, 60);
    }

    public HashSet<String> getUsedWords() {
        return usedWords;
    }

    public void setUsedWords(HashSet<String> usedWords) {
        this.usedWords = usedWords;
    }
}
