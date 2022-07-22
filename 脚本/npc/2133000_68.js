/* ==================
 脚本类型:  毒物副本	    
 脚本作者： 颜林 
 联系方式： 1500663066
 =====================
 */

var status = 0;

var itemList1 = [

	


[4020001, 10, 1, 1],
[4020002, 10, 1, 1],
[4020003, 10, 1, 1],
[4020004, 10, 1, 1],
[4020005, 10, 1, 1],
[4020006, 10, 1, 1],
[4020007, 10, 1, 1],
[4020008, 10, 1, 1],
[4032392, 10, 1, 1],
[4032391, 0.001, 1, 1]




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
    	var txt = "#d\t\t\t#b欢迎查看『毒物副本』奖励列表#n#k\r\n\r\n";
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

