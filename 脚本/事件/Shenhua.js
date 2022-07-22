/*
 副本：	神话副本
 作者：	Memory
 */
var mobid;
var mob;
var modified;
var MaxRandom;
var setupTask;
var itemList=Array(
	Array(4033924, 600), //神话耳环蓝图
	Array(2432013, 600),  //女神之泪
	Array(2432014, 100), //女神之血滴
	Array(1113077, 300),  //绝对之戒
	Array(5062010, 600),  //终极魔方
	Array(1032205, 200),  //神话耳环
	Array(2431354, 100),  //星火幸运箱子
	Array(4033356, 200),  //正义火种
	//Array(3010879, 100), // 繁星椅子, 300), // 由中国玩家"小术"设计的的夏日天空星星椅子。每10秒中恢复HP，MP各500。
	Array(1432086, 50), // - 狮心长枪, 50), // - (无描述)
	Array(1302152, 50), // - 狮心弯刀, 50), // - (无描述)
	Array(1522018, 50), // - 龙翼巨弩枪, 50), // - (无描述)
	Array(1232014, 50), // - 狮心痛苦命运, 50), // - (无描述)
	Array(1322096, 50), // - 狮心震雷钉, 50), // - (无描述)
	Array(1342036, 50), // - 精灵角暗影刀, 50), // - (无描述)
	Array(1402095, 50), // - 狮心战斗弯刀, 50), // - (无描述)
	Array(1372084, 50), // - 龙尾精灵短杖, 50), // - (无描述)
	Array(1382104, 50), // - 龙尾战斗长杖, 50), // - (无描述)
	Array(1212014, 50), // - 龙尾黑甲凶灵, 50), // - (无描述)
	Array(1452111, 50), // - 鹰翼组合弓, 50), // - (无描述)
	Array(1462099, 50), // - 鹰翼重弩, 50), // - (无描述)
	Array(1242042, 50), // - 渡鸦之魂女王意志之剑, 50), // - (无描述)
	Array(1332130, 50), // - 渡鸦之魂短刀, 50), // - (无描述)
	Array(1362019, 50), // - 渡鸦之魂真红手杖, 50), // - (无描述)
	Array(1482084, 50), // - 鲨齿巨鹰爪, 50), // - (无描述)
	Array(1492085, 50), // - 鲨齿锐利手铳, 50), // - (无描述)
	Array(1532018, 50), // - 鲨齿火焰炮, 50), // - (无描述)
	Array(1222014, 50), // - 鲨齿灵魂汲取者, 50), // - (无描述)
	Array(1242014, 50), // - 鲨齿女王意志之剑, 50), // - (无描述)
	Array(1052314, 50), // - 狮心战斗锁子甲, 50), // - (无描述)
	Array(1052315, 50), // - 龙尾法师长袍, 50), // - (无描述)
	Array(1052316, 50), // - 鹰翼哨兵服, 50), // - (无描述)
	Array(1052317, 50), // - 渡鸦之魂追踪者盔甲, 50), // - (无描述)
	Array(1052318, 50), // - 鲨齿船长外套, 50), // - (无描述)
	Array(1082296, 50), // - 龙尾法师手套, 50), // - (无描述)
	Array(1082297, 50), // - 鹰翼哨兵手套, 50), // - (无描述)
	Array(1082298, 50), // - 渡鸦之魂追踪者手套, 50), // - (无描述)
	Array(1082299, 50), // - 鲨齿船长手套, 50), // - (无描述)
	Array(1082295, 50), // - 狮心战斗护腕, 50), // - (无描述)
	Array(1152110, 50), // - 龙尾法师护肩, 50), // - (无描述)
	Array(1152111, 50), // - 鹰翼哨兵护肩, 50), // - (无描述)
	Array(1152112, 50), // - 渡鸦之魂猎人护肩, 50), // - (无描述)
	Array(1152113, 50), // - 鲨齿船长护肩, 50), // - (无描述)
	Array(1152108, 50), // - 狮心战斗护肩, 50), // - (无描述)
	Array(1102275, 50), // - 狮心战斗披风, 50), // - (无描述)
	Array(1102276, 50), // - 龙尾法师披风, 50), // - (无描述)
	Array(1102277, 50), // - 鹰翼哨兵披风, 50), // - (无描述)
	Array(1102278, 50), // - 渡鸦之魂猎人披风, 50), // - (无描述)
	Array(1102279, 50), // - 鲨齿船长披风, 50), // - (无描述)
	Array(1003172, 50), // - 狮心战斗头盔, 50), // - (无描述)
	Array(1003173, 50), // - 龙尾法师帽子, 50), // - (无描述)
	Array(1003174, 50), // - 鹰翼哨兵便帽, 50), // - (无描述)
	Array(1003175, 50), // - 渡鸦之魂追踪者帽, 50), // - (无描述)
	Array(1003176, 50), // - 鲨齿船长帽, 50), // - (无描述)
	Array(1072485, 50), // - 狮心战斗鞋, 50), // - (无描述)
	Array(1072486, 50), // - 龙尾法师鞋, 50), // - (无描述)
	Array(1072487, 50), // - 鹰翼哨兵鞋, 50), // - (无描述)
	Array(1072488, 50), // - 渡鸦之魂追踪者鞋, 50), // - (无描述)
	Array(1072489, 50), // - 鲨齿船长鞋, 50), // - (无描述)
	Array(1003797, 10), // - 高贵战士头盔, 50), // - (无描述)
	Array(1003798, 10), // - 高贵流丹维奇帽, 50), // - (无描述)
	Array(1003799, 10), // - 高贵游侠贝雷帽, 50), // - (无描述)
	Array(1003800, 10), // - 高贵刺客软帽, 50), // - (无描述)
	Array(1003801, 10), // - 高贵流浪者帽, 50), // - (无描述)
	Array(1042254, 10), // - 鹰眼战士盔甲, 50), // - (无描述)
	Array(1042255, 10), // - 鹰眼丹维奇长袍, 50), // - (无描述)
	Array(1042256, 10), // - 鹰眼游侠斗篷, 50), // - (无描述)
	Array(1042257, 10), // - 鹰眼刺客衬衣, 50), // - (无描述)
	Array(1042258, 10), // - 鹰眼流浪者外衣, 50), // - (无描述)
	Array(1062165, 10), // - 魔术师战士短裤, 50), // - (无描述)
	Array(1062166, 10), // - 魔术师丹维奇短裤, 50), // - (无描述)
	Array(1062167, 10), // - 魔术师游侠短裤, 50), // - (无描述)
	Array(1062168, 10), // - 魔术师刺客短裤, 50), // - (无描述)
	Array(1062169, 10), // - 魔术师流浪者短裤, 50), // - (无描述)
	//130装备
	Array(1232040, 290), // 豪华阿加雷斯猩红黄道剑
	Array(1302228, 290), // 豪华阿加雷斯拳刃
	Array(1312117, 290), // 豪华阿加雷斯头盔(名字显示有问题）
	Array(1322163, 290), // 豪华阿加雷斯锤
	Array(1402152, 290), // 豪华阿加雷斯双手剑
	Array(1412105, 290), // 豪华阿加雷斯拳套（名字显示有问题)
	Array(1422108, 290), // 豪华阿加雷斯大槌
	Array(1432139, 290), // 豪华阿加雷斯之矛
	Array(1442183, 290), // 豪华阿加雷斯之矛
	Array(1332194, 290), // 豪华赫尔巴斯猎手
	Array(1362068, 290), // 豪华赫尔巴斯手杖         
	Array(1472180, 290), // 豪华赫尔巴斯手套
	Array(1212043, 290), // 豪华艾里格斯双头杖
	Array(1372140, 290), // 豪华艾里格斯短杖
	Array(1382169, 290), // 豪华艾里格斯笞鞭
	Array(1252030, 290), // 豪华艾里格斯猫梳魔法棒
	Array(1452171, 290), // 豪华伊布斯长弓
	Array(1462160, 290), // 豪华伊布斯弩
	Array(1522072, 290), // 豪华伊布斯双弩枪
	Array(1222043, 290), // 豪华维帕尔血月
	Array(1242046, 290), // 豪华维帕尔狮蝎剑
	Array(1482141, 290), // 豪华维帕尔指节手套
	Array(1492153, 290), // 豪华维帕尔之鹰
	Array(1532075, 290), // 豪华维帕尔火炮
	Array(1003589, 290), // 豪华阿加雷斯头箍
	Array(1003592, 290), // 豪华赫尔巴斯头箍
	Array(1003590, 290), // 豪华艾里格斯头箍
	Array(1003591, 290), // 豪华伊布斯头箍
	Array(1003593, 290), // 豪华维帕尔头箍
	Array(1052498, 290), // 豪华阿加雷斯锁子甲
	Array(1052501, 290), // 豪华赫尔巴斯锁子甲
	Array(1052499, 290), // 豪华艾里格斯锁子甲
	Array(1052500, 290), // 豪华伊布斯锁子甲
	Array(1052502, 290), // 豪华维帕尔锁子甲
	Array(1102445, 290), // 豪华阿加雷斯披风
	Array(1102448, 290), // 豪华赫尔巴斯披风
	Array(1102446, 290), // 豪华艾里格斯披风
	Array(1102447, 290), // 豪华伊布斯披风
	Array(1102449, 290), // 豪华维帕尔披风
	Array(1082466, 290), // 豪华阿加雷斯手套
	Array(1082469, 290), // 豪华赫尔巴斯手套
	Array(1082467, 290), // 豪华艾里格斯手套
	Array(1082468, 290), // 豪华伊布斯手套
	Array(1082470, 290), // 豪华维帕尔手套
	Array(1072703, 290), // 豪华阿加雷斯靴
	Array(1072706, 290), // 豪华赫尔巴斯靴
	Array(1072704, 290), // 豪华艾里格斯靴
	Array(1072705, 290), // 豪华伊布斯靴
	Array(1072707, 290) // 豪华维帕尔靴
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
    player.dropMessage(6, "[神话副本] 进入到了挑战地图，请小心行事。");
    player.changeMap(map, map.getPortal(0));
}


