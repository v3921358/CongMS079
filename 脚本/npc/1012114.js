/*
 ��Ҷ����ð�յ�(079)��Ϸ�����
 �ű�������
 */

//ͨ���������
var ͨ������ = 10;
var ͨ�ؾ��� = 30000;


var status = -1;

function action(mode, type, selection) {
    if (mode == 0 && status == 0) {
        cm.�Ի�����();
        return;
    }
    if (mode == 1)
        status++;
    else
        status--;
    if (status == 0) {
        cm.sendSimple("���ռ����ӷ��ڵؿ�����������6������ȫ������ʱ���½����֡������»��ٻ�����С���ӣ�ÿ��һ��ʱ������С���ӻᵷ����⣬�ռ� #r10 #k�����󽻸��ӳ�Ȼ�󽻸�NPC������ͨ�ء�\r\n#rע�����������ӵ�����ʱ�򱣻������������ӱ����﹥��������������ʧ��#b\r\n#L0##v4001101# x " + ͨ������ + " ͨ��#l\r\n#L1##v4001101# x 20 �� #v1002798##l#k\r\n\r\n#L3##r������뿪��#l");
    } else if (status == 1) {
        if (selection == 0) {
            if (!cm.isLeader()) {
                cm.sendNext("ֻ�жӳ������Ҳ�Ҫ��");
                cm.�Ի�����();
            } else {
                if (cm.haveItem(4001101, ͨ������)) {
                    cm.gainItem(4001101, -ͨ������);
					
					cm.removeAll(4001095);//�۳�����ȫ�������Ʒ
					cm.removeAll(4001096);//�۳�����ȫ�������Ʒ
					cm.removeAll(4001097);//�۳�����ȫ�������Ʒ
					cm.removeAll(4001098);//�۳�����ȫ�������Ʒ
					cm.removeAll(4001099);//�۳�����ȫ�������Ʒ
					cm.removeAll(4001100);//�۳�����ȫ�������Ʒ
					
                    cm.showEffect(true, "quest/party/clear");
                    cm.playSound(true, "Party1/Clear");
                    cm.givePartyExp(ͨ�ؾ���);
					cm.endPartyQuest(1200);
                    cm.warpParty(910010100);
                    
                    cm.worldMessage(2, "[����-����] : ��ϲ " + cm.getPlayer().getName() + " ������飬����������");
                    cm.�Ի�����();
                } else {
                    cm.sendNext("��û����Ⱑ��");
                    cm.�Ի�����();
                }
            }
        } else if (selection == 1) {
            if (cm.haveItem(1002798, 1)) {
                cm.sendOk("���Ѿ�����");
            } else if (!cm.canHold(1002798, 1)) {
                cm.sendOk("���Ѿ�����");
            } else if (cm.haveItem(4001101, 20)) {
                cm.gainItem(4001101, -20); 
                cm.gainItem(1002798, 1);
            } else {
                cm.sendOk("����Ҫ20�������Ԫ��");
            }
            cm.�Ի�����();
        } else if (selection == 3) {
			cm.removeAll(4001101);//�۳�����ȫ�������Ʒ
			cm.removeAll(4001095);//�۳�����ȫ�������Ʒ
			cm.removeAll(4001096);//�۳�����ȫ�������Ʒ
			cm.removeAll(4001097);//�۳�����ȫ�������Ʒ
			cm.removeAll(4001098);//�۳�����ȫ�������Ʒ
			cm.removeAll(4001099);//�۳�����ȫ�������Ʒ
			cm.removeAll(4001100);//�۳�����ȫ�������Ʒ
            cm.warp(100000200);
			cm.�Ի�����();
        }
    }
}