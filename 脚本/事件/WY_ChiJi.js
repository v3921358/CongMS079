
var CY0 = "┣━━━━━━━━━━━━━━ ━━━━━━━━━━━━━━━━┫";
var CY1 = "┣       - 创意 -       ┫";
var CY2 = "┣ 玩法仿制  　定制脚本 ┫";
var CY3 = "┣ 技术支持 　 游戏顾问 ┫";
var CY4 = "┣ ＷＺ添加　  地图制作 ┫";
var CY5 = "┣ 加盾防御　  售登陆器 ┫";
var CY7 = "┣ 手游开服    端游开服 ┫";
var CY8 = "┣━━━━━━━━━━━━━━ ━━━━━━━━━━━━━━━━┫";
var CY9 = "┣   唯一QQ:3066318387  ┫";
var CY0 = "┣━━━━━━━━━━━━━━ ━━━━━━━━━━━━━━━━┫";
load("nashorn:mozilla_compat.js");
importPackage(Packages.database);
importPackage(Packages.java.util);
importPackage(Packages.client);
importPackage(Packages.server);

//【配置区】-----------------------------------------------------
var config_ReadyMapId = 990009999;//吃鸡战场准备地图ID
var config_BattleMapId = 990010000;//吃鸡地图990010000
var config_EventName = "WY_ChiJi";//事件脚本名
var config_Pvpdamage = 600;//pvp伤害
var config_PvpLimitSkills = [2121007];//PK限制技能不能打出伤害，普攻填0
var config_PvpSkillDamage = {//PK自定义技能伤害，左边填技能，右边填对应的伤害
	0:600,
	2121006:200,
	2121003:300,
};


var config_MinNP = 2;//吃鸡战场，等待地图，要求最少的开启人数
var config_WaitJoinTime = 1;//分钟。准备地图等待玩家加入的时间。超出该时间，若玩家不足则活动自动中止
var config_WaitReadyTime = 1;//分钟。战斗准备等待时间。当人数满足开启活动条件时触发！
var config_RunTime = 20;//分钟。活动持续时间
var config_Channel = 1;//活动启动频道
var config_DuHp = 0.01;//每次固定报毒扣血量0.01就是百分之1
var config_DuTime = 1;//秒，投毒间隔时间
var config_DuStartTime = 19;//分钟，将在最后多少分钟时，开启投毒。

var 奖励道具 =2022679;


//------------------------------------------------0000000-----


var setupTask = null;
var setupTaskJoin = null;
var setupTaskDu = null;
var time4Join = 0;
var eim;
var mapReady;
var mapBattle;

function init() {
	if(em.getChannelServer().getChannel() == config_Channel){
		em.broadcastServerMsg(5,"『吃鸡战场』：活动已结束或未启动,等待活动开启!",false);
		eim = em.newInstance(config_EventName);
		mapBattle = eim.getMapInstance(config_BattleMapId);
		mapReady = eim.getMapInstance(config_ReadyMapId);
		//eim.setInstanceMap(config_ReadyMapId);
		_InitProperty();//Pro初始
		_ScheduleCheckOpen();//等待GM开启检查
	}
}


/**
*
*	初始结构脚本
*	
*/
function _InitProperty(){
    em.setProperty("state", "0");
	em.setProperty("time", "0");
	em.setProperty("team", "0");
	em.setProperty("showDu","0");
}

/**
*
*	时间总控检查（GM手动开启活动）
*	
*/
function _ScheduleCheckOpen(){
	var cal = java.util.Calendar.getInstance();
    cal.set(java.util.Calendar.SECOND, 5);
	var nextTime = cal.getTimeInMillis();
	while (nextTime <= java.lang.System.currentTimeMillis()) {
		nextTime += 1000;
	}
	setupTask = em.scheduleAtTimestamp("_TimeCheckOpen", nextTime);
}



/**
*
*	逻辑总控-分配活动状态，以及计时器状态
*	
*/
function _TimeCheckOpen(){
	
	eim = em.newInstance(config_EventName);
	if (em.getProperty("state")=="0"){////0代表没有开启状态
		//em.broadcastServerMsg(5,"吃鸡战场-等待开启",false);
		_ScheduleCheckOpen();
	}else if(em.getProperty("state")=="1"){//1代表开启入口并且等待
		time4Join = config_WaitJoinTime * 60;
		_ScheduleCheckJoin();
	}
}

