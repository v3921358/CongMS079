var 需要物品 = new Array(
[4001159,15],
[4001160,15]

);
var 获得物品 = new Array(
[1122010,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"勋章"]

);
//[1142176,5,5,5,5,0,0,5,5,0,0,0,0,0,0,"6属性+10"]
var sels;
var status = -1;

function start() {
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else if (mode == 0) {
        status--;
    } else {
        cm.dispose();
        return;
    }
    if (status == 0) {
		检测材料 = false;
        var msg = "";
        msg += "#b准备好开始合成你想要的吗？:#k\r\n";
        for (var i = 0; i < 获得物品.length; i++) {
            msg += "#i" + 获得物品[i][0] + ":##z" + 获得物品[i][0] + "##b[" + 获得物品[i][15] + "]#k\r\n";            
        }
        msg += "#b需要以下物品:--------------------------------------#k\r\n";
        for (var i = 0; i < 需要物品.length; i++) {
            msg += "#i" + 需要物品[i][0] + ":##z" + 需要物品[i][0] + "# [#b#c" + 需要物品[i][0] + "##k/#r" + 需要物品[i][1] + "#k]\r\n";   
            if (!cm.haveItem(需要物品[i][0],需要物品[i][1])){
			检测材料 = true;
			}			
        }		
        cm.sendYesNo(msg);
    } else if (status == 1) {
        sels = selection;
		if (检测材料){
		cm.sendOk("很抱歉！材料不足无法让您兑换！\r\n为了证明你能力，请把需要的材料带来！");	
        cm.dispose();
        return;			
		}

		for (var i = 0; i < 需要物品.length; i++) {			
            cm.gainItem(需要物品[i][0],-需要物品[i][1]);				
		}
		for (var i = 0; i < 获得物品.length; i++) {
		cm.gainItem(获得物品[i][0],获得物品[i][1],获得物品[i][2],获得物品[i][3],获得物品[i][4],获得物品[i][5],获得物品[i][6],获得物品[i][7],获得物品[i][8],获得物品[i][9],获得物品[i][10],获得物品[i][11],获得物品[i][12],获得物品[i][13],获得物品[i][14]);
        
		}
		cm.sendNext("恭喜你兑换成功！");
        cm.dispose();


    } else if (status == 2) {
 
    } else {
        //cm.sendNext("#r发生错误: mode : " + mode + " status : " + status);
        cm.dispose();
    }
}