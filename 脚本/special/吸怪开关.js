var CY0 = "�ǩ��������������������������� ����������������������������������";
var CY1 = "��       - ���� -       ��";
var CY2 = "�� �淨����  �����ƽű� ��";
var CY3 = "�� ����֧�� �� ��Ϸ���� ��";
var CY4 = "�� �ף���ӡ�  ��ͼ���� ��";
var CY5 = "�� �Ӷܷ�����  �۵�½�� ��";
var CY7 = "�� ���ο���    ���ο��� ��";
var CY8 = "�ǩ��������������������������� ����������������������������������";
var CY9 = "��   ΨһQQ:3066318387  ��";
var CY0 = "�ǩ��������������������������� ����������������������������������";
var chosenMap = -1;
var monsters = 0;
var towns = 0;
var bosses = 0;
var fuben = 0;
function start() {
    status = -1;
    action(1, 0, 0);
}
function action(mode, type, selection) {
    if (mode == -1) {
       
        cm.dispose();
    } else {
        if (status >= 0 && mode == 0) {
            cm.sendOk("#b�õ�,�´��ټ�.");
            cm.dispose();
            return;
        }
        if (mode == 1) {
            status++;
        } else {
            status--;
        }
        if (status == 0) {

		if(cm.getPlayer().getMapId()==180000001){
			
				cm.dispose();
				cm.openNpc(9900005);
				return;
			}
			if(cm.getPlayer().getMapId()==970000005 || cm.getPlayer().getMapId()==901111112 || cm.getPlayer().getMapId()==901111113){
			
				cm.dispose();
				return;
			}
		
		
            var add =             " \t\t"+�ʺ�+"  #e#r �� �� �� �� #k#n  #r  "+�ʺ�+"#b#k#n\r\r\n";
            add += "  "+è��+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+è��+"\r\n";add +="\t   #e��ǰ���#r" +cm.getPlayer().getCSPoints(1) +  "#k��  #e����ʱ�䣺#r" + cm.getGamePoints() + "#k����\r\n\r\n";

			
add +="                  "+ͷ��Ů+"\r\n";


add +="#L1#"+���ֿ�+"#l\r\n\r\n";

add +="#L2#"+�ر�����+"#l\r\n\r\n"; 
	 



            cm.sendSimple(add);

//------------------------------------------------------------------------

        } else if (status == 1) {
			
		if (selection == 1) {
		cm.dispose();
		cm.����();
        }
		if (selection == 2) {
		cm.dispose();
        cm.�ر�����();
		
        }	
		
             
        }
    }
}
//---------------------------------------------------------------------------
var ͷ��Ů = "#fString/zerek/ͷ��/7#"; 
var ���ֿ� = "#fString/zerek/�ز�1/���ֿ�#"; 
var �ر����� = "#fString/zerek/�ز�1/�ر�����#"; 
var acc = "#fEffect/CharacterEff/1112903/0/0#";//������
var ��ɫ�ǵ� = "#fUI/UIWindow.img/PvP/Scroll/enabled/next2#";
var ��ɫ��ͷ = "#fUI/UIWindow/Quest/icon2/7#";
var ��ɫ��ͷ = "#fUI/UIWindow/Quest/icon6/7#";
var Բ�� = "#fUI/UIWindow/Quest/icon3/6#";
var �۱� = "#fUI/UIWindow/Quest/icon7/0#";
var ����ne = "#fUI/UIWindow/Quest/icon6/7#";
var ��̾�� = "#fUI/UIWindow/Quest/icon0#";
var ������ͷ = "#fUI/Basic/BtHide3/mouseOver/0#";
var ����è ="#fUI/ChatBalloon/37/n#";
var è�� =  "#fUI/ChatBalloon/37/ne#";
var è�� ="#fUI/ChatBalloon/37/nw#";
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
var С�� = "#fEffect/CharacterEff/1112960/3/0#" ; //а��С�� ��С��
var Сˮ�� = "#fItem/Etc/0427/04270001/Icon10/5#";  //
var ��ˮ�� = "#fItem/Etc/0427/04270001/Icon10/4#";  //
var �찮�� ="#fEffect/CharacterEff/1112905/0/1#";
var ���ͼ�� = "#fUI/UIWindow.img/QuestIcon/7/0#";
var aaa = "#fUI/UIWindow.img/Quest/icon9/0#";
var zzz = "#fUI/UIWindow.img/Quest/icon8/0#";
var sss = "#fUI/UIWindow.img/QuestIcon/3/0#";
