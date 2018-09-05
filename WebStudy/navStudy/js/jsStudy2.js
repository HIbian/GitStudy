//创建标签，删除标签
function creatStudents(){
	var jsonstr = '[{"name":"cx","sex":"man","age":18},{"name":"cx","sex":"man","age":18},{"name":"cx","sex":"man","age":18}]';
	var stuStr = JSON.parse(jsonstr);
	console.log(stuStr);
	//找到table父节点
	var table = document.getElementById("table_stu");
	for(i = 0; i < stuStr.length; i++) {
		var tr = document.createElement("tr");
		table.appendChild(tr);
		for(j = 0; j < 4; j++) {
			var td = document.createElement("td");
			tr.appendChild(td);
			if(j == 0) {
				td.appendChild(document.createTextNode(stuStr[i].name));
			} else if(j == 1) {
				td.appendChild(document.createTextNode(stuStr[i].sex));
			} else if(j == 2) {
				td.appendChild(document.createTextNode(stuStr[i].age));
			} else if(j == 3) {
				//td.innerHTML="<input type='button' value='delete' onclick='deleteRow_1(this)'/>";
	
				var inputdelete = document.createElement("input");
				inputdelete.type = "button";
				inputdelete.value = "delete";
				inputdelete.onclick = deleteRow_2; //使用这种方法添加，函数内的this代表当前标签（节点）
				td.appendChild(inputdelete);
			}
	
		}
	}
}
//使用dom创建的button
function deleteRow_2() {
	var tr = this.parentNode.parentNode;
	tr.parentNode.removeChild(tr);

	//			var tr = this.parentNode.parentNode;
	//			var td = document.getElementById("table_stu");
	//			td.removeChild(tr);
}
//使用innerHtml创建的button
function deleteRow_1(thisd) {
	var tr = thisd.parentNode.parentNode;
	tr.parentNode.removeChild(tr);
	//			var tr = thisd.parentNode.parentNode;
	//			var td = document.getElementById("table_stu");
	//			td.removeChild(tr);
}
function info(){
	console.log(window.location.host);
	console.log(window.location.hostname);
	console.log(window.location.port);
	console.log(window.location.protocol);
	console.log(window.location.href);
	console.log(window.location.pathname);
	window.location.href="http://www.baidu.com";
//	window.location.assign("http://www.baidu.com");
}
info();
