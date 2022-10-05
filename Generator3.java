package Projekt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Generator3 {
    int dl = 10;
    int szer = 10;
    Random rand = new Random();

    ArrayList<ArrayList<String>> listofManyWalls = new ArrayList<>();
    ArrayList<ArrayList<Integer>> memory = new ArrayList<>();

    public ArrayList<ArrayList<String>> generate(int size) {
        listofManyWalls.clear();

        dl = size;
        szer = size;

        createGrid();

        ArrayList<Integer> startSize = new ArrayList<>(Arrays.asList(0,size-1,0,size-1));

        divide(startSize);

        listofManyWalls.get(0).set(0,listofManyWalls.get(0).get(0).substring(0,3) + "0");
        listofManyWalls.get(size - 1).set(size - 1,listofManyWalls.get(size - 1).get(size - 1).substring(0,1) + "0" + listofManyWalls.get(size - 1).get(size - 1).substring(2));

        return listofManyWalls;
    }

    public void createGrid() {
        for(int i=0; i<dl; i++){
            ArrayList<String> temporaryList = new ArrayList<>();
            for(int j=0; j<szer; j++){
                String temporaryS;
                if (i == 0 && j == 0) {
                    temporaryS = "1001";
                } else if (i == 0 && j == szer - 1) {
                    temporaryS = "1100";
                } else if (i == dl - 1 && j == 0) {
                    temporaryS = "0011";
                } else if (i == dl - 1 && j == szer - 1) {
                    temporaryS = "0110";
                } else if(i == 0){
                    temporaryS = ("1000");
                } else if (j == szer - 1) {
                    temporaryS = ("0100");
                } else if (i == dl - 1) {
                    temporaryS = ("0010");
                } else if (j == 0) {
                    temporaryS = ("0001");
                } else {
                    temporaryS = ("0000");
                }
                temporaryList.add(temporaryS);
            }
            listofManyWalls.add(temporaryList);
        }
        //System.out.println(listofManyWalls);
    }

    public void divide(ArrayList<Integer> Area) {
//        counter ++;
//        System.out.println(counter);
        //System.out.println(memory);

        int y1 = Area.get(0);
        int y2 = Area.get(1);
        int x1 = Area.get(2);
        int x2 = Area.get(3);

        //System.out.println("y1: " + y1 + "| y2: " + y2 + "| x1: " + x1 + "| x2: " + x2);

        if (y2 - y1 > 0 && x2 - x1 > 0) {

            int randomElement;

            if ((x2 - x1) > (y2 - y1)) {
                randomElement = 0;
            } else if ((y2 - y1) > (x2 - x1)) {
                randomElement = 1;
            } else {
                randomElement = rand.nextInt(2);
            }
            switch (randomElement) {
                case 0 -> {
                    int l = x2 - x1;
                    int randomNum = x1 + 1 + rand.nextInt(l);
                    //System.out.println("Rysowanie ściany dla:" + randomNum);
                    int draw = y2 - y1 + 1;
                    int yDrawing = y1;
                    while (draw > 0) {
                        listofManyWalls.get(yDrawing).set(randomNum, listofManyWalls.get(yDrawing).get(randomNum).substring(0, 3) + "1");
                        listofManyWalls.get(yDrawing).set(randomNum - 1, listofManyWalls.get(yDrawing).get(randomNum - 1).substring(0, 1) + "1" + listofManyWalls.get(yDrawing).get(randomNum - 1).substring(2, 4));
                        if (yDrawing < y2) {
                            yDrawing++;
                        }
                        draw--;
                    }
                    int addPath = rand.nextInt(y2 - y1) + y1;
                    listofManyWalls.get(addPath).set(randomNum, listofManyWalls.get(addPath).get(randomNum).substring(0, 3) + "0");
                    listofManyWalls.get(addPath).set(randomNum - 1, listofManyWalls.get(addPath).get(randomNum - 1).substring(0, 1) + "0" + listofManyWalls.get(addPath).get(randomNum - 1).substring(2, 4));
                    ArrayList<Integer> nextToDivide = new ArrayList<>(Arrays.asList(y1, y2, x1, randomNum - 1));
                    if ((x2 - randomNum) > 0 && (y2 - y1) > 0) {
                        ArrayList<Integer> field = new ArrayList<>(Arrays.asList(y1, y2, randomNum, x2));
                        memory.add(field);
                    }
                    divide(nextToDivide);
                }
                case 1 -> {
                    int l = y2 - y1;
                    int randomNum = y1 + 1 + rand.nextInt(l);
                    //System.out.println("Rysowanie ściany dla:" + randomNum);
                    int draw = x2 - x1 + 1;
                    int xDrawing = x1;
                    while (draw > 0) {
                        listofManyWalls.get(randomNum).set(xDrawing, "1" + listofManyWalls.get(randomNum).get(xDrawing).substring(1));
                        listofManyWalls.get(randomNum - 1).set(xDrawing, listofManyWalls.get(randomNum - 1).get(xDrawing).substring(0, 2) + "1" + listofManyWalls.get(randomNum - 1).get(xDrawing).substring(3));
                        if (xDrawing < x2) {
                            xDrawing++;
                        }
                        draw--;
                    }
                    int addPath = rand.nextInt(x2 - x1) + x1;
                    listofManyWalls.get(randomNum).set(addPath, "0" + listofManyWalls.get(randomNum).get(addPath).substring(1));
                    listofManyWalls.get(randomNum - 1).set(addPath, listofManyWalls.get(randomNum - 1).get(addPath).substring(0, 2) + "0" + listofManyWalls.get(randomNum - 1).get(addPath).substring(3));
                    ArrayList<Integer> nextToDivide = new ArrayList<>(Arrays.asList(y1, randomNum - 1, x1, x2));
                    if ((y2 - randomNum) > 0 && (x2 - x1) > 0) {
                        ArrayList<Integer> field = new ArrayList<>(Arrays.asList(randomNum, y2, x1, x2));
                        memory.add(field);
                    }
                    divide(nextToDivide);
                }
            }
        } else if (memory.size() != 0) {
            ArrayList<Integer> Remember = new ArrayList<>(memory.get(memory.size() - 1));
            memory.remove(memory.size() - 1);
            divide(Remember);
        } else {
            System.out.println("Proces generowania labiryntu się zakończył");
        }
    }


    public static void main (String[]args){
//        Generator3 test3 = new Generator3();
//        test3.generate(80);
    }
}