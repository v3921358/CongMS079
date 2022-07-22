var status = -1;
function action(mode, type, selection) {
    if (mode == 1) {
		status++;
    } else {
		cm.dispose();
		return;
    }
    if (status == 0) {
		if (cm.getPlayer().getMapId() == 105050400) {
			if (cm.getPlayer().hasEquipped(1003036)) {
				cm.sendYesNo("你想到达地狱大公散步的地方?");
			} else {
				cm.sendOk("你需要佩戴#v1003036##z1003036#进入.");
				cm.dispose();
			}
		} else if (cm.getPlayer().getMapId() == 677000011) { 
			cm.warp(677000013,0);
			cm.dispose();
		} else if (cm.getPlayer().getMapId() == 677000013) {
				if (cm.getParty() == null) {
					cm.sendOk("你必须在这里开组队.");
					cm.dispose();
					return;
				}else if (cm.getMap(677000012).getCharactersSize() > 0) {
					cm.sendOk("有人已经试图在打败地狱大公了");
					cm.dispose();
					return;
				}else if (!cm.isLeader()) { 
                    cm.sendOk("请让你的队长和我说话~");
                    cm.dispose();
			        return;
                }else {
					var party = cm.getParty().getMembers();
                    var inMap = cm.partyMembersInMap();
                    var levelValid = 0;
                for (var i = 0; i < party.size(); i++) {
                if (party.get(i).getLevel() >= 10 && party.get(i).getLevel() <= 250)
                    levelValid++;
                }
			    if (levelValid != inMap) {
                cm.sendOk("请确保你的队伍里所有人员都在本地图，且最小等级在10以上.");
                cm.dispose();
				return;
			    }
			
			cm.warpParty(677000012);//传送至boss地图
			cm.dispose();
			}
			
			
		} else {
			if (!cm.isLeader()) { 
                    cm.sendOk("请让你的队长和我说话");
                    cm.dispose();
			        return;
                }else if (cm.getParty() != null) {
				cm.warpParty(105050400);
			} else {
				cm.warp(105050400,0);
			}
			cm.dispose();
		}
	} else {
		 if(cm.getBossLog("大公次数") >= 1) {
	            cm.sendOk("您好,限定每天只能挑战1次！");
                cm.dispose();
				return;
            }
		cm.setBossLog("大公次数");//给次数	
		cm.warp(677000010,0);
		cm.dispose();
	}
}