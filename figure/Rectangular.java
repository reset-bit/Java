package homework.figure;

public class Rectangular {
	private double length, width;//area可由length与width计算出，不应该作为属性，作为属性是冗余的，除非提高效率

	public Rectangular(double num1, double num2) {
		length = num1;
		width = num2;
	}
	public void set(double num1, double num2) {
		length = num1;
		width = num2;
	}
	public double getlength() {//区分get和show
		return length;
	}
	public double getwidth() {
		return width;
	}
	public double getPerimeter() {
		return 2 * (length + width);
	}
	public double getArea() {
		return length * width;
	}

}
