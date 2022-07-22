/* Dawnveil
    To Rien
	Puro
    Made by Daenerys
*/
function start() {
    cm.sendYesNo("你想要去耶雷佛必須支付#b 500 楓幣#k 到那邊約一分鐘.");
}

function action(mode, type, selection) {
    if (mode == 0) {
        cm.sendOk("等你考慮好再來找我吧!");
        cm.dispose();
    } else {
        if (cm.getPlayer().getMeso() >= 500) {
            cm.gainMeso(-500);
            cm.warpBack(200090032, 130000210, 80);
        }
        cm.dispose();
    }
}