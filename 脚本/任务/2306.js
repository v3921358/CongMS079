/* 
	任務: 危機中的蘑菇王國
*/

var status = -1;

function start(mode, type, selection) {
    status++;
    if (mode != 1) {
        if (type == 1 && mode == 0) {
            status -= 2;
        } else {
            qm.sendOk("真的嗎??給你一段時間考慮，考慮好再來找我。");
            qm.dispose();
            return;
        }
    }
    if (status == 0) {
        if (qm.getMapId() == 180000001) {
            qm.sendOk("很遺憾，您因為違反用戶守則被禁止遊戲活動，如有異議請聯繫管理員.");
            qm.dispose();
        } else {
            qm.sendAcceptDecline("現在你的強大了許多，我有一件事情想找你幫忙，你是否願意聽聽？");
        }
    } else if (status == 1) {
        qm.sendNext("故事發生在蘑菇王國，具體的事情我也不太清楚。但是好像很緊急。");
    } else if (status == 2) {
        qm.sendNext("我不知道事情的細節，所以想找你幫幫忙，你可能會節省更多的時間幫助蘑菇王國，我送你一封信，請你把它交給門衛。 \r\n\r\n#fUI/UIWindow.img/QuestIcon/4/0#\r\n#v4032375# #t4032375#");
    } else if (status == 3) {
        qm.gainItem(4032375, 1);
        qm.forceStartQuest();
        qm.sendYesNo("如果你現在想去蘑菇城堡的話，我可以送你去。你確定要去嗎？");
    } else if (status == 4) {
        qm.warp(106020000);
        qm.dispose();
    }
}
function end(mode, type, selection) {
    status++;
    if (mode != 1) {
        if (type == 1 && mode == 0) {
            status -= 2;
        } else {
            qm.dispose();
            return;
        }
    }
    if (status == 0) {
        qm.sendNext("嗯？這個從轉職教官那裡得來的信件嗎？");
    } else if (status == 1) {
        qm.sendNextPrev("我看看……。");
    } else if (status == 2) {
        qm.sendNextPrev("好吧，既然你有轉職教官的推薦信，我想你是一個很棒的人，很抱歉我沒有自我介紹，我是包圍蘑菇城堡的衛兵，正如你所看到的，這裡是我們暫時的藏身之地，我們的情況很糟糕，儘管如此，歡迎你來到蘑菇王國！");
    } else if (status == 3) {
        qm.forceCompleteQuest();
        qm.gainItem(4032375, -1);
        qm.forceStartQuest(2312);
        qm.dispose();
    }
}