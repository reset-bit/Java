import java.io.*;
public class TestFileStreamCopypdf {
	public static void main(String[] args) {
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			in = new FileInputStream("testfilestreamcopypdf.pdf");
			out = new FileOutputStream("cdp.pdf");
			int b = 0;
			while((b = in.read()) != -1) {
				out.write(b);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				in.close();
				out.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}