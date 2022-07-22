var status = -1;

function start(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        if (status == 0) {
            qm.sendNext("有很紧急的事情。要是拒绝的话，肯定会后悔的哦？#b有关你长矛的事情#k，也就是有关你的过去。谁知道呢？……说不定这个长矛能够唤醒你的能力？");
            qm.dispose();
            return;
        }
        status--;
    }
    if (status == 0) {
        qm.askAcceptDecline("修练还顺利吗？我知道你很忙，很抱歉打扰你，不过请快点跟我回来#b瑞恩村#k。#b玛哈#k又有了奇怪的反应…好奇怪。跟之前的反应不一样。好像更深沉更黑暗…我有这样的感觉");
    } else if (status == 1) {
        qm.forceStartQuest(21400);
        qm.dispose();
    }
}

function end(mode, type, selection) {

}