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

function start(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        if (status == 0) {
            qm.sendNext("这真的很紧急，如果你拒绝，你会后悔. #b它与你的极手臂有关,#k这意味着它与你的过去有关。 谁知道...？ 也许杆臂是唤醒你的能力的关键...?");
            qm.dispose();
            return;
        }
        status--;
    }
    if (status == 0) {
        qm.askAcceptDecline("培训如何进行？ 哇，看着你，我可以告诉你的水平射过屋顶。 这是惊人的...好吧，反正，我看到你的忙，但你必须回到岛上一点");
    } else if (status == 1) {
        qm.forceStartQuest(21200, "3");
        qm.sendOk("#b保管在#m140000000##k的你的#b#p1201001##k突然出现了奇怪的反应。据说长矛在呼唤自己主人的时候才会发出那样的反应。#b也许有什么事情要转达给你？#k请速回岛上一趟吧。");
        qm.dispose();
    }
}

function end(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        if (status == 11) {
            qm.sendNext("嘿，至少你告诉我你试过!");
            qm.dispose();
            return;
        }
        status--;
    }
    if (status == 0) {
        qm.sendNextS("嗡嗡嗡嗡嗡……", 2);
    } else if (status == 1) {
        qm.sendNextPrevS("#b（#p1201001#在發出嗡鳴聲。奇怪，那邊的少年是誰？）#k", 2);
    } else if (status == 2) {
        qm.sendNextPrevS("#b（以前沒見過他啊？怎麼看起來不太像人類？）#k", 2);
    } else if (status == 3) {
        qm.sendNextPrev("喂！狂狼勇士！還聽不見我的聲音嗎？到底聽不聽得見？唉，煩死了！");
    } else if (status == 4) {
        qm.sendNextPrevS("#b（咦？這是誰的聲音？怎麼聽起來像個凶巴巴的少年……）#k", 2);
    } else if (status == 5) {
        qm.sendNextPrev("唉……哪有這樣的主人啊？丟開武器在冰窟裡睡了幾百年，現在連話都聽不懂了……");
    } else if (status == 6) {
        qm.sendNextPrevS("你是誰啊？", 2);
    } else if (status == 7) {
        qm.sendNextPrev("啊，狂狼勇士？現在聽到我的聲音了？是我啊，不記得我了？我就是武器#b長矛 #p1201002##k啊？");
    } else if (status == 8) {
        qm.sendNextPrevS("#b（……#p1201002#？#p1201001#會說話？）#k", 2);
    } else if (status == 9) {
        qm.sendNextPrev("不至於吧？這麼吃驚？再怎麼失憶，總不能連我都忘了吧？太不夠意思了！");
    } else if (status == 10) {
        qm.sendNextPrevS("不好意思，真的一点都想不起來。", 2);
    } else if (status == 11) {
        qm.sendYesNo("请速回岛上一趟吧说声不好意思就能算了？！几百年来就我一个人孤苦伶仃地，有多寂寞你知道吗？不管怎样，你快点给我想起来！");
    } else if (status == 12) {
        qm.sendNextS("#b（一口一個自己是#p1201001#、#p1201002#的，還越說越生氣了。再這麼說下去也不會有啥進展，還是先走到 #p1201000#跟前，好好商量商量。）#k", 2);
        qm.forceCompleteQuest();
    } else if (status == 13) {
        qm.MovieClipIntroUI(true);
		qm.warp(914090200, 0);
        qm.dispose();
    }
}