var status = 0;
var typed = 0;
var currenttimes;
var maxGifts = 18;

function start() {
	status = -1;
	action(1, 0, 0);
}

function action(mode, type, selection) {
	if (mode == -1) {
		cm.dispose();
	} else {
		if (mode == 0 && status == 0) {
			cm.dispose();
			return;
		}
		if (mode == 1)
			status++;
		else
			status--;
		if (status == 0) {
			var currentTimestamp = java.lang.System.currentTimeMillis();
			var ca = java.util.Calendar.getInstance();
			ca.set(2015, 8 - 1, 9, 21, 10, 0); //���ÿ���ʱ�� �·�Ҫ-1���ǵ�ǰ�·�
			var startTimestamp = ca.getTimeInMillis();
			if (currentTimestamp < startTimestamp) {
				var lasttime = (startTimestamp - currentTimestamp) / (60 * 1000);
				cm.sendOk("����������ڽ���" + ca.get(java.util.Calendar.HOUR_OF_DAY) + ":" + ca.get(java.util.Calendar.MINUTE) + "��ʼ���ţ����η���#r18#k�������������ʼ����#r" + Math.floor(lasttime) + "#k���ӣ�ץ��ʱ��Ѱ�������ɾ��ɣ������λ����׼����");
				cm.dispose();
				return;
			}
			var queryCurrenttimes = cm.getConnection().prepareStatement("select currenttimes from gamegift where id = 1;").executeQuery();
			if (queryCurrenttimes.next()) {
				currenttimes = queryCurrenttimes.getString("currenttimes");
			} else {
				currenttimes = 0;
			}
			//var text = "";
			var text = "#e#b����ϲ�����#n#k\r\n\r\n��ʣ#r" + (maxGifts - currenttimes) + "#k�������ÿ���˺�ֻ����ȡһ�Σ�������ȡ�ɣ�\r\n��ȡǰ��ȷ�����İ������㹻�Ŀռ�\r\n";
			text += "#b#L1#��Ҫ��ȡ#l";
			if ((maxGifts - currenttimes) <= 0) {
				text = "�汧Ǹ���������ˣ�����Ѿ�������ˡ�";
			}
			cm.sendSimple(text);
			//cm.dispose();
		} else if (status == 1) {
			if (cm.getBossLogAcc("��ϲ���") == -1) {
				cm.sendOk("�Բ������Ѿ��������ˡ�");
				cm.dispose();
				return;
			}
			var queryCurrenttimes = cm.getConnection().prepareStatement("select currenttimes from gamegift where id = 1;").executeQuery();
			if (queryCurrenttimes.next()) {
				currenttimes = queryCurrenttimes.getString("currenttimes");
			} else {
				currenttimes = 0;
			}
			if (currenttimes >= maxGifts) {
				cm.sendOk("�汯�磬��������ԥ����һ˲�䣬����Ѿ���һ�����ա�");
				cm.dispose();
				return;
			}
			var text = "���ɹ���ȡ��������Ʒ��\r\n#b";
			/*if (currenttimes <= 0) {
				cm.gainItem(3010715, 1);
				text += "�Ҹ�9���군�������Σ�";
			}
			if (currenttimes <= 2) {
				cm.gainItem(1003843, 1);
				text += "��ֵĺ�����ߣ�";
			}*/
			if (currenttimes <= 18) {
				//cm.gainItem(2049323, 10);
				//cm.gainItem(2049137, 10);
				//cm.gainItem(2431354, 1);
				cm.gainItem(5062009, 10);
				cm.gainItem(5062500, 10);
				//cm.gainItem(4310036, 1000);
				cm.gainItem(4001714, 10);
				cm.gainItem(4001893, 1000);
				cm.gainNX(2, 20000);
				cm.gainNX(1, 10000);
				//text += "�������������� 40%x10������߼�װ��ǿ����x10���ǻ���������x1������ħ��x10����ʦ����ħ��x10��������x1000�����ӽ�100W���x20������x1000�����þ�x10000�����x5000";
				text += "����ħ��x10����ʦ����ħ��x10�����ӽ�100W���x10������x1000�����þ�x20000�����x10000";
			}
			cm.getConnection().prepareStatement("update gamegift set currenttimes = currenttimes +1 where id = 1;").executeUpdate();
			cm.setBossLogAcc("��ϲ���", -2);
			cm.sendOk(text);
			cm.worldSpouseMessage(0x24, "[ϵͳ����] : ��ϲ��ҡ�" + cm.getChar().getName() + "�������˵�" + (currenttimes + 1) + "�������������������Ľ��");
			cm.dispose();
		}
	}
}