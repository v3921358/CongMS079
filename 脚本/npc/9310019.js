var ���� = "#fEffect/CharacterEff/1114000/2/0#";
var ���� = "#fEffect/CharacterEff/1022223/4/0#";
var ��ɫ��ͷ = "#fUI/UIWindow/Quest/icon6/7#";
var ������ = "#fUI/UIWindow/Quest/icon3/6#";
var ��ɫ��ͷ = "#fUI/UIWindow/Quest/icon2/7#";
var �ʹڰ� ="#fUI/GuildMark/Mark/Etc/00009004/16#";
var ���˲� ="#fUI/GuildMark/Mark/Plant/00003006/15#";
var ��ˮ ="#fUI/GuildMark/Mark/Pattern/00004008/15#";
var ������ ="#fUI/Basic/HScr7/disabled/base#";
var ������ ="#fUI/ChatBalloon/tutorial/w#";

var ����è ="#fUI/ChatBalloon/169/n#";
var è�� =  "#fUI/ChatBalloon/169/ne#";
var è�� =  "#fUI/ChatBalloon/169/nw#";
var �� =    "#fUI/ChatBalloon/169/e#";
var �� =    "#fUI/ChatBalloon/169/w#";
  
var ����è ="#fUI/ChatBalloon/169/s#";
var è���� ="#fUI/ChatBalloon/169/se#";
var è���� ="#fUI/ChatBalloon/169/sw#";

var ��ɫ ="#fUI/GuildMark/Mark/Pattern/00004003/16#";
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
            text += "                 #k"+�ʹڰ�+" #w#r#eССð�յ�#n#k "+�ʹڰ�+"\r\n";//#n#k�����㣺#r" + cm.getBeans() + "#k��\t\t//
			
            text += "    #L1##d" + ��ɫ��ͷ + "������Ӹ���#l         #L2##d" + ��ɫ��ͷ + "������Ӹ���#l\r\n\r\n"//3
            text += "    #L3##d" + ��ɫ��ͷ + "�����Ӹ���#l         #L4##d" + ��ɫ��ͷ + "�����Ӹ���#l\r\n\r\n"//3
            text += "    #L5##d" + ��ɫ��ͷ + "������Ӹ���#l         #L6##d" + ��ɫ��ͷ + "������Ӹ���#l\r\n\r\n"//3
            text += "    #L7##d" + ��ɫ��ͷ + "�����Ӹ���#l         #L8##d" + ��ɫ��ͷ + "����ŷ������Ҷ����#l\r\n\r\n\r\n\r\n\r\n"//3
            cm.sendSimple(text);
        } else if (selection == 1) { //������Ӹ���
            cm.dispose();
			cm.openNpc(1012112, 0);
        } else if (selection == 2) {  //������Ӹ���
            cm.dispose();
			cm.openNpc(9020000, 0);
        } else if (selection == 3) { //�����Ӹ���
            cm.dispose();
			cm.openNpc(2040034, 0);
        } else if (selection == 4) {//�����Ӹ���
            cm.dispose();
			cm.openNpc(2013000, 0);
        } else if (selection == 5) {//������Ӹ���
            cm.warp(300030100);
            cm.dispose();
        } else if (selection == 6) {//������Ӹ���
            cm.dispose();
			cm.openNpc(2094000, 0);
        } else if (selection == 7) {//�����Ӹ���
            cm.dispose();
			cm.warp(800000000, 0);
        } else if (selection == 8) {//��Ů��Ӹ���
            cm.dispose();
			cm.warpParty(261000011, 0);
        }
    }
}


