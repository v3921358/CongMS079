var eventmapid = 220080001;
var returnmap = 910000000;

var monster = new Array(
    4130103, // Rombot
    9300039, // Papa Pixie
    9300119, // Lord Pirate
    9300152, // Angry Franken Lloyd
    9400549, // Headless Horseman
    9300028, // Ergoth
    8180000, // Manon
    8180001, // Griffey
    8220003, // Lev
    9400014, // Black Crow
    9500362, // Papulatus
	8500002 // Papulatus 2nd
);

function init() {
    // After loading, ChannelServer
}

function setup(partyid) {
    var instanceName = "BossQuest" + partyid;

    var eim = em.newInstance(instanceName);
    // If there are more than 1 map for this, you'll need to do mapid + instancename
    var map = eim.createInstanceMapS(eventmapid);
	
    map.toggleDrops();
    map.spawnNpc(9209101, new java.awt.Point(-780, -557));

    eim.setProperty("points", 0);
    eim.setProperty("monster_number", 0);

    beginQuest(eim);
    return eim;
}

function beginQuest(eim) { // Custom function
    if (eim != null) {
        eim.startEventTimer(5000); // After 5 seconds -> scheduledTimeout()
    }
}

function monsterSpawn(eim) { // Custom function
    var monsterid = monster[parseInt(eim.getProperty("monster_number"))];
    var mob = em.getMonster(monsterid);
	var modified = em.newMonsterStats();
	
    switch (monsterid) {
        case 8180000:
        case 8180001: //Manon Griffey
		case 8220003: // Lev
		case 9300028: // Ergoth
		case 9400014: // Black Crow
        case 4130103: // Rombot
        case 9300119: // Lord Pirate
        case 9300152: // Angry Frankenlloyd
        case 9400549: // Headless Horseman
		case 9300039: // Papa Pixie
			modified.setOExp(mob.getMobExp());
            modified.setOHp(mob.getMobMaxHp());
            modified.setOMp(mob.getMobMaxMp());
            mob.setOverrideStats(modified);
            break;
        case 9500362: // Papulatus
			modified.setOExp(mob.getMobExp() * 0.3);
            modified.setOHp(mob.getMobMaxHp() * 2.5);
            modified.setOMp(mob.getMobMaxMp() * 1.5);
			
            mob.setOverrideStats(modified);
            break;
        case 8500002: // Papulatus 2nd
			modified.setOExp(mob.getMobExp() * 3.8);
            modified.setOHp(mob.getMobMaxHp() * 20);
            modified.setOMp(mob.getMobMaxMp() * 15);
			
            mob.setOverrideStats(modified);
            break;
        
    }
    eim.registerMonster(mob);

    var map = eim.getMapInstance(0);
    map.spawnMonsterOnGroundBelow(mob, new java.awt.Point(-400, -386));
}

function playerEntry(eim, player) {
    var map = eim.getMapInstance(0);
    player.changeMap(map, map.getPortal(0));
}

function changedMap(eim, player, mapid) {
    if (mapid != eventmapid) {
        eim.unregisterPlayer(player);

        eim.disposeIfPlayerBelow(0, 0);
    }
}

function scheduledTimeout(eim) {
    var num = parseInt(eim.getProperty("monster_number"));
    if (num < monster.length) {
        monsterSpawn(eim);
        eim.setProperty("monster_number", num + 1);
    } else {
        eim.disposeIfPlayerBelow(100, returnmap);
    }
    // When event timeout..

    // restartEventTimer(long time)
    // stopEventTimer()
    // startEventTimer(long time)
    // isTimerStarted()
}

function allMonstersDead(eim) {
    eim.restartEventTimer(3000);

    var mobnum = parseInt(eim.getProperty("monster_number"));
    var num = mobnum * 1; // Total 1170
    var totalp = parseInt(eim.getProperty("points")) + num;

    eim.setProperty("points", totalp);

    //
	//eim.broadcastPlayerMsg(5, "你的隊伍獲得了 " + num + " 點數! 總共為 " + totalp + ".");

    //eim.saveBossQuest(num);
    if (mobnum < monster.length) {
        eim.broadcastPlayerMsg(6, "请做好准备！下一只的BOSS即将来临。");
    } else {
		eim.saveBossQuest(12);
        eim.broadcastPlayerMsg(5, "恭喜整队挑战普通模式成功。");
    }
    // When invoking unregisterMonster(MapleMonster mob) OR killed
    // Happens only when size = 0
}

function playerDead(eim, player) {
    // Happens when player dies
}

function playerRevive(eim, player) {
    player.addHP(50);
    player.changeMap(eim.getMapInstance(returnmap), eim.getMapInstance(returnmap).getPortal(0));
	eim.broadcastPlayerMsg(5, player.getName() + " 因为死亡离开了BOSSPQ");
    return true;
}

function playerDisconnected(eim, player) {
	player.setMap(eim.getMapInstance(returnmap));
	eim.broadcastPlayerMsg(5, player.getName() + " 离开了BOSSPQ");
}

function monsterValue(eim, mobid) {
    return 0;
    // Invoked when a monster that's registered has been killed
    // return x amount for this player - "Saved Points"
}

function leftParty(eim, player) {
    // Happens when a player left the party
    eim.unregisterPlayer(player);

    var map = em.getMapFactory().getMap(returnmap);
    player.changeMap(map, map.getPortal(0));

    eim.disposeIfPlayerBelow(0, 0);
}

function disbandParty(eim, player) {
    // Boot whole party and end
    eim.disposeIfPlayerBelow(100, returnmap);
}

function clearPQ(eim) {
    // Happens when the function EventInstanceManager.finishPQ() is invoked by NPC/Reactor script
}

function removePlayer(eim, player) {
    eim.dispose();
    // Happens when the funtion NPCConversationalManager.removePlayerFromInstance() is invoked
}

function registerCarnivalParty(eim, carnivalparty) {
    // Happens when carnival PQ is started. - Unused for now.
}

function onMapLoad(eim, player) {
    // Happens when player change map - Unused for now.
}

function cancelSchedule() {}

function monsterKilled(eim) {}