 // An interface is like a contract that defines a set of methods a class must implement. 
 //Interfaces provide loose coupling and allow multiple inheritance.
/*
Syntax: 
public interface name {
    public type name(type name, ..., type name);
    public type name(type name, ..., type name);
    ...
    public type name(type name, ..., type name);
}


*/

 // First Interface
interface Engine {
    void startEngine();
}

// Second Interface
interface MusicSystem {
    void playMusic();

    // Default method can have implementation
    default void showInfo() {
        System.out.println("This is a MusicSystem.");
    }
}

// Implementing multiple interfaces in a class
class SmartCar implements Engine, MusicSystem {
    @Override
    public void startEngine() {
        System.out.println("SmartCar engine started.");
    }

    @Override
    public void playMusic() {
        System.out.println("Playing music in SmartCar.");
    }
}

public class Abstraction2 {
    public static void main(String[] args) {
        SmartCar myCar = new SmartCar();
        myCar.startEngine();
        myCar.playMusic();
        myCar.showInfo ();
    }
}
