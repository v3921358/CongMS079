var status = 0;
var bossid = "�������";
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
		if (month==3 && day==1 && hour==20 && (minute>=40 && minute <=59)) {
			text += "2��28�ղ������������3��1����8��40�ֵ�9��֮��ǰ����ȡ��\r\n";
			text+="#b#L1#��ȡ�������#l\r\n";
			//text+="#b#L2#��ȡ360���ӽ�����15000����15000����ȯ��#l";
			cm.sendSimple(text);
		} else {
			cm.sendOk("��Ѿ�����");
			cm.dispose();
		}
	} else if (status == 1) {
		typed = selection;
		cm.sendYesNo("�Ƿ����ھ���ȡ���������ÿ���˺�ֻ����ȡһ�Σ����ҽ�ɫ�ȼ���Ҫ���ڵ���160����");
	} else if (status == 2) {
		
		var needtime = 120;
		
		if (cm.getPlayer().getTodayOnlineTime()>=needtime) {
			if (cm.getBossLogAcc(bossid+typed)!=-1) {
				cm.setBossLogAcc(bossid+typed, -2);
				cm.gainItem(5062009, 10);
				cm.gainItem(5062500, 10);
				cm.gainNX(1, 10000);
				cm.gainNX(2, 50000);
				cm.sendOk("��ȡ�ɹ����������10����������ħ����10����ʦ��������ħ����10000����50000����ȯ��");
				cm.dispose(); 
			} else {
				cm.sendOk("��ȡʧ�ܣ����Ѿ���ȡ����");
				cm.dispose();
			}
		} else {
			cm.sendOk("��������ʱ�䲻��"+needtime+"���ӣ�");
			cm.dispose();
		}
	}
}