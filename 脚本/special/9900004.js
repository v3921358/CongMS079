 

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
var ������ͷ = "#fUI/Basic/BtHide3/mouseOver/0#";
var ��̾�� = "#fUI/UIWindow/Quest/icon0#";
var ����new = "#fUI/UIWindow/Quest/icon5/1#";
var Բ�� = "#fUI/UIWindow/Quest/icon3/6#";
//var ��ɫ��ͷ = "#fEffect/CharacterEff/1114000/2/0#";
var ��ɫ��ͷ = "#fEffect/CharacterEff/1112908/0/1#";  //�ʹ�3
var ttt1 = "#fEffect/CharacterEff/1062114/1/0#";  //����
//var ��ɫ��ͷ = "#fUI/UIWindow/Quest/icon6/7#";
var ��ɫ�ǵ� = "#fUI/UIWindow.img/PvP/Scroll/enabled/next2#";

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

            var add =  " \t\t\t  #d#e#b#v4030001#��ӭ����#râ��ð�յ�#e#b#v4030001##b#k#n\r\r\n";
			 add +="\t#d#e��ǰ���#r" +cm.getPlayer().getCSPoints(1) +  "#k��  #d��ǰ���þ�#r" +cm.getPlayer().getCSPoints(2) +  "#k��#b#k#n\r\n";
			 add +="\t#d#e��ǰ��ң�#r" +cm.getPlayer().getMeso() +  "#k���#b#k#n\r\n";
                         add +="\t#d#e�������������ˣ�#r" + cm.getGamePoints() + "#k����,��ע����ϢŶ~!#k#n\r\n";
                         add += "  #L1##b���ܴ���#l #L2##b���ﱬ��#l #L3##b����ϴѪ#l #L4##b�ۺ�����#l #k#n\r\n";	
                         add += "  #L5##b����̵�#l #L6##b����תְ#l #L7##bװ��ǿ��#l #L8##bʦͽϵͳ#l #k#n\r\n";
                         add += "  #L9##bÿ������#l #L10##b�ƹ�ϵͳ#l #L11##b���ƹ���#l #L12##b�ƹ��̵�#l #k#n\r\n";
                         add += "  #L13##b�ƹ�����#l #L14##bBOSS�ٻ�#l #L15##bѫ��ǿ��#l #L16##b���߽���#l #k#n\r\n";
						 

            cm.sendSimple(add);

//------------------------------------------------------------------------

        } else if (status == 1) {


	    if (selection == 1) {
		cm.dispose();
                cm.openNpc(9010000, "���ܴ���");
	    }

	    if (selection == 2) {
		cm.dispose();
                cm.openNpc(9010000, "���ﱬ��");
	    }

	    if (selection == 3) {
		cm.dispose();
                cm.openNpc(9010000, "����ϴѪ");
	    }

	    if (selection == 4) {
		cm.dispose();
                cm.openNpc(9010000, "�ۺ�����");
	    }

	    if (selection == 5) {
		cm.dispose();
                cm.openNpc(9010000, "����̵�");
	    }

	    if (selection == 6) {
		cm.dispose();
                cm.openNpc(9010000, "����תְ");

		
            }
	    if (selection == 7) {
		cm.dispose();
                cm.openNpc(9010000, "װ��ǿ��");

            }
	    if (selection == 8) {
		cm.dispose();
                cm.openNpc(9010000, "ʦͽϵͳ");
            }
	    if (selection == 9) {
		cm.dispose();
                cm.openNpc(9010000, "ÿ������");

		
            }
	    if (selection == 10) {
		cm.dispose();
                cm.openNpc(9010000, "�ƹ�ϵͳ");

		
            }
	    if (selection == 11) {
		cm.dispose();
                cm.openNpc(9010000, "���ƹ���");

		
            }
	    if (selection == 12) {
		cm.dispose();
                cm.openNpc(9010000, "�ƹ��̵�");

		
            }
 	    if (selection == 13) {
		cm.dispose();
                cm.openNpc(9010000, "�ƹ�����");

		
            }
	    if (selection == 14) {
		cm.dispose();
                cm.openNpc(9010000, "BOSS�ٻ�");

		
            }
	    if (selection == 15) {
		cm.dispose();
                cm.openNpc(9010000, "ѫ��ǿ��");


            }
	    if (selection == 16) {
		cm.dispose();
                cm.openNpc(9010000, "���߽���");

            }

				

		
             
        }
    }
}

