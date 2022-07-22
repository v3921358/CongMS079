
/* global cm */

        var status = -1;
var select = -1;
var eff1 = "";//"#fUI/LogoMs/1#";
var eff2 = "";//"#fUI/LogoMs/2#";
var eff3 = "";//"#fUI/LogoMs/3#";
var eff4 = "";//"#fUI/LogoMs/4#";
var eff5 = "";//"#fUI/LogoMs/7#";
var eff6 = "";//"#fUI/LogoMs/10#";
var 红色小爱心1 ="#fEffect/CharacterEff/1112905/0/1#";
var 浅黄小爱心 ="#fMap/MapHelper/weather/balloon/5#";
var 黑色小爱心 ="#fMap/MapHelper/weather/sweetHeart/0#";
var 黑色小爱心1 ="#fMap/MapHelper/weather/sweetHeart/1#";
var 黑色小爱心2 ="#fMap/MapHelper/weather/sweetHeart/2#";
var 黑色小爱心3 ="#fMap/MapHelper/weather/sweetHeart/3#";
var 淡黄小爱心 ="#fMap/MapHelper/weather/sweetHeart/5#";
var 蝴蝶 = "#fEffect/CharacterEff/1051366/1/0#"; // 蓝色蝴蝶
var 草莓 = "#fUI/GuildMark/Mark/Plant/00003000/1#"; // 红色草莓
var 草莓1 = "#fUI/GuildMark/Mark/Plant/00003000/10#"; // 淡蓝色草莓
var 草莓2 = "#fUI/GuildMark/Mark/Plant/00003000/11#"; // 紫色草莓
var 草莓3 = "#fUI/GuildMark/Mark/Plant/00003000/15#"; // 白色草莓
var 草莓4 = "#fUI/GuildMark/Mark/Plant/00003000/3#"; // 黄色草莓
var 草莓5 = "#fUI/GuildMark/Mark/Plant/00003000/8#"; // 绿色草莓
var 大粉红爱心 = "#fItem/Etc/0427/04270001/Icon8/4#";  //
var 小粉红爱心 = "#fItem/Etc/0427/04270001/Icon8/5#";  //
var 小黄星 = "#fItem/Etc/0427/04270001/Icon9/0#";  //
var 大黄星 = "#fItem/Etc/0427/04270001/Icon9/1#";  //
var 小水滴 = "#fItem/Etc/0427/04270001/Icon10/5#";  //
var 大水滴 = "#fItem/Etc/0427/04270001/Icon10/4#";  //
var tz = "#fEffect/CharacterEff/1082565/4/0#";  //粉兔子
var tz1 = "#fEffect/CharacterEff/1082565/0/0#";  //橙兔子
var tz2 = "#fEffect/CharacterEff/1082565/2/0#";  //蓝兔子
var 邪恶小兔 = "#fEffect/CharacterEff/1112960/3/0#";  //邪恶小兔 【小】
var 音乐 = "#fEffect/CharacterEff/1112960/3/1#";  //邪恶小兔 【大】
var 花草 ="#fEffect/SetEff/208/effect/walk2/4#";
var 花草1 ="#fEffect/SetEff/208/effect/walk2/3#";
var 小花 ="#fMap/MapHelper/weather/birthday/2#";
var 桃花 ="#fMap/MapHelper/weather/rose/4#";
var 金枫叶 ="#fMap/MapHelper/weather/maple/2#";
var 红枫叶 ="#fMap/MapHelper/weather/maple/1#";
var 银杏叶 ="#fMap/MapHelper/weather/maple/3#";
var 小烟花 ="#fMap/MapHelper/weather/squib/squib4/1#";


function start() {
    if (cm.getPlayer().getLevel() < 10) {
        cm.sendOk("等级小于10级无法使用拍卖功能。");
        cm.dispose();
        return;
    }
	
			
  cm.sendSimple(

	  //"\t\t\t  #e欢迎来到#r " + cm.getChannelServer().getServerName() + " #k!#n\r\n"+
        //    "         " + eff6 + " " + eff6 + " " + eff6 + " " + " #b目前在线人数#k " + eff4 + " #r" + (cm.getTotalOnline() * 2 + cm.getRandom([0, 1])) + "#k人\r\n" +
	 /*
	    "#L99##b"+草莓+"个人信息#l"+
		"#L1##b"+草莓+"自由市场#l"+
		"#L2##b"+草莓+"万能传送#l"+
		"#L3##b"+草莓+"快速转职#l"+
		"#L4##b"+草莓+"每日任务#l"+


		*/
		
		"您好，尊敬的 #b#h ##k,欢迎来到 #r" + cm.getChannelServer().getServerName() + " #k签到中心#k，\r\n记得每天签到，福利多多哦！#l\r\n#b注：以下各种签到奖励，到达等级即可每日领取一次\r\n高等级者可#r兼领数种#k#b签到奖励！ 更有金叶、超级药水等道具赠送#l\r\n"+
		
		//"#L10##e#r签到图章兑换道具#k\r\n"+
		"#L1##e#b小学签到 需要等级#d（LV30级）\r\n"+
		"#L2##b中学签到 需要等级#d（LV70级）\r\n"+
		"#L3##b大学签到 需要等级#d（LV120级）\r\n"+
		"#L4##b博士签到 需要等级#d（LV150级）\r\n"+
		"#L5##b神级签到 需要等级#d（LV200级）\r\n"+
		
		
//=====================================================================================		
""
     );
	 
	 
	
	 
	 
	 
}

