package vo;

import java.sql.Timestamp;

public class customer {
	private int id;
	private String number;
	private String name;
	private String pass;
	private String idcard;
	private double money;
	private Timestamp custdate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public customer() {
	}
	public customer(int id, String number, String name, String pass, String idcard, double money, Timestamp custdate) {
		super();
		this.id = id;
		this.number = number;
		this.name = name;
		this.pass = pass;
		this.idcard = idcard;
		this.money = money;
		this.custdate = custdate;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public Timestamp getCustdate() {
		return custdate;
	}
	public void setCustdate(Timestamp custdate) {
		this.custdate = custdate;
	}

}
