var status = 0;
var skin = Array(0, 1, 2, 3, 4);
var price;

function start() {
    cm.sendSimple("歡迎來到101大道護髮中心!，我是#p9330054# 您好~幸會 是否有想要染的皮膚呢?? 就像我的健康皮膚??  如果你有 #b#t5153008##k, 就可以隨意染的想到的皮膚~~~\r\n\#L2#我已經有一個#t5153008#!#l");
}

function action(mode, type, selection) {
    if (mode < 1)
        cm.dispose();
    else {
        status++;
        if (status == 1)
            cm.sendStyle("選一個想要的風格.", skin);
        else {
            if (cm.haveItem(5153008)){
                cm.gainItem(5153008, -1);
                cm.setSkin(selection);
                cm.sendOk("享受!");
            } else
                cm.sendOk("您貌似沒有#b#t5153008##k..");
            cm.dispose();
        }
    }
}
