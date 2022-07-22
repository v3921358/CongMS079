/*
 ���ʱ�䣺2014��8��9�� 20:15:06
 �ű����ܣ����޹ؿ�
 
 var mob = em.getMonster(9600000);
 mob.setOverrideStats(em.getOverrideMonsterStats(1100000000, 1, 1));//11E
 eim.registerMonster(mob);
 var rand = Math.floor(Math.random() * mapArray.length);
 var mapForMob = eim.getMapInstance(mapArray[rand][0]);
 mapForMob.spawnMonsterOnGroundBelow(mob, new java.awt.Point(mapArray[rand][2], mapArray[rand][3]));
 em.broadcastServerMsg(5120026, "[��Ϯ�Ļƽ�] �ƽ��Ѿ���" + mapArray[rand][1] + "�ٻ��ˡ�", true);
 em.broadcastServerMsg(6, "[��Ϯ�Ļƽ�] �ƽ��Ѿ���" + mapArray[rand][1] + "�ٻ��ˡ�", false);
 em.setProperty("SPAWNMONSTER", "true");
 
 
 em.setProperty("FriendlyTips", "1");
 em.schedule("openMessageBoxInBattle", 1000 * 60 * 1, eim);
 
 */




var EventDataBase;
var Times = 0;
var GiftTimes = 0;
var charid = 0;
var MobList =
        Array(
                9400917, // - ��ͷȮ
                9303095, // - ΰ��İ�������
                9303092, // - ΰ���ϣ��˹
                9303087, // - ΰ���Ʒ����
                9303085, // - ΰ��İࡤ�װ�
                9303083, // - ΰ��������
                9303103, // - ΰ���ëĪ
                9303104, // - ����ëĪսʿ
                9303105, // - ����ëĪħ��ʦ
                9303106, // - ����ëĪ������
                9303107, // - ����ëĪ����
                9303108// - ����ëĪ����
                );

function init() {
    em.setProperty("started", "false");
    em.setProperty("Gift", "false");
	em.setProperty("Times", 0);
}

function monsterValue(eim, mobId) {
    return 1;
}

function setup() {
    var eim = em.newInstance("Limitless");
    var map = eim.setInstanceMap(923020100);
    eim.startEventTimer(1000 * 60 * 10);//10 min
    var players = map.getCharacters().iterator();
    while (players.hasNext()) {
        var player = players.next();
        eim.registerPlayer(player);
    }
    Times = 0;
    GiftTimes = 0;
    map.killAllMonsters(true);
    em.setProperty("started", "true");
    em.setProperty("Gift", "false");

    return eim;
}

function playerEntry(eim, player) {
    var map = eim.getMapInstance(0);
    player.changeMap(map, map.getPortal(0));
    charid = map.getCharacters().get(0).getId();
	var conn = Packages.database.DatabaseConnection.getConnection();
	var pstmt = conn.prepareStatement("SELECT times FROM limitlessEvent where charid = " + charid + "");
    EventDataBase = pstmt.executeQuery();
    while (EventDataBase.next()) {
        Times = EventDataBase.getString("times");
    }
	EventDataBase.close();
	pstmt.close();
	//conn.close();
    eim.broadcastPlayerMsg(1, "��ӭ���� <���޸���>!!\r\n�����ǵ�" + (parseInt(Times) + 1) + "�أ�\r\n ÿͨ��10���ɻ�ô������߽���! ");
    //eim.broadcastPlayerMsg(1, charid);
    SpwnMobForPlayer(eim)
}

function SpwnMobForPlayer(eim) {
    if (GiftTimes != 0) {
        var map = eim.getMapInstance(0);
        var players = map.getCharacters().iterator();
		em.setProperty("Times", Times+1);
        while (players.hasNext()) {
            var player = players.next();
            player.changeMap(map, map.getPortal(0));
            if ((GiftTimes % 10) == 0) {
                em.setProperty("Gift", "true");
                player.openNpc(2060103);
            }
        }
		
        eim.startEventTimer(1000 * 60 * 1);//10 min ����ʱ��
        eim.broadcastPlayerMsg(-1, "[����ս��] �����ǵ�" + (Times + 1) + "�أ� ����1���ӵ�ʱ���������! ");
    }
    var mobid = MobList[Math.floor(Math.random() * MobList.length)];
    var mob = em.getMonster(mobid);
    var modified = em.newMonsterStats();
	if (Times<=50)
    	modified.setOHp(100000 + Times * 5000000);
    else if (Times>50 && Times<=80)
		modified.setOHp(100000 + Times * 15000000);
	else if (Times>80 && Times<=100)
		modified.setOHp(100000 + Times * 150000000);
	else 
		modified.setOHp(100000 + Times * 350000000);
	modified.setOMp(mob.getMobMaxMp());
    mob.setOverrideStats(modified);
    eim.registerMonster(mob);
    var mapForMob = eim.getMapInstance(923020100);
    mapForMob.spawnMonsterOnGroundBelow(mob, new java.awt.Point(490, 152));
}

function playerDead(eim, player) {
    em.setProperty("started", "false");
    eim.disposeIfPlayerBelow(100, 923020000);
}

function playerRevive(eim, player) {
}

function scheduledTimeout(eim) {
    em.setProperty("started", "false");
    eim.disposeIfPlayerBelow(100, 923020000);
}

function changedMap(eim, player, mapid) {
    if (mapid == 923020100) {
        return;
    }
    em.setProperty("started", "false");
    eim.unregisterPlayer(player);
}

function playerDisconnected(eim, player) {
    em.setProperty("started", "false");
    eim.disposeIfPlayerBelow(100, 923020000);
    return 0;
}

function leftParty(eim, player) {
    // If only 2 players are left, uncompletable:
    playerExit(eim, player);
}

function disbandParty(eim) {
    em.setProperty("started", "false");
    eim.disposeIfPlayerBelow(100, 923020000);
}

function playerExit(eim, player) {
    em.setProperty("started", "false");
    eim.unregisterPlayer(player);
    var map = eim.getMapFactory().getMap(923020000);
    player.changeMap(map, map.getPortal(0));
}

function clearPQ(eim) {
    em.setProperty("started", "false");
    eim.disposeIfPlayerBelow(100, 923020000);
}

function allMonstersDead(eim) {
	var conn = Packages.database.DatabaseConnection.getConnection();
    var UpDateData = conn.prepareStatement("update limitlessEvent set times=? where charid = " + charid + "");
    UpDateData.setString(1, parseInt(Times) + 1);
    UpDateData.executeUpdate();//����;
    Times++;
    GiftTimes++;
	em.setProperty("Times", Times);
	UpDateData.close();
	//conn.close();
    eim.broadcastPlayerMsg(-1, "[����ս��] �����˹����ȴ�10�������һ�أ�");
    eim.startEventTimer(1000 * 10);//10 min
    em.schedule("SpwnMobForPlayer", 1000 * 8, eim);//10�����
}

function cancelSchedule() {
    em.setProperty("started", "false");
}