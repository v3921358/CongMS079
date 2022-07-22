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
    em.setProperty("leader", "true");
    em.setProperty("state", "0");
}

function setup(eim, leaderid) {
    em.setProperty("leader", "true");
    var eim = em.newInstance("PinkBeanBattle");
    eim.setInstanceMap(270050100).resetFully();
    em.setProperty("state", "1");

    eim.startEventTimer(10800000); // 1 hr
	eim.schedule("messageDamage", 1000*60*1)//毫秒计算的  显示伤害间隔时间
    return eim;
}

function playerEntry(eim, player) {
    var NowMapID = eim.getProperty("NowMapID");
    var toMapid = 270050100;
    if (NowMapID != null) {
        toMapid = java.lang.Integer.parseInt(NowMapID);
    }
    var map = eim.getMapFactory().getMap(toMapid);
    player.changeMap(map, map.getPortal(0));
    eim.setProperty("isSquadPlayerID_" + player.getId(), "1");
    //var map = eim.getMapFactory().getMap(270050100);
    //player.changeMap(map, map.getPortal(0));
	eim.setProperty("damage-"+player.getId(), 0)
}

function playerRevive(eim, player) {
    return false;
}

function scheduledTimeout(eim) {
    eim.disposeIfPlayerBelow(100, 270050300);
    em.setProperty("state", "0");
    em.setProperty("leader", "true");
}

function changedMap(eim, player, mapid) {
    switch (mapid) {
        case 270050100:
            eim.setProperty("NowMapID", "" + mapid);
            return;
    }
    if (mapid != 270050100) {
        eim.unregisterPlayer(player);

        if (eim.disposeIfPlayerBelow(0, 0)) {
            em.setProperty("state", "0");
            em.setProperty("leader", "true");
        }
    }
}

function playerDisconnected(eim, player) {
    playerExit(eim, player);
    return 0;
}

function monsterValue(eim, mobId) {
	if (mobId == 8820001) {//击败怪物后显示一次伤害
		messageDamage(eim)
	}
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
    if (eim.disposeIfPlayerBelow(100, 270050300)) {
        em.setProperty("state", "0");
        em.setProperty("leader", "true");
    }
}

function clearPQ(eim) {
    end(eim);
}

function allMonstersDead(eim) {
}

function leftParty(eim, player) {}
function disbandParty(eim) {}
function playerDead(eim, player) {
    //eim.setProperty("isSquadPlayerID_" + player.getId(), "1");//注释之后死亡可以仅
}
function cancelSchedule() {}
var mobids = [//品克滨全部boss
	8820001,//品克滨  21亿血
	8820019,//女神雕像  6亿血
	8820021,//贤者莱克斯 3亿血
	8820003,//贤者所罗门 3亿血
	8820022,//火鹰雕像   4.5亿血
	8820023,//冰鹰雕像   4.5亿血
	8820010,//初始形态品克滨   3亿血
	
]
var total = 3300000000;//总血量

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
				'msg':"  ["+players.get(i).getName()+"]> > > 累计对时间的宠儿－品克缤造成伤害"+d+"，百分比"+(d/(total*1.0) * 100).toFixed(2)+"%"
			})
		}
	}
	arr = arr.sort(compare)
	if (arr.length > 0) {
		eim.broadcastPlayerMsg(6, "┏ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ┓")
		eim.broadcastPlayerMsg(6, "  品克缤总血量"+total)
		for (var i in arr) {
			eim.broadcastPlayerMsg(6, arr[i]['msg'])
		}
		eim.broadcastPlayerMsg(6, "┗ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ┛")
	}
}

function compare(a, b) {
	return b['d']-a['d'];
}