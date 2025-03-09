// Parent class
class Animal {
    String name;

    // Parent class constructor
    Animal(String name) {
        this.name = name;
        System.out.println("Animal constructor: " + name);
    }
    // Parent class method
    void makeSound() {
        System.out.println("Some sound...");
    }
}

// Child class
class Dog extends Animal {
    String breed;

    // Child class constructor
    Dog(String name, String breed) {
        this.breed = breed;
        super(name);  // Calls the constructor of Animal
        super.makeSound(); // Calls the function of the super class
        
        System.out.println("Dog constructor: " + breed);
    }

    String name;
    // Another use of super
    void printNames() {
       // this.name = "Indian";
        System.out.println("Child class name: " + name); // Refers to Dog's name
        System.out.println("Parent class name: " + super.name); // Refers to Animal's name
    }
}

public class Puppy extends Dog, Animal {
    int age;
    Puppy (int age){
        this.age = age;
        System.out.println("Puppy"); 
    }
}

public class Inherit2 {
    public static void main(String[] args) {
        Dog dog = new Dog("Buddy", "Labrador");
        Puppy mypup = new Puppy (5);
        
        dog.printNames ();
    }
}
