
var CY0 = "�ǩ��������������������������� ����������������������������������";
var CY1 = "��       - ���� -       ��";
var CY2 = "�� �淨����  �����ƽű� ��";
var CY3 = "�� ����֧�� �� ��Ϸ���� ��";
var CY4 = "�� �ף���ӡ�  ��ͼ���� ��";
var CY5 = "�� �Ӷܷ�����  �۵�½�� ��";
var CY7 = "�� ���ο���    ���ο��� ��";
var CY8 = "�ǩ��������������������������� ����������������������������������";
var CY9 = "��   ΨһQQ:3066318387  ��";
var CY0 = "�ǩ��������������������������� ����������������������������������";
load("nashorn:mozilla_compat.js");
importPackage(Packages.database);
importPackage(Packages.java.util);
importPackage(Packages.client);
importPackage(Packages.server);

//����������-----------------------------------------------------
var config_ReadyMapId = 990009999;//�Լ�ս��׼����ͼID
var config_BattleMapId = 990010000;//�Լ���ͼ990010000
var config_EventName = "WY_ChiJi";//�¼��ű���
var config_Pvpdamage = 600;//pvp�˺�
var config_PvpLimitSkills = [2121007];//PK���Ƽ��ܲ��ܴ���˺����չ���0
var config_PvpSkillDamage = {//PK�Զ��弼���˺��������ܣ��ұ����Ӧ���˺�
	0:600,
	2121006:200,
	2121003:300,
};


var config_MinNP = 2;//�Լ�ս�����ȴ���ͼ��Ҫ�����ٵĿ�������
var config_WaitJoinTime = 1;//���ӡ�׼����ͼ�ȴ���Ҽ����ʱ�䡣������ʱ�䣬����Ҳ������Զ���ֹ
var config_WaitReadyTime = 1;//���ӡ�ս��׼���ȴ�ʱ�䡣���������㿪�������ʱ������
var config_RunTime = 20;//���ӡ������ʱ��
var config_Channel = 1;//�����Ƶ��
var config_DuHp = 0.01;//ÿ�ι̶�������Ѫ��0.01���ǰٷ�֮1
var config_DuTime = 1;//�룬Ͷ�����ʱ��
var config_DuStartTime = 19;//���ӣ����������ٷ���ʱ������Ͷ����

var �������� =2022679;


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
		em.broadcastServerMsg(5,"���Լ�ս��������ѽ�����δ����,�ȴ������!",false);
		eim = em.newInstance(config_EventName);
		mapBattle = eim.getMapInstance(config_BattleMapId);
		mapReady = eim.getMapInstance(config_ReadyMapId);
		//eim.setInstanceMap(config_ReadyMapId);
		_InitProperty();//Pro��ʼ
		_ScheduleCheckOpen();//�ȴ�GM�������
	}
}


/**
*
*	��ʼ�ṹ�ű�
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
*	ʱ���ܿؼ�飨GM�ֶ��������
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
*	�߼��ܿ�-����״̬���Լ���ʱ��״̬
*	
*/
function _TimeCheckOpen(){
	
	eim = em.newInstance(config_EventName);
	if (em.getProperty("state")=="0"){////0����û�п���״̬
		//em.broadcastServerMsg(5,"�Լ�ս��-�ȴ�����",false);
		_ScheduleCheckOpen();
	}else if(em.getProperty("state")=="1"){//1��������ڲ��ҵȴ�
		time4Join = config_WaitJoinTime * 60;
		_ScheduleCheckJoin();
	}
}

/**
*
*	�ȴ�����볡
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
*	�ȴ�����볡
*	
*/
function _TimeJoin(){
	if(time4Join>0){
		
		if(time4Join%10 == 0 || time4Join < 10){
			if(time4Join == 30){
				em.broadcastServerMsg(5,"���Լ�ս�����볡�ȴ�-���30�뵹��ʱ��δ�볡�����ץ���ˣ�",false);
			}else{
				em.broadcastServerMsg(5,"���Լ�ս�����볡�ȴ�-����ʱ"+time4Join,false);
			}
		}
		time4Join -= 1;
		_ScheduleCheckJoin();
	}else{
		if(mapReady.getCharacters().size() < config_MinNP){
			//�������㣬�����˴��ͻ��г���
			em.schedule("_TimeOutGameOver", 1000, eim);
		}else{
			em.schedule("_StartReady", 1000, eim);
		}
	}
}

