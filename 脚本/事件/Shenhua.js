/*
 ������	�񻰸���
 ���ߣ�	Memory
 */
var mobid;
var mob;
var modified;
var MaxRandom;
var setupTask;
var itemList=Array(
	Array(4033924, 600), //�񻰶�����ͼ
	Array(2432013, 600),  //Ů��֮��
	Array(2432014, 100), //Ů��֮Ѫ��
	Array(1113077, 300),  //����֮��
	Array(5062010, 600),  //�ռ�ħ��
	Array(1032205, 200),  //�񻰶���
	Array(2431354, 100),  //�ǻ���������
	Array(4033356, 200),  //�������
	//Array(3010879, 100), // ��������, 300), // ���й����"С��"��Ƶĵ���������������ӡ�ÿ10���лָ�HP��MP��500��
	Array(1432086, 50), // - ʨ�ĳ�ǹ, 50), // - (������)
	Array(1302152, 50), // - ʨ���䵶, 50), // - (������)
	Array(1522018, 50), // - �������ǹ, 50), // - (������)
	Array(1232014, 50), // - ʨ��ʹ������, 50), // - (������)
	Array(1322096, 50), // - ʨ�����׶�, 50), // - (������)
	Array(1342036, 50), // - ����ǰ�Ӱ��, 50), // - (������)
	Array(1402095, 50), // - ʨ��ս���䵶, 50), // - (������)
	Array(1372084, 50), // - ��β�������, 50), // - (������)
	Array(1382104, 50), // - ��βս������, 50), // - (������)
	Array(1212014, 50), // - ��β�ڼ�����, 50), // - (������)
	Array(1452111, 50), // - ӥ����Ϲ�, 50), // - (������)
	Array(1462099, 50), // - ӥ������, 50), // - (������)
	Array(1242042, 50), // - ��ѻ֮��Ů����־֮��, 50), // - (������)
	Array(1332130, 50), // - ��ѻ֮��̵�, 50), // - (������)
	Array(1362019, 50), // - ��ѻ֮���������, 50), // - (������)
	Array(1482084, 50), // - ��ݾ�ӥצ, 50), // - (������)
	Array(1492085, 50), // - ����������, 50), // - (������)
	Array(1532018, 50), // - ��ݻ�����, 50), // - (������)
	Array(1222014, 50), // - �����꼳ȡ��, 50), // - (������)
	Array(1242014, 50), // - ���Ů����־֮��, 50), // - (������)
	Array(1052314, 50), // - ʨ��ս�����Ӽ�, 50), // - (������)
	Array(1052315, 50), // - ��β��ʦ����, 50), // - (������)
	Array(1052316, 50), // - ӥ���ڱ���, 50), // - (������)
	Array(1052317, 50), // - ��ѻ֮��׷���߿���, 50), // - (������)
	Array(1052318, 50), // - ��ݴ�������, 50), // - (������)
	Array(1082296, 50), // - ��β��ʦ����, 50), // - (������)
	Array(1082297, 50), // - ӥ���ڱ�����, 50), // - (������)
	Array(1082298, 50), // - ��ѻ֮��׷��������, 50), // - (������)
	Array(1082299, 50), // - ��ݴ�������, 50), // - (������)
	Array(1082295, 50), // - ʨ��ս������, 50), // - (������)
	Array(1152110, 50), // - ��β��ʦ����, 50), // - (������)
	Array(1152111, 50), // - ӥ���ڱ�����, 50), // - (������)
	Array(1152112, 50), // - ��ѻ֮�����˻���, 50), // - (������)
	Array(1152113, 50), // - ��ݴ�������, 50), // - (������)
	Array(1152108, 50), // - ʨ��ս������, 50), // - (������)
	Array(1102275, 50), // - ʨ��ս������, 50), // - (������)
	Array(1102276, 50), // - ��β��ʦ����, 50), // - (������)
	Array(1102277, 50), // - ӥ���ڱ�����, 50), // - (������)
	Array(1102278, 50), // - ��ѻ֮����������, 50), // - (������)
	Array(1102279, 50), // - ��ݴ�������, 50), // - (������)
	Array(1003172, 50), // - ʨ��ս��ͷ��, 50), // - (������)
	Array(1003173, 50), // - ��β��ʦñ��, 50), // - (������)
	Array(1003174, 50), // - ӥ���ڱ���ñ, 50), // - (������)
	Array(1003175, 50), // - ��ѻ֮��׷����ñ, 50), // - (������)
	Array(1003176, 50), // - ��ݴ���ñ, 50), // - (������)
	Array(1072485, 50), // - ʨ��ս��Ь, 50), // - (������)
	Array(1072486, 50), // - ��β��ʦЬ, 50), // - (������)
	Array(1072487, 50), // - ӥ���ڱ�Ь, 50), // - (������)
	Array(1072488, 50), // - ��ѻ֮��׷����Ь, 50), // - (������)
	Array(1072489, 50), // - ��ݴ���Ь, 50), // - (������)
	Array(1003797, 10), // - �߹�սʿͷ��, 50), // - (������)
	Array(1003798, 10), // - �߹�����ά��ñ, 50), // - (������)
	Array(1003799, 10), // - �߹���������ñ, 50), // - (������)
	Array(1003800, 10), // - �߹�̿���ñ, 50), // - (������)
	Array(1003801, 10), // - �߹�������ñ, 50), // - (������)
	Array(1042254, 10), // - ӥ��սʿ����, 50), // - (������)
	Array(1042255, 10), // - ӥ�۵�ά�泤��, 50), // - (������)
	Array(1042256, 10), // - ӥ����������, 50), // - (������)
	Array(1042257, 10), // - ӥ�۴̿ͳ���, 50), // - (������)
	Array(1042258, 10), // - ӥ������������, 50), // - (������)
	Array(1062165, 10), // - ħ��ʦսʿ�̿�, 50), // - (������)
	Array(1062166, 10), // - ħ��ʦ��ά��̿�, 50), // - (������)
	Array(1062167, 10), // - ħ��ʦ�����̿�, 50), // - (������)
	Array(1062168, 10), // - ħ��ʦ�̿Ͷ̿�, 50), // - (������)
	Array(1062169, 10), // - ħ��ʦ�����߶̿�, 50), // - (������)
	//130װ��
	Array(1232040, 290), // ����������˹�ɺ�Ƶ���
	Array(1302228, 290), // ����������˹ȭ��
	Array(1312117, 290), // ����������˹ͷ��(������ʾ�����⣩
	Array(1322163, 290), // ����������˹��
	Array(1402152, 290), // ����������˹˫�ֽ�
	Array(1412105, 290), // ����������˹ȭ�ף�������ʾ������)
	Array(1422108, 290), // ����������˹���
	Array(1432139, 290), // ����������˹֮ì
	Array(1442183, 290), // ����������˹֮ì
	Array(1332194, 290), // �����ն���˹����
	Array(1362068, 290), // �����ն���˹����         
	Array(1472180, 290), // �����ն���˹����
	Array(1212043, 290), // ���������˹˫ͷ��
	Array(1372140, 290), // ���������˹����
	Array(1382169, 290), // ���������˹�ױ�
	Array(1252030, 290), // ���������˹è��ħ����
	Array(1452171, 290), // ��������˹����
	Array(1462160, 290), // ��������˹��
	Array(1522072, 290), // ��������˹˫��ǹ
	Array(1222043, 290), // ����ά����Ѫ��
	Array(1242046, 290), // ����ά����ʨЫ��
	Array(1482141, 290), // ����ά����ָ������
	Array(1492153, 290), // ����ά����֮ӥ
	Array(1532075, 290), // ����ά��������
	Array(1003589, 290), // ����������˹ͷ��
	Array(1003592, 290), // �����ն���˹ͷ��
	Array(1003590, 290), // ���������˹ͷ��
	Array(1003591, 290), // ��������˹ͷ��
	Array(1003593, 290), // ����ά����ͷ��
	Array(1052498, 290), // ����������˹���Ӽ�
	Array(1052501, 290), // �����ն���˹���Ӽ�
	Array(1052499, 290), // ���������˹���Ӽ�
	Array(1052500, 290), // ��������˹���Ӽ�
	Array(1052502, 290), // ����ά�������Ӽ�
	Array(1102445, 290), // ����������˹����
	Array(1102448, 290), // �����ն���˹����
	Array(1102446, 290), // ���������˹����
	Array(1102447, 290), // ��������˹����
	Array(1102449, 290), // ����ά��������
	Array(1082466, 290), // ����������˹����
	Array(1082469, 290), // �����ն���˹����
	Array(1082467, 290), // ���������˹����
	Array(1082468, 290), // ��������˹����
	Array(1082470, 290), // ����ά��������
	Array(1072703, 290), // ����������˹ѥ
	Array(1072706, 290), // �����ն���˹ѥ
	Array(1072704, 290), // ���������˹ѥ
	Array(1072705, 290), // ��������˹ѥ
	Array(1072707, 290) // ����ά����ѥ
);
function init() {
    em.setProperty("state", "0");
}


