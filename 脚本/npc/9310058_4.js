var status = 0;
var bossid = "������";
var ca = java.util.Calendar.getInstance();
var year = ca.get(java.util.Calendar.YEAR); //������
var month = ca.get(java.util.Calendar.MONTH) + 1; //����·�
var day = ca.get(java.util.Calendar.DATE); //��ȡ��
var hour = ca.get(java.util.Calendar.HOUR_OF_DAY); //���Сʱ
var minute = ca.get(java.util.Calendar.MINUTE); //��÷���
var second = ca.get(java.util.Calendar.SECOND); //�����
var weekday = ca.get(java.util.Calendar.DAY_OF_WEEK);
function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (status == 0 && mode == 0) {
        cm.dispose();
        return;
    }
    if (mode == 1) {
        status++;
    } else {
        status--;
    }
    if (status == 0) {
		var text = "";
		if ((month==8 || month==8) && (day == 1 || (day >=14 && day <=20))) {
			//text += "7��31����8��6��֮�䣬ÿ������ڴ˴���ȡһ���Ľ�����\r\n";
			text += "8��14����8��20��֮�䣬ÿ������ڴ˴���������ʱ����ȡ���͵���ȯ������\r\n";
			text+="#b#L1#��ȡ180���ӽ�����2000���þ�#l\r\n";
			text+="#b#L2#��ȡ360���ӽ�����5000����ȯ��#l\r\n";
			text+="#b#L3#��ȡ600���ӽ�����10000���þ�#l\r\n";
			text+="#b#L4#��ȡ720���ӽ�����10000��ȯ��10000���þ�#l\r\n";
			cm.sendSimple(text);
		} else {
			cm.sendOk("��Ѿ��������߻δ��������鿴����������ʱ��");
			cm.dispose();
		}
	} else if (status == 1) {
		typed = selection;
		cm.sendYesNo("�Ƿ����ھ���ȡ�������ÿ���˺�ֻ����ȡһ�Σ����ҽ�ɫ�ȼ���Ҫ���ڵ���150����");
	} else if (status == 2) {
		var points = 0;
		var nxpoints = 0;
		var needtime = 0;
		var level = 150;
		if (typed==1) {
			points = 0;
			nxpoints = 2000;
			needtime = 180;
		} else if (typed==2){
			points = 0;
			nxpoints = 5000;
			needtime = 300;
		} else if (typed==3){
			points = 0;
			nxpoints = 10000;
			needtime = 600;
		} else if (typed==4){
			points = 10000;
			nxpoints = 10000;
			needtime = 720;
		}
		if (cm.getPlayer().getTodayOnlineTime()>=needtime) {
			if (cm.getBossLogAcc(bossid+typed)==0) {
				cm.setBossLogAcc(bossid+typed);
				cm.gainNX(1, points);
				cm.gainNX(2, nxpoints);
				//cm.gainRMB(200);
				cm.sendOk("��ȡ�ɹ���");
				//cm.channelMessage(0x20, "����������" + " : " + "��� " + cm.getChar().getName() + " ��ȡ������ " + condition[sel-1] + " ���ӵĵ�����������");
				cm.dispose(); 
			} else {
				cm.sendOk("��ȡʧ�ܣ��������Ѿ���ȡ����");
				cm.dispose();
			}
		} else {
			cm.sendOk("��������ʱ�䲻��"+needtime+"���ӣ�");
			cm.dispose();
		}
	}
}