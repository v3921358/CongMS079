var 星星 = "#fUI/CN_Chat.img/roomList/Vip#";
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
			text += "#v4031505##z4031505# 数量 +1\r\n\r\n" 
                        text += ""+奖励+"\r\n\r\n" 
                        text += "#v1902024#增加移动速度坐骑,全属性+30\r\n"
						text += "#v1912017#增加移动速度鞍子,全属性+30\r\n"
						text += "#v1142803#全属性+30,功魔+50\r\n"
						text += "#r找GM领取:本职业专属红色武器一把\r\n\r\n"
						text += "#L1##r#e#v4031505#兑换礼包#l\r\n\r\n"
            cm.sendOk(text); 
        } else if (selection == 1) {
			if (cm.getInventory(4).isFull(0)){//判断第四个也就是其它栏的装备栏是否有一个空格
		    cm.sendOk("#b请保证其它栏位至少有1个空格,否则无法兑换.");
		    cm.dispose();
            } else if (!cm.haveItem(4031505,1)) {//判断物品
		    cm.sendOk("请你尽快收集道具物品,赞助中心在线充值哦!#v4031505#");
		    cm.dispose();
			} else {
			cm.gainItem(4031505, -1);//扣除物品
			cm.给属性装备(1902024, 1, 0, 30, 30, 30, 30, 0, 0, 0, 0,0, 0, 0, 0, 0, 30, 0);//坐骑
			cm.给属性装备(1912017, 1, 0, 30, 30, 30, 30, 0, 0, 0, 0,0, 0, 0, 0, 0, 30, 0);//坐骑
			cm.给属性装备(1142803, 1, 0, 30, 30, 30, 30, 100, 100, 50, 50,30, 30, 12, 12, 0, 0, 0);//徽章
			cm.sendOk("#b恭喜你成功领取了万元户礼包:全属性+35！");
			cm.worldMessage(6,"[会员公告]：玩家【"+cm.getName()+"】领取万元户礼包，感谢你对本服的大力支持！");//公告
		    cm.dispose();
			}
        }
    }
}