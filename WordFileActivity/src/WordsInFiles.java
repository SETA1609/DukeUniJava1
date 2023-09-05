import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> wordToFileNamesMap;

    public WordsInFiles() {
        wordToFileNamesMap=new HashMap<>();
    }
    private void addWordsFromFile(File f){
        String word=f.getName();
        String path=f.getPath();
        ArrayList<String>filenames=wordToFileNamesMap.get(word);

        if (filenames == null) {
            filenames = new ArrayList<>();
            wordToFileNamesMap.put(word, filenames);
        }

        if (!filenames.contains(path)) {
            filenames.add(path);
        }
    }
}
