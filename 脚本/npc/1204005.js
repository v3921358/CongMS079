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

var status = -1;

function action(mode, type, selection) {
    if (mode == 1)
	status++;
    else
	status--;
    if (status == 0) {
		if (cm.getMap().getAllMonstersThreadsafe().size() <= 0) {//判断地图有没有怪物
        //cm.sendNext("感谢你救了我!");
		cm.forceStartQuest(21733); //开始任务
		cm.forceStartQuest(21762, "2");//给可以完成任务的条件
		cm.warp(104000004);
		cm.dispose();
		} else {
        cm.sendNext("请消灭人偶师在来解救我吧!");
		cm.dispose();
    }
}}