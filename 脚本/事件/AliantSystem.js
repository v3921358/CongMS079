/*
 ���ã�	���ﰲ�ؾ����� - �ȴ�����
 ���ߣ�	��о
 ����	���˰�
 */
var MobList =
        Array(
                2600422, // - ǿ������ϵͳ
                2600421, // - ����ϵͳ
                2600423, // - AF�����ܻ�����
                2600424, // - �����ϵ�DF�����ܻ�����
                2600418, // - ��ʯ������
                9500618, // - ��ɫ�ȶ�����
                9500617, // - ��ɫ�ȶ�����
                9500614 // - ��ɫ�ȶ�����
                );
var MobBossList =
        Array(
                7220001, // - ��β��
                6300005, // - ��ʬĢ����
                2220000, // - ����ţ��
                3220000, // - ������
                9303085, // - ΰ��İ�?�װ�
                6130101, // - Ģ����
                8220007, // - ��Ģ����
                9600009 // - �������
                );

var mobid;
var mob;
var modified;


function init() {
    em.setProperty("FriendlyTips", "0");
    em.setProperty("playing", "false");
}


function setup() {
    var eim = em.newInstance("AliantSystem");
    var map = eim.setInstanceMap(980010101);
    //map.resetFully();
    //map.killAllMonsters(true);
    map.respawn(false);
    eim.startEventTimer(1000 * 60 * 6);//����Ϊ5����
    em.setProperty("FriendlyTips", "0");
    em.setProperty("playing", "true");
    openMessageBoxInBattle(eim);

    //��ʼ��ʱ�����ٻ�5ֻ�߼���
    for (var i = 0; i < 15; i++) {
        mobid = MobList[Math.floor(Math.random() * MobList.length)];
        mob = em.getMonster(mobid);
        modified = em.newMonsterStats();
        modified.setOHp(500000);
        modified.setOMp(mob.getMobMaxMp());
        mob.setOverrideStats(modified);
        eim.registerMonster(mob);
        var mapForMob = eim.getMapInstance(980010101);
        mapForMob.spawnMonsterOnGroundBelow(mob, new java.awt.Point(-328, -85));
    }
    //em.schedule("EndThisBattle", 13250000, eim);// 4m50s
    return eim;
}


function playerEntry(eim, player) {
    var map = eim.getMapInstance(0);
    player.dropMessage(6, "[���ﰲ�ؾ�����] ���뵽����ս��ͼ��");
    player.changeMap(map, map.getPortal(0));
    player.openNpc(2101014)
}


function scheduledTimeout(eim) {
    finish();
    eim.broadcastPlayerMsg(1, "[���ﰲ�ؾ�����] ���ź����ѳ����޶���սʱ�䣬������սʧ�ܣ������٣��ڴ�����ǿ�����ǰ����ս~");
    eim.disposeIfPlayerBelow(100, 910000000);
}

function cancelSchedule() {
}


function playerDead(eim, player) {
}




function playerRevive(eim, player) {
    var map = em.getMapFactory().getMap(910000000);
    if (map != null) {
        player.changeMap(map, map.getPortal(0));
    }
    eim.disposeIfPlayerBelow(100, 910000000);
    return false;
}


function changedMap(eim, player, mapid) {
    switch (mapid) {
        case 980010100:
        case 980010101:
            return;
    }
    player.dropMessage(6, "[���ﰲ�ؾ�����] ���˳���ս��");
    eim.dispose();
    if (eim.getPlayerCount() <= 0) {
        eim.disposeIfPlayerBelow(100, 910000000);
    }
}


function playerExit(eim, player) {
    eim.disposeIfPlayerBelow(100, 910000000);
}



function playerDisconnected(eim, player) {
    if (eim.getPlayerCount() <= 1) {
        eim.disposeIfPlayerBelow(100, 910000000);
        eim.dispose();
    }
    return 0;
}


function monsterValue(eim, mobid) {
    return 1;
}


function monsterKilled(eim, player, cp) {
}


