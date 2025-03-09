// Parent class
class Animal {
    private void makeSound() {
        System.out.println("Some sound...");
    }
}

// Child class
class Dog extends Animal {
    void bark() {
        System.out.println("Woof! Woof!");
    }
}




public class Inherit {
    public static void main(String[] args) {
        Dog myDog = new Dog();
        myDog.makeSound(); // 
        myDog.bark();      // 
    }
}
