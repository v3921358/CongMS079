
var status = -1;
var text;
var sel;
var time;
var aaa ="#fUI/UIWindow.img/PvP/Scroll/enabled/next2#";
var ca = java.util.Calendar.getInstance();
var hour = ca.get(java.util.Calendar.HOUR_OF_DAY); //���Сʱ
var minute = ca.get(java.util.Calendar.MINUTE);//��÷���
var second = ca.get(java.util.Calendar.SECOND); //�����

// ÿ��������������ʱ��
var condition = new Array(30, 60, 120, 180, 240, 300, 360);
var reward = new Array(// �����š�����id������
					// 30
					Array(1, 2000005, 10), //����ҩˮ
					Array(1, 5072000, 1, 1),  //����5��
					Array(1, 3992025, 1),//ʥ������
					//Array(1, 5062000, 1),//����ħ��


					// 60
					Array(2, 2000005, 20),
					Array(2, 5072000, 2, 1),  //����5��
					Array(2, 3992025, 2),//ʥ������
					//Array(2, 5062000, 1),//����ħ��
					
					// 120
					Array(3, 2049401, 1),//Ǳ�ܾ�70%
					Array(3, 2000005, 30), //����ҩˮ
					Array(3, 5072000, 2, 1),  //����5��
					Array(3, 3992025, 3),//ʥ������
					//Array(3, 5062000, 1),//����ħ��

					// 180
					Array(4, 2000005, 50), //����ҩˮ3992025
					Array(4, 3992025, 5),//ʥ������
					Array(4, 4000463, 1),
					//Array(4, 5062000, 1),//����ħ��

					// 240
					Array(5, 2000005, 80), //����ҩˮ3992025
					Array(5, 2340000, 1),
					Array(5, 3992025, 5),//ʥ������
					Array(5, 4000463, 1),
					//Array(5, 5062000, 1),//����ħ��

					// 300
					Array(6, 2000005, 100), //����ҩˮ3992025
					Array(6, 2340000, 1),
					Array(6, 3992025, 5),//ʥ������
					Array(6, 4000463, 1),
					//Array(6, 5062000, 2),//����ħ��
					
					
					// 360
					Array(7, 2049400, 1),//�߼�Ǳ�ܾ�90%
					Array(7, 2000005, 100), //����ҩˮ3992025
					Array(7, 2340000, 1),
					Array(7, 3992025, 10),//ʥ������
					//Array(7, 4000464, 1),//�й���
					//Array(7, 5062000, 4),//����ħ��
					Array(7, 4000463, 2)


			);

function start() {
	action(1, 0, 0);
}

function action(mode, type, selection) {
	if (status == 0 && mode == 0)
	{
		cm.dispose();
		return;
	}
	if (mode == 1)
	{
		status++;
	} else {
		status--;
	}

	var time = cm.getGamePoints();//����ʱ�亯��
	var curlevel = -1;

	if (status == 0) {
		text = "#e#d��������[" + cm.getChannelServer().getServerName() + "]����ʱ��Ϊ�� #r" + time + "#k #d����#n#k\r\n#e#d��ʾ#n#k��#e#r23 �� 50#n #b��#r #e00 �� 10#n #bʱ�޷���ȡ���߽�����#k\r\n#b���� #e#r23��50#n#b ��ǰ��ȡ����δ��ȡ�Ľ��������������ʧ��#k\r\n\r\n";
		for (var i = 1; i <= condition.length; i++) {
			text += "#b#L" + i + "#"+aaa+" ��ȡ����" + condition[i-1] + "���ӽ���";
			if (cm.getBossLog("�������" + i) > 0) {
				text += "(����ȡ)";
				curlevel = curlevel == -1 ? i : curlevel;
			}
			text += "#l\r\n";
		}
		text += "#k";
		cm.sendSimple(text);
	} else if (status == 1) {
		// 23:50 ~ 23: 59 ǰһ�첻��ȡ��ʱ��  00:00 ~ 00:10 �ڶ��첻��ȡ��ʱ��  
		if ((hour == 23 && (minute >= 50 && minute <= 59)) || (hour == 0 && (minute >= 0 && minute <= 10))){
			cm.sendOk("#d��������ǰʱ�䣺 #r" + hour +" ʱ " + minute + " �� " + second + " ��#k\r\n\r\n#e#d��ʾ#n#k��#r23 �� 50 #b��#r 00 �� 10 #bʱ�޷���ȡ���߽�����#k");
			cm.dispose();
			return;
		}
		if (cm.getBossLog("�������" + selection) > 0) {
			cm.sendOk("���������Ѿ���ȡ����");
			cm.dispose();
			return;
		}
		sel = selection;
		text = "\t\t\t\t#e#r- ���� " + condition[selection - 1] + " ���ӽ��� -#k#n\r\n\r\n";
		for (var i = 0; i < reward.length; i++) {
			if (reward[i][0] == selection) {
				text += "\t\t\t#i" + reward[i][1] + "# #z" + reward[i][1] + "#[" + reward[i][2] + "��]\r\n";
			}
		}
		cm.sendYesNo(text);
	} else if (status == 2) {
		if (time < condition[sel-1]) {
			cm.sendOk("����ʱ�䲻�㣬�޷���ȡ��");
			cm.dispose();
			return;
		}
		var rewardlist = new Array();
		for (var i = 0; i < reward.length; i++) {
			if (reward[i][0] == sel) {
				if (reward[i][3] == null || reward[i][3] == '')
					reward[i][3]=0;
				rewardlist.push(new Array(reward[i][1], reward[i][2], reward[i][3]));
			}
		}
		if (!cm.canHoldSlots(rewardlist.length)) {
			cm.sendOk("�����ռ䲻�㣬��ȷ������ÿ����λ������ " + rewardlist.length + " ��ռ�");
			cm.dispose();
			return;
		}
		for (var i = 0; i < rewardlist.length; i++) {
			if (rewardlist[i][2] != 0) {
				//�����޵���
				cm.gainItemPeriod(rewardlist[i][0], rewardlist[i][1], rewardlist[i][2]);
				//java.lang.System.out.println("��");
			} else {
				//�����޵���
				cm.gainItem(rewardlist[i][0], rewardlist[i][1]);
			}
		}
		cm.setBossLog("�������" + sel);
		cm.playerMessage(1, "��ȡ�ɹ���");
		//cm.worldMessage(6,"��ң�["+cm.getName()+"] ��ȡ������ " + condition[sel-1] + " ���ӽ�����.");
		cm.ȫ����ɫ����("[����ʱ�佱��] : " + " : " + "��� " + cm.getChar().getName() + " ��ȡ������ " + condition[sel-1] + " ���ӽ�����")
		//cm.channelMessage(0x18, "������ʱ�佱����" + " : " + "��� " + cm.getChar().getName() + " ��ȡ������ " + condition[sel-1] + " ���ӽ�����");
		if (sel == 4) {
			cm.finishActivity(120108);
		} else if (sel == 5) {
			cm.finishActivity(120109);
		}
		cm.dispose();
	}
}