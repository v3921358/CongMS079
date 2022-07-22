// 卡勒塔

function start() {
    if (cm.getQuestStatus(6301) == 1) {
        if (cm.haveItem(4000175)) {
            cm.gainItem(4000175, -1);
            if (cm.getParty() == null) {
                cm.warp(923000000)
            } else {
                cm.warpParty(923000000)
            }
        } else {
            cm.sendOk("為了維持海底的生態您必須要有#b#t4000175##k才能進入洞穴。");
        }
    } else {
        cm.sendOk("嗨，您好 我是#b#p2060100##k請不要跟我說一些愚蠢的話題, 因為我知道我有把人變成愚蠢的習慣。");
    }
    cm.dispose();
}