var 皇冠白 ="#fUI/GuildMark/Mark/Plant/00003006/15#";
load("nashorn:mozilla_compat.js");
importPackage(Packages.client);
importPackage(Packages.client.inventory);
importPackage(Packages.server);
importPackage(Packages.tools);
var 琴符 = "#fEffect/CharacterEff/1003252/0/0#";
var 音符 = "#fEffect/CharacterEff/1032063/0/0#";
var 花 = "#fUI/UIWindow.img/AriantMatch/characterIcon/0#";
var s = "#fUI/StatusBar/BtClaim/normal/0#";
var h = "#fUI/CashShop/CSEffect/effect/1#";
var 时间之石 = 4021010;
var status = 0;
var zones = 0;
var ItemId = Array(

        //Array(1332247, 500, "旋涡匕首"),
		//Array(1302297, 500, "旋涡剑"),
		//Array(1322223, 500, "旋涡锤"),
		//Array(1342090, 500, "旋涡刀"),
		//Array(1382231, 500, "旋涡短杖"),
		//Array(1312173, 500, "旋涡斧"),
		//Array(1452226, 500, "旋涡弓"),
		//Array(1442242, 500, "旋涡戟"),
		//Array(1152160, 500, "旋涡剑"),
		//Array(1402220, 500, "旋涡双手剑"),
		//Array(1482189, 500, "漩涡冲拳"),
		//Array(1432187, 500, "漩涡矛"),
		//Array(1462213, 500, "漩涡弩"),
		//Array(1472235, 500, "漩涡拳甲"),
		//Array(1492199, 500, "漩涡手铳"),
		//Array(1122269, 200, "旋涡吊坠"),
		//Array(1032224, 200, "旋涡耳环"),
		//Array(1052669, 200, "旋涡皇家外套"),
		//Array(1102623, 200, "旋涡披风"),
		//Array(1082556, 200, "旋涡手套"),
		//Array(1012438, 200, "旋涡文身"),
		//Array(1072870, 200, "旋涡鞋"),
		//Array(1022211, 200, "旋涡眼睛"),
		//Array(1132247, 200, "旋涡腰带"),
		//Array(1003976, 200, "漩涡帽子")
        );
function start() {
    status = -1;
    action(1, 0, 0);
}
function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    } else {
        if (status >= 0 && mode == 0) {
            cm.dispose();
            return;
        }
        if (mode == 1)
            status++;
        else
            status--;
        if (status == 0) {
            if (cm.getLevel < 10) {
                cm.sendOK("你的等级不足10级。。打开干嘛？", 2);
                cm.dispose();
            } else {
                selStr = "\t\t#e#k"+皇冠白+" 想要用荣誉兑换什么物品呢? "+皇冠白+"\r\n==============================================\r\n\t\t\t#d目前拥有荣誉数量:#r"+cm.getBeans()+"#n\r\n";
                for (var i = 0; i < ItemId.length; i++) {
                    selStr += "\r\n#L" + i + "##b#v" + ItemId[i][0] + "##k (需要#r " + ItemId[i][1] + " #k点荣誉值！)";
                }
                cm.sendSimple(selStr);
                zones == 0;
            }
        } else if (status == 1) {
            if (zones == 1) {
                cm.sendNext("你让我帮你做什么呢？", 2);
                zones = 2;
            } else if (zones == 0) {
				if (cm.getBeans() >= ItemId[selection][1]) { 
				var finalitem = Array();
				finalitem.push(ItemId[selection][1]);
				if (finalitem.length != 0) {
			var item;
            var random = new java.util.Random();
            var finalchance = random.nextInt(finalitem.length);
            var notice = finalitem[finalchance][3];
              item = cm.gainGachaponItem(ItemId[selection][0], 1, "荣誉兑换");
			if (item != -1) {
				cm.gainBeans(-(ItemId[selection][1]));
                     //   cm.gainGachaponItem(ItemId[selection][0], 1, "荣誉兑换", notice);
                        
						Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『兑换商城』" + " : " + "[" + cm.getChar().getName() + "]成功用荣誉兑换了旋涡装备！！")); 
						cm.sendOk("你兑换了1个 #b#t" + item + "");
                        cm.dispose();
					}
					else
					{
					cm.sendOk("请你确认在背包的装备，消耗，设置，其他，特殊窗口中是否有一格以上的空间。");
					cm.safeDispose();
					}
					cm.safeDispose();
					}
                    } else {
                        cm.sendOk("荣誉不足！");
                        cm.dispose();
                    }
                }
            } else if (status == 2) {
                if (zones == 2) {
                    cm.sendNext("我用于提升我魔法的时间之石被一群蘑菇妖偷走了,你能帮去抢回来吗？");
                    zones = 3;
                }
            } else if (status == 3) {
                if (zones == 3) {
                    cm.sendNext("行,这个没问题？你告诉我那些偷了你时间之石的蘑菇妖现在在什么地方呢?", 2);
                    zones = 4;
                }
            } else if (status == 4) {
                if (zones == 4) {
                    cm.sendNext("这个物品是#b全世界掉落#k的。你可以在世界上的#b任何一个怪物#k上获取！");
                    zones = 5;
                }
            } else if (status == 5) {
                if (zones == 5) {
                    cm.sendYesNo("如果你能帮我这个大忙的话,我会给你一些丰厚的奖励的，您是否愿意帮我？");
                }
            } else if (status == 6) {
                cm.setBossLog('MogoQuest');
                cm.gainItem(5220001, 1);
                cm.sendOk("非常感谢！获得后和我说话，就能换购物品了！");
                cm.dispose();
            }
    }
}	
