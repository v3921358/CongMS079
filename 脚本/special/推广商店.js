var 铅笔图标 = "#fUI/UIWindow.img/PvP/btWrite/mouseOver/0#";
var 警报灯 = "#fUI/StatusBar/BtClaim/normal/0#";
var 兔子1 = "#fEffect/CharacterEff/1082565/0/0#";
var 兔子2 = "#fEffect/CharacterEff/1082565/2/0#";
var 兔子3 = "#fEffect/CharacterEff/1082565/4/0#";
var 商店物品 = Array(
			Array(4001129,10,20, "枫叶"), 
			Array(4001129,1,20, "五彩水晶"),
			Array(4001129,1,5, "蓝宝石"),
			Array(4001129,1,5, ""),	
			Array(4001129,1,10, "")
			);
function start() {
    status = -1;

    action(1, 0, 0);
}
function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    }
    else {
        if (status >= 0 && mode == 0) {

            cm.sendOk("感谢你的光临！");
	cm.dispose();
           // cm.Guaguale();
            return;
        }
        if (mode == 1) {
            status++;
        }
        else {
            status--;
        }
        if (status == 0) {
			var str1 = "";	
			for (var i = 0; i < 商店物品.length; i++){
                   str1 += "#L"+i+"##v"+商店物品[i][0]+"##z"+商店物品[i][0]+"#× #b"+商店物品[i][1]+"#d  价格:#r"+商店物品[i][2]+"#d "+商店物品[i][3]+"#l\r\n";
            }
            cm.sendSimple("你好，这里是 - 推广值商店 - \r\n 您目前的推广值:#r"+cm.获取推广值()+"#n#d  \r\n\r\n"+str1);//#L2#"+兔子2+"#r推广系统介绍 - 奖励分红详细说明
        } else if (status == 1) {
			if(cm.获取推广值() >= 商店物品[selection][2]){
				cm.更改推广值(-商店物品[selection][2]);
				cm.gainItem(商店物品[selection][0],商店物品[selection][1]); 
				cm.sendOk("购买成功~!");
				cm.喇叭(1,"["+ cm.getPlayer().getName() + "] 在推广值商店中购买商品!");
                cm.dispose();
			} else {
				cm.sendOk("推广值不足~!");
                cm.dispose();
			}
		}
	}
}


