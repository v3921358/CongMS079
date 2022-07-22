﻿/* ==================
 脚本类型:  NPC	    
 脚本作者： 一线海-维多利亚 
 联系方式： 297870163
 =====================
 */
var status;
var exp = 5950;
			
function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
	
	var next = true;
	   var size = 0;
	   var it = cm.getPlayer().getParty().getMembers().iterator();
			while (it.hasNext()) {
				var cPlayer = it.next();
				var ccPlayer = cm.getPlayer().getMap().getCharacterById(cPlayer.getId());
				if (ccPlayer == null) {
					next = false;
					break;
				}
				size++;
			}
			
    if (status == -1 && cm.isLeader()) {
	var eim = cm.getEventInstance();

	if (eim.getProperty("crackLeaderPreamble") == null) {
	    eim.setProperty("crackLeaderPreamble", "done");
	    cm.sendNext("请干掉 窗台上的 #b黑色老鼠#k 然后就会召唤 #b剧情战斗机#k 干掉之后捡到钥匙再来找我。");
	    cm.dispose();
	} else {
	    if (cm.haveItem(4001023)) {
		status = 0;
		cm.sendNext("恭喜完成，想要前往颁奖之地？");
	    } else {
		cm.sendNext("请打败#r巨型战斗机#k 给我#v4001023#。");
		cm.dispose();
	    }
	}
    } else if (status == -1 && !cm.isLeader()) {
	cm.sendNext("请干掉 窗台上的 #b黑色老鼠#k 然后就会召唤 #b剧情战斗机#k 干掉之后捡到钥匙再来来请队长找我。");
	cm.dispose();
    } else if (status == 0 && cm.isLeader() && next && size >= 1) {
	
	
	var eim = cm.getEventInstance();
	clear(9,eim,cm);
	cm.gainItem(4001023,-1);

	var players = eim.getPlayers();
	cm.givePartyExp(exp, players);
	eim.setProperty("cleared", "true"); //set determine
	eim.restartEventTimer(60000);
	var bonusmap = cm.getMap(922011000);
	for (var i = 0; i < players.size(); i++) {
	    players.get(i).changeMap(bonusmap, bonusmap.getPortal(0));
	}
	cm.dispose();
    } else {
	cm.sendOk("捡到钥匙后,组队成员必须全部在这里哟。");
	cm.dispose();
    }
	
	
}

function clear(stage, eim) {
    eim.setProperty("stage" + stage.toString() + "status","clear");

    cm.showEffect(true, "quest/party/clear");
    cm.playSound(true, "Party1/Clear");
}