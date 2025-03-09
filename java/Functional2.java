@FunctionalInterface
interface Calculator {
    int add(int a, int b);  // Single abstract method
}


public class Functional2 {
    public static void main(String[] args) {
        // Using Lambda Expression
        Calculator calc = (a, b) -> a + b;
        System.out.println("Sum: " + calc.add(5, 10));
    }
}

