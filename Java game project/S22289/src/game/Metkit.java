package game;

public class Metkit {

    //enemy traits
    private int metkit = 0;
    private String way ="12345.png";
    int m_x = 0;
    int m_y = 0;
    int m_w = 32;//width
    int m_h = 32;//height


    //GETTER AND SETTER

    public int getEnemy() {
        return metkit;
    }

    public void setEnemy(int inEnemy) {
        metkit = inEnemy;
    }

    public String getWay() {
        return way;
    }

}
