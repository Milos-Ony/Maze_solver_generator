package Projekt;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solver {
    File file;
    String data = "";
    ArrayList<String> cell = new ArrayList<>();
    ArrayList<ArrayList<String>> cells = new ArrayList<>();
    ArrayList<ArrayList<Integer>> path = new ArrayList<>();
    ArrayList<ArrayList<Integer>> badPath = new ArrayList<>();
    ArrayList<Integer> start = new ArrayList<>(Arrays.asList(0,0));
    ArrayList<Integer> destination = new ArrayList<>();
    boolean fin = true;
    boolean save = true;
    int back = 2;
    int movesCounter = 0;
    int size = 0;

    public boolean CheckIfInt(double z) {
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

    public void read(String file_str) {
        file = new File(file_str);

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
        if (CheckIfInt(Math.sqrt(cell.size()))) {
            int step = (int) Math.sqrt(cell.size());
            int listInt = 0;
            for (int j = 0; j < cell.size()/step; j++) {
                ArrayList<String> listList = new ArrayList<>();
                List <String> temporaryList = cell.subList(listInt, listInt + step);
                listList.addAll(temporaryList);
                cells.add(listList);
                listInt += step;
            }
        } else {
            System.out.println("Podana labirynt ma niepoprawne wymiary - nie jest kwadratem.");
        }
        System.out.println(cells);
    }

    public ArrayList<ArrayList<Integer>> solveWithList(ArrayList<ArrayList<String>> List){
        cells.addAll(List);
        size = cells.get(0).size();
        destination.addAll(Arrays.asList(size - 1, size - 1));
        solve();

        ArrayList<ArrayList<Integer>> Result = new ArrayList<>(path);
        for (ArrayList<Integer> i: badPath) {
            Result.remove(i);
        }

        path.clear();
        badPath.clear();
        destination.clear();

        fin = true;
        save = true;

        movesCounter = 0;
        back = 2;

        return Result;
    }

    public ArrayList<ArrayList<Integer>> solveWithFile(String filePath){
        read(filePath);
        size = cells.get(0).size();
        destination.addAll(Arrays.asList(size - 1, size - 1));
        solve();

        ArrayList<ArrayList<Integer>> Result = new ArrayList<>(path);
        for (ArrayList<Integer> i: badPath) {
            Result.remove(i);
        }

        cell.clear();
        path.clear();
        badPath.clear();
        destination.clear();

        fin = true;
        save = true;

        movesCounter = 0;
        back = 2;

        return Result;
    }

    //up
    public ArrayList<Integer> up(ArrayList<Integer> location) {
        if(location.get(0) != 0) {
            location.set(0, location.get(0) - 1); //y
            location.set(1, location.get(1)); //x
        }
        return location;
    }

    //right
    public ArrayList<Integer> right(ArrayList<Integer> location) {
        if(location.get(1) != size - 1) {
            location.set(0, location.get(0)); //y
            location.set(1, location.get(1) + 1); //x
        }
        return location;
    }

    //down
    public ArrayList<Integer> down(ArrayList<Integer> location) {
        if(location.get(0) != size - 1) {
            location.set(0, location.get(0) + 1); //y
            location.set(1, location.get(1)); //x
        }
        return location;
    }

    //left
    public ArrayList<Integer> left(ArrayList<Integer> location) {
        if(location.get(1) != 0) {
            location.set(0, location.get(0)); //y
            location.set(1, location.get(1) - 1); //x
        }
        return location;
    }

    public ArrayList<Integer> move(ArrayList<Integer> currentPos) {
        save = true;
        String walls = cells.get(currentPos.get(0)).get(currentPos.get(1));
        List<Character> chars = walls.chars().mapToObj(e -> (char) e).collect(Collectors.toList());
        ArrayList<Integer> quickCheckU = new ArrayList<>(currentPos);
        ArrayList<Integer> quickCheckR = new ArrayList<>(currentPos);
        ArrayList<Integer> quickCheckD = new ArrayList<>(currentPos);
        ArrayList<Integer> quickCheckL = new ArrayList<>(currentPos);
        if (currentPos.equals(destination)) {
            System.out.println("Udało się dojść do końca!");
            fin = false;
            save = false;
            return currentPos;
        } else if (chars.get(0) == '0' & !path.contains(up(quickCheckU))) {
            back = 2;
            return up(currentPos);
        } else if (chars.get(1) == '0' & !path.contains(right(quickCheckR))) {
            back = 2;
            return right(currentPos);
        } else if (chars.get(2) == '0' & !path.contains(down(quickCheckD))) {
            back = 2;
            return down(currentPos);
        }  else if (chars.get(3) == '0' & !path.contains(left(quickCheckL))) {
            back = 2;
            return left(currentPos);
        } else {
            ArrayList<Integer> removablePath = new ArrayList<>(currentPos);
            badPath.add(removablePath);
            currentPos.set(0, path.get(path.size()-back).get(0));
            currentPos.set(1, path.get(path.size()-back).get(1));
            save = false;
            back++;
            return currentPos;
        }
    }

    public void solve() {
        ArrayList<Integer> pos = new ArrayList<>(start);
        path.add(start);
        while(fin) {
            movesCounter++;
            ArrayList<Integer> secPos = new ArrayList<>(move(pos));
            if (save) {
                path.add(secPos);
            }
        }
        ArrayList<ArrayList<Integer>> cleanPath = new ArrayList<>(path);
        for (ArrayList<Integer> i: badPath) {
            cleanPath.remove(i);
        }
        //System.out.println(path);
        //System.out.println(badPath);
        //System.out.println("Rozwiązanie labiryntu to: " + cleanPath);
        //System.out.println("Licznik wszystkich ruchów wykonanych przez program: " + movesCounter);
    }


    public static void main (String[] args) {
        Solver test = new Solver();
        test.solveWithFile("test2.txt");
        System.out.println(test.solveWithFile("test2.txt"));
        //test.solveWithFile("test2.txt");
    }
}