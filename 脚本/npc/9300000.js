/* Dawnveil
    To Victoria Island
	Puro
    Made by Daenerys
*/
function start() {
    cm.sendYesNo("我可以直接送你去红鸾宫,需要10万金币,你想去吗？");
}

function action(mode, type, selection) {
    
    if(cm.getPlayer().getMeso() >= 100000) {
	cm.gainMeso(-100000);
	cm.saveLocation("MULUNG_TC");
	cm.warp(700000000,0);
	cm.dispose();
    }else {
	cm.sendOk("恩... 看起来你并没有#b10#k万金币，这样我可帮不了你。");
	cm.dispose();
	}

}