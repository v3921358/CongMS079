/*
 ZEVMSð�յ�(079)��Ϸ�����
 �ű�������ɭ��
 */
var status = -1;

function action(mode, type, selection) {
	var next = true;
	var size = 0;
	var it = cm.getPlayer().getParty().getMembers().iterator();
    if (mode == 1) {
	status++;
    } else {
	status--;
    }
    switch(cm.getPlayer().getMapId()) {
	case 930000500:
	    if (!cm.haveItem(4001163)) {
	    	cm.sendNext("��#b#t4001163##k�������ҡ�");
	    } else {
			while (it.hasNext()) {
				var cPlayer = it.next();
				var ccPlayer = cm.getPlayer().getMap().getCharacterById(cPlayer.getId());
				if (ccPlayer == null) {
					next = false;
					break;
				}
				size++;
			    }
	            if (next && size >= 1) {
				cm.warpParty(930000600);
                cm.dispose();
				} else {
				cm.sendOk("��ϲ�����Ѿ���#b#t4001163##k�����Ƕ����Ա����û�����Ҫ��֤��Ա���ڲſ��Խ�����һ��");
				cm.dispose();
			    }
                
	    }
	    break;
    }
    cm.dispose();
}