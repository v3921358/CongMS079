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
Array(4000023,100),Array(4001126,100),Array(4000017,100),
Array(4000001,100),Array(4000024,100),Array(4000039,100),
Array(4000003,100),Array(4000018,100),Array(4000005,100),
Array(4000033,100),Array(4000032,100),Array(4000021,100),
Array(4000034,100),Array(4000037,100),Array(4000035,100),
Array(4000036,100),Array(4000004,100),Array(4000026,100),
Array(4000029,100),Array(4000041,100),Array(4000013,100),
Array(4000007,100),Array(4000043,100),Array(4000073,100),
Array(4000015,100),Array(4000008,100),Array(4000006,100),
Array(4000154,100),Array(4000161,100),Array(4000163,100),
Array(4000170,100),Array(4000108,100),Array(4000127,100),
Array(4000191,100),Array(4000166,100)

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
var items = [4000012, 100];//����������Ҫ����Ʒ������
var �������� = 1;//�����������

//�����ƷID    �������0-100    ��������    ע�⣺װ������ֻ����1
var �������=[
[1102163, 1, 1,],
[4170000, 20, 1,],
[2340000, 20, 1,],
[2049100, 20, 1,],
[2000002, 40, 100,],
[2000003, 40, 100,],
[2000005, 40, 10,],
[2000004, 40, 10,],
[4000313, 40, 1,],
[4002000, 40, 1,],
[4002001, 40, 1,],
[4002002, 40, 1,],
[4002003, 40, 1,],
[5150040, 30, 1,],
[5040000, 30, 3,],
[5390000, 30, 3,],
[5390001, 30, 3,],
[5390002, 30, 3,],
[5390006, 30, 3,],
[2100007, 50, 1,],
[2100006, 50, 1,],
[2100005, 50, 1,],
[2100004, 50, 1,] 


];
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
        	ringnum = cm.getBossLog("paoshang");
			
        	var strlen = "���Ѿ��ҵ����ҵ�������\r\n#e#rע������������Ͼ�Ϊ���������һ��������������\r\nÿ4���н���#l\r\n";
        	if(ringnum == 0)
            	strlen += "#r�淨���飺 #bÿ����������յ�һ����������Ҫ������Ҫ�Ĳ��������������ҵ��������һ����һ���޴�Ľ�ƷŶ��ÿ���Ҷ����ṩ����20���������������ȡ�޶�󽱣�";
            else if (ringnum <= 20){
				strlen +=    "#e#b#L1#"+С����+"�ύ����#l\t\t#L2#"+С����+"���ñ���#l\r\n\r\n";
            	strlen += "��Ʒһ����\r\n#v1102163# #v2340000# #v4170000# #v2340000# #v2049100# #v2000002# #v2000003# #v2000005# #v2000004# #v4000313# #v4002000# #v4002001# #v4002002# #v4002003# #v5150040# #v5040000# #v5390000# #v5390001# #v5390002# #v5390006# #v2100007# #v2100006# #v2100005#\r\n";
            	
            }else{
				strlen = "�����������Ѿ�����˵����������̣�";
				cm.sendOk(strlen);
				cm.dispose();
				return;
			}
            cm.sendNext(strlen);
        }else if(status == 1){
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
        			cm.sendNext("��ϲ���ύ�˲��ϣ�����һҳ��ɱ���������ȡ������");
        		}else{
        		    cm.sendOk("��"+ringnum+"�����Բ�������û����������Ҫ�Ĳ��ϣ�����Ŷ��\r\n\r\n�������Ҫ�����Ѽ�"+itemList[itemIndex][1]+"��#v"+itemList[itemIndex][0]+"#\r\n�ڴ����ĺ���Ϣ");
					cm.dispose();
					return;
        		}
        	}else if(ringnum == 20){//�ڶ�ʮ��  ������  ���ս���
        		if(cm.haveItem(itemList[itemIndex][0],itemList[itemIndex][1])){
        			cm.gainItem(itemList[itemIndex][0],-itemList[itemIndex][1]);
        			
					for (var i = 0; i < 1; i++) {
			var item;
			var ran = Math.floor(Math.random() * 100);
			var ran1 = null;
			ran1 = finalGift(�������);
			cm.gainItem(ran1[0], ran1[2]);
		}
					cm.setBossLog("paoshang");
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
        		cm.setBossLog("paoshang");
        		cm.sendOk(strlen1);
        		cm.dispose();
        	}
        }else if(status == 2){
        	if(ringnum < 20){
				if((ringnum == 4)||(ringnum == 8)||(ringnum == 12)||(ringnum == 16)){//����4���𣬸�����
        									for (var i = 0; i < ��������; i++) {
			var item;
			var ran = Math.floor(Math.random() * 100);
			var ran1 = null;
			ran1 = finalGift(�������);
			cm.gainItem(ran1[0], ran1[2]);
		}

        			}

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
	        	cm.setBossLog("paoshang");
	        	cm.sendOk(strlen);
	        	cm.dispose();
	        }else{
	        	cm.sendOk("���Ѿ���������̣�");
	        	cm.dispose();
	        }
	    }
    }          
}
function getBossLog(boss,id) {
        var con = DatabaseConnection.getConnection();
        var count = 0;
        var ps;
        //ps = con.prepareStatement("SELECT COUNT(*) FROM bosslog WHERE characterid = ? AND bossid = ? AND lastattempt >= subtime(CURRENT_TIMESTAMP, '23:0:0.0')");
		var day = ""+year+"-"+month+"-"+days+"";
		ps = con.prepareStatement("SELECT COUNT(*) FROM bosslog WHERE characterid = ? AND bossid = ?");// AND lastattempt >= ?
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
    var ps = con1.prepareStatement("insert into onetimelog (characterid, log) values (?,?)");
    ps.setInt(1, id);
    ps.setString(2, "�ܻ�����_" + bossid);
    ps.executeUpdate();
    ps.close();
}
function changeOneTimeLog(bossid,id) {
    var con1 = DatabaseConnection.getConnection();
    var ps = con1.prepareStatement("update onetimelog set log = ? where characterid = ? and log like '%�ܻ�����%'");
    ps.setString(1, "�ܻ�����_" + bossid);
    ps.setInt(2, id);
    ps.executeUpdate();
    ps.close();
}

function getOneTimeLog(id) {
        var con = DatabaseConnection.getConnection();
        var count = 0;
        var ps;
        ps = con.prepareStatement("SELECT log FROM onetimelog WHERE characterid = ? and log like '%�ܻ�����%'");
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
function finalGift(lists) {
	var maxChance = 0;
	for (var i in lists) {
		if (lists[i][1] > maxChance) {
			maxChance = lists[i][1];
		}
	}
	var chance = Math.floor(Math.random() * maxChance);
	var finalitem = Array();
	for (var i = 0; i < lists.length; i++) {
		if (lists[i][1] >= chance) {
			finalitem.push(lists[i]);
		}
	}
	var ran1 = Math.floor(Math.random() * finalitem.length);
	return finalitem[ran1];
}
