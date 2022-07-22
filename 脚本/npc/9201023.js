/*
 NPC Name: 		Hera
 Map(s): 		Towns
 Description: 		Wedding Village Entrance
 */

var status = -1;

function start() {
    cm.sendSimple("啊~今天真是個好日子！這世界太美好了~！你不覺得這世界充滿了愛嗎？滿溢婚禮村的愛意都流淌到這裡來了~！ \n\r #b#L0# 我想要去結婚小鎮.#l");
}

function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        cm.sendOk("你居然要放棄這麼好的機會？那裡真的很美~。你不會是還沒遇到心愛的人吧？沒錯，如果你有心愛的人，怎麼會對這麼浪漫的消息聽而不聞呢！！");
        cm.dispose();
        return;
    }
    if (status == 0) {
        cm.sendNext("哦！多麼美好的一天！這個世界是多麼的美好〜！這個世界似乎是充滿愛的，不是嗎？我可以從這裡感受到愛的精神填補了婚禮!");
    } else if (status == 1) {
        cm.sendYesNo("你曾經去過的婚禮村莊？這是一個了不起的地方，愛情是無極限的。恩愛夫妻可以結婚還有，如何浪漫它是什麼？如果你想在那裡，我會告訴你的方式.");
    } else if (status == 2) {
        cm.sendNext("你做了一個正確的決定！你可以感受到愛的精神在婚禮村發揮到淋漓盡致。當你想回來，你的目的地將在這裡，所以不要擔心.");
    } else if (status == 3) {
        cm.saveLocation("AMORIA");
        cm.warp(680000000, 0);
        cm.dispose();
    }
}