function setup(level, leaderid) {
    var eim = em.newInstance("Shenhua");
	eim.setInstanceMap(262030100).resetPQ(level);
    eim.setInstanceMap(262030200).resetPQ(level);
    var map = eim.setInstanceMap(262030300);
    map.resetFully();
    map.killAllMonsters(true);
    map.respawn(false);
	mobid = 9300600;
    mob = em.getMonster(mobid);
	modified = em.newMonsterStats();
	modified.setOHp(mob.getMobMaxHp()*20);
	modified.setOMp(mob.getMobMaxMp()*999);
	mob.setOverrideStats(modified);
	eim.registerMonster(mob);
	var mapForMob = eim.getMapInstance(262030300);
	mapForMob.spawnMonsterOnGroundBelow(mob, new java.awt.Point(0, -85));
	var map2 = eim.getMapInstance(262030200);
	mobid = 8870005;
    mob = em.getMonster(mobid);
	modified = em.newMonsterStats();
	modified.setOHp(mob.getMobMaxHp()*100);
	modified.setOMp(mob.getMobMaxMp()*999);
	mob.setOverrideStats(modified);
	map2.spawnMonsterOnGroundBelow(mob, new java.awt.Point(1132, 196));
    eim.startEventTimer(1000 * 60 * 60);
    em.setProperty("state", "1");
    return eim;
}


function playerEntry(eim, player) {
    var map = eim.getMapInstance(0);
    player.dropMessage(6, "[�񻰸���] ���뵽����ս��ͼ����С�����¡�");
    player.changeMap(map, map.getPortal(0));
}


function scheduledTimeout(eim) {
    eim.broadcastPlayerMsg(1, "[�񻰸���] ���ź����ѳ����޶���սʱ�䣬������սʧ�ܣ������٣��ڴ�����ǿ�����ǰ����ս~");
    eim.disposeIfPlayerBelow(100, 262030000);
}

function cancelSchedule() {
}


function playerDead(eim, player) {
}




function playerRevive(eim, player) {
    var map = em.getMapFactory().getMap(262030000);
    if (map != null) {
        player.changeMap(map, map.getPortal(0));
    }
    eim.disposeIfPlayerBelow(100, 262030000);
    return false;
}


function changedMap(eim, player, mapid) {
    switch (mapid) {
        case 262030100:
		case 262030200:
		case 262030300:
            return;
    }
	
    player.dropMessage(6, "[�񻰸���] ���˳���ս��");
    eim.unregisterPlayer(player);
    if (eim.getPlayerCount() <= 0) {
        eim.disposeIfPlayerBelow(100, 262030000);
    }
}


function playerExit(eim, player) {
    eim.disposeIfPlayerBelow(100, 262030000);
}



function playerDisconnected(eim, player) {
	eim.unregisterPlayer(player);
    if (eim.getPlayerCount() <= 1) {
        eim.disposeIfPlayerBelow(100, 262030000);
		if (setupTask!=null)
			setupTask.cancel(true);
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
    if (em.getProperty("state").equals("1")) {
		eim.setProperty("giftcount","0");
		roll(eim);
		eim.startEventTimer(1000 * 60 * 5);
		eim.broadcastPlayerMsg(6, "[�񻰸���] 10��������䣬����ʱ�����������������������Ҫ��10��������������ѡ�񣬷��򽫻ᱻǿ�����ߡ�");
    	//em.broadcastServerMsg(5120059, "[�񻰸���] ϣ���ѱ����ܣ�10��󽫿������䡣" ,true);
		var map = eim.getMapInstance(262030300);
		map.startMapEffect("[�񻰸���] ϣ���ѱ����ܣ�10��󽫿������䡣", 5120059);
	}
}

function roll(eim) {
	MaxRandom = 0;
	var count = eim.getProperty("giftcount");
	var rewardPlayer = null;
	//�ڶ��ο�ʼ,ͳ����һ��ROLL����ҽ���������Ž�����
	if ((count*1)>=1) {
		for (var i = 0; i < eim.getPlayerCount(); i++) {
			var charName = eim.getPlayers().get(i).getName();
			var charId = eim.getPlayers().get(i).getId();
			//����ROLL����Ϣ
			for (var j = 0; j < eim.getPlayerCount(); j++) {
				var notice =  "[�񻰸���] ��� "+charName+" ������ "+eim.getProperty("charid_"+charId)+"��";
				if ((eim.getProperty("charid_"+charId)*1)==0) {
					notice =  "[�񻰸���] ��� "+charName+" ����������";
				}
				eim.getPlayers().get(j).dropMessage(6,notice);
			}
			//�����������ֵ
			if ((eim.getProperty("charid_"+charId)*1)>MaxRandom) {
				MaxRandom = eim.getProperty("charid_"+charId);
				//�û��������
				eim.setProperty("rewardplayer", charName);
				//�û����ID
				eim.setProperty("rewardplayerid", charId);
			} 
   		}
		for (var j = 0; j < eim.getPlayerCount(); j++) {
			//����NPC ���Ž���
			eim.getPlayers().get(j).openNpc(1052008, 1111);
		}
	}
	for (var j = 0; j < eim.getPlayerCount(); j++) {
		//�����������ROLL�����Ϊ��
		eim.setProperty("charid_"+eim.getPlayers().get(j).getId(),"0");
	}
	//����+1
	eim.setProperty("giftcount", (count*1+1));
	//���¶������
	count = eim.getProperty("giftcount");
	count = (count*1);
	//�˳�ս��
	if ((count*1)>10) {
		EndThisBattle(eim);
		return;
	}
	//��������
	var chance = Math.floor(Math.random()*600);
	//������Ʒ�б�
	var finalItemList = Array();
	for(var m=0; m<itemList.length; m++) {
		if (itemList[m][1] >= chance) {
			finalItemList.push(itemList[m][0]);
		}
	}
	var currentItem = finalItemList[Math.floor(Math.random()*finalItemList.length)];
	switch(count) {
		case 8:
		case 9:
		case 10:
			currentItem = 2432013;
		break;
	}
	eim.setProperty("rewarditem", currentItem);
	//�ӳ�10���ROLL��NPC
	setupTask = em.schedule("openRollNpc", 1000 * 10 * 1, eim);
}

function openRollNpc(eim) {
	for (var i = 0; i < eim.getPlayerCount(); i++) {
		eim.getPlayers().get(i).openNpc(1052008);
    }
	//10������ROLL��
	setupTask = em.schedule("roll", 1000 * 10 * 1, eim);
}

function EndThisBattle(eim) {
	for (var i = 0; i < eim.getPlayerCount(); i++) {
        eim.getPlayers().get(i).dropMessage(1, "[�񻰸���] ��ս�ɹ���");
    }
	//em.broadcastYellowMsg("[�񻰸���] ��ս����");
    em.setProperty("state", "done");
    eim.disposeIfPlayerBelow(100, 262030000);
	if (setupTask!=null)
		setupTask.cancel(true);
	eim.dispose();
}

function monsterDamaged(eim, player, mobid, damage) {
}

function cancelSchedule() {
	if (setupTask!=null)
		setupTask.cancel(true);
}

function leftParty(eim, player) {
    eim.disposeIfPlayerBelow(100, 262030000);
}


function disbandParty(eim) {
    eim.disposeIfPlayerBelow(100, 262030000);
}


function onMapLoad(eim, player) {
}