import java.io.*;
public class TestFileWriterPrintunicode{
	public static void main(String[] args) {
		//FileWriter fw = null;
		BufferedWriter fw = null;
		try {
			//fw = new FileWriter("unicode.txt");
			fw= new BufferedWriter (new OutputStreamWriter (new FileOutputStream ("unicode.txt"), "UTF-8"));
			for(int i = 0; i <= 100; ++i) {
				fw.write(i);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				fw.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}