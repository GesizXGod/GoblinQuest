import java.util.ArrayList;
public class Main {

    public static void main(String[] args) {
        Database.createTable();
        Database.createPlayersTable();
        Player player = Database.loadPlayer("Larry");
        if (player == null) {
            player = new Player("Larry", 100);
            Database.savePlayer(player);
        }
        Game game = new Game(player);
        Database.savePlayer(game.getPlayer());
        Database.insertHabit(new HardHabit("Push_Ups"));
        Database.insertHabit(new EasyHabit("Reading"));

        for (Habit h : Database.loadHabits()) {
            game.addHabit(h);
        }

        game.defeatHabit("Push_Ups");
        game.printReport();



    }


}

