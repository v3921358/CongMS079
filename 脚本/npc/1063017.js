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
 function action(mode, type, selection) {
	if (cm.getPlayerCount(910510202) <= 0 && cm.isQuestActive(21734) ==1 ) {//判断地图是否有人。判断任务
	cm.getMap(910510202).resetFully();//地图刷新
	cm.spawnMobOnMap(9300346,1,205,257,910510202);//召唤怪物
	cm.warp(910510202, 0);
	cm.teachSkill(21100005, cm.getPlayer().getSkillLevel(21100005), 10);//连环吸血技能
	cm.forceCompleteQuest(21734);//完成任务
	cm.dispose();
	} else {
	cm.sendOk("里面有人或者没有接受相关任务而无法进入此洞穴!");
	cm.dispose();
}}