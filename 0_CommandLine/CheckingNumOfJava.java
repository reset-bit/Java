import java.io.*;
import java.lang.String;
import java.util.*;
public class CheckingNumOfJava {
	public static void main(String[] args) {
		BufferedReader br = null;
		OutputStreamWriter ow = null;
		try {
			int num = 0;
			br = new BufferedReader(new FileReader("article.txt"));
			
			String s;
			while(null != (s = br.readLine())) {
				if(null == s){continue;}
				String[] w = s.split(" ");
				for(int i = 0; i < w.length; ++i) {
					String word = w[i].toLowerCase();
					if(word.charAt(word.length() - 1) < 'a' || word.charAt(word.length() - 1) > 'z') {
						word = word.substring(0, word.length() - 1);
					}
					word = word.trim();
					if("java".equals(word)) {
						++num;
					}
				}
			}

			System.out.println(num);

			ow = new OutputStreamWriter(new FileOutputStream("numOfJava.txt"), "unicode");
			ow.write(num);
		}catch(IOException e) {
			System.out.println(e);
		}finally {
			try {
				br.close();
				ow.close();
			}catch(IOException e) {
				System.out.println(e);
			}
		}
	}
}