/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Hugo Jacobo
 */
public class Exercises {

    public static void main(String[] args) {
        Exercises ex = new Exercises();
        //   Scanner sc = new Scanner(System.in);
        //   String a = "";
        //    String r = "";
        //    System.out.println("Enter your string");
        //     a = sc.nextLine();

        //      System.out.println("Tu string =" + a);
        //     r = ex.getBackString(a);
        //      System.out.println("Tu string reverso =" + r);
        /* List<Integer> testList = new ArrayList();
        List<Integer> testList2 = new ArrayList();
        testList.add(0);
        testList.add(-1);
        testList.add(1);
        testList.add(-2);
        testList.add(2);
        testList.add(3);
        testList.add(5);
        testList.add(6);
        testList.add(-7);

        testList2 = ex.processList2(testList);

        System.out.println(testList);
        System.out.println(testList2);
        testList2.stream().forEach(System.out::println);
         */
        String aloh = "Hola amigo como estas";
        String t = null;

       t = ex.letters(aloh);
        System.out.println(t);

    }
// Method that receive a String value and returns the same string reversed

    public String getBackString(String valor) {

        String str = "";

        for (int i = valor.length() - 1; i >= 0; i--) {

            str += valor.charAt(i);

        }
        return str;
    }

    public List<Integer> processList(List<Integer> nums) {
        List<Integer> processBackup = new ArrayList<>();

        for (int i = 0; i < nums.size(); i++) {

            if (!processBackup.contains(nums.get(i))) {
                processBackup.add(nums.get(i));
            }

        }

        //define un metodo en una interfaz para poder utilizarla
        /*processBackup.sort(new Comparator() {
            public int compare(Object v1, Object v2) {
                return (Integer) v2 - (Integer) v1;
            }
        });  */
        //Envia directamente la instruccion metodo que quieres que haga el comparator
        //A esto se le llama Lambda Expresion
        // processBackup.sort((v1, v2) -> (Integer) v2 - (Integer) v1);
        //Se almacena el metodo del comparador en una variable y despues se utiliza la variable en el metodo sort
        Comparator c = ((v1, v2) -> (Integer) v2 - (Integer) v1);
        //processBackup.sort(c);

        // Se crea una instancia de la clase MyComparator y se coloca un metodo por referencia por medio de los ::
        MyComparator varia = new MyComparator();

        processBackup.sort(varia::compare);

        return processBackup.subList(0, 5);

    }

    public List<Integer> processList2(List<Integer> nums) {

        return nums.stream().distinct().sorted((i1, i2) -> i2 - i1).limit(5).collect(Collectors.toList());
    }

    public String letters(String palabra) {

        StringBuilder switcher = new StringBuilder();
        palabra.chars().mapToObj(c -> {
            char cc = (char) c;
            return Character.isUpperCase(cc) ? Character.toLowerCase(cc) : Character.toUpperCase(cc);
        }).forEach(c -> switcher.append(c));

        return switcher.toString();
    }
}