/**
*
*	��ʱ��-�����
*	
*/
function _ScheduleDu(){
	//eim.broadcastPlayerMsg(5, "���Լ��-�����¶� ʱ�䣺"+ ((config_RunTime * 1000 *60) + eim.getTimeLeft()));
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
*	�����
*	
*/
function _TimeDu(){

	if(((config_RunTime * 1000 *60) + eim.getTimeLeft()) < (config_DuStartTime * 1000 *60)){
		//eim.broadcastPlayerMsg(5, "���Լ��-�¶�");
		var players = mapBattle.getCharacters().iterator();
		while (players.hasNext()) {
			var p = players.next();
			//eim.broadcastPlayerMsg(5, "���Լ��-ִ��");
			p.addHP(-p.getStat().getMaxHp()*config_DuHp);
			if(em.getProperty("showDu") == "0"){
				em.setProperty("showDu","1");
				em.broadcastServerMsg(5121000, "���Լ�ս���������Ŷ�ģʽ,ս�����ÿ����ģ�"+p.getStat().getMaxHp()*config_DuHp+"Ѫ����", true);
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
*	��ʱ��-����
*	
*/
function scheduledTimeout(eim) {
	eim = em.newInstance(config_EventName);
	
	if(em.getProperty("time")=="1"){
		_StartGame(eim);
	}else if(em.getProperty("time")=="2"){
		//��Ϸ����
		var players = mapBattle.getCharacters().iterator();
		while (players.hasNext()) {
		   var player = players.next();
		   player.addHP(-999999);
		}
	}
}

/**
*
*	����ʱ����-��Ϸ�����㿪��������
*	
*/
function _TimeOutGameOver(eim){
	var players = mapReady.getCharacters().iterator();
    while (players.hasNext()) {
       var player = players.next();
	   player.dropMessage(1, "�����������㣬�Լ�ս���޷�������");
	   eim.registerPlayer(player);
    }
	em.warpAllPlayer(config_ReadyMapId, 910000000);
	init();
}

/**
*
*	ս��׼��
*	
*/
function _StartReady(eim){
	var players = mapReady.getCharacters().iterator();
	while (players.hasNext()) {
		var player = players.next();
		eim.registerPlayer(player);
		//player.changeMap(mapReady,mapReady.getPortal(0));
		player.startMapEffect("ս������["+config_WaitReadyTime+"����]��ʼ��������׼����", 5121000);
		player.setBossLog("�Լ�ս��");
		player.resetBossLog("�Լ�Rank");
		//player.dropMessage(1, "ս������["+config_WaitReadyTime+"����]��ʼ��������׼����");
	}
	em.setProperty("time","1");
	em.setProperty("state", "2");
	eim.startEventTimer(config_WaitReadyTime*1000*60); // 10 min
	
}

/**
*
*	ս����ʼ
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
*	�������
*	
*/
function playerDead(eim, player) {
	eim.broadcastPlayerMsg(5, "���Լ�ս�������["+player.getName()+"]�����̷�ʬ����ǰ������["+eim.getPlayerCount()+"] ");
	player.dropMessage(1, "���Լ�ս������ϲ���õ�"+eim.getPlayerCount()+"��!\r\n���:�Լ����");
	
	player.setBossLog("�Լ�Rank",0,eim.getPlayerCount());
	player.gainItem(��������,1);
	
	player.changeMap(910000000);
	
	if(eim.getPlayerCount() == 1){
		//eim.broadcastPlayerMsg(5, "���Լ��-ֻ��һ���˻������ˡ�");
		
		var players = mapBattle.getCharacters().iterator();
		while (players.hasNext()) {
			var p = players.next();
			say = "���Լ�ս������ϲ���["+p.getName()+"]�ɹ��Լ�������Լ����󼪴�����";
			em.broadcastServerMsg(5122000, say, true);
			p.dropMessage(1, "��ϲ�����˵�һ��!\r\n���:�Լ����");
			p.gainItem(��������,1);
			p.setBossLog("�Լ�Rank",0,eim.getPlayerCount());
			//p.addHP(-99999);
			p.changeMap(910000000);
			em.scheduleAtTimestamp("_reStart", java.util.Calendar.getInstance().getTimeInMillis() + 3000);
			break;
		}
	}else{
		eim.broadcastPlayerMsg(5, "���Լ�ս�����Ҵ���ң�"+eim.getPlayerCount()+"��");
	}
}
/**
*
*	���¹����¼��
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
	//em.broadcastServerMsg(5,player.getName()+"�볡",false);
}

function playerRevive(eim, player) {
    return false;
}

function changedMap(eim, player, mapid) {
	
	//em.broadcastServerMsg(5,player.getName()+"�볡-ChangeMap",false);
	
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
	//em.broadcastServerMsg(5,player.getName()+"�˳�",false);
	eim.unregisterPlayer(player);
}
function leftParty(eim, player) {}
function disbandParty(eim) {}
function monsterDrop(eim,player,mob){}
function monsterValue(eim, mobId) {return 1;}







