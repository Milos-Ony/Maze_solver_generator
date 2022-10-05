package Projekt;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GUI extends JFrame implements ActionListener {

    //Frames
    JFrame Start=new JFrame();
    JFrame Generate=new JFrame();
    JFrame Import=new JFrame();

    JFrame Export=new JFrame();
    JFrame Finish=new JFrame();

    //Panels
    JPanel Start_panel=new JPanel();
    JPanel Generate_panel=new JPanel();
    JPanel Import_panel=new JPanel();

    JPanel Maze4=new JPanel();
    JPanel Maze6=new JPanel();
    JPanel Maze7=new JPanel();
    JPanel Export_panel=new JPanel();
    JPanel Finish_panel=new JPanel();

    //Buttons
    JButton GoBack2= new JButton("Wróć");
    JButton GoBack3= new JButton("Wróć");
    JButton GoBack4= new JButton("Wróć");
    JButton GoBack5= new JButton("Wróć");
    JButton GoBack7= new JButton("Wróć");
    JButton GenerateMaze= new JButton("Generuj");
    JButton ImportMaze= new JButton("Importuj");
    JButton Get2= new JButton("Wybierz");
    JButton Get3= new JButton("Wybierz");

    JButton Get5= new JButton("Zapisz");
    JButton Save_txt= new JButton("Zapisz do pliku");
    JButton Save_image_generated= new JButton("Wygeneruj obraz");
    JButton Save_image_solved= new JButton("Wygeneruj obraz");
    JButton ShowSolve= new JButton("Pokaż rozwiązanie");
    JButton ShowSolve_next= new JButton("Pokaż rozwiązanie");
    JButton Solve= new JButton("Rozwiąż sam!");
    JButton Reset= new JButton("Reset");
    JButton Up=new JButton("^");
    JButton Down=new JButton("v");
    JButton Left=new JButton("<");
    JButton Right=new JButton(">");
    JButton End7=new JButton("Zakończ");
    JButton End9=new JButton("Zakończ");

    //Labels
    JLabel Welcome=new JLabel("Witaj w programie Labirynt!");
    JLabel Algorithm= new JLabel("Algorytm:");
    JLabel GiveData = new JLabel("Uzupełnij dane!");
    JLabel GiveDimensions = new JLabel("Podaj rozmiar (2-80):");
    JLabel GiveName = new JLabel("Podaj nazwę pliku:");
    JLabel Mino;

    JLabel GiveName_Export = new JLabel("Podaj nazwę pliku:");
    JLabel Congratulations1;
    JLabel Congratulations2 = new JLabel("Udało Ci się rozwiązać Labirynt!");


    //TextFields
    JTextField Dimensions = new JTextField();
    JTextField NameFile = new JTextField();

    JTextField NameFile_Export = new JTextField();
    JTextField Warning = new JTextField();

    //ComboBox
    JComboBox Algo = new JComboBox();

    //Variables
    boolean txt=false;

    //Others
    static GUI L=new GUI();
    static Generator G1;
    static  Generator2 G2;
    static  Generator3 G3;
    static Solver S1;
    test4 panel;

    //Initialization
    int A=1;
    String FileName="";

    String FileName_Export="";

    int Size=10;
    int size = 25; //rozmiar
    int movey = 40; // przesunięcie całości wobec punktu (0,0)
    int movex = 20; // przesunięcie całości wobec punktu (0,0)
    int p = 8; // odstęp ramki od labiryntu
    int w = size*Size; // szerokosc boku labiryntu

    public GUI(){

        //Element 1
        Start.setTitle("Labirynt!");
        Start.setSize(450,400);
        Start.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Start_panel.setLayout(null);

        Welcome.setBounds(70,10,350,25);
        Welcome.setFont(new Font("Dialog", Font.PLAIN, 24));
        Start_panel.add(Welcome);

        GenerateMaze.setBounds(130,300,80,25);
        GenerateMaze.setFocusable(false);
        GenerateMaze.addActionListener(this);
        Start_panel.add(GenerateMaze);

        ImportMaze.setBounds(220,300,80,25);
        ImportMaze.setFocusable(false);
        ImportMaze.addActionListener(this);
        Start_panel.add(ImportMaze);

        ImageIcon icon1=new ImageIcon("minotaur3.jpg");
        Image image1 = icon1.getImage();
        Image newimg1 = image1.getScaledInstance(394, 250,  java.awt.Image.SCALE_SMOOTH);
        icon1 = new ImageIcon(newimg1);

        Mino = new JLabel(icon1);
        Mino.setBounds(20,40,394,250);
        Start_panel.add(Mino);

        Start.add(Start_panel);
        Start.setVisible(true);

        //Element 2
        Generate.setTitle("Generowanie");
        Generate.setSize(400,160);
        Generate.setDefaultCloseOperation(EXIT_ON_CLOSE);

        Generate_panel.setLayout(null);

        GoBack2.setBounds(80,80,110,25);
        GoBack2.setFocusable(false);
        GoBack2.addActionListener(this);
        Generate_panel.add(GoBack2);

        Get2.setBounds(210,80,110,25);
        Get2.setFocusable(false);
        Get2.addActionListener(this);
        Generate_panel.add(Get2);

        Algorithm.setBounds(150,10,150,25);
        Generate_panel.add(Algorithm);

        GiveData.setBounds(10,10,200,25);
        Generate_panel.add(GiveData);

        GiveDimensions.setBounds(10,40,350,25);
        Generate_panel.add(GiveDimensions);

        Dimensions.setBounds(150,43,230,25);
        Generate_panel.add(Dimensions);

        Algo.setEditable(true);
        Algo.addItem("Recursive backtracking");
        Algo.addItem("Binary Tree");
        Algo.addItem("Recursive Division");
        Algo.setEditable(false);
        Algo.setSize(10,10);
        Algo.setBounds(210,10,170,30);
        Algo.addActionListener(this);
        Generate_panel.add(Algo);

        Generate.add(Generate_panel);

        //Element 3
        Import.setTitle("Importowanie");
        Import.setSize(280,160);
        Import.setDefaultCloseOperation(EXIT_ON_CLOSE);

        Import_panel.setLayout(null);

        GoBack3.setBounds(10,80,110,25);
        GoBack3.setFocusable(false);
        GoBack3.addActionListener(this);
        Import_panel.add(GoBack3);

        Get3.setBounds(140,80,110,25);
        Get3.setFocusable(false);
        Get3.addActionListener(this);
        Import_panel.add(Get3);

        GiveName.setBounds(10,10,150,25);
        Import_panel.add(GiveName);

        NameFile.setBounds(10,42,240,25);
        Import_panel.add(NameFile);

        Import.add(Import_panel);

        //Element 4


        //Element 5
        Export.setTitle("Eksportowanie");
        Export.setSize(280,160);

        Export_panel.setLayout(null);

        GoBack5.setBounds(10,80,110,25);
        GoBack5.setFocusable(false);
        GoBack5.addActionListener(this);
        Export_panel.add(GoBack5);

        Get5.setBounds(140,80,110,25);
        Get5.setFocusable(false);
        Get5.addActionListener(this);
        Export_panel.add(Get5);

        GiveName_Export.setBounds(10,10,150,25);
        Export_panel.add(GiveName_Export);

        NameFile_Export.setBounds(10,42,240,25);
        Export_panel.add(NameFile_Export);

        Export.add(Export_panel);

        //Element 6


        //Element 7


        //Element 8
        //Korzystam z elementu 5

        //Element 9
        Finish.setSize(400,380);
        Finish_panel.setLayout(null);
        Finish.setDefaultCloseOperation(EXIT_ON_CLOSE);

        ImageIcon icon2=new ImageIcon("grat5.jpg");
        Image image2 = icon2.getImage();
        Image newimg2 = image2.getScaledInstance(360, 242,  java.awt.Image.SCALE_SMOOTH);
        icon2 = new ImageIcon(newimg2);

        Congratulations1 = new JLabel(icon2);
        Congratulations1.setBounds(10,10,360,242);
        Finish_panel.add(Congratulations1);

        Congratulations2.setBounds(20,270,400,25);
        Congratulations2.setFont(new Font("Dialog", Font.PLAIN, 24));;
        Finish_panel.add(Congratulations2);

        End9.setBounds(90,310,200,25);
        End9.setFocusable(false);
        End9.addActionListener(this);
        Finish_panel.add(End9);

        Finish.add(Finish_panel);
    }

    File file;
    String data = "";
    ArrayList<String> cell;
    ArrayList<ArrayList<String>> cells;

    public void Reader(String file_str) {
        file = new File(file_str);
        cell = new ArrayList<>();
        cells = new ArrayList<>();
        data="";

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.replaceAll("\\s+", "");
                data = data + line;
            }
        } catch (IOException e) {
            System.out.println("Wystąpił błąd: " + e);
        }
        System.out.println(data);
        int charInt = 0;
        for(int j = 0; j < data.length()/4; j++) {
            String temporaryString = data.substring(charInt,charInt+4);
            cell.add(temporaryString);
            charInt = charInt + 4;
        }
        charInt = 0;
        if (CheckInt(Math.sqrt(cell.size()))) {
            int step = (int) Math.sqrt(cell.size());
            System.out.println(step);
            Size=step;
            int listInt = 0;
            for (int j = 0; j < cell.size()/step; j++) {
                ArrayList<String> listList = new ArrayList<>();
                List<String> temporaryList = cell.subList(listInt, listInt + step);
                listList.addAll(temporaryList);
                cells.add(listList);
                listInt = listInt + step;
            }
            listInt = 0;
        } else {
            System.out.println("Podana labirynt ma niepoprawne wymiary - nie jest kwadratem.");
        }
        System.out.println(cells);
    }

    public boolean CheckInt(double z) {
        String str = Double.toString(z);
        str = str.substring(0,str.lastIndexOf("."));
        try {
            @SuppressWarnings("unused")
            int x = Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean CanGoUp(int xx, int yy){
        char k;
        k = c.get(yy).get(xx).charAt(0);
        if (k=='0'){
            return true;
        }
        return false;
    }

    public boolean CanGoDown(int xx, int yy){
        char k;
        k = c.get(yy).get(xx).charAt(2);
        if (k=='0'){
            return true;
        }
        return false;
    }

    public boolean CanGoLeft(int xx, int yy){
        char k;
        k = c.get(yy).get(xx).charAt(3);
        if (k=='0'){
            return true;
        }
        return false;
    }

    public boolean CanGoRight(int xx, int yy){
        char k;
        k = c.get(yy).get(xx).charAt(1);
        if (k=='0'){
            return true;
        }
        return false;
    }

    static ArrayList<ArrayList<Integer>> path= new ArrayList<ArrayList<Integer>>();
    static ArrayList<ArrayList<Integer>> path_solve= new ArrayList<ArrayList<Integer>>();
    static ArrayList<ArrayList<Integer>> path_time= new ArrayList<ArrayList<Integer>>();
    static ArrayList<ArrayList<String>> c = new ArrayList<ArrayList<String>>();

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source =e.getSource();

        ArrayList<Integer> pathka;

        if(source == GenerateMaze){
            System.out.println("Generuj");
            Generate.setVisible(true);
            GenerateMaze.setEnabled(false);
            ImportMaze.setEnabled(false);
        } else if(source==ImportMaze){
            GenerateMaze.setEnabled(false);
            ImportMaze.setEnabled(false);
            System.out.println("Importuj");
            Import.setVisible(true);
        } else if(source==GoBack2){
            System.out.println("Wróć");
            GenerateMaze.setEnabled(true);
            ImportMaze.setEnabled(true);
            Generate.setVisible(false);
        } else if(source==GoBack3){
            System.out.println("Wróć");
            GenerateMaze.setEnabled(true);
            ImportMaze.setEnabled(true);
            Import.setVisible(false);
        } else if(source==Get2){
            System.out.println("Wybierz");

            try {
                Size = Integer.parseInt(Dimensions.getText());
                System.out.println(Size);
                if(Size<81 && Size>1){
                    Generate.setVisible(false);
                    Start.setVisible(false);

                if (Size>30){
                    size=10;
                    setSize(Size * size + 300, Size * size + 100);
                } else {
                    size=25;
                    if (Size < 8) {
                        setSize(450, 380);
                    } else {
                        setSize(Size * size + 300, Size * size + 100);
                    }
                }

                    setTitle("Labirynt :-)");

                    setDefaultCloseOperation(EXIT_ON_CLOSE);

                    //Element 4
                    Maze4.setLayout(null);

                    GoBack4.setBounds(Size*size+40,200,200,25);
                    GoBack4.setFocusable(false);
                    GoBack4.addActionListener(this);
                    Maze4.add(GoBack4);

                    Save_txt.setBounds(Size*size+40,10,200,25);
                    Save_txt.setFocusable(false);
                    Save_txt.addActionListener(this);
                    Maze4.add(Save_txt);

                    Save_image_generated.setBounds(Size*size+40,40,200,25);
                    Save_image_generated.setFocusable(false);
                    Save_image_generated.addActionListener(this);
                    Maze4.add(Save_image_generated);

                    Solve.setBounds(Size*size+40,70,200,25);
                    Solve.setFocusable(false);
                    Solve.addActionListener(this);
                    Maze4.add(Solve);

                    ShowSolve.setBounds(Size*size+40,100,200,25);
                    ShowSolve.setFocusable(false);
                    ShowSolve.addActionListener(this);
                    Maze4.add(ShowSolve);

                    //Element 6
                    Maze6.setLayout(null);

                    Up.setBounds(Size*size+110,90,50,50);
                    Up.addActionListener(this);
                    Up.setFocusable(false);
                    Maze6.add(Up);

                    Down.setBounds(Size*size+110,190,50,50);
                    Down.setFocusable(false);
                    Down.addActionListener(this);
                    Maze6.add(Down);

                    Left.setBounds(Size*size+60,140,50,50);
                    Left.setFocusable(false);
                    Left.addActionListener(this);
                    Maze6.add(Left);

                    Right.setBounds(Size*size+160,140,50,50);
                    Right.setFocusable(false);
                    Right.addActionListener(this);
                    Maze6.add(Right);

                    ShowSolve_next.setBounds(Size*size+40,10,200,25);
                    ShowSolve_next.setFocusable(false);
                    ShowSolve_next.addActionListener(this);
                    Maze6.add(ShowSolve_next);

                    Reset.setBounds(Size*size+40,40,200,25);
                    Reset.setFocusable(false);
                    Reset.addActionListener(this);
                    Maze6.add(Reset);

                    Warning.setBounds(Size*size+40,280,200,25);
                    Warning.setEditable(false);
                    Maze6.add(Warning);

                    //Element 7
                    Maze7.setLayout(null);

                    Save_image_solved.setBounds(Size*size+40,40,200,25);
                    Save_image_solved.setFocusable(false);
                    Save_image_solved.addActionListener(this);
                    Maze7.add(Save_image_solved);

                    GoBack7.setBounds(Size*size+40,200,200,25);
                    GoBack7.setFocusable(false);
                    GoBack7.addActionListener(this);
                    Maze7.add(GoBack7);

                    End7.setBounds(Size*size+40,250,200,25);
                    End7.setFocusable(false);
                    End7.addActionListener(this);
                    Maze7.add(End7);

                    G1 = new Generator();
                    G2 = new Generator2();
                    G3 = new Generator3();
                    if (A==1) {
                        c = G1.generate(Size);
                    } else if(A==2) {
                        c=G2.generate(Size);
                    } else {
                        c=G3.generate(Size);
                    }

//                    repaint();

                    panel = new test4(c,Size,size);
                    add(panel);

                    add(Maze4);
                    setVisible(true);

                } else {
                    JOptionPane.showMessageDialog(null, "Wymiar musi mieć wartość całkowitą od 2 do 70!");
                }
            } catch (NumberFormatException ex1) {
                JOptionPane.showMessageDialog(null, "Wymiar musi mieć wartość całkowitą od 2 do 70!");
            }
        } else if (source == Algo) {
            if (Algo.getSelectedIndex() == 0) {
                A=1;
                System.out.println(A);
            } else if (Algo.getSelectedIndex() == 1) {
                A=2;
                System.out.println(A);
            } else if (Algo.getSelectedIndex() == 2) {
                A=3;
                System.out.println(A);
            }
        } else if(source==Get3){
            try {
                System.out.println("Wybierz");
                FileName = NameFile.getText();
                Import.setVisible(false);
                Start.setVisible(false);

                Reader(FileName);

                //Element 4
                Maze4.setLayout(null);


                if (Size>30){
                    size=10;
                    setSize(Size * size + 300, Size * size + 100);
                } else {
                    size=25;
                    if (Size < 8) {
                        setSize(450, 380);
                    } else {
                        setSize(Size * size + 300, Size * size + 100);
                    }
                }

                setTitle("Labirynt :-)");
                setDefaultCloseOperation(EXIT_ON_CLOSE);

                GoBack4.setBounds(Size*size+40,200,200,25);
                GoBack4.setFocusable(false);
                GoBack4.addActionListener(this);
                Maze4.add(GoBack4);

                Save_txt.setBounds(Size*size+40,10,200,25);
                Save_txt.setFocusable(false);
                Save_txt.addActionListener(this);
                Maze4.add(Save_txt);

                Save_image_generated.setBounds(Size*size+40,40,200,25);
                Save_image_generated.setFocusable(false);
                Save_image_generated.addActionListener(this);
                Maze4.add(Save_image_generated);

                Solve.setBounds(Size*size+40,70,200,25);
                Solve.setFocusable(false);
                Solve.addActionListener(this);
                Maze4.add(Solve);

                ShowSolve.setBounds(Size*size+40,100,200,25);
                ShowSolve.setFocusable(false);
                ShowSolve.addActionListener(this);
                Maze4.add(ShowSolve);

                //Element 6
                Maze6.setLayout(null);

                Up.setBounds(Size*size+110,90,50,50);
                Up.addActionListener(this);
                Up.setFocusable(false);
                Maze6.add(Up);

                Down.setBounds(Size*size+110,190,50,50);
                Down.setFocusable(false);
                Down.addActionListener(this);
                Maze6.add(Down);

                Left.setBounds(Size*size+60,140,50,50);
                Left.setFocusable(false);
                Left.addActionListener(this);
                Maze6.add(Left);

                Right.setBounds(Size*size+160,140,50,50);
                Right.setFocusable(false);
                Right.addActionListener(this);
                Maze6.add(Right);

                ShowSolve_next.setBounds(Size*size+40,10,200,25);
                ShowSolve_next.setFocusable(false);
                ShowSolve_next.addActionListener(this);
                Maze6.add(ShowSolve_next);

                Reset.setBounds(Size*size+40,40,200,25);
                Reset.setFocusable(false);
                Reset.addActionListener(this);
                Maze6.add(Reset);

                Warning.setBounds(Size*size+40,280,200,25);
                Warning.setEditable(false);
                Maze6.add(Warning);

                //Element 7
                Maze7.setLayout(null);

                Save_image_solved.setBounds(Size*size+40,40,200,25);
                Save_image_solved.setFocusable(false);
                Save_image_solved.addActionListener(this);
                Maze7.add(Save_image_solved);

                GoBack7.setBounds(Size*size+40,200,200,25);
                GoBack7.setFocusable(false);
                GoBack7.addActionListener(this);
                Maze7.add(GoBack7);

                End7.setBounds(Size*size+40,250,200,25);
                End7.setFocusable(false);
                End7.addActionListener(this);
                Maze7.add(End7);

                c=cells;

                panel = new test4(c,Size,size);
                add(panel);

//                repaint();
                add(Maze4);
                setVisible(true);

                System.out.println(FileName);
            } catch (ArithmeticException ex2) {
                JOptionPane.showMessageDialog(null, "Nie poprawna nazwa pliku!");
            }

        } else if(source==GoBack4){
            setVisible(false);
            Start.setVisible(true);
            GenerateMaze.setEnabled(true);
            ImportMaze.setEnabled(true);

            System.out.println("Wróć");
        } else if(source==Save_txt){
            Export.setVisible(true);

            Save_txt.setEnabled(false);
            Save_image_generated.setEnabled(false);
            GoBack4.setEnabled(false);
            Solve.setEnabled(false);
            ShowSolve.setEnabled(false);

            txt=true;

            System.out.println("Zapisz do pliku");
        } else if(source==Save_image_generated){
            Export.setVisible(true);

            Save_txt.setEnabled(false);
            Save_image_generated.setEnabled(false);
            GoBack4.setEnabled(false);
            Solve.setEnabled(false);
            ShowSolve.setEnabled(false);

            System.out.println("Wygeneruj obraz");
        } else if(source==Solve){
            remove(Maze4);
            add(Maze6);
            setVisible(false);
            setVisible(true);

            path = new ArrayList<ArrayList<Integer>>();
            pathka= new ArrayList<Integer>();
            int startx = 0;
            int starty = 0;
            pathka.add(startx);
            pathka.add(starty);
            path.add(pathka);

            panel.setPath(path);
//            repaint();

            System.out.println("Rowiąż sam");
        } else if(source==ShowSolve){
            remove(Maze4);
            add(Maze7);
            setVisible(false);

            path_solve = new ArrayList<ArrayList<Integer>>();
//            pathka= new ArrayList<Integer>();
//            int startx=0;
//            int starty=0;
//            pathka.add(startx);
//            pathka.add(starty);
//            path_solve.add(pathka);

            S1 = new Solver();
            path_solve=S1.solveWithList(c);
//            repaint();
            panel.setPath_solve(path_solve);

            path_time=S1.solveWithList(c);

            setVisible(true);

            System.out.println("Pokaż rozwiązanie");
        } else if(source==GoBack7){

            panel = new test4(c,Size,size);

            remove(Maze7);
//            removeAll();
            add(panel);
            add(Maze4);
            setVisible(false);

            path_solve = new ArrayList<ArrayList<Integer>>();
            path = new ArrayList<ArrayList<Integer>>();

//            pathka= new ArrayList<Integer>();
//            int startx=0;
//            int starty=0;
//            pathka.add(startx);
//            pathka.add(starty);
//            path_solve.add(pathka);
//
//            S1 = new Solver();
//            path_solve=S1.solveWithList(c);
//            repaint();

            panel.setPath(path);
            panel.setPath_solve(path_solve);
            setVisible(true);

            System.out.println("GOBACK7");
        } else if(source==GoBack5){
            Export.setVisible(false);

            Save_txt.setEnabled(true);
            Save_image_generated.setEnabled(true);
            GoBack4.setEnabled(true);
            Solve.setEnabled(true);
            ShowSolve.setEnabled(true);

            Save_image_solved.setEnabled(true);
            End7.setEnabled(true);

            txt=false;

            System.out.println("Pokaż rozwiązanie");
        } else if(source==Get5){
            FileName_Export = NameFile_Export.getText();

            if (txt){
                try {
                    String t;
                    String format = "txt";
                    String fileName = FileName_Export + "." + format;
                    FileWriter Writer = new FileWriter(fileName);
                    for (int y = 0; y < Size; y++) {
                        for (int x = 0; x < Size; x++) {
                            t = c.get(y).get(x);
                            Writer.write(t);
                            Writer.write(" ");
                        }
                        Writer.write("\n");
                    }
                    Writer.close();

                    System.out.println("Zapisano txt");

                } catch (IOException error){
                    System.out.println("Błąd przy zapisie pliku txt.");
                }
            } else {
                try {
                    w=Size*size;
                    Rectangle rect = this.getBounds();
                    String format = "png";
                    String fileName = FileName_Export + "." + format;
                    BufferedImage captureImage =new BufferedImage(w+30, w+50,BufferedImage.TYPE_INT_ARGB);
                    this.paint(captureImage.getGraphics());

                    ImageIO.write(captureImage, format, new File(fileName));

                    System.out.println("Zapisano image");
                } catch(Exception err) {
                    err.printStackTrace();
                }
            }
            JOptionPane.showMessageDialog(null, "Zapisano");

            Export.setVisible(false);
            Save_txt.setEnabled(true);
            Save_image_generated.setEnabled(true);
            GoBack4.setEnabled(true);
            Solve.setEnabled(true);
            ShowSolve.setEnabled(true);

            Save_image_solved.setEnabled(true);
            End7.setEnabled(true);

            txt=false;
            System.out.println("Zapisz");
        } else if(source==Up){

            int x=path.get(path.size()-1).get(0);
            int y=path.get(path.size()-1).get(1);

            if (L.CanGoUp(x,y)){
                y+=-1;

                if (path.size()>2 && x==path.get(path.size()-2).get(0) && y==path.get(path.size()-2).get(1)){
                    path.remove(path.size()-1);
                    panel.setPath(path);
                } else {
                    pathka= new ArrayList<Integer>();
                    pathka.add(x);
                    pathka.add(y);
                    path.add(pathka);
                    panel.setPath(path);

                    if(x==Size-1 && y==Size-1){
                        setVisible(false);
                        Finish.setVisible(true);
                    }
                    Warning.setText("");
                }
            } else {
                Warning.setText("Zły ruch!");
            }

            System.out.println(path);
            System.out.println("^");
        } else if(source==Down){

            int x=path.get(path.size()-1).get(0);
            int y=path.get(path.size()-1).get(1);

            if (L.CanGoDown(x,y)){
                y+=+1;

                if (path.size()>2 && x==path.get(path.size()-2).get(0) && y==path.get(path.size()-2).get(1)){
                    path.remove(path.size()-1);
                    panel.setPath(path);
                } else {
                    pathka= new ArrayList<Integer>();
                    pathka.add(x);
                    pathka.add(y);
                    path.add(pathka);
                    panel.setPath(path);

                    if(x==Size-1 && y==Size-1){
                        setVisible(false);
                        Finish.setVisible(true);
                    }
                    Warning.setText("");
                }
            } else {
                Warning.setText("Zły ruch!");
            }

            System.out.println(path);
            System.out.println("v");
        } else if(source==Left){

            int x=path.get(path.size()-1).get(0);
            int y=path.get(path.size()-1).get(1);

            if (L.CanGoLeft(x,y)){
                x+=-1;

                if (path.size()>1 && x==path.get(path.size()-2).get(0) && y==path.get(path.size()-2).get(1)){
                    path.remove(path.size()-1);
                    panel.setPath(path);
                } else {
                    pathka= new ArrayList<Integer>();
                    pathka.add(x);
                    pathka.add(y);
                    path.add(pathka);
                    panel.setPath(path);

                    if(x==Size-1 && y==Size-1){
                        setVisible(false);
                        Finish.setVisible(true);
                    }
                    Warning.setText("");
                }
            } else {
                Warning.setText("Zły ruch!");
            }

            System.out.println(path);
            System.out.println("<");
        } else if(source==Right){

            int x=path.get(path.size()-1).get(0);
            int y=path.get(path.size()-1).get(1);

            if (L.CanGoRight(x,y)){
                x+=1;

                if (path.size()>1 && x==path.get(path.size()-2).get(0) && y==path.get(path.size()-2).get(1)){//DO POPRAWY
                    path.remove(path.size()-1);
                    panel.setPath(path);
                } else {
                    pathka= new ArrayList<Integer>();
                    pathka.add(x);
                    pathka.add(y);
                    path.add(pathka);
                    panel.setPath(path);

                    if(x==Size-1 && y==Size-1){
                        setVisible(false);
                        Finish.setVisible(true);
                    }
                    Warning.setText("");
                }
            } else {
                Warning.setText("Zły ruch!");
            }

            System.out.println(path);
            System.out.println(">");
        } else if(source==ShowSolve_next){

            path_solve = new ArrayList<ArrayList<Integer>>();
            path_time = new ArrayList<ArrayList<Integer>>();

//            pathka= new ArrayList<Integer>();
//            int startx=0;
//            int starty=0;
//            pathka.add(startx);
//            pathka.add(starty);
//            path_solve.add(pathka);

            S1= new Solver();

            remove(Maze6);
            add(Maze7);

            path_time=S1.solveWithList(c);

            panel.setPath_time(path_time);
            panel.setDelay(30);
            panel.starttimer();

//            remove(Maze6);
//            add(Maze7);
            setVisible(false);
            setVisible(true);

            System.out.println("Pokaż rozwiązanie");
        } else if(source==Reset){

            Warning.setText("");
            path = new ArrayList<ArrayList<Integer>>();
            pathka= new ArrayList<Integer>();
            int startx=0;
            int starty=0;
            pathka.add(startx);
            pathka.add(starty);
            path.add(pathka);

            panel.setPath(path);

            System.out.println("Reset");
        } else if(source==Save_image_solved){
            Save_image_solved.setEnabled(false);
            End7.setEnabled(false);

            Export.setVisible(true);

            System.out.println("Zapisz rozwiązanie");
        } else if(source==End7){
            setVisible(false);
            System.exit(0);
            System.out.println("Zakończono");
        } else if(source==End9){
            System.exit(0);

            System.out.println("Zakończono");
        }
    }

    public static void main(String [] args){
    }
}
