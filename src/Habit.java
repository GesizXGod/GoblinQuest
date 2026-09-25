public class Habit {
    private String name;
    private int expReward;
    private boolean isDefeated;
    public Habit(String name, int expReward){
        this.name = name;
        this.expReward = expReward;
        this.isDefeated = false;
    }
    public String getName(){
        return name;
    }
    public int getExpReward(){
        return expReward;
    }
    public void defeat(){
        isDefeated = true;
    }
    public boolean isDefeated(){
       return isDefeated;
    }
}

