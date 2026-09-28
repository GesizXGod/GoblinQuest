import java.util.ArrayList;

public class main {

    public static void main(String[] args) {
        Player player = new Player("Larry", 100);
         ArrayList<Habit> habits = new ArrayList<>();
             habits.add(new HardHabit("Push-ups"));
             habits.add(new EasyHabit("Reading"));
            habits.add(new EasyHabit("Cleaning"));

        for(Habit h : habits){
            System.out.println("goblin " + h.getName() + " found! Reward: " + h.getExpReward());
            h.defeat();
            System.out.println("goblin " + h.getName() + " defeated " +h.isDefeated());
            player.addExp(h.getExpReward());
        }
        habits.add(new HardHabit("Yoga"));
        System.out.println("goblins in List: " + habits.size());
        habits.remove(0);
        System.out.println("goblins in List: " + habits.size());
        System.out.println("Персонаж: " + player.getName());
        System.out.println("Персонаж жив: " + player.isAlive());
        System.out.println("Всего опыта: " + player.getExp());
    }


}

