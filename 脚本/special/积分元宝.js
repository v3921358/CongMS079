var CY0 = "��������������������������������������������������������������������";
var CY1 = "��       - ���� -       ��";
var CY2 = "�� �ű�����  �����ƽű� ��";
var CY3 = "�� ����֧�� �� ��Ϸ���� ��";
var CY4 = "�� �ף���ӡ�  ��ͼ���� ��";
var CY5 = "�� �Ӷܷ�����  �۵�½�� ��";
var CY6 = "�ǩ�����������������������������������������������������������������";
var CY7 = "��   ΨһQQ:12384161    ��";
var CY8 = "��������������������������������������������������������������������";
var �ķ�ӡ = "#fUI/GuildMark/Mark/Pattern/00004014/11#"; // ��ɫ�ķ�ӡ
var status = -1;
var beauty = 0;
var tosend = 0;
var sl;
var mats;
var xx = -1;
var jiage = -1;
var ÿ��ͻ�� = 200000;
var ���ͻ�Ƶȼ� = 250;
	function start() {
		status = -1;
		action(1, 0, 0);
		}


	function action(mode, type, selection) {
		if (mode == -1) {
		cm.sendOk("��л���㣡");
		cm.dispose();
		} else {

	if (mode == 0) {
		cm.sendOk("��л���㣡");
        	cm.dispose();
        	return;
    		}

	if (mode == 1) {
		status++;
		} else {
		status--;
		}
        if (status == 0) {
			
            var gsjb = "";
				gsjb = ""+�ķ�ӡ+�ķ�ӡ+�ķ�ӡ+�ķ�ӡ+�ķ�ӡ+�ķ�ӡ+�ķ�ӡ+�ķ�ӡ+�ķ�ӡ+�ķ�ӡ+�ķ�ӡ+�ķ�ӡ+�ķ�ӡ+�ķ�ӡ+�ķ�ӡ+�ķ�ӡ+�ķ�ӡ+�ķ�ӡ+�ķ�ӡ+�ķ�ӡ+�ķ�ӡ+"\r\n\r\n";

				gsjb += "            #L1##e#b1000���ֶһ�500���#r[Hot]#l";
            cm.sendSimple(gsjb);
        } else if (status == 1) {  
		if (selection == 1) {
			if(cm.getPlayer().getjf3()>= 1000){ 
                    cm.getPlayer().setjf3(cm.getPlayer().getjf3()-1000);
					cm.gainNX(500);
					cm.sendOk("�ɹ��û��ջ��ֶһ��˵��");
					
					cm.dispose();
                } else {
                    cm.sendNext("��ӵ�еĻ��ջ�����������1000");
                    cm.dispose();
                }
		}
        }
    }
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
