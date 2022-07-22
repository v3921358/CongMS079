
importPackage(Packages.tools);

var exitMap = 0;
var waitingMap = 1;
var reviveMap = 2;
var fieldMap = 3;
var winnerMap = 4;
var loserMap = 5;

function init() {
}

function monsterValue(eim, mobId) {
    return 1;
}

function setup(mapid) {
    var map = parseInt(mapid);
    var eim = em.newInstance("cpq" + mapid);
    eim.setInstanceMap(980000000); // <exit>
    eim.setInstanceMap(map);
    eim.setInstanceMap(map+2);
    eim.setInstanceMap(map+1).resetFully();
	em.getMapFactory().destroyMap(map+1, false);
    eim.setInstanceMap(map+3);
    eim.setInstanceMap(map+4);
    eim.setProperty("forfeit", "false");
    eim.setProperty("blue", "-1");
    eim.setProperty("red", "-1");
    var portal = eim.getMapInstance(reviveMap).getPortal("pt00");
    portal.setScriptName("MCrevive1");
    //em.schedule("timeOut", 30 * 60000);
    eim.setProperty("started", "false");
    return eim;
}

function playerEntry(eim, player) {
    player.disposeClones();
    player.changeMap(eim.getMapInstance(waitingMap), eim.getMapInstance(waitingMap).getPortal(0));
    player.tryPartyQuest(1301);
}


function registerCarnivalParty(eim, carnivalParty) {
    if (eim.getProperty("red").equals("-1")) {
        eim.setProperty("red", carnivalParty.getLeader().getId() + "");
        eim.schedule("end", 3 * 60 * 1000); // 3 minutes
		eim.broadcastPlayerMsg(5, "接下來的三分您的队伍可以找寻其他人进行挑战");
    } else {
        eim.setProperty("blue", carnivalParty.getLeader().getId() + "");
		eim.schedule("check", 1000);
		eim.broadcastPlayerMsg(5, "正在检测是否异常.");
    }
}
function playerDead(eim, player) {
}

function leftParty(eim, player) {
    disbandParty(eim);
}

function disbandParty(eim) {
    //if (eim.getProperty("started").equals("true")) {
    //    warpOut(eim);
    //} else {
	disposeAll(eim);
    //}
}

function disposeAll(eim) {
    	var iter = eim.getPlayers().iterator();
    	while (iter.hasNext()) {
	    var player = iter.next();
            eim.unregisterPlayer(player);
            player.changeMap(eim.getMapInstance(exitMap), eim.getMapInstance(exitMap).getPortal(0));
			try {
				player.getCarnivalParty().removeMember(player);
			} catch (e) {
			}
        }
        eim.dispose();
}

function playerExit(eim, player) {
    eim.unregisterPlayer(player);
    player.getCarnivalParty().removeMember(player);
    player.changeMap(eim.getMapInstance(exitMap), eim.getMapInstance(exitMap).getPortal(0));
    eim.disposeIfPlayerBelow(0, 0);
}

//for offline players
function removePlayer(eim, player) {
    eim.unregisterPlayer(player);
    player.getCarnivalParty().removeMember(player);
    player.getMap().removePlayer(player);
    player.setMap(eim.getMapInstance(exitMap));
    eim.disposeIfPlayerBelow(0, 0);
}


function getParty(eim, property) {
	var chr = em.getChannelServer().getPlayerStorage().getCharacterById(parseInt(eim.getProperty(property)));
    if (chr == null) {
	eim.broadcastPlayerMsg(5, "队伍的队长 " + property + " 找不到.");
	disposeAll(eim);
	return null;
    } else {
	return chr.getCarnivalParty();
    }
}

function start(eim) {
    eim.setProperty("started", "true");
    eim.startEventTimer(10 * 60 * 1000);
    var blueP = getParty(eim, "blue");
    if ( blueP != null )
        blueP.warp(eim.getMapInstance(fieldMap), "blue00");
    var redP = getParty(eim, "red");
    if( redP != null )
        redP.warp(eim.getMapInstance(fieldMap), "red00");
}

