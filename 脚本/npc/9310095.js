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
            var tex2 = "";
            var text = "";
            for (i = 0; i < 10; i++) {
                text += "";
            }
			text += "                   #k"+�ʹڰ�+" #r#e#wССð�յ�#n#k "+�ʹڰ�+"\r\n\r\n";//#n#k�����㣺#r" + cm.getBeans() + "#k��\t\t//"
			text += "   "+è��+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+è��+"#d\r\n\r\n";	
			text += "\t#L1#�ȼ����а�#l\t#L2#��Ʒ���ʲ�ѯ#l\t#L3#ɾ����Ʒ#l";	
            cm.sendSimple(text);
        } else if (selection == 1) {
			cm.showlvl();
            cm.dispose();
        }else if (selection == 2) {
			cm.dispose();
			cm.openNpc(9310095,1);
		}else if (selection == 3) {
			cm.dispose();
			cm.openNpc(9900004,17);
		}
	}
}