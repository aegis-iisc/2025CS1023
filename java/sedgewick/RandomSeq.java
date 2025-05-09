

/******************************************************************************
 * Take user input
 *  Compilation:  javac RandomSeq.java
 *  Execution:    java RandomSeq n
 *
 *  Prints n random real numbers between 0 and 1.
 *
 *  % java RandomSeq 5
 *  0.1654760343787165
 *  0.6212262060326124
 *  0.631755596883274
 *  0.4165639935584283
 *  0.4603525361488371
 *
 ******************************************************************************/

public class RandomSeq {
    public static void main(String[] args) {

        // command-line argument
        int n = Integer.parseInt(args[0]);

        // generate and print n numbers between 0 and 1
        for (int i = 0; i < n; i++) {
            System.out.println(Math.random());
        }
    }
}
