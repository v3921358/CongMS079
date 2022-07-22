var 铅笔图标 = "#fUI/UIWindow.img/PvP/btWrite/mouseOver/0#";
var 警报灯 = "#fUI/StatusBar/BtClaim/normal/0#";
var 兔子1 = "#fEffect/CharacterEff/1082565/0/0#";
var 兔子2 = "#fEffect/CharacterEff/1082565/2/0#";
var 兔子3 = "#fEffect/CharacterEff/1082565/4/0#";
var 商店物品 = Array(
			//Array(5062000,1,50, ""), 
			//Array(5062000,10,480, ""),
			//Array(2049401,1,30, ""),
			//Array(2049401,10,280, ""),	
			Array(2340000,1,10, "")
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
	//cm.Lunpan();
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
                   str1 += "#L"+i+"##v"+商店物品[i][0]+"##z"+商店物品[i][0]+"# 数量×#b"+商店物品[i][1]+"个#d  价格:#r"+商店物品[i][2]+"#d "+商店物品[i][3]+"#l\r\n";
            }
            cm.sendSimple("你好，这里是 - 豆豆兑换宝库 - \r\n 您目前的豆豆值:#r"+cm.getBeans()+"#n#d  \r\n\r\n"+str1);//#L2#"+兔子2+"#r推广系统介绍 - 奖励分红详细说明
        } else if (status == 1) {
			if(cm.getBeans() >= 商店物品[selection][2]){
				cm.gainBeans(-商店物品[selection][2]);
				cm.gainItem(商店物品[selection][0],商店物品[selection][1]); 
				cm.sendOk("购买成功~!");
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『兑换商城』" + " : " + "[" + cm.getChar().getName() + "]成功用豆豆兑换了特殊道具！！")); 

				//cm.喇叭(1,"["+ cm.getPlayer().getName() + "] 在推广值商店中购买商品!");
                cm.dispose();
			} else {
				cm.sendOk("您的豆豆值不足，请去自由市场挂机泡点获得~!");
                cm.dispose();
			}
		}
	}
}


