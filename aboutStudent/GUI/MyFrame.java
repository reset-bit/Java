package homework.aboutstudent.GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.jfree.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.event.*;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

public class MyFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private StudentDaoImpl op;
    
    public MyFrame(){
    	JFrame jframe = new JFrame("学生成绩管理系统");
		jframe.setSize(800, 600);
		jframe.setLayout(new BorderLayout());
		
		initButtons(jframe);
		
		jframe.setBackground(Color.white);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setVisible(true);
		op = new StudentDaoImpl();
    }
	

	public void initButtons(JFrame jframe) {
		
		JPanel p1 = new JPanel();
		JButton button1 = new JButton("添加");
		JButton button2 = new JButton("删除");
		JButton button3 = new JButton("修改");
		p1.add(button2);
		p1.add(button3);
		p1.add(button1);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                add();
            }
		});
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                delete();
            }
		});
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                modify();
            }
		});
		
		JPanel p3 = new JPanel();
		JLabel label2 = new JLabel("输入学号查询成绩：");
		JTextField text1 = new JTextField(10);
		p3.add(label2);
		p3.add(text1);
		JButton button4 = new JButton("查询");
		p3.add(button4);
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(text1.getText().trim());
                System.out.println("find no:"+id);
                MyTable t1 = new MyTable(op.readStuByNo(id));
            	t1.setVisible(true);
            }
		});
		
		JPanel p2 = new JPanel();
		JButton button5 = new JButton("成绩分布柱状图");
		JButton button6 = new JButton("成绩分布饼状图");
		JButton button7 = new JButton("导出学生成绩");
		button5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				histogram();
            }
		});
		button6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                pieChart();
            }
		});
		button7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                derive();
            }
		});
		p2.add(button5);
		p2.add(button6);
		p2.add(button7);
		
		JPanel p4 = new JPanel();
		JLabel label1 = new JLabel("排序条件：");
		String[] str1 = {"学号", "成绩"};
		JComboBox<String> combobox1 = new JComboBox<String>(str1);
		p4.add(label1);
		p4.add(combobox1);
		JButton button8 = new JButton("所有学生");
		p4.add(button8);
		button8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                show();
            }
		});
		JLabel label3 = new JLabel("查看课程平均成绩：");
		HashMap<String,Integer> c = new HashMap<String,Integer>();
