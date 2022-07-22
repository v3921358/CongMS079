var 正在进行中 = "#fUI/UIWindow/Quest/Tab/enabled/1#";
var 完成 = "#fUI/UIWindow/Quest/Tab/enabled/2#";
var 正在进行中蓝 = "#fUI/UIWindow/MonsterCarnival/icon1#";
var 完成红 = "#fUI/UIWindow/MonsterCarnival/icon0#";
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
            return;
        }
        if (mode == 1) {
            status++;
        }
        else {
            status--;
        }
        if (status == 0) {
            var tex2 = "";
            var text = "";
            for (i = 0; i < 10; i++) {
                text += "";
            }		
			text +="\t\t\t\t#e#d冒险岛Ver.085\r\n\t\t\t#d  在线时间：#r" + cm.getGamePoints() + "分钟#k#n\r\n "
			text +="#b在线奖励依次为：[超级药水][祝福混沌][抽奖币][点券].\r\n"
			text +="#L1##r领取永久雇佣商人！#v5030001# x1#l\r\n\r\n\r\n"//3

			
			if(cm.getPlayer().getGamePoints() >= 100 && cm.getPlayer().getGamePointsPD() == 0){
					text += "#L2##r"+完成红+"在线时间超过100分钟！"+完成+"#v2000019#x50个#l\r\n\r\n\r\n\r\n"//3
				} else if(cm.getPlayer().getGamePoints() >= 100 && cm.getPlayer().getGamePointsPD() > 0){
					text += ""+完成红+"#r在线时间超过100分钟！#l"+完成+"\r\n\r\n"//3
				} else {
					text += ""+正在进行中蓝+"#r在线时间超过100分钟！#l"+正在进行中+"\r\n\r\n"//3
			}
			
			if(cm.getPlayer().getGamePoints() >= 200 && cm.getPlayer().getGamePointsPD() == 1){
					text += "#L3##r"+完成红+"在线时间超过200分钟！"+完成+"#v2340000#x2个.#v2049100#x2.#l\r\n\r\n\r\n"//3
				} else if(cm.getPlayer().getGamePoints() >= 200 && cm.getPlayer().getGamePointsPD() > 1){
					text += ""+完成红+"#r在线时间超过200分钟！#l"+完成+"\r\n\r\n"//3
				} else {
					text += ""+正在进行中蓝+"#r在线时间超过200分钟！#l"+正在进行中+"\r\n\r\n"//3
			}
			
			if(cm.getPlayer().getGamePoints() >= 250 && cm.getPlayer().getGamePointsPD() == 2){
					text += "#L4##r"+完成红+"在线时间超过250分钟！"+完成+"#v4310030#x1个.#l\r\n\r\n\r\n"//3
				} else if(cm.getPlayer().getGamePoints() >= 250 && cm.getPlayer().getGamePointsPD() > 2){
					text += ""+完成红+"#r在线时间超过250分钟！#l"+完成+"\r\n\r\n"//3
				} else {
					text += ""+正在进行中蓝+"#r在线时间超过250分钟！#l"+正在进行中+"\r\n\r\n"//3
			}
			if(cm.getPlayer().getGamePoints() >= 300 && cm.getPlayer().getGamePointsPD() == 3){
					text += "#L5##r"+完成红+"在线时间超过300分钟！"+完成+"点券#x1500.#l\r\n\r\n\r\n"//3
				} else if(cm.getPlayer().getGamePoints() >= 300 && cm.getPlayer().getGamePointsPD() > 3){
					text += ""+完成红+"#r在线时间超过300分钟！#l"+完成+"\r\n\r\n"//3
				} else {
					text += ""+正在进行中蓝+"#r在线时间超过300分钟！#l"+正在进行中+"\r\n\r\n"//3
			}
            cm.sendSimple(text);
        } else if (selection == 1) {
			if(cm.haveItem(5030001, 1)){
            cm.sendOk("你已经领取过了。无法重新领取！");
            cm.dispose();
			}else if (cm.haveItem(5030000, 1)){
            cm.sendOk("你已经领取过了。无法重新领取！");
            cm.dispose();
			}else{
			cm.gainItem(5030001, 1);//
			//cm.gainGamePointsPD(1);
            cm.sendOk("领取奖励成功！");
			cm.worldMessage(6,"玩家：["+cm.getName()+"]领取永久雇佣商人！");
            cm.dispose();
			}
        } else if (selection == 2) {
			cm.gainItem(2000019, 50);//特殊药水
			cm.gainGamePointsPD(1);
            cm.setBossLog("在线时间奖励");
			cm.worldMessage(6,"〈冒险岛〉：玩家["+cm.getName()+"]领取了100分钟在线奖励：50个超级药水！");
            cm.dispose();
        } else if (selection == 3) {
			cm.gainItem(2340000, 2);//超级药水
			cm.gainItem(2049100, 2);//超级药水
			cm.gainGamePointsPD(1);
            cm.setBossLog("在线时间奖励");
			cm.worldMessage(6,"〈冒险岛〉：玩家["+cm.getName()+"]领取了200分钟在线奖励：祝福混沌卷轴各2个！");
            cm.dispose();
        } else if (selection == 4) {
			cm.gainItem(4310030, 1);//特殊药水
			cm.gainGamePointsPD(1);
            cm.setBossLog("在线时间奖励");
			cm.worldMessage(6,"〈冒险岛〉：玩家["+cm.getName()+"]领取了250分钟在线奖励：极品抽奖币1个！");
            cm.dispose();
        } else if (selection == 5) {
               cm.gainNX(1500);
			cm.gainGamePointsPD(1);
            cm.sendOk("领取奖励成功！");
			cm.worldMessage(6,"〈冒险岛〉：玩家["+cm.getName()+"]领取了300分钟在线奖励：点券1500！");
            cm.dispose();
        } else if (selection == 6) {
			cm.gainGamePointsPD(-300);
                        cm.gainGamePoints(-300)
			cm.gaintodayOnlineTime(-300);
			cm.gainItem(4001322, 2);//白雪人法老蓝宝石
			cm.gainGamePointsPD(1);
            cm.sendOk("领取奖励成功！");
			cm.worldMessage(6,"玩家：["+cm.getName()+"]领取了300分钟在线奖励！");
            cm.dispose();
        } else if (selection == 7) {
                cm.gainD(200);
			cm.gainGamePointsPD(1);
            cm.sendOk("领取奖励成功！");
			cm.worldMessage(6,"玩家：["+cm.getName()+"]领取了360分钟在线奖励！");
            cm.dispose();
        } else if (selection == 8) {
               cm.gainNX(100);
			cm.gainGamePointsPD(1);
            cm.sendOk("领取奖励成功！");
			cm.worldMessage(6,"玩家：["+cm.getName()+"]领取了420分钟在线奖励！");
            cm.dispose();
		}
    }
}


