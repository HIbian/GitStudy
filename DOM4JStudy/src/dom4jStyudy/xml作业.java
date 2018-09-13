package dom4jStyudy;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class xml作业 {
	public static void main(String[] args) {
		xml作业 zuoye = new xml作业();
//		zuoye.showXML();
//		zuoye.update();
//		zuoye.deleteType();
//		zuoye.addMi();
		zuoye.listXML();
	}
	//遍历所有的数据，封装一个集合
	public void listXML() {
		ArrayList<Phone> ps = new ArrayList<Phone>();
		Element root = getDocument("phone.xml").getRootElement();
		Iterator<Element> elementIterator = root.elementIterator();
		while (elementIterator.hasNext()) {
			Element element = (Element) elementIterator.next();
			Iterator<Element> elementIterator2 = element.elementIterator();
			Phone p = new Phone();
			while (elementIterator2.hasNext()) {
				Element element2 = (Element) elementIterator2.next();
				if ("brand".equals(element2.getName())) {
					p.setBran(element2.getText());
				}
				if ("type".equals(element2.getName())) {
					p.setType(element2.getText());
				}
				if ("price".equals(element2.getName())) {
					p.setPrice(element2.getText());
				}
			}
			ps.add(p);
		}
		System.out.println(ps.toString());
	}
	
	//6.拓展：新增一个新的手机节点，小米手机信息
	//品牌brand  小米  类型type   6S  价格price   5000
	public void addMi() {
		Scanner sc = new Scanner(System.in);
		Document doc = getDocument("phone.xml");
		Element root =doc.getRootElement();
		
		System.out.println("输入品牌");
		String bran = sc.next();
		System.out.println("输入类型");
		String type = sc.next();
		System.out.println("输入价格");
		String price = sc.next();
		//添加到对象，下一题用的类
		Phone p = new Phone(bran, type, price);
		//添加
		Element addElement = root.addElement("phone").addAttribute("id", "3");
		addElement.addElement("brand").setText(p.getBran());
		addElement.addElement("type").setText(p.getType());
		addElement.addElement("price").setText(p.getPrice());
		//保存
		Savefile(doc, "phone.xml");
		sc.close();
	}
	
	//删除华为手机中的type元素
	public void deleteType() {
		Document doc = getDocument("phone.xml");
		Element root =doc.getRootElement();
		List<Element> elements = root.elements();
		for (Element element : elements) {
			List<Element> elements2 = element.elements();
			for (Element e : elements2) {
				if (e.getName().equals("brand")&&e.getText().equals("华为")) {
					element.remove(element.element("type"));
				}
			}
		}
		//保存
		Savefile(doc, "phone.xml");
	}
	
	//使用DOM4J把苹果手机的price修改为4500元
	public void update() {
		Scanner sc = new Scanner(System.in);
		System.out.println("输入手机型号：");
		String type = sc.next();
		System.out.println("输入修改后的价格：");
		String price = sc.next();
		Document doc = getDocument("phone.xml");
		Element root =doc.getRootElement();
		
		List<Element> elements = root.elements();
		for (Element element : elements) {
			List<Element> elements2 = element.elements();
			for (Element e : elements2) {
				if (e.getName().equals("type")&&e.getText().equals(type)) {
					element.element("price").setText(price);
				}
			}
		}
		//保存
		Savefile(doc, "phone.xml");
		sc.close();
	}
	
	//遍历输出
	public void showXML() {
		Element root = getDocument("phone.xml").getRootElement();
		Iterator<Element> elementIterator = root.elementIterator();
		while (elementIterator.hasNext()) {
			Element element = (Element) elementIterator.next();
			System.out.println(element.getName()+"\t"+element.attribute("id").getName()+"="+element.attribute("id").getText());
			Iterator<Element> elementIterator2 = element.elementIterator();
			while (elementIterator2.hasNext()) {
				Element element2 = (Element) elementIterator2.next();
				System.out.println(element2.getName()+":"+element2.getText());
			}
		}
	}

	//打开xml
	private  Document getDocument(String file) {
		SAXReader reader = new SAXReader();
		@SuppressWarnings("unused")
		Document doc = null;
		try {
			return doc = reader.read(file);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//保存xml
	private void Savefile(Document doc,String file) {
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		XMLWriter xw = null;
		try {
			xw = new XMLWriter(new FileWriter(file));
			xw.write(doc);
			xw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
class Phone{
	private String bran;
	private String type;
	private String price;
	public Phone(String bran, String type, String price) {
		super();
		this.bran = bran;
		this.type = type;
		this.price = price;
	}
	public Phone() {
	}
	
	public String getBran() {
		return bran;
	}
	public void setBran(String bran) {
		this.bran = bran;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Phone [bran=" + bran + ", type=" + type + ", price=" + price + "]";
	}
}