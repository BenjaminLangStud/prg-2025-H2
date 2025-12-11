package oop;

public class CarExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Car Example");

		Car tesla = new Car();
		tesla.printDetails();
		
		tesla.setBrand("Tesla");
		tesla.setDoors(4);
		tesla.setSpeed(100);

        tesla.setOwner("Donald Duck");
        System.out.println(tesla.getOwner());
		//tesla.printDetails();
/*
		Car porsche = new Car();
		porsche.setBrand("Porsche");
		porsche.setDoors(2);
        porsche.setSpeed(50);
		
		porsche.printDetails();
		
		porsche.accelerate(-10000);
		
		porsche.printDetails();

		//tesla.printDetails();
*/

	}

}
