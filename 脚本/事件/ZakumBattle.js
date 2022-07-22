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
    em.setProperty("state", "0");
    em.setProperty("leader", "true");
}

function setup(eim, leaderid) {
    em.setProperty("state", "1");
    em.setProperty("leader", "true");
    var eim = em.newInstance("ZakumBattle");

    eim.setProperty("zakSummoned", "0");
    eim.setProperty("totaldamage", "0");
    eim.setInstanceMap(280030000).resetFully();
    eim.startEventTimer(18000000); //5 小时  扎昆 毫秒计算的
	eim.schedule("messageDamage", 1000*60*1)//毫秒计算的  显示伤害间隔时间
    return eim;
}

function playerEntry(eim, player) {
    var NowMapID = eim.getProperty("NowMapID");
    var toMapid = 280030000;
    if (NowMapID != null) {
        toMapid = java.lang.Integer.parseInt(NowMapID);
    }
    var map = eim.getMapFactory().getMap(toMapid);
    player.changeMap(map, map.getPortal(0));
    //加入远征队玩家信息
    eim.setProperty("isSquadPlayerID_" + player.getId(), "1");
    //var map = eim.getMapInstance(0);
    //player.changeMap(map, map.getPortal(0));
	eim.setProperty("damage-"+player.getId(), 0)
}

function playerRevive(eim, player) {
    return false;
}

function changedMap(eim, player, mapid) {
    switch (mapid) {
        case 280030000:
            //保存当前执行地图ID
            eim.setProperty("NowMapID", "" + mapid);
            return;
    }

    if (mapid != 280030000) {
        eim.unregisterPlayer(player);
        if (eim.disposeIfPlayerBelow(0, 0)) {
            em.setProperty("leader", "true");
            em.setProperty("state", "0");
        }
    }
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
	if (mobId == 8800002) {
		messageDamage(eim)
	}
    return 1;
}

function playerExit(eim, player) {
    eim.unregisterPlayer(player);

    if (eim.disposeIfPlayerBelow(0, 0)) {
        em.setProperty("leader", "true");
        em.setProperty("state", "0");
    }
}

function end(eim) {
    eim.disposeIfPlayerBelow(100, 211042300);
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
   //end(eim);
   
}
function cancelSchedule() {}

var mobids = [//扎昆全部boss
	8800000,
	8800001,
	8800002,
	8800003,
	8800004,
	8800005,
	8800006,
	8800007,
	8800008,
	8800009,
	8800010,
]
var total = 670100000;//总血量

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
				'msg':"  ["+players.get(i).getName()+"] > > >累计对扎昆造成伤害"+d+"，百分比"+(d/(total*1.0) * 100).toFixed(2)+"%"
			})
		}
	}
	arr = arr.sort(compare)
	if (arr.length > 0) {
		eim.broadcastPlayerMsg(6, "┏ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ┓")
		eim.broadcastPlayerMsg(6, "  扎昆总血量"+total)
		for (var i in arr) {
			eim.broadcastPlayerMsg(6, arr[i]['msg'])
		}
		eim.broadcastPlayerMsg(6, "┗ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ━ ┛")
	}
}

function compare(a, b) {
	return b['d']-a['d'];
}