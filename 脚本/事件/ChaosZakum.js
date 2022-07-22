function init() {
    em.setProperty("state", "0");
    em.setProperty("leader", "true");
}

function setup(eim, leaderid) {
    em.setProperty("state", "1");
    em.setProperty("leader", "true");
    var eim = em.newInstance("ChaosZakum");
    eim.setProperty("zakSummoned", "0");
    //eim.createInstanceMap(280030001).resetFully();
    eim.setInstanceMap(280030001).resetFully();
    eim.startEventTimer(4500000); //1 hr 15 min
//    eim.schedule("checkStart", 1200000); // 20 min

    return eim;
}

function playerEntry(eim, player) {
    var NowMapID = eim.getProperty("NowMapID");
    var toMapid = 280030001;
    if (NowMapID != null) {
        toMapid = java.lang.Integer.parseInt(NowMapID);
    }
    var map = eim.getMapFactory().getMap(toMapid);
    player.changeMap(map, map.getPortal(0));
    //����Զ���������Ϣ
    eim.setProperty("isSquadPlayerID_" + player.getId(), "1");
    //var map = eim.getMapInstance(0);
    //player.changeMap(map, map.getPortal(0));
}

function playerRevive(eim, player) {
    return false;
}

function changedMap(eim, player, mapid) {
    switch (mapid) {
        case 280030001:
            //���浱ǰִ�е�ͼID
            eim.setProperty("NowMapID", "" + mapid);
            return;
    }
    if (mapid != 280030001) {
        eim.unregisterPlayer(player);

        if (eim.disposeIfPlayerBelow(0, 0)) {
            em.setProperty("state", "0");
            em.setProperty("leader", "true");
        }
    }
    //if (mapid != 280030001) {
    //     eim.unregisterPlayer(player);

    //    if (eim.disposeIfPlayerBelow(0, 0)) {
    //        em.setProperty("state", "0");
    //        em.setProperty("leader", "true");
    //    }
    // }
}

function playerDisconnected(eim, player) {
    playerExit(eim, player);
    return 0;
    //return 0;
}

function scheduledTimeout(eim) {
    end(eim);
}

function monsterValue(eim, mobId) {
    return 1;
}

function playerExit(eim, player) {
    eim.unregisterPlayer(player);

    if (eim.disposeIfPlayerBelow(0, 0)) {
        em.setProperty("state", "0");
        em.setProperty("leader", "true");
    }
}

function end(eim) {
    eim.disposeIfPlayerBelow(100, 211042301);
    em.setProperty("state", "0");
    em.setProperty("leader", "true");
    em.setProperty("zakSummoned", "0");
}

function clearPQ(eim) {
    end(eim);
}

function allMonstersDead(eim) {
    if (em.getProperty("state").equals("1")) {
        em.setProperty("state", "2");
    } else if (em.getProperty("state").equals("2")) {
        em.setProperty("state", "3");
    }
}

function leftParty(eim, player) {}
function disbandParty(eim) {}
function playerDead(eim, player) {
    eim.setProperty("isSquadPlayerID_" + player.getId(), "0");
}
function cancelSchedule() {}