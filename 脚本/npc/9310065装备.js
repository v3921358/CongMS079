/*var acc = "#fEffect/CharacterEff/1112903/0/0#";//������
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

//------------------------------------------------------------------------

var chosenMap = -1;
var monsters = 0;
var towns = 0;
var bosses = 0;
var fuben = 0;

//------------------------------------------------------------------------



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

            var add =  " \t\t#d#e#b#v4030001# ��ӭ����#r" + cm.getServerName() + " �ϳ����� #e#b#v4030001##b#k#n\r\r\n";
				add += ""+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+"\r\n\r\n";
				add += "#e  #rȫ���,#dֻҪ�ϻ�ʱ��,���������ţ�Ƶ�����/����#n\r\n\r\n"
                        //add +="\t#d#e�������������ˣ�#r" + cm.getGamePoints() + "#k����,��ע����ϢŶ~!#k#n\r\n";
						
               
				
				//add += "#L4##r - - - <<<������Ʒװ��>>> - - -������100%��#l\r\n";
				
				//add += "#L5##r - - - <<<��������װ��>>> - - -������90%��#l\r\n";
				
				add += "#L10##r - - - <<<��������װ��>>> - - -������70%��#l\r\n";
				
				

            cm.sendSimple(add);

//------------------------------------------------------------------------

        } else if (status == 1) {

		if (selection == 0) {
		cm.dispose();
		//cm.warp(910000000);//�����г�
                cm.openNpc(9330079, "10����װ��");
	    }

	    if (selection == 1) {
		cm.dispose();
                cm.openNpc(9330079, "4����װ��");
	    }

	    if (selection == 2) {
		cm.dispose();
                cm.openNpc(9330079, "79�Ͻ�װ��");
	    }

	    if (selection == 3) {
		cm.dispose();
                cm.openNpc(9330079, "100����װ��");
	    }

	    if (selection == 4) {
		cm.dispose();
                cm.openNpc(9330079, "12����װ��");
	    }

	    if (selection == 5) {
		cm.dispose();
                cm.openNpc(9330079, "145�޽����ʺϳ�");
	    }

	    if (selection == 6) {
		cm.dispose();
                cm.openNpc(9330079, "135����������");

		
            }


		

	    if (selection == 10) {
		cm.dispose();
                cm.openNpc(9330079, "150FFN���ʺϳ�");

		
            }
	    if (selection == 11) {
		cm.dispose();
                cm.openNpc(9330079, "145�޽�װ��");

		
            }
	    if (selection == 12) {
		cm.dispose();
                cm.openNpc(9330079, "150FFN����");

		
            }
 	   
			

		
             
        }
    }
}

