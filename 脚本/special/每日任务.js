var ���ڽ����� = "#fUI/UIWindow/Quest/Tab/enabled/1#";
var ��� = "#fUI/UIWindow/Quest/Tab/enabled/2#";
var ���ڽ������� = "#fUI/UIWindow/MonsterCarnival/icon1#";
var ��ɺ� = "#fUI/UIWindow/MonsterCarnival/icon0#";
var ���� = "#fEffect/CharacterEff/1022223/4/0#";
var ��ɫ��ͷ = "#fUI/UIWindow/Quest/icon6/7#";
var ������ = "#fUI/UIWindow/Quest/icon3/6#";
var ��ɫ��ͷ = "#fUI/UIWindow/Quest/icon2/7#";
var ��ɫ�ǵ� = "#fUI/UIWindow.img/PvP/Scroll/enabled/next2#";
var ���ڽ����� = "#fUI/UIWindow/Quest/Tab/enabled/1#";
var ��� = "#fUI/UIWindow/Quest/Tab/enabled/2#";
var ���ڽ������� = "#fUI/UIWindow/MonsterCarnival/icon1#";
var ��ɺ� = "#fUI/UIWindow/MonsterCarnival/icon0#";
var ���� = "#fEffect/CharacterEff/1051295/0/0#";
var �ʹڰ� = "#fEffect/CharacterEff/1003252/0/0#";
var Сѩ�� = "#fEffect/CharacterEff/1003393/0/0#";
var ���� = "#fEffect/CharacterEff/1032063/0/0#";
var ��̾�� = "#fUI/UIWindow/Quest/icon0#";
var ��̾�� = "#fUI/UIWindow/Quest/icon0#";
var �ʹڰ� ="#fUI/GuildMark/Mark/Etc/00009004/16#";;
var ��ˮ ="#fUI/GuildMark/Mark/Pattern/00004008/15#";
var ������ ="#fUI/Basic/HScr7/disabled/base#";
var ������ ="#fUI/ChatBalloon/tutorial/w#";

var ����è ="#fUI/ChatBalloon/169/n#";
var è�� =  "#fUI/ChatBalloon/169/ne#";
var è�� =  "#fUI/ChatBalloon/169/nw#";
var �� =    "#fUI/ChatBalloon/169/e#";
var �� =    "#fUI/ChatBalloon/169/w#";
  
var ����è ="#fUI/ChatBalloon/169/s#";
var è���� ="#fUI/ChatBalloon/169/se#";
var è���� ="#fUI/ChatBalloon/169/sw#";
var а��С��2 = "#fEffect/CharacterEff/1112960/3/1#";  //а��С�� ����

var status = 0;
var suiji;
//var suiji2;
var wpid = Array(
  2043003,//���ֽ���������(�س�)
  2044003,//˫�ֽ���������(�س�)
  2044303,//ǹ������������(�س�)
  2044403,//ì������������(�س�)
  2044503,//��������������(�س�)
  2044603,//�󹥻��سɾ�
  2043703,//���ȹ����سɾ�
  2043803,//���ȹ�������(�س�)
  2043303,//�̽������سɾ�
  2044703,//ȭ�׹�������(�س�)
  2044908,//��ǹ��������(�س�)
  2044815,//ָ�ڹ����سɾ� 
  2040807,//���׹�������(�س�)
  2040506,//ȫ���������ݾ���(�س�)
  2040710,//Ь����Ծ����(�س�)
  4011007,//��ʯ
  4021009,//��ʯ
  4011008,//�(��ʯ)
  4000463,//��������
  2022428,//��������
  2614000);//�ƹ�ʯͷһ��
