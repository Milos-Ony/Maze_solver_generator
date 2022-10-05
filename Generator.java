package Projekt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Generator {

    int dl = 10;     //dlugosc w pionie - liczba kafelkow
    int szer = 10;   //szeroksoc w poziomie
    int back = 1;
    boolean generacja = true;

    ArrayList<ArrayList<String>> listofManyWalls = new ArrayList<>();
    ArrayList<Integer> currentLocationList = new ArrayList<>(Arrays.asList(0,0));   //(wierszY   , kolumnaX) //lista dwuelementowa przechowujaca aktualna pozycje
    ArrayList<ArrayList<Integer>> visitedList = new ArrayList<>();          //lista odwiedzonych komorek
    ArrayList<ArrayList<Integer>> boundaryTopList = new ArrayList<>();      //pomocnicza list do wyznaczenia górnej granicy
    ArrayList<ArrayList<Integer>> boundaryRightList = new ArrayList<>();    //pomocnicza list do wyznaczenia prawej granicy
    ArrayList<ArrayList<Integer>> boundaryDownList = new ArrayList<>();     //pomocnicza list do wyznaczenia dolnej granicy
    ArrayList<ArrayList<Integer>> boundaryLeftList = new ArrayList<>();     //pomocnicza list do wyznaczenia lewej granicy

    public ArrayList<ArrayList<String>> generate(int size) {
        listofManyWalls.clear();
        boundaryTopList.clear();
        boundaryRightList.clear();
        boundaryDownList.clear();
        boundaryLeftList.clear();
        visitedList.clear();
        currentLocationList.clear();
        currentLocationList.addAll(Arrays.asList(0,0));

        dl = size;
        szer = size;

        createGrid();

        generacja = true;
        while (generacja) {
            randomMove(currentLocationList);
        }

        listofManyWalls.get(0).set(0, listofManyWalls.get(0).get(0).substring(0,3) + "0"); //tworzenie wejścia
        listofManyWalls.get(size - 1).set(size - 1, listofManyWalls.get(size - 1).get(size - 1).substring(0,1) + "0" + listofManyWalls.get(size - 1).get(size - 1).substring(2)); //tworzenie wyjścia

        return listofManyWalls;
    }

    /*
    tworzy labirynt jako lista list, najmneijszym jej elementem jest String "1111" oznaczajacy wszystkie scianki postawione
     */

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


    public ArrayList<Integer> possibleMoves(ArrayList<Integer> locationList){
        ArrayList<Integer> moves=new ArrayList<>();
        moves.add(0);   //gora
        moves.add(1);   //prawo
        moves.add(2);   //dol
        moves.add(3);   //lewo
        ArrayList<Integer> temp1 = new ArrayList<>(locationList);
        ArrayList<Integer> temp2 = new ArrayList<>(locationList);
        ArrayList<Integer> temp3 = new ArrayList<>(locationList);
        ArrayList<Integer> temp4 = new ArrayList<>(locationList);
        if(boundaryTopList.contains(locationList) || visitedList.contains(upCheck(temp1))) {
            moves.removeAll(Arrays.asList(0));
        }
        if(boundaryRightList.contains(locationList) || visitedList.contains(rightCheck(temp2))) {
            moves.removeAll(Arrays.asList(1));
        }
        if(boundaryDownList.contains(locationList) || visitedList.contains(downCheck(temp3))) {
            moves.removeAll(Arrays.asList(2));
        }
        if(boundaryLeftList.contains(locationList) || visitedList.contains(leftCheck(temp4))) {
            moves.removeAll(Arrays.asList(3));
        }
        return moves;  //zwraca liste dostepnych ruchow
    }

    /**
     !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     */

    public ArrayList<Integer> randomMove(ArrayList<Integer> locationList) {
        if (!Check()) {
            if (!visitedList.contains(locationList)) {
                ArrayList<Integer> addPosition = new ArrayList<>(locationList);
                visitedList.add(addPosition);
            }
            if (possibleMoves(locationList).size() == 0) {
                back = back + 1;
                Collections.copy(locationList,visitedList.get(visitedList.size() - back));
                return locationList;
            } else {
                back = 1;
                Random rand = new Random();
                int randomElement = possibleMoves(locationList).get(rand.nextInt(possibleMoves(locationList).size()));
                switch (randomElement) {
                    case 0: {
                        return up(locationList);
                    }
                    case 1: {
                        return right(locationList);
                    }
                    case 2: {
                        return down(locationList);
                    }
                    case 3: {
                        return left(locationList);
                    }
                }
            }
        }
        else
        {
            generacja = false;
            System.out.println("Proces generowania labiryntu się zakończył");
            return locationList;
        }
        return locationList;
    }

    public boolean Check(){
        return visitedList.size() == dl*szer;
    }


    public ArrayList<Integer> up (ArrayList<Integer>locationList){      //idzie do gory
        if (locationList.get(0) != 0) {
            String s1 = "0" + listofManyWalls.get(locationList.get(0)).get(locationList.get(1)).substring(1);
            listofManyWalls.get(locationList.get(0)).set(locationList.get(1), s1);
            locationList.set(0, locationList.get(0) - 1);  // nasz kolejny krok do góry
            String s2 = listofManyWalls.get(locationList.get(0)).get(locationList.get(1)).substring(0, 2) + "0" + listofManyWalls.get(locationList.get(0)).get(locationList.get(1)).substring(3, 4);
            listofManyWalls.get(locationList.get(0)).set(locationList.get(1), s2);
        }
        return locationList;

    }

    public ArrayList<Integer> right (ArrayList<Integer>locationList){
        if (locationList.get(1) != (szer - 1)) {
            String s1 = listofManyWalls.get(locationList.get(0)).get(locationList.get(1)).substring(0, 1) + "0" + listofManyWalls.get(locationList.get(0)).get(locationList.get(1)).substring(2);
            listofManyWalls.get(locationList.get(0)).set(locationList.get(1), s1);
            locationList.set(1, locationList.get(1) + 1);  // nasz kolejny krok w prawo
            String s2 = listofManyWalls.get(locationList.get(0)).get(locationList.get(1)).substring(0, 3) + "0";
            listofManyWalls.get(locationList.get(0)).set(locationList.get(1), s2);
        }
        return locationList;
    }

    public ArrayList<Integer> down(ArrayList<Integer>locationList){
        if (locationList.get(0) != (dl - 1)) {
            String s1 = listofManyWalls.get(locationList.get(0)).get(locationList.get(1)).substring(0, 2) + "0" + listofManyWalls.get(locationList.get(0)).get(locationList.get(1)).substring(3);
            listofManyWalls.get(locationList.get(0)).set(locationList.get(1), s1);
            locationList.set(0, locationList.get(0) + 1);  // nasz kolejny krok do dołu
            String s2 = "0" + listofManyWalls.get(locationList.get(0)).get(locationList.get(1)).substring(1);
            listofManyWalls.get(locationList.get(0)).set(locationList.get(1), s2);
        }
        return locationList;
    }

    public ArrayList<Integer> left (ArrayList<Integer>locationList){
        if (locationList.get(1) != 0) {
            String s1 = listofManyWalls.get(locationList.get(0)).get(locationList.get(1)).substring(0, 3) + "0";
            listofManyWalls.get(locationList.get(0)).set(locationList.get(1), s1);
            locationList.set(1, locationList.get(1) - 1);  // nasz kolejny krok w lewo
            String s2 = listofManyWalls.get(locationList.get(0)).get(locationList.get(1)).substring(0, 1) + "0" + listofManyWalls.get(locationList.get(0)).get(locationList.get(1)).substring(2);
            listofManyWalls.get(locationList.get(0)).set(locationList.get(1), s2);
        }
        return locationList;
    }

    public ArrayList<Integer> upCheck (ArrayList<Integer> locationList) {
        if (locationList.get(0) != 0) {
            locationList.set(0, locationList.get(0) - 1);
        }
        return locationList;
    }

    public ArrayList<Integer> rightCheck (ArrayList<Integer> locationList) {
        if (locationList.get(1) != (szer - 1)) {
            locationList.set(1, locationList.get(1) + 1);
        }
        return locationList;
    }

    public ArrayList<Integer> downCheck (ArrayList<Integer> locationList) {
        if (locationList.get(0) != (dl - 1)) {
            locationList.set(0, locationList.get(0) + 1);
        }
        return locationList;
    }

    public ArrayList<Integer> leftCheck (ArrayList<Integer> locationList) {
        if (locationList.get(1) != 0) {
            locationList.set(1, locationList.get(1) - 1);
        }
        return locationList;
    }

    public static void main (String[]args){
        //Generator test = new Generator();
        //test.generate(10);
        //test.generate(5);
    }
}