package studentManageSystem.demo;

public class Student {
	private int sno;
	private String sname;
	private int sage;
	private char ssex;
	@Override
	public String toString() {
		return "Student [sno=" + sno + ", sname=" + sname + ", sage=" + sage + ", ssex=" + ssex + "]";
	}
	public Student(int sno, String sname, int sage, char ssex) {
		super();
		this.sno = sno;
		this.sname = sname;
		this.sage = sage;
		this.ssex = ssex;
	}
	public Student() {
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public int getSage() {
		return sage;
	}
	public void setSage(int sage) {
		this.sage = sage;
	}
	public char getSsex() {
		return ssex;
	}
	public void setSsex(char ssex) {
		this.ssex = ssex;
	}
	
}
