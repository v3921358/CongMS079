/* ==================
 �ű�����: ��ͨ��Ա
 �ű����ߣ�����
 ��ϵ��ʽ��804295666
 =====================
 */

var inputName;
var Silver = 30;
var Gold = 60;
var Diamond = 90;

var NoobExchange    = 5220020;  //����VIP�һ���
var SilverExchange  = 5220019;  //����VIP�һ���
var GoldExchange    = 5220022;  //�ƽ�VIP�һ���
var DiamondExchange = 5220023;  //��ʯVIP�һ���
var PowerExchange   = 5220024;  //����VIP�һ���

var NoobItemid      = 4310195;  //����VIP
var SilverItemid    = 4310197;  //����VIP
var GoldItemid      = 4310198;  //�ƽ�VIP
var DiamondItemid   = 4310199;  //��ʯVIP
var PowerItemid     = 4310200;  //����VIP

var flag = 0; //��ʶ�������ƽ���ʯ��
var ExchangeFlag = 0;//�һ���־

var NoobReward = [
    [4001126, 20], //��Ҷ
    [4000463, 1], //��������
    [4000313, 1],  //�ƽ��Ҷ
    [4170005, 1],  //��ߵ�
    [4170006, 1],  //��յ�
    [4001128, 200], //��ҩͰ
    [4032391, 5], //�������Ƭ1
    [4032392, 5] //�������Ƭ2
];

var SilverReward = [
    [4001126, 50], //��Ҷ
    [4000463, 2], //��������
    [4000313, 2],  //�ƽ��Ҷ
    [4170005, 1],  //��ߵ�
    [4170006, 1],  //��յ�
    [4032391, 10], //�������Ƭ1
    [4032392, 10] //�������Ƭ2

];
var GoldReward = [
    [4001126, 100], //��Ҷ
    [4000463, 5], //��������
    [4000313, 5], //�ƽ��Ҷ
    [4170005, 2],  //��ߵ�
    [4170006, 2],  //��յ�
    [4032391, 20], //�������Ƭ1
    [4032392, 20]  //�������Ƭ2
];
var DiamondReward = [
    [4001126, 200], //��Ҷ
    [4000463, 8], //��������
    [4000313, 8], //�ƽ��Ҷ
    [4170005, 3],  //��ߵ�
    [4170006, 3],  //��յ�
    [4032391, 35], //�������Ƭ1
    [4032392, 35]  //�������Ƭ2

];

//VIP�һ�����
var ExchangeItemid = [
    [NoobExchange,    1, NoobItemid,    7 * 24,  "����VIP"], //1������VIP�һ���һ�����VIP7��
    [SilverExchange,  1, SilverItemid,  30 * 24, "����VIP"], //1�Ű���VIP�һ���һ�����VIP30��
    [GoldExchange,    1, GoldItemid,    30 * 24, "�ƽ�VIP"],
    [DiamondExchange, 1, DiamondItemid, 30 * 24, "��ʯVIP"]
];


