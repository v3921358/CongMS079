/* ==================
 脚本类型:  玩具副本	    
 脚本作者： 颜林 
 联系方式： 1500663066
 =====================
 */

var status = 0;

var itemList1 = [

	


[4010003, 10, 1, 1],
[4010004, 10, 1, 1],
[4010005, 10, 1, 1],
[4010006, 10, 1, 1],
[4020000, 10, 1, 1],
[4000463, 10, 1, 1],//国庆纪念币
[4000313, 10, 1, 1],//黄金枫叶
[4001126, 10, 1, 1],//枫叶
[4170005, 0.001, 1, 1]//玩具蛋




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
    	var txt = "#d\t\t\t#b欢迎查看『玩具副本』奖励列表#n#k\r\n\r\n";
		txt += "\t#r通关奖励列表 \r\n";
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

