/* ==================
 �ű�����: NPC	    
 �ű���Ȩ����Ϸ���Ŷ�
 ��ϵ�ۿۣ�297870163    609654666
 =====================
*/
var status = 0;

function start() {
    status = -1;
    boat = cm.getEventManager("Boats");
    action(1, 0, 0);
}

function action(mode, type, selection) {
    status++;
    if(mode == 0) {
	cm.sendNext("��ò�ƽ�Ҳ���?");
	cm.dispose();
	return;
    }
    if (status == 0) {
	if(boat == null) {
	    cm.sendNext("����δ֪����");
	    cm.dispose();
	} else if(boat.getProperty("entry").equals("true")) {
	    cm.sendYesNo("����Ҫ���");
	} else if(boat.getProperty("entry").equals("false") && boat.getProperty("docked").equals("true")) {
	    cm.sendNext("�ܱ�Ǹ������Ĵ��Ѿ�����,����ʱ������ͨ����Ʊչ̨�鿴��");
	    cm.dispose();
	} else {
	    cm.sendNext("�ɴ����ǰ5������ֹͣ��Ʊ����ע��ʱ�䡣");
	    cm.dispose();
	}
    } else if(status == 1) {
	if(!cm.haveItem(4031045)) {
	    cm.sendNext("��!��û��#b#t4031045##k�����Ҳ��������ϴ�!.");
	} else {
	    cm.gainItem(4031045, -1);
	    cm.warp(101000301, 0);
	}
	cm.dispose();
    }
}