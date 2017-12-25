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
		setTitle("�γ̿��Գɼ�����");
		setBounds(100, 300, 490, 338);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lb = new JLabel("�γ̿��Գɼ�����");
		lb.setFont(new Font("΢���ź�", Font.BOLD, 16));
		lb.setHorizontalAlignment(SwingConstants.CENTER);
		lb.setBounds(149, 10, 161, 25);
		contentPane.add(lb);
		
		JLabel lb1 = new JLabel("New label");
		lb1.setFont(new Font("����", Font.PLAIN, 13));
		lb1.setHorizontalAlignment(SwingConstants.CENTER);
		lb1.setBounds(33, 45, 379, 19);
		lb1.setText("��߷֣�"+GradesAnalyse.maxScore+"�֣���ͷ֣�"+GradesAnalyse.minScore+"�֣�ƽ���֣�"+GradesAnalyse.averageScore+"��");
		contentPane.add(lb1);
		
		JLabel lb2 = new JLabel("New label");
		lb2.setBounds(102, 75, 243, 15);
		lb2.setText("�����񣨷���<60����"+GradesAnalyse.fail+"�ˣ�ռ"+df.format(GradesAnalyse.failRate*100)+"%");
		contentPane.add(lb2);
		
		JLabel lb3 = new JLabel("New label");
		lb3.setBounds(102, 100, 243, 15);
		lb3.setText("����60<=����<70����"+GradesAnalyse.pass+"�ˣ�ռ"+df.format(GradesAnalyse.passRate*100)+"%");
		contentPane.add(lb3);
		
		JLabel lb4 = new JLabel("New label");
		lb4.setBounds(102, 121, 243, 15);
		lb4.setText("�еȣ�70<=����<80����"+GradesAnalyse.middle+"�ˣ�ռ"+df.format(GradesAnalyse.middleRate*100)+"%");
		contentPane.add(lb4);
		
		JLabel lb5 = new JLabel("New label");
		lb5.setBounds(102, 146, 243, 15);
		lb5.setText("���ã�80<=����<90����"+GradesAnalyse.good+"�ˣ�ռ"+df.format(GradesAnalyse.goodRate*100)+"%");
		contentPane.add(lb5);
		
		JLabel lb6 = new JLabel("New label");
		lb6.setBounds(102, 171, 243, 15);
		lb6.setText("���㣨90<=����<100����"+GradesAnalyse.excellent+"�ˣ�ռ"+df.format(GradesAnalyse.excellentRate*100)+"%");
		contentPane.add(lb6);

	}

}
