
// The primary unit is a Class

class Car {
    private String brand;
    String model;
    int year;

    // Constructor
    public Car(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    // Method to display car details
    public void displayInfo() {
        System.out.println(year + " " + brand + " " + model);
    }


    public void changeBrand (String brand) {
        this.brand = brand;
    }
}

public class Intro {
    public static void main(String[] args) {
        Car car1 = new Car("Toyota", "Corolla", 2022);
        Car car2 = new Car("Honda", "Civic", 2023);
        

        car1.displayInfo(); 
        car2.displayInfo(); 



        car1.changeBrand("Hyundai");
        car1.displayInfo(); 

    }
}
