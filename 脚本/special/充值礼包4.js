﻿var 星星 = "#fUI/CN_Chat.img/roomList/Vip#";
var 奖励 = "#fUI/CashShop/CSDiscount/bonus#";
function start() {
    status = -1;

    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    } else {
        if (status >= 0 && mode == 0) {

            cm.sendOk("感谢你的光临！");
            cm.dispose();
            return;
        }
        if (mode == 1) {
            status++;
        } else {
            status--;
        }
        if (status == 0) {
			var tex2 = "";
            var text = "";
            for (i = 0; i < 10; i++) {
                text += "";
            }
            text += "#b领取礼包需要以下道具物品#l:\r\n\r\n" 
			//text += ""+奖励+"\r\n" 
			//text += "1.会员VIP②#v1142617##z1142617# 数量 +1\r\n" 
			//text += "2.赞助点卷:数量 +100000\r\n" 
			//text += "3.游戏金币:数量 +2亿\r\n" 
			//text += "4.材料:#v4001126##z4001126# 数量 +10000\r\n" 
			text += "#v4000422##z4000422# 数量 +1\r\n\r\n" 
                        text += ""+奖励+"\r\n\r\n" 
                        text += "#v1112422##z1112422# 全属性:25，功魔:25，血蓝：300\r\n"
                        text += "#v4310034#5个   #v4000463#100个\r\n"
						text += "#v3010045#椅子1个   #v2049118#2个\r\n\r\n"
						text += "#L1##r#e#v4000422#兑换礼包#l\r\n\r\n"
            cm.sendOk(text); 
        } else if (selection == 1) {
			if (cm.getInventory(4).isFull(0)){//判断第四个也就是其它栏的装备栏是否有一个空格
		    cm.sendOk("#b请保证其它栏位至少有1个空格,否则无法兑换.");
		    cm.dispose();
            } else if (!cm.haveItem(4000422,1)) {//判断物品
		    cm.sendOk("请你尽快收集道具物品,赞助中心在线充值哦!#v4000422#");
		    cm.dispose();
			} /*else if (!cm.haveItem(1142617, 1)) {//判断物品
		    cm.sendOk("请你把会员VIP②放在背包里哦！#v1142617#");
		    cm.dispose();
			} */else {
			cm.gainItem(4000422, -1);//扣除物品
			cm.gainItem(4310034, 5);//给物品矿石
			cm.gainItem(3010045, 1);
			cm.gainItem(4000463, 100);//国庆纪念币
			cm.gainItem(2049118, 2);//混沌卷轴
                        cm.给属性装备(1112422, 1, 0, 25, 25, 25, 25, 300, 300, 25, 25,100, 100, 0, 0, 0, 0, 0);
			cm.sendOk("#b恭喜你成功领取了会员礼包:全属性+25！#v1112422##z1112422#");
			cm.worldMessage(6,"[会员公告]：玩家【"+cm.getName()+"】领取会员礼包，感谢你对本服的大力支持！");//公告
		    cm.dispose();
			}
        }
    }
}