function action(mode, type, selection) {
    if (select === -1) {
        select = selection;
    }
    var level = cm.getPlayer().getLevel();
    switch (select) {
        case 0:
        {
            openNpc(9900004, "每日签到");

            break;
        }
        case 1:
        {
		if (cm.getPlayer().getBossLog("每日签到30") >= 1 || cm.getGamePoints() <= 30  || cm.getPlayerStat("LVL") < 30 ) {
            cm.sendOk("签到失败原因：\r\n1.您今天已经签到过了\r\n2.您的在线时间不足30分钟\r\n3.您的等级不足30级\r\n谢谢光临，请明天再过來。");
			cm.dispose();	
        }else{
            cm.getPlayer().setBossLog("每日签到30");
			//cm.serverNotice("『公告』：玩家【"+ cm.getChar().getName() +"】在自由市场 领取了每日奖励。");
			//cm.gainItem(2049100,5);//混沌卷轴
			//cm.gainItem(5072000,3);//高质地喇叭

				cm.gainItem(4032398, 1);//出席图章
				cm.gainItem(2000005, 10);//超级药水
				cm.gainExp( + 100000);//经验
				cm.gainMeso(+ 100000);//金币
			    //cm.gainItem(2340000,5);//祝福卷
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『每日签到』" + " : " + "[" + cm.getChar().getName() + "]成功完成了小学签到！！")); 
			cm.sendOk("每日小学签到完成,获得以下奖励:\r\n#b出席图章1个+金币10万+经验21万+超级药水10个!");
		}
			break;
		}
		
		case 2:
        {
		if (cm.getPlayer().getBossLog("每日签到70") >= 1 || cm.getGamePoints() <= 60  || cm.getPlayerStat("LVL") < 70 ) {//|| cm.getGamePoints() < 60
            cm.sendOk("签到失败原因：\r\n1.您今天已经签到过了\r\n2.您的在线时间不足60分钟\r\n3.您的等级不足70级\r\n谢谢光临，请明天再过來。");
			cm.dispose();	
        }else{
            cm.getPlayer().setBossLog("每日签到70");
			//cm.serverNotice("『公告』：玩家【"+ cm.getChar().getName() +"】在自由市场 领取了每日奖励。");
			//cm.gainItem(2049100,5);//混沌卷轴
			//cm.gainItem(5072000,3);//高质地喇叭

				//cm.gainItem(4032398, 1);//出席图章
				cm.gainItem(2000005, 50);//超级药水
				cm.gainExp( + 200000);//经验
				cm.gainMeso(+ 300000);//金币
			    //cm.gainItem(2340000,5);//祝福卷
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『每日签到』" + " : " + "[" + cm.getChar().getName() + "]成功完成了中学签到！！")); 
			cm.sendOk("每日中学签到完成,获得以下奖励:\r\n#b金币30万+经验20万+超级药水50个!");
		}
			break;
		}
		
		
		case 3:
        {
		if (cm.getPlayer().getBossLog("每日签到120") >= 1 || cm.getGamePoints() <= 120  || cm.getPlayerStat("LVL") < 120 ) {
            cm.sendOk("签到失败原因：\r\n1.您今天已经签到过了\r\n2.您的在线时间不足120分钟\r\n3.您的等级不足120级\r\n谢谢光临，请明天再过來。");
			cm.dispose();	
        }else{
            cm.getPlayer().setBossLog("每日签到120");
			//cm.serverNotice("『公告』：玩家【"+ cm.getChar().getName() +"】在自由市场 领取了每日奖励。");
			//cm.gainItem(2049100,5);//混沌卷轴
			//cm.gainItem(5072000,3);//高质地喇叭

				//cm.gainItem(4032398, 1);//出席图章
				cm.gainItem(2000005, 50);//超级药水
				cm.gainExp( + 200000);//经验
				cm.gainMeso(+ 300000);//金币
			    //cm.gainItem(2340000,5);//祝福卷
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『每日签到』" + " : " + "[" + cm.getChar().getName() + "]成功完成了大学签到！！")); 
			cm.sendOk("每日大学签到完成,获得以下奖励:\r\n#b金币50万+经验30万+超级药水100个!");
		}
			break;
		}
		
        
		case 4:
        {
		if (cm.getPlayer().getBossLog("每日签到150") >= 1 || cm.getGamePoints() <= 120  || cm.getPlayerStat("LVL") < 150 ) {
            cm.sendOk("签到失败原因：\r\n1.您今天已经签到过了\r\n2.您的在线时间不足120分钟\r\n3.您的等级不足150级\r\n谢谢光临，请明天再过來。");
			cm.dispose();	
        }else{
            cm.getPlayer().setBossLog("每日签到150");
			//cm.serverNotice("『公告』：玩家【"+ cm.getChar().getName() +"】在自由市场 领取了每日奖励。");
			//cm.gainItem(2049100,5);//混沌卷轴
			//cm.gainItem(5072000,3);//高质地喇叭

				//cm.gainItem(4032398, 1);//出席图章
				cm.gainItem(2000005, 150);//超级药水
                cm.gainExp( + 500000);//经验
				cm.gainMeso(+ 800000);//金币
				cm.gainItem(2340000,1);//祝福卷
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『每日签到』" + " : " + "[" + cm.getChar().getName() + "]成功完成了博士签到！！")); 
                cm.sendOk("每日博士签到完成,获得以下奖励:\r\n#b祝福卷轴1张+金币80万+经验50万+超级药水150个!");
		}
			break;
		}
       
        case 5:
        {
		if (cm.getPlayer().getBossLog("每日签到200") >= 1 || cm.getGamePoints() <= 120  || cm.getPlayerStat("LVL") < 200 ) {
            cm.sendOk("签到失败原因：\r\n1.您今天已经签到过了\r\n2.您的在线时间不足120分钟\r\n3.您的等级不足200级\r\n谢谢光临，请明天再过來。");
			cm.dispose();	
        }else{
            cm.getPlayer().getBossLog("每日签到200");
			//cm.serverNotice("『公告』：玩家【"+ cm.getChar().getName() +"】在自由市场 领取了每日奖励。");
			//cm.gainItem(2049100,5);//混沌卷轴
			//cm.gainItem(5072000,3);//高质地喇叭

				//cm.gainItem(4032398, 1);//出席图章
				cm.gainItem(2000005, 200);//超级药水
                cm.gainExp( + 1000000);//经验
				cm.gainMeso(+ 1000000);//金币
				cm.gainItem(2340000,5);//祝福卷
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『每日签到』" + " : " + "[" + cm.getChar().getName() + "]成功完成了神级签到！！")); 
                cm.sendOk("每日神级签到完成,获得以下奖励:\r\n#b祝福卷轴5张+金币100万+经验100万+超级药水200个!");
		}
			break;
		}
		
		
        
		
		
		case 10:
        {
            openNpc(9330079, "签到兑换");
            break;
		
		}
		
		
       




        case 100:
        {
            select3(mode, type, selection);
            break;
        }
        case 101:
        {
            cm.sendOk(
                    "每日0:00重置所有次數除了拉圖斯。\r\n" +
                    "#b皮卡啾剩餘次數：#k#r" + (3 - cm.getPlayer().getBossLogD("皮卡啾次數")) + "#k\r\n" +
                    "#b闇黑龍王剩餘次數：#k#r" + (3 - cm.getPlayer().getBossLogD("龍王次數")) + "#k\r\n" +
                    "#b殘暴炎魔次數剩餘次數：#k#r" + (3 - cm.getPlayer().getBossLogD("殘暴炎魔次數")) + "#k\r\n" +
                    "#b拉圖斯剩餘次數：#k#r" + (2 - cm.getPlayer().getBossLogD("pop")) + "#k\r\n" +
                    "#b熊獅王剩餘次數：#k#r" + (3 - cm.getPlayer().getBossLogD("熊獅王次數")) + "#k\r\n" +
                    "");
            cm.dispose();
            return;
        }

        case 102:
        {
            if (cm.haveItem(5460000)) {
                cm.gainItem(5460000, -1);
                cm.teachSkill(8, 1, 0);
                cm.teachSkill(10000018, 1, 0); // Maker
                cm.teachSkill(20000024, 1, 0); // Maker
                cm.sendOk("学习成功。");
                cm.dispose();
                return;
            } else {
                cm.sendOk("你沒有#t5460000##i5460000#。");
                cm.dispose();
                return;
            }
            break;
        }

	

	case 103:
        {
            if (cm.getOneTimeLog("新手宠物") < 1) {
                cm.setOneTimeLog("新手宠物");
                cm.gainPet(5000061, "皮卡啾", 1, 0, 100, 0, 119);//皮卡啾 90天 (寵物)
                cm.sendOk("领取新手宠物成功。");
                cm.dispose();
                return;
            } else {
                cm.sendOk("您已领取过新手宠物。");
                cm.dispose();
                return;
            }
            cm.dispose();
            break;
        }








	
		case 99://个人信息
        {
           cm.sendOk(
                    "#d"+草莓+"亲爱的玩家#r [#e#h ##n#k#r] #d\t 欢迎来到 童心冒险岛 "+草莓+"\r\n" +
                    "#d"+大黄星+"#b童心冒险岛  充值比例:\t#r1元==1余额==1000点券.\r\n" +
                    "#d"+大黄星+"#b当前在线        时间:\t#r" + java.lang.Long.valueOf((cm.getCurrentTime() - cm.getPlayer().getMrqdTime()) / 60000) + "#k分鐘\r\n" +
                    "#b"+大黄星+"#b当前可用        点券:\t#r"+cm.getPlayer().getCSPoints(1)+"  \r\n" +
                    "#b"+大黄星+"#b当前可用      抵用卷:\t#r"+cm.getPlayer().getCSPoints(2)+"  \r\n" +
                    //"#b"+大黄星+"#b当前可用        余额:\t#r"+cm.getmoneyb()+"    #k\r\n" +
					"#b"+大黄星+"#b当前可用      游戏币:\t#r" + cm.getMeso() + "#k\r\n" +

					
					
					
					
                    "");
            cm.dispose();
            return;
        }
		
		
        default :
        {
            cm.sendOk("此功能未完成");
            cm.dispose();
        }
    }
}

