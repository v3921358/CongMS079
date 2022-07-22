var status = -1;
var beauty = 0;
var tosend = 0;
var sl;
var mats;
var xx = -1;
var jiage = -1;

	function start() {
		status = -1;
		action(1, 0, 0);
		}


	function action(mode, type, selection) {
		if (mode == -1) {
		cm.sendOk("冒险岛感谢有你！");
		cm.dispose();
		} else {

	if (mode == 0) {
		cm.sendOk("冒险岛感谢有你！");
        	cm.dispose();
        	return;
    		}

	if (mode == 1) {
		status++;
		} else {
		status--;
		}
        if (status == 0) {
            var gsjb = "";
            gsjb = "我这里是伤害上限突破处!有需要帮忙的吗?\r\n";
            gsjb += "#r#e你的伤害太低了！是否想提升你的伤害呢？我这里可以帮你！但是首先你得有足够的材料！\r\n";
			gsjb += "#k#b每次突破可以提高限制10000点伤害！你还需要多多努力呀~ #k\r\n";
			gsjb += "按照你目前的伤害上限为:#r#e"+ ((cm.getChar().getPGSXDJ()*10000)+199999) +"#k#n\r\n";
			gsjb += "当前拥有#v2614000##z2614000#[#r#c2614000##k]个!赶快突破伤害限制吧！\r\n";
            gsjb += "#L3##e#b立即突破#v2614000##r[Hot]#l\r\n";
			gsjb += "#L33##e#b购买突破石#v2614000##r[Hot]#l\r\n";
            cm.sendSimple(gsjb);
        } else if (status == 1) {
            if (selection == 3) {
                //var iter = cm.getChar().getInventory(2).listById(2614000).iterator();
            if (cm.haveItem(2614000) == 0) {
                cm.sendNext("您的帐户#z2614000#数量不足突破伤害限制。");
                status = -1;
        } else {
                beauty = 3;
                cm.sendGetNumber("请输入对应#b#v2614000##z2614000##k突破#r#k伤害限制:\r\n#b比例 - (#r1= 10000#b)\r\n你的伤害限制最高信息 - \r\n    目前最高限制为: #r" +
                            ((cm.getChar().getPGSXDJ()*10000)+199999) + "   \r\n", 1, 1, 32767);
		}
		}
		if (selection == 33) {
             cm.dispose();
			 cm.openNpc(9270064,  "购买突破石");
		}
		
		
        } else if (status == 2) {
            if (beauty == 1) {
            if (selection <= 0) {
                    cm.sendOk("输入的兑换数字错误。");
                    cm.dispose();
		}
        }else if (beauty == 3) {
				var mapId = cm.getPlayer().getMapId();
            if (cm.haveItem(2614000,selection) && ((cm.getChar().getPGSXDJ()*10000)+199999) <= 10000000) {//判断最高突破多少
                    cm.gainItem(2614000, -selection);
					cm.getChar().gainPGSXDJ(1*selection);
                    cm.sendOk("您成功将#z2614000##v2614000# x #r" + selection + " #k\r\n增加了#r " + (1*selection)*10000 + " #k破功伤害上限。\r\n目前破功限制:#r#e"+ ((cm.getChar().getPGSXDJ()*10000)+199999) +"#k#n\r\n还有待提升呀！");
					Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(11, cm.getClient().getChannel(), "[破攻系统]" + " : " + " 玩家: [ "+ cm.getPlayer().getName() +" ] 最高破攻伤害突破到了 "+((cm.getChar().getPGSXDJ()*10000)+199999)+" 快去膜拜吧！"));
					cm.getChar().saveToDB(true,false);
					//cm.changeJob(cm.getPlayer().getJob());
					cm.dispose();
                } else {
                    cm.sendNext("您的输入的数量错误，无法突破伤害上限。或者你已经达到最高1000万伤害值了");
                    cm.dispose();
                }
			} 
        }
    }
}
