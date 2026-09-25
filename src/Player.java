public class Player {
    private String name;
    private int hp;
    private int exp;
    public Player(String name, int hp){
        this.name = name;
        this.hp = hp;
        this.exp = 0;
    }
    public String getName(){
        return name;
    }
    public int getHp(){
        return hp;
    }
    public void takeDamage(int damage){
        this.hp = this.hp - damage;
        if(this.hp < 0){
            this.hp = 0;
        }

    }
    public boolean isAlive(){
        if(hp > 0){
           return true;
        }else{
          return  false;
        }
    }
    public void addExp(int amount){
        this.exp = this.exp + amount;
    }
    public int getExp(){
        return exp;
    }

}
