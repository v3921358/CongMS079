/*���������qq1500663066*/



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
			text += "               #k"+�ʹڰ�+" #r#e#w �� �� ը ҩ Ͱ #n#k "+�ʹڰ�+"\r\n\r\n";
			text += "  "+è��+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+è��+"\r\n";
            
			
			text += "\t#r����ÿ��ըҩͰ�ɻ��1���þ��ƿ��ȼ��ɻ��ף����1������������5��#l\r\n\r\n"
			//text += "\t#L4##d#v4001128#�һ�#d#fUI/Basic/BtHide3/mouseOver/0#���þ�  (#r���� 1:1#d)#l\r\n\r\n"//3
			text += "\t#L44##d#v4001128#����#d#fUI/Basic/BtHide3/mouseOver/0#  (#r�ƿ�����ȼ�#d)#l\r\n\r\n"
			
            //text += "\t#L70##d#v4032226#�һ�#d#fUI/Basic/BtHide3/mouseOver/0#���  (#r���� 1:1000#d)#l\r\n\r\n"//3

			//text += "     #L2#��Ҷһ�#d#fUI/Basic/BtHide3/mouseOver/0#��ȯ  (#r���� 1800��:1��#d)#l\r\n"//3			  
			//text += "     #L3#��ȯ�һ�#d#fUI/Basic/BtHide3/mouseOver/0#��  Ҷ #l\r\n\r\n"//3
			//text += "     #L33#��ȯ�һ�#d#fUI/Basic/BtHide3/mouseOver/0#��  Ҷ  (#r���� 500:500#d)#l\r\n\r\n"//3
			//text += "     #L333#��ȯ�һ�#d#fUI/Basic/BtHide3/mouseOver/0#��  Ҷ  (#r���� 1000:1000#d)#l\r\n\r\n"//3
			//text += "     #L69##b100��Ҷ�һ�#d#fUI/Basic/BtHide3/mouseOver/0#10MP  (#r���� 100:10#d)#l\r\n\r\n"
            cm.sendSimple(text);
		}else if (selection == 48) {//��Ϸ����
				cm.dispose();
				cm.openNpc(3003302, 48);
        } 
		else if (selection == 44) {//��Ϸ����
				cm.dispose();
				cm.openNpc(9310072, "����ȼ�");
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
			if(cm.haveItem(4032392,100) ){
				cm.gainItem(4032392,-100);
				cm.gainItem(2049117,1);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "���һ����ġ�" + " : " + "[" + cm.getChar().getName() + "]ͨ���һ���Ʒ�������һ�Ż�����ᣡ")); 
				cm.dispose();
			}else{
				cm.sendOk("\t���ϲ��㡣");
				cm.dispose();
			}
		}
		else if (selection == 66) {
			if(cm.haveItem(4032391,100) ){
				cm.gainItem(4032391,-100);
				cm.gainItem(2340000,1);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "���һ����ġ�" + " : " + "[" + cm.getChar().getName() + "]ͨ���һ���Ʒ�������һ��ף�����ᣡ")); 
				cm.dispose();
			}else{
				cm.sendOk("\t���ϲ��㡣");
				cm.dispose();
			}
		}
		else if (selection == 99) {
			if(cm.haveItem(4170002,30) ){
				cm.gainItem(4170002,-30);
				cm.gainItem(1902001,1);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "���һ����ġ�" + " : " + "[" + cm.getChar().getName() + "]ͨ���һ���Ʒ�������һ�Ż�����ᣡ")); 
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
		}else if (selection == 3) {//��Ϸ����
				cm.dispose();
				cm.openNpc(9310072, 303);
        } 
		else if (selection == 33) {
			
			if(cm.getPlayer().getMeso() >= 500 ){ //��Ʒ����
				cm.getPlayer().modifyCSPoints(1,-500, true);//��ȯ
				cm.gainItem(4001126,500);

				
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "���һ����ġ�" + " : " + "[" + cm.getChar().getName() + "]�һ���500����Ҷ��")); 
		        cm.dispose();
			}else{
				cm.sendOk("\t��ȯ���㡣");
				cm.dispose();
			}
        }
		else if (selection == 333) {
			
			if(cm.getPlayer().getMeso() >= 1000 ){ //��Ʒ����
				cm.getPlayer().modifyCSPoints(1,-1000, true);//��ȯ
				cm.gainItem(4001126,1000);

				
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "���һ����ġ�" + " : " + "[" + cm.getChar().getName() + "]�һ���1000����Ҷ��")); 
		        cm.dispose();
			}else{
				cm.sendOk("\t��ȯ���㡣");
				cm.dispose();
			}
        }
		else if (selection == 2) { 
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
					cm.getPlayer().modifyCSPoints(2,zliang*1, true);		
                    cm.sendOk("�һ��ɹ������һ���:[#r"+(zliang)+"#k] ����");
					cm.worldMessage(6,"[�һ�����]����� "+cm.getName()+" Ŭ����ש,�����������ըҩͰ�һ��ˣ�"+(zliang*1)+" ���þ�");//����
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