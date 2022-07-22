var status = 0;
var heartBig = "#fUI/UIWindow2.img/UserInfo/character/married#";
var heart = "#fEffect/CharacterEff/1042176/0/0#"
var maxCount = 3;

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (status == 0 && mode == 0) {
        cm.dispose();
        return;
    }
    if (mode == 1) {
        status++;
    } else {
        status--;
    }
    if (status == 0) {
		
		if (cm.getPQLog("新人助战奖励", 1) >= maxCount) {
			cm.sendOk("您已经不是新人啦，无法领取该奖励。");
			cm.dispose();
			return;
		}
		
		var text = heartBig + "#d亲爱的 [ #r"+cm.getPlayer().getName()+"#d ]，你好！\r\n";
		text += heartBig + "助战奖励每日可领取 [ #r1#d ] 次。\r\n"

		text += heartBig + "已领取 [ #r"+cm.getPQLog("新人助战奖励", 1)+"#d ] 次，还可以领取 [ #r"+(maxCount-cm.getPQLog("新人助战奖励", 1))+"#d ] 次\r\n";
		text += "------------------------------------------------------\r\n";
		text += heart + " #b#t5211060##d [ #r1#d ] 张\r\n";
		text += heart + " #b#t5210000##d [ #r1#d ] 张\r\n";
		text += heart + " #b#t2022708##d [ #r1#d ] 个\r\n";
		text += heart + " #b#t4001839##d [ #r100#d ] 个\r\n";
		text += heart + " #b#t4001832##d [ #r500#d ] 个\r\n";
		text += heart + " #b#t5151036##d [ #r2#d ] 张\r\n";
		text += heart + " #b#t5150040##d [ #r2#d ] 张\r\n";
		text += heart + " #b#t5152053##d [ #r2#d ] 张\r\n";
		text += heart + " #b全属性#t1112941##d [ #r1#d ] 个\r\n";
 
		
		cm.sendYesNo(text);
	} else if (status == 1) {
		
		if (!cm.canHoldSlots(5)) {
			cm.getPlayer().dropMessage(1, "领取失败，请确认每个栏位有5个格子。");
			cm.dispose();
			return;
		}
		
		if (cm.getPQLog("领取助战奖励") >= 1) {
			cm.sendOk("您今日已经领取过助战奖励了哦！");
			cm.dispose();
			return;
		}
		
		if (cm.getPQLog("新人助战奖励", 1) >= maxCount) {
			cm.sendOk("您已经不是新人啦，无法领取该奖励。");
			cm.dispose();
			return;
		}
		
		cm.setPQLog("新人助战奖励", 1);
		cm.setPQLog("领取助战奖励");
		
		cm.gainItemPeriod(5211060, 1, 2 * 60 * 60 * 1000); // 3倍经验2小时
		cm.gainItemPeriod(5210000, 1, 1); // 双倍经验 1天
		cm.gainItem(4001839, 100); //星星
		cm.gainItem(5151036, 2);
		cm.gainItem(5150040, 2);
		cm.gainItem(5152053, 2);
		cm.gainItem(2022708, 1);
		//cm.gainNX(1, 1000000);
		cm.gainLockItem(4001832, 500, true, -1, "");
		//赠送1天全属性WELCOME
		var ii = cm.getItemInfo();				
		var toDrop = cm.getNewEquip(1112941); // 生成一个Equip类                    
		toDrop.setStr(50); //装备力量
		toDrop.setDex(50); //装备敏捷
		toDrop.setInt(50); //装备智力
		toDrop.setLuk(50); //装备运气
		toDrop.setMatk(30); //物理攻击
		toDrop.setWatk(30); //魔法攻击 
		var cal = java.util.Calendar.getInstance();
		cal.add(java.util.Calendar.DATE, 1);
		var year = cal.get(java.util.Calendar.YEAR);
		var month = cal.get(java.util.Calendar.MONTH) + 1; //获得月份
		var day = cal.get(java.util.Calendar.DATE); //获取日
		var date = year + "-" + month + "-" + day + " 00:00:00.0";
		var timeStamp = java.sql.Timestamp.valueOf(date).getTime();
		var expirationDate = timeStamp;// + 86400 * 1000;
		toDrop.setExpiration(expirationDate);
		//Packages.server.MapleInventoryManipulator.addFromDrop(cm.getC(), toDrop, false);
		cm.addFromDrop(toDrop);
		cm.getPlayer().dropMessage(1, "领取成功！");
		cm.worldSpouseMessage(0x25, "[财神爷爷] : 玩家 "+cm.getPlayer().getName()+" 成功领取了新人助战奖励第 ["+cm.getPQLog("新人助战奖励", 1)+"] 次！");
		cm.dispose();
		
	}
}