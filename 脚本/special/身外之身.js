var �����Ʒ = "#v1302000#";
var x1 = "1302000,+1";// ��ƷID,����
var x2;
var x3;
var x4;

var ��Ҫ��ȯ = 5000;
function start() {
    status = -1;

    action(1, 0, 0);
}
function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    } else {
        if (status >= 0 && mode == 0) {

            cm.sendOk("��л��Ĺ��٣�");
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
            var text = "";
            for (i = 0; i < 10; i++) {
                text += "";
            }
				
			
            text += "  "+Сѩ��+"#v1003861#       #e#r��ӭ��������֮��    #v1003861#"+Сѩ��+"#k#n\r\n" 
			//ע���˷���ֻ�߱���ɫ 25% ~ 30% ���˺�          ��Ҫ�ﵽ 100% �˺�����ϵGM��������!  #v1003861##k#n\r\n"
			text += "#r#e----------------------------------------------#k#n\r\n";
			
			text += "ע���˷���߱���ɫ100%�˺�!�رշ����ݿ���:@�ر�����\r\n";
			
			text += "#L0##k" + aaa + "#d��������֮��(���ߺ����ȹر�����֮���ٿ�������)#l\r\n\r\n"
			if(cm.haveItem(3994769,1)){
			text += "      #L10##k"+Сѩ��+"#d< < <��������֮���> > >"+Сѩ��+"#l\r\n"
			text += "      #L11##k"+Сѩ��+"#r< < <�ر�����֮���> > >"+Сѩ��+"#l\r\n\r\n"

			}
            text += "             #L1##k" + aaa + "#r�ر�����֮��#k#l\r\n"
			text += "#L2#" + aaa + "#b��������#v3994759##n#l    #L3##K" + aaa + "#b����һʱ#v3994759##n\r\n\r\n"

            cm.sendSimple(text);
        } else if (status == 1) {
            if (selection == 0) {
				
                if (cm.getPlayer().getjf2() >= 1 ) {
					cm.sendOk("���Ѿ�����������֮�������ٴο�����");
					cm.dispose();
					
				}else if(!cm.haveItem(3994759,1)){
					cm.sendOk("��ò��û������Ķ���");
					cm.dispose();
					
				} else {					
					cm.getPlayer().cloneLook();
					cm.getPlayer().setjf2(cm.getPlayer().getjf2()+1);
					cm.sendOk("�ɹ���������֮��");
					cm.dispose();
				}
			
			 
                }else if (selection == 10) {
                if (cm.getPlayer().getjf4() >= 1 ) {
					cm.sendOk("���Ѿ�����������֮��2�������ٴο�����");
					cm.dispose();
					
				}else if(!cm.haveItem(3994769,1)){
					cm.sendOk("��ò��û������Ķ���");
					cm.dispose();
					
				} else {
					cm.getPlayer().cloneLook();
					cm.getPlayer().setjf4(cm.getPlayer().getjf4()+1);
					cm.sendOk("�ɹ���������֮��2��");
					cm.dispose();
				}
			
			 
                }else if (selection == 1) {
                if (cm.getPlayer().getjf2() < 1 ) {
					cm.sendOk("�㻹δ��������֮������رա�");
					cm.dispose();
				} else {
					cm.getPlayer().setjf2(cm.getPlayer().getjf2()-1);
					cm.getPlayer().disposeClones();
					cm.sendOk("�ѹر�����֮��,����Ҫ�����¿���~");
					cm.dispose();
                 }
				
            }else if (selection == 11) {
                if (cm.getPlayer().getjf4() < 1 ) {
					cm.sendOk("�㻹δ��������֮������رա�");
					cm.dispose();
				} else {
					cm.getPlayer().setjf4(cm.getPlayer().getjf4()-1);
					cm.getPlayer().disposeClones();
					cm.sendOk("�ѹر�����֮��,����Ҫ�����¿���~");
					cm.dispose();
                 }
				
            }else if (selection == 2) {
               if (!cm.checkNumSpace(4, 1)) {
			cm.sendOk("�����������ռ䲻��1��");
			cm.dispose();
			return;
		    }
			if(getmoneyb()>=1000 && cm.getPlayer().getItemQuantity(3994759,false)<1){
				setmoneyb(-1000);
				//cm.gainItemPeriod(3994720,1,30*24);//�����Ǹ�1����Ʒ��30*24Сʱ
				cm.gainItem(3994759,1);
				cm.sendOk("����ɹ�");
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "������֮��" + " : " + "[" + cm.getChar().getName() + "]ͨ�����򣬻������������֮���ܣ�")); 
				cm.dispose();
				return;
			}else {
			cm.sendOk("Ԫ������1000,�������Ѿ������������");
			cm.dispose();
			return;
			}
				
            }
			else if (selection == 3) {
               if (!cm.checkNumSpace(4, 1)) {
			cm.sendOk("�����������ռ䲻��1��");
			cm.dispose();
			return;
		    }
			if(cm.getPlayer().getItemQuantity(3994759,false)<1 && cm.getPlayer().getPrizeLog("��1") < 1){
				cm.getPlayer().setPrizeLog("��1");	
				cm.gainItemPeriod(3994759,1,1);
				cm.sendOk("��ȡ�ɹ�");
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "������֮��" + " : " + "[" + cm.getChar().getName() + "]�������������֮���ܣ�")); 
				cm.dispose();
				return;
			}else {
			cm.sendOk("���Ѿ�����������ˣ��������ù�һ����");
			cm.dispose();
			return;
			}
				
            }
        }
    }
}

var add = "#fEffect/CharacterEff/1022223/4/0#";
var ttt = "#fUI/UIWindow.img/Quest/icon9/0#";
var xxx = "#fUI/UIWindow.img/Quest/icon8/0#";
var sss = "#fUI/UIWindow.img/QuestIcon/3/0#";
var add = "#fEffect/CharacterEff/1112905/0/1#";//������
var aaa = "#fUI/UIWindow.img/Quest/icon9/0#";//��ɫ�Ҽ�ͷ
var zzz = "#fUI/UIWindow.img/Quest/icon8/0#";//��ɫ�Ҽ�ͷ
var sss = "#fUI/UIWindow.img/QuestIcon/3/0#";//ѡ�����
var ��̾�� = "#fUI/UIWindow/Quest/icon0#";
var ����new = "#fUI/UIWindow/Quest/icon5/1#";
var Բ�� = "#fUI/UIWindow/Quest/icon3/6#";
var ���� = "#fEffect/CharacterEff/1022223/4/0#";
var ��ɫ��ͷ = "#fUI/UIWindow/Quest/icon6/7#";
var �ٷ� = "#fEffect/CharacterEff/1003252/0/0#";
var ���� = "#fEffect/CharacterEff/1032063/0/0#";
var �� = "#fUI/UIWindow.img/AriantMatch/characterIcon/0#";
var s = "#fUI/StatusBar/BtClaim/normal/0#";
var h = "#fUI/CashShop/CSEffect/effect/1#";
var Сѩ�� = "#fEffect/CharacterEff/1003393/0/0#";
var ��� = "#fUI/CashShop/Base/Tab/Enable/2#";//���
var ����4 = "#fEffect/CharacterEff/1042176/1/1#"; // ʵ����찮�ġ�С�͡�
var ����2 = "#fEffect/CharacterEff/1022223/3/0#"; // ������ɫ�ۺ찮��
var ����1 = "#fEffect/CharacterEff/1003271/0/0#"; // ʵ��ۺ찮��
var �����Ц ="#fUI/GuildBBS/GuildBBS/Emoticon/Basic/2#";//�����Ц/1��/0΢Ц 

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
