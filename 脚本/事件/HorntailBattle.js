var CY0 = "┣━━━━━━━━━━━━━━ ━━━━━━━━━━━━━━━━┫";
var CY1 = "┣       - 创意 -       ┫";
var CY2 = "┣ 玩法仿制  　定制脚本 ┫";
var CY3 = "┣ 技术支持 　 游戏顾问 ┫";
var CY4 = "┣ ＷＺ添加　  地图制作 ┫";
var CY5 = "┣ 加盾防御　  售登陆器 ┫";
var CY7 = "┣ 手游开服    端游开服 ┫";
var CY8 = "┣━━━━━━━━━━━━━━ ━━━━━━━━━━━━━━━━┫";
var CY9 = "┣    唯一微信:ZerekY   ┫";
var CY0 = "┣━━━━━━━━━━━━━━ ━━━━━━━━━━━━━━━━┫";
function init() {
    // 0 = Not started, 1 = started, 2 = first head defeated, 3 = second head defeated
    em.setProperty("state", "0");
    em.setProperty("leader", "true");
    em.setProperty("preheadCheck", "0");
}

function setup(eim, leaderid) {
    em.setProperty("state", "1");
    em.setProperty("preheadCheck", "0");
    em.setProperty("leader", "true");

    var eim = em.newInstance("HorntailBattle");

    eim.setInstanceMap(240060000).resetFully();//清除这个怪物
    eim.setInstanceMap(240060100).resetFully();//清除这个怪物
    eim.setInstanceMap(240060200).resetFully();//清除这个怪物
    eim.startEventTimer(21600000); //now changed to 1 hour 15 mins 限时6小时黑龙    毫秒计算的
    eim.schedule("CheckHorntailHead", 3000);
	eim.schedule("messageDamage", 1000*60*1)//毫秒计算的  显示伤害间隔时间
	eim.setProperty("killcount", 0)
    return eim;
}

function CheckHorntailHead(eim) {
    var prop = em.getProperty("preheadCheck");
    if (prop.equals("0")) {
        eim.schedule("CheckHorntailHead", 3000);
    } else if (prop.equals("1")) {
        em.setProperty("preheadCheck", "2");
        var mob = em.getMonster(8810024); // First HT Head
        eim.registerMonster(mob);
        eim.getMapFactory().getMap(240060000).spawnMonsterOnGroundBelow(mob, new java.awt.Point(890, 230));
        eim.broadcastPlayerMsg(5, "暗黑龙王吼了一声，你必须杀死暗黑龙王的左头颅，才能进入下一关。");
        eim.schedule("CheckHorntailHead", 3000);
        em.setProperty("state", "2");
    } else if (prop.equals("2")) {
        eim.schedule("CheckHorntailHead", 3000);
    } else if (prop.equals("3")) {
        em.setProperty("preheadCheck", "4");
        var mob = em.getMonster(8810025); // Second HT Head
        eim.registerMonster(mob);
        eim.getMapFactory().getMap(240060100).spawnMonsterOnGroundBelow(mob, new java.awt.Point(-360, 230));
        eim.broadcastPlayerMsg(5, "暗黑龙王吼了一声，你必须杀死暗黑龙王的右头颅，才能进入下一关。");
        em.setProperty("state", "3");
    }
}

function playerEntry(eim, player) {
    var NowMapID = eim.getProperty("NowMapID");
    var toMapid = 240060200;//传送要不要打2个头，240060200是直接打龙，240060000是打左右头，改好要重载副本
    if (NowMapID != null) {
        toMapid = java.lang.Integer.parseInt(NowMapID);
    }
    var map = eim.getMapFactory().getMap(toMapid);
    player.changeMap(map, map.getPortal(0));
    //加入远征队玩家信息
    eim.setProperty("isSquadPlayerID_" + player.getId(), "1");
    //var map = eim.getMapFactory().getMap(240060000);
    //player.changeMap(map, map.getPortal(0));
	eim.setProperty("damage-"+player.getId(), 0)
}

function changedMap(eim, player, mapid) {
    switch (mapid) {
        case 240060000://龙头1
        case 240060100://龙头2
        case 240060200://黑龙本体
            //保存当前执行地图ID
            eim.setProperty("NowMapID", "" + mapid);
            return;
    }
    eim.unregisterPlayer(player);

    if (eim.disposeIfPlayerBelow(0, 0)) {
        em.setProperty("state", "0");
        em.setProperty("leader", "true");
    }
    // switch (mapid) {
    //     case 240060000:
    //     case 240060100:
    //     case 240060200:
    //         return;
    // }
    //  eim.unregisterPlayer(player);

    //  if (eim.disposeIfPlayerBelow(0, 0)) {
    //      em.setProperty("state", "0");
    //      em.setProperty("leader", "true");
    //  }
}

function playerDisconnected(eim, player) {
    playerExit(eim, player);
    return 0;
    //return 0;

}

function scheduledTimeout(eim) {
    eim.disposeIfPlayerBelow(100, 240050400);
    em.setProperty("state", "0");
    em.setProperty("leader", "true");
}

function playerExit(eim, player) {
    eim.unregisterPlayer(player);

    if (eim.disposeIfPlayerBelow(0, 0)) {
        em.setProperty("state", "0");
        em.setProperty("leader", "true");
    }
}


function monsterValue(eim, mobId) {
	if (mobids.indexOf(mobId) >= 0) { //击败怪物后显示一次伤害
		eim.setProperty("killcount", parseInt(eim.getProperty("killcount"))+1)
	}
	if (parseInt(eim.getProperty("killcount")) == mobids.length) {
		messageDamage(eim)
	}
    return 1;
}

function allMonstersDead(eim) {
}

function playerRevive(eim, player) {
    return false;
}

function clearPQ(eim) {}
function leftParty(eim, player) {}
function disbandParty(eim) {}
function playerDead(eim, player) {
    //eim.setProperty("isSquadPlayerID_" + player.getId(), "0");//注释这个就死了后可以重返
}
function cancelSchedule() {}

var mobids = [//黑龙全部boss
	//8810018,
	8810009,
	8810008,
	8810007,
	8810006,
	8810005,
	8810004,
	8810003,
	8810002,
	//8800009,
	//8800010,
]
var total = 2009000000;//总血量

function monsterDamaged(eim, player, mobId, damage) {
	if (mobids.indexOf(mobId) >= 0) {
		eim.setProperty("damage-"+player.getId(), parseInt(eim.getProperty("damage-" +player.getId())) + damage)
	}
}

function messageDamage(eim) {
	eim.schedule("messageDamage", 1000*60*1)
	var players = eim.getPlayers();
	var arr = [];
	
	for (var i in players) {
		var d = parseInt(eim.getProperty("damage-" +players.get(i).getId()));
		if (d > 0) {
			arr.push({
				'd':d,
				'msg':"  ["+players.get(i).getName()+"]> > > 累计对黑龙造成伤害"+d+"，百分比"+(d/(total*1.0) * 100).toFixed(2)+"%"
			})
		}
	}
	arr = arr.sort(compare)
	if (arr.length > 0) {
		eim.broadcastPlayerMsg(6, "┏ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ┓")
		eim.broadcastPlayerMsg(6, "  黑龙总血量"+total)
		for (var i in arr) {
			eim.broadcastPlayerMsg(6, arr[i]['msg'])
		}
		eim.broadcastPlayerMsg(6, "┗ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ┛")
	}
}

function compare(a, b) {
	return b['d']-a['d'];
}