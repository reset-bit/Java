package homework.figure;

public class Round {
	private double radius;
	public static final double PI = 3.1415926;//常量代替，易于维护
	
	public Round(double num) {
		radius = num;
	}
	
	public void set(double num) {
		radius = num;
	}
	
	public double get() {
		return radius;
	}
	
	public double getPerimeter() {
		return 2 * PI * radius;
	}
	
	public double getArea() {
		return PI * radius * radius;
	}
}
