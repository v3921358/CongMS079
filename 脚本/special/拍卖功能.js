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
		
		
            var add =             " \t\t"+�ʺ�+"  #e#r " + cm.getChannelServer().getServerName() + " #k#n  #r  "+�ʺ�+"#b#k#n\r\r\n";
            add += "  "+è��+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+è��+"\r\n";add +="\t   #e��ǰ���#r" +cm.getPlayer().getCSPoints(1) +  "#k��  #e����ʱ�䣺#r" + cm.getGamePoints() + "#k����\r\n";

			
add +="#L1#"+С��+"#r����̵�#l   #L2#"+С��+"#r���ܴ���#l   #L3#"+С��+"��ȥ����#l\r\n";
add +="#L4#"+С��+"#r�������#l   #L5#"+С��+"���ʲ�ѯ#l   #L6#"+С��+"��������#l\r\n\r\n";
add += "  "+è��+����è+����è+����è+����è+����è+����è+����è+" #d���õ�һ�� "+����è+����è+����è+����è+����è+����è+����è+è��+"#k\r\n";

add +="#L7#"+С��+"#b�����ȡ#l   #L8#"+С��+"��ʮ�߹�#l   #L9#"+С��+"��������#l\r\n";
add +="#L10#"+С��+"ÿ��Ѱ��#l   #L11#"+С��+"#b�������#l   #L12#"+С��+"���߽���#l\r\n\r\n";
add +=  "  "+è��+����è+����è+����è+����è+����è+����è+����è+" #d��ð�տ�ʼ "+����è+����è+����è+����è+����è+����è+����è+è��+"\r\n";
add +="#L803#"+С��+"#b��ԭ����#l   #L806#"+С��+"#b��������#l   #L807#"+С��+"#b��������#l\r\n";
add +="#L13#"+С��+"#bһ��תְ#l   #L802#"+С��+"#b���￨Ƭ#l   #L15#"+С��+"#b����֮��#l\r\n\r\n";

					 
			 
		add += ""+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+"\r\n";

		if (cm.getPlayer().isGM()) {
			
			add += "#d- - - - - - �� �� �� G M �� �� ̨ - - - - - -\r\n"
			add += "#r   ���¹��ܽ�����Ա�ɼ���#k\r\n"
			add += "����������"+(cm.��������()+0)+"#L200##d"+С����+"���߸�����"+С����+"#l #L201##d"+С����+"���߸�����"+С����+"#l\r\n\r\n"
		
		add += " #L100#"+acc+"ˢ �� ��"+acc+"#l";
		add += " #L101#"+acc+"ˢ �� ��"+acc+"#l";
		add += " #L102#"+acc+"ˢ�µ�ͼ"+acc+"#l\r\n";
		
		add += " #L103#"+acc+"ˢ��״̬"+acc+"#l";
		add += " #L104#"+acc+"��ɫ����"+acc+"#l";
		add += " #L13#"+acc+"һ��תְ"+acc+"#l\r\n";
		
		add += " #L105#"+acc+"�ص����"+acc+"#l";
		add += " #L106#"+acc+"һ������"+acc+"#l\r\n";

		
		
		
		
		}
		
		 
						// #L13##b�ƹ�����#l #L6##b����תְ#l#L11##b���ƹ���#l #L12##b�ƹ��̵�#l #L35#"+Сˮ��+"#b�ͽ�����#l#L36#"+Сˮ��+"#bɱ������#l

            cm.sendSimple(add);

//------------------------------------------------------------------------

        } else if (status == 1) {
			
		if (selection == 15) {
		cm.dispose();
		cm.openNpc(3003332,  "����֮��");
        }
		if (selection == 13) {
		cm.dispose();
        cm.openNpc(3003332, "����תְ");
		
        }	
		if (selection == 12) {
		cm.dispose();
		cm.openNpc(3003332,  "���߽���");
        }
        if (selection == 11) {
		cm.dispose();
		cm.openNpc(3003385,71);
        }
        if (selection == 10) {
		cm.dispose();
		cm.openNpc(3003332,  "ÿ��Ѱ��");
        }	
		if (selection == 9) {
		cm.dispose();
        cm.openNpc(3003332, "��������");

        }
			
		if (selection == 8) {
		cm.warp(970030000, 0 );
		cm.dispose();
        }
			
		if (selection == 7) {
		cm.dispose();
		cm.openNpc(3003332, 144);
        }
		if (selection == 6) {
		cm.dispose();
		cm.openNpc(3003332,  "4����װ��");
        }
		if (selection == 5) {
		cm.dispose();
		cm.openNpc(3003332,  "���ʲ�ѯ");
        }
	    if (selection == 4) {
		cm.dispose();
        cm.openNpc(3003332, "���ﱬ��");
	    }
		if (selection == 3) {//��ȥ����
		cm.dispose();
		cm.warp(910000000);
        }
		if (selection == 2) {
		cm.dispose();
        cm.openNpc(3003332, "���ܴ���");
	    }	
		if (selection == 1) {
		cm.dispose();
        cm.openNpc(3003332, "����̵�");
	    }	
		
//-----------------------------------------------------����Ա����---------------------------------------------
        
		
		if (selection == 200) {
		cm.dispose();
		cm.openNpc(3003332, "���߸���");
        }
		if (selection == 201) {
		cm.dispose();
		cm.openNpc(3003332, "���߸���");
        }
		if (selection == 100) {//ˢ���
            cm.dispose();
            cm.gainMeso(210000000);
			cm.sendOk("ϵͳ�ж����ǹ���Ա����ϲ������2.1E���!");
			
		}
		if (selection == 101) {
            cm.dispose();
            cm.gainNX(999999);
			cm.sendOk("ϵͳ�ж����ǹ���Ա����ϲ������999999���!");
			
		}
		if (selection == 102) {
			cm.dispose();
			cm.ˢ�µ�ͼ();
			cm.sendOk("ϵͳ�ж����ǹ���Ա����ǰ��ͼ״̬�Ѿ�ˢ��!");
			
        }
		if (selection == 103) {
		    cm.dispose();
            cm.ˢ��״̬();
			cm.sendOk("ϵͳ�ж����ǹ���Ա����ǰ״̬�Ѿ�ˢ��!");
			
		}
		if (selection == 104) {
				cm.dispose();
                cm.openNpc(3003332, "��ɫ����");
	    }
		if (selection == 105) {
            cm.dispose();
            cm.warp("http://wpa.qq.com/msgrd?V=1&Uin=1342041396&Site");	
			cm.sendOk("ϵͳ�ж����ǹ���Ա������ص������ط�!");
			
		}
		if (selection == 106) {
		cm.dispose();
		cm.openNpc(3003332, "ѧϰ����");

        }
		if (selection == 802) {
				cm.dispose();
                cm.openNpc(3003332, "���￨Ƭ");
	    }
		if (selection == 803) {
				cm.dispose();
				cm.openNpc(3003332,  "��ԭ����");
        }
        if (selection == 806) {
				cm.dispose();
				cm.openNpc(3003332,  "��������");
        }
		if (selection == 807) {
				cm.dispose();
				cm.openNpc(3004661);
        }

             
        }
    }
}
//---------------------------------------------------------------------------
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
