package com.company;

import java.util.*;
import java.io.File;


public class Main {

    public static int menuData()
    {
        int selection;
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("Select your option:");
        System.out.println("===================");
        System.out.println("1 - Task 1");
        System.out.println("2 - Task 2");
        System.out.println("3 - Task 3");
        System.out.println("0 - Quit");

        System.out.println("Your choise: ");
        selection = sc.nextInt();
        return selection;
    }

    public static void fillingMatrix(Scanner scan, int[][] num, int rows, int columns)
    {
        System.out.println("Please enter elements in matrix : ");
        for(int a = 0; a < rows; a++)
        {
            for(int b = 0; b < columns; b++)
            {
                num[a][b] = scan.nextInt();
            }
        }
    }

    public static void printingMatrix(int[][] num, int rows, int columns)
    {
        System.out.println("Your matrix: ");
        for(int a = 0; a < rows; a++)
        {
            for(int b = 0; b < columns; b++)
            {
                System.out.print(num[a][b] + "\t");
            }
            System.out.println();
        }
    }

    public static boolean isPrime(int num)
    {
        int temp;
        for (int i=2; i<=num/2; i++) {
            temp = num % i;
            if (temp == 0) {
                return false;
            }
        }
        //System.out.println( num + " - is prime");
        return true;
    }

    public static List<Integer> maxPrimeColumn(int[][] num, int rows, int columns)
    {
        int maxColumn = 0;
        List<Integer> res = new ArrayList<Integer>();

        for(int b = 0; b < columns; b++)
        {
            for(int a = 0; a < rows; a++)
            {
                if(isPrime(num[a][b]) && num[a][b]>maxColumn)
                {
                    maxColumn = num[a][b];
                }
                else
                {
                    maxColumn = -1;
                }
            }
            res.add(maxColumn);
            maxColumn = 0;
        }
        return res;
    }

    public static void task1()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter number of matrix rows : ");
        int row = sc.nextInt();
        System.out.println("Please enter number of matrix columns : ");
        int col = sc.nextInt();
        int[][] numbers = new int[row][col];
        fillingMatrix(sc, numbers, row, col);
        printingMatrix(numbers, row, col);
        List<Integer> result = maxPrimeColumn(numbers, row, col);
        System.out.println(result);
    }

    public static void fillingStringArray(Scanner scan, String[] str, int count)
    {
        System.out.println("Please enter words: ");
        for(int i = 0; i < count; i++ )
        {
            str[i] = scan.next();
        }
    }

    public static void printStringArray(String[] str)
    {
        for(int i = 0; i < str.length; i++ )
        {
            System.out.println(str[i]+ "\t");
        }
    }

    public static String[] deletedCharacter(String[] str)
    {

        for (int i = 0; i < str.length; i++)
        {
            Character fCh = str[i].charAt(0);
            StringBuilder sb = new StringBuilder(str[i]);
            for (int j = 1; j < str[i].length(); j++)
            {
                Character nCh = str[i].charAt(j);
                if(Character.toLowerCase(nCh) == Character.toLowerCase(fCh))
                {
                    str[i] = sb.deleteCharAt(j).toString();
                }
            }
        }
        return str;
    }

    public static void task2()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter number of words : ");
        int count = sc.nextInt();

        String[] str = new String[count];

        fillingStringArray(sc, str, count);
        String[] result = deletedCharacter(str);
        printStringArray(result);
    }

    public static void readFromFile(Scanner scan, String filePath, ArrayList<Item> items)
    {
        try {
            var fileIn = new Scanner(new File(filePath)).useDelimiter("\n");
            while (fileIn.hasNext())
            {
                var split = fileIn.nextLine().split(";");
                if (Objects.equals(split[0], "Item"))
                {
                    items.add(new Item(split[1], split[2], Float.parseFloat(split[3])));
                }
                else if (Objects.equals(split[0], "CommonItem"))
                {
                    items.add(new CommonItem(split[1],split[2],Float.parseFloat(split[3]),Integer.parseInt(split[4]),Integer.parseInt(split[5])));
                }
                else if (Objects.equals(split[0], "WearItem"))
                {
                    items.add(new WearItem(split[1],split[2],Float.parseFloat(split[3]),split[4],split[5])
                    );
                }
            }
            fileIn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printClasses(ArrayList<Item> items)
    {
        for(int i = 0; i < items.size(); i++)
        {
            System.out.println();
            System.out.print(items.get(i).toString() + '\'');
        }
        System.out.println();
    }

    public static void sortByPrice(ArrayList<Item> items)
    {
        Collections.sort(items);
        System.out.println();
        System.out.println("After sort: ");
        printClasses(items);

    }

    public static void searchWearBySize(Scanner scaner, ArrayList<Item> items)
    {
        var founded = new ArrayList<Item>();

        System.out.println();
        System.out.println("Please enter size to search: ");
        String searchSize = scaner.next().toLowerCase();

        for (var itm: items) {
            if (itm instanceof WearItem && Objects.equals(((WearItem) itm).getSize().toLowerCase(), searchSize))
            {
                founded.add(itm);
            }
        }
        if (founded.size() != 0){
            System.out.println();
            System.out.println("Founded items: ");
            printClasses(founded);
        }
        else {
            System.out.println();
            System.out.println("No items was found: ");
        }


    }

    public static void printWithHighestDate(ArrayList<Item> items)
    {
        int maxDate = 0;
        for (var itm: items) {
            if (itm instanceof CommonItem && ((CommonItem) itm).getDueToDate() > maxDate)
            {
                maxDate = ((CommonItem) itm).getDueToDate();
            }
        }
        for (var itm: items) {
            if (itm instanceof CommonItem && ((CommonItem) itm).getDueToDate() == maxDate)
            {
                System.out.println(itm);
            }
        }

        System.out.println();
    }

    public static void task3()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter path to file: ");
        String filePath = sc.next();

        var items = new ArrayList<Item>();

        readFromFile(sc, filePath, items);
        System.out.println("Read classes: ");
        printClasses(items);
        sortByPrice(items);
        searchWearBySize(sc, items);
        printWithHighestDate(items);
    }

    public static void main(String[] args) {
        int userSelected;
        do {
            userSelected = menuData();
            switch (userSelected) {
                case 1 -> {
                    System.out.println('\'' + "Option 1 was selected." + '\'');
                    task1();
                }
                case 2 -> {
                    System.out.println('\'' + "Option 2 was selected." + '\'');
                    task2();
                }
                case 3 -> {
                    System.out.println('\'' + "Option 3 was selected." + '\'');
                    task3();
                }
                default -> {
                }
            }
        }
        while(userSelected <= 3 && userSelected >= 1);
    }
}