function allMonstersDead(eim) {
    for (var i = 0; i < 5; i++) {
        mobid = MobList[Math.floor(Math.random() * MobList.length)];
        mob = em.getMonster(mobid);
        modified = em.newMonsterStats();
        modified.setOHp(500000);
        modified.setOMp(mob.getMobMaxMp());
        mob.setOverrideStats(modified);
        eim.registerMonster(mob);
        var mapForMob = eim.getMapInstance(980010101);
        mapForMob.spawnMonsterOnGroundBelow(mob, new java.awt.Point(-328, -85));
    }
    for (var i = 0; i < 5; i++) {
        mobid = MobList[Math.floor(Math.random() * MobList.length)];
        mob = em.getMonster(mobid);
        modified = em.newMonsterStats();
        modified.setOHp(500000);
        modified.setOMp(mob.getMobMaxMp());
        mob.setOverrideStats(modified);
        eim.registerMonster(mob);
        var mapForMob = eim.getMapInstance(980010101);
        mapForMob.spawnMonsterOnGroundBelow(mob, new java.awt.Point(-691, 95));
    }
    for (var i = 0; i < 5; i++) {
        mobid = MobList[Math.floor(Math.random() * MobList.length)];
        mob = em.getMonster(mobid);
        modified = em.newMonsterStats();
        modified.setOHp(500000);
        modified.setOMp(mob.getMobMaxMp());
        mob.setOverrideStats(modified);
        eim.registerMonster(mob);
        var mapForMob = eim.getMapInstance(980010101);
        mapForMob.spawnMonsterOnGroundBelow(mob, new java.awt.Point(-832, 275));
    }
    mobid = MobBossList[Math.floor(Math.random() * MobList.length)];
    mob = em.getMonster(mobid);
    modified = em.newMonsterStats();
    modified.setOHp(5000000);
    modified.setOMp(mob.getMobMaxMp());
    mob.setOverrideStats(modified);
    eim.registerMonster(mob);
    var mapForMob = eim.getMapInstance(980010101);
    mapForMob.spawnMonsterOnGroundBelow(mob, new java.awt.Point(-311, 275));
}


function monsterDamaged(eim, player, mobid, damage) {
}


function leftParty(eim, player) {
    eim.disposeIfPlayerBelow(100, 910000000);
}


function disbandParty(eim) {
    eim.disposeIfPlayerBelow(100, 910000000);
}


function onMapLoad(eim, player) {
}

function finish() {
    em.setProperty("playing", "false");
}

