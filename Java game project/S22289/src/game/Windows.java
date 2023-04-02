package game;

import javax.swing.JFrame;

public class Windows {
    public static void main(String[] args) {

        JFrame fur = new JFrame();


        fur.setTitle("cukur <***>");
        fur.setSize(815,710);
        fur.setLocation(0, 0);
        fur.setResizable(false);


        Game dgame = new Game();

        dgame.character.setHealth(100);

        fur.add(dgame);
        fur.setVisible(true);
        fur.setDefaultCloseOperation(fur.EXIT_ON_CLOSE);

    }
}