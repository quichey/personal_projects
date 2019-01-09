package classes;

import java.util.ArrayList;
import java.util.List;

public class MakingChange {
    public static List<Integer> act(Integer[] denoms, Integer amount) {
        int currentDenomIndex = denoms.length - 1;
        Integer soFar = 0;
        ArrayList<Integer> change = new ArrayList<>();
        while (soFar < amount) {
            Integer current = denoms[currentDenomIndex];
            if (amount - soFar >= current) {
                soFar += current;
                change.add(current);
            } else {
                currentDenomIndex--;
            }
        }
        return change;
    }

    public static <T> void printAll(List<T> list) {
        String output = "[ ";
        for (int i = 0; i < list.size(); i++) {
            output += list.get(i);
            if (i != list.size() - 1) {
                output += ", ";
            }
        }
        output += " ]";
        System.out.print(output);
    }

    public static void main(String[] args) {
        Integer[] denoms = {1, 4, 8, 9};
        printAll(act(denoms, 70));
        List<String> s = new ArrayList<>();
    }
}
