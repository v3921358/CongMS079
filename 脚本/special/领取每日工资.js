



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
			text += "                #k"+�ʹڰ�+" #r#e#w �� ȡ �� �� #n#k "+�ʹڰ�+"\r\n\r\n";
			text += "  "+è��+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+è��+"\r\n";
			
            text += "��ඣ�ֻҪ��ӵ�й��ʿ����Ϳ�����������ÿ����ȡ���ʹ���\r\n\r\n";
			text += "#bÿ�춼��������ȡһ�Σ��зḻ�����ʸ���������Ϸ����һ��#k\r\n\r\n";
			text += "#d�������𱭣���ң���Ҷ������ң�����֮�ģ���ߺ���յ�#k\r\n\r\n";
			text += "#d���ͣ��ڰ�����BOSS��Ʒ����BOSSÿ����ս����������10�Σ�#k\r\n\r\n\r\n";
			
			text += "\t\t  #L1##r�� �� �� �����ȡ �� �� ��#l\r\n\r\n"
		
            cm.sendSimple(text);
		} 
		else if (selection == 1) { 
			if(cm.getBossLog("����") >= 1){
				cm.sendOk("\t�����Ѿ����������Ӵ������������.");
				cm.dispose();
				return;
			}
			if (!cm.checkNumSpace(4, 6)) {
			cm.sendOk("�����������ռ䲻��6����Ԥ���ÿ���λ�ã��Ҳſ��Ը�������Ӵ��");
			cm.dispose();
			return;
		    }
			var zliang = cm.getPlayer().getItemQuantity(3700067, false);
			if (zliang == 0) {
                    cm.sendOk("���û�й��ʿ�����ȡ��������Ӵ��");
                    cm.dispose();
				    return;
                } else {
					cm.getPlayer().setBossLog('����');
					
			        cm.gainMeso(1000000);//���100��
					cm.gainItem(4001126,10);//��Ҷ
				    cm.gainItem(4170006,5);//��յ�
					cm.gainItem(4170005,5);//��ߵ�
					cm.gainItem(4001230,5);//����֮�ģ��̣�
					cm.gainItem(4000463,2);//��������
					cm.gainItem(4000038,2);//��
				    Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "�����ɡ�" + " : " + "[" + cm.getChar().getName() + "]ͨ����ȡ���ʣ�����˷������ʣ�")); 
				    cm.dispose();
					return;
					}
        } 
		
		  
    }
}