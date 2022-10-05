package Projekt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Generator2 {

    int dl = 10;     //dlugosc w pionie - liczba kafelkow
    int szer = 10;   //szeroksoc w poziomie

    ArrayList<ArrayList<String>> listofManyWalls = new ArrayList<>();
    ArrayList<ArrayList<Integer>> boundaryTopList = new ArrayList<>();
    ArrayList<ArrayList<Integer>> boundaryRightList = new ArrayList<>();
    ArrayList<ArrayList<Integer>> boundaryDownList = new ArrayList<>();
    ArrayList<ArrayList<Integer>> boundaryLeftList = new ArrayList<>();

    public ArrayList<ArrayList<String>> generate(int size){
        listofManyWalls.clear();
        boundaryTopList.clear();
        boundaryRightList.clear();
        boundaryDownList.clear();
        boundaryLeftList.clear();

        dl = size;
        szer = size;

        createGrid();
        randomMove();

        listofManyWalls.get(0).set(0,listofManyWalls.get(0).get(0).substring(0,3) + "0");
        listofManyWalls.get(size - 1).set(size - 1,listofManyWalls.get(size - 1).get(size - 1).substring(0,1) + "0" + listofManyWalls.get(size - 1).get(size - 1).substring(2));

        return listofManyWalls;
    }

    public void createGrid() {
        for (int x = 0; x < dl; x++) {
            ArrayList<String> temporaryList = new ArrayList<>();
            int i = szer;
            while (i > 0) {
                temporaryList.add("1111");                                            //0
                i--;                                                             //3      //1
            }                                                                         //2
            listofManyWalls.add(temporaryList);
        }                                                                         //1111

        for (int k = 0; k < szer; ++k) {    // gora
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(0);
            temp.add(k);
            boundaryTopList.add(temp);
        }

        for (int k = 0; k < dl; ++k) {     //lewa
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(k);
            temp.add(0);
            boundaryLeftList.add(temp);
        }

        for (int k = 0; k < dl; ++k) {   //prawa
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(k);
            temp.add(szer - 1);
            boundaryRightList.add(temp);
        }

        for (int k = 0; k < szer; k++) {    //dol
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(dl - 1);
            temp.add(k);
            boundaryDownList.add(temp);
        }
    }

    public ArrayList<Integer> possibleMoves(int i, int j){
        ArrayList<Integer> moves = new ArrayList<>();
        ArrayList<Integer> currentLocation = new ArrayList<>(Arrays.asList(i,j));
        moves.add(0);   //gora
        moves.add(1);   //lewo
        if(boundaryTopList.contains(currentLocation)) {
            moves.removeAll(Collections.singletonList(0));
        }
        if(boundaryLeftList.contains(currentLocation)) {
            moves.removeAll(Collections.singletonList(1));
        }
        return moves;
    }

    public ArrayList<ArrayList<String>> randomMove() {
        createGrid();
        for (int i = dl - 1; i > -1; i--) {
            for(int j=szer-1; j>-1; j--){
                if(possibleMoves(i,j).size()!=0) {
                    ArrayList<Integer> allMoves = new ArrayList<>(possibleMoves(i,j));
                    Random rand = new Random();
                    int randomElement = allMoves.get(rand.nextInt(allMoves.size()));
                    switch(randomElement){
                        case 0: {
                            up(i, j);
                            break;
                        }
                        case 1: {
                            left(i,j);
                            break;
                        }
                    }
                }else{
                    System.out.println("Proces generowania labiryntu się zakończył");
                }
            }
        }
        return listofManyWalls;
    }

    public void up (int i, int j){      //idzie do gory    i-kolumna  j- wiersz
        if (i != 0) {
            String s1 = "0" + listofManyWalls.get(i).get(j).substring(1);
            listofManyWalls.get(i).set(j, s1);
            i--;
            String s2 = listofManyWalls.get(i).get(j).substring(0, 2) + "0" + listofManyWalls.get(i).get(j).charAt(3);
            listofManyWalls.get(i).set(j, s2);
        }
    }

    public void left (int i, int j){
        if (j != 0) {
            String s1 = listofManyWalls.get(i).get(j).substring(0, 3) + "0";
            listofManyWalls.get(i).set(j, s1);
            j--;
            String s2 = listofManyWalls.get(i).get(j).charAt(0) + "0" + listofManyWalls.get(i).get(j).substring(2);
            listofManyWalls.get(i).set(j, s2);
        }
    }



    public static void main (String[]args){
//        Generator2 test2 = new Generator2();
//        test2.createGrid();
//        test2.randomMove();
    }
}