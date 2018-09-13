package dom4jStyudy;

import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
//<?xml version="1.0" encoding="UTF-8"?>
//<stus>
//	<stu id = "1">
//		<name>cxx</name>
//		<age>18</age>
//		<sex>man</sex>
//		<hight>1.75</hight>
//	</stu>
//	<stu id = "2">
//		<name>cxx</name>
//		<age>19</age>
//		<sex>woman</sex>
//		<hight>1.80</hight>
//	</stu>
//	<stu id = "3">
//		<name>cx</name>
//		<age>20</age>
//		<sex>man</sex>
//		<hight>1.65</hight>
//	</stu>
//</stus>
public class test {
	public static void main(String[] args) throws Exception {
//		bianli();
//		bianli_i();
//		getS2h();
//		addStu();
//		update();
//		delete();
	}
	//实现节点的删除
	public static void delete() throws Exception{
		SAXReader reader = new SAXReader();
		Document doc = reader.read("stu.xml");
		Element root = doc.getRootElement();
		//删除3号学生的身高，思路：获取他的父节点
		Iterator<Element> elementIterator = root.elementIterator();
		while (elementIterator.hasNext()) {
			//找到父节点
			Element element = (Element) elementIterator.next();
			if (!element.attribute("id").getText().equals("3")) {
				continue;
			}
			//找到子节点
			Iterator<Element> elementIterator2 = element.elementIterator();
			while (elementIterator2.hasNext()) {
				Element element2 = (Element) elementIterator2.next();
				if (!element2.getName().equals("hight")) {
					continue;
				}
				//父节点删除子节点
				element.remove(element2);
//				element2.getParent().remove(element2);
			}
		}
		//保存
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		XMLWriter xw = new XMLWriter(new FileWriter("stu.xml"));
		xw.write(doc);
		xw.close();
	}
	//更新2号学生的身高为2.0
	public static void update() throws Exception {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("学生的id？");
		String id = sc.next();
		System.out.println("更新后的身高？");
		String hight = sc.next();
		
		//创建解析器对象
		SAXReader reader = new SAXReader();
		//添加xml文件
		Document doc = reader.read("stu.xml");
		//获取根节点
		Element root = doc.getRootElement();
		Iterator<Element> elementIterator = root.elementIterator();
		while (elementIterator.hasNext()) {
			Element element = (Element) elementIterator.next();
			if (element.attribute("id").getText().equals(id)) {
				Iterator<Element> elementIterator2 = element.elementIterator();
				while (elementIterator2.hasNext()) {
					Element element2 = (Element) elementIterator2.next();
					if (element2.getName().equals("hight")) {
						element2.setText(hight);
					}
				}
			}
		}
		//保存
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		XMLWriter xw = new XMLWriter(new FileWriter("stu.xml"));
		xw.write(doc);
		xw.close();
	}
	//实现xml跟新，添加一个学生
	public static void addStu() throws Exception {
		//创建解析器对象
		SAXReader reader = new SAXReader();
		//添加xml文件
		Document doc = reader.read("stu.xml");
		//获取根节点
		Element root = doc.getRootElement();
		//设置节点名字
		Element addElement = root.addElement("stu");
		//添加属性
		addElement.addAttribute("id", "4");
		//添加子节点
		addElement.addElement("name", "cn");
		addElement.addElement("age", "18");
		addElement.addElement("sex", "woman");
		addElement.addElement("hight", "1.61");
		
		//内存中修改文件后需要保存文件
		OutputFormat format = OutputFormat.createPrettyPrint();//按照一定排版保存，方便查看
		//设置编码
		format.setEncoding("UTF-8");
		//创建写入xml文件的对象
		XMLWriter xw = new XMLWriter(new FileWriter("stu.xml"));
		//添加Document文件
		xw.write(doc);
		//关闭，写入硬盘
		xw.close();
	}
	//获取第二个学生的身高
	public static void getS2h() throws Exception{
		//创建解析器对象
			SAXReader reader = new SAXReader();
			//添加xml文件
			Document doc = reader.read("stu.xml");
			//获取根节点
			Element root = doc.getRootElement();
			//获取第二个学生节点的第四个节点的值
			System.out.println(root.elements().get(1).elements().get(3).getText());
	}
	
	//通过迭代器遍历XML文件
	public static void bianli_i() throws Exception {
		//创建解析器对象
		SAXReader reader = new SAXReader();
		//添加xml文件
		Document doc = reader.read("stu.xml");
		//获取根节点
		Element root = doc.getRootElement();
		Iterator<Element> elementIterator = root.elementIterator();
		while (elementIterator.hasNext()) {
			
			Element element = (Element) elementIterator.next();
			System.out.print(element.getName()+"\t");
			
			Iterator<Attribute> attributeIterator = element.attributeIterator();
			while (attributeIterator.hasNext()) {
				Attribute attribute = (Attribute) attributeIterator.next();
				System.out.println(attribute.getName()+"="+"\""+attribute.getText()+"\"");
			}
			
			Iterator<Element> elementIterator2 = element.elementIterator();
			while (elementIterator2.hasNext()) {
				Element element2 = (Element) elementIterator2.next();
				System.out.println(element2.getName()+":"+element2.getText());
				
			}
		}
	}
	//通过列表遍历XML文件
	public static void bianli() throws Exception {
		//创建解析器对象
		SAXReader reader = new SAXReader();
		//添加xml文件
		Document doc = reader.read("stu.xml");
		//获取根节点
		Element root = doc.getRootElement();
		
		//列表遍历
		//获取子节点到一个列表
		List<Element> elements = root.elements();
		for (Element e : elements) {
			System.out.print(e.getName()+"\t");
			//子节点属性以及属性名
			List<Attribute> attributes = e.attributes();
			for (Attribute a : attributes) {
				System.out.println(a.getName()+"="+"\""+a.getText()+"\"");
			}
			//遍历子节点中的元素
			List<Element> ee = e.elements();
			for (Element eee : ee) {
				System.out.println(eee.getName()+":"+eee.getText());
			}
		}
	}
}
