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
			text += "                  #k"+�ʹڰ�+" #r#e#w �� ҵ �� Ʒ #n#k "+�ʹڰ�+"\r\n\r\n";
			text += "  "+è��+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+è��+"\r\n";
            text += "    ע��:���е��ߵĽ�ָһ��ֻ����һ��,����ֻ�۲���.\r\n";
			text += "\t#L66##k[#r���200��#k]+[#v4170000##r#c4170000##k/10]�һ�#d#fUI/Basic/BtHide3/mouseOver/0##i1112426:##l\r\n\r\n"
			text += "\t\t�������������Ի�����#l\r\n"
			text += "\t----------------------------------------------#l\r\n"
			text += "\t#L6##k[#r���200��#k]+[#v4310020##r#c4310020##k/20]�һ�#d#fUI/Basic/BtHide3/mouseOver/0##i1112422:##l\r\n\r\n"
			text += "\t\tͨ���ر�ͼ�ڱ������Ի�ñ��ر�#l\r\n"
			text += "\t----------------------------------------------#l\r\n"
			text += "\t#L1##k[#r���200��#k]+[#v4310108##r#c4310108##k/20]�һ�#d#fUI/Basic/BtHide3/mouseOver/0##i1112425:##l\r\n\r\n"
			text += "\t\tͨ�����￨Ƭ���������Ի�ü�����#l\r\n"
						text += "\t----------------------------------------------#l\r\n"
			text += "\t#L666##k[#v1132004##r#c1132004##k/5]�һ�#d#fUI/Basic/BtHide3/mouseOver/0##i1132115:##l\r\n\r\n"
			text += "\t\t������긱�������Ի������#l\r\n"
			text += "\t----------------------------------------------#l\r\n"
			//text += "\t#L67##k[#v4032392##r#c4032392##k/100]�һ�#d#fUI/Basic/BtHide3/mouseOver/0##v2049118##l\r\n\r\n"//3
            //text += "\t#L99##k[#v4170002##r#c4170002##k/30]�һ�#d#fUI/Basic/BtHide3/mouseOver/0##v1902001##l\r\n\r\n"//3
			//text += "\t#L100##k[#v4170005##r#c4170005##k/35]�һ�#d#fUI/Basic/BtHide3/mouseOver/0##v1912000##l\r\n\r\n"//3
			//text += "\t#L1100##k[#v4001128##r#c4001128##k/100]�һ�#d#fUI/Basic/BtHide3/mouseOver/0##v4001126##l50��\r\n\r\n"//3
            
			
			
			//text += "     #L68##b100��Ҷ�һ�#d#fUI/Basic/BtHide3/mouseOver/0#10HP  (#r���� 100:10#d)#l\r\n\r\n"
			//text += "     #L69##b100��Ҷ�һ�#d#fUI/Basic/BtHide3/mouseOver/0#10MP  (#r���� 100:10#d)#l\r\n\r\n"
            cm.sendSimple(text);
		}else if (selection == 48) {//��Ϸ����
				cm.dispose();
				cm.openNpc(9310072, 48);
        } else if (selection == 1) {
			if(cm.haveItem(1112425,1)){
				cm.sendOk("\t���Ѿ����������ָ��һ��ֻ����һ����");
				cm.dispose();
				return;
				}else{
			if(cm.getMeso() >= 2000000 && cm.haveItem(4310108,20) ){
				cm.gainItem(4310108,-20);
				cm.gainMeso(-2000000);
				cm.gainItem(1112425,1);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "���һ����ġ�" + " : " + "[" + cm.getChar().getName() + "]ͨ���һ���Ʒ�������һ���𻳱��ָ��")); 
				cm.dispose();
			}else{
				cm.sendOk("\t��һ���ϲ��㡣");
				cm.dispose();
				}
				}
		}
		else if (selection == 666) {
			if(cm.haveItem(1132004,5) ){
				cm.gainItem(1132004,-5);
				cm.gainItem(1132115,1);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "���һ����ġ�" + " : " + "[" + cm.getChar().getName() + "]ͨ���һ���Ʒ�������һ����������")); 
				cm.dispose();
			}else{
				cm.sendOk("\t���ϲ��㡣");
				cm.dispose();
			}
		}else if (selection == 6) {
			if(cm.haveItem(1112422,1)){
				cm.sendOk("\t���Ѿ�������ɫ���ָ��һ��ֻ����һ����");
				cm.dispose();
				return;
				}else{
			if(cm.getMeso() >= 2000000 && cm.haveItem(4310020,20) ){
				cm.gainItem(4310020,-20);
				cm.gainMeso(-2000000);
				cm.gainItem(1112422,1);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "���һ����ġ�" + " : " + "[" + cm.getChar().getName() + "]ͨ���һ���Ʒ�������һ����ɫ���ָ��")); 
				cm.dispose();
			}else{
				cm.sendOk("\t��һ���ϲ��㡣");
				cm.dispose();
				}
				}
		}
		else if (selection == 66) {
			if(cm.haveItem(1112426,1)){
				cm.sendOk("\t���Ѿ������ѹ�Ӣ��ָ��һ��ֻ����һ����");
				cm.dispose();
				return;
				}else{
			if (cm.getMeso() >= 2000000 && cm.haveItem(4170000,10)) { 
			    cm.gainItem(-4170000,10);
			    cm.gainMeso(-2000000);
				cm.gainItem(1112426,1);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "���һ����ġ�" + " : " + "[" + cm.getChar().getName() + "]ͨ����ָ�һ��������һ���ѹ�Ӣ��ָ��")); 
				cm.dispose();
			}else{
				cm.sendOk("\t��һ���ϲ��㡣");
				cm.dispose();
			}
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