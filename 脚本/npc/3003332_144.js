var CY0 = "��������������������������������������������������������������������";
var CY1 = "��       - ���� -       ��";
var CY2 = "�� �ű�����  �����ƽű� ��";
var CY3 = "�� ����֧�� �� ��Ϸ���� ��";
var CY4 = "�� �ף���ӡ�  ��ͼ���� ��";
var CY5 = "�� �Ӷܷ�����  �۵�½�� ��";
var CY6 = "�ǩ�����������������������������������������������������������������";
var CY7 = "��   ΨһQQ:12384161    ��";
var CY8 = "��������������������������������������������������������������������";
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
			text += "\t\t"+�ʺ�+"  #e#d �� ȡ �� �� #k#n  #r  "+�ʺ�+"#b#k#n\r\r\n"+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+"\r\n\r\n";
            
			//text += "\t#L66##k��ȡ#d#fUI/Basic/BtHide3/mouseOver/0#�������#l\r\n\r\n"
			//text += "\t#L72##k��ȡ#d#fUI/Basic/BtHide3/mouseOver/0#������#l\r\n\r\n"//3
			text += "\t#L67##k��ȡ#d#fUI/Basic/BtHide3/mouseOver/0#Ů�����#l\r\n\r\n"
			//text += "\t#L488##k��ȡ#d#fUI/Basic/BtHide3/mouseOver/0#�ع����#l\r\n\r\n"
			
            //text += "\t#L99##k[#v4170002##r#c4170002##k/30]��ȡ#d#fUI/Basic/BtHide3/mouseOver/0##v1902001##l\r\n\r\n"//3
			//text += "\t#L100##k[#v4170005##r#c4170005##k/35]��ȡ#d#fUI/Basic/BtHide3/mouseOver/0##v1912000##l\r\n\r\n"//3
			//text += "\t#L1100##k[#v4001128##r#c4001128##k/100]��ȡ#d#fUI/Basic/BtHide3/mouseOver/0##v4001126##l50��\r\n\r\n"
            
			
			
			//text += "     #L68##b100��Ҷ�һ�#d#fUI/Basic/BtHide3/mouseOver/0#10HP  (#r���� 100:10#d)#l\r\n\r\n"
			//text += "     #L69##b100��Ҷ�һ�#d#fUI/Basic/BtHide3/mouseOver/0#10MP  (#r���� 100:10#d)#l\r\n\r\n"
            cm.sendSimple(text);
		}else if (selection == 48) {//��Ϸ����
				cm.dispose();
				cm.openNpc(9310072, 48);
        }else if (selection == 488) {//��Ϸ����
				cm.dispose();
				cm.openNpc(3003332, "ά�����");
        }
		else if (selection == 72) { 
			cm.dispose();
				cm.openNpc(9900004, "������");
        }
		else if (selection == 70) { 
			var zliang = cm.getPlayer().getItemQuantity(4032226, false);
			if (zliang == 0) {
                    cm.sendOk("�����Ʒ����һ�.");
                    status = -1;
                } else {
                    beauty = 70
					cm.sendYesNo("��ǰ����: #r"+zliang+"#k �����Ƿ������ȫ���һ���");
					}
        }
		else if (selection == 68) {
			var PlayselfMaxHp = cm.getPlayer().getMaxHp();
			if(cm.haveItem(4001126,100) ){
				cm.gainItem(4001126,-100);
				cm.getPlayer().getStat().setMaxHp(cm.getPlayer().getStat().getMaxHp()+ 10);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "���һ����ġ�" + " : " + "[" + cm.getChar().getName() + "]ͨ���һ���Ʒ�������10HP��")); 
				cm.dispose();
			}else{
				cm.sendOk("\t���ϲ��㡣");
				cm.dispose();
			}
		}else if (selection == 69) {
			var PlayselfMaxMp = cm.getPlayer().getMaxMp();
			if(cm.haveItem(4001126,100) ){
				cm.gainItem(4001126,-100);
				cm.getPlayer().getStat().setMaxMp(cm.getPlayer().getStat().getMaxMp()+ 10);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "���һ����ġ�" + " : " + "[" + cm.getChar().getName() + "]ͨ���һ���Ʒ�������10MP��")); 
				cm.dispose();
			}else{
				cm.sendOk("\t���ϲ��㡣");
				cm.dispose();
			}
		}
		else if (selection == 67) {
			if(cm.haveItem(4001239,1) ){
				cm.gainItem(4001239,-1);
				cm.gainItem(1142574,10,10,10,10,50,50,5,5,5,5,8,8,0,0);//Ů�����
				cm.gainItem(5150038,1);//��������������
				cm.gainMeso(2000000);//�����200��
				cm.gainDY(100000);//�����þ�10��
				cm.getPlayer().modifyCSPoints(1,50000, true);//�����1Ϊ���0Ϊ���þ�
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "��Ů����֤��" + " : " + "[" + cm.getChar().getName() + "]ͨ��Ů����֤�������Ů�������")); 
				cm.dispose();
			}else{
				cm.sendOk("\t��Ǹ������û���ʸ���ȡŮ�����Ŷ�����ҹ���ȷ�Ϻ�������ȡ��");
				cm.dispose();
			}
		}
		else if (selection == 66) {
			if (cm.getPlayer().getPrizeLog("���64") < 1 ) {
				if (!cm.checkNumSpace(4, 5)) {
			    cm.sendOk("����������,�ռ䲻��5��");
			    cm.dispose();
			    return;
		        }
				cm.getPlayer().setPrizeLog("���64");
				//cm.gainItem(4170005,50);//��ߵ�
				//cm.gainItem(4170006,50);//��յ�
				cm.gainItem(5150038,1);//��������������
				cm.gainMeso(20000000);//�����1000��
				cm.gainItem(2614000,10);//�ƹ�ʯ
				//cm.gainItem(4000313,150);//���ױ�
				cm.gainDY(100000);//�����þ�1��
				cm.getPlayer().modifyCSPoints(1,200000, true);//�����1Ϊ���0Ϊ���þ�
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "����ȡ�����" + " : " + "[" + cm.getChar().getName() + "]��ȡ�˳������������˷��Ľ�����")); 
				cm.dispose();
			}else{
				cm.sendOk("\t��Ǹ����һ��ֻ������ȡһ��Ӵ��");
				cm.dispose();
			}
		}
		else if (selection == 99) {
			if(cm.haveItem(4170002,30) ){
				cm.gainItem(4170002,-30);
				cm.gainItem(1902001,1);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "���һ����ġ�" + " : " + "[" + cm.getChar().getName() + "]ͨ���һ���Ʒ�������Ұ�����")); 
				cm.dispose();
			}else{
				cm.sendOk("\t���ϲ��㡣");
				cm.dispose();
			}
		}
		else if (selection == 100) {
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
		else if (selection == 1100) {
			if(cm.haveItem(4001128,100) ){
				cm.gainItem(4001128,-100);
				cm.gainItem(4001126,50);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "���һ����ġ�" + " : " + "[" + cm.getChar().getName() + "]ͨ���һ���Ʒ�������50����Ҷ��")); 
				cm.dispose();
			}else{
				cm.sendOk("\t���ϲ��㡣");
				cm.dispose();
			}
		}

		else if (selection == 5) {
			if(cm.haveItem(4170005,1) && cm.haveItem(4170013,1) && cm.haveItem(4170002,1)){
				cm.gainItem(4170005,-1);
				cm.gainItem(4170013,-1);
				cm.gainItem(4170002,-1);
				cm.gainItem(2340000,1);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "�����һ���" + " : " + "[" + cm.getChar().getName() + "]ͨ���Ŷ��������棬�һ���һ��ף�����ᣡ")); 
				cm.dispose();
			}else{
				cm.sendOk("\t���ϲ��㡣");
				cm.dispose();
			}
		}else if (selection == 6) {
			if(cm.haveItem(4170001,1) && cm.haveItem(4170004,1) && cm.haveItem(4170009,1) ){
				cm.gainItem(4170001,-1);
				cm.gainItem(4170004,-1);
				cm.gainItem(4170009,-1);
				cm.gainItem(2049116,1);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "�����һ���" + " : " + "[" + cm.getChar().getName() + "]ͨ���Ŷ��������棬�һ���һ�����������ᣡ")); 
				cm.dispose();
			}else{
				cm.sendOk("\t���ϲ��㡣");
				cm.dispose();
			}
		}else if (selection == 3) {
			
			if(cm.getPlayer().getMeso() >= 100 ){ //��Ʒ����
				cm.getPlayer().modifyCSPoints(1,-100, true);//��ȯ
				cm.gainItem(4001126,100);
				//cm.gainMeso(+18000000);//�����
				
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "���һ����ġ�" + " : " + "[" + cm.getChar().getName() + "]�һ���100����Ҷ��")); 
		        cm.dispose();
			}else{
				cm.sendOk("\t��ȯ���㡣");
				cm.dispose();
			}
        }else if (selection == 2) { 
			/*if(cm.getPlayer().getBossLogD("��Ҷһ���ȯ") > 4){
				cm.sendOk("\t�����Ѿ��һ���5��.");
				cm.dispose();
				return;
			}*/
			if(cm.getPlayer().getMeso() >= 18000000 ){ //��Ʒ����
				cm.getPlayer().modifyCSPoints(1,10000, true);//��ȯ
				cm.gainMeso(-18000000);
				cm.getPlayer().setBossLog("��Ҷһ���ȯ");
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "�����һ���" + " : " + "[" + cm.getChar().getName() + "]Ŭ����ש�һ���10000��ȯ��")); 
		        cm.dispose();
			}else{
				cm.sendOk("\t��Ҳ��㡣");
				cm.dispose();
			}
        } else if (selection == 1) { 
			var zliang = cm.getPlayer().getItemQuantity(4001126, false);
			if (zliang == 0) {
                    cm.sendOk("�����Ʒ����һ�.");
                    status = -1;
                } else {
                    beauty = 1
					cm.sendYesNo("��ǰ����: #r"+zliang+"#k �����Ƿ������ȫ���һ���");
					}
        } else if (selection == 4) { 
			var zliang = cm.getPlayer().getItemQuantity(4001128, false);
			if (zliang == 0) {
                    cm.sendOk("�����Ʒ����һ�.");
                    status = -1;
                } else {
                    beauty = 4
					cm.sendYesNo("��ǰ����: #r"+zliang+"#k �����Ƿ������ȫ���һ���");
					}
        }  else if (status == 2) {
            if (beauty == 1) {
				var zliang = cm.getPlayer().getItemQuantity(4001126, false);
                if (zliang > 0){
					cm.removeAll(4001126);
					cm.gainMeso(8000*zliang);					
							
                    cm.sendOk("�һ��ɹ������һ���:[#r"+(zliang)+"#k] ����");
					cm.worldMessage(6,"[���һ�]����� "+cm.getName()+" Ŭ����ש,���������һ��ˣ�"+(zliang*8000)+" ��ҡ�");
					cm.dispose();
                } else {
                    cm.sendOk("������Ʒ���㣬�޷��һ���");
                    cm.dispose()
                }            		
            }if (beauty == 4) {
				var zliang = cm.getPlayer().getItemQuantity(4001128, false);
                if (zliang > 0){
					cm.removeAll(4001128);  
					cm.getPlayer().modifyCSPoints(2,zliang*2, true);		
                    cm.sendOk("�һ��ɹ������һ���:[#r"+(zliang)+"#k] ����");
					cm.worldMessage(6,"[�һ�����]����� "+cm.getName()+" Ŭ����ש,�����������ըҩͰ�һ��ˣ�"+(zliang*2)+" ���þ�");//����
					cm.dispose();
                } else {
                    cm.sendOk("������Ʒ���㣬�޷��һ���");
                    cm.dispose()
                }
            }
			if (beauty == 70) {
				var zliang = cm.getPlayer().getItemQuantity(4032226, false);
                if (zliang > 0){
					cm.removeAll(4032226);  
					cm.getPlayer().modifyCSPoints(1,zliang*1000, true);	//�����*������1Ϊ���2Ϊ���þ�	
                    cm.sendOk("�һ��ɹ������һ���:[#r"+(zliang)+"#k] ����");
					cm.worldMessage(6,"[�һ�����]����� "+cm.getName()+" Ŭ����ש,����������ûƽ�����һ��ˣ�"+(zliang*1000)+" ���");//����
					cm.dispose();
                } else {
                    cm.sendOk("������Ʒ���㣬�޷��һ���");
                    cm.dispose()
                }
            }
        }
    }
}

var ���� ="#fEffect/SetEff/208/effect/walk2/4#";
var ����1 ="#fEffect/SetEff/208/effect/walk2/3#";
var С�� ="#fMap/MapHelper/weather/birthday/2#";
var �һ� ="#fMap/MapHelper/weather/rose/4#";
var ���Ҷ ="#fMap/MapHelper/weather/maple/2#";
var ���Ҷ ="#fMap/MapHelper/weather/maple/1#";
var �ʺ� ="#fEffect/ItemEff/1071085/effect/walk1/2#";
var ����è ="#fUI/ChatBalloon/37/n#";
var è�� =  "#fUI/ChatBalloon/37/ne#";
var è�� =  "#fUI/ChatBalloon/37/nw#";
var �� =    "#fUI/ChatBalloon/37/e#";
var �� =    "#fUI/ChatBalloon/37/w#";
var ����è ="#fUI/ChatBalloon/37/s#";
var è���� ="#fUI/ChatBalloon/37/se#";
var è���� ="#fUI/ChatBalloon/37/sw#";