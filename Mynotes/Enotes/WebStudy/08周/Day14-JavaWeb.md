
## XML技术
### 基本概念
* XML(Extensible Markup Language),可扩展标记语言
* 一种数据格式，可以表示和储存一组数据，由一系列标签组成
### 基本结构
* XML声明，定义xml版本，以及编码信息
* 有且仅有一个根元素
* 大小写敏感
* 属性值用双引号
* 标签成对，自定义，可用中文。也可用DTD约束。
### 优势
* xml一般在项目中做配置文件
* 作为数据交换使用
* 存储少量数据
### 编写
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--注释-->
<stus>
	<stu id = "1">
		<name>cxx</name>
		<age>18</age>
		<sex>man</sex>
		<hight>1.75</hight>
	</stu>
	<stu id = "2">
		<name>cxx</name>
		<age>19</age>
		<sex>woman</sex>
		<hight>1.80</hight>
	</stu>
	<stu id = "3">
		<name>cx</name>
		<age>20</age>
		<sex>man</sex>
		<hight>1.65</hight>
	</stu>
</stus>
```
### 解析
* DOM
* SAX
* JDOM
* DOM4J
## DOM4J
* Dom4j是一个简单、灵活的**开放源代码**的库,作用就是专门负责XML文件解析！
* Dom4j是一个非常优秀的Java XML API，具有性能优异、功能强大和极易使用的特点。现在很多软件采用的Dom4j，例如Hibernate，**包括sun公司自己的JAXM也用了Dom4j。**
* **使用Dom4j开发，需下载dom4j相应的jar文件。**
### DOM4J解析XML文件原理
有几个关键字
* Element,rootElement 表示节点，根节点
* Attibute 节点的属性名
* Text 节点内容的值,属性的值
* Name 节点名字,属性的值

### DOM4J实现XML文件的遍历
```java
package dom4jStyudy;

import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class test {
	public static void main(String[] args) throws Exception {
//		bianli();
		bianli_i();
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

```
```java
	//获取第二个学生的身高
	public static void getS2h() throws Exception{
		//创建解析器对象
			SAXReader reader = new SAXReader();
			//添加xml文件
			Document doc = reader.read("stu.xml");
			//获取根节点
			Element root = doc.getRootElement();
			//获取第二个学生节点
			System.out.println(root.elements().get(1).elements().get(3).getText());
	}
```
### DOM4J实现文件新增
```java
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
```
### DOM4J实现XML文件更新
```java
	//更新2号学生的身高为2.0
	public static void update() throws Exception {
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
```
### DOM4J实现XML文件元素删除
```java
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
```
## DTD(补充)
* DTD（文档类型定义）的作用是定义 XML 文档的合法构建模块。
* DTD 可被成行地声明于 XML 文档中，也可作为一个外部引用。
**为啥要使用DTD**
* 通过 DTD，您的每一个 XML 文件均可携带一个有关其自身**格式的描述**。
* 通过 DTD，独立的团体可一致地使用某个**标准**的 DTD 来交换数据。
* 而您的应用程序也可使用某个标准的 DTD 来**验证**从外部接收到的数据。
* 您还可以使用 DTD 来**验证**您自身的数据。


写在内部
```XML
<!DOCTYPE NEWSPAPER [

<!ELEMENT NEWSPAPER (ARTICLE+)>
<!ELEMENT ARTICLE (HEADLINE,BYLINE,LEAD,BODY,NOTES)>
<!ELEMENT HEADLINE (#PCDATA)>
<!ELEMENT BYLINE (#PCDATA)>
<!ELEMENT LEAD (#PCDATA)>
<!ELEMENT BODY (#PCDATA)>
<!ELEMENT NOTES (#PCDATA)>

<!ATTLIST ARTICLE AUTHOR CDATA #REQUIRED>
<!ATTLIST ARTICLE EDITOR CDATA #IMPLIED>
<!ATTLIST ARTICLE DATE CDATA #IMPLIED>
<!ATTLIST ARTICLE EDITION CDATA #IMPLIED>

]>
```

写在外部
```XML
<?xml version="1.0"?>
<!DOCTYPE note SYSTEM "note.dtd">
<note>
  <to>Tove</to>
  <from>Jani</from>
  <heading>Reminder</heading>
  <body>Don't forget me this weekend!</body>
</note>
<!-- --------------note.dtd----------------- -->
<!ELEMENT note (to,from,heading,body)>
<!ELEMENT to (#PCDATA)>
<!ELEMENT from (#PCDATA)>
<!ELEMENT heading (#PCDATA)>
<!ELEMENT body (#PCDATA)>
```
