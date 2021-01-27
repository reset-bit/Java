package homework.test;

public class Matrix {
	
	private double[][] matrix;
	private final int N = 5;
	
	Matrix(){
		matrix = new double[N][N];
		for(int i = 0; i < N; ++i) {
			for(int j = 0;j < N; ++j) {
				matrix[i][j] = 1;
			}
		}
	}
	
	public void set(int row, int col, double value) {
		matrix[row][col] = value;
	}
	
	public double get(int row, int col) {
		return matrix[row][col];
	}
	
	public int width() {
		return matrix[0].length;
	}
	
	public int height() {
		return matrix.length;
	}
	
	public Matrix add(Matrix b) {
		if(width() == b.width() && height() == b.height()) {
			Matrix a = new Matrix();
			for(int i = 0; i < height(); ++i) {
				for(int j = 0; j < width(); ++j) {
					a.set(i, j, matrix[i][j] + b.matrix[i][j]);
				}
			}
			return a;
		}
		else {
			return null;
		}
	}
	
	public Matrix multiply(Matrix b) {
		if(width() == b.height()) {
			Matrix a = new Matrix();
			//c矩阵的第i行第j列所对应的数值，等于当前矩阵的第i行分别乘以b矩阵的第j列之和
			for (int i = 0; i < height(); i++) {
				for (int j = 0; j < b.width(); j++) {
	            	for (int k = 0; k < b.height(); k++) {
	                	a.matrix[i][j] += matrix[i][k] * b.matrix[k][j];
	                }
	            }
	             
			}
			return a;
		}
		else {
			return null;
		}
	}
	
	public Matrix transpose() {
		Matrix a = new Matrix();
		for(int i = 0; i < height(); ++i) {
			for(int j = 0; j < width(); ++j) {
				a.set(j, i, matrix[i][j]);
			}
		}
		return a;
	}
	
	public double getMax() {
		double max = 0;
		int raw = 0;
		int col = 0;
		for(int i = 0; i < height(); ++i) {
			for(int j = 0; j < width(); ++j) {
				if(matrix[i][j] > max) {
					max = matrix[i][j];
					raw = i;
					col = j;
				}
			}
		}
		
		System.out.println(raw+"行"+col+"列");
		return max;
	}
	
	public void print() {
		for(int i = 0; i < height(); ++i) {
			for(int j = 0; j < width(); ++j) {
				System.out.print(matrix[i][j]+"   ");
			}
			System.out.println("");
		}
		System.out.println("");
	}
	
	public static void main(String[] args) {
		Matrix a = new Matrix();
		Matrix b = new Matrix();
		b.set(2, 3, 3);
		a.print();
		b.print();
		
		Matrix c = b.transpose();
		c.print();
		c = a.multiply(b);
		c.print();
	}
}
