var CY0 = "��������������������������������������������������������������������";
var CY1 = "��       - ���� -       ��";
var CY2 = "�� �ű�����  �����ƽű� ��";
var CY3 = "�� ����֧�� �� ��Ϸ���� ��";
var CY4 = "�� �ף���ӡ�  ��ͼ���� ��";
var CY5 = "�� �Ӷܷ�����  �۵�½�� ��";
var CY6 = "�ǩ�����������������������������������������������������������������";
var CY7 = "��   ΨһQQ:12384161    ��";
var CY8 = "��������������������������������������������������������������������";

var money;

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
            var text = "#r�����������������з���������Ӫ��֧!\r\n��л���Ĵ���֧��!!!!!!!\r\n\r\n";
            //text += "#L1##b���߹���������ֵ��#k#l\r\n";
            text += "#L2##b���뿨�Ž��жһ�#k#l\r\n";
            cm.sendSimple(text);
            //cm.sendSimple(text);
        } else if (status == 1) {
            if(selection == 1){
                cm.openWeb("https://shop108398141.taobao.com/");
                cm.playerMessage(5, "���ڴ�������ֵ��վ�����û�е�����ֵ��վ����ϵȺ��!"); //���˿����ĶԻ� 5��ɫ�� 6��ɫ�� 1Ϊ����
                cm.dispose();
                return;
            }else if(selection == 2){
                cm.sendGetText("#b\t\t\t\t\t\t\t\t\r\n#d�������ֵ���ţ���CTRL+V����ճ��):");
            }
        } else if(status == 2){
            money = cm.getText();
            if(money === ""){
                cm.sendOk("����δ�����κο���!");
                cm.dispose();
            }
            cm.sendYesNo("�Ƿ���г�ֵ���һ���");
        } else if(status == 3){
            //0����ȯ��1�����þ�2��Ԫ��
            var ret = cm.donateMoney(money, cm.getPlayer().getAccountID(), cm.getPlayer().getName());
            //cm.getPlayer().dropMessage(5, ret);
            var type = ret[0];
            var value = ret[1];
            if(type == -1 && value == -1){
                cm.sendOk("�����Ѿ���ʹ�ù����޷���ֵ��");
                cm.dispose();
                return;
            }
            if(type == 0 && value == 0){
                cm.sendOk("��ֵʧ�ܣ����Ų�����!\r\n");
            }else{
                if(type == 0){
                    cm.gainNX(value);
                    cm.sendOk("��ֵ��ȯ��#r" + value +"#k#l���ɹ�!!!");
                }else if(type == 1){
                    cm.gainDY(value);
                    cm.sendOk("��ֵ����ȯ��#r" + value +"#k#l���ɹ�!!!");
                }else if(type == 2){
                    cm.setmoneyb(value);
                    cm.sendOk("��ֵԪ����#r" + value +"#k#l���ɹ�!!!");
                }
            }
            cm.dispose();
        }
    }
}

var acc = "#fEffect/CharacterEff/1112903/0/0#";//������
var aaa = "#fUI/UIWindow.img/Quest/icon9/0#";//��ɫ�Ҽ�ͷ
var zzz = "#fUI/UIWindow.img/Quest/icon8/0#";//��ɫ�Ҽ�ͷ
var sss = "#fUI/UIWindow.img/QuestIcon/3/0#";//ѡ�����
var ��ɫ�ǵ� = "#fUI/UIWindow.img/PvP/Scroll/enabled/next2#";
var ��ɫ��ͷ = "#fUI/UIWindow/Quest/icon2/7#";
var ��ɫ��ͷ = "#fUI/UIWindow/Quest/icon6/7#";
var Բ�� = "#fUI/UIWindow/Quest/icon3/6#";
var ����new = "#fUI/UIWindow/Quest/icon2/7#";
var ����ne = "#fUI/UIWindow/Quest/icon6/7#";
var ��̾�� = "#fUI/UIWindow/Quest/icon0#";
var ������ͷ = "#fUI/Basic/BtHide3/mouseOver/0#";
var ����è ="#fUI/ChatBalloon/37/n#";
var è�� =  "#fUI/ChatBalloon/37/ne#";
var è�� =  "#fUI/ChatBalloon/37/nw#";
var �� =    "#fUI/ChatBalloon/37/e#";
var �� =    "#fUI/ChatBalloon/37/w#";
var ����è ="#fUI/ChatBalloon/37/s#";
var è���� ="#fUI/ChatBalloon/37/se#";
var è���� ="#fUI/ChatBalloon/37/sw#";
var �ʹڰ� ="#fUI/GuildMark/Mark/Etc/00009004/16#";
var ��ɫ�ǵ� = "#fUI/UIWindow.img/PvP/Scroll/enabled/next2#";
var ��ݮ = "#fUI/GuildMark/Mark/Plant/00003000/1#"; // ��ɫ��ݮ
var ��ݮ1 = "#fUI/GuildMark/Mark/Plant/00003000/10#"; // ����ɫ��ݮ
var ��ݮ2 = "#fUI/GuildMark/Mark/Plant/00003000/11#"; // ��ɫ��ݮ
var ��ݮ3 = "#fUI/GuildMark/Mark/Plant/00003000/15#"; // ��ɫ��ݮ
var ��ݮ4 = "#fUI/GuildMark/Mark/Plant/00003000/3#"; // ��ɫ��ݮ
var ��ݮ5 = "#fUI/GuildMark/Mark/Plant/00003000/8#"; // ��ɫ��ݮ
var С���� = "#fItem/Etc/0427/04270001/Icon9/0#";  //
var �ʺ� ="#fEffect/ItemEff/1071085/effect/walk1/2#";
var ����� = "#fItem/Etc/0427/04270001/Icon9/1#";  //
var С�� = "#fEffect/CharacterEff/1112960/3/0#";  //а��С�� ��С��
var Сˮ�� = "#fItem/Etc/0427/04270001/Icon10/5#";  //
var ��ˮ�� = "#fItem/Etc/0427/04270001/Icon10/4#";  //
var �찮�� ="#fEffect/CharacterEff/1112905/0/1#";
var ���ͼ�� = "#fUI/UIWindow.img/QuestIcon/7/0#";
var aaa = "#fUI/UIWindow.img/Quest/icon9/0#";
var zzz = "#fUI/UIWindow.img/Quest/icon8/0#";
var sss = "#fUI/UIWindow.img/QuestIcon/3/0#";