package logicSimulator;
import java.nio.channels.Pipe;
import java.util.*;

import static org.ejml.EjmlUnitTests.assertEquals;

public class MapTeste {

    MapTeste(){

    }

    public long factorialUsingRecursion(int n) {
        if (n <= 2) {
            return n;
        }
        return n * factorialUsingRecursion(n - 1);
    }

    public long combination(int n, int p){
        int combination = (int) (factorialUsingRecursion(n)/ ((factorialUsingRecursion(p) * (factorialUsingRecursion(n-p)))));
        return combination;
    }

    private static void combinationPassx(int signal_size, int times){
        ArrayList <Integer> x = new ArrayList<>();
        int pivot = 0;
        for (int i = 1; i <= signal_size; i++) {
            x.add(i);
            for (pivot = i; pivot < signal_size; pivot+= times) {
                ArrayList<Integer> faultList = new ArrayList<>();
                for (int element = pivot + 1; element < signal_size; element++) {
                    faultList.add(element);
                    System.out.println("Pivot: " + pivot + "  - " + element + "   list: " + faultList);
                }

                System.out.println("--------end Pivot -------");

            }
        }
    }

    private static ArrayList<Integer> combinationPassNew(int signal_size, int init){
        ArrayList <Integer> x = new ArrayList<>();

        for (int i = init; i <= signal_size; i++) {
                x.add(i);
        }
        System.out.println("--------end Pivot -------");
        return x;
    }


    private static void helper(List<int[]> combinations, int data[], int start, int end, int index) {
        if (index == data.length) {
            int[] combination = data.clone();
            combinations.add(combination);
        } else if (start <= end) {
            data[index] = start;
            helper(combinations, data, start + 1, end, index + 1);
            helper(combinations, data, start + 1, end, index);
        }
    }

    public static List<int[]> generate(int n, int r) {
        List<int[]> combinations = new ArrayList<>();
        helper(combinations, new int[r], 0, n-1, 0);
        return combinations;
    }



    public static void main(String[] args) throws Exception {

        ArrayList<Integer> x = new ArrayList<>();
        int signalSize = 11;
        int pairs = 1;


        for (int i = 0; i < 11; i++) {


            List<int[]> combinations = generate(signalSize, i);

            System.out.println(combinations);

            for (int[] combination : combinations) {
                System.out.println(Arrays.toString(combination));

                // for (int element = 0; element < combination.length; element++) {
                //     System.out.println(combination[element]);
                // }
            }

            System.out.printf("generated %d combinations of %d items from %d ", combinations.size(), signalSize, i+1);


        }
    }
    }