//		if(null == op.getCoursesAverage()) {
//			c.put("无", new Integer(0));
//		}
//		else {
			//c.putAll(op.getCoursesAverage());
		//}
		//String[] str2 = new String[c.size()];
		String[] str2 = {"Math", "English"};
		int i = 0;
		for(Map.Entry<String, Integer> entry:c.entrySet()) {
			str2[i++] = entry.getKey();
		}
		JComboBox<String> combobox2 = new JComboBox<String>(str2);
		p4.add(label3);
		p4.add(combobox2);
		JButton button9 = new JButton("查看");
		p4.add(button9);
		button9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                //MyTable t = new MyTable(c);
                //t.setVisible(true);
            }
		});
		
		jframe.add(p1, BorderLayout.CENTER);
		jframe.add(p2, BorderLayout.SOUTH);
		jframe.add(p3, BorderLayout.WEST);
		jframe.add(p4, BorderLayout.NORTH);
	}
    
    public void add() {
    	JFrame jf = new JFrame("添加学生信息");
		jf.setSize(500, 300);
		jf.setLayout(new BorderLayout());
    	jf.setBackground(Color.white);
		jf.setVisible(true);
    	
    	JPanel jp = new JPanel();
		jp.setLayout(new GridLayout(4,1));
		
    	JPanel jp1 = new JPanel();
		JLabel lable1 = new JLabel("学号：");
		JTextField text1 = new JTextField(10);
		jp1.add(lable1);
		jp1.add(text1);
		
		JPanel jp2 = new JPanel();
		JLabel lable2 = new JLabel("姓名：");
		JTextField text2 = new JTextField(10);
		jp2.add(lable2);
		jp2.add(text2);
		
		JPanel jp3 = new JPanel();
		JLabel lable3 = new JLabel("出生日期：(如2008-04-14)");
		JTextField text3 = new JTextField(10);
		jp3.add(lable3);
		jp3.add(text3);
		
		JPanel jp4 = new JPanel();
		JLabel lable4 = new JLabel("课程及成绩：(如Java-95/C-89)");
		JTextField text4 = new JTextField(10);
		jp4.add(lable4);
		jp4.add(text4);
		
		jp.add(jp1);
		jp.add(jp2);
		jp.add(jp3);
		jp.add(jp4);
		jf.add(jp, BorderLayout.CENTER);
		
		JButton b = new JButton("确定");
		jf.add(b, BorderLayout.SOUTH);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int no = Integer.parseInt(text1.getText().trim());
				String name = text2.getText().trim();

		        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		        Date date = null;  
		        try {  
		            date = sdf.parse(text3.getText().trim());  
		        } catch (ParseException e1) {  
		            System.out.println(e1.getMessage());  
		        }
		        
				String courses = text4.getText().trim();
				
                System.out.println(no+"  "+name+"  "+date+"  "+courses);
                
                op.writeStudent(name, no, date, courses);
            }
		});
    	
    }
    
    public void delete() {
    	JFrame jf = new JFrame("删除学生信息");
		jf.setSize(500, 300);
		jf.setLayout(new BorderLayout());
    	jf.setBackground(Color.white);
		jf.setVisible(true);
		
    	JPanel jp1 = new JPanel();
		JLabel lable1 = new JLabel("待删除学生学号：");
		JTextField text1 = new JTextField(10);
		jp1.add(lable1);
		jp1.add(text1);
		jf.add(jp1, BorderLayout.CENTER);
		
		JButton b = new JButton("确定");
		jf.add(b, BorderLayout.SOUTH);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                if(op.deleteStudent(Integer.parseInt(text1.getText().trim()))) {
                	System.out.println("success delete!");
                }
                else {
                	System.out.println("fail delete!");
                }
            }
		});
    }
    
    public void modify() {
    	JFrame jf = new JFrame("修改学生信息");
		jf.setSize(500, 300);
		jf.setLayout(new BorderLayout());
    	jf.setBackground(Color.white);
		jf.setVisible(true);
    	
    	JPanel jp = new JPanel();
		jp.setLayout(new GridLayout(3,1));
		
    	JPanel jp1 = new JPanel();
		JLabel lable1 = new JLabel("请输入待修改学生学号：");
		JTextField text1 = new JTextField(10);
		jp1.add(lable1);
		jp1.add(text1);
		
		JPanel jp2 = new JPanel();
		JLabel label1 = new JLabel("待修改项：");
		String str1[] = {"学号", "姓名", "出生日期", "课程-新成绩"};
		JComboBox<String> combobox1 = new JComboBox<String>(str1);
		jp2.add(label1);
		jp2.add(combobox1);
		JLabel lable2 = new JLabel("修改值：");
		JTextField text2 = new JTextField(10);
		jp2.add(lable2);
		jp2.add(text2);
		
		jp.add(jp1);
		jp.add(jp2);
		jf.add(jp, BorderLayout.CENTER);
		
		JButton b = new JButton("确定");
		jf.add(b, BorderLayout.SOUTH);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                int curop = combobox1.getSelectedIndex();
                int no = Integer.parseInt(text1.getText().trim());
                switch(curop) {
                case 0:
                	int curno = Integer.parseInt(text2.getText().trim());
                	op.modifyStudent(no, curno);
                	break;
                case 1:
                	String curname = text2.getText().trim();
                	op.modifyStudent(no, curname);
                	break;
                case 2:
    		        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
    		        Date date = null;  
    		        try {  
    		            date = sdf.parse(text2.getText().trim());  
    		        } catch (ParseException e1) {  
    		            System.out.println(e1.getMessage());  
    		        }
    		        op.modifyStudent(no, date);
                case 3:
                	String cc = text2.getText().trim();
                	String[] c = cc.split("-");
                	op.modifyStudent(no, c[0], Integer.parseInt(c[1]));
                	break;
                }
            }
		});
    }
    
    public void histogram() {
    	Map<String, Integer> datas = new HashMap<>();
    	datas = getGradeDistribution(datas);
    	new Histogram(datas);
    }
    
    public void pieChart() {
    	Map<String, Integer> datas = new HashMap<>();
    	datas = getGradeDistribution(datas);
    	new PieChart(datas);
    }
    
    public void derive() {
    	if(op.storeAllStu()) {
    		System.out.println("success store!");
    	}
    	else {
    		System.out.println("fail store!");
    	}
    }
	
    public void show() {
		ArrayList<Student> list = (ArrayList<Student>)op.showAllStu();
    	MyTable t1 = new MyTable(list);
    	t1.setVisible(true);
    	
    }
    
    public Map<String, Integer> getGradeDistribution(Map<String, Integer> datas) {
    	datas.put("A优秀", new Integer(0));
    	datas.put("B良好", new Integer(0));
    	datas.put("C中等", new Integer(0));
    	datas.put("D及格", new Integer(0));
    	datas.put("E不及格", new Integer(0));
    	ArrayList<Student> list = (ArrayList<Student>)op.showAllStu();
    	for(int i = 0; i < list.size(); ++i) {
    		int ave = list.get(i).getScoreAverage();
    		switch(ave/10) {
    		case 10:
    		case 9:datas.put("A优秀", 1+((int)datas.get("A优秀")));break;
    		case 8:datas.put("B良好", 1+((int)datas.get("B良好")));break;
    		case 7:datas.put("C中等", 1+((int)datas.get("C中等")));break;
    		case 6:datas.put("D及格", 1+((int)datas.get("D及格")));break;
    		default:datas.put("E不及格", 1+((int)datas.get("E不及格")));break;
    		}
    	}
    	return datas;
    }
    
	public static void main(String[] args) {
		new MyFrame();
	}
	
}


