var CY0 = "�ǩ��������������������������� ����������������������������������";
var CY1 = "��       - ���� -       ��";
var CY2 = "�� �淨����  �����ƽű� ��";
var CY3 = "�� ����֧�� �� ��Ϸ���� ��";
var CY4 = "�� �ף���ӡ�  ��ͼ���� ��";
var CY5 = "�� �Ӷܷ�����  �۵�½�� ��";
var CY7 = "�� ���ο���    ���ο��� ��";
var CY8 = "�ǩ��������������������������� ����������������������������������";
var CY9 = "��    Ψһ΢��:ZerekY   ��";
var CY0 = "�ǩ��������������������������� ����������������������������������";

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
var numArr = Array("#fUI/Basic.img/LevelNo/0#","#fUI/Basic.img/LevelNo/1#","#fUI/Basic.img/LevelNo/2#","#fUI/Basic.img/LevelNo/3#","#fUI/Basic.img/LevelNo/4#","#fUI/Basic.img/LevelNo/5#","#fUI/Basic.img/LevelNo/6#","#fUI/Basic.img/LevelNo/7#","#fUI/Basic.img/LevelNo/8#","#fUI/Basic.img/LevelNo/9#");
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
			���� = 1;
			if (�� == 52) {
				���� = 1000;
				����˵�� = "#e#r��ϲ������ǰ��ֵ�ɻ�� 1:1000 ��������ʱ��С�#n\r\n";
			} else {
				���� = 1;
				����˵�� = "";
			}
			//������ = getzanzhu();
			���� = getmoneyb();
			
            Ԫ�� = getmoney();

			var �������� = cm.getBossRankCount8("�������ֻ���");
			if (�������� < 0) {
				�������� = 0
			}
			
			��ȡ��� = ����*����;
            var text = "";
            text += "\t\t\t      "+ ���� +"#e#d��������#k#n"+ ���� + "#l\r\n\r\n"; 
			text += "#d\t"+numArr[1]+"������Ϊ�������÷�,������ú�����԰�������\r\n"
			text += "#d\t"+numArr[2]+"���������� 1:"+����+"    1������������ֳ�"+����+"Ԫ��\r\n\r\n"	
            //text += "#d\t"+numArr[3]+"�������κε���,װ��,��Ʒ,����������Ϸ���ɻ��\r\n\r\n"	
            //text += "#d\t"+numArr[4]+"�������κε���,װ��,��Ʒ,����������Ϸ���ɻ��\r\n\r\n"			
			
			//text += ""+���ͼ��+"�˺��ۼ�������"+�ۼƳ�ֵ+"\r\n"
			text += ""+���ͼ��+"��ɫ�ۼ�������" + �������� +"\r\n"
			//text += ""+���ͼ��+"��ǰ�����㣺"+��ֵ��()+" \r\n"
			text += ""+���ͼ��+"��ǰ�˻������㣺"+����+" \r\n"
			text += ""+���ͼ��+"��ǰ�˻�Ԫ������"+Ԫ��+" \r\n"
			
			/*if(��ֵ��()>0){
			text += "\r\n#e       ����:"+��ֵ��()+" ������������ֳ�Ԫ��Ӵ\r"	
			text += "   #L999##e- - - <<<"+���ͼ��+"��������Ԫ��"+���ͼ��+">>> - - -#l#k\r\n\r\n"
			}*/
			if (���� > 0) {
				text += "#e#b       ����:"+����+" �������Զһ�Ԫ��Ӵ#n\r\n"
				text += "   #L2##e#b- - - <<<"+���ͼ��+"�����һ�Ԫ��"+���ͼ��+">>> - - -#l\r\n\r\n"
			} 
			//text += "#L5##e#r- - - <<<ÿ�ճ�ֵ���>>> - - -#l\r\n"
			text += "#L3##e#r- <<�鿴�������>> -#l  #L33##e#r- <<�����������>> -#l\r\n\r\n"
			
            text += "#L0##r#r- <<�����������>> -#l  #L44##r#r- <<���������ȡ>> -#l\r\n"
		
			//text += "#L45##r#r- - - <<< �������а� >>> - - -#l\r\n"	
			cm.sendSimple(text);
 
        } else if (status == 1) {
			sele1 = selection;
			if(selection ==999){
				var msg = "";
				msg += "1������ = 1Ԫ�� ��ǰ�����㣺"+��ֵ��()+"\r\n"
				msg += "�����������������һ�Ԫ��\r\n"
				cm.sendGetNumber(msg, 1,1, ��ֵ��());
				
				
			}else if (selection == 0) { //������ֵ�ӿ�
                    cm.openWeb("http://lk4.cn/2nj"); //����ҳ
					cm.playerMessage(5, "�Ѿ�Ϊ����������ֵ��վ,���û�е�����վ����ϵGM��ֵ");
					cm.dispose();
            } else if (selection == 44) { 
			        cm.dispose();
                    cm.openNpc(3003385,"���ų�ֵ");
					
            }else if (selection == 45) {
				cm.showBossRank8Rank("�������ֻ���")
				cm.dispose();
			}else if (selection == 1) {
				���� = cm.getHyPay(1);
				if (���� >= 1) {
					cm.д���ƹ�ֵ(����);
					cm.addHyPay(����);
					//cm.gainmoney(-����);
					cm.gain(-����);
					cm.worldMessage(9, cm.getC().getChannel(),"���������ġ�" + " : " + " [" + cm.getPlayer().getName() + "] �Ա�������֧�֣� ����ˣ�"+����+"Ԫ�����ƹ��˻��10%������", true);
					cm.dispose();
				} else {
					cm.sendOk("��Ǹ����û�г�ֵ��");
					cm.dispose();
				
				}
			} else if (selection == 2) {
				var msg = "";
				msg += "1���� = "+Ԫ��+"Ԫ�� ��ǰ������"+����+"\r\n"
				msg += "���������������һ�Ԫ��\r\n"
				cm.sendGetNumber(msg, 1,1, 9999);
			} else if (selection == 3) {
				cm.dispose();
			    cm.openNpc(3003385,2102);
			} 
			else if (selection == 33) {
				cm.dispose();
			    cm.openNpc(3003385,70);
			}
			else if (selection == 4) {	
                                      
				if (���� >= 1) {
					setmoneyb(0);
					cm.gainNX(��ȡ���);	
                    cm.getPlayer().setBossLog("ÿ�ճ�ֵ",1,+����*����);			
					
					cm.dispose();
				} else {
					cm.sendOk("��Ǹ����û��������");
					cm.dispose();
				
				}
			} else if (selection == 5) {				
				 cm.dispose();
			     cm.openNpc(9900004,184816);
							 
			} else if (selection == 9) { //�ױ��һ�
                    cm.dispose();
			        cm.openNpc(9900004,27);
			} else {
				
				cm.dispose();
			}
				 							 
 

        
        } else if (status == 2) {
			
			sele2 = selection;
			
            if (sele1 == 0) {
	
			} else	if (sele1 == 1) {
			} else	if (sele1 == 2) {
				if (���� >= sele2) {
					cm.setmoneyb(-sele2);
					
					�ı�Ԫ��(sele2*����);
					cm.sendOk("�һ��ɹ�");
					cm.setBossRankCount8("�������ֻ���", sele2);
					if(�ƹ���()!=0){
					cm.sendOk("���ֳɹ�");	
					�ƹ��˻�õ��(sele2*10);
					}else {
					cm.sendOk("���ֳɹ�");	
					}
                    cm.dispose();
				} else {
					cm.sendOk("���������޷��һ�");
                    cm.dispose();
				}
				
			

			
			}else if (sele1 == 999) {
				if (��ֵ��() >= sele2) {
					cm.setmoneyb(sele2);
					�۳���ֵ��(sele2);
					//cm.setBossRankCount8("�������ֻ���", sele2);+�Ӹ�����������
					
					//cm.gainNX(sele2*����);
					
                    cm.dispose();
				} else {
					cm.sendOk("�����㲻���޷��һ�");
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

function չʾԪ��(xiezhi) {
	accid = cm.getPlayer().getAccountID();
    var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "UPDATE accounts SET money = "+xiezhi+"  WHERE id = "+accid+"  ;";
    var pstmt = conn.prepareStatement(sql);
	pstmt.executeUpdate();
	pstmt.close();
}


function �ı�Ԫ��(xiezhi) {
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