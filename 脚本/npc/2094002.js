var status = -1;

function action(mode, type, selection) {
    if (mode == 1) {
	status++;
    } else {
	status--;
    }
    if (cm.getPlayer().getMapId() == 925100700) {
	cm.removeAll(4001117);
	cm.removeAll(4001120);
	cm.removeAll(4001121);
	cm.removeAll(4001122);
	cm.warp(251010404,0);
	cm.dispose();
	return;
    }
    var em = cm.getEventManager("Pirate");
    if (em == null) {
	cm.sendNext("����δ֪�������Ժ����ԡ�");
	cm.dispose();
	return;
    }
    /*if (!cm.isLeader()) {
	cm.sendNext("��жӳ����ҶԻ���");
	cm.dispose();
	return;
    }*/
    switch(cm.getPlayer().getMapId()) {
	case 925100000:
	   cm.sendNext("�������ڽ��뺣���ڲ���������й��������ռ�6�ѹ�¥Կ�ף����ܿ�����һ�ء�");
	   cm.dispose();
	   break;
	case 925100100:
       cm.sendNext("��ϲ�������ڶ��أ����ռ�30 �� ���� �м� �߼� �������ߣ�����ͨ����һ�ء�");
	   break;
	case 925100200:
	   cm.sendNext("ͻ�����I�������Ǳ���������������");
	   cm.dispose();
	   break;
	case 925100300:
	   cm.sendNext("ͻ�����I�������Ǳ���������������");
	   cm.dispose();
	   break;
	case 925100201:
	   if (cm.getMap().getAllMonstersThreadsafe().size() == 0) {
		cm.sendNext("Excellent.");
		if (em.getProperty("stage2a").equals("0")) {
		    cm.getMap().setReactorState();
		    em.setProperty("stage2a", "1");
		}
	   } else {
	   	cm.sendNext("�������ڶ�ء�����һ��Ҫ������ǡ�");
		cm.dispose();
	   }
	   cm.dispose();
	   break;
	case 925100301:
	   if (cm.getMap().getAllMonstersThreadsafe().size() == 0) {
		cm.sendNext("���㡣");
		cm.dispose();
		if (em.getProperty("stage3a").equals("0")) {
		    cm.getMap().setReactorState();
		    em.setProperty("stage3a", "1");
		}
	   } else {
	   	cm.sendNext("�������ڶ�ء�����һ��Ҫ������ǡ�");
		cm.dispose();
	   }
	   cm.dispose();
	   break;
	case 925100202:
	case 925100302:
	   cm.sendNext("�������ڶ�ء�����һ��Ҫ������ǡ�");
	   cm.dispose();
	   break;
	case 925100400:
	   cm.sendNext("����Щ������Ȼ������ǵ�Կ�׷ŵ��ſھ����ˡ�");
	   cm.dispose();
	   break;
	case 925100500:
	   if (cm.getMap().getAllMonstersThreadsafe().size() == 0) {
		cm.warp(925100600);
		cm.dispose();
	   } else {
	   	cm.sendNext("���ܵ�ͼ�ϵ����й��");
		cm.dispose();
	   }
	   cm.dispose();
	   break;
    }
}