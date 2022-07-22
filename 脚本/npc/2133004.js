/*
 ZEVMS冒险岛(079)游戏服务端
 脚本：毒雾森林
 */
var status = -1;

function action(mode, type, selection) {
	var next = true;
	var size = 0;
	var it = cm.getPlayer().getParty().getMembers().iterator();
    if (mode == 1) {
	status++;
    } else {
	status--;
    }
    switch(cm.getPlayer().getMapId()) {
	case 930000500:
	    if (!cm.haveItem(4001163)) {
	    	cm.sendNext("把#b#t4001163##k带来给我。");
	    } else {
			while (it.hasNext()) {
				var cPlayer = it.next();
				var ccPlayer = cm.getPlayer().getMap().getCharacterById(cPlayer.getId());
				if (ccPlayer == null) {
					next = false;
					break;
				}
				size++;
			    }
	            if (next && size >= 1) {
				cm.warpParty(930000600);
                cm.dispose();
				} else {
				cm.sendOk("恭喜您，已经拿#b#t4001163##k。但是队伍成员有人没在这里，要保证成员都在才可以进入下一关");
				cm.dispose();
			    }
                
	    }
	    break;
    }
    cm.dispose();
}