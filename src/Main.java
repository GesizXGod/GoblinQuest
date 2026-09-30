
public class Main {

    public static void main(String[] args) {
        Game game = new Game("Larry", 100);
        game.addHabit(new HardHabit("Push_Ups"));
        game.addHabit(new EasyHabit("Reading"));
        game.defeatHabit("Push_Ups");
        game.printReport();


    }


}

