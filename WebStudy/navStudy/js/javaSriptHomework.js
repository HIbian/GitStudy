//冒泡排序
var num =[7,3,4,1,16,8];
function MaoPaoSort(nums){
	for (i=0;i<nums.length;i++) {
		for (j=0;j<nums.length-i-1;j++) {
			if(nums[j]>nums[j+1]){
				var temp = nums[j];
				nums[j] = nums[j+1];
				nums[j+1] = temp;
			}
		}
	}
	return num;
}
var num2 = MaoPaoSort(num);
console.log(num2);

//选择排序
function XuanZheSort(nums){
	for (i=0;i<nums.length;i++) {
		var mark = i;
		for (j=i+1;j<nums.length;j++) {
			if (nums[mark]>nums[j]) {
				mark = j;
			}
		}
		if(i!=mark){
			var temp = nums[i];
			nums[i] = nums[mark];
			nums[mark] = temp;
		}
	}
	return nums;
}
console.log(XuanZheSort(num));

//打印99乘法表
function print99(){
	document.write("<table border='1px'");
	for (i=1;i<=9;i++) {
		document.write("<tr>");
		for (j=1;j<=i;j++) {
			document.write("<td>"+j+"x"+i+"="+j*i+"</td>");
		}
		document.write("</tr>");
	}
	document.write("</table>");
}
print99();
//3.文本框输入一个年份，判断是否是闰年（能被4整除却不能被100整除的年份。世纪年份能被400整除的是闰年）将结果在弹出窗口中显示
function homework3(){
	var textInput = document.getElementById("w3").value;
	if(textInput.length==0){
		alert("输入年份");
	}
	if((textInput%4==0&&textInput%100!=0)||textInput%400==0){
		alert(textInput+"年是闰年");
	}else{
		alert(textInput+"年不是闰年");
	}
}
//4.一个游戏，前20关是每一关自身的分数， 
//	21-30关每一关是10分 
//	31-40关，每一关是20分 
//	41-49关，每一关是30分 
//	50关，是100分 
//	输入你现在闯到的关卡数，求你现在拥有的分数 
function homework4(){
	var textInputs = document.getElementById("w4").value;
	var finalscore = getscore(textInputs);
	if (finalscore!=-1) {
		alert("得分："+finalscore);
	} else{
		alert("输入不合法");
	}

}
function getscore(number){
	if(0<=number&&number<=20){
		return number;
	}else if(21<=number&&number<=30){
		return 20+10*(number-20);
	}else if(31<=number&&number<=40){
		return 20+10*10+20*(number-30);
	}else if(41<=number&&number<=49){
		return 20+10*10+20*10+30*(number-40);
	}else if(number==50){
		return 20+10*10+20*10+30*10+100;
	}else{
		return -1;
	}
}
//5.完成页面表单验证
//	邮箱验证 包含 @ 和 . @在.前面
//	用户名 必须是字母(大小写)开头,可以有数字和下划线 限制5-8位 必填
//	密码 必填
//	手机号: 11位 数字
//	身份证号: 18位 最后一位可以是X
//	点击提交按钮 完成表单验证 验证若不符合条件 就弹出提示窗口
function homework5(){
	var emailtxt = document.getElementById("w5_email").value;
	var userName = document.getElementById("w5_userName").value;
	var userPsw = document.getElementById("w5_userPsw").value;
	var phone = document.getElementById("w5_phone").value;
	var sfz = document.getElementById("w5_sfz").value;
	alert(regEmail(emailtxt));
}
function regEmail(email){
	var regemail = new RegExp(/^([0-9a-zA-Z_]{1,})@[0-9a-zA-Z_]{1,}.([a-zA-Z]{1,})$/);
	return regemail.test(email);
}





















