
var CY0 = "������������������������������������������������������������������";
var CY1 = "��       - ���� -       ��";
var CY2 = "�� �ű�����  �����ƽű� ��";
var CY3 = "�� ����֧�� �� ��Ϸ���� ��";
var CY4 = "�� �ף���ӡ�  ��ͼ���� ��";
var CY5 = "�� �Ӷܷ�����  �۵�½�� ��";
var CY6 = "�� ���ο�����  ���ο��� ��";
var CY7 = "�ǩ���������������������������������������������������������������";
var CY8 = "��   ΨһQQ:12384161    ��";
var CY9 = "������������������������������������������������������������������";

var ����è ="#fUI/ChatBalloon/37/n#";
var è�� =  "#fUI/ChatBalloon/37/ne#";
var è�� =  "#fUI/ChatBalloon/37/nw#";
var �� =    "#fUI/ChatBalloon/37/e#";
var �� =    "#fUI/ChatBalloon/37/w#";
var ����è ="#fUI/ChatBalloon/37/s#";
var è���� ="#fUI/ChatBalloon/37/se#";
var è���� ="#fUI/ChatBalloon/37/sw#";
var �ʹڰ� ="#fUI/GuildMark/Mark/Etc/00009004/16#";
function start() {
    status = -1;
    action(1, 0, 0);
}
function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    }
    else {
        if (status >= 0 && mode == 0) {

            cm.dispose();
            return;
        }
        if (mode == 1) {
            status++;
        }
        else {
            status--;
        }
        if (status == 0) {
            var tex2 = "";
            var text = "";
            for (i = 0; i < 10; i++) {
                text += "";
            }
			text += ""
			text += "                 #k"+�ʹڰ�+" #r#e#w �� ɫ �� �� #n#k "+�ʹڰ�+"\r\n";
			text += "  "+è��+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+è��+"\r\n";
			
			text += "#L1##k- - - - -[9800���]����#v4310058#30��- - - - -#l\r\n\r\n\t\t#d����ʹ�κγ��ﴥ��ȫ����ȡ��Һ���Ʒ#l\r\n\r\n"
			
			text += "#L2##k- - - - -[9800���]����#v3994765#30��- - - - -#l\r\n\r\n\t\t#d����ʹЯ�����κγ���һֱ���ἢ��#l\r\n\r\n"
			
			text += "#L3##k- - - - -[18000���]����#i1122017:#30��- - - - -#l\r\n\r\n\t\t#dװ������Դ�ֻ�ö��⾭��#l\r\n\r\n"
			
			//text += "#L4##k#b[10Ԫ��]ɾ����ɫ#l           #L5##k#b[20Ԫ��]���ӽ�ɫ#l\r\n\r\n"
            cm.sendSimple(text);
		}else if(status ==1){
			
		if (selection == 1){ 
			if (!cm.checkNumSpace(4, 1)) {
			cm.sendOk("�����������ռ䲻��1��");
			cm.dispose();
			return;
		    }
		if(cm.getPlayer().getCSPoints(1)>=9800 && cm.getPlayer().getItemQuantity(4310058,false)<1){
				cm.gainNX(-9800);
				cm.gainItemPeriod(4310058,1,30*24);
				cm.sendOk("����ɹ�");
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "����ɫ���ܡ�" + " : " + "[" + cm.getChar().getName() + "]ͨ��������Ʒ�������30��������ܣ�")); 
				cm.dispose();
				return;
		}else {
			cm.sendOk("�����9800,�������Ѿ������������");
			cm.dispose();
			return;
		    }
		}
			
		 if (selection == 2) {
			if (!cm.checkNumSpace(3, 1)) {
			cm.sendOk("�����������ռ䲻��1��");
			cm.dispose();
			return;
		}
			if(cm.getPlayer().getCSPoints(1)>=9800 && cm.getPlayer().getItemQuantity(3994765,false)<1){
				cm.gainNX(-9800);
				cm.gainItemPeriod(3994765,1,30*24);
				cm.sendOk("����ɹ�");
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "����ɫ���ܡ�" + " : " + "[" + cm.getChar().getName() + "]ͨ��������Ʒ�������30������㼢�����ܣ�")); 
				cm.dispose();
				return;
			}else {
			cm.sendOk("�����9800,�������Ѿ������������");
			cm.dispose();
			return;
			}
		}if (selection == 3){ 
			if (!cm.checkNumSpace(1, 1)) {
			cm.sendOk("����װ�����ռ䲻��1��");
			cm.dispose();
			return;
		}if(cm.getPlayer().getCSPoints(1)>=18000 && cm.getPlayer().getItemQuantity(1122017,false)<1){
				cm.gainNX(-18000);
				cm.gainItemPeriod(1122017,1,30*24);
				cm.sendOk("����ɹ�");
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "����ɫ���ܡ�" + " : " + "[" + cm.getChar().getName() + "]ͨ��������Ʒ�������30�쾫���׹��")); 
				cm.dispose();
				return;
			}else {
			cm.sendOk("�����18000,�������Ѿ������������");
			cm.dispose();
			return;
			}
            }
		if (selection == 4){
			cm.dispose();
            cm.openNpc(9310072, "ɾ����ɫ");
			
		}
		if (selection == 5){
			cm.dispose();
            cm.openNpc(9310072, "���ӽ�ɫ");
			
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
	var sql = "UPDATE accounts SET moneyb = moneyb+"+xiezhi+"  WHERE id = "+accid+"  ;";
    var pstmt = conn.prepareStatement(sql);
	pstmt.executeUpdate();
	pstmt.close();
}
