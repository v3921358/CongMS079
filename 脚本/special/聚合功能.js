 

var aaa = "#fUI/UIWindow.img/Quest/icon9/0#";
var zzz = "#fUI/UIWindow.img/Quest/icon8/0#";
var sss = "#fUI/UIWindow.img/QuestIcon/3/0#";

//------------------------------------------------------------------------

var chosenMap = -1;
var monsters = 0;
var towns = 0;
var bosses = 0;
var fuben = 0;

//------------------------------------------------------------------------

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
var ����� = "#fItem/Etc/0427/04270001/Icon9/1#";  //
var Сˮ�� = "#fItem/Etc/0427/04270001/Icon10/5#";  //
var ��ˮ�� = "#fItem/Etc/0427/04270001/Icon10/4#";  //
var �찮�� ="#fEffect/CharacterEff/1112905/0/1#";

//---------------------------------------------------------------------------
function start() {
    status = -1;
    action(1, 0, 0);
}
function action(mode, type, selection) {
    if (mode == -1) {
        cm.sendOk("#b�õ�,�´��ټ�.");
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

//------------------------------------------------------------------------

        if (status == 0) {

            var add =  " \t\t  #d#e#v4030001#  ��ӭ����  #r" + cm.getServerName() + "  #e#b#v4030001##b#k#n\r\r\n";
			 //add +="\t#d#e��ǰ���#r" +cm.getPlayer().getCSPoints(1) +  "#k��  #d��ǰ���þ�#r" +cm.getPlayer().getCSPoints(2) +  "#k��#b#k#n\r\n";
			 //add +="\t#d#e��ǰ��ң�#r" +cm.getPlayer().getMeso() +  "#k��� #d#e����ʱ�䣺#r" + cm.getGamePoints() + "#k����\r\n";
			 //add += ""+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+"\r\n";
			 	add += ""+�찮��+""+�찮��+""+�찮��+""+�찮��+""+�찮��+""+�찮��+�찮��+""+�찮��+""+�찮��+""+�찮��+""+�찮��+""+�찮��+""+�찮��+""+�찮��+""+�찮��+""+�찮��+""+�찮��+""+�찮��+""+�찮��+""+�찮��+""+�찮��+""+�찮��+""+�찮��+""+�찮��+""+�찮��+""+�찮��+""+�찮��+""+�찮��+""+�찮��+"\r\n";

			 //add += ""+С����+С����+С����+С����+С����+С����+С����+С����+С����+С����+С����+С����+С����+С����+С����+С����+С����+С����+С����+С����+С����+"\r\n";
                         //add +="\t#d#e�������������ˣ�#r" + cm.getGamePoints() + "#k����,��ע����ϢŶ~!\r\n";
                         add +="#L99#"+Сˮ��+"#e#b������Ϣ#l#L0#"+Сˮ��+"�����г�#l#L1#"+Сˮ��+"���ܴ���#l#L2#"+Сˮ��+"���ﱬ��#l\r\n";	
                         add +="#L5#"+Сˮ��+"#b����̵�#l#L7#"+Сˮ��+"װ��ǿ��#l#L8#"+Сˮ��+"ʦͽϵͳ#l#L4#"+Сˮ��+"�ۺ�����#l\r\n";
                         add +="#L9#"+Сˮ��+"#bÿ������#l#L10#"+Сˮ��+"�ƹ�ϵͳ#l#L14#"+Сˮ��+"BOSS�ٻ�#l#L15#"+Сˮ��+"ѫ��ǿ��#l\r\n\r\n";
                         add +="#L16#"+Сˮ��+"#b���߽���#l#L17#"+Сˮ��+"�������#l#L18#"+Сˮ��+"��������#l#L19#"+Сˮ��+"ÿ��ǩ��#l\r\n";
						 add +="#L20#"+Сˮ��+"#b�ȼ�����#l#L21#"+Сˮ��+"�ϳ�����#l#L22#"+Сˮ��+"�һ�����#l#L23#"+Сˮ��+"װ�����#l\r\n";
						 add +="#L3#"+Сˮ��+"#b����ϴѪ#l\r\n";
						 
			add += "\r\n"+�찮��+""+�찮��+""+�찮��+""+�찮��+""+�찮��+""+�찮��+�찮��+""+�찮��+""+�찮��+""+�찮��+""+�찮��+""+�찮��+""+�찮��+""+�찮��+""+�찮��+""+�찮��+""+�찮��+""+�찮��+""+�찮��+""+�찮��+""+�찮��+""+�찮��+""+�찮��+""+�찮��+""+�찮��+""+�찮��+""+�찮��+""+�찮��+""+�찮��+"\r\n";
			 
						 
						 
						 //#L13##b�ƹ�����#l #L6##b����תְ#l#L11##b���ƹ���#l #L12##b�ƹ��̵�#l 

            cm.sendSimple(add);

//------------------------------------------------------------------------

        } else if (status == 1) {

		if (selection == 0) {
		cm.dispose();
		cm.warp(910000000);//�����г�
                //cm.openNpc(9330079, "���ܴ���");
	    }

	    if (selection == 1) {
		cm.dispose();
                cm.openNpc(9330079, "���ܴ���");
	    }

	    if (selection == 2) {
		cm.dispose();
                cm.openNpc(9330079, "���ﱬ��");
	    }

	    if (selection == 3) {
		cm.dispose();
                cm.openNpc(9330079, "����ϴѪ");
	    }

	    if (selection == 4) {
		cm.dispose();
                cm.openNpc(9330079, "�ۺ�����");
	    }

	    if (selection == 5) {
		cm.dispose();
                cm.openNpc(9330079, "����̵�");
	    }

	    if (selection == 6) {
		cm.dispose();
                cm.openNpc(9330079, "����תְ");

		
            }
	    if (selection == 7) {
		cm.dispose();
                //cm.openNpc(9330079, "װ��ǿ��");
				cm.openNpc(9330079, "9310072");

            }
	    if (selection == 8) {
		cm.dispose();
                cm.openNpc(9330079, "ʦͽϵͳ");
            }
	    if (selection == 9) {
		cm.dispose();
                cm.openNpc(9330079, "ÿ������");

		
            }
	    if (selection == 10) {
		cm.dispose();
                cm.openNpc(9330079, "�ƹ�ϵͳ");

		
            }
	    if (selection == 11) {
		cm.dispose();
                cm.openNpc(9330079, "���ƹ���");

		
            }
	    if (selection == 12) {
		cm.dispose();
                cm.openNpc(9330079, "�ƹ��̵�");

		
            }
 	    if (selection == 13) {
		cm.dispose();
                cm.openNpc(9330079, "�ƹ�����");

		
            }
	    if (selection == 14) {
		cm.dispose();
                cm.openNpc(9330079, "BOSS�ٻ�");

		
            }
	    if (selection == 15) {
		cm.dispose();
                cm.openNpc(9330079, "ѫ��ǿ��");


            }
	    if (selection == 16) {
		cm.dispose();
                //cm.openNpc(9330079, "���߽���");
				cm.openNpc(9330079, "����ʱ��");

            }
			
		if (selection == 17) {
		cm.dispose();
                cm.openNpc(9330079, "�������");

            }

			if (selection == 18) {
		cm.dispose();
                cm.openNpc(9330079, "��������");

            }	
			
			if (selection == 19) {
		cm.dispose();
                cm.openNpc(9330079, "ÿ��ǩ��");

            }
			
			if (selection == 20) {
		cm.dispose();
                cm.openNpc(9330079, "�ȼ�����");

            }
			
			if (selection == 21) {
		cm.dispose();
                cm.openNpc(9330079, "�ϳ�����");

            }
			
			if (selection == 22) {
		cm.dispose();
		cm.openNpc(9330079, "�һ�����");
                //cm.openNpc(9330079, "�Ͻ��Ҷ�����һ�");

            }
			
			if (selection == 23) {
		cm.dispose();
		cm.openNpc(9330079, "�������װ��");
                //cm.openNpc(9330079, "�Ͻ��Ҷ�����һ�");

            }
			
			if (selection == 24) {
		cm.dispose();
		cm.openNpc(9330079, "��������");
                //cm.openNpc(9330079, "�Ͻ��Ҷ�����һ�");

            }

			if (selection == 99) {//������Ϣ
		cm.dispose();
		cm.openNpc(9330079, 100);
                //cm.openNpc(9330079, "�Ͻ��Ҷ�����һ�");

            }
		
             
        }
    }
}

