import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class codilityPiDayChallenge2023Solution {

    static int minDistinctLetter;



    public static List<List<Integer>> generateCombinations(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        generateCombinationsHelper(n, k, 1, current, result);
        return result;
    }

    public static void generateCombinationsHelper(int n, int k, int start, List<Integer> current, List<List<Integer>> result) {
        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i <= n; i++) {
            current.add(i);
            generateCombinationsHelper(n, k, i+1, current, result);
            current.remove(current.size()-1);
        }
    }
    public static int DistinctLetter(String output){

        char[] letters = output.toCharArray();
        Arrays.sort(letters);
        int distinctCount = 0;
        for (int i = 0; i < letters.length; i++) {
            if (i == 0 || letters[i] != letters[i-1]) {
                distinctCount++;
            }
        }
        return distinctCount;
    }
    public static void generateCombination(String P, int i, String Q, int j) {

//            System.out.printf("P: %s , i: %d , P at i: %c\n", P , k , P.charAt(k));
            List<List<Integer>> result_P = generateCombinations(P.length(), i);
            System.out.println(result_P);
            System.out.printf("length of result_P: %d\n", result_P.size() );

            String output = "";
//            for (int l = 0 ; l< i ; l++){
//                System.out.printf("P(0)(%d) : %c \n",l, P.charAt(result_P.get(0).get(l))-1) ;
//                char pChar = (char) (P.charAt(result_P.get(0).get(l))-1);
//                output += pChar;
//             }

//            System.out.printf("P(0)(0) : %c \n",P.charAt(result_P.get(0).get(0))-1);
            List<List<Integer>> result_Q = generateCombinations(Q.length(), j);
            System.out.println(result_Q);
            System.out.printf("length of result_Q: %d\n", result_Q.size() );
//            for (int g = 0 ; g< j ; g++){
//                System.out.printf("Q(0)(%d) : %c \n",g, Q.charAt(result_Q.get(0).get(g))-1) ;
//                char qChar = (char) (Q.charAt(result_Q.get(0).get(g))-1);
//                output += qChar;
//            }

        for (int w = 0 ; w < result_P.size() ; w++){  // cross product
            for (int v = 0 ; v < result_Q.size() ; v++){   // cross product
                for (int l = 0 ; l< i ; l++){
                    char pChar = (char) (P.charAt(result_P.get(w).get(l)-1));
                    output += pChar;
                }
                for (int g = 0 ; g< j ; g++){
                    char qChar = (char) (Q.charAt(result_Q.get(v).get(g)-1));
                    output += qChar;
                }
                System.out.println(output);
                int distinctLetters = DistinctLetter(output);
                System.out.printf("distinct letters count: %d\n", distinctLetters);
                if (distinctLetters < minDistinctLetter){
                    minDistinctLetter = distinctLetters ;


                }
                output = "";
            }
        }





    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String P = scanner.next();
            String Q = scanner.next();
            // 2 : 1 + 1
            // 3 : 1 + 2 , 2 + 1
            // 4 : 1 + 3 , 2 + 2 , 3 + 1
            // 5 : 1 + 4 , 2 + 3 , 3 + 2 , 4 + 1
           // ArrayList<String> sequence = new ArrayList<String>();
            if (P.equals(Q)) {
                System.out.printf("Minimum distinct letter count is now: %d\n", P.length());
            } else {
                int N = P.length();
                minDistinctLetter = P.length();
                for (int i = 1; i < N; i++) {
                    int j = N - i;
                    System.out.println(i + " + " + j);
                    generateCombination(P, i, Q, j);
                }

                System.out.printf("Minimum distinct letter count is now: %d\n", minDistinctLetter);
            }


        }

    }
}

/**
P = abc
Q = bcd

 P[1] + Q[1,2] = abc
 P[1] + Q[1,3] = abd
 P[1] + Q[2,3] = acd
 P[2] + Q[1,2] = bbc
 P[2] + Q[1,3] = bbd
 P[2] + Q[2,3] = bcd
 P[3] + Q[1,2] =
 P[1,3] + Q[2] = acc

 P[2,3] + Q[2] = bcc
 P[2,3] + Q[3] = bcd

**/
