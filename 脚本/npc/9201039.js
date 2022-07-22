var status = 0;
var mhair = Array(30270, 30240, 30020, 30000, 30132, 30192, 30032, 30112, 30162);
var fhair = Array(31150, 31250, 31310, 31050, 31050, 31030, 31070, 31091, 31001);
var hairnew = Array();

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 0 && status == 0) {
        cm.sendNext("好吧，如果準備好隨時來找我談談。");
        cm.dispose();
        return;
    }
    if (mode == 1)
        status++;
    else
        status--;
    if (status == 0) {
        if (cm.getQuestStatus(8860) == 2 && !cm.haveItem(4031528)) {
            cm.sendNext("請確認是否做好了準備。");
            cm.dispose();
        } else {
            cm.sendYesNo("準備要剪一個漂亮的髮型了？？");
        }
    }
    if (status == 1) {
        hairnew = Array();
        if (cm.getChar().getGender() == 0) {
            for (var i = 0; i < mhair.length; i++) {
                hairnew.push(mhair[i]);
            }

        }
        if (cm.getChar().getGender() == 1) {
            for (var i = 0; i < fhair.length; i++) {
                hairnew.push(fhair[i]);
            }
        }
        cm.sendNext("那來吧～！");
    }
    if (status == 2) {
        if (cm.haveItem(4031528)) {
            cm.gainItem(4031528, -1);
            cm.setHair(hairnew[Math.floor(Math.random() * hairnew.length)]);
            cm.sendBackNext("享受！");
            cm.dispose();
        } else {
            cm.sendNext("嗯...你確定有我們的免費優惠券？");
            cm.dispose();
        }
    }
}
