var ���� = "#fEffect/CharacterEff/1112903/0/0#";
var ���� = "#fEffect/CharacterEff/1032063/0/0#";
var ��ɫ��ͷ = "#fUI/UIWindow/Quest/icon6/7#";
var ������ = "#fUI/UIWindow/Quest/icon3/6#";
var ��ɫ��ͷ = "#fUI/UIWindow/Quest/icon2/7#";
var ttt ="#fUI/UIWindow.img/Quest/icon9/0#";
var xxx ="#fUI/UIWindow.img/Quest/icon8/0#";
var sss ="#fUI/UIWindow.img/QuestIcon/3/0#";
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
            //text += "\t\t\t  #e#����ð�յ�����ר�� #k#n\r\n"
            text += ""+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+"\r\n"
			
            //text += ""+����+����+����+����+����+����+����+"\r\n"
			text += "#L0##b" + ��ɫ��ͷ + "Ӣ����Ӹ���(0-250)#l   #L4##b" + ��ɫ��ͷ + "�����Ӹ���(50-250)#l\r\n\r\n"
            text += "#L1##b" + ��ɫ��ͷ + "������Ӹ���(10-250)#l  #L5##b" + ��ɫ��ͷ + "������Ӹ���(70-250)#l\r\n\r\n"
            
            text += "#L2##b" + ��ɫ��ͷ + "������Ӹ���(15-250)#l  #L6##b" + ��ɫ��ͷ + "������Ӹ���(80-250)#l\r\n\r\n"
			
			text += "#L3##b" + ��ɫ��ͷ + "�����Ӹ���(35-250)#l\r\n\r\n"
            //text += "#L7##b" + ��ɫ��ͷ + "��Ů��Ӹ��� (71-256)  \r\n"
     
            text += ""+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+"\r\n"  
            //text += "#L27##b"+��ɫ��ͷ+ "��ʮ�߹�#l \r\n"//3 
			text += "#L38##b" + ��ɫ��ͷ + "������� (����״Ԫ)\r\n"
			text += "#L8##b" + ��ɫ��ͷ + "��ַ����Կ�ս(���帱��)#l\r\n"
			text += "#L10##b" + ��ɫ��ͷ + "������껪 (��ӶԿ�����.���2V2)�ȼ�(30-100)#l\r\n\r\n"
			text += ""+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+"\r\n"  
            //text += "#L28##b" + ��ɫ��ͷ + "��������#l\r\n\r\n"//3
			//text += "#L1##b" + ��ɫ��ͷ + "������Ӹ���(10-200)#l #L9##b" + ��ɫ��ͷ + "Ӣ��ѧԺ����\r\n"//3	  
	  
	  //  text += " #L11#"+ttt+""+xxx+"Զ������(100��)#l#b#L12#"+ttt+""+xxx+"Զ������(120��)#l#b\r\n";
            
           // text += " #L23#"+ttt+""+xxx+"�������(70��)#l#b#L22#"+ttt+""+xxx+"�޴�����(90)#l#b\r\n";
	    //text += " #L13#"+ttt+""+xxx+"Զ������(130��)#l#b#L14#"+ttt+""+xxx+"Զ����ɮ(140��)#l#b\r\n";

            //text += " #L15#"+ttt+""+xxx+"糺�(120��)#l#b#L16#"+ttt+""+xxx+"����(120��)#l#b\r\n\r\n";

            //text += "#L11##dLv120.ǧ���������ż���#l\r\n\r\n"//3
            //text += "#L12##dLv130.��żʦBOSS��ս#l\r\n\r\n"//3
            //text += "" + ��ɫ��ͷ + "#L13##rLv120������.糺츱����ս#l\r\n\r\n"//3
            //text += "" + ��ɫ��ͷ + "#L14##rLv140������.���㸱����ս#l\r\n\r\n"//3
          // text += "" + ��ɫ��ͷ + "#L60##rLv160������.��ս�߼�boss#l\r\n\r\n"//3
            //text += ""+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+"\r\n"
            cm.sendSimple(text);
        } else if (selection == 0) { //Ӣ�︱��
            cm.warp(702090400);
            cm.dispose();
        } else if (selection == 1) { //������Ӹ��� //100000020
            cm.warp(100000200);
            cm.dispose();
        } else if (selection == 2) {  //������Ӹ���
            cm.warp(103000000);
            cm.dispose();
        } else if (selection == 3) { //�����Ӹ���
            cm.warp(221024500);
            cm.dispose();
        } else if (selection == 4) {//�����Ӹ���
            cm.warp(200080101);
            cm.dispose();
        } else if (selection == 5) {//������Ӹ���
	    cm.warp(300030100);
            cm.dispose();
        } else if (selection == 6) {//������Ӹ���
            cm.warp(251010404);
            cm.dispose();
        } else if (selection == 7) {//����ŷ������Ҷ��Ӹ���
	    cm.warp(261000011);
            cm.dispose();
        } else if (selection == 8) {//��ַ����Կ�ս
	    cm.warp(101030104);
            cm.dispose();
        } else if (selection == 9) {//Ӣ��ѧԺ����
            cm.warp(702090400);
            cm.dispose();
         } else if (selection == 21) {//���﹫԰
            cm.warp(951000000);
            cm.dispose();
        } else if (selection == 28) {//��������
            cm.warp(130020000);
            cm.dispose();
       } else if (selection == 29) {//����������
            cm.warp(926010000);
            cm.dispose();
       } else if (selection == 23) {//�������
            cm.warp(701010320);
            cm.dispose();
            
        } else if (selection == 22) {//�޴�����
            cm.warp(105100100);
            cm.dispose();

        } else if (selection == 11) {//����
            cm.warp(220080000);
            cm.dispose();
            //cm.openNpc(9310057, 0);
        } else if (selection == 12) {//��
            cm.warp(211042400);
            cm.dispose();
            //cm.openNpc(9310057, 0);
        } else if (selection == 13) {//����
            if (cm.getLevel() < 180 && cm.party.size() < 2) {  
            cm.sendOk("����ͼ���Ƶȼ�180������������û���ʸ���ս����");
                cm.dispose();
              }else{
			cm.warp(541020800);  
				cm.dispose();
                return;
	      } 
        } else if (selection == 14) {//��ɮ
            if (cm.getLevel() < 140 ) {  
            cm.sendOk("����ͼ���Ƶȼ�140������������û���ʸ���ս����");
                cm.dispose();
              }else{
			cm.warp(702070400);  
                cm.dispose();
                return;
	      }
        } else if (selection == 15) {//糺�
            if (cm.getLevel() < 120 ) {  
            cm.sendOk("����ͼ���Ƶȼ�120������������û���ʸ���ս����");
                cm.dispose();
              }else{
			cm.warp(803001200);  
                cm.dispose();
                return;
	      } 
        } else if (selection == 16) {//����
            if (cm.getLevel() < 120 ) {  
            cm.sendOk("����ͼ���Ƶȼ�120������������û���ʸ���ս����");
                cm.dispose();
              }else{
			cm.warp(230040420);  
                cm.dispose();
                return;
	      }
        } else if (selection == 10) {//.������껪
            cm.warp(980000000);
            cm.dispose();
            //cm.openNpc(9310057, 0);
          } else if (selection == 60) {//.������껪
            cm.warp(970030001);
            cm.dispose();
            //cm.openNpc(9310057, 0);
        } else if (selection == 15) {//.���ﰲ��
            cm.openNpc(2101018, 0); 
        } else if (selection == 27) {//.��ʮ�߹�
            cm.warp(970030000);
            cm.showInstruction("#r[��ʮ�߹�����˵��]#k\r\n\r\n", 240, 60);
            cm.dispose();
       } else if (selection == 38) {//.�������
            cm.warp(925020000);
            cm.showInstruction("#r[�����������˵��]#k��ȡ����\r\n\r\n", 240, 60);
            cm.dispose();
           
        } else if (selection == 31) {//.����ɨ��
           if (cm.haveItem(4031890) > 0){
           cm.gainItem(4001322,10);
           cm.gainItem(4002000,1);//����ţ��Ʊ
           cm.gainExp(50000);
           cm.gainItem(4031890,-1);
            cm.dispose();
           }
         else{
              cm.sendOk("��û��ɨ����������ɨ������");
              cm.dispose();
             }
        }
      else if (selection == 32) {//.���ɨ��
           if (cm.haveItem(4031890) > 0){
           cm.gainItem(4001322,10);
           cm.gainItem(4002000,1);//����ţ��Ʊ
             cm.gainExp(50000);
           cm.gainItem(4031890,-1);
            cm.dispose();
           }
         else{
              cm.sendOk("��û��ɨ����������ɨ������");
              cm.dispose();
             }
        }
