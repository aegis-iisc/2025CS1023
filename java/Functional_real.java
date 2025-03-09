
//Predicate and Function to filter even numbers and square them.

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // generic types in Java
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Predicate to check if a number is even
        Predicate<Integer> isEven = num -> num % 2 == 0;

        // Function to square a number
        Function<Integer, Integer> square = num -> num * num;

        // Filtering even numbers and squaring them
        List<Integer> squaredEvenNumbers = numbers.stream()
                .filter(isEven)
                .map(square)
                .collect(Collectors.toList());

        System.out.println(squaredEvenNumbers);
    }
}


https://www.cl.cam.ac.uk/teaching/2122/OOProg/oop-slides.pdf