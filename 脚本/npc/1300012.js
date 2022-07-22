function start() {
    cm.sendSimple("移動到#b#m106021500##k 要前往到哪裡呢？ #b\r\n#L0#打三色雪吉拉 (需要組隊)#l\r\n#L1#拯救菲歐娜#l#k");
}

function action(mode,type,selection) {
    if (mode == 1) {
	switch(selection) {
	    case 0:
	    if (cm.getPlayer().getParty() == null || !cm.isLeader()) {
		cm.sendOk("請找你的隊長來和我說話。");
	    } else {
		var party = cm.getPlayer().getParty().getMembers();
		var mapId = cm.getPlayer().getMapId();
		var next = true;
		var size = 0;
		var it = party.iterator();
		while (it.hasNext()) {
			var cPlayer = it.next();
			var ccPlayer = cm.getPlayer().getMap().getCharacterById(cPlayer.getId());
			if (ccPlayer == null) {
				next = false;
				break;
			}
			size += (ccPlayer.isGM() ? 4 : 1);
		}	
		if (next && (cm.getPlayer().isGM() || size >= 3)) {
	    	    for(var i = 0; i < 10; i++) {
			if (cm.getMap(106021500 + i).getCharactersSize() == 0) {
		    		cm.warpParty(106021500 + i);
				cm.dispose();
		    		return;
			}
	    	    }
			cm.sendOk("已經有另一個隊伍在挑戰，請稍後再嘗試。");
		} else {
			cm.sendOk("隊伍裡需要三個人以上。");
		}
	    }
		break;
	    case 1:
		cm.warp(106021401,0);
		break;
	}
    }
    cm.dispose();
}