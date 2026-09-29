import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        Player player = new Player("Larry", 100);
        ArrayList<Habit> habits = new ArrayList<>();
        habits.add(new HardHabit("Push_Ups"));
        habits.add(new EasyHabit("Reading"));
        habits.add(new EasyHabit("Cleaning"));

        for (Habit h : habits) {
            System.out.println("goblin " + h.getName() + " found! Reward: " + h.getExpReward());
            h.defeat();
            System.out.println("goblin " + h.getName() + " defeated " + h.isDefeated());
            player.addExp(h.getExpReward());
        }
        habits.add(new HardHabit("Yoga"));
        System.out.println("goblins in List: " + habits.size());
        habits.remove(0);
        HashMap<String, Habit> habitMap = new HashMap<>();
        for (Habit h : habits) {
            habitMap.put(h.getName(), h);
        }
        System.out.println("goblins in List: " + habits.size());
        StringBuilder report = new StringBuilder();
        report.append("Player: ").append(player.getName()).append("\n");
        report.append("Player alive: ").append(player.isAlive()).append("\n");
        report.append("Total experience: ").append(player.getExp());
        System.out.println(report.toString());
        System.out.println(habitMap.containsKey("Push_Ups"));
        System.out.println(habitMap.containsKey("Yoga"));
        try {
            System.out.println(habitMap.get("Dragon").getExpReward());
        } catch (NullPointerException e) {
            System.out.println("ошибка");
        }finally {
            System.out.println("Проверка завершена");
        }
        try {
            System.out.println(habitMap.get("Reading").getExpReward());
        } catch (NullPointerException e) {
            System.out.println("ошибка");
        }finally {
            System.out.println("Проверка завершена");
        }
        try{
            Habit badhabit = new Habit("Playing", -21);
        }catch (IllegalArgumentException e){
            System.out.println("this catch was passed: " + e.getMessage());
        }

    }


}

