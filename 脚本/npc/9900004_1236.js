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
            text += "#b会员VIP②需要以下道具物品#l:\r\n\r\n" 
			//text += ""+奖励+"\r\n" 
			//text += "1.会员VIP①#v1142609##z1142609# 数量 +1\r\n" 
			//text += "2.赞助点卷:数量 +40000\r\n" 
			//text += "3.游戏金币:数量 +8千万\r\n" 
			//text += "4.材料:#v4001126##z4001126# 数量 +3000\r\n" 
			text += "#v4000425# 数量 +1#v1142609# 数量 +1\r\n\r\n" 
                        text += ""+奖励+"\r\n\r\n" 
                        text += "#v1142617# 全属性:+15\r\n"
                        text += "#L1##r#e#v4000425#兑换会员VIP②#l\r\n\r\n"
            cm.sendOk(text); 
        } else if (selection == 1) {
			if (cm.getInventory(4).isFull(0)){//判断第四个也就是其它栏的装备栏是否有一个空格
		    cm.sendOk("#b请保证其它栏位至少有1个空格,否则无法兑换.");
		    cm.dispose();
            } else if (!cm.haveItem(4000425, 1)) {//判断物品
		    cm.sendOk("请你尽快收集道具物品,赞助中心在线充值哦!#v4000425##z4000425#.");
		    cm.dispose();
			} else if (!cm.haveItem(1142609, 1)) {//判断物品
		    cm.sendOk("请你把会员VIP①放在背包里哦！#v1142609#");
		    cm.dispose();
			}else {
			cm.gainItem(4000425, -1);//扣除物品
			cm.gainItem(1142609, -1);//扣物品
			//cm.gainItem(4001126, -3000);//扣物品
			//cm.gainNX(-40000);//扣点卷40000点
			//cm.gainMeso(-800000000);//扣金币800000000
                        cm.给属性装备(1142617, 1, 0, 15, 15, 15, 15, 80, 80, 8, 8,80, 80, 0, 0, 0, 0, 0);
			cm.sendOk("#b恭喜你成功领取了会员VIP②:全属性+15！#v1142617##z1142617#");
			cm.worldMessage(6,"[会员公告]：玩家【"+cm.getName()+"】领取会员VIP②感谢你对本服的大力支持！");//公告
		    cm.dispose();
			}
        }
    }
}