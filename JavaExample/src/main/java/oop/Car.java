package oop;

public class Car {
    private String brand;
    private int doors;
    private int speed = 0;
    //private String owner;
    private String ownerFirstname;
    private String ownerLastname;

    public String getOwner() {
        return this.ownerLastname + ", "+ this.ownerFirstname;
    }

    public void setOwner(String owner) {
        String[] nameValues = owner.split(" ");
        this.ownerFirstname = nameValues[0];
        this.ownerLastname = nameValues[1];
    }

    public Car() {
        System.out.println("new car is created");
        this.brand = "Unknown";
        this.doors = 0;
        this.speed = 0;
        // static counter
    }

    public Car(String brand, int doors, int speed) {
        this.brand = brand;
        this.doors = doors;
        this.speed = speed;
    }


    public void drive() {
        System.out.println("Brumm");
    }

    public void printDetails() {
        System.out.println("Brand: " + brand + ", Doors:" + doors + ", Speed: " + speed);
    }

    public void accelerate(int difference) {
        speed = speed + difference;
        if (speed < 0) {
            speed = 0;
        }
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        if (speed < 0) {
            this.speed = 0;
        } else {
            this.speed = speed;
        }

    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }


}
