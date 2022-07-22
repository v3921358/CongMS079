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
var skills = Array(21001003, 21000000, 21100002, 21100004, 21100005, 21110002);
function start(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        if (status == 0) {
            qm.sendNext("#b(再考虑一下好了...)#k");
            qm.dispose();
            return;
        }
        status--;
    }
    if (status == 0) {
        qm.sendYesNo("#b(我自己确信是使用过#p1201001#的英雄吗？确定的话就拿出力量抓住#p1201001#吧一定会有什么反应。)#k");
    } else if (status == 1) {
        if (qm.getJob() == 2000) {
            qm.changeJob(2100);
            qm.forceCompleteQuest();
            qm.resetStats(35, 4, 4, 4);
            qm.expandInventory(1, 4);
            qm.expandInventory(2, 4);
            qm.expandInventory(3, 4);
            qm.expandInventory(4, 4);
            qm.gainItem(1142129, 1);
            qm.forceCompleteQuest(29924); //medal
            qm.teachSkill(20009000, 0, -1);
            qm.teachSkill(20009000, 1, 0);
            for (var i = 0; i < skills.length; i++) {
                qm.teachSkill(skills[i], 0);
            }
            qm.sendNextS("#b(好像想起什么了...)#k", 3);
        }
    } else if (status == 2) {
            qm.MovieClipIntroUI(true);
            qm.warp(914090100, 0);
			qm.dispose();
    }
}

function end(mode, type, selection) {
    qm.dispose();
}