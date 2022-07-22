var status = 0;
var ca = java.util.Calendar.getInstance();
var year = ca.get(java.util.Calendar.YEAR); //������
var month = ca.get(java.util.Calendar.MONTH) + 1; //����·�
var day = ca.get(java.util.Calendar.DATE); //��ȡ��
var hour = ca.get(java.util.Calendar.HOUR_OF_DAY); //���Сʱ
var minute = ca.get(java.util.Calendar.MINUTE); //��÷���
var second = ca.get(java.util.Calendar.SECOND); //�����
var weekday = ca.get(java.util.Calendar.DAY_OF_WEEK);

var ��ʼʱ�� = "2019-12-10 18:00:00";//��ʼʱ��
var ����ʱ�� = "2019-12-20 23:00:00";//����ʱ��
var minLevel = 50;//Ҫ����С�ȼ�
var minOnlineTime = 20;//��Ҫ����ʱ��


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
			var text = "���������֣�#b2019��12��10����2019��12��22��#k֮�䣬���ǵ�¼��Ϸ����ң�����ʱ����ȼ��ﵽ��ӦҪ�󣬵������ϵ�#r10��10����10��45��#k���ڴ���ȡ����Ԫ��������\r\n";
			text += "#b#L2#�鿴�콱Ҫ��#l\r\n";
			text += "#b#L1#��Ҫ��ȡ���ս���#l";
			cm.sendSimple(text);
			//cm.dispose();
		} else if (status == 1) {
			if (selection == 2) {
				var text = "#d#e8��1�գ�#n#k\r\n";
				text += "\t����ʱ��ﵽ#r10����#k���ȼ��ﵽ#b120��#k������ȡԪ��#r10#kö��\r\n";

				text += "#d#e8��2�գ�#n#k\r\n";
				text += "\t����ʱ��ﵽ#r360����#k���ȼ��ﵽ#b150��#k������ȡԪ��#r10#kö��\r\n";

				text += "#d#e8��3�գ�#n#k\r\n";
				text += "\t����ʱ��ﵽ#r360����#k���ȼ��ﵽ#b180��#k������ȡԪ��#r10#kö��\r\n";

				text += "#d#e8��4�գ�#n#k\r\n";
				text += "\t����ʱ��ﵽ#r360����#k���ȼ��ﵽ#b200��#k������ȡԪ��#r10#kö��\r\n";

				text += "#d#e8��5�գ�#n#k\r\n";
				text += "\t����ʱ��ﵽ#r360����#k���ȼ��ﵽ#b220��#k������ȡԪ��#r20#kö��\r\n";

				text += "#d#e8��6�գ�#n#k\r\n";
				text += "\t����ʱ��ﵽ#r360����#k���ȼ��ﵽ#b230��#k������ȡԪ��#r30#kö��\r\n";

				text += "#d#e8��7�գ�#n#k\r\n";
				text += "\t����ʱ��ﵽ#r360����#k���ȼ��ﵽ#b240��#k������ȡԪ��#r80#kö��\r\n";
				status = -1;
				cm.sendSimple(text);
			} else if (selection == 1){
				cm.sendYesNo("��ע�⣬#r#eÿ̨����ͬһ�˺�ֻ����ȡһ�ν���#n#k���Ƿ����ھ���ȡ������");
			}
		} else if (status == 2) {
			if (month == 12 && day >= 10 && day <= 22 && (hour == 21 && (minute >= 10 && minute <= 59)) || hour == 22 && (minute >= 01 && minute <= 45)) {
				var points = 0;
				var level = 0;
				var onlineTime = 0;
				if (true) {
					switch (day) {
					case 1:
						points = 10;
						level = 10;
						onlineTime = 10;
						break;
					case 2:
						points = 10;
						level = 150;
						onlineTime = 360;
						break;
					case 3:
						points = 10;
						level = 180;
						onlineTime = 360;
						break;
					case 4:
						points = 10;
						level = 200;
						onlineTime = 360;
						break;
					case 5:
						points = 20;
						level = 220;
						onlineTime = 360;
						break;
					case 6:
						points = 30;
						level = 230;
						onlineTime = 360;
						break;
					case 7:
						points = 80;
						level = 240;
						onlineTime = 360;
						break;
					}
					//if (points==0) {
					//	cm.sendOk("��ȡ��������");
					//	cm.dispose();
					//	return;
					//}
					if (cm.getLevel() >= level && cm.getGamePoints() >= onlineTime) {
						if (getBossLog("�����弶���", 1)<=0) {
							if (cm.getBossLogAcc("�����弶���") < 1) {
								cm.setBossLogAcc("�����弶���");
								cm.gainItem(4000463, points);
								setBossLog("�����弶���", 1);
								cm.sendOk("��ȡ�ɹ�����ȡ��#b" + points + "#k ����");
								cm.dispose();
							} else {
								cm.sendOk("�������Ѿ���ȡ�����������ظ���ȡ��");
								cm.dispose();
							}
						} else {
							cm.sendOk("ÿ̨����ÿ��IPֻ����ȡһ�Σ��޷��ظ���ȡ��");
							cm.dispose();
						}
					} else {
						cm.sendOk("���ĵȼ�������ʱ�䲻���Ͻ�����ȡҪ�󡣾�����鿴�콱˵����");
						cm.dispose();
					}
				}
			} else {
				cm.sendOk("���ڲ�����ȡ��ʱ��Ŷ������#b2019��12��1����2019��12��22��#k֮���ÿ��10��10����10��45��֮�������ȡ�����ʱ����ˣ����޷���ȡ��Ŷ��");
				cm.dispose();
			}
		}
	}
}
function getBossLog(bossid, type) {
	if (type==null)
		type=1;
	var t = 'mac';
	if (type == 1)
		t = 'ipaddress';
	var tValue = (type==0) ? cm.getC().getMac() : cm.getC().getSessionIPAddress();
	if (tValue == "/127.0.0.1")
		return 0;
	var times = 0;
	var conn = cm.getConnection();
	var sql = "SELECT * FROM `BossLog` WHERE `bossid` = ? and `"+t+"`=? ";
	var pstmt = conn.prepareStatement(sql);
	pstmt.setString(1, bossid);
	pstmt.setString(2, tValue);
	var result = pstmt.executeQuery();
	if (result.next()) {
		var time = result.getString('time');
		var lastTimestamp = time.substring(0, 10);
		lastTimestamp +=" 00:00:00";
		lastTimestamp = java.sql.Timestamp.valueOf(lastTimestamp).getTime();
		var dayTimestamp = year+"-"+month+"-"+day+" 00:00:00";
		dayTimestamp = java.sql.Timestamp.valueOf(dayTimestamp).getTime();
		if (lastTimestamp == dayTimestamp) {
			times = result.getInt('count');
		} else {
			sql = "UPDATE `BossLog` SET `count` = 0, `time` = CURRENT_TIMESTAMP where `bossid`=? and `"+t+"`=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bossid);
			pstmt.setString(2, tValue);
			pstmt.executeUpdate();
			times = 0;
		}
	} else { 
		sql = "INSERT INTO `BossLog`(`bossid`,`"+t+"`,`count`) values(?,?,0)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, bossid);
		pstmt.setString(2, tValue);
		pstmt.executeUpdate();
		times = 0;
	}
	result.close();
	pstmt.close();
	return times;
}
function setBossLog(bossid, type) {
	if (type==null)
		type=1;
	var t = 'mac';
	if (type == 1)
		t = 'ipaddress';
	var tValue = (type==0) ? cm.getC().getMac() : cm.getC().getSessionIPAddress();
	var times = getBossLog(bossid, type);
	var conn = cm.getConnection();
	sql = "UPDATE `BossLog` SET `count` = ?, `time` = CURRENT_TIMESTAMP where `bossid`=? and `"+t+"`=?";
	pstmt = conn.prepareStatement(sql);
	pstmt.setInt(1, times+1);
	pstmt.setString(2, bossid);
	pstmt.setString(3, tValue);
	pstmt.executeUpdate();
}