/**
*
*	等待玩家入场
*	
*/
function _ScheduleCheckJoin(){
	var cal = java.util.Calendar.getInstance();
    cal.set(java.util.Calendar.SECOND, 5);
	var nextTime = cal.getTimeInMillis();
	while (nextTime <= java.lang.System.currentTimeMillis()) {
		nextTime += 1000;
	}
	setupTaskJoin = em.scheduleAtTimestamp("_TimeJoin", nextTime);
}

/**
*
*	等待玩家入场
*	
*/
function _TimeJoin(){
	if(time4Join>0){
		
		if(time4Join%10 == 0 || time4Join < 10){
			if(time4Join == 30){
				em.broadcastServerMsg(5,"『吃鸡战场』入场等待-最后30秒倒计时！未入场玩家请抓紧了！",false);
			}else{
				em.broadcastServerMsg(5,"『吃鸡战场』入场等待-倒计时"+time4Join,false);
			}
		}
		time4Join -= 1;
		_ScheduleCheckJoin();
	}else{
		if(mapReady.getCharacters().size() < config_MinNP){
			//人数不足，所有人传送回市场。
			em.schedule("_TimeOutGameOver", 1000, eim);
		}else{
			em.schedule("_StartReady", 1000, eim);
		}
	}
}

/**
*
*	计时器-随机毒
*	
*/
function _ScheduleDu(){
	//eim.broadcastPlayerMsg(5, "测试检查-开启下毒 时间："+ ((config_RunTime * 1000 *60) + eim.getTimeLeft()));
	var cal = java.util.Calendar.getInstance();
    cal.set(java.util.Calendar.SECOND, 5);
	var nextTime = cal.getTimeInMillis();
	while (nextTime <= java.lang.System.currentTimeMillis()) {
		nextTime += 1000 * config_DuTime;
	}
	setupTaskDu = em.scheduleAtTimestamp("_TimeDu", nextTime);
}


/**
*
*	随机毒
*	
*/
function _TimeDu(){

	if(((config_RunTime * 1000 *60) + eim.getTimeLeft()) < (config_DuStartTime * 1000 *60)){
		//eim.broadcastPlayerMsg(5, "测试检查-下毒");
		var players = mapBattle.getCharacters().iterator();
		while (players.hasNext()) {
			var p = players.next();
			//eim.broadcastPlayerMsg(5, "测试检查-执行");
			p.addHP(-p.getStat().getMaxHp()*config_DuHp);
			if(em.getProperty("showDu") == "0"){
				em.setProperty("showDu","1");
				em.broadcastServerMsg(5121000, "『吃鸡战场』开启放毒模式,战场玩家每秒损耗："+p.getStat().getMaxHp()*config_DuHp+"血量！", true);
			}
		}
		if(mapBattle.getCharacters().size() != 0){
		_ScheduleDu();
		}
	}else{
		_ScheduleDu();
	}
}


/**
*
*	计时器-结束
*	
*/
function scheduledTimeout(eim) {
	eim = em.newInstance(config_EventName);
	
	if(em.getProperty("time")=="1"){
		_StartGame(eim);
	}else if(em.getProperty("time")=="2"){
		//游戏结束
		var players = mapBattle.getCharacters().iterator();
		while (players.hasNext()) {
		   var player = players.next();
		   player.addHP(-999999);
		}
	}
}

/**
*
*	倒计时结束-游戏不满足开启条件。
*	
*/
function _TimeOutGameOver(eim){
	var players = mapReady.getCharacters().iterator();
    while (players.hasNext()) {
       var player = players.next();
	   player.dropMessage(1, "由于人数不足，吃鸡战场无法开启！");
	   eim.registerPlayer(player);
    }
	em.warpAllPlayer(config_ReadyMapId, 910000000);
	init();
}

