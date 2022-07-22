/*
 ZEVMS冒险岛(079)游戏服务端
 脚本：结婚殿堂
 */
var jt = "#fUI/Basic/BtHide3/mouseOver/0#";
var 箭头 = "#fUI/Basic/BtHide3/mouseOver/0#";
var victim;
var ring = 1112001;
function start() {
    status = -1;
    action(1, 0, 0);
}


function action(mode, type, selection) {
    if (status <= 0 && mode <= 0) {
        cm.dispose();
        return;
    }
    if (mode == 1) {
        status++;
    } else {
        status--;
    }

    var MC = cm.getServerName();
    var 性别 = cm.getPlayer().getGender();
    var 结婚开关 = cm.GetPiot("结婚开关", "1");
    var 结婚价格 = cm.GetPiot("结婚价格", "1");
	var 离婚价格 = cm.GetPiot("离婚价格", "1");
	var 点券 =cm.getPlayer().getCSPoints(1);
    if (cm.GetPiot("自由传送开关", "2") == 100) {
        cm.showInstruction("", 200, 3);
        cm.dispose();
    } else if (status <= 0) {
        var
        selStr = "我是月老，你需要伴侣吗？我可是专门帮人介绍对象的，看你人不错，要不要介绍一个给你呢？\r\n#b";
		
        selStr += "#L3##b我要结婚[#r男女#k]#b#l\r\n";
        selStr += "#L4##b我要离婚[#r双方在场情况#k#b]#l\r\n";
        selStr += "\r\n_________________________________________________\r\n";

        selStr += "#L1##b我要搞基[#r男男#k#b]#l\r\n";
        selStr += "#L2##b我要百合[#r女女#k#b]#l\r\n";


        selStr += "#L5##b我要离婚[#r单人强行出轨#k#b]#l\r\n";
        selStr += "#L6##b找[#r男性#k#b]#b小三#l\r\n";
        selStr += "#L7##b找[#r女性#k#b]#b小三#l\r\n";
        selStr += "#L8##b找儿子[#r结婚后可领#b]#l\r\n";
        selStr += "#L9##b找女儿[#r结婚后可领#b]#l\r\n";
		
        if (cm.getPlayer().getGMLevel() == 6) {
            selStr += "\r\n#L100##d" + 箭头 + " 结婚手术费用#r[GM]#k#l";
			selStr += "\r\n#L101##d" + 箭头 + " 离婚手术费用#r[GM]#k#l";
            if (cm.GetPiot("结婚开关", "1") <= 0) {
                selStr += "\r\n#L500#" + 箭头 + " #b结婚#g[开启中]#r[GM]#k#l";
            }
            if (cm.GetPiot("结婚开关", "1") >= 1) {
                selStr += "\r\n#L501#" + 箭头 + " #b结婚#r[关闭中]#r[GM]#k#l";
            }
        }
        cm.sendSimple(selStr);
    } else if (status == 1) {
        switch (selection) {
			case 100:
                cm.dispose();
                cm.openNpc(9300003, 1);
                break;
			case 101:
                cm.dispose();
                cm.openNpc(9300003, 2);
                break;
            case 9:
                cm.sendNext("未开放。");
                cm.dispose();
                break;
            case 8:
                cm.sendNext("未开放。");
                cm.dispose();
                break;
            case 7:
                cm.sendNext("未开放。");
                cm.dispose();
                break;
            case 6:
                cm.sendNext("未开放。");
                cm.dispose();
                break;
            case 5:
                cm.sendNext("单人强行出轨暂时未开通。");
                cm.dispose();
                break;
                //离婚
            case 4:
				if(点券<离婚价格){
					cm.sendNext("  #b离婚需要 #r"+离婚价格+"#k#b 点券。" );
                    cm.dispose();
                    return;
				}
				if (cm.GetPiot("变性开关", "1") > 0) {
					cm.sendNext("管理关闭了月老功能。");
                    cm.dispose();
                    return;
				}
                if (cm.getPlayer().getMarriageId() == 0) {
                    cm.sendNext("你还没结婚呢？单身狗");
                    cm.dispose();
                    return;
                }
                if (cm.getParty() == null) {
                    cm.sendNext("请和你的对象组队哦！");
                    cm.dispose();
                    return;
                }
                if (!cm.isLeader()) {
                    cm.sendNext("让队长与我对话。");
                    cm.dispose();
                    return;
                }
                var gender = cm.getPlayer().getGender();
                var mapId = cm.getPlayer().getMapId();
                var next = true;
                var party = cm.getPlayer().getParty().getMembers();
                var it = party.iterator();
                while (it.hasNext()) {
                    var cPlayer = it.next();
                    victim = cm.getPlayer().getMap().getCharacterById(cPlayer.getId());
                    if (victim.getId() != cm.getPlayer().getId() && (party.size() > 2 || victim == null || victim.getMarriageId() > 0 || victim.getMapId() != mapId || victim.getGender() == gender)) {
                        next = true;
                        break;
                    }
                }
                if (!next) {
                    cm.sendNext("请确认您跟您的的另外一半在这一张地图、不同性別、并且都在线以及队伍中沒有其他人");
                    cm.dispose();
                    return;
                }
                cm.getPlayer().setMarriageId(0);
                victim.setMarriageId(0);
                cm.getPlayer().saveToDB(false, false);
				cm.gainNX(-离婚价格);
                cm.sendNext("离婚成功，恭喜你回复单身生活。");
                cm.dispose();
                break;
                //男女结婚
            case 3:
				if(点券<结婚价格){
					cm.sendNext("  #b结婚需要 #r"+结婚价格+"#k#b 点券。" );
                    cm.dispose();
                    return;
				}
				if (cm.GetPiot("变性开关", "1") > 0) {
					cm.sendNext("管理关闭了月老功能。");
                    cm.dispose();
                    return;
				}
                if (cm.getPlayer().getMarriageId() > 0) {
                    cm.sendNext("你已经结过婚，想离婚吗？");
                    cm.dispose();
                    return;
                }
                if (cm.getParty() == null) {
                    cm.sendNext("请和你的对象组队哦！");
                    cm.dispose();
                    return;
                }
                if (!cm.isLeader()) {
                    cm.sendNext("让队长与我对话。");
                    cm.dispose();
                    return;
                }
                var gender = cm.getPlayer().getGender();
                var mapId = cm.getPlayer().getMapId();
                var next = true;
                var party = cm.getPlayer().getParty().getMembers();
                var it = party.iterator();
                while (it.hasNext()) {
                    var cPlayer = it.next();
                    victim = cm.getPlayer().getMap().getCharacterById(cPlayer.getId());
                    if (victim.getId() != cm.getPlayer().getId() && (party.size() > 2 || victim == null || victim.getMarriageId() > 0 || victim.getMapId() != mapId || victim.getGender() == gender)) {
                        next = true;
                        break;
                    }
                }
                if (!next) {
                    cm.sendNext("请确认您跟您的的另外一半在这一张地图、不同性別、并且都在线以及队伍中沒有其他人");
                    cm.dispose();
                    return;
                }
                if (!victim.hasEquipped(ring) || !cm.getPlayer().hasEquipped(ring)) {
                    cm.sendNext("您或您的另一半沒有装备#v" + ring + "##z" + ring + "#？");
                    cm.dispose();
                    return;
                }
                if (!cm.canHold(1112804) || !victim.canHold(1112804)) {
                    cm.sendNext("您或您的另一半背包空间不足");
                    cm.dispose();
                    return;
                }
                if (victim.getGender() == 性别) {
                    cm.sendNext("你们 2 个性别一样啊？百合还是搞基呢？");
                    cm.dispose();
                    return;
                }
                cm.getPlayer().setMarriageId(victim.getId());
                victim.setMarriageId(cm.getPlayer().getId());
                cm.givePartyItems(1112804, 1, false);
				cm.gainNX(-结婚价格);
                cm.getPlayer().saveToDB(false, false);
                Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(11, cm.getClient().getChannel(), "[月老]" + " : " + " [" + cm.getChar().getName() + "] 和 [" + victim.getName() + "] 结为夫妻。"));
                //cm.ShowMarrageEffect();
                break;
                //男男
            case 1:
				if(点券<结婚价格){
					cm.sendNext("  #b离婚需要 #r"+结婚价格+"#k#b 点券。" );
                    cm.dispose();
                    return;
				}
				if (cm.GetPiot("变性开关", "1") > 0) {
					cm.sendNext("管理关闭了月老功能。");
                    cm.dispose();
                    return;
				}
                if (cm.getPlayer().getMarriageId() > 0) {
                    cm.sendNext("你已经结过婚，想离婚吗？");
                    cm.dispose();
                    return;
                }
                if (cm.getPlayer().getGender() != 0) {
                    cm.sendNext("你不是男的？");
                    cm.dispose();
                    return;
                }
                if (cm.getParty() == null) {
                    cm.sendNext("请和你的对象组队哦！");
                    cm.dispose();
                    return;
                }
                if (!cm.isLeader()) {
                    cm.sendNext("让队长与我对话。");
                    cm.dispose();
                    return;
                }
                var gender = cm.getPlayer().getGender();
                var mapId = cm.getPlayer().getMapId();
                var next = true;
                var party = cm.getPlayer().getParty().getMembers();
                var it = party.iterator();
                while (it.hasNext()) {
                    var cPlayer = it.next();
                    victim = cm.getPlayer().getMap().getCharacterById(cPlayer.getId());
                    if (victim.getId() != cm.getPlayer().getId() && (party.size() > 2 || victim == null || victim.getMarriageId() > 0 || victim.getMapId() != mapId || victim.getGender() == gender)) {
                        next = true;
                        break;
                    }
                }
                if (!next) {
                    cm.sendNext("请确认您跟您的的另外一半在这一张地图、不同性別、并且都在线以及队伍中沒有其他人");
                    cm.dispose();
                    return;
                }
                if (!victim.hasEquipped(ring) || !cm.getPlayer().hasEquipped(ring)) {
                    cm.sendNext("您或您的另一半沒有装备#v" + ring + "##z" + ring + "#？");
                    cm.dispose();
                    return;
                }
                if (!cm.canHold(1112804) || !victim.canHold(1112804)) {
                    cm.sendNext("您或您的另一半背包空间不足");
                    cm.dispose();
                    return;
                }
                if (victim.getGender() != 性别) {
                    cm.sendNext("2个男的才可以搞基！");
                    cm.dispose();
                    return;
                }
                cm.getPlayer().setMarriageId(victim.getId());
                victim.setMarriageId(cm.getPlayer().getId());
                cm.givePartyItems(1112804, 1, false);
				cm.gainNX(-结婚价格);
                cm.getPlayer().saveToDB(false, false);
                Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(11, cm.getClient().getChannel(), "[月老]" + " : " + " [" + cm.getChar().getName() + "] 和 [" + victim.getName() + "] 结为基友。"));
                //cm.ShowMarrageEffect();
                break;
                //女女
            case 2:
				if(点券<结婚价格){
					cm.sendNext("  #b离婚需要 #r"+结婚价格+"#k#b 点券。" );
                    cm.dispose();
                    return;
				}
				if (cm.GetPiot("变性开关", "1") > 0) {
					cm.sendNext("管理关闭了月老功能。");
                    cm.dispose();
                    return;
				}
                if (cm.getPlayer().getMarriageId() > 0) {
                    cm.sendNext("你已经结过婚，想离婚吗？");
                    cm.dispose();
                    return;
                }
                if (cm.getPlayer().getGender() != 1) {
                    cm.sendNext("你不是女的？");
                    cm.dispose();
                    return;
                }
                if (cm.getParty() == null) {
                    cm.sendNext("请和你的对象组队哦！");
                    cm.dispose();
                    return;
                }
                if (!cm.isLeader()) {
                    cm.sendNext("让队长与我对话。");
                    cm.dispose();
                    return;
                }
                var gender = cm.getPlayer().getGender();
                var mapId = cm.getPlayer().getMapId();
                var next = true;
                var party = cm.getPlayer().getParty().getMembers();
                var it = party.iterator();
                while (it.hasNext()) {
                    var cPlayer = it.next();
                    victim = cm.getPlayer().getMap().getCharacterById(cPlayer.getId());
                    if (victim.getId() != cm.getPlayer().getId() && (party.size() > 2 || victim == null || victim.getMarriageId() > 0 || victim.getMapId() != mapId || victim.getGender() == gender)) {
                        next = true;
                        break;
                    }
                }
                if (!next) {
                    cm.sendNext("请确认您跟您的的另外一半在这一张地图、不同性別、并且都在线以及队伍中沒有其他人");
                    cm.dispose();
                    return;
                }
                if (!victim.hasEquipped(ring) || !cm.getPlayer().hasEquipped(ring)) {
                    cm.sendNext("您或您的另一半沒有装备#v" + ring + "##z" + ring + "#？");
                    cm.dispose();
                    return;
                }
                if (!cm.canHold(1112804) || !victim.canHold(1112804)) {
                    cm.sendNext("您或您的另一半背包空间不足");
                    cm.dispose();
                    return;
                }
                if (victim.getGender() != 性别) {
                    cm.sendNext("2个女的才可以百合！");
                    cm.dispose();
                    return;
                }
                cm.getPlayer().setMarriageId(victim.getId());
                victim.setMarriageId(cm.getPlayer().getId());
                cm.givePartyItems(1112804, 1, false);
                cm.getPlayer().saveToDB(false, false);
				cm.gainNX(-结婚价格);
                Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(11, cm.getClient().getChannel(), "[月老]" + " : " + " [" + cm.getChar().getName() + "] 和 [" + victim.getName() + "] 结为百合。"));
                //cm.ShowMarrageEffect();
                break;
            case 500:
                cm.GainPiot("结婚开关", "1", -结婚开关);
                cm.GainPiot("结婚开关", "1", 1);
                cm.dispose();
                cm.openNpc(9300003, 0);
                break;
            case 501:
                cm.GainPiot("结婚开关", "1", -结婚开关);
                cm.dispose();
                cm.openNpc(9300003, 0);
                break

        }
    }
}
