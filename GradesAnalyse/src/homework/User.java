package homework;

import java.io.Serializable;

//ʵ�����л�
public class User implements Serializable {
	   
	String information = "";
	String score = "";

	// ��д���췽�����������Ĵ���
	public User(String information,  String score) {
	     super();
	     this.information = information;
	     this.score = score;
	 }

	// ��дtoString������������ʾ
	@Override
	public String toString() {
	     return information+"\t"+score+"\n";
	}
}
