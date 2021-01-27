package homework.figure;

public class Triangle {

	private double edge1, edge2, edge3;
	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		
//	}

	public Triangle(double num1, double num2, double num3) {
		edge1 = num1;
		edge2 = num2;
		edge3 = num3;
	}
	public void set(double num1, double num2, double num3) {
		edge1 = num1;
		edge2 = num2;
		edge3 = num3;
	}
	public double getadge1() {
		return edge1;
	}
	public double getadge2() {
		return edge2;
	}
	public double getadge3() {
		return edge3;
	}
	public double getPerimeter() {
		return edge1 + edge2 + edge3;
	}
	public double getArea() {
		double p = (edge1 + edge2 + edge3) / 2;
		double s = p * (p - edge1) * (p - edge2) * (p - edge3);
		return Math.sqrt(s);
	}
}
