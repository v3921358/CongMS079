var FY0 = "��������������������������";
var FY1 = "��       - ��Ҷ -       ��";
var FY2 = "�� �ű�����  �����ƽű� ��";
var FY3 = "�� ����֧�� �� ��Ϸ���� ��";
var FY4 = "�� �ף���ӡ�  ��ͼ���� ��";
var FY5 = "�ǩ�����������������������";
var FY6 = "�� ΨһQQ:1848350048    ��";
var FY7 = "��������������������������";

var MapleInventoryManipulator = Java.type('server.MapleInventoryManipulator');
var MapleItemInformationProvider = Java.type('server.MapleItemInformationProvider');
var DatabaseConnection = Java.type('database.DatabaseConnection');

var status = 0;
var ringnum = 0;
var id = 0;
var itemIndex;
var itemList = Array(//Array(id,Num)
Array(4000000,100),Array(4000001,100),Array(4000002,100),//����ţ�ǣ���Ģ���ǣ�������
Array(4000003,100),Array(4000004,100),Array(4000005,100),//��֦����Ҷ��Ҷ��
Array(4000006,100),Array(4000007,100),Array(4000008,100),//��������Ĵ��֣��������֮β������
Array(4000009,100),Array(4000010,90),Array(4000011,100),//��Ģ���ǣ���ˮ���飬Ģ��ѿ�� 
Array(4000012,100),Array(4000013,100),Array(4000014,100),//��Ģ���ǣ��������֮β������ͷ��
Array(4000015,100),Array(4000016,100),Array(4000018,100),//��Ģ���ǣ�����ţ�ǣ�ľ��
Array(4000019,80),Array(4000026,100),Array(4000029,100),//����ţ�ǣ��������ޣ��㽶
Array(4000031,100),Array(4000032,80),Array(4000034,100),//�������ޣ�����Ƥ����Ƥ
Array(4000035,100),Array(4000036,100),Array(4000037,100),//�����������ҩ����ˮ���ˮ��
Array(4000039,100),Array(4000042,100),Array(4000043,100),//�������㣬�����򣬺��зǯ
Array(4000044,100),Array(4000045,100),Array(4000048,100),//���зǯ���ڹ�ǣ�С��ѩ��Ƥ

Array(4000051,80),Array(4000052,80),Array(4000053,10),//Ұ��֮β������֮β�����˵Ľ�ֺ��
Array(4000054,10),Array(4000059,100),Array(4000060,100),//�����˵Ľ�ֺ�ף��ǹ⾫����ǿ飬�¹⾫����ǿ�
Array(4000061,100),Array(4000069,100),Array(4000074,100),//�չ⾫����տ飬��ʬ���ݣ���ɫ��ʨβ

Array(4000070,100),Array(4000071,100),Array(4000072,100),//�����ʨβ���ƶ���ʨβ��������ʨβ
Array(4000101,100),Array(4000102,100),Array(4000106,100),//��ɫ���Ȧ����ɫ���Ȧ�������è���޻���
Array(4000107,70),Array(4000108,100),Array(4000115,100),//�����è�Ļ�ɫ˿������è���ޣ�����
Array(4000128,100),Array(4000129,90),Array(4000143,70),//��С���ñ�ӣ���С���С�飬��ʬ����
Array(4000161,100),Array(4000162,100),Array(4000180,80),//�����β�ͣ���������Ƥ���������
Array(4000181,80),Array(4000182,100),Array(4000183,100),//�䶳��ᣬʯ�ҷ�ƿ��ī֭ƿ
Array(4000187,100),Array(4000188,80),Array(4000189,100),//��צ��Ѽ������ë
Array(4000190,70),Array(4000191,100),Array(4000192,100),//ɽ��ǣ���ɽ��ǣ��ǻ�
Array(4000196,200),Array(4000197,100),Array(4000204,100),//ľ�壬ʯ�壬����Ȯ��ͷ
Array(4000205,100),Array(4000206,100),Array(4000207,100),//�������߹ǣ�����
Array(4000292,100),Array(4000293,100),Array(4000294,100),//ɽ�������۹�������۹�
Array(4000329,70),Array(4000330,100),Array(4020008,80),//�����������ƵĴ̣������ƵĻ�
Array(4020007,100),Array(4020006,100),Array(4020005,100),//��ɫ��������ɫ��������ɫ����
Array(4020004,5),Array(4004000,20),Array(4004001,20),//�������ң�����ĸ���ǻ�ĸ��
Array(4004002,20),Array(4004003,20),Array(4004004,20),//����ĸ������ĸ�󣬺ڰ�ˮ��ĸ��
Array(4010000,20),Array(4010001,20),Array(4010002,20),//ĸ��ϵ��
Array(4010003,20),Array(4010004,20),Array(4010005,20),//ĸ��ϵ��
Array(4010006,20),Array(4010007,20),Array(4020000,20),//ĸ��ϵ��
Array(4020001,20),Array(4020002,20),Array(4020003,20)

);
var myDate = new Date();
var year = myDate.getFullYear();
var month = myDate.getMonth() + 1;
var days = myDate.getDate();
var giftList = Array(//Array(id,Num)
	Array(2022468,3)//
);
var finalGiftList = Array(//Array(id,Num)
	Array(2022468,5)//
);
//������Ҫ��Ʒ
var items = [4001126, 1000];
var С���� = "#fItem/Etc/0427/04270001/Icon9/0#";
function start() {
    status = -1;
    action(1, 0, 0);
}
function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    } else {
        if (mode == 1)
            status++;
        else
            status--;
        if (status == 0) {
        	id = cm.getPlayer().getId();
        	ringnum = cm.getBossLog2("��������");
			
        	var strlen = "���������������ҵ�������#e#r��ע������������Ͼ�Ϊ���������һ����������������5����ʼ���� 20����������� ף�� Ԫ�� ˫����Ǯbuff��#l\r\n\r\n";
        	if(ringnum == 0)
            	strlen += "#r�淨���飺 #bÿ����������յ�һ����������Ҫ������Ҫ�Ĳ��������������ҵ��������һ����һ���޴�Ľ�ƷŶ��ÿ���Ҷ����ṩ����20���������������ȡ�޶�󽱣�";
            else if (ringnum <= 20){
            	strlen += "���Ѿ��������ε�ί����ô��\r\n";
				strlen += "#e#b#L1#"+С����+"�ύ����#l\t\t#L2#"+С����+"���ñ���#l";
            }else{
				strlen = "�����������Ѿ�����˵����������̣�";
				cm.sendOk(strlen);
				cm.dispose();
				return;
			}
            cm.sendNext(strlen);
        }else if(status == 1){
			if (cm.getPlayer().getGuildId() <= 0) {
				cm.sendOk("#b����Ҫ���������ܽ��м�������,�����޷�����.");
				cm.dispose();
				return;
			}
			if (selection == 2) {
				if (cm.haveItem(items[0], items[1])) {
					var ran = Math.floor(Math.random() * itemList.length);
					var itmeid = itemList[ran][0];
					var itemnum = itemList[ran][1];
					changeOneTimeLog(ran,cm.getPlayer().getId());
					var strlen1 = "����ǰ�ܻ�����Ϊ�� "+(ringnum)+"\r\n\r\n";
					strlen1 += "���Ѿ��ɹ�����ȡ�˱����ܻ���";
					strlen1 += " �������Ҫ�����Ѽ�"+itemnum +"��#v"+itmeid+"#\r\n�ڴ����ĺ���Ϣ";
					cm.sendOk(strlen1);
					cm.gainItem(items[0], -items[1])
				} else {
					cm.sendOk("#r���ñ�����Ҫ#b"+items[1]+"��#v"+items[0]+"##z"+items[0]+"#");
				}
				cm.dispose();
				return;
			}
			
			if(cm.getInventory(4).isFull()){
    		cm.sendOk("#b�뱣֤������λ������2���ո�,�����޷�����.");
    		cm.dispose();
    		return;
			}
			if (cm.getInventory(2).isFull()){
            cm.sendOk("#b�뱣֤������λ������2���ո�,�����޷�����.");
            cm.dispose();
            return;
			}
        	itemIndex = getOneTimeLog(id);
        	if(ringnum > 0 && ringnum < 20){//���ڵ�һ�ֺ�С��20��   ������
        		if(cm.haveItem(itemList[itemIndex][0],itemList[itemIndex][1])){
        			cm.gainItem(itemList[itemIndex][0],-itemList[itemIndex][1]);
        			//cm.getPlayer().gainMeso(500000, true);
        			cm.sendNext("��ϲ���ύ�˲��ϣ�����һҳ��ɱ�������������ȡ������");
        		}else{
        		    cm.sendOk("��"+ringnum+"�����Բ�������û����������Ҫ�Ĳ��ϣ�����Ŷ��\r\n\r\n�������Ҫ�����Ѽ�"+itemList[itemIndex][1]+"��#v"+itemList[itemIndex][0]+"#\r\n�ڴ����ĺ���Ϣ");
					cm.dispose();
					return;
        		}
        	}else if(ringnum == 20){//���ս���
        		if(cm.haveItem(itemList[itemIndex][0],itemList[itemIndex][1])){
        			cm.gainItem(itemList[itemIndex][0],-itemList[itemIndex][1]);
        			cm.getPlayer().gainMeso(1000000, true);
        			cm.gainItem(4000463,30);//��������
					cm.gainItem(2340000,1);//ף����
					cm.gainItem(4001126,200);//��Ҷ			
					cm.gainItem(4000038, 2);//��
					cm.gainItem(2022529,1);//��������� +100%˫����ҵ���
					cm.gainItem(2022112,5);//СħŮ
					cm.setBossLog2("��������");
					Packages.server.custom.bossrank.BossRankManager.getInstance().addRanklistGP(cm.getPlayer().getGuildId(), 2, 1);
					cm.getPlayer().dropMessage(5, "���1��������");
        			cm.sendNext("��ϲ�������������̣��������");
        		}else{
        			cm.sendOk(""+ringnum+"�Բ�������û����������Ҫ�Ĳ��ϣ�����Ŷ��\r\n\r\n�������Ҫ�����Ѽ�"+itemList[itemIndex][1]+"��#v"+itemList[itemIndex][0]+"#\r\n�ڴ����ĺ���Ϣ");
					cm.dispose();
					return;
        		}
        	}else{
        		var ran = Math.floor(Math.random() * itemList.length);
        		var itmeid = itemList[ran][0];
        		var itemnum = itemList[ran][1];
        		if(itemIndex == -1){
        			setOneTimeLog(ran,id);
        		}else{
        			changeOneTimeLog(ran,id);
        		}
        		var strlen1 = "����ǰ�ܻ�����Ϊ�� "+(ringnum+1)+"\r\n\r\n";
				strlen1 += "���Ѿ��ɹ�����ȡ�˱����ܻ���";
	        	strlen1 += " �������Ҫ�����Ѽ�"+itemnum +"��#v"+itmeid+"#\r\n�ڴ����ĺ���Ϣ";
        		cm.setBossLog2("��������");
        		cm.sendOk(strlen1);
        		cm.dispose();
        	}
        }else if(status == 2){
			     if(ringnum > 4){//����4���𣬸�����
        				cm.gainItem(4000463,1);//��������
						cm.gainItem(4000313,1);//�ƽ��Ҷ
        				cm.gainItem(4001126,20);//��Ҷ
						Packages.server.custom.bossrank.BossRankManager.getInstance().addRanklistGP(cm.getPlayer().getGuildId(), 2, 1);
					    cm.getPlayer().dropMessage(5, "���1��������");
        			}
        	if(ringnum < 20){
	        	var ran = Math.floor(Math.random() * itemList.length);
	        	var itemid = itemList[ran][0];
	        	var itemnum = itemList[ran][1];
	        	if(itemIndex == -1){
	        		setOneTimeLog(ran,id);
	        	}else{
	        		changeOneTimeLog(ran,id);
	        	}
	        	var strlen = "����ǰ�ܻ�����Ϊ�� "+ (ringnum+1) +"\r\n\r\n";
	        	strlen += " �������Ҫ�����Ѽ�"+itemnum +"��#v"+itemid +"#\r\n�ڴ����ĺ���Ϣ";;
	        	cm.setBossLog2("��������");
	        	cm.sendOk(strlen);
	        	cm.dispose();
	        }else{
	        	cm.sendOk("���Ѿ���������̣�");
	        	cm.dispose();
	        }
	    }
    }          
}
function getBossLog2(boss,id) {
        var con = DatabaseConnection.getConnection();
        var count = 0;
        var ps;
        //ps = con.prepareStatement("SELECT COUNT(*) FROM bosslog WHERE characterid = ? AND bossid = ? AND lastattempt >= subtime(CURRENT_TIMESTAMP, '23:0:0.0')");
		var day = ""+year+"-"+month+"-"+days+"";
		ps = con.prepareStatement("SELECT COUNT(*) FROM bosslog2 WHERE characterid = ? AND bossid = ?");// AND lastattempt >= ?
        ps.setInt(1, id);
        ps.setString(2, boss);
		//ps.setString(3,day);
        var rs = ps.executeQuery();
        if (rs.next()) {
            count = rs.getInt(1);
        } else {
            count = -1;
        }
        rs.close();
        ps.close();
        return count;
}

function setOneTimeLog(bossid,id) {
    var con1 = DatabaseConnection.getConnection();
    var ps = con1.prepareStatement("insert into onetimelog2 (characterid, log) values (?,?)");
    ps.setInt(1, id);
    ps.setString(2, "�ܻ�����_" + bossid);
    ps.executeUpdate();
    ps.close();
}
function changeOneTimeLog(bossid,id) {
    var con1 = DatabaseConnection.getConnection();
    var ps = con1.prepareStatement("update onetimelog2 set log = ? where characterid = ? and log like '%�ܻ�����%'");
    ps.setString(1, "�ܻ�����_" + bossid);
    ps.setInt(2, id);
    ps.executeUpdate();
    ps.close();
}

function getOneTimeLog(id) {
        var con = DatabaseConnection.getConnection();
        var count = 0;
        var ps;
        ps = con.prepareStatement("SELECT log FROM onetimelog2 WHERE characterid = ? and log like '%�ܻ�����%'");
        ps.setInt(1, id);
        var rs = ps.executeQuery();
        if (rs.next()) {
            count = rs.getString("log").replace("�ܻ�����_", "");
        } else {
            count = -1;
        }
        rs.close();
        ps.close();
        return count;
}

