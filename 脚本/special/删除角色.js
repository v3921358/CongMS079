/* ==================
 �ű�����:  ����	    
 �ű���Ȩ����Ϸ����
 ��ϵ�ۿۣ�12384161
 =====================
 */

var inputName;

var needMoneyb = 10;

function start() {
    status = -1;
    //if(cm.getPlayer().isGM()){
    if(true){
        action(1, 0, 0);
    }else{
        cm.sendOk("��δ���ŵĹ��ܣ������Ҫ�����������ϵ����Ա������10Ԫ��/����ɫ��������ȫ�����ڷ�������Ӫ����л֧�֣�");
        cm.dispose();
    }
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
            cm.sendGetText("#r�����ʹ�øù��ܣ�һ��ɾ�����˺���ָ����ɫ�������ɻָ�#l!!#n\r\n\r\n#b\t\t\t\t\t\t\t\t\r\n#d�����뱾�˺ŵ�������ɫ��:");
        } else if (status == 1) {
            /*if(cm.getmoneyb < needMoneyb){
                cm.sendOk("��������ɬ����ȷ����" + needMoneyb + "Ԫ������˵��");
                cm.dispose();
                return;
            }*/
            inputName = cm.getText();
            if(inputName === ""){
                cm.sendOk("����δ�����κν�ɫ����!");
                cm.dispose();
            }
            var currentName = cm.getPlayer().getName();
            if(inputName === currentName){
                cm.sendOk("������ɾ����ǰʹ�õ������ɫ��");
                cm.dispose();
            }else{
                 cm.sendYesNo("������˼���Ƿ����Ҫɾ����ɫ?");
            }
        } else if(status == 2){
            var ret = cm.deleteRoleByName(inputName);
            //cm.getPlayer().dropMessage(5, ret);
            if(ret >= 0){
                //cm.gainNX(-2000);
                //cm.setmoneyb(-needMoneyb);
                cm.sendOk("#b�ɹ�ɾ����ǰ�˺��½�ɫ��Ϊ["+ inputName +"]�Ľ�ɫ�������µ�¼�鿴��");
            }else{
                cm.sendOk("��ɫ��#r��" +inputName+ "��#k#l�����ڻ����Ѿ���ɾ��!!!\r\n");
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