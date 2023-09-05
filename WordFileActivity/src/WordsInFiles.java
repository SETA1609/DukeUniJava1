import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;
import java.util.*;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> wordToFileNamesMap;

    public WordsInFiles() {
        wordToFileNamesMap = new HashMap<>();
    }

    private void addWordsFromFile(File f) {
        String fileName = f.getName();
        FileResource fr = new FileResource(f);
        String[] fileWords = fr.asString().toLowerCase().split(" ");
        ArrayList<String> currentFile = new ArrayList<>();
        for (String word:fileWords) {

            //I was here
        }
        for (String word : currentFile) {
            word = word.toLowerCase().trim();
            ArrayList<String> filenames = wordToFileNamesMap.get(word);

            if (filenames == null) {
                filenames = new ArrayList<>();
                wordToFileNamesMap.put(word, filenames);
            }

            if (!filenames.contains(fileName)) {
                filenames.add(fileName);
            }
        }

    }

    public void buildWordFileMap() {
        wordToFileNamesMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }

    public int maxNumber() {
        int biggestNumber = 0;

        for (Map.Entry<String, ArrayList<String>> entry : wordToFileNamesMap.entrySet()) {
            int currentSize = entry.getValue().size();
            if (biggestNumber < currentSize) {
                biggestNumber = currentSize;
            }
        }

        return biggestNumber;
    }

    public HashSet<String> wordsInNumFiles(int numberOfFiles) {
        HashSet<String> words = new HashSet<>();

        for (Map.Entry<String, ArrayList<String>> entry : wordToFileNamesMap.entrySet()) {
            int currentSize = entry.getValue().size();
            if (currentSize==numberOfFiles){
                words.add(entry.getKey());
            }
        }

        return words;
    }


    public void printFilesIn(String word) {
        for (String file : wordToFileNamesMap.get(word)) {
            System.out.println(file);
        }
    }

    public void test() {
        buildWordFileMap();
        int maxNumberOfFiles = maxNumber();
        HashSet<String> wordsInMostFiles = wordsInNumFiles(maxNumberOfFiles);
        for (Map.Entry<String, ArrayList<String>> entry : wordToFileNamesMap.entrySet()) {
            String word = entry.getKey();
            if (wordsInMostFiles.contains(word)) {
                System.out.println(word + " is in this list of files: ");
                printFilesIn(word);
            }
        }
        System.out.println(wordsInMostFiles.toString());
    }

    public void getFileWithoutWord(String word){
        HashSet<String> filesWithoutWord=new HashSet<>();
        for (Map.Entry<String, ArrayList<String>> entry : wordToFileNamesMap.entrySet()){

                for (String file: entry.getValue()) {
                    if (!wordToFileNamesMap.get(word).contains(file)){
                        filesWithoutWord.add(file);
                    }
                }
        }
        System.out.println("The files without this word are: "+ filesWithoutWord);
    }

    public HashMap<String, ArrayList<String>> getWordToFileNamesMap() {
        return wordToFileNamesMap;
    }

    public void setWordToFileNamesMap(HashMap<String, ArrayList<String>> wordToFileNamesMap) {
        this.wordToFileNamesMap = wordToFileNamesMap;
    }
}
