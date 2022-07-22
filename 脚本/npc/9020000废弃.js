/* ==================
 脚本类型:  NPC	    
 脚本作者：月亮     
 联系方式：2412614144
 =====================
 */

var 正方箭头 = "#fUI/Basic/BtHide3/mouseOver/0#";
var 爱心 = "#fEffect/CharacterEff/1022223/4/0#";
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
var 邪恶小兔2 = "#fEffect/CharacterEff/1112960/3/1#";  //邪恶小兔 【大】
var 花草 ="#fEffect/SetEff/208/effect/walk2/4#";
var 花草1 ="#fEffect/SetEff/208/effect/walk2/3#";
var 小花 ="#fMap/MapHelper/weather/birthday/2#";
var 桃花 ="#fMap/MapHelper/weather/rose/4#";
var 金枫叶 ="#fMap/MapHelper/weather/maple/2#";
var 红枫叶 ="#fMap/MapHelper/weather/maple/1#";
var 银杏叶 ="#fMap/MapHelper/weather/maple/3#";
var 小烟花 ="#fMap/MapHelper/weather/squib/squib4/1#";
var 星星 ="#fMap/MapHelper/weather/witch/3#";
var status;
var fbmc = "废弃都市-(废弃副本)";//副本名称
var minLevel = 20;
var maxLevel = 255;
var minPartySize = 3;
var maxPartySize = 6;
var maxjinbi = 500000;//判断征集令金币
var eventname = "KerningPQ";//副本配置文件

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 1)
        status++;
    else {
        cm.dispose();
        return;
    }
    if (status == 0) {
            var tex2 = "";
            var text = "";
            for (i = 0; i < 10; i++) {
                text += "";
            }
			//显示物品ID图片用的代码是  #v这里写入ID#
            text += "#k\t\t\t#e    " + fbmc + "#k\r\n\r\n"+正方箭头+"人数限制:#r " + minPartySize + " #b- #r" + maxPartySize + "#k 队员\t\r\n"+正方箭头+"等级限制:#r " + minLevel + " #b- #r" + maxLevel + " #k级 本次通关[奖励经验"+cm.getLevel()*5000+" ] #k\r\n\r\n"//3
            text += "#L1##e#r开始组队副本#l\r\n"//3
			text += "#L2##e#b发送征集令#k(500000金币)#l\r\n\r\n"//3
            cm.sendSimple(text);
	} else if (selection == 1) {
        if (cm.getParty() == null) { // No Party
            cm.sendOk("你没有队伍无法进入！");
            cm.dispose();
        } else if (!cm.isLeader()) { // Not Party Leader
            cm.sendOk("请让你的队长和我说话~");
            cm.dispose();
        } else {
            var party = cm.getParty().getMembers();
            var inMap = cm.partyMembersInMap();
            var levelValid = 0;
            for (var i = 0; i < party.size(); i++) {
                if (party.get(i).getLevel() >= minLevel && party.get(i).getLevel() <= maxLevel)
                    levelValid++;
            }
            if (inMap < minPartySize || inMap > maxPartySize) {
                cm.sendOk("你的队伍人数不足"+minPartySize+"人.请把你的队伍人员召集到废弃都市在进入副本.");
                cm.dispose();
            } else if (levelValid != inMap) {
                cm.sendOk("请确保你的队伍人员最小等级在 "+minLevel+" 和 "+maxLevel+"之间.或者你有队员处于离线状态,请退出下线的队员");
                cm.dispose();
            } else {
                var em = cm.getEventManager("KerningPQ");
                if (em == null) {
                    cm.sendOk("这台电脑是当前不可用.");
                //} else if (em.getProperty("KPQOpen").equals("true")) {
                } else {
        if (cm.getPlayerCount(103000800) <= 0 && cm.getPlayerCount(103000801) <= 0 && cm.getPlayerCount(103000802) <= 0 && cm.getPlayerCount(103000803) <= 0 && cm.getPlayerCount(103000804) <= 0) {
		cm.removeAll(4001007);
	    cm.removeAll(4001008);
                em.startInstance(cm.getParty(), cm.getPlayer().getMap());
		} else {
                            cm.sendOk("请稍等...任务正在进行中.");
                        }
                }
                cm.dispose();
            }
        }
	} else if (selection == 2) {
            if (cm.getMeso() >= maxjinbi){//判断多少金币
                cm.gainMeso(- maxjinbi );//扣除多少金币
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(9,cm.getC().getChannel(),"废弃副本召集令" + " : " + cm.getPlayer().getName() +" 废弃副本奖励多多,我在废弃都市等你",true));//黄色
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(9,cm.getC().getChannel(),"废弃副本召集令" + " : " + cm.getPlayer().getName() +" 废弃副本奖励多多,我在废弃都市等你",true));//黄色
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(9,cm.getC().getChannel(),"废弃副本召集令" + " : " + cm.getPlayer().getName() +" 废弃副本奖励多多,我在废弃都市等你",true));//黄色
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(9,cm.getC().getChannel(),"废弃副本召集令" + " : " + cm.getPlayer().getName() +" 废弃副本奖励多多,我在废弃都市等你",true));//黄色
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(9,cm.getC().getChannel(),"废弃副本召集令" + " : " + cm.getPlayer().getName() +" 废弃副本奖励多多,我在废弃都市等你",true));//黄色

                cm.dispose();
                }else{
                    cm.sendOk("你的冒险币不足" + maxjinbi + "。无法发送征集令");
                    cm.dispose();
				}
	} else if (selection == 3) {
			text ="副本奖励：\r\n\r\n#v4004000# #v4004001# #v4004002# #v4004003# #v4004004# #v4002001# #v4031456#\r\n"
			text +="#v4260000# #v4260001# #v4260002# #v4260003# #v4260004# #v4260005# #v4260006# #v4260007# #v4260008#\r\n"//3
			text +="#v4000463# #v4031053# #v4000313# #v4001126# #v4001201# #v4011007# #v4021009# #v4251202# #v4000492#\r\n"//3
            cm.sendSimple(text);//	cm.openNpc(9310019,0);
	}	
}















