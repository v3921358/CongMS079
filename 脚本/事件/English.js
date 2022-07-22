
//简单
var letters0 = Array("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");
var answers0 = Array("APPLE", "BEAR", "CHEESE", "DOUGHNUT", "EARTH", "FLY", "GOLD", "HALLOWEEN", "ICE", "JEWELRY", "KING", "LOVE", "MOUNTAIN", "NOTE", "ORANGE", "POLICE", "QUIZ", "ROSE", "SNAKE", "TABLE", "UFO", "VIP", "WOOD", "XMAS", "YOUNG", "ZZZ");

//中级
var letters1 = Array("苹果", "肉", "鸡蛋", "橙色", "柠檬", "蜂蜜","青苹果", "沙拉", "炸鸡", "蛋糕", "披萨", "汉堡包", "热狗", "第三", "发票", "橘子汁", "葡萄汁", "融化奶酪", "驯鹿",  "日落", "芝士蛋糕");
var answers1 = Array("APPLE", "MEAT", "EGG", "ORANGE", "LEMON", "HONEY", "GREENAPPLE", "SALAD", "FRIEDCHICKEN", "CAKE", "PIZZA", "HAMBURGER", "HOTDOG", "DRIEDSQUID", "FATSAUSAGE", "ORANGEJUICE", "GRAPEJUICE", "MELTINGCHEESE", "REINDEERMILK", "SUNSETDEW", "CHEESECAKE");

//困难
var letters2 = Array("Lirin最喜欢什么动物??",
 "金字塔法老王是什麼颜色??", 
 "请给我这些字母 [REINDEER]", 
 "How much EXP does it take for level 1-2?", 
 "Who exchanges Vote Points in FM?", 
 "What level does a Beginner become a Magician?",
 "What town does an Evan start in?",
 "What town is the home to the Black Wings?", 
 "What are the Wild Hunters, Battle Mages, and Mechanics?", 
 "What type of dragon is Mir?", 
 "Who is Mir's ancestor?",
 "What weapon does the Aran use?",
 "Who is the job instructor for Mechanic?", 
 "冒险家三转需要用到那一种石头??", 
 "哪一个NPC可以给你第一只宠物??", 
 "狂暴猎人的豹英文??", 
 "品克缤头上的雕像名字是??", 
 "暗黑龙王靠近那个地图??", 
 "在枫之谷世界中残暴扎昆多少级才能挑战??");
var answers2 = Array("WOLF", "YELLOW", "REINDEER","FIFTEEN", "PHOENIX", "EIGHT","HENESYS", "EDELSTEIN", "RESISTANCE", "ONYX", "AFRIEN", "POLEARM", "CHECKY", "DARKCRYSTAL", "GAGA", "JAGUAR","ARIEL", "LEAFRE", "FIFTY");

function init() {
}

function monsterValue(eim, mobId) {
    return 1;
}

function setup(mapid) {

    var eim = em.newInstance("English" + mapid);
    eim.setInstanceMap(702090101 + (parseInt(mapid) * 100)).resetFully();
    eim.setInstanceMap(702090102 + (parseInt(mapid) * 100)).resetFully();
    eim.setInstanceMap(702090103 + (parseInt(mapid) * 100)).resetFully();
    
	eim.setProperty("mode", mapid);
	if (eim.getProperty("mode").equals("0")) {
		var ee = java.lang.Math.floor(java.lang.Math.random() * letters0.length);
		eim.setProperty("question", letters0[ee]);
		eim.setProperty("answer", answers0[ee]);
	} else if (eim.getProperty("mode").equals("1")) {
		var ee = java.lang.Math.floor(java.lang.Math.random() * letters1.length);
		eim.setProperty("question", letters1[ee]);
		eim.setProperty("answer", answers1[ee]);
	} else if (eim.getProperty("mode").equals("2")) {
		var ee = java.lang.Math.floor(java.lang.Math.random() * letters2.length);
		eim.setProperty("question", letters2[ee]);
		eim.setProperty("answer", answers2[ee]);
	}
    eim.startEventTimer(10 * 60000); //10 mins

    return eim;
}

function playerEntry(eim, player) {
    var map = eim.getMapInstance(0);
    player.changeMap(map, map.getPortal(0));
	if (eim.getProperty("mode").equals("0")) {
		player.sendEnglishQuiz("在英文村的 [" + eim.getProperty("question") + "] 怪物代表什么??");		
	} else if (eim.getProperty("mode").equals("1")) {
		player.sendEnglishQuiz("请给我这些字母 [" + eim.getProperty("question") + "]！");		
	} else if (eim.getProperty("mode").equals("2")) {
		player.sendEnglishQuiz(eim.getProperty("question"));		
	}
}

function playerDead(eim, player) {
}

function changedMap(eim, player, mapid) {
    switch (mapid) {
	case 702090101: // 1st Stage
	case 702090102: // 2nd Stage
	case 702090103: // 3rd Stage
	case 702090201:
	case 702090202:
	case 702090203:
	case 702090301:
	case 702090302:
		case 702090303:
	    return; // Everything is fine
    }
    eim.unregisterPlayer(player);

    if (eim.disposeIfPlayerBelow(2, 702090400)) {
    }
}

function playerRevive(eim, player) {
}

function playerDisconnected(eim, player) {
    return -2;
}

function leftParty(eim, player) {			
    // If only 2 players are left, uncompletable
    if (!eim.disposeIfPlayerBelow(2, 702090400)) {
	playerExit(eim, player);
    }
}

function disbandParty(eim) {
    // Boot whole party and end
    eim.disposeIfPlayerBelow(100, 702090400);
}


function scheduledTimeout(eim) {
    clearPQ(eim);
}

function playerExit(eim, player) {
    eim.unregisterPlayer(player);

    var exit = eim.getMapFactory().getMap(702090400);
    player.changeMap(exit, exit.getPortal(0));
    if (eim.disposeIfPlayerBelow(2, 702090400)) {
    }
}

function clearPQ(eim) {
    // KPQ does nothing special with winners
    eim.disposeIfPlayerBelow(100, 702090400);
}

function allMonstersDead(eim) {
}

function cancelSchedule() {
}