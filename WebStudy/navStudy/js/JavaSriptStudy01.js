//变量的基本类型又有Number、String、Boolean、Undefined、Null五种。
var a=1;
console.log(a);

//js自定义对象
//创建新对象有两种不同的方法：
//		1、定义并创建对象的实例
//		2、使用函数来定义对象，然后创建新的对象实例
var pserson = new Object();
pserson.name="陈昕";
pserson.age=18;
pserson.work=function(){
	return "work"
}
console.log(pserson.name+"--"+pserson.age+"--"+pserson.work());

var person ={name:"cx",age:18};
console.log(person.name+"--"+person.age);

//使用构造函数创建对象
function per(name,age){
	this.name=name;
	this.age=age;
}
var cx = new per("cx",18);
console.log(cx.name+"--"+cx.age);

//数组就是和我们之前理解的数组概念一致，而在JavaScript中成为Array类型。
var is=[1,2,3,4,5,6,7,8];
for (i=0;i<is.length;i++) {
	console.log(is[i]);
}

var students =[{id:1,name:"cx",age:18},{id:2,name:"cn",age:18},{id:3,name:"dd",age:19}];
for (i=0;i<students.length;i++) {
	console.log(students[i].id+"--"+students[i].name+"--"+students[i].age);
}

//###### 关系运算
//
//==相等
//
//<小于
//
//<=小于或等于
//
//>大于
//
//>=大于或等于
//
//!=不等于
//
//===引用相同 !important
console.log("1"==1);//true
console.log("1"=='1');//true
console.log("1"==='1');//true
console.log("1"===1);//false
console.log(1==='1');//false

//用function关键字来声明，后面是方法名字，参数列表里不写var。整个方法不写返回值类型。
var num=0;
function funcName(){
	console.log(++num);
}

function add(a,b){
	return a+b;
}
console.log(add(100,200));

function alertwindow(){
	alert("警告窗口");
}

function confirmwindow(){
	var re = confirm("确认窗口");
	console.log(re);
}

function promptWindow(){
	var re = prompt("你爱学习吗","也许吧");
	console.log(re);
}

//正则表达式是描述字符模式的对象。
//正则表达式用于对字符串模式匹配及检索替换，是对字符串执行模式匹配的强大工具。
//语法：
//var patt=new RegExp(pattern,modifiers);
//或者更简单的方式:
//var patt=/pattern/modifiers;
//var re = new RegExp("\\w+");
//var re = /\w+/;

//正则表达式的使用
//test()方法：
//test()方法搜索字符串指定的值，根据结果并返回真或假

var re = /ab*.c/;
var str="abcd";
console.log(re);
console.log(str);
console.log(re.test(str));

//身份证验证
var sfzReg = new RegExp(/^([0-9]{17})([0-9xX])$/);
var sfzStr="51090219970128933x";
console.log(sfzReg.test(sfzStr));