function openMessageBoxInBattle(eim) {
    var map = eim.setInstanceMap(980010101)
    if (em.getProperty("FriendlyTips") == "0") {
        map.startMapEffect("[���ﰲ�ؾ�����] ��������ս���Ѿ���ʼ��! ��Ͽ���������!", 5120026);
        for (var i = 0; i < eim.getPlayerCount(); i++) {
            eim.getPlayers().get(i).dropMessage(6, "[���ﰲ�ؾ�����] ��������ս���Ѿ���ʼ��! ��Ͽ���������!");
        }
        em.setProperty("FriendlyTips", "1");
        em.schedule("openMessageBoxInBattle", 1000 * 60 * 1, eim);
    } else if (em.getProperty("FriendlyTips") == "1") {
        map.startMapEffect("[���ﰲ�ؾ�����] �ɵĲ�������Խ��Ĺ���õ��Ľ���Ҳ��Խ���", 5120026);
        for (var i = 0; i < eim.getPlayerCount(); i++) {
            eim.getPlayers().get(i).dropMessage(6, "[���ﰲ�ؾ�����]  �ɵĲ�������Խ��Ĺ���õ��Ľ���Ҳ��Խ���");
        }
        em.setProperty("FriendlyTips", "2");
        em.schedule("openMessageBoxInBattle", 1000 * 60 * 1, eim);
    } else if (em.getProperty("FriendlyTips") == "2") {
        map.startMapEffect("[���ﰲ�ؾ�����] �����Ŭ��һ��Ļ�����������ʲô������û�С�", 5120026);
        for (var i = 0; i < eim.getPlayerCount(); i++) {
            eim.getPlayers().get(i).dropMessage(6, "[���ﰲ�ؾ�����]  �����Ŭ��һ��Ļ�����������ʲô������û�С�");
        }
        em.setProperty("FriendlyTips", "3");
        em.schedule("openMessageBoxInBattle", 1000 * 60 * 1, eim);
    } else if (em.getProperty("FriendlyTips") == "3") {
        map.startMapEffect("[���ﰲ�ؾ�����] ��;�˳�ս�۵Ļ����Ͳ�����ȡ���κεĽ�����", 5120026);
        for (var i = 0; i < eim.getPlayerCount(); i++) {
            eim.getPlayers().get(i).dropMessage(6, "[���ﰲ�ؾ�����] ��;�˳�ս�۵Ļ����Ͳ�����ȡ���κεĽ�����");
        }
        em.setProperty("FriendlyTips", "4");
        em.schedule("openMessageBoxInBattle", 1000 * 60 * 1, eim);
        /*} else if (em.getProperty("FriendlyTips") == "4") {
         map.startMapEffect("[���ﰲ�ؾ�����] ս�ۿ����Ҫ�����ˣ���Ŭ���ӰѾ�����", 5120026);
         for (var i = 0; i < eim.getPlayerCount(); i++) {
         eim.getPlayers().get(i).dropMessage(6, "[���ﰲ�ؾ�����] ս�ۿ����Ҫ�����ˣ���Ŭ���ӰѾ�����");
         }
         em.setProperty("FriendlyTips", "5");
         em.schedule("openMessageBoxInBattle", 1000 * 60 * 1, eim);
         } else if (em.getProperty("FriendlyTips") == "5") {
         map.startMapEffect("[���ﰲ�ؾ�����] ս�۽����󣬽����Զ�����ս�۽����", 5120026);
         for (var i = 0; i < eim.getPlayerCount(); i++) {
         eim.getPlayers().get(i).dropMessage(6, "[���ﰲ�ؾ�����] ս�۽����󣬽����Զ�����ս�۽����");
         }
         em.setProperty("FriendlyTips", "6");
         em.schedule("openMessageBoxInBattle", 1000 * 60 * 1, eim);
         } else if (em.getProperty("FriendlyTips") == "6") {
         map.startMapEffect("[���ﰲ�ؾ�����] ����4���ӣ�����־���ʤ������", 5120026);
         for (var i = 0; i < eim.getPlayerCount(); i++) {
         eim.getPlayers().get(i).dropMessage(6, "[���ﰲ�ؾ�����] ����4���ӣ�����־���ʤ������");
         }
         em.setProperty("FriendlyTips", "7");
         em.schedule("openMessageBoxInBattle", 1000 * 60 * 1, eim);
         } else if (em.getProperty("FriendlyTips") == "7") {
         map.startMapEffect("[���ﰲ�ؾ�����] ����3���ӣ�ʤ����������ģ�", 5120026);
         for (var i = 0; i < eim.getPlayerCount(); i++) {
         eim.getPlayers().get(i).dropMessage(6, "[���ﰲ�ؾ�����] ����3���ӣ�ʤ����������ģ�");
         }
         em.setProperty("FriendlyTips", "8");
         em.schedule("openMessageBoxInBattle", 1000 * 60 * 1, eim);
         } else if (em.getProperty("FriendlyTips") == "8") {
         map.startMapEffect("[���ﰲ�ؾ�����] ����2���ӣ�", 5120026);
         for (var i = 0; i < eim.getPlayerCount(); i++) {
         eim.getPlayers().get(i).dropMessage(6, "[���ﰲ�ؾ�����] ����2���ӣ�");
         }
         em.setProperty("FriendlyTips", "9");
         em.schedule("openMessageBoxInBattle", 1000 * 60 * 1, eim);*/
    } else if (em.getProperty("FriendlyTips") == "4") {
        map.startMapEffect("[���ﰲ�ؾ�����] ����1���Ӿͽ���ս���ˣ�����ץ��ʱ�䡣", 5120026);
        for (var i = 0; i < eim.getPlayerCount(); i++) {
            eim.getPlayers().get(i).dropMessage(6, "[���ﰲ�ؾ�����] ����1���Ӿͽ���ս���ˣ�����ץ��ʱ�䡣");
        }
        em.setProperty("FriendlyTips", "5");
        //em.schedule("openMessageBoxInBattle", 1000 * 55 * 1, eim);
        em.schedule("openMessageBoxInBattle", 1000 * 55 * 1, eim);
    } else if (em.getProperty("FriendlyTips") == "5") {
        EndThisBattle(eim)
    }
}

function EndThisBattle(eim) {
    em.setProperty("FriendlyTips", "done");
    em.setProperty("PlayerCount", "" + eim.getPlayerCount() + "");
    var map = eim.setInstanceMap(980010100)
    for (var i = 0; i < eim.getPlayerCount(); i++) {
        eim.getPlayers().get(i).changeMap(map, map.getPortal(0));
        map.startMapEffect("[���ﰲ�ؾ�����] ���ڹ��������1���Ӻ��Զ��뿪�˵�ͼ��", 5120026);
        eim.getPlayers().get(i).dropMessage(6, "[���ﰲ�ؾ�����] ���ڹ��������1���Ӻ��Զ��뿪�˵�ͼ��");
        eim.getPlayers().get(i).openNpc(2101017);
    }
    eim.startEventTimer(1000 * 60 * 1);
}