var RewardArray = [
    [NoobItemid,    50000,  200, NoobReward,  "����VIP"],
    [SilverItemid,  100000, 300, SilverReward,  "����VIP"],
    [GoldItemid,    200000, 600, GoldReward,    "�ƽ�VIP"],
    [DiamondItemid, 500000, 900, DiamondReward, "��ʯVIP"]
]

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
            var text = "";
            text += "#d#e������Ϊ������ð�ջ�Ա��������ͬ�ȼ��Ļ�Ա�����õ���ͬ����Ʒ�Ͳ��ϣ�#k#l\r\n\r\n";
            text += "#L1#" +��ɫ��ͷ+ " #b����һ���°�����Ա��" + Silver + "Ԫ��#k#l\r\n\r\n";
            text += "#L2#" +��ɫ��ͷ+ " #b����һ���»ƽ��Ա��" + Gold + "Ԫ��#k#l\r\n\r\n";
            text += "#L3#" +��ɫ��ͷ+ " #b����һ������ʯ��Ա��" + Diamond + "Ԫ��#k#l\r\n\r\n";
            text += "#L4#" +��ɫ��ͷ+ " #b��ȡ��Ա����#k#l\r\n\r\n";
            text += "#L5#" +��ɫ��ͷ+ " #bʹ�öһ���һ�VIP#k#l\r\n";
            cm.sendSimple(text);
        } else if (status == 1) {
            if(selection == 1){
                flag = 1;
                cm.sendYesNo("#b������Ա��Ҫ" + Silver + "Ԫ�����Ƿ�ȷ�Ϲ���#l!");
            }else if(selection == 2){
                flag = 2;
                cm.sendYesNo("#b�ƽ��Ա��Ҫ" + Gold + "Ԫ�����Ƿ�ȷ�Ϲ���#l!");
            }else if(selection == 3){
                flag = 3;
                cm.sendYesNo("#b��ʯ��Ա��Ҫ" + Diamond + "Ԫ�����Ƿ�ȷ�Ϲ���#l!");
            }else if(selection == 4){
                var RewardText = "ȷ����ȡÿ�ջ�Ա������\r\n����VIPÿ�ս����������ȡ#v5211047#һ�ţ�ʱ��ʹ��3Сʱ��\r\n";
                flag = 4
                for(var i = 0; i < RewardArray.length; i++){
                    RewardText += "#v" + RewardArray[i][0] + "# ��Աÿ�ս�����\r\n"
                    RewardText += "#r��ң�x " + RewardArray[i][1] + "      ";
                    RewardText += "��ȯ��x " + RewardArray[i][2] + "\r\n#k#l";
                    var Tmp = RewardArray[i][3];
                    for(var j = 0; j < Tmp.length; j++){
                        RewardText += "#v" + Tmp[j][0] + "#x" + Tmp[j][1]+ "  "
                    }
                    RewardText += "\r\n\r\n";
                }
                cm.sendYesNo(RewardText);
            }else if(selection == 5){
                flag = 5;
                var ExchangeText = "���������ʹ�öһ������һ�VIP��\r\n\r\n";
                for(var j = 0; j < ExchangeItemid.length; j++){
                    ExchangeText += "#L2" + j + "#" +С��+ " #b ʹ��#v" + ExchangeItemid[j][0]+ "#x" + ExchangeItemid[j][1] + "�һ�#v" + ExchangeItemid[j][2] + "#" +ExchangeItemid[j][3] / 24 + "�죡\r\n"
                }
                cm.sendSimple(ExchangeText);
            }
            //cm.sendYesNo("������˼���Ƿ����Ҫ����#r��2000��#k#l��ȯɾ����ɫ���ص����ǰ?");
        }else if(status == 2){
            var sel = selection;

            //cm.getPlayer().dropMessage(5, sel);
            if(flag == 1){
                buyCard(Silver, SilverItemid, 30);
            }else if(flag == 2){
                buyCard(Gold, GoldItemid, 30);
            }else if(flag == 3){
                buyCard(Diamond, DiamondItemid, 30);
            }else if(flag == 4){
                if(cm.getInventory(4).isFull(6)){
                    cm.sendOk("Ϊ�˱�֤��ȡ����������#b����#k#l������Ҫ��#r6#k#l�����ӣ����飡");
                    cm.dispose();
                    return;
                }
                SubmitReward();
            }else if(flag == 5){
                //����һ�VIP�߼�
                
                //cm.getPlayer().dropMessage(5, sel);
                switch (sel) {
                    case 20:
                        ExchangeFlag = 20;
                        cm.sendYesNo("#bȷ��ʹ��#v" +NoobExchange + "#���һ�#v" + NoobItemid + "#7�죿#l!");
                        break;
                    case 21:
                        ExchangeFlag = 21;
                        cm.sendYesNo("#bȷ��ʹ��#v" +SilverExchange + "#���һ�#v" + SilverItemid + "#30�죿#l!");
                        break;
                    case 22:
                        ExchangeFlag = 22;
                        cm.sendYesNo("#bȷ��ʹ��#v" +GoldExchange + "#���һ�#v" + GoldItemid + "#30�죿#l!");
                        break;
                    case 23:
                        ExchangeFlag = 23;
                        cm.sendYesNo("#bȷ��ʹ��#v" +DiamondExchange + "#���һ�#v" + DiamondItemid + "#30�죿#l!");
                        break;
                    default:
                        cm.sendOk("�ű���������ϵ����Ա��");
                        cm.dispose();
                        break
                }
            }
        }else if(status == 3){
            //cm.getPlayer().dropMessage(5, ExchangeFlag);
            switch (ExchangeFlag){
                case 20:
                    SubmitExchange(NoobExchange, NoobItemid);
                    break;
                case 21:
                    SubmitExchange(SilverExchange, SilverItemid);
                    break;
                case 22:
                    SubmitExchange(GoldExchange, GoldItemid);
                    break;
                case 23:
                    SubmitExchange(DiamondExchange, DiamondItemid);
                    break;
                default:
                    cm.sendOk("�ű���������ϵ����Ա��");
                    cm.dispose();
                    break;
            }
        }
    }
}

