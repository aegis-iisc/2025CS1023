// Polymorphism (Compile time)

class MathUtils {
    // Overloaded add methods
    int add(int a, int b) {
        return a + b;
    }

    double add(double a, double b) {
        return a + b;
    }
}

// Runtime polymprohism
class Animal {
    void makeSound() {
        System.out.println("Some generic sound...");
    }
}

class Cat extends Animal {
    @Override
    void makeSound() {
        System.out.println("Meow!");
    }
}


class Dog extends Animal {
   @Override
    void makeSound() {
        System.out.println("Woof! Woof!");
    }
        
   
}



public class Overload {
    public static void main(String[] args) {
        MathUtils math = new MathUtils();
        System.out.println(math.add(2, 3));        
        System.out.println(math.add(2.5, 3.5));   



        Animal myAnimal = new Cat();
        myAnimal.makeSound(); // Output: Meow!

        Animal myanotherAnimal = new Dog ();
        myanotherAnimal.makeSound ();
    }
}
