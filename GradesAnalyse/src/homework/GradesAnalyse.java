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
		setTitle("ѧ���ɼ���������");
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
		// ����ҳ�漰���¼�
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
		chooseLesson = new JButton("ѡ��γ�");
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
		
		chooseClass = new JButton("ѡ��༶");
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
		classTitle.setFont(new Font("����", Font.BOLD, 14));
		Panel_save.setLayout(null);
		Panel_save.add(classTitle);
		
		lessonTitle = new JLabel("lessonTitle");
		lessonTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lessonTitle.setBounds(142, 36, 194, 15);
		Panel_save.add(lessonTitle);
		
		saveGrades = new JButton("����");
		Panel_save.add(saveGrades);
		saveReturn = new JButton("����");
		Panel_save.add(saveReturn);
		Panel_save_event();
		
	}
	private void createPanel_modify() {
		Panel_modify = new JPanel();
		mainPanel.add(Panel_modify, "4");
		JLabel modifyTitle = new JLabel("�ɼ��޸�");
		modifyTitle.setHorizontalAlignment(SwingConstants.CENTER);
		modifyTitle.setBounds(136, 10, 209, 16);
		modifyTitle.setFont(new Font("����", Font.BOLD, 14));
		Panel_modify.setLayout(null);
		Panel_modify.add(modifyTitle);
		
		subTitle = new JLabel("subTitle");
		subTitle.setHorizontalAlignment(SwingConstants.CENTER);
		subTitle.setBounds(142, 36, 194, 15);
		Panel_modify.add(subTitle);
		
		modifyGrades = new JButton("�޸�");
		Panel_modify.add(modifyGrades);
		modifyReturn = new JButton("����");
		Panel_modify.add(modifyReturn);
		sampleAnalyse = new JButton("�򵥷���");
		Panel_modify.add(sampleAnalyse);
		shapeAnalyse = new JButton("ͼ�η���");
		Panel_modify.add(shapeAnalyse);
		Panel_modify_event();
		
	}
	private void Panel_event() {
		/*
		 * �½�������ҳ�����γ�ҳ�棨��ȡcourse.txt����һϵ�пγ̰�ť��
		 */
		build.addActionListener(new ActionListener() {
			BufferedReader bufr;
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// build�¼�����������һҳ�棬����ҳ���¼�����ʾҳ��
				createPanel_course();
				try {  
					int i=0;// i����������棬�������һ����
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
					JOptionPane.showMessageDialog(Panel, "��ȡ�γ��쳣","tip", JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException("��ȡ�γ��쳣"); 
                  
                }
				finally {
					try {
						bufr.close();
					} catch (IOException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
				}
				
				cl_mainPanel.show(mainPanel,"1");
			}
		});
		/*
		 * �򿪣�����ҳ������޸ĳɼ�ҳ�棨����readFile()��ȡ.dat�ļ�������ȡѧ���б�
		 */
		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createPanel_modify();
				jfc=new JFileChooser(gradesPath);  
	            jfc.setDialogTitle("ѡ��Ҫ�򿪵ĳɼ����ļ�");  
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
		 * ѡ��γ̣��ɿγ�ҳ�����༶ҳ�棨��ȡclass�ļ�������һϵ�а༶��ť��
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
		 * ѡ��༶������ɼ�¼��ҳ�棨����createStudentList()����ѧ���б�
		 */
		chooseClass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				createPanel_save();
				datFile= new File(gradesPath, classList.getSelectedValue()+"-"+lessonList.getSelectedValue()+".dat");//ָ���ļ�·��
				if (datFile.exists()) {
					JOptionPane.showMessageDialog(Panel_class, "�ð�ĳɼ��Ѿ����룬�����ٽ������롣","tip", JOptionPane.INFORMATION_MESSAGE);
					System.out.println("�ð�ĳɼ��Ѿ�����,�����ٽ������롣");
					
				}
				// ����ļ������ڣ����ȡѧ���б�����label��textField
				else {
					classTitle.setText(classList.getSelectedValue());
					lessonTitle.setText("( �ɼ�¼�룺"+lessonList.getSelectedValue()+" )");
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
		 * ����ɼ�������createFile()����.dat�ļ�,����writeFile()д��.dat�ļ�
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
		 * �޸ĳɼ�������modifyFile()�޸�.dat�ļ�
		 */
		modifyGrades.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				modifyFile(jfc.getSelectedFile());
				JOptionPane.showMessageDialog(Panel_modify, "Modify Successful","tip", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		/*
		 * �򵥷���
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
		 * ͼ�η���
		 */
		shapeAnalyse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				analyse();
				JFrame f=new JFrame("�ɼ�ͼ�η���");  
			    f.setLayout(new GridLayout(2,2,10,10));  
			    f.add(new BarChart().getChartPanel());           //�������ͼ   
			    f.add(new PieChart().getChartPanel());           //��ӱ�״ͼ  
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
            System.out.println(path + " ������ ����ȡ�༶�б�ʧ�ܣ�");
            return;
        }
		File fa[] = f.listFiles();
        for (int j = 0; j < fa.length; j++) {
            File fs = fa[j];
            if (fs.isDirectory()) {
                //System.out.println(fs.getName() + " [Ŀ¼]");
                continue;
            } else {
                //System.out.println(fs.getName());
                classes[i++]=fs.getName();
            }
        }
	}
	// ����ѧ���б�
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
            throw new RuntimeException("��ȡѧ���б�ʧ��");  
        }
		finally {
			try {
				bufr.close();
			} catch (IOException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
		}
	}
	// ����.dat�ļ�
	private void createFile() {
		try {
			datFile.createNewFile();// �����ļ�
	    } catch (IOException e1) {
	        System.out.println("����.dat�ļ�ʧ��");
	    }

	}
	 // д��.dat�ļ�
	 private void writeFile(File file) {
		 LinkedList<User> msgOut = new LinkedList<User>(); 
		 for(int i=0;i<writeLength;i++) {
			 msgOut.add(new User(studentLabel[i].getText(), scoreText[i].getText()));
		 }
		 writeObject(file,msgOut);
         System.out.println("д��.dat�ļ��ɹ�");
	        
	 }
	 // ��ȡ.dat �ļ�����ȡѧ���б�
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
	 // �޸�.dat�ļ�
	 private void modifyFile(File file) {
	        // �˴���bufr��Ҫ��Ϊ�˻�ȡ����
            LinkedList<User> msgOut = new LinkedList<User>();
            for(int i=0;i<readLength;i++) {
            	msgOut.add(new User(studentLabel[i].getText(), scoreText[i].getText()));
            }	        
	        writeObject(file,msgOut);
	        System.out.println("�޸�.dat�ļ��ɹ�");   
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
				// ������doule���ͣ��ʲ�ʹ��switch
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

