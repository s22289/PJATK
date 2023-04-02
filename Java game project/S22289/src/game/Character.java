package game;

public class Character {

    //character traits
    private String name = "";
    private int health =0;
    int c_x = 350;
    int c_y = 50;
    int c_w = 25;//width
    int c_h = 25;//height


    //GETTER AND SETTER
    public String getName() {
        System.out.println(name);
        return name;
    }
    public void setName(String inName) {
        name = inName;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int inHealth) {
        if(inHealth < 0) {
            System.err.println("character health cannot be less than zero: "+ health);
        }else {
            health = inHealth;
            System.out.println("character's health adjusted: "+ health);
        }
    }
}