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
			text += "#v4031504##z4031504# 数量 +1\r\n\r\n" 
                        text += ""+奖励+"\r\n\r\n" 
                        text += "#v1032024#顶级透明时装,全属性+20  功魔+10\r\n"
						text += "#v1003276#顶级透明时装,全属性+20  功魔+10\r\n"
						text += "#v1072153#顶级透明时装,全属性+20  功魔+10\r\n"
						text += "#v1082102#顶级透明时装,全属性+20  功魔+10\r\n"
						text += "#v2614000#5个\r\n"
						text += "#r找GM领取:顶级翅膀一件\r\n\r\n"
						text += "#r找GM领取一次提升满觉醒，满卷武器或装备\r\n\r\n"
						text += "#v1142803#找GM提升2星，全属性+20,功魔+10\r\n\r\n"
						
						text += "#L1##r#e#v4031504#兑换礼包#l\r\n\r\n"
            cm.sendOk(text); 
        } else if (selection == 1) {
			if (cm.getInventory(4).isFull(0)){//判断第四个也就是其它栏的装备栏是否有一个空格
		    cm.sendOk("#b请保证其它栏位至少有1个空格,否则无法兑换.");
		    cm.dispose();
            } else if (!cm.haveItem(4031504,1)) {//判断物品
		    cm.sendOk("请你尽快收集道具物品,赞助中心在线充值哦!#v4031505#");
		    cm.dispose();
			} else {
			cm.gainItem(4031504, -1);//扣除物品
			cm.gainItem(2614000, 5);
			cm.给属性装备(1032024, 1, 0, 20, 20, 20, 20, 0, 0, 10, 10,20, 20, 5, 5, 8, 8, 0);//耳环
			cm.给属性装备(1003276, 1, 0, 20, 20, 20, 20, 0, 0, 10, 10,20, 20, 5, 5, 8, 8, 0);//帽子
			cm.给属性装备(1072153, 1, 0, 20, 20, 20, 20, 0, 0, 10, 10,20, 20, 5, 5, 8, 8, 0);//鞋子
			cm.给属性装备(1082102, 1, 0, 20, 20, 20, 20, 0, 0, 10, 10,20, 20, 5, 5, 8, 8, 0);//手套
			cm.sendOk("#b恭喜你成功领取了累计充值两万元礼包！");
			cm.worldMessage(6,"[会员公告]：玩家【"+cm.getName()+"】领取累计充值两万元礼包，感谢你对本服的大力支持！");//公告
		    cm.dispose();
			}
        }
    }
}