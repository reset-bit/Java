package homework.test;

/**
 *用户在键盘每输入一行文本，程序将这段文本显示在控制台中。
 *当用户输入的一行文本是“exit”（不区分大小写）时，程序将用户所有输入的文本都写入到文件log.txt中，并退出。
 *（要求：控制台输入通过流封装System.in获取，不要使用Scanner）
 *
 *@author Reset
 *@time 2020/07/13
 * */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToTxt {
	static int N  = 10;
	
	public static void main(String[] args) {
		BufferedWriter bw = null;
		try {
			int b;
			int i = 0;
			String str[] = new String[N];
			while(true) {
				boolean enter = false;
				
				while(true) {
					b = System.in.read();
					System.out.print((char)b);
					
					if(str[i] == null) {
						str[i] = String.valueOf((char)b);
					}
					else {
						if((char)b == '\r') {
							enter = true;
						}
						str[i] += (char)b;
					}
					
					if(enter && ((char)b == '\n')) {
						break;
					}
				}
				System.out.println();
				if(str[i].equals("exit\r\n")) {
					break;
				}
				++i;
			}
			
			bw = new BufferedWriter(new FileWriter("d:\\log.txt"));
			for(int j = 0;j <= i; ++j) {
				bw.write(str[j]);
			}
			bw.flush();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