else if (selection == 33) {//.���ɨ��
           if (cm.haveItem(4031890) > 0){
           cm.gainItem(4001322,10);
           cm.gainItem(4002000,1);//����ţ��Ʊ
             cm.gainExp(50000);
           cm.gainItem(4031890,-1);
            cm.dispose();
           }
         else{
              cm.sendOk("��û��ɨ����������ɨ������");
              cm.dispose();
             }
        }
else if (selection == 34) {//.��Ůɨ��
           if (cm.haveItem(4031890) > 0){
           cm.gainItem(4001322,10);
           cm.gainItem(4002000,1);//����ţ��Ʊ
             cm.gainExp(50000);
           cm.gainItem(4031890,-1);
            cm.dispose();
           }
         else{
              cm.sendOk("��û��ɨ����������ɨ������");
              cm.dispose();
             }
        }
else if (selection == 35) {//.����ɨ��
           if (cm.haveItem(4031890) > 0){
           cm.gainItem(4001322,10);
           cm.gainItem(4002000,1);//����ţ��Ʊ
             cm.gainExp(100000);
           cm.gainItem(4031890,-1);
            cm.dispose();
           }
         else{
              cm.sendOk("��û��ɨ����������ɨ������");
              cm.dispose();
             }
        }
else if (selection == 36) {//.����ɨ��
           if (cm.haveItem(4031890) > 0){
           cm.gainItem(4001322,10);
           cm.gainItem(4002000,1);//����ţ��Ʊ
            cm.gainExp(100000);
           cm.gainItem(4031890,-1);
            cm.dispose();
           }
         else{
              cm.sendOk("��û��ɨ����������ɨ������");
              cm.dispose();
             }
        }
    }
}


