﻿var CY0 = "┣━━━━━━━━━━━━━━ ━━━━━━━━━━━━━━━━┫";
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

function start(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        if (status == 2) {
            qm.dispose();
            return;
        }
        status--;
    }
    if (status == 0) {
        qm.sendNextS("修炼进行的不错吧？企鹅老师#p1202006#个性很强，我还担心他要是痴呆了就不好办了……他对英雄的技能确实很有研究，对你应该能帮上不少忙。", 8);
    } else if (status == 1) {
        qm.sendNextPrevS("#b(告诉他自己回想起来连击能力这个技能。)#k", 2);
    } else if (status == 2) {
        qm.askAcceptDecline("是吗！看来除了#p1202006#的训练方式之外，你自己仍然记的从前的那些技能也很关键啊……看来只是在冰窖里封冻的太久，需要时间恢复而已。#b继续加油修炼吧，争取早日恢复所有的技能！#k  \r\n\r\n#fUI/UIWindow.img/QuestIcon/8/0# 500 exp");
    } else if (status == 3) {
        qm.forceCompleteQuest();
        qm.gainExp(500);
        qm.dispose();
    }
}

function end(mode, type, selection) {
    qm.dispose();
}