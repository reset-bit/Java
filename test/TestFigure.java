package homework.test;

import homework.figure.*;
public class TestFigure {
	
	public static void main(String[] args) {
		Triangle triangle = new Triangle(3,4,5);
		Rectangular rectangular = new Rectangular(1,2);
		Round round = new Round(1);
		
		System.out.println("triangle : perimeter"+triangle.getPerimeter()+", area"+triangle.getArea());
		System.out.println("rectangular : perimeter"+rectangular.getPerimeter()+", area"+rectangular.getArea());
		System.out.println("round : perimeter"+round.getPerimeter()+", area"+round.getArea());
	}

}
