var aaa ="#fUI/UIWindow.img/PvP/Scroll/enabled/next2#";

var status = 0;
var typed=0;
var RMB = 0;

function start() {
	status = -1;
	action(1, 0, 0);
}

function action(mode, type, selection) {
	if (mode == -1) {
		cm.dispose();
	} else {
		if (mode == 0 && status == 0) {
			cm.dispose();
			return;
		}
		if (mode == 1)
			status++;
		else
			status--;
		if (status == 0) {
			var selStr = "#g-------------------#e#r新年红包#k#g-------------------#k\r\n\r\n#b#b管理提示：懒人请无视，此福利越勤奋获得福利越多\r\n\r\n不要说没有福利，就看你是不是勤快了\r\n\r\n#b收集：#r#z4034251#、#z4034252#、#z4034253#、#z4034254##k#b 各 #k#r100 #b个制作 #r#z2430210##k\r\n\r\n#b不知道你今天能制作多少红包呢？\r\n\r\n";
			selStr +="#L1##r"+aaa+" 红包制作说明[详情点击查看]#l#k\r\n\r\n"; 
			selStr +=" \r\n\r\n";
                        cm.sendSimple(selStr);	
		} else if (status == 1) {
			if (selection == 1) {
				typed=1;
				cm.sendYesNo("- #d---------------#e活动要求---------------#n#k\r\n#b制作红包需要 #r#z4034251#、#z4034252#、#z4034253#、#z4034254##k#b 各 #k#r100 #b个#n#k\r\n#r(PS:世界怪物都有掉落,爆率：很高)\r\n\r\n#b当前拥有 #r#z4034251##k#b 个数为：           #r" + cm.getItemQuantity(4034251) + " / 100 个\r\n#b当前拥有 #r#z4034252##k#b 个数为：           #r" + cm.getItemQuantity(4034252) + " / 100 个\r\n#b当前拥有 #r#z4034253##k#b 个数为：           #r" + cm.getItemQuantity(4034253) + " / 100 个\r\n#b当前拥有 #r#z4034254##k#b 个数为：           #r" + cm.getItemQuantity(4034254) + " / 100 个\r\n#b当前您获得的 #r#z2430210##k#b 个数为：   #r" + cm.getItemQuantity(2430210) + " 个\r\n#b当前任务已经获得 #r抵用卷#k 为：     #r"+(2000*cm.getBossLog("制作红包", 1))+" 点\r\n#e#d管理提示：#n#k#b每制作一个#r#z2430210##k#b可以获得 #r2000#b 点抵用卷\r\n打开 #r#z2430210##k#b 还可以获得2000点卷\r\n点是制作红包，点否返回上一页，请选择：");
			}
		} else if (status == 2) {
			if(typed==1){
                if (cm.haveItem(4034251,100) && cm.haveItem(4034252,100) && cm.haveItem(4034253,100) && cm.haveItem(4034254,100)&& cm.getSpace(4) >= 1) {
			cm.gainItem(4034251, -100);
			cm.gainItem(4034252, -100);
			cm.gainItem(4034253, -100);
			cm.gainItem(4034254, -100);
			cm.gainItem(2430210, 1);
			cm.gainNX(2, 2000);
			cm.setBossLog("制作红包", 1);
			cm.sendOk("#b成功获得了一个 #r红包#b 和 #r2000#b 点抵用卷奖励。");
			cm.worldSpouseMessage(0x20, "『红包制作』 : "+ cm.getChar().getName() +" 制作红包获得2000点抵用卷。总共获得了 "+(2000*cm.getBossLog("制作红包", 1))+" 点抵用卷。");
			cm.dispose();;
				} else {
			cm.sendOk("您的物品不够或者背包空间不足.");
			cm.dispose();
				}
           }
		}
	  }
	}