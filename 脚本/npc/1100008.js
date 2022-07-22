/* Dawnveil
    To Rien
	Puro
    Made by Daenerys
*/
function start() {
    cm.sendYesNo("你想要去耶雷弗必須支付#b 5000 楓幣#k 大概一分鐘...");
}

function action(mode, type, selection) {
    if (mode == 0) {
	cm.sendOk("等你考慮好再來找我吧!");
	cm.dispose();
	} else {
    if(cm.getPlayer().getMeso() >= 5000) {
	cm.gainMeso(-5000);
	cm.warpBack(200090020,130000210,80);
    }
    cm.dispose();
}
}