class MyTable extends JFrame{

	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel tableModel;
	
	public MyTable(ArrayList<Student> list) {
		super();
        setTitle("学生成绩表");
        setBounds(100,100,500,400);//动态响应位置和大小
        String[] columnNames = {"学号","姓名","出生日期","课程及成绩"};
        String [][]tableVales = new String[list.size()][5];
        for(int i = 0; i < list.size(); ++i) {
        	tableVales[i][0] = list.get(i).getNo()+"";
        	tableVales[i][1] = list.get(i).getName();
        	
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        	java.util.Date date = list.get(i).getBirthday();
        	tableVales[i][2] = sdf.format(date);
        	
        	String str = "";
    		for(Map.Entry<String, Integer> entry:list.get(i).getCourses().entrySet()) {
    			str += entry.getKey()+":"+entry.getValue()+"   ";
    		}
    		tableVales[i][3] = str;
        }
        
        tableModel = new DefaultTableModel(tableVales,columnNames);
        table = new JTable(tableModel);
        
        JScrollPane scrollPane = new JScrollPane(table);   //支持滚动
        getContentPane().add(scrollPane,BorderLayout.CENTER);
	}
	
	public MyTable(Student stu) {
		super();
        setTitle("学生信息表");
        setBounds(100,100,500,400);//动态响应位置和大小
        String[] columnNames = {"学号","姓名","出生日期","课程及成绩"};
        String[][] tableVales = new String[1][4];
        tableVales[0][0] = stu.getNo()+"";
        tableVales[0][1] = stu.getName();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	java.util.Date date = stu.getBirthday();
    	tableVales[0][2] = sdf.format(date);
    	
    	String str = "";
		for(Map.Entry<String, Integer> entry:stu.getCourses().entrySet()) {
			str += entry.getKey()+":"+entry.getValue()+"   ";
		}
		tableVales[0][3] = str;
        
        tableModel = new DefaultTableModel(tableVales,columnNames);
        table = new JTable(tableModel);
        
        JScrollPane scrollPane = new JScrollPane(table);   //支持滚动
        getContentPane().add(scrollPane,BorderLayout.CENTER);
	}
	
