import java.util.ArrayList;
import java.util.HashMap;
public class Game {
    private Player player;
    private ArrayList<Habit> habits;
    private HashMap<String, Habit> habitMap;

    public Game(String playerName, int playerHp){
        this.player = new Player(playerName, playerHp);
        this.habits = new ArrayList<>();
        this.habitMap = new HashMap<>();

    }
    public void addHabit(Habit h){
        habits.add(h);
        habitMap.put(h.getName(), h);
    }
    public void defeatHabit(String name){
        Habit habit = habitMap.get(name);
        habit.defeat();
        player.addExp(habit.getExpReward());
    }
    public void printReport(){
        StringBuilder report = new StringBuilder();
        report.append("Player: ").append(player.getName()).append("\n");
        report.append("Player alive: ").append(player.isAlive()).append("\n");
        report.append("Total experience: ").append(player.getExp());
        System.out.println(report);
    }

}

