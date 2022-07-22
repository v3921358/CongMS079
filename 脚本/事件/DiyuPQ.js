function init() {
    em.setProperty("state", "0");
}

function monsterValue(eim, mobId) {
    return 1;
}

function setup() {
    em.setProperty("state", "1");

    var eim = em.newInstance("DiyuPQ");
    var map = eim.setInstanceMap(677000010);
    map.resetFully();
   // map.getPortal("next00").setScriptName("checkWildeye");
    map = eim.setInstanceMap(677000011);
    map.resetFully();
    map = eim.setInstanceMap(677000012);
    map.resetFully();
    eim.startEventTimer(1800000);

    return eim;
}

function playerEntry(eim, player) {
    var map = eim.getMapFactory().getMap(677000010);
    player.changeMap(map, map.getPortal(0));
}

function playerDead(eim, player) {}

function changedMap(eim, player, mapid) {
    switch (mapid) {
        case 677000010: // 1st Stage
        case 677000011: // 2nd Stage
        case 677000012: // 3rd Stage
            return;
    }
    eim.unregisterPlayer(player);

    if (eim.disposeIfPlayerBelow(0, 0)) {
        em.setProperty("state", "0");
    }
}

function playerRevive(eim, player) {}

function playerDisconnected(eim, player) {
    return -2;
}

function leftParty(eim, player) {
    // If only 2 players are left, uncompletable
    if (eim.disposeIfPlayerBelow(3, 677000013)) {
        em.setProperty("started", "false");
    } else {
        playerExit(eim, player);
    }
}

function disbandParty(eim) {
    // Boot whole party and end
    eim.disposeIfPlayerBelow(100, 677000013);

    em.setProperty("state", "0");
}

function playerExit(eim, player) {
    eim.unregisterPlayer(player);

    var exit = eim.getMapFactory().getMap(677000013);
    player.changeMap(exit, exit.getPortal(0));
}

function clearPQ(eim) {
    // KPQ does nothing special with winners
    eim.disposeIfPlayerBelow(100, 677000013);

    em.setProperty("state", "0");
}

function scheduledTimeout(eim) {

    var players = eim.getPlayers();
    var exit = eim.getMapFactory().getMap(677000013);
    for (var i = 0; i < players.size(); i++) {
        var player = players.get(i);
        eim.unregisterPlayer(player);
        player.changeMap(exit, exit.getPortal(0));
    }

}


function allMonstersDead(eim) {}

function cancelSchedule() {}
