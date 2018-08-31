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

//查找指定字符串
var getreg = new RegExp(/ab+/g);//正则表达式最后加个g，表示作用于全局
var str = "ssabbssabbb2222";
console.log(str.match(getreg,str));//可以全局匹配，返回匹配的字符串数组
console.log(str.replace(getreg,"替换"));//可以全局替换，返回替换后的字符串
console.log(str.search(getreg));//返回匹配字符串的开始下标
console.log(str.split(getreg));//返回分割后的字符串数组，最后的字符串匹配的话，会有一个长度为0的字符串

console.log(getreg.test(str));//判断是否匹配
var k = getreg.exec(str)
console.log(k.length);
console.log(k[0]);

//全选反选
function quanxuan(){
	var inputbox = document.getElementsByName("hobbit");
	for (i=0;i<inputbox.length;i++) {
		inputbox[i].checked = true;
	}
}
function fanxuan(){
	var inputbox = document.getElementsByName("hobbit");
	for (i=0;i<inputbox.length;i++) {
		inputbox[i].checked = false;
	}
}
function xuan(){
	var inputbox = document.getElementsByName("hobbit");
	var now = document.getElementById("che");
	for (i=0;i<inputbox.length;i++) {
		inputbox[i].checked = now.checked;
	}
}

function big(){
	var big = document.getElementById("p1");
	big.style.fontSize="18px";
}
function middle(){
	var big = document.getElementById("p1");
	big.style.fontSize="16px";
}
function small(){
	var big = document.getElementById("p1");
	big.style.fontSize="14px";
}

function changecolor(){
	var ps = document.getElementById("txt");
	ps.style.color="rgb(122,756,124)";
}
function changewidth(){
	var ps = document.getElementById("txt");
	ps.style.display="inline-block";
	ps.style.height="300px";
	ps.style.weight="300px";
//	ps.style="none";
}
function hidetext(){
	var ps = document.getElementById("txt");
	ps.style.display="none";
}
function showtext(){
	var ps = document.getElementById("txt");
	ps.style.display="inline-block";
}
function queXiao(){
	var ps = document.getElementById("txt");
	ps.style.color="#000000";
	ps.style.display="block";
	ps.style.height="auto";
	ps.style.weight="auto";
}

//实现函数 isInteger(x) 来判断 x 是否是整数
function isoushu(){
	var num = document.getElementById("inputshu").value;
	if(isInteger(num)){
		alert("是整数");
	}else{
		alert("不是整数");
	}
}
function isInteger(x){
	var regnum = new RegExp(/^(0|[1-9][0-9]*|-[1-9][0-9]*)$/);
	return regnum.test(x);
}

//写一个少于 80 字符的函数，判断一个字符串是不是回文字符串
function fanzhuan(){
	var getstr = document.getElementById("fanzhuanbox").value;
	var bool = true;
//	alert(getstr);
	for (i=0;i<getstr.length/2;i++) {
		if(getstr[i]==getstr[getstr.length-1-i]){
			continue;
		}
		bool = false;
		break;
	}
	alert(bool);
}