/**
*
*	战斗准备
*	
*/
function _StartReady(eim){
	var players = mapReady.getCharacters().iterator();
	while (players.hasNext()) {
		var player = players.next();
		eim.registerPlayer(player);
		//player.changeMap(mapReady,mapReady.getPortal(0));
		player.startMapEffect("战斗将在["+config_WaitReadyTime+"分钟]后开始，请做好准备！", 5121000);
		player.setBossLog("吃鸡战场");
		player.resetBossLog("吃鸡Rank");
		//player.dropMessage(1, "战斗将在["+config_WaitReadyTime+"分钟]后开始，请做好准备！");
	}
	em.setProperty("time","1");
	em.setProperty("state", "2");
	eim.startEventTimer(config_WaitReadyTime*1000*60); // 10 min
	
}

/**
*
*	战斗开始
*	
*/
function _StartGame(eim){
	em.initPvp(true, config_Channel, config_BattleMapId, config_Pvpdamage, config_PvpLimitSkills, config_PvpSkillDamage)
	var players = mapReady.getCharacters().iterator();
	while (players.hasNext()) {
		var player = players.next();
		eim.registerPlayer(player);
		player.changeMap(mapBattle,mapBattle.getPortal(Math.floor(Math.random()*53)));
	}
	
	em.setProperty("time","2");
	em.setProperty("state", "3");
	_ScheduleDu();
	eim.startEventTimer(config_RunTime*1000*60); // 10 min
}

/**
*
*	玩家死亡
*	
*/
function playerDead(eim, player) {
	eim.broadcastPlayerMsg(5, "『吃鸡战场』玩家["+player.getName()+"]被残忍分尸：当前排名：["+eim.getPlayerCount()+"] ");
	player.dropMessage(1, "『吃鸡战场』恭喜你获得第"+eim.getPlayerCount()+"名!\r\n获得:吃鸡礼包");
	
	player.setBossLog("吃鸡Rank",0,eim.getPlayerCount());
	player.gainItem(奖励道具,1);
	
	player.changeMap(910000000);
	
	if(eim.getPlayerCount() == 1){
		//eim.broadcastPlayerMsg(5, "测试检查-只有一个人活下来了。");
		
		var players = mapBattle.getCharacters().iterator();
		while (players.hasNext()) {
			var p = players.next();
			say = "『吃鸡战场』恭喜玩家["+p.getName()+"]成功吃鸡！今晚吃鸡，大吉大利！";
			em.broadcastServerMsg(5122000, say, true);
			p.dropMessage(1, "恭喜你获得了第一名!\r\n获得:吃鸡礼包");
			p.gainItem(奖励道具,1);
			p.setBossLog("吃鸡Rank",0,eim.getPlayerCount());
			//p.addHP(-99999);
			p.changeMap(910000000);
			em.scheduleAtTimestamp("_reStart", java.util.Calendar.getInstance().getTimeInMillis() + 3000);
			break;
		}
	}else{
		eim.broadcastPlayerMsg(5, "『吃鸡战场』幸存玩家："+eim.getPlayerCount()+"人");
	}
}
/**
*
*	重新挂起事件活动
*	
*/
function _reStart(){
	init();
}


function cancelSchedule() {
	if(setupTask!=null){
		setupTask.cancel(true);
	}
	
	if(setupTaskJoin!=null){
		setupTaskJoin.cancel(true);
	}
	
	
	if(setupTaskDu!=null){
		setupTaskDu.cancel(true);
	}
}

function playerEntry(eim, player) {
	//em.broadcastServerMsg(5,player.getName()+"入场",false);
}

function playerRevive(eim, player) {
    return false;
}

function changedMap(eim, player, mapid) {
	
	//em.broadcastServerMsg(5,player.getName()+"入场-ChangeMap",false);
	
	if (mapid != config_ReadyMapId && mapid != config_BattleMapId) {
		eim.unregisterPlayer(player);
	}
	return;
}

function clearPQ(eim) {
	_InitProperty();
}
function allMonstersDead(eim) {}
function playerDisconnected(eim, player) {}
function playerExit(eim, player) {
	//em.broadcastServerMsg(5,player.getName()+"退出",false);
	eim.unregisterPlayer(player);
}
function leftParty(eim, player) {}
function disbandParty(eim) {}
function monsterDrop(eim,player,mob){}
function monsterValue(eim, mobId) {return 1;}