function SubmitExchange(itemidBefore,itemidAfter){
    var index = 0;
    for(index == 0; index < ExchangeItemid.length; index++){
        if((ExchangeItemid[index][0] == itemidBefore) && (ExchangeItemid[index][2] == itemidAfter)){
            if(!cm.haveItem(ExchangeItemid[index][0], ExchangeItemid[index][1])){
                cm.sendOk("����û��#v"+ExchangeItemid[index][0]+"#x"+ExchangeItemid[index][1]+"�����ܶһ�#v" + ExchangeItemid[index][2] + "#��");
                cm.dispose();
            }else{
                if(cm.haveItem(ExchangeItemid[index][2])){
                    cm.sendOk("���Ѿ��жһ���#v" +ExchangeItemid[index][2]+ "#�ˣ�����Ҫ�ظ��һ���");
                    break;
                }else{
                    cm.gainItem(ExchangeItemid[index][2], 1, ExchangeItemid[index][3]);
                    cm.gainItem(ExchangeItemid[index][0], -ExchangeItemid[index][1]);
                    cm.dispose();
                    break;
                }
            }
        }
    }
    cm.dispose();
}

function SubmitReward(){
    var i = 0;
    var j = 0;
    var RewardFlag = false;

    for(i = 0; i < RewardArray.length; i++ ){
        if (cm.haveItem(RewardArray[i][0])){
            if(cm.getBossLog(RewardArray[i][4]) >=1){
                cm.sendOk("������Ѿ���ȡ����Ա�����ˣ�");
                cm.dispose();
                continue;
            }else{
                cm.setBossLog(RewardArray[i][4]);
            }
            RewardFlag = true;
            cm.gainMeso(RewardArray[i][1]); //���
            //cm.getPlayer().dropMessage(6, "��ң�+" + RewardArray[i][1]);
            cm.gainNX(RewardArray[i][2]);   //��ȯ
            cm.getPlayer().dropMessage(6, "��ȯ��+" + RewardArray[i][2]);
            var TmpArray = RewardArray[i][3];//����
            for(j = 0; j< TmpArray.length; j++ ){
                cm.gainItem(TmpArray[j][0], TmpArray[j][1]);
            }
        }
    }
    if(RewardFlag == true){
        
        if(!cm.canHold(5211047, 1)){
            cm.sendOk("������������λ����������1�����ӣ�");
            cm.dispose();
            return;
        }
        cm.����(2, "������ҡ�" + cm.getPlayer().getName()+ "����ȡ��ÿ�ջ�Ա������")
        cm.sendOk("�ɹ���ȡ��Ա������");
        if (!cm.haveItem(5211047)){
            cm.gainItem(5211047,1,3);
        }
    }else{
        cm.sendOk("����δ��ͨ��ԱȨ�ޣ��뿪ͨ������ȡ��")
    }
    cm.dispose();
}

function buyCard(needMoney, itemid, timeout){
    if(!cm.canHold(itemid, 1)){
        cm.sendOk("������������λ����������1�����ӣ�");
        cm.dispose();
        return;
    }
    if(cm.getmoneyb() < needMoney){
        cm.sendOk("��������ɬ����ȷ����"+needMoney+"Ԫ������˵��");
        cm.dispose();
        return;
    }
     if (!cm.haveItem(itemid)){
        cm.setmoneyb(-needMoney);
        cm.gainItem(itemid, 1, timeout * 24);//�����Ա��ʱ��
        cm.sendOk("��ϲ���ɹ����#v"+itemid+"#�����߷�������������С�Ĳ�Ҫ����������Ҫ���¹���Ŷ��");
        cm.����(2, "Ŷ��~~������ҡ�" + cm.getPlayer().getName()+ "��ͨ��������ͨ�˻�Ա���ܣ�");
        cm.dispose();
    }else{
        cm.sendOk("���Ѿ���#v"+itemid+"#���벻Ҫ�ظ�����!");
        cm.dispose();
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