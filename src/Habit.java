public class Habit implements Rewardable {
    private String name;
    private int expReward;
    private boolean isDefeated;

    public Habit(String name, int expReward) throws IllegalArgumentException {
        if (expReward < 0) {
            throw new IllegalArgumentException("Награда не может быть отрицательной");
        }
        this.name = name;
        this.expReward = expReward;
        this.isDefeated = false;
    }

    public String getName() {
        return name;
    }

    public int getExpReward() {
        return expReward;
    }

    public void defeat() {
        isDefeated = true;
    }

    public boolean isDefeated() {
        return isDefeated;
    }

}

