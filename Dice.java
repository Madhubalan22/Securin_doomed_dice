import java.util.Arrays;
import java.util.Scanner;

public class Dice {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the number of dice you want to roll: ");
        int noOfFaces =sc.nextInt();
        //System.out.println("----------Part A-----------");
        
        // Part A || 1st Question|| Total Combinations
        int[] Die_A = new int[noOfFaces];
        for(int faceIndex=0;faceIndex < noOfFaces;faceIndex++){
            Die_A[faceIndex]=faceIndex+1;
        }
        int[] Die_B = Die_A;
        System.out.println("Dice A:"+Arrays.toString(Die_A));
        System.out.println("Dice B:"+Arrays.toString(Die_B));
        int totalCombinations = Die_A.length * Die_B.length;
        System.out.println();
        System.out.println("Total Possible Combination for 6*6 Matrix: " + totalCombinations);

        // Part A || 2nd Question|| List of Combinations
        System.out.println();
        System.out.println("Distribution of all possible combinations");
        for (int die_a_faceValue = 1; die_a_faceValue <= Die_A.length; die_a_faceValue++) {
            for (int die_b_faceValue = 1; die_b_faceValue <= Die_B.length; die_b_faceValue++) {
                System.out.print("(" + die_a_faceValue + "," + die_b_faceValue + ")");
            }
            System.out.println();
        }

        // Part A || 3rd Question || Probability of all Possible Sums
        
        int maxSum = Die_A.length + Die_B.length;
        int[] distribution = new int[maxSum * 2];
        for (int i : Die_A) {
            for (int j : Die_B) {
                int sum = i + j;
                distribution[sum]++;
            }
        }
        System.out.println();
        System.out.print("Probability of all Possible Sums");
        for(int sumProbabilities=2; sumProbabilities<=maxSum; sumProbabilities++){
            System.out.printf("%nP(Sum = %d) = %d/%d",
                sumProbabilities,distribution[sumProbabilities],totalCombinations);
        }

        //call all the function to find the exact match of probability of
        //both regular dice and spots removed dice
        int[] combinationDistribution2 = numberOfOccurence(Die_A, Die_B);
        int[][] allDice = allDiceCombination();
        for (int[] die1 : allDice) {
            outerloop: for (int[] die2 : allDice) {
                int[] combinationDistribution1 = numberOfOccurence(die1, die2);
                //main condition for dieA 
                for (int face : die1){
                    if (face >4) {
                        break outerloop;
                    }
                }
                if (areProbabilityOfDiceSame(combinationDistribution1, combinationDistribution2)) {
                    System.out.print("\nNew Die_A: ");
                    for (int num : die1) {
                        System.out.print(num + " ");
                    }
                    System.out.print("\nNew Die_B: ");
                    for (int num : die2) {
                        System.out.print(num + " ");
                    }
                }
            }
        }
    }


    //calculate the number of 6 Occurence for each dice in the combination
    public static int[] numberOfOccurence(int[] newDieA, int[] newDieB) {
        int arraySize = newDieA.length + newDieB.length;
        int[] distribution = new int[arraySize * 2];
        for (int indexA : newDieA) {
            for (int indexB : newDieB) {
                int sum = indexA + indexB;
                distribution[sum]++;
            }
        }
        return distribution;
    }

    //Check the probability of regular and undoomed dice are equal
    public static boolean areProbabilityOfDiceSame(int[] spotsRemovedDice, int[] regulardice) {
        if (spotsRemovedDice.length != regulardice.length) {
            return false;
        }
        for (int sumIndex = 0; sumIndex < regulardice.length; sumIndex++) {
            if (spotsRemovedDice[sumIndex] != regulardice[sumIndex]) {
                return false;
            }
        }
        return true;
    }

    public static int[][] allDiceCombination() {
        int count = 1287;// number of possible combination that the dice can have
        int[][] allPossibleCombinationOfDice = new int[count][6];
        count = 0;
        for (int allPosibbleWays_2nd = 2; allPosibbleWays_2nd <= 10; allPosibbleWays_2nd++) {
            for (int allPosibbleWays_3rd = allPosibbleWays_2nd; allPosibbleWays_3rd <= 10; allPosibbleWays_3rd++) {
                for (int allPosibbleWays_4th = allPosibbleWays_3rd; allPosibbleWays_4th <= 10; allPosibbleWays_4th++) {
                    for (int allPosibbleWays_5th = allPosibbleWays_4th; allPosibbleWays_5th <= 10; allPosibbleWays_5th++) {
                        for (int allPosibbleWays_6th = allPosibbleWays_5th; allPosibbleWays_6th <= 10; allPosibbleWays_6th++) {
                            allPossibleCombinationOfDice[count++] = new int[] { 1, allPosibbleWays_2nd, allPosibbleWays_3rd,
                                         allPosibbleWays_4th, allPosibbleWays_5th, allPosibbleWays_6th };
                        }
                    }
                }
            }
        }
        return allPossibleCombinationOfDice;
    }
}
