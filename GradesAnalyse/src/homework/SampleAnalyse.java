package homework;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class SampleAnalyse extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public SampleAnalyse() {
		DecimalFormat df = new DecimalFormat("#.00");
		setTitle("课程考试成绩分析");
		setBounds(100, 300, 490, 338);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lb = new JLabel("课程考试成绩分析");
		lb.setFont(new Font("微软雅黑", Font.BOLD, 16));
		lb.setHorizontalAlignment(SwingConstants.CENTER);
		lb.setBounds(149, 10, 161, 25);
		contentPane.add(lb);
		
		JLabel lb1 = new JLabel("New label");
		lb1.setFont(new Font("宋体", Font.PLAIN, 13));
		lb1.setHorizontalAlignment(SwingConstants.CENTER);
		lb1.setBounds(33, 45, 379, 19);
		lb1.setText("最高分："+GradesAnalyse.maxScore+"分，最低分："+GradesAnalyse.minScore+"分，平均分："+GradesAnalyse.averageScore+"分");
		contentPane.add(lb1);
		
		JLabel lb2 = new JLabel("New label");
		lb2.setBounds(102, 75, 243, 15);
		lb2.setText("不及格（分数<60）："+GradesAnalyse.fail+"人，占"+df.format(GradesAnalyse.failRate*100)+"%");
		contentPane.add(lb2);
		
		JLabel lb3 = new JLabel("New label");
		lb3.setBounds(102, 100, 243, 15);
		lb3.setText("及格（60<=分数<70）："+GradesAnalyse.pass+"人，占"+df.format(GradesAnalyse.passRate*100)+"%");
		contentPane.add(lb3);
		
		JLabel lb4 = new JLabel("New label");
		lb4.setBounds(102, 121, 243, 15);
		lb4.setText("中等（70<=分数<80）："+GradesAnalyse.middle+"人，占"+df.format(GradesAnalyse.middleRate*100)+"%");
		contentPane.add(lb4);
		
		JLabel lb5 = new JLabel("New label");
		lb5.setBounds(102, 146, 243, 15);
		lb5.setText("良好（80<=分数<90）："+GradesAnalyse.good+"人，占"+df.format(GradesAnalyse.goodRate*100)+"%");
		contentPane.add(lb5);
		
		JLabel lb6 = new JLabel("New label");
		lb6.setBounds(102, 171, 243, 15);
		lb6.setText("优秀（90<=分数<100）："+GradesAnalyse.excellent+"人，占"+df.format(GradesAnalyse.excellentRate*100)+"%");
		contentPane.add(lb6);

	}

}
