var CY0 = "��������������������������������������������������������������������";
var CY1 = "��       - ���� -       ��";
var CY2 = "�� �ű�����  �����ƽű� ��";
var CY3 = "�� ����֧�� �� ��Ϸ���� ��";
var CY4 = "�� �ף���ӡ�  ��ͼ���� ��";
var CY5 = "�� �Ӷܷ�����  �۵�½�� ��";
var CY6 = "�ǩ�����������������������������������������������������������������";
var CY7 = "��   ΨһQQ:12384161    ��";
var CY8 = "��������������������������������������������������������������������";

var ca = java.util.Calendar.getInstance();
var �� = ca.get(java.util.Calendar.YEAR); //������
var �� = ca.get(java.util.Calendar.MONTH) + 1; //����·�
var �� = ca.get(java.util.Calendar.DATE);//��ȡ��
var ʱ = ca.get(java.util.Calendar.HOUR_OF_DAY); //���Сʱ
var �� = ca.get(java.util.Calendar.MINUTE);//��÷���
var �� = ca.get(java.util.Calendar.SECOND); //�����
var weekday = ca.get(java.util.Calendar.DAY_OF_WEEK);

var status = -1;
var beauty = 0;
var tosend = 0;
var sl;
var mats;

var ��1 = "#fUI/GuildMark/Mark/Animal/00002011/6#";
var ��2 = "#fUI/GuildMark/Mark/Animal/00002011/7#";
var ��3 = "#fUI/GuildMark/Mark/Animal/00002011/8#";
var ��4 = "#fUI/GuildMark/Mark/Animal/00002011/9#";
var ��5 = "#fUI/GuildMark/Mark/Animal/00002011/5#";
var ������ = "#fUI/UIWindow/Quest/icon3/6#";
var С���� = "#fEffect/CharacterEff/1112925/0/0#"; //����
var ������ = "#fUI/StatusBar/BtClaim/normal/0#";
var ���ͼ�� = "#fUI/UIWindow.img/Item/BtCoin/normal/0#";
var ���ͼ�� = "#fUI/CashShop/CashItem/0#";
var t2 = "#fEffect/CharacterEff/1112902/0/0#";
var ���� ="#fMap/MapHelper/weather/witch/3#";

function start() {
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
        if (mode == 1) {
            status++;
        } else {
            if (status == 0) {
                cm.sendNext("�����Ҫ����н�����������Ұɡ�");
                cm.dispose();
            }
            status--;
        }
		

		
		
        if (status == 0) {
			���� = 500;
			if (�� == 52) {
				���� = 1000;
				����˵�� = "#e#r��ϲ������ǰ��ֵ�ɻ�� 1:1000 ��������ʱ��С�#n\r\n";
			} else {
				���� = 1;
				����˵�� = "";
			}
			��� = getmoneyb();
			Ԫ�� = getmoneyb();
            �ۼƳ�ֵ = getmoney();
			��� = cm.getChar().getCSPoints(1);
			
			��ȡ��� = ���*����;
            var text = "";
            text += "\t\t\t     "+ ���� +"#e#d��ȡ�ƹ�ʯ#k#n"+ ���� + "#l\r\n\r\n"; 
					
			text += "��ඣ���Ҫ�����ƹ�ʯ��ʹ���ƹ�ʯ����ʹ��ͻ�Ƽ���Ӵ\r\n"
			text += "��ϲ���ռ�������ҵ���ҿ��Ը����ƹ�ʯӴ\r\n"
			
			text += "���Ѿ�ʹ��ħ�����鿴����ӵ�У�#r"+���+" #k���\r\n\r\n"
			
			
			
				text += "                         #L2##b <<<������ƹ�ʯ>>> #l\r\n\r\n"
			
				
			
			cm.sendSimple(text);
 
        } else if (status == 1) {
			sele1 = selection;
			 if (selection == 2) {
				var msg = "";
				msg += "1800��� = "+����+"�ƹ�ʯ       ��ǰ���"+���+"\r\n"
				msg += "��������Ҫ���ƹ�ʯ����\r\n"
				cm.sendGetNumber(msg, 1,1, 9999);
			}else {
				
				cm.dispose();
			}
				 							 
		 

        
        } else if (status == 2) {
			
			sele2 = selection;
			
            if (sele1 == 0) {
	
			} else	if (sele1 == 1) {
			} else	if (sele1 == 2) {
				if (cm.getPlayer().getCSPoints(1) >= sele2*1800) {
				if (!cm.checkNumSpace(2, 2)) {
			    cm.sendOk("�����������ռ䲻��2��");
			    cm.dispose();
			    return;
		        }
					
					cm.gainItem(2614000,sele2);
					cm.gainNX(-sele2*1800);
					cm.sendOk("�һ��ɹ�");
                    cm.dispose();
				} else {
					cm.sendOk("������������ǣ�"+sele2+"   ÿ����Ҫ���1800\r\nһ����Ҫ���"+sele2*1800+"   ���е��"+���+"");
                    cm.dispose();
				}
				
			

			
			} else {
				
			}	
        
			

            status = -1;
        } else {
            cm.dispose();
        }
    }
}





