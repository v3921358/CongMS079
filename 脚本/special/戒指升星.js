/*���������qq1500663066��327321366*/



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
			text += "                  #k"+�ʹڰ�+" #r#e#w �� Ʒ �� �� #n#k "+�ʹڰ�+"\r\n\r\n";
			text += "  "+è��+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+è��+"\r\n";
            
            text += "#L48##b#v1112426#�����ǡ�       #L81##b#v1112915#�����ǡ�\r\n\r\n"
			//text += "#L8##b#v1112422#�����ǡ�       \r\n\r\n"
			text += "#L4888##b#v1132115#�����ǡ�       #L488##b#v1012171#�����ǡ�\r\n\r\n"
            cm.sendSimple(text);
		}else if (selection == 81) {
				cm.dispose();
				cm.openNpc(9900004, "��������");
        }
		else if (selection == 8) {
				cm.dispose();
				cm.openNpc(9900004, "��ɫ������");
        }
		else if (selection == 48) {
				cm.dispose();
				cm.openNpc(9900004, "�ѹ�Ӣ����");
        } else if (selection == 488) {
				cm.dispose();
				cm.openNpc(9900004, "�������");
        }else if (selection == 4888) {
				cm.dispose();
				cm.openNpc(9900004, "��������");
        }
		else if (selection == 69) {
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