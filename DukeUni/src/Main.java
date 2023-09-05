public class Main {
    public static void main(String[] args) {
        GladLibMap gL = new GladLibMap();
        gL.makeStory();
        System.out.println();
        System.out.println("All the words from the pool: "+gL.totalWordsInMap());
        System.out.println("All the words from the pool of categories used: "+gL.totalWordsConsidered());
        System.out.println(gL.getUsedCategories());
    }
}