//cm.gainItem(wpid[suiji],1);
//cm.gainItem(wpid[suiji2],1);

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
			//text += "   "+è��+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+è��+"\r\n";
                        text += "           #K"+�ʹڰ�+" #eð�յ�ÿ���ռ�����#n "+�ʹڰ�+"#k\r\n"
						//text += "          #rÿ���ռ�����������:�����������!!#k\r\n"
			//text += "   "+è����+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+è����+"\r\n";
			if(cm.getPlayer().getBossLog("ÿ������1") == 0  ){
					text += "             #L1#"+����+"#e#wÿ������01(#r�ɿ�ʼ#k)#n#l\r\n\r\n"//3
				} else if(cm.getPlayer().getBossLog("ÿ������1") > 0){
					text += "             "+����+"#r#eÿ����������01"+����+"#n#l"+���+"#k\r\n"//3
			}
			
			if(cm.getPlayer().getBossLog("ÿ������") == 1){
					text += "             #L2#"+����+"#eÿ����������02(#r�ɿ�ʼ#k)#n#l\r\n\r\n"//3
				} else if(cm.getPlayer().getBossLog("ÿ������") > 1){
					text += "             "+����+"#r#eÿ����������02"+����+"#n#l"+���+"#k\r\n"//3
				} else {
					text += "	          "+�ʹڰ�+"#e ÿ����������02#l\r\n"//3
			}
			
			if(cm.getPlayer().getBossLog("ÿ������") == 2){
					text += "             #L3#"+����+"#eÿ����������03(#r�ɿ�ʼ#k)#n#l\r\n\r\n"//3
				} else if(cm.getPlayer().getBossLog("ÿ������") > 2){
					text += "             "+����+"#r#eÿ����������03"+����+"#n#l"+���+"#k\r\n"//3
				} else {
					text += "	          "+�ʹڰ�+"#e ÿ����������03#l\r\n"//3
			}
			if(cm.getPlayer().getBossLog("ÿ������") == 3){
					text += "             #L4#"+����+"#eÿ����������04(#r�ɿ�ʼ#k)#n#l\r\n\r\n"//3
				} else if(cm.getPlayer().getBossLog("ÿ������") > 3){
					text += "             "+����+"#r#eÿ����������04"+����+"#n#l"+���+"#k\r\n"//3
				} else {
					text += "	          "+�ʹڰ�+"#e ÿ����������04#l\r\n"//3
			}
			if(cm.getPlayer().getBossLog("ÿ������") == 4){
					text += "             #L5#"+����+"#eÿ����������05(#r�ɿ�ʼ#k)#n#l\r\n\r\n"//3
				} else if(cm.getPlayer().getBossLog("ÿ������") > 4){
					text += "             "+����+"#r#eÿ����������05"+����+"#n#l"+���+"#k\r\n"//3
				} else {
					text += "	          "+�ʹڰ�+"#e ÿ����������05#l\r\n"//3
			}
			if(cm.getPlayer().getBossLog("ÿ������") == 5){
					text += "             #L6#"+����+"#eÿ����������06(#r�ɿ�ʼ#k)#n#l\r\n\r\n"//3
				} else if(cm.getPlayer().getBossLog("ÿ������") > 5){
					text += "             "+����+"#r#eÿ����������06"+����+"#n#l"+���+"#k\r\n"//3
				} else {
					text += "	          "+�ʹڰ�+"#e ÿ����������06#l\r\n"//3
			}
			if(cm.getPlayer().getBossLog("ÿ������") == 6){
					text += "             #L7#"+����+"#eÿ����������07(#r�ɿ�ʼ#k)#n#l\r\n\r\n"//3
				} else if(cm.getPlayer().getBossLog("ÿ������") > 6){
					text += "             "+����+"#r#eÿ����������07"+����+"#n#l"+���+"#k\r\n"//3
				} else {
					text += "	          "+�ʹڰ�+"#e ÿ����������07#l\r\n"//3
			}
			if(cm.getPlayer().getBossLog("ÿ������") == 7){
					text += "             #L8#"+����+"#eÿ����������08(#r�ɿ�ʼ#k)#n#l\r\n\r\n"//3
				} else if(cm.getPlayer().getBossLog("ÿ������") > 7){
					text += "             "+����+"#r#eÿ����������08"+����+"#n#l"+���+"#k\r\n"//3
				} else {
					text += "	          "+�ʹڰ�+"#e ÿ����������08#l\r\n"//3
			}
							
            cm.sendSimple(text);
		} else if (cm.getInventory(1).isFull(2)){//�жϵ�һ��Ҳ����װ������װ�����Ƿ���һ���ո�
			cm.sendOk("#bװ�����ռ䲻��3��.");	
			cm.dispose();		
		} else if (cm.getInventory(2).isFull(2)){//�жϵڶ���Ҳ������������װ�����Ƿ���һ���ո�	
			cm.sendOk("#b�������ռ䲻��3��.");	
			cm.dispose();	
		} else if (cm.getInventory(3).isFull(2)){//�жϵ�����Ҳ������������װ�����Ƿ���һ���ո�	
			cm.sendOk("#b�������ռ䲻��3��.");	
			cm.dispose();
		} else if (cm.getInventory(4).isFull(2)){//�жϵ��ĸ�Ҳ������������װ�����Ƿ���һ���ո�
			cm.sendOk("#b�������ռ䲻��3��.");	
			cm.dispose();
		} else if (selection == 1) {
		if (cm.haveItem(4000002,50) && cm.haveItem(4000017,10)){
			cm.gainItem(4000002, -50);//�����Ʒ
			cm.gainItem(4000017, -10);//�����Ʒ
			//cm.gainExp(10000);//���˸�����
			//cm.gainExp(10000);//���˸�����
			//cm.gainExp(10000);//���˸�����
			cm.gainMeso(+500000); //�Ӽ����
			//cm.gainNX(100);//���
			cm.worldMessage(6,"��ң�["+cm.getName()+"]�����ÿ���ռ�����1����ã�50W��Ϸ�ң�");
			cm.getPlayer().setBossLog("ÿ������1");
			cm.getPlayer().setBossLog("ÿ������");
			
			cm.sendOk("�����ÿ���ռ�����1����ã�50W��Ϸ�ң�����");
			cm.dispose();
		}else{

			cm.sendOk(��̾��+"#r��ӭ����ÿ���ռ�����1���ռ�50��#v4000002#10��#v4000017#��\r\n#r������50W��Ϸ�ң�");
			cm.dispose();
	    }
        } else if (selection == 2) {
		if (cm.haveItem(4000013,50) ){
			cm.gainItem(4000013, -50);//�����Ʒ
			//cm.gainItem(4000008, -100);//�����Ʒ
			cm.gainMeso(+500000); //�Ӽ����
			//cm.gainNX(200);//���
			//cm.gainExp(10000);//���˸�����
			//cm.gainExp(10000);//���˸�����
			//cm.gainExp(10000);//���˸�����
			//cm.gainExp(10000);//���˸�����
			//cm.gainExp(10000);//���˸�����
			//cm.gainItem(1102828,10,10,10,10,10,10,10,10,1,1,1,10,10,10);//���˽�ָ
			cm.getPlayer().setBossLog("ÿ������");
			cm.worldMessage(6,"��ң�["+cm.getName()+"]�����ÿ���ռ�����2����ã�50W��Ϸ�ң�����");
			cm.sendOk("�����ÿ���ռ�����2����ã�50W��Ϸ�ң�������");
			cm.dispose();
		}else{

			cm.sendOk(��̾��+"#r��ӭ����ÿ���ռ�����1���ռ�#v4000013#50����\r\n#r������50W��Ϸ�ң�");
			cm.dispose();
	    }
        } else if (selection == 3) {
		if (cm.haveItem(4000190,30) && cm.haveItem(4000191,30) ){
			cm.gainItem(4000190,-30);
			cm.gainItem(4000191,-30);
			//cm.gainItem(4000215,-100);
			cm.gainMeso(+500000); //�Ӽ����
			cm.getPlayer().setBossLog("ÿ������");
			cm.worldMessage(6,"��ң�["+cm.getName()+"]�����ÿ���ռ�����3����ã�50W��Ϸ�ң�����");
			cm.sendOk("�����ÿ���ռ�����3����ã�50W��Ϸ�ң�������");
			cm.dispose();	
		}else{
		 cm.sendOk(��̾��+"#r��ӭ����ÿ���ռ�����3���ռ�#v4000190#  #v4000191#��30����\r\n#r������50W��Ϸ�ң�");
		 cm.dispose();
		
	    }
        } else if (selection == 4) {
			
		if (cm.haveItem(4000043,50) ){
			cm.gainItem(4000043, -50);//�����Ʒ
			cm.gainMeso(+500000); //�Ӽ����
			cm.getPlayer().setBossLog("ÿ������");
			cm.worldMessage(6,"��ң�["+cm.getName()+"]�����ÿ���ռ�����4����ã�50W��Ϸ�ң�����");
			cm.sendOk("�����ÿ���ռ�����4����ã�50W��Ϸ�ң�������");
			cm.dispose();	
		}else{
		 cm.sendOk(��̾��+"#r��ӭ����ÿ���ռ�����4���ռ�#v4000043#50����\r\n#r������50W��Ϸ�ң�");
		 cm.dispose();
	    }
		
        } else if (selection == 5) {			
		if (cm.haveItem(4000165,20) && cm.haveItem(4000164,20)){
			cm.gainItem(4000165, -20);//��
			cm.gainItem(4000164, -20);//��
			cm.gainMeso(+500000);
			cm.getPlayer().setBossLog("ÿ������");
			cm.worldMessage(6,"��ң�["+cm.getName()+"]�����ÿ���ռ�����5����ã�50w��Ϸ�ң���");
			cm.sendOk("�����ÿ���ռ�����5����ã�100���������");
			cm.dispose();	
		}else{
		 cm.sendOk(��̾��+"#r��ӭ����ÿ���ռ�����5���ռ�#v4000165#  #v4000164#��20����\r\n#r������50w��Ϸ�ң�");
		 cm.dispose();
	    }
		
        } else if (selection == 6) {
			if (cm.haveItem(4000173,50)){
			cm.gainItem(4000173, -50);//�����Ʒ
			cm.gainMeso(+500000);
			cm.getPlayer().setBossLog("ÿ������");
			cm.worldMessage(6,"��ң�["+cm.getName()+"]�����ÿ���ռ�����6����ã�50w��Ϸ�ң���");
			cm.sendOk("�����ÿ���ռ�����6����ã�50w��Ϸ�ң�������");
			cm.dispose();	
		}else{
		 cm.sendOk(��̾��+"#r��ӭ����ÿ���ռ�����6���ռ�#v4000173#50����\r\n#r������50w��Ϸ�ң�");
		 cm.dispose();
	    }
        } else if (selection == 7) {
			if (cm.haveItem(2020015,30)&& cm.haveItem(2020014,30)){
			cm.gainItem(2020015, -30);//�����Ʒ
			cm.gainItem(2020014, -30);//�����Ʒ
			cm.getPlayer().setBossLog("ÿ������");
			cm.gainMeso(+500000);
			cm.worldMessage(6,"��ң�["+cm.getName()+"]�����ÿ���ռ�����7����ã�50w��Ϸ�ң�");
			cm.sendOk("�����ÿ���ռ�����7����ã�50w��Ϸ�ң���");
			cm.dispose();	
		}else{
		 cm.sendOk(��̾��+"#r��ӭ����ÿ���ռ�����7���ռ�#v2020015#  #v2020014#��30����\r\n#r������50w��Ϸ�ң���");
		 cm.dispose();

	    }
        } else if (selection == 8) {
			if (cm.haveItem(4000040,1)&& cm.haveItem(4000176,1)){
			cm.gainItem(4000040, -1);//�����Ʒ
			cm.gainItem(4000176, -1);//�����Ʒ
			cm.getPlayer().setBossLog("ÿ������");
			cm.gainItem(4032398, 1); //�Ӽ����
			cm.worldMessage(6,"��ң�["+cm.getName()+"]�����ÿ���ռ�����8����ã���ϯͼ�£�");
			cm.sendOk("�����ÿ���ռ�����8����ã� ��ϯͼ�£�");
			cm.dispose();	
		}else{
		 cm.sendOk(��̾��+"#r��ӭ����ÿ���ռ�����8���ռ�#v4000040#  #v4000176#��1����\r\n#r��������ϯͼ�£���");
		 cm.dispose();
	    }
		} 
    }
}