public class Main {
    public static void main(String[] args) {
        GladLib gL = new GladLib();
        gL.makeStory();
        System.out.println();
        System.out.println("Number of words replaced: "+gL.getUsedWords().size());
    }
}