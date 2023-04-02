package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Game extends JPanel implements KeyListener,ActionListener{

    Random R = new Random();//enemy is rondom
    Random R2 = new Random();//Money is random

    Character character = new Character();
    Metkit metkit = new Metkit();


    //area traits
    int a_x = 50;
    int a_y = 0;
    int a_w = 700;//width
    int a_h = 700;//height

    //Money traits
    int Money = 0;
    int mo_x = 80;
    int mo_y = 365;
    int mo_w = 45;
    int mo_h = 45;

    //Money.property
    String way2 ="coin.gif";

    //CONSTRUCTOR
    public Game() {
        String C_name = JOptionPane.showInputDialog(this,"character name: ");
        character.setName(C_name);
        if(C_name == null || C_name.length() == 0) {
            JOptionPane.showMessageDialog(this,"You have to enter a character name.","Error", 0);
            System.exit(0);
        }

        addKeyListener(this);

        Timer  t = new Timer(1000, this);
        t.start();

    }

    public void areaControl() {

        int max = 10;
        int min = -40;

        this.a_w -= 5;
        this.a_h -= 5;

        this.a_x = R.nextInt((max - min)+1)+ min;
        this.a_y = R.nextInt((max - min)+1)+ min;

    }

    //character and enemy collision case collision

    public boolean Control() {
        if(new Rectangle(character.c_x,character.c_y,character.c_h,character.c_w).intersects
                (new Rectangle(metkit.m_x,metkit.m_y,metkit.m_h,metkit.m_w))
                && metkit.getEnemy() < 5) {
            System.out.println("metkit is get");
            metkit.setEnemy(metkit.getEnemy() + 1);
            metkit.m_x = R.nextInt(800);
            metkit.m_y = R.nextInt(600);
            return true;
        }

        return false;
    }
    public boolean Control2() {
        if(new Rectangle(character.c_x,character.c_y,character.c_h,character.c_w).intersects
                (new Rectangle(this.mo_x,this.mo_y,this.mo_h,this.mo_w))
                && this.Money < 1000) {
            System.out.println("Money is get");
            this.Money +=1;
            this.mo_x = R2.nextInt(600);
            this.mo_y = R2.nextInt(500);
            return true;
        }
        return false;
    }

    public boolean CharacterLife() {
        if(character.getHealth() > 0) {
            System.out.println("it is alive");
            return true;
        }
        else {
            System.out.println("DEAD...");
            character.setHealth(0);
            JOptionPane.showMessageDialog(this,character.getName() +": The Game is Over","GAME OVER", 1);
            System.exit(0);

            return false;
        }

    }
    public boolean whereIs_Area() {
        if(new Rectangle(character.c_x,character.c_y,character.c_h,character.c_w).intersects
                (new Rectangle(this.a_x,this.a_y,this.a_h,this.a_w))) {
            return true;
        }
        return false;
    }

    //object drawings
    @Override
    public void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);

        //area
        g.setColor(Color.lightGray);
        g.fillOval(this.a_x,this.a_y,this.a_h,this.a_w);

        //character

        g.setColor(Color.BLUE);
        g.fillRect(character.c_x, character.c_y, character.c_w, character.c_h);

        //metkit
        ImageIcon png1 = new ImageIcon(metkit.getWay());
        png1.paintIcon(this, g,metkit.m_x,metkit.m_y);

        //MOney
        ImageIcon png2 = new ImageIcon(this.way2);
        png2.paintIcon(this, g,this.mo_x,this.mo_y);

        //character status notification
        g.setColor(Color.RED);
        g.drawString("Character Name: "+character.getName(),620, 45);
        g.drawString("Character Health: "+character.getHealth(), 620, 60);
        g.drawString("Number of metkit: "+metkit.getEnemy(), 620, 75);
        g.drawString("Money: "+this.Money, 620, 90);

        repaint();
    }

    public boolean isFocusTraversable() {
        return true;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void keyPressed(KeyEvent a) {
        int touch =a.getKeyCode();
        if(touch == KeyEvent.VK_LEFT  && character.c_x > 0) {
            character.c_x -= character.c_w;
            System.out.println(character.c_x);
        }
        else if(touch ==KeyEvent.VK_RIGHT   && character.c_x < 775) {
            character.c_x += character.c_w;
        }
        else if(touch ==KeyEvent.VK_UP  && character.c_y > 0) {
            character.c_y -= character.c_w;
        }
        else if(touch ==KeyEvent.VK_DOWN && character.c_y < 650) {
            character.c_y += character.c_w;
        }
        else if(touch ==KeyEvent.VK_M && metkit.getEnemy() > 0 && character.getHealth() < 75) {
            character.setHealth(character.getHealth() + 10);
            metkit.setEnemy(metkit.getEnemy() - 1);
        }

    }
    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }
    //timer
    @Override
    public void actionPerformed(ActionEvent e) {
        this.Control();
        this.Control2();
        this.CharacterLife();
        this.areaControl();

        if(whereIs_Area() == true) {
            System.out.println("Player is area");
        }else {
            character.setHealth(character.getHealth() - R.nextInt(5));
        }

    }

}