	public MyTable(HashMap<String,Integer> datas) {
		super();
        setTitle("平均成绩表");
        setBounds(100,100,500,400);//动态响应位置和大小
        String[] columnNames = {"课程","成绩"};
        String [][]tableVales = new String[datas.size()][2];
        int i = 0;
        for(Map.Entry<String, Integer> entry:datas.entrySet()) {
        	tableVales[i][0] = entry.getKey();
        	tableVales[i][1] = entry.getValue().toString();
        	++i;
        }
        
        tableModel = new DefaultTableModel(tableVales,columnNames);
        table = new JTable(tableModel);
        
        JScrollPane scrollPane = new JScrollPane(table);   //支持滚动
        getContentPane().add(scrollPane,BorderLayout.CENTER);
	}
}


class Histogram {
	
	public Histogram(Map<String, Integer> datas) {
		DefaultCategoryDataset ds = new DefaultCategoryDataset();
		for(Map.Entry<String, Integer> entry:datas.entrySet()) {
			ds.setValue(Integer.parseInt(entry.getValue().toString()), entry.getKey().toString(),                         
					entry.getKey().toString());
		}
		String filePath = "d:/histogram.jpg";
        creatHistogram(ds,filePath);
	}
	
	public void creatHistogram(DefaultCategoryDataset ds,String filePath) {
		try {
			//创建柱状图,柱状图分水平显示和垂直显示两种
	        JFreeChart chart = ChartFactory.createBarChart("学生成绩分布情况", "种类", "分布", ds, PlotOrientation.VERTICAL, true, true, true);
	        Font font = new Font("宋体", Font.BOLD, 15);
	        //设置整个图片的标题字体
	        chart.getTitle().setFont(font);
	        //设置提示条字体
	        chart.getLegend().setItemFont(font);
	        //得到绘图区
	        CategoryPlot plot = (CategoryPlot) chart.getPlot();
	        //得到绘图区的域轴(横轴),设置标签的字体
	        plot.getDomainAxis().setLabelFont(font);
	        //设置横轴标签项字体
	        plot.getDomainAxis().setTickLabelFont(font);
	        //设置范围轴(纵轴)字体
	        plot.getRangeAxis().setLabelFont(font);
	        //存储成图片
	        ChartUtilities.saveChartAsJPEG(new File(filePath), chart, 600, 400);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}

class PieChart{
	
	public PieChart(Map<String, Integer> datas){
		DefaultPieDataset pds = new DefaultPieDataset();
		for(Map.Entry<String, Integer> entry:datas.entrySet()) {
			pds.setValue(entry.getKey().toString(), Integer.parseInt(entry.getValue().toString()));
		}
//		pds.setValue("优秀", 100);
//        pds.setValue("良好", 200);
//        pds.setValue("中等", 300);
//        pds.setValue("及格", 400);
//        pds.setValue("不及格", 500);
		String filePath = "d:/pie.jpg";
		createPieChart(pds, filePath);
	}

	public void createPieChart(DefaultPieDataset pds, String filePath) {
		try {
            // 分别是:显示图表的标题、需要提供对应图表的DateSet对象、是否显示图例、是否生成贴士以及是否生成URL链接
            JFreeChart chart = ChartFactory.createPieChart("学生成绩分布情况", pds, true, false, true);
            // 如果不使用Font,中文将显示不出来
            Font font = new Font("宋体", Font.BOLD, 12);
            // 设置图片标题的字体
            chart.getTitle().setFont(font);
            // 得到图块,准备设置标签的字体
            PiePlot plot = (PiePlot) chart.getPlot();
            // 设置标签字体
            plot.setLabelFont(font);
            plot.setStartAngle(new Float(3.14f / 2f));
            // 设置plot的前景色透明度
            plot.setForegroundAlpha(0.7f);
            // 设置plot的背景色透明度
            plot.setBackgroundAlpha(0.0f);
            // 设置标签生成器(默认{0})
            // {0}:key {1}:value {2}:百分比 {3}:sum
            plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}({1}占{2})"));
            // 将内存中的图片写到本地硬盘
            ChartUtilities.saveChartAsJPEG(new File(filePath), chart, 600, 300);
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }  
    }

}