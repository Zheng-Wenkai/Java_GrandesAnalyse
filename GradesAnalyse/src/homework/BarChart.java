package homework;

import java.awt.Font;  

import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartPanel;  
import org.jfree.chart.JFreeChart;  
import org.jfree.chart.axis.CategoryAxis;  
import org.jfree.chart.axis.ValueAxis;  
import org.jfree.chart.plot.CategoryPlot;  
import org.jfree.chart.plot.PlotOrientation;  
import org.jfree.data.category.CategoryDataset;  
import org.jfree.data.category.DefaultCategoryDataset;  
  
public class BarChart {  
    ChartPanel frame;  
    public  BarChart(){  
        CategoryDataset dataset = getDataSet();  
        JFreeChart chart = ChartFactory.createBarChart3D(  
                            "�ɼ�������״ͼ", // ͼ�����  
                            "������", // Ŀ¼�����ʾ��ǩ  
                            "����", // ��ֵ�����ʾ��ǩ  
                            dataset, // ���ݼ�  
                            PlotOrientation.VERTICAL, // ͼ����ˮƽ����ֱ  
                            true,           // �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ������false)  
                            false,          // �Ƿ����ɹ���  
                            false           // �Ƿ�����URL����  
                            );  
          
        //�����￪ʼ  
        CategoryPlot plot=chart.getCategoryPlot();//��ȡͼ���������  
        CategoryAxis domainAxis=plot.getDomainAxis();         //ˮƽ�ײ��б�  
        domainAxis.setLabelFont(new Font("����",Font.BOLD,14));         //ˮƽ�ײ�����  
        domainAxis.setTickLabelFont(new Font("����",Font.BOLD,12));  //��ֱ����  
        ValueAxis rangeAxis=plot.getRangeAxis();//��ȡ��״  
        rangeAxis.setLabelFont(new Font("����",Font.BOLD,15));  
        chart.getLegend().setItemFont(new Font("����", Font.BOLD, 15));  
        chart.getTitle().setFont(new Font("����",Font.BOLD,20));//���ñ�������  
            
        frame=new ChartPanel(chart,true);        //����Ҳ������chartFrame,����ֱ������һ��������Frame  
           
    }  
    private static CategoryDataset getDataSet() {  
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
        dataset.addValue(GradesAnalyse.fail, "<60��", "<60��");  
        dataset.addValue(GradesAnalyse.pass, "60-69��", "60-69��");  
        dataset.addValue(GradesAnalyse.middle, "70-79��", "70-79��");  
        dataset.addValue(GradesAnalyse.good, "80-89��", "80-89��");  
        dataset.addValue(GradesAnalyse.excellent, "90������", "90������");  
        return dataset;  
    }  
  
    public ChartPanel getChartPanel(){  
    	return frame;        
    }  
}  
