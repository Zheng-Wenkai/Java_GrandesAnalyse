package homework;

import javax.swing.*;
import java.io.*;
import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.border.EmptyBorder;
import java.util.LinkedList;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GradesAnalyse extends JFrame {

	private JPanel Panel,mainPanel,Panel_course,Panel_class,Panel_save;
	private JButton chooseLesson,chooseClass;
	private CardLayout cl_mainPanel;
	String classes[] = new String[1000];
	String lessons[] = new String[1000];
	private JScrollPane scrollPane_1,scrollPane_2;
	private JList<String> lessonList,classList;
	private JLabel classTitle,subTitle,lessonTitle;
	private JLabel[] studentLabel=new JLabel[1000];
	private JTextField[] scoreText=new JTextField[1000];
	private JButton saveGrades,modifyGrades,build,open;
	private String imagePath="picture//scau.jpg";
	private String coursePath="course.txt";
	private String classPath="classes";
	private String gradesPath="grades";
	private File datFile;
	private JButton lessonReturn,classReturn,saveReturn,modifyReturn,sampleAnalyse,shapeAnalyse;
	private JPanel Panel_modify;
	private JFileChooser jfc;
	private int readLength,writeLength;
	static int fail=0;
	static int pass=0;
	static int middle=0;
	static int good=0;
	static int excellent=0;
	static double failRate=0;
	static double passRate=0;
	static double middleRate=0;
	static double goodRate=0;
	static double excellentRate=0;
	static double totalScore=0;
	static double averageScore=0;
	static double minScore=0;
	static double maxScore=0;
	static double now=0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GradesAnalyse frame = new GradesAnalyse();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the frame.
	 */
	public GradesAnalyse() {
		init();
	}
	private void init() {
		setTitle("学生成绩分析程序");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 522, 382);
		mainPanel = new JPanel();
		mainPanel.setForeground(Color.WHITE);
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPanel);
		cl_mainPanel=new CardLayout(0, 0);
		mainPanel.setLayout(cl_mainPanel);
		createPanel();
		
					
	}
	private void createPanel() {
		// 创建页面及其事件
		Panel = new JPanel();
		mainPanel.add(Panel, "0");
		
		JLabel programName = new JLabel("\u5B66 \u751F \u6210 \u7EE9 \u5206 \u6790");
		programName.setBounds(0, 0, 505, 32);
		programName.setHorizontalAlignment(SwingConstants.CENTER);
		programName.setForeground(Color.DARK_GRAY);
		programName.setFont(new Font("Dialog", Font.BOLD, 18));
		
		JLabel picture = new JLabel("");
		picture.setBounds(0, 34, 496, 254);
		ImageIcon icon=new ImageIcon(imagePath);
		icon.setImage(icon.getImage().getScaledInstance(522,
			    382, Image.SCALE_DEFAULT));
		picture.setIcon(icon);
		Panel.setLayout(null);
		Panel.add(programName);
		Panel.add(picture);
		
		build = new JButton("\u65B0\u5EFA");
		build.setBounds(63, 300, 93, 23);
		Panel.add(build);
		
		open = new JButton("\u6253\u5F00");
		open.setBounds(330, 300, 93, 23);
		Panel.add(open);
		Panel_event();
	}
	private void createPanel_course() {
		
		Panel_course = new JPanel();
		mainPanel.add(Panel_course, "1");
		Panel_course.setLayout(null);
		chooseLesson = new JButton("选择课程");
		chooseLesson.setBounds(58, 310, 101, 23);
		Panel_course.add(chooseLesson);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 496, 310);
		Panel_course.add(scrollPane_1);
		
		lessonList = new JList<String>();
		scrollPane_1.setViewportView(lessonList);
		
		lessonReturn = new JButton("\u8FD4\u56DE");
		lessonReturn.setBounds(320, 310, 93, 23);
		Panel_course.add(lessonReturn);
		Panel_course_event();
	
	}
	private void createPanel_class() {
		
		Panel_class = new JPanel();
		mainPanel.add(Panel_class, "2");
		Panel_class.setLayout(null);
		
		chooseClass = new JButton("选择班级");
		chooseClass.setBounds(58, 310, 100, 23);
		Panel_class.add(chooseClass);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 0, 496, 310);
		Panel_class.add(scrollPane_2);
		
		classList = new JList<String>();
		scrollPane_2.setViewportView(classList);
		
		classReturn = new JButton("\u8FD4\u56DE");
		classReturn.setBounds(316, 310, 93, 23);
		Panel_class.add(classReturn);
		Panel_class_event();

	}
	private void createPanel_save() {
		Panel_save = new JPanel();
		mainPanel.add(Panel_save, "3");
		
		classTitle = new JLabel("classTitle");
		classTitle.setHorizontalAlignment(SwingConstants.CENTER);
		classTitle.setBounds(136, 10, 209, 16);
		classTitle.setFont(new Font("宋体", Font.BOLD, 14));
		Panel_save.setLayout(null);
		Panel_save.add(classTitle);
		
		lessonTitle = new JLabel("lessonTitle");
		lessonTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lessonTitle.setBounds(142, 36, 194, 15);
		Panel_save.add(lessonTitle);
		
		saveGrades = new JButton("保存");
		Panel_save.add(saveGrades);
		saveReturn = new JButton("返回");
		Panel_save.add(saveReturn);
		Panel_save_event();
		
	}
	private void createPanel_modify() {
		Panel_modify = new JPanel();
		mainPanel.add(Panel_modify, "4");
		JLabel modifyTitle = new JLabel("成绩修改");
		modifyTitle.setHorizontalAlignment(SwingConstants.CENTER);
		modifyTitle.setBounds(136, 10, 209, 16);
		modifyTitle.setFont(new Font("宋体", Font.BOLD, 14));
		Panel_modify.setLayout(null);
		Panel_modify.add(modifyTitle);
		
		subTitle = new JLabel("subTitle");
		subTitle.setHorizontalAlignment(SwingConstants.CENTER);
		subTitle.setBounds(142, 36, 194, 15);
		Panel_modify.add(subTitle);
		
		modifyGrades = new JButton("修改");
		Panel_modify.add(modifyGrades);
		modifyReturn = new JButton("返回");
		Panel_modify.add(modifyReturn);
		sampleAnalyse = new JButton("简单分析");
		Panel_modify.add(sampleAnalyse);
		shapeAnalyse = new JButton("图形分析");
		Panel_modify.add(shapeAnalyse);
		Panel_modify_event();
		
	}
	private void Panel_event() {
		/*
		 * 新建，由主页面进入课程页面（读取course.txt生成一系列课程按钮）
		 */
		build.addActionListener(new ActionListener() {
			BufferedReader bufr;
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// build事件包括创建下一页面，创建页面事件及显示页面
				createPanel_course();
				try {  
					int i=0;// i必须放在里面，否则会多出一空行
                    bufr=new BufferedReader(new FileReader(coursePath));  
                    String line=null;  
                    while((line=bufr.readLine())!=null){  
                        //System.out.println(line);
                        lessons[i++]=line;
                    }
                    lessonList.setModel(new AbstractListModel<String>() {
    					public int getSize() {
    						return lessons.length;
    					}
    					public String getElementAt(int index) {
    						return lessons[index];
    					}
    				});
                } 
				catch (IOException e2) {
					JOptionPane.showMessageDialog(Panel, "读取课程异常","tip", JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException("读取课程异常"); 
                  
                }
				finally {
					try {
						bufr.close();
					} catch (IOException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
				
				cl_mainPanel.show(mainPanel,"1");
			}
		});
		/*
		 * 打开，由主页面进入修改成绩页面（调用readFile()读取.dat文件，即读取学生列表）
		 */
		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createPanel_modify();
				jfc=new JFileChooser(gradesPath);  
	            jfc.setDialogTitle("选择要打开的成绩单文件");  
	            jfc.showOpenDialog(mainPanel);
	            if(jfc.getSelectedFile()!=null) {
	            	subTitle.setText(jfc.getSelectedFile().getName());
	            	readFile(jfc.getSelectedFile());
					cl_mainPanel.show(mainPanel,"4");
	            }  
			}
		});
	}
	private void Panel_course_event() {
		/*
		 * 选择课程，由课程页面进入班级页面（读取class文件夹生成一系列班级按钮）
		 */
		chooseLesson.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//System.out.println(lessonList.getSelectedValue());
				createPanel_class();
				getDirectoryFile(classPath);
				classList.setModel(new AbstractListModel<String>() {
					public int getSize() {
						return classes.length;
					}
					public String getElementAt(int index) {
						return classes[index];
					}
				});
				
				cl_mainPanel.show(mainPanel,"2");
			}
		
		});
		lessonReturn.addActionListener(new ReturnMenu());
	}
	private void Panel_class_event() {
		/*
		 * 选择班级，进入成绩录入页面（调用createStudentList()创建学生列表）
		 */
		chooseClass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				createPanel_save();
				datFile= new File(gradesPath, classList.getSelectedValue()+"-"+lessonList.getSelectedValue()+".dat");//指定文件路径
				if (datFile.exists()) {
					JOptionPane.showMessageDialog(Panel_class, "该班的成绩已经输入，无需再进行输入。","tip", JOptionPane.INFORMATION_MESSAGE);
					System.out.println("该班的成绩已经输入,无需再进行输入。");
					
				}
				// 如果文件不存在，则读取学生列表并生成label和textField
				else {
					classTitle.setText(classList.getSelectedValue());
					lessonTitle.setText("( 成绩录入："+lessonList.getSelectedValue()+" )");
					BufferedReader bufr=null;
					createStudentList();
					cl_mainPanel.show(mainPanel, "3");
				}
				
			}
		});
		classReturn.addActionListener(new ReturnMenu());
	}
	private void Panel_save_event() {
		/*
		 * 保存成绩，调用createFile()创建.dat文件,调用writeFile()写入.dat文件
		 */
		saveGrades.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				createFile();
				writeFile(datFile);
				JOptionPane.showMessageDialog(Panel_save, "Save Successful","tip", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		saveReturn.addActionListener(new ReturnMenu());
	}
	private void Panel_modify_event() {
		/*
		 * 修改成绩，调用modifyFile()修改.dat文件
		 */
		modifyGrades.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				modifyFile(jfc.getSelectedFile());
				JOptionPane.showMessageDialog(Panel_modify, "Modify Successful","tip", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		/*
		 * 简单分析
		 */
		sampleAnalyse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//System.out.println(df.format(averageScore));
				//JOptionPane.showMessageDialog(null, df.format(averageScore));
				analyse();
				JFrame f=new SampleAnalyse();
				f.setVisible(true);
				clean();
			}
		});
		/*
		 * 图形分析
		 */
		shapeAnalyse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				analyse();
				JFrame f=new JFrame("成绩图形分析");  
			    f.setLayout(new GridLayout(2,2,10,10));  
			    f.add(new BarChart().getChartPanel());           //添加柱形图   
			    f.add(new PieChart().getChartPanel());           //添加饼状图  
			    f.setBounds(300, 100, 800, 600);  
			    f.setVisible(true); 
			    clean();
			}
		});
		modifyReturn.addActionListener(new ReturnMenu());		
	}
	private void getDirectoryFile(String path) {
		int i=0;
		File f = new File(path);
        if (!f.exists()) {
            System.out.println(path + " 不存在 （读取班级列表失败）");
            return;
        }
		File fa[] = f.listFiles();
        for (int j = 0; j < fa.length; j++) {
            File fs = fa[j];
            if (fs.isDirectory()) {
                //System.out.println(fs.getName() + " [目录]");
                continue;
            } else {
                //System.out.println(fs.getName());
                classes[i++]=fs.getName();
            }
        }
	}
	// 创建学生列表
	private void createStudentList() {
		
		BufferedReader bufr=null;
		try {  
			bufr=new BufferedReader(new FileReader(classPath+"//"+classList.getSelectedValue()));  
            String line=null;
            int i=0;
            int student_y=77;
            int score_y=74;
            while((line=bufr.readLine())!=null){
            	//System.out.println(line);
            	studentLabel[i]=new JLabel();
            	studentLabel[i].setText(line);
            	studentLabel[i].setBounds(104, student_y, 152, 15);
            	
            	scoreText[i]=new JTextField("0");
            	scoreText[i].setBounds(270, score_y, 66, 21);
            	scoreText[i].setColumns(10);
            	
            	Panel_save.add(studentLabel[i]);
            	Panel_save.add(scoreText[i]);
            	
        		i++;
        		student_y+=31;
        		score_y+=31;
            }
            writeLength=i--;
    		saveGrades.setBounds(120, score_y+29, 93, 23);
    		saveReturn.setBounds(240, score_y+29, 93, 23);
        } 
		catch (IOException e2) {  
            throw new RuntimeException("读取学生列表失败");  
        }
		finally {
			try {
				bufr.close();
			} catch (IOException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
		}
	}
	// 创建.dat文件
	private void createFile() {
		try {
			datFile.createNewFile();// 创建文件
	    } catch (IOException e1) {
	        System.out.println("创建.dat文件失败");
	    }

	}
	 // 写入.dat文件
	 private void writeFile(File file) {
		 LinkedList<User> msgOut = new LinkedList<User>(); 
		 for(int i=0;i<writeLength;i++) {
			 msgOut.add(new User(studentLabel[i].getText(), scoreText[i].getText()));
		 }
		 writeObject(file,msgOut);
         System.out.println("写入.dat文件成功");
	        
	 }
	 // 读取.dat 文件（读取学生列表）
	 private void readFile(File file) {
		 int i=0;
         int student_y=77;
         int score_y=74;
         LinkedList<User> msgIn = null;
         msgIn=readObject(file);
         readLength=msgIn.size();
         for(User temp:msgIn) {  
             //System.out.println(temp.information + temp.score); 
	        studentLabel[i]=new JLabel();
         	studentLabel[i].setText(temp.information);
         	studentLabel[i].setBounds(104, student_y, 152, 15);
         	
         	scoreText[i]=new JTextField();
         	scoreText[i].setText(temp.score);
         	scoreText[i].setBounds(270, score_y, 66, 21);
         	scoreText[i].setColumns(10);
         	
         	Panel_modify.add(studentLabel[i]);
         	Panel_modify.add(scoreText[i]);
         	
     		i++;
     		student_y+=31;
     		score_y+=31;
         }
         modifyGrades.setBounds(120, score_y+29, 93, 23);
         modifyReturn.setBounds(240, score_y+29, 93, 23);
         sampleAnalyse.setBounds(120, score_y+70, 93, 23);
         shapeAnalyse.setBounds(240, score_y+70, 93, 23);
	 }
	 // 修改.dat文件
	 private void modifyFile(File file) {
	        // 此处的bufr主要是为了获取长度
            LinkedList<User> msgOut = new LinkedList<User>();
            for(int i=0;i<readLength;i++) {
            	msgOut.add(new User(studentLabel[i].getText(), scoreText[i].getText()));
            }	        
	        writeObject(file,msgOut);
	        System.out.println("修改.dat文件成功");   
	 }
	 public class ReturnMenu implements ActionListener{
		 public void actionPerformed(ActionEvent e) {
			 cl_mainPanel.show(mainPanel,"0"); 
			 classes=new String[1000];
			 lessons=new String[1000];
			 studentLabel=new JLabel[1000];
			 scoreText=new JTextField[1000];
	     }
	}
	 private LinkedList<User> readObject(File file) {    
	        ObjectInputStream ois = null;  
	        LinkedList<User> msgAll = null;  
	          
	        try {  
	            ois = new ObjectInputStream(new FileInputStream(file));  
	            try {  
	                msgAll = (LinkedList<User>)ois.readObject();  
	                  
	            } catch (ClassNotFoundException e) {  
	                e.printStackTrace();  
	            }  
	        } catch (FileNotFoundException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } finally {  
	            try {  
	                ois.close();  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	            }  
	        }  
	          
	        return msgAll;  
	    }
	 private void writeObject(File file, LinkedList<User> msg) {   
	        if (false == file.isFile()) {  
	            return ;  
	        }  
	          
	        try {  
	            // The value "false" for FileOutputStream means that overwrite this file,  
	            // if it is "true",append the new data to this file.  
	            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file,false));  
	            oos.writeObject(msg);  
	            oos.flush();  
	            oos.close();  
	        } catch (FileNotFoundException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	    }
	 private void analyse() {
		 for(int i=0;i<readLength;i++) {
				//System.out.println(studentLabel[i].getText()+"\t"+Double.parseDouble( scoreText[i].getText() ));
				now=Double.parseDouble( scoreText[i].getText() );
				if(now<minScore) {
					minScore=now;
				}
				if(now>maxScore) {
					maxScore=now;
				}
				totalScore+=now;
				// 由于是doule类型，故不使用switch
				if(now>=90) {
					excellent++;
				}else {
					if(now>=80) {
						good++;
					}else {
						if(now>=70) {
							middle++;
						}else {
							if(now>=60) {
								pass++;
							}else {
								fail++;
							}
						}
					}
				}
			}
			averageScore=totalScore/readLength;
			failRate=fail/(double)readLength;
			passRate=pass/(double)readLength;
			middleRate=middle/(double)readLength;
			goodRate=good/(double)readLength;
			excellentRate=excellent/(double)readLength;
	 }
	 private void clean() {
		 	fail=0;
			pass=0;
			middle=0;
			good=0;
			excellent=0;
			failRate=0;
			passRate=0;
			middleRate=0;
			goodRate=0;
			excellentRate=0;
			totalScore=0;
			averageScore=0;
			minScore=0;
			maxScore=0;
			now=0;
	 }
}

