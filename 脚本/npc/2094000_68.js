var FY0 = "┃                      ┃";
var FY1 = "┃       - 枫叶 -       ┃";
var FY2 = "┃ 脚本仿制  　定制脚本 ┃";
var FY3 = "┃ 技术支持 　 游戏顾问 ┃";
var FY4 = "┃ ＷＺ添加　  地图制作 ┃";
var FY5 = "┃ 加盾防御　  售登陆器 ┃";
var FY6 = "┃       百度推广       ┃";
var FY7 = "┃ 唯一QQ:1848350048    ┃";
var FY8 = "┃                      ┃";

var status = 0;

var itemList1 = [

	
[4007000, 10, 1, 1],
[4007001, 10, 1, 1],
[4007002, 10, 1, 1],
[4007003, 10, 1, 1],
[4007004, 10, 1, 1],
[4007005, 10, 1, 1],
[4007006, 10, 1, 1],
[4007007, 10, 1, 1],

[4170009, 10, 1, 1],
[4032392, 10, 1, 1],
[4032391, 1, 1, 1]




];
var useNx = 400;
var sel0 = -1;
function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (status == 0 && mode == 0) {
        cm.dispose();
        return;
    }
    if (mode == 1) {
        status++;
		cm.dispose();
    } else {
        status--;
		cm.dispose();
    }

    if (status == 0) {
    	var txt = "#d\t\t\t#b欢迎查看『海盗副本』奖励列表#n#k\r\n\r\n";
		txt += "\t#r通关随机奖励浏览 \r\n";
		cm.dispose();
		
		var txt2 = "";
		for (var i = 0; i < itemList1.length;  i++){
			txt2 += "#i"+itemList1[i][0]+":#";
			cm.dispose();
		}
    	cm.sendSimple(txt + txt2);
		cm.dispose();
    }  
	cm.dispose();
}

