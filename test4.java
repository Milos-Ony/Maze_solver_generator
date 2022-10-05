package Projekt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class test4 extends JPanel implements ActionListener {

    Timer timer = new Timer(1000,this);

    private final static Color BACKGROUND_COLOR = Color.WHITE;

    static ArrayList<ArrayList<Integer>> path= new ArrayList<ArrayList<Integer>>();
    static ArrayList<ArrayList<Integer>> path_solve= new ArrayList<ArrayList<Integer>>();
    static ArrayList<ArrayList<Integer>> path_time= new ArrayList<ArrayList<Integer>>();
    static ArrayList<ArrayList<String>> c = new ArrayList<ArrayList<String>>();

    int W=0;

    int Size=10;
    int size = 25; //rozmiar
    int movey = 10; // przesunięcie całości wobec punktu (0,0)
    int movex = 10; // przesunięcie całości wobec punktu (0,0)
    int p = 8; // odstęp ramki od labiryntu
    int w = size*Size; // szerokosc boku labiryntu
    int counter=0;

    public test4(ArrayList<ArrayList<String>> A, int S0, int S1) {

        c=A;
        Size=S0;
        size=S1;
        W=S0*S1;
        setBounds(1,1,W+20,W+20);
        setLayout(null);

        setBackground(BACKGROUND_COLOR);

    }

    void setPath_time(ArrayList<ArrayList<Integer>> A){
        path_time=A;
    }

    void setPath(ArrayList<ArrayList<Integer>> A){
        path=A;
        repaint();
    }

    void setPath_solve(ArrayList<ArrayList<Integer>> A){
        path_solve=A;
        repaint();
    }

    void starttimer(){
        timer.start();
    }

    void setDelay(int delay){
        timer.setDelay(delay);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Stroke stroke = new BasicStroke(5f);
        g2d.setStroke(stroke);
        g2d.setColor(Color.RED);

        Stroke kropka;

        char zero;
        char one;
        char two;
        char three;

        for (int y = 0; y < Size; y++) {
            for (int x = 0; x < Size; x++) {
                stroke = new BasicStroke(3f);
                g2d.setStroke(stroke);
                g2d.setColor(Color.black);
                zero = c.get(y).get(x).charAt(0);
                one = c.get(y).get(x).charAt(1);
                two = c.get(y).get(x).charAt(2);
                three = c.get(y).get(x).charAt(3);

                if (zero == '1') {
                    g2d.drawLine(movex + x * size, movey + y * size, movex + (x + 1) * size, movey + y * size);
                }
                if (one == '1') {
                    g2d.drawLine(movex + (x + 1) * size, movey + y * size, movex + (x + 1) * size, movey + (y + 1) * size);
                }
                if (two == '1') {
                    g2d.drawLine(movex + (x + 1) * size, movey + (y + 1) * size, movex + x * size, movey + (y + 1) * size);
                }
                if (three == '1') {
                    g2d.drawLine(movex + x * size, movey + y * size, movex + x * size, movey + (y + 1) * size);
                }
            }
        }

        for (int i = 1; i < path_solve.size(); ++i) {
            if (size==25){
                kropka = new BasicStroke(5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
            } else {
                kropka = new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
            }
            g2d.setStroke(kropka);
            g2d.setColor(Color.BLUE);
            int x2=path_solve.get(i).get(1);
            int x22=path_solve.get(i-1).get(1);
            int y2=path_solve.get(i).get(0);
            int y22=path_solve.get(i-1).get(0);
            g2d.drawLine(movex+x2*size+size/2,movey+y2*size+size/2,movex+x22*size+size/2,movey+y22*size+size/2);
        }

        for (int i = 0; i < path.size(); ++i) {
            if (size==25) {
                kropka = new BasicStroke(12f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
            } else {
                kropka = new BasicStroke(6f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
            }

            g2d.setStroke(kropka);
            g2d.setColor(Color.RED);
            int x2 = path.get(i).get(0);
            int y2 = path.get(i).get(1);
            g2d.drawLine(movex + x2 * size + size / 2, movey + y2 * size + size / 2, movex + x2 * size + size / 2, movey + y2 * size + size / 2);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source==timer){
            if (counter<path_time.size()){
                path_solve.add(path_time.get(counter));
                counter+=1;
            } else {
                timer.stop();
            }
            repaint();
        }
    }

    public static void main (String[] args) {

    }
}

