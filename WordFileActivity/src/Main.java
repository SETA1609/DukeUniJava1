public class Main {
    public static void main(String[] args) {
        WordsInFiles wf = new WordsInFiles();
        wf.test();
        System.out.println(wf.wordsInNumFiles(4).size());
        wf.getFileWithoutWord("sad");
        System.out.println(wf.getWordToFileNamesMap().get("red"));
    }
}