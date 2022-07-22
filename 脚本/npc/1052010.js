var status = 0;


function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1 || mode == 0) {
        cm.dispose();
    } else {
        status++;
        if (status == 0) {
            cm.sendSimple("嗨，我是#p9000003#，需要什麼幫忙嗎？？\r\n#L0#我拿到寶箱了。");
        } else if (status == 1) {
            if (cm.getQianDaoAcLog("箱子活動") < 3) {
                if (cm.haveItem(4001102)) {
                    cm.setAcLog("箱子活動");
                    cm.gainItem(4001102, -1);
                    cm.giveEventPrize();
                    cm.dispose();
                } else {
                    cm.sendOk("你沒有#b#t4001102##k，你來找我做什麼？？");
                    cm.dispose();
                }
            } else {
                cm.sendOk("你今天已經兌換過3次寶箱。");
                cm.dispose();
                return;
            }
            cm.dispose();
        }
    }
}

function getEimForGuild(em, id) {
    var stringId = "" + id;
    return em.getInstance(stringId);
}