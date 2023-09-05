import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> wordToFileNamesMap;

    public WordsInFiles() {
        wordToFileNamesMap = new HashMap<>();
    }

    private void addWordsFromFile(File f) {
        String fileName = f.getName();
        FileResource fr = new FileResource(f);
        String[] fileWords = fr.asString().toLowerCase().split(" ");
        ArrayList<String> currentFile = new ArrayList<>(List.of(fileWords));
        for (String word : currentFile) {

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
        int biggestNumber=0;

        for (Map.Entry<String,ArrayList<String>> entry:wordToFileNamesMap.entrySet()) {
            int currentSize=entry.getValue().size();
            if (biggestNumber<currentSize){
                biggestNumber=currentSize;
            }
        }

        return biggestNumber;
    }


}
