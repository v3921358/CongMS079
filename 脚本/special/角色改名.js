/* ==================
 �ű�����:  ����	    
 �ű���Ȩ����Ϸ����
 ��ϵ�ۿۣ�12384161
 =====================
 */

var inputName;
var needNX = 10;

function start() {
    status = -1;
    //if(cm.getPlayer().isGM()){
    if(true){
        action(1, 0, 0);
    }else{
        cm.sendOk("��ɫ������ʱδ���ţ������Ҫ�˷�������ϵ����Ա������10Ԫ���޸ģ�������ȫ�����ڷ�������Ӫ��");
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
            //cm.sendGetText("#r��ɫ����������Ҫ#r��"+needNX+"��Ԫ��#l!!#n\r\n\r\n#b\t\t\t\t\t\t\t\t\r\n#d��������Ҫ�޸ĵ��µĽ�ɫ��(������16���ַ�):");
			cm.sendGetText("#b\t\t\t\t\t\t\t\t\r\n#d��������Ҫ�޸ĵ��µĽ�ɫ��(������16���ַ�):");
        } else if (status == 1) {
            /*if(cm.getmoneyb < needNX){
                cm.sendOk("��������ɬ����ȷ����" +needNX+ "Ԫ������˵��");
                cm.dispose();
                return;
            }*/
            inputName = cm.getText();
            var len = inputName.length();
            if(len > 8){
                cm.sendOk("������Ľ�ɫ���ȳ���8���ַ��������һЩ��")
                cm.dispose();
            }
            if(inputName === ""){
                cm.sendOk("����δ�����κν�ɫ����!");
                cm.dispose();
            }
            inputName = inputName.replace(/\s/g,"");
            var ret = cm.searchRoleByName(inputName);
            if( ret == 1){
                cm.sendOk("����Ҫ�޸ĵĽ�ɫ������ϵͳ���Ѿ����ڣ�");
                cm.dispose();
            }else if(ret == 0){
                //���ݿ��в鲻���ý�ɫ
                var currentName = cm.getPlayer().getName();
                if(inputName === currentName){
                    cm.sendOk("��Ҫ�ĵ������뽫Ҫ�޸ĵ�����һ�£������޸ģ�");
                    cm.dispose();
                }else{
                     //cm.sendYesNo("������˼���Ƿ����Ҫ����#r��" +needNX+ "��#k#lԪ������ɫ����Ϊ#r��" +inputName+ "��#k#l���������ո�)?");
					 cm.sendYesNo("������˼���Ƿ����Ҫ����ɫ����Ϊ#r��" +inputName+ "��#k#l���������ո�)?");
                }
            }else if(ret == -1){
                cm.sendOk("ϵͳ��������ϵ����Ա��");
                cm.dispose();
            }


        } else if(status == 2){
			//cm.setmoneyb(-needNX);
            //cm.getPlayer().dropMessage(5, "Ԫ����-" + needNX);//�������ʾ��
            cm.getPlayer().setName(inputName);
            cm.getPlayer().fakeRelog();
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