/* Dawnveil
    To Victoria Island
	Puro
    Made by Daenerys
*/
function start() {
    cm.sendYesNo("�ҿ���ֱ������ȥ��𽹬,��Ҫ10����,����ȥ��");
}

function action(mode, type, selection) {
    
    if(cm.getPlayer().getMeso() >= 100000) {
	cm.gainMeso(-100000);
	cm.saveLocation("MULUNG_TC");
	cm.warp(700000000,0);
	cm.dispose();
    }else {
	cm.sendOk("��... �������㲢û��#b10#k���ң������ҿɰﲻ���㡣");
	cm.dispose();
	}

}