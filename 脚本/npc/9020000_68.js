var status = 0;
//普通奖池
var itemList1 = [
	//物品id，几率，数字越大概率越大，数量
	


[4000463, 10, 1, 1],
[4170002, 10, 1, 1],
[4001126, 10, 1, 1],
[4010000, 10, 1, 1],
[4010001, 10, 1, 1],
[4010002, 0.001, 1, 1]




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
    	var txt = "#d\t\t\t#b欢迎查看『废弃副本』奖励列表#n#k\r\n\r\n";
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