function select3(mode, type, selection) {
    if (mode === 1) {
        status++;
    } else if (mode === 0) {
        status--;
    }

    var i = -1;
    if (status <= i++) {
        cm.dispose();
    } else if (status === i++) {
        var gain = cm.getMP();
        if (gain <= 0) {
            cm.sendOk("目前沒有任何在線點數唷。");
            cm.dispose();
            return;
        } else {
            cm.sendYesNo("目前楓葉點數: " + cm.getMaplePoint() + "\r\n" + "目前在線點數已經累積: " + gain + " 點，是否領取?");
        }
    } else if (status === i++) {
        var gain = cm.getMP();
        cm.setMP(0);
        cm.gainMaplePoint(gain);
        cm.save();
        cm.sendOk("领取了 " + gain + " 点在线点数, 目前楓葉點數: " + cm.getMaplePoint());
        cm.dispose();
    } else {
        cm.dispose();
    }
}

function CGM(mode, type, selection) {
    if (mode === 1) {
        status++;
    } else if (mode === 0) {
        status--;
    }

    var i = -1;
    if (status <= i++) {
        cm.dispose();
    } else if (status === i++) {
        cm.sendGetText("请输入你要對GM传送的信息");
    } else if (status === i++) {
        var text = cm.getText();
        if (text === null || text === "") {
            cm.sendOk("并未输入任何內容.");
            cm.dispose();
            return;
        }
        cm.dispose();
        cm.processCommand("@CGM " + text);
    } else {
        cm.dispose();
    }
}

function openNpc(npcid) {
    openNpc(npcid, null);
}

function openNpc(npcid, script) {
    var mapid = cm.getMapId();
    cm.dispose();
    if (cm.getPlayerStat("LVL") < 10) {
        cm.sendOk("你的等級不能小于10等.");
    } else if (
            cm.hasSquadByMap() ||
            cm.hasEventInstance() ||
            cm.hasEMByMap() ||
            mapid >= 990000000 ||
            (mapid >= 680000210 && mapid <= 680000502) ||
            (mapid / 1000 === 980000 && mapid !== 980000000) ||
            mapid / 100 === 1030008 ||
            mapid / 100 === 922010 ||
            mapid / 10 === 13003000
            ) {
        cm.sendOk("你不能在这里使用这个功能.");
    } else {
        if (script == null) {
            cm.openNpc(npcid);
        } else {
            cm.openNpc(npcid, script);
        }
    }
}