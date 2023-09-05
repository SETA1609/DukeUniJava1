public class Main {
    public static void main(String[] args) {
        GladLibMap gL = new GladLibMap();
        gL.makeStory();
        System.out.println();
        System.out.println(gL.totalWordsInMap());
    }
}