function �ƹ���(){
	var ��ɫid = cm.getPlayer().getId();
	var ��ֵ =0;
	var ��ֵ1 =1;
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "SELECT ownid,objecid FROM invsystem WHERE ownid = "+��ɫid+"";
	var pstmt = conn.prepareStatement(sql);
	var result = pstmt.executeQuery();
	if (result.next()) {
	��ֵ = result.getInt("objecid");
	��ֵ1= 0;
	}
	result.close();
	pstmt.close();
	if(��ֵ1==1){
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "INSERT INTO invsystem(id,ownid,objecid) value(null,"+��ɫid+",0)";
	var pstmt = conn.prepareStatement(sql);
	pstmt.executeUpdate();
	pstmt.close();
	}
	return ��ֵ;	
	
}




function �ƹ��˻�õ��(uit) {
	var uid = �ƹ���();
    var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "UPDATE tuiguangjf SET dj = dj+"+uit+"  WHERE ownid = "+uid+"";
    var pstmt = conn.prepareStatement(sql);
	pstmt.executeUpdate();
	pstmt.close();
}















//V95�˵ĳ�ֵ��ȡ
function getmoney() {
	accid = cm.getPlayer().getAccountID();
	xmfhz = 0;
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "SELECT * FROM accounts WHERE id = "+accid+"   ;";
	var pstmt = conn.prepareStatement(sql);
	var result = pstmt.executeQuery();
	if (result.next()) {
	xmfhz = result.getString("money");
	}
	result.close();
	pstmt.close();	
	return xmfhz;
}

function setmoney(xiezhi) {
	accid = cm.getPlayer().getAccountID();
    var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "UPDATE accounts SET money = "+xiezhi+"  WHERE id = "+accid+"  ;";
    var pstmt = conn.prepareStatement(sql);
	pstmt.executeUpdate();
	pstmt.close();
}

function gainmoney(xiezhi) {
	accid = cm.getPlayer().getAccountID();
    var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "UPDATE accounts SET money = money+"+xiezhi+"  WHERE id = "+accid+"  ;";
    var pstmt = conn.prepareStatement(sql);
	pstmt.executeUpdate();
	pstmt.close();
}


function getmoneyb() {
	accid = cm.getPlayer().getAccountID();
	xmfhz = 0;
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "SELECT * FROM accounts WHERE id = "+accid+"   ;";
	var pstmt = conn.prepareStatement(sql);
	var result = pstmt.executeQuery();
	if (result.next()) {
	xmfhz = result.getString("moneyb");
	}
	result.close();
	pstmt.close();	
	return xmfhz;
}
function setmoneyb(xiezhi) {
	accid = cm.getPlayer().getAccountID();
    var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "UPDATE accounts SET moneyb = "+xiezhi+"  WHERE id = "+accid+"  ;";
    var pstmt = conn.prepareStatement(sql);
	pstmt.executeUpdate();
	pstmt.close();
}

function ��ֵ��() {
	accid = cm.getPlayer().getAccountID();
	xmfhz = 0;
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "SELECT * FROM accounts WHERE id = "+accid+"   ;";
	var pstmt = conn.prepareStatement(sql);
	var result = pstmt.executeQuery();
	if (result.next()) {
	xmfhz = result.getString("moneyg");
	}
	result.close();
	pstmt.close();	
	return xmfhz;
}
function �۳���ֵ��(xiezhi) {
	accid = cm.getPlayer().getAccountID();
    var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "UPDATE accounts SET moneyg = moneyg-"+xiezhi+"  WHERE id = "+accid+"  ;";
    var pstmt = conn.prepareStatement(sql);
	pstmt.executeUpdate();
	pstmt.close();
}





function getmoneyc() {
	accid = cm.getPlayer().getAccountID();
	xmfhz = 0;
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "SELECT * FROM accounts WHERE id = "+accid+"   ;";
	var pstmt = conn.prepareStatement(sql);
	var result = pstmt.executeQuery();
	if (result.next()) {
	xmfhz = result.getString("moneyc");
	}
	result.close();
	pstmt.close();	
	return xmfhz;
}

function gainmoneyc(xiezhi) {
	accid = cm.getPlayer().getAccountID();
    var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "UPDATE accounts SET moneyc = moneyc+"+xiezhi+"  WHERE id = "+accid+"  ;";
    var pstmt = conn.prepareStatement(sql);
	pstmt.executeUpdate();
	pstmt.close();
}