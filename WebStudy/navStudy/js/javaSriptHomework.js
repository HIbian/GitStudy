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
	if(!regEmail(emailtxt)){
		alert("邮箱格式不正确");
	}
	if(!reguserName(userName)){
		alert("用户名格式不正确");
	}
	if(!regpsw(userPsw)){
		alert("密码格式不正确");
	}
	if(!regphone(phone)){
		alert("手机号码格式不正确");
	}
	if(!regsfz(sfz)){
		alert("身份证码格式不正确");
	}
}
function regEmail(email){
	var regemail = new RegExp(/^([\w_]+)@[0-9A-z_]+.([A-z]+)$/);//\w包括字母和数字
//	alert(regemail.test(email));
	var ele = document.getElementById("emaillog");
	if(regemail.test(email)){
		ele.innerText="正确";
		ele.style.color="green";
	}else{
		ele.innerText="不正确";
		ele.style.color="red";
	}
	return regemail.test(email);
}
function reguserName(userName){
	var reguser = new RegExp(/^([A-Z])([0-9_]{4,7})$/);
	return reguser.test(userName);
}
function regpsw(psw){ 
	var regpsw = new RegExp(/.{1,}/);
	return regpsw.test(psw);
}
function regphone(phone){
	var reg = new RegExp(/^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\d{8}$/);
	return reg.test(phone);
}
function regsfz(sfz){
	var reg = new RegExp(/[0-9]{17}[0-9Xx]/);
	return reg.test(sfz);
}

//6.	
//请输入性别：
//请输入身高：
//请输入体重：	
//男性标准体重=（身高cm－80）×70﹪
//女性标准体重=（身高cm－70）×60﹪
//评估标准
//	标准体重正负10%为正常体重
//	标准体重正负10%-20%为体重过重或过轻 
//	标准体重正负20%以上为肥胖或体重不足
//
//		轻度肥胖:超过标准体重 20% -30%
//		中度肥胖:超过标准体重 40%-50%
//		重度肥胖:超过标准体重 50%以上
//要求:
//	1.判断文本框是否为空 如果为空 需要提示
//	2.弹出窗口显示评估结果 输出 性别 身高 体重 标准体重 
//	评估结果 
//		轻:输出体重偏轻 多吃点 
//		正常:继续保持
//		重:轻度/中度/重度肥胖 体重过重 多运动
function homework6(){
	var sex = document.getElementById("w6_sex").value;
	var height = document.getElementById("w6_height").value;
	var weight = document.getElementById("w6_weight").value;
	switch(sex){
		case "男":
		break;
		case "女":
		break;
		default:
	}
}
