function check(eim) {
	var ck = eim.check();
	//if(ck) {//注释了就不检测偷渡者
		eim.broadcastPlayerMsg(5, "检测无异常.");
		eim.schedule("start", 10000);
		eim.broadcastPlayerMsg(5, "10秒后将开始对战!");
	/*} else {
		eim.broadcastPlayerMsg(5, "发现异常,即将传送出去!");
		disposeAll(eim);
	}*/   //注释了就不检测偷渡者
}

function monsterKilled(eim, chr, cp) {
    chr.getCarnivalParty().addCP(chr, cp);
    chr.CPUpdate(false, chr.getAvailableCP(), chr.getTotalCP(), 0);
    var iter = eim.getPlayers().iterator();
    while (iter.hasNext()) {
        iter.next().CPUpdate(true, chr.getCarnivalParty().getAvailableCP(), chr.getCarnivalParty().getTotalCP(), chr.getCarnivalParty().getTeam());
    }
}

function monsterValue(eim, mobId) {
    return 0;
}


function end(eim) {
    if (!eim.getProperty("started").equals("true")) {
        disposeAll(eim);
    }
}

function warpOut(eim) {
    if (!eim.getProperty("started").equals("true")) {
	if (eim.getProperty("blue").equals("-1")) {
            disposeAll(eim);
	}
    } else {
	var blueParty = getParty(eim, "blue");
	var redParty = getParty(eim, "red");
    	if (blueParty.isWinner()) {
    	    blueParty.warp(eim.getMapInstance(winnerMap), 0);
    	    redParty.warp(eim.getMapInstance(loserMap), 0);
    	} else {
    	    redParty.warp(eim.getMapInstance(winnerMap), 0);
    	    blueParty.warp(eim.getMapInstance(loserMap), 0);
    	}
    	eim.disposeIfPlayerBelow(100,0);
    }
}

function scheduledTimeout(eim) {
    eim.stopEventTimer();
    if (!eim.getProperty("started").equals("true")) {
	if (eim.getProperty("blue").equals("-1")) {
            disposeAll(eim);
	}
    } else {
        var blueParty = getParty(eim, "blue");
        var redParty = getParty(eim, "red");
        if( blueParty != null && redParty != null ) {
            if (blueParty.getTotalCP() > redParty.getTotalCP()) {
                blueParty.setWinner(true);
            } else if (redParty.getTotalCP() > blueParty.getTotalCP()) {
                redParty.setWinner(true);
            }
            blueParty.displayMatchResult();
            redParty.displayMatchResult();
        }
        eim.schedule("warpOut", 10000);
    }
}

function playerRevive(eim, player) {
   // player.getCarnivalParty().useCP(player,10);
    var iter = eim.getPlayers().iterator();
    while (iter.hasNext()) {
        iter.next().CPUpdate(true, player.getCarnivalParty().getAvailableCP(), player.getCarnivalParty().getTotalCP(), player.getCarnivalParty().getTeam());
    }
	player.addHP(1000);
	player.addMP(1000);
    player.changeMap(eim.getMapInstance(reviveMap), eim.getMapInstance(reviveMap).getPortal(0));
	return true;
}

function playerDisconnected(eim, player) {
    player.setMap(eim.getMapInstance(exitMap));
    eim.unregisterPlayer(player);
    player.getCarnivalParty().removeMember(player);
    eim.broadcastPlayerMsg(5, player.getName() + " 离开了怪物嘉年华");
    disposeAll(eim);
	return 0;
}

function onMapLoad(eim, chr) {
    if (!eim.getProperty("started").equals("true")) {
        disposeAll(eim);
    } else if (chr.getCarnivalParty().getTeam() == 0) {
	var blueParty = getParty(eim, "blue");
        chr.startMonsterCarnival(blueParty.getAvailableCP(), blueParty.getTotalCP());
    } else {
	var redParty = getParty(eim, "red");
        chr.startMonsterCarnival(redParty.getAvailableCP(), redParty.getTotalCP());
    }
}

function cancelSchedule() {
}

function clearPQ(eim) {
}

function allMonstersDead(eim) {
}

function changedMap(eim, chr, mapid) {
}