function scheduledTimeout(eim) {
    eim.broadcastPlayerMsg(1, "[神话副本] 真遗憾！已超过限定挑战时间，本次挑战失败！别气馁，期待更加强大的您前来挑战~");
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
	
    player.dropMessage(6, "[神话副本] 已退出挑战。");
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
		eim.broadcastPlayerMsg(6, "[神话副本] 10秒后开启宝箱，掷点时请勿进行其他操作，并且需要在10秒钟内做出需求选择，否则将会被强制下线。");
    	//em.broadcastServerMsg(5120059, "[神话副本] 希拉已被击败，10秒后将开出宝箱。" ,true);
		var map = eim.getMapInstance(262030300);
		map.startMapEffect("[神话副本] 希拉已被击败，10秒后将开出宝箱。", 5120059);
	}
}

function roll(eim) {
	MaxRandom = 0;
	var count = eim.getProperty("giftcount");
	var rewardPlayer = null;
	//第二次开始,统计上一次ROLL点玩家结果，并发放奖励。
	if ((count*1)>=1) {
		for (var i = 0; i < eim.getPlayerCount(); i++) {
			var charName = eim.getPlayers().get(i).getName();
			var charId = eim.getPlayers().get(i).getId();
			//推送ROLL点信息
			for (var j = 0; j < eim.getPlayerCount(); j++) {
				var notice =  "[神话副本] 玩家 "+charName+" 掷出了 "+eim.getProperty("charid_"+charId)+"点";
				if ((eim.getProperty("charid_"+charId)*1)==0) {
					notice =  "[神话副本] 玩家 "+charName+" 放弃了掷点";
				}
				eim.getPlayers().get(j).dropMessage(6,notice);
			}
			//不断重置最大值
			if ((eim.getProperty("charid_"+charId)*1)>MaxRandom) {
				MaxRandom = eim.getProperty("charid_"+charId);
				//置换玩家名称
				eim.setProperty("rewardplayer", charName);
				//置换玩家ID
				eim.setProperty("rewardplayerid", charId);
			} 
   		}
		for (var j = 0; j < eim.getPlayerCount(); j++) {
			//操作NPC 发放奖励
			eim.getPlayers().get(j).openNpc(1052008, 1111);
		}
	}
	for (var j = 0; j < eim.getPlayerCount(); j++) {
		//重置所有玩家ROLL点点数为零
		eim.setProperty("charid_"+eim.getPlayers().get(j).getId(),"0");
	}
	//次数+1
	eim.setProperty("giftcount", (count*1+1));
	//重新读入次数
	count = eim.getProperty("giftcount");
	count = (count*1);
	//退出战场
	if ((count*1)>10) {
		EndThisBattle(eim);
		return;
	}
	//创建几率
	var chance = Math.floor(Math.random()*600);
	//最终物品列表
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
	//延迟10秒打开ROLL点NPC
	setupTask = em.schedule("openRollNpc", 1000 * 10 * 1, eim);
}

function openRollNpc(eim) {
	for (var i = 0; i < eim.getPlayerCount(); i++) {
		eim.getPlayers().get(i).openNpc(1052008);
    }
	//10秒后继续ROLL点
	setupTask = em.schedule("roll", 1000 * 10 * 1, eim);
}

function EndThisBattle(eim) {
	for (var i = 0; i < eim.getPlayerCount(); i++) {
        eim.getPlayers().get(i).dropMessage(1, "[神话副本] 挑战成功！");
    }
	//em.broadcastYellowMsg("[神话副本] 挑战结束");
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