var dh;
var entry = true;

function start() {
    dh = cm.getEventManager("DollHouse");
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {

    if (mode == -1) {
        cm.dispose();
    } else {
        if (mode == 0 && status == 0) {
            cm.sendNext("我明白。這是非常可以理解的，考慮到你將面臨內部一個非常危險的怪物的事實。如果你覺得心臟的改變，那麼請你跟我說話。我確信可以從別人像你使用的幫助。");
            cm.dispose();
            return;
        } else if (mode == 0 && status == 2) {
            cm.sendNext("我明白。這是非常可以理解的，考慮到你將面臨內部一個非常危險的怪物的事實。如果你覺得心臟的改變，那麼請你跟我說話。我確信可以從別人像你使用的幫助。");
            cm.dispose();
            return;
        }
        if (mode == 1) {
            status++;
        } else {
            status--;
        }
        if (cm.getQuestStatus(3230) == 1) {
            if (status == 0) {
                cm.sendYesNo("嗯...我是 #b#p2040001##k. 我聽說過很多關於你的事情... 你能幫我找回 #b#t4031093##k 嗎拜託了!");
            } else if (status == 1) {
                cm.sendNext("非常感謝。其實, #b#p2040001##k 問你拿  #b#t4031093##k 為測試自己的能力，看看你是否能處理這個問題，所以不要把它當做一個隨機請求的方式。我覺得你這樣的人能處理好逆境.");
            } else if (status == 2) {
                cm.sendYesNo("前段時間，一個怪物來到這裡從另一個層面得益於尺寸的裂縫，並偷走了鐘擺。它躲在自己的房間裡那邊偽裝成一個玩具屋。這一切看起來是一樣的我，所以沒有辦法找到它。你會幫助我們找到它？");
                if (dh != null && dh.getProperty("noEntry") != null && dh.getProperty("noEntry").equals("true")) {
                    entry = false;
                }
            } else if (status == 3) {
                cm.sendNext("好的，我會帶你到另一個房間，那邊有許多相同的玩具屋，不過你仔細看會發現有所不同，你的任務是打破真正的玩具屋然後把#t4031094#帶回來給我。.");
            } else if (status == 4) {
                cm.sendNextPrev("你需要在時間內找到#t4031094# 然後像我回報。");
            } else if (status == 5) {
                if (dh == null || entry == false) {
                    cm.sendPrev("玩具屋內好像有人了。我只能讓一個人在裡面，所以請你等待吧。");
                } else {
                    cm.removeAll(4031093);
                    dh.startInstance(cm.getChar());
                }
                cm.dispose();
            }
        } else if (cm.getQuestStatus(3230) == 2) {
            cm.sendNext("謝謝你 #h #, 我們得到了 #b#t4031094##k 回並銷毀從另一個邪惡的怪物。值得慶幸的是，我們還沒有找到一個這樣的，因為。我不知道怎麼感謝你幫助我們這麼多。希望你在#m220000000#過得愉快!");
            cm.dispose();
        } else {
            cm.sendOk("我們在這裡守衛這個房間的玩具士兵，防止任何人進入這個地方。");
            cm.dispose();
        }
    }
}