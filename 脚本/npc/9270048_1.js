var �ʺ� ="#fEffect/ItemEff/1071085/effect/walk1/2#";
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
			text +=  "    \t"+�ʺ�+"  #e#d ר �� �� �� #k#n  #r  "+�ʺ�+"#b#k#n\r\r\n";
			text += "#d��ඣ���û����Ľ�����˵�Ӱ�������˺��أ�\r\n����У�����1778Ԫ���Ϳ��������Ӱ�����˺����һ��Ŷ��#k\r\n"
			//text += "\t#L100##k[#v4170005##r#c4170005##k/35]�һ�#d#fUI/Basic/BtHide3/mouseOver/0##v1912000##l\r\n\r\n"
			text += "\t#L101##k[1778Ԫ��]�һ�#v3994720#����ʹ��#l\r\n\r\n"
            cm.sendSimple(text);
		}else if(status ==1){
			if (!cm.checkNumSpace(4, 1)) {
			cm.sendOk("�����������ռ䲻��1��");
			cm.dispose();
			return;
		    }
			var jobid=cm.getPlayer().getJob();
			if (jobid==412||jobid==1411){

			if(getmoneyb()>=1778 && cm.getPlayer().getItemQuantity(3994720,false)<1){
				setmoneyb(-1778);
				cm.gainItem(3994720,1);
				cm.sendOk("����ɹ�");
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "������ר��" + " : " + "[" + cm.getChar().getName() + "]ͨ��������ʦ�����������Ӱ�����˺��������ܣ�")); 
				cm.dispose();
				return;
			}else {
			cm.sendOk("Ԫ������1778,�������Ѿ������������");
			cm.dispose();
			return;
			}
			}else {
			cm.sendOk("�ף������Ƿ����Ͱ�Ӱ�����˲���Ч����Ӵ��");
			cm.dispose();
			return;
			}

		 if (selection == 100) {
			if(cm.haveItem(4170005,35) ){
				cm.gainItem(4170005,-35);
				cm.gainItem(1912000,1);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "���һ����ġ�" + " : " + "[" + cm.getChar().getName() + "]ͨ���һ���Ʒ����������ﰰ�ӣ�")); 
				cm.dispose();
			}else{
				cm.sendOk("\t���ϲ��㡣");
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
	var sql = "UPDATE accounts SET moneyb = moneyb+"+xiezhi+"  WHERE id = "+accid+"  ;";
    var pstmt = conn.prepareStatement(sql);
	pstmt.executeUpdate();
	pstmt.close();
}
