public class main {

    public static void main(String[] args) {
        Player player = new Player("Larry", 100);
         Habit[] habit = {new HardHabit("Push-ups"),
                 new EasyHabit("ReadingBooks"),
                 new EasyHabit("Cleaning") };

        for(int i = 0; i < habit.length; i++){
            System.out.println("goblin " + habit[i].getName() + " found! Reward: " + habit[i].getExpReward());
            habit[i].defeat();
            System.out.println("goblin " + habit[i].getName() + " defeated " + habit[i].isDefeated());
            player.addExp(habit[i].getExpReward());
        }
        System.out.println("Персонаж: " + player.getName());
        System.out.println("Персонаж жив: " + player.isAlive());
        System.out.println("Всего опыта: " + player.getExp());
    }


}

