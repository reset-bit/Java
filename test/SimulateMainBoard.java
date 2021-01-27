package homework.test;

/**
 * 模拟主板
 * 
 * @author Reset
 * @date 2020/5/9 晚
 * */
import java.util.Scanner;
public class SimulateMainBoard{
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String mainBoardModel = scan.next();//用户输入主板的型号
		String mousePS2Model = scan.next();//用户输入PS2口鼠标的型号
		String keyBoardPS2Model = scan.next();//用户输入PS2口键盘的型号
		String flashDiskModel = scan.next();//用户输入U盘的型号
		
		MainBoard mainboard = new MainBoard(mainBoardModel);//生成主板对象
		
		KeyBoardPS2 keyboard = new KeyBoardPS2(keyBoardPS2Model);//生成PS2口键盘对象
		mainboard.setPs2(keyboard);//将键盘接入主板的PS2口
		mainboard.inputFromPS2();//获取从键盘录入的数据（注意：是模拟从键盘录入数据，不需要编码利用Scanner接收数据）
		mainboard.setPs2(null);//拔出PS2口键盘
		
		MousePS2 mouse = new MousePS2(mousePS2Model);//生成PS2口的鼠标对象
		mainboard.setPs2(mouse);//将鼠标接入主板的PS2口
		mainboard.inputFromPS2();//获取从键盘录入的数据
		//////////////////////////////////////////////////////////
		FlashDisk flashdisk = new FlashDisk(flashDiskModel);//生成U盘对象
		mainboard.setUsb(flashdisk);//在主板上插入U盘的USB口
		mainboard.read();//从外接设备（USB或TypeC口设备上读取数据，只要接入相关设备，则从该设备读取数据）
		mainboard.setUsb(null);//拔下U盘：flashdisk
		
		mainboard.setTypeC(flashdisk);//将U盘：flashdisk插入主板的TypeC口（FlashDisk的对象同时支持USB和TypeC口）
		mainboard.read();//从外接设备（USB或TypeC口设备上读取数据，只要接入相关设备，则从该设备读取数据）
		
		MouseUSB mouseUSB = new MouseUSB();//生成USB鼠标
		mainboard.setUsb(mouseUSB);//在主板上插入USB鼠标的USB口
		
	    scan.close();
	}
}

/**
 * 各类接口用于分别得到每种硬件的独特型号
 * */
interface PS2{
	void getModelOfPS2();
}

interface USB{
	void getModelOfUSB();
}

interface TypeC{
	void getModelOfTypeC();
}

/**
 * 本例并未使用抽象类，各种型号键盘之间并无涉及到调用的共同属性
 * */

class MainBoard{//主板
	private String modelOfMainBoard;
	private PS2 ps2;
	private USB usb;
	private TypeC typec;
	
	MainBoard(String m){
		modelOfMainBoard = m;
	}
	
	/**
	 * 向上转型一般用于简洁代码，此处转型之后并没有此功能，故而不转
	 * 完成set = 对接完毕
	 * */
	public void setPs2(PS2 o) {
		this.ps2 = o;
	}
	
	public void setUsb(USB o) {
		this.usb = o;
	}
	
	public void setTypeC(TypeC o) {
		this.typec = o;		
	}
	
	public void inputFromPS2() {
		System.out.print(modelOfMainBoard+" MainBoard:Get data from ");
		ps2.getModelOfPS2();
	}
	
	public void read() {
		System.out.print(modelOfMainBoard+" MainBoard:Read data through ");
		if(usb != null) {//支持USB和TypeC，利用null判断何种方式连接
			usb.getModelOfUSB();
		}
		else {
			typec.getModelOfTypeC();
		}
	}
}

class KeyBoardPS2 implements PS2{//键盘PS2
	private String modelOfKeyBoardPS2;
	
	KeyBoardPS2(String m){
		modelOfKeyBoardPS2 = m;
	}
	
	public void getModelOfPS2() {
		System.out.println(modelOfKeyBoardPS2+" PS2 keyboard");
	}
	
}

class MousePS2 implements PS2{//鼠标PS2
	private String modelOfMousePS2;
	
	MousePS2(String m){
		modelOfMousePS2 = m;
	}
	
	public void getModelOfPS2() {
		System.out.println(modelOfMousePS2+" PS2 Mouse");
	}	
}

class FlashDisk implements USB, TypeC {//U盘
	private String modelOfFlashDisk;
	
	FlashDisk(String m){
		modelOfFlashDisk = m;
	}
	
	public void getModelOfUSB() {
		System.out.println(modelOfFlashDisk+" USB U盘");
	}
	
	public void getModelOfTypeC() {
		System.out.println(modelOfFlashDisk+" TypeC U盘");
	}
}

class MouseUSB implements USB{//鼠标USB
	public void getModelOfUSB() {}
}