/* ==================
 脚本类型:  天空副本	    
 脚本作者： 颜林 
 联系方式： 1500663066
 =====================
 */

var status = 0;

var itemList1 = [

	


[4004000, 10, 1, 1],
[4004001, 10, 1, 1],
[4004002, 10, 1, 1],
[4004003, 10, 1, 1],
[4004004, 10, 1, 1],
[4170006, 10, 1, 1],//国庆纪念币
[4000313, 10, 1, 1],//黄金枫叶
[4001126, 10, 1, 1],//枫叶
[2049100, 0.001, 1, 1]//玩具蛋




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
    	var txt = "#d\t\t\t#b欢迎查看『天空副本』奖励列表#n#k\r\n\r\n";
		txt += "\t#r通关奖励随机数量概率 \r\n";
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

