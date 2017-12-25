package homework;

import java.io.Serializable;

//实现序列化
public class User implements Serializable {
	   
	String information = "";
	String score = "";

	// 重写构造方法，方便数的传入
	public User(String information,  String score) {
	     super();
	     this.information = information;
	     this.score = score;
	 }

	// 重写toString方法，方便显示
	@Override
	public String toString() {
	     return information+"\t"+score+"\n";
	}
}
