var ����= "#fUI/UIWindow.img/PvP/Scroll/enabled/next2#"; 
var ���� = "#fEffect/CharacterEff/1050334/0/1#";
function start() {
    status = -1;

    action(1, 0, 0);
}
function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    } else {
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
			if(cm.getJob() >= 0 && cm.getJob()<= 522 && cm.hasSkill(1017) == false){
			cm.teachSkill(1017,1,1);
			}else if(cm.getJob() >=1000 || cm.getJob() <= 2112 && cm.hasSkill(20001019) == false){
			cm.teachSkill(20001019,1,1);
			}
            var tex2 = "";
            var text = "";
            for (i = 0; i < 10; i++) {
                text += "";
            }

           text += "���ã��𾴵�#b#h ##k#b ���ǵȼ��ɳ������ȡNPC#k\r\n#r[����Ϊ2���������飬�������ע�⣺����ɫ��Ϸ �������� ץ��һ��ͬ�ʣ�\r\n"//3
           //text += " \t   #d�������֣�#r"+cm.getzb()+"#k#n#d\t\t�����#b" + cm.getPlayer().getCSPoints(1) + "#k#n\t#d\r\n"
           text += " #b���ע�⣺��ȡ���ǰ�����������ռ䣬����ÿ����λ����8����λ���������Ը�#e\r\n"
	
           text += "     #L1#" + ���� + " #r10�� �ɳ����#l\r\n"//3
           text += "     #L2#" + ���� + " #r30�� �ɳ����#l\r\n"//3
           text += "     #L3#" + ���� + " #r70�� �ɳ����#l\r\n"//3
           text += "     #L4#" + ���� + " #r100�� �ɳ����#l\r\n"//3
           text += "     #L5#" + ���� + " #r110�� �ɳ����#l\r\n"//3
           text += "     #L6#" + ���� + " #r120�� �ɳ����#l\r\n"//3
           text += "     #L7#" + ���� + " #r130�� �ɳ����#l\r\n"//3
           text += "     #L8#" + ���� + " #r140�� �ɳ����#l\r\n"//3
           text += "     #L9#" + ���� + " #r150�� �ɳ����#l\r\n"//3
           text += "     #L10#" + ���� + " #r160�� �ɳ����#l\r\n"//3
           text += "     #L11#" + ���� + " #r170�� �ɳ����#l\r\n"//3
           text += "     #L12#" + ���� + " #r180�� �ɳ����#l\r\n"//3
           text += "     #L13#" + ���� + " #r190�� �ɳ����#l\r\n"//3
           text += "     #L14#" + ���� + " #r200�� �ɳ����#l\r\n"//3
           text += "     #L15#" + ���� + " #r210�� �ɳ����#l\r\n"//3
           text += "     #L16#" + ���� + " #r220�� �ɳ����#l\r\n"//3
           text += "     #L17#" + ���� + " #r230�� �ɳ����#l\r\n"//3
           text += "     #L18#" + ���� + " #r240�� �ɳ����#l\r\n"//3
           text += "     #L19#" + ���� + " #r250�� �ɳ����#l\r\n"//3


		    cm.sendSimple(text);
        } else if (selection == 0) {//�����г�
            cm.warp(910000000);
            cm.dispose();

        } else if (selection == 100) {//
            cm.dispose();
          cm.openNpc(9310071, 0);
        } else if (selection == 1004) {//
            cm.gainNX(999999);;
            cm.gainMeso(210000000);
              cm.sendOk("\r\n\r\n\t\t\t#e#r��ϲ������99999���!\r\n\r\n\t\t\t#e#r��ϲ������2E���!");
            cm.dispose();


        } else if (selection == 1) {//10���ɳ����
	if (cm.getPlayerStat("LVL") >= 10  && cm.getPlayer().getOneTimeLog("�ɳ����1") <1){
		cm.getPlayer().setOneTimeLog("�ɳ����1");//�����ü�¼
		cm.gainItem(4001126, 100);//500����Ҷ
		cm.gainMeso(300000 );//������
		cm.gainItem(2000005, 30);//����ҩˮ
		cm.gainDY(1000)//����1000��
		//cm.gainItem(1142803,5,5,5,5,50,50,5,5,0,0,0,0,0,0);
		cm.worldMessage(6,"�����³ɳ��������ϲ��ң�["+cm.getName()+"] �ﵽ10������ȡ��10�����³ɳ������");
		cm.sendOk("��ȡ�ɹ���");
		cm.dispose();
		}else{
            cm.sendOk("���Ѿ���ȡ���ˣ�\r\n���ĵȼ�����10�����������������ռ�");
            cm.dispose();
		}
        } else if (selection == 2) {//30���ɳ����
	if(cm.getPlayerStat("LVL") >= 30 && cm.getPlayer().getOneTimeLog("�ɳ����2") < 1){
			cm.getPlayer().setOneTimeLog("�ɳ����2");//�����ü�¼
			cm.gainItem(4001126, 200);//500����Ҷ
			cm.gainMeso(500000 );//������
            cm.gainItem(2000005, 50);//����ҩˮ
			cm.gainDY(3000)//����3000��
	//cm.gainItem(1012011,3,3,3,3,0,0,3,3,0,0,0,0,0,0);//ʥ������
			cm.worldMessage(6,"�����³ɳ��������ϲ��ң�["+cm.getName()+"] �ﵽ30������ȡ��30�����³ɳ������");
            cm.sendOk("��ȡ�ɹ���");
            cm.dispose();
		}else{
            cm.sendOk("���Ѿ���ȡ���ˣ�\r\n���ĵȼ�����30�����������������ռ�");
            cm.dispose();
}
        } else if (selection == 3) {//70���ɳ����
	if(cm.getPlayerStat("LVL")>= 70 && cm.getPlayer().getOneTimeLog("�ɳ����3") < 1){
			cm.getPlayer().setOneTimeLog("�ɳ����3");//�����ü�¼
			cm.gainMeso(1000000 );//������
            cm.gainItem(2000005, 100);//����ҩˮ
			cm.gainDY(5000)//����3000��
			cm.gainItem(4001126, 200);//500����Ҷ
            //cm.gainItem(1003529, 1);//�Ͻ��Ҷñ��
            //cm.gainItem(1052457, 1);//�Ͻ��Ҷ�׷�
            //cm.gainItem(1102394, 1);//�Ͻ��Ҷ����
            //cm.gainItem(1082430, 1);//�Ͻ��Ҷ����
            //cm.gainItem(1072660, 1);//�Ͻ��ҶЬ��
            //cm.gainItem(2022618, 1);//�Ͻ��Ҷ������ѡ
			cm.worldMessage(6,"�����³ɳ��������ϲ��ң�["+cm.getName()+"] �ﵽ70������ȡ��70�����³ɳ������");
            cm.sendOk("��ȡ�ɹ���");
            cm.dispose();
		}else{
            cm.sendOk("���Ѿ���ȡ���ˣ�\r\n���ĵȼ�����70�����������������ռ�");
            cm.dispose();
}
        } else if (selection == 4) {//100���ɳ����
	if(cm.getPlayerStat("LVL") >= 100 && cm.getPlayer().getOneTimeLog("�ɳ����4") < 1){
	cm.getPlayer().setOneTimeLog("�ɳ����4");//�����ü�¼
            cm.gainItem(1003946, 1);//����ñ��
            cm.gainItem(1052647, 1);//�����׷�
            cm.gainItem(1102612, 1);//��������
            cm.gainItem(1082540, 1);//��������
            cm.gainItem(1072853, 1);//����Ь��
            //cm.gainItem(2022613, 1);//����������ѡ
			cm.gainItem(4001126, 200);//500����Ҷ
			cm.gainMeso(3000000 );//������
            cm.gainItem(2000005, 100);//����ҩˮ
			cm.gainDY(10000)//����3000��
            cm.gainNX(5000);
			cm.worldMessage(6,"�����³ɳ��������ϲ��ң�["+cm.getName()+"] �ﵽ100������ȡ��100�����³ɳ������");
            cm.sendOk("��ȡ�ɹ���");
            cm.dispose();
		}else{
            cm.sendOk("���Ѿ���ȡ���ˣ�\r\n���ĵȼ�����100�����������������ռ�");
            cm.dispose();
}
        } else if (selection == 5) {//110���ɳ����
	if(cm.getPlayerStat("LVL") >= 110 && cm.getPlayer().getOneTimeLog("�ɳ����5") < 1){
			cm.getPlayer().setOneTimeLog("�ɳ����5");//�����ü�¼
			cm.gainItem(4001126, 200);//500����Ҷ
			cm.gainMeso(5000000 );//������
            cm.gainItem(2000005, 200);//����ҩˮ
			cm.gainDY(10000)//����3000��
            cm.gainNX(5000);
  	//cm.gainItem(1113091,6,6,6,6,200,200,6,6,0,0,0,0,0,0);//�ɱ���ֹ
			cm.worldMessage(6,"�����³ɳ��������ϲ��ң�["+cm.getName()+"] �ﵽ110������ȡ��110�����³ɳ������");
            cm.sendOk("��ȡ�ɹ���");
            cm.dispose();
		}else{
            cm.sendOk("���Ѿ���ȡ���ˣ�\r\n���ĵȼ�����110�����������������ռ�");
            cm.dispose();
}
        } else if (selection == 6) {//120���ɳ����
	if(cm.getPlayerStat("LVL") >= 120 && cm.getPlayer().getOneTimeLog("�ɳ����6") < 1){
			cm.getPlayer().setOneTimeLog("�ɳ����6");//�����ü�¼
			cm.gainItem(4001126, 200);//500����Ҷ
			cm.gainMeso(5000000 );//������
            cm.gainItem(2000005, 200);//����ҩˮ
			cm.gainDY(10000)//����3000��
            cm.gainNX(5000);
	//cm.gainItem(1012011,5,5,5,5,0,0,5,5,0,0,0,0,0,0);//ʥ������
			cm.worldMessage(6,"�����³ɳ��������ϲ��ң�["+cm.getName()+"] �ﵽ120������ȡ��120�����³ɳ������");
            cm.sendOk("��ȡ�ɹ���");
            cm.dispose();
		}else{
            cm.sendOk("���Ѿ���ȡ���ˣ�\r\n���ĵȼ�����120�����������������ռ�");
            cm.dispose();
}
        } else if (selection == 7) {//130���ɳ����
	if(cm.getPlayerStat("LVL") >= 130 && cm.getPlayer().getOneTimeLog("�ɳ����7") < 1){
			cm.getPlayer().setOneTimeLog("�ɳ����7");//�����ü�¼
			cm.gainItem(4001126, 300);//500����Ҷ
			cm.gainMeso(5000000 );//������
            cm.gainItem(2000005, 200);//����ҩˮ
			cm.gainDY(10000)//����3000��
            cm.gainNX(5000);

			cm.worldMessage(6,"�����³ɳ��������ϲ��ң�["+cm.getName()+"] �ﵽ130������ȡ��130�����³ɳ������");
            cm.sendOk("��ȡ�ɹ���");
            cm.dispose();
		}else{
            cm.sendOk("���Ѿ���ȡ���ˣ�\r\n���ĵȼ�����130�����������������ռ�");
            cm.dispose();
}
        } else if (selection == 8) {//140���ɳ����
	if(cm.getPlayerStat("LVL") >= 140 && cm.getPlayer().getOneTimeLog("�ɳ����8") < 1){
			cm.getPlayer().setOneTimeLog("�ɳ����8");//�����ü�¼
			cm.gainItem(4001126, 300);//500����Ҷ
			cm.gainMeso(5000000 );//������
            cm.gainItem(2000005, 200);//����ҩˮ
			cm.gainDY(10000)//����3000��
            cm.gainNX(5000);

			cm.worldMessage(6,"�����³ɳ��������ϲ��ң�["+cm.getName()+"] �ﵽ140������ȡ��140�����³ɳ������");
            cm.sendOk("��ȡ�ɹ���");
            cm.dispose();
		}else{
            cm.sendOk("���Ѿ���ȡ���ˣ�\r\n���ĵȼ�����140�����������������ռ�");
            cm.dispose();
}
        } else if (selection == 9) {//150���ɳ����
	if(cm.getPlayerStat("LVL") >= 150 && cm.getPlayer().getOneTimeLog("�ɳ����9") < 1){
			cm.getPlayer().setOneTimeLog("�ɳ����9");//�����ü�¼
			cm.gainItem(4001126, 300);//500����Ҷ
			cm.gainMeso(10000000 );//������
            cm.gainItem(2000005, 200);//����ҩˮ
			cm.gainDY(10000)//����3000��
            cm.gainNX(10000);
  		//cm.gainItem(1142650,15,15,15,15,200,200,10,10,0,0,0,0,0,0);
			cm.worldMessage(6,"�����³ɳ��������ϲ��ң�["+cm.getName()+"] �ﵽ150������ȡ��150�����³ɳ������");
            cm.sendOk("��ȡ�ɹ���");
            cm.dispose();
		}else{
            cm.sendOk("���Ѿ���ȡ���ˣ�\r\n���ĵȼ�����150�����������������ռ�");
            cm.dispose();
}
        } else if (selection == 10) {//160���ɳ����
	if(cm.getPlayerStat("LVL") >= 160 && cm.getPlayer().getOneTimeLog("�ɳ����10") < 1){
			cm.getPlayer().setOneTimeLog("�ɳ����10");//�����ü�¼
			cm.gainItem(4001126, 300);//500����Ҷ
			cm.gainMeso(10000000 );//������
            cm.gainItem(2000005, 200);//����ҩˮ
			cm.gainDY(10000)//����3000��
            cm.gainNX(10000);
			cm.worldMessage(6,"�����³ɳ��������ϲ��ң�["+cm.getName()+"] �ﵽ160������ȡ��160�����³ɳ������");
            cm.sendOk("��ȡ�ɹ���");
            cm.dispose();
		}else{
            cm.sendOk("���Ѿ���ȡ���ˣ�\r\n���ĵȼ�����160�����������������ռ�");
            cm.dispose();
}


        } else if (selection == 11) {//170���ɳ����
	if(cm.getPlayerStat("LVL") >= 170 && cm.getPlayer().getOneTimeLog("�ɳ����11") < 1){
			cm.getPlayer().setOneTimeLog("�ɳ����11");//�����ü�¼
			cm.gainItem(4001126, 300);//500����Ҷ
			cm.gainMeso(10000000 );//������
            cm.gainItem(2000005, 200);//����ҩˮ
			cm.gainDY(20000)//����3000��
            cm.gainNX(20000);
			cm.worldMessage(6,"�����³ɳ��������ϲ��ң�["+cm.getName()+"] �ﵽ170������ȡ��170�����³ɳ������");
            cm.sendOk("��ȡ�ɹ���");
            cm.dispose();
		}else{
            cm.sendOk("���Ѿ���ȡ���ˣ�\r\n���ĵȼ�����170�����������������ռ�");
            cm.dispose();
}
        } else if (selection == 12) {//180���ɳ����
	if(cm.getPlayerStat("LVL") >= 180 && cm.getPlayer().getOneTimeLog("�ɳ����12") < 1){
			cm.getPlayer().setOneTimeLog("�ɳ����12");//�����ü�¼
			cm.gainItem(4001126, 300);//500����Ҷ
			cm.gainMeso(10000000 );//������
            cm.gainItem(2000005, 200);//����ҩˮ
			cm.gainDY(20000)//����3000��
            cm.gainNX(20000);
			cm.worldMessage(6,"�����³ɳ��������ϲ��ң�["+cm.getName()+"] �ﵽ180������ȡ��180�����³ɳ������");
            cm.sendOk("��ȡ�ɹ���");
            cm.dispose();
		}else{
            cm.sendOk("���Ѿ���ȡ���ˣ�\r\n���ĵȼ�����180�����������������ռ�");
            cm.dispose();
}
        } else if (selection == 13) {//190���ɳ����
	if(cm.getPlayerStat("LVL") >= 190 && cm.getPlayer().getOneTimeLog("�ɳ����13") < 1){
			cm.getPlayer().setOneTimeLog("�ɳ����13");//�����ü�¼
			cm.gainItem(4001126, 300);//500����Ҷ
			cm.gainMeso(10000000 );//������
            cm.gainItem(2000005, 200);//����ҩˮ
			cm.gainDY(20000)//����3000��
            cm.gainNX(20000);
			cm.worldMessage(6,"�����³ɳ��������ϲ��ң�["+cm.getName()+"] �ﵽ190������ȡ��190�����³ɳ������");
            cm.sendOk("��ȡ�ɹ���");
            cm.dispose();
		}else{
            cm.sendOk("���Ѿ���ȡ���ˣ�\r\n���ĵȼ�����190�����������������ռ�");
            cm.dispose();
}
        } else if (selection == 14) {//200���ɳ����
	if(cm.getPlayerStat("LVL") >= 200 && cm.getPlayer().getOneTimeLog("�ɳ����14") < 1){
			cm.getPlayer().setOneTimeLog("�ɳ����14");//�����ü�¼
			cm.gainItem(4001126, 500);//500����Ҷ
			cm.gainMeso(10000000 );//������
            cm.gainItem(2000005, 200);//����ҩˮ
			cm.gainDY(30000)//����3000��
            cm.gainNX(30000);
  	//cm.gainItem(1142472,20,20,20,20,400,400,15,15,0,0,0,0,0,0);
             //cm.gainItem(1142111,10,10,10,10,50,50,10,10,10,10,10,10,10,10);
			cm.worldMessage(6,"�����³ɳ��������ϲ��ң�["+cm.getName()+"] �ﵽ200������ȡ��200�����³ɳ������");
            cm.sendOk("��ȡ�ɹ���");
            cm.dispose();
		}else{
            cm.sendOk("���Ѿ���ȡ���ˣ�\r\n���ĵȼ�����200�����������������ռ�");
            cm.dispose();
}
        } else if (selection == 15) {//210���ɳ����
	if(cm.getPlayerStat("LVL") >= 210 && cm.getPlayer().getOneTimeLog("�ɳ����15") < 1){
			cm.getPlayer().setOneTimeLog("�ɳ����15");//�����ü�¼
			cm.gainItem(4001126, 500);//500����Ҷ
			cm.gainMeso(20000000 );//������
            cm.gainItem(2000005, 200);//����ҩˮ
			cm.gainDY(30000)//����3000��
            cm.gainNX(30000);
			cm.worldMessage(6,"�����³ɳ��������ϲ��ң�["+cm.getName()+"] �ﵽ210������ȡ��210�����³ɳ������");
            cm.sendOk("��ȡ�ɹ���");
            cm.dispose();
		}else{
            cm.sendOk("���Ѿ���ȡ���ˣ�\r\n���ĵȼ�����210�����������������ռ�");
            cm.dispose();
}
        } else if (selection == 16) {//220���ɳ����
	if(cm.getPlayerStat("LVL") >= 200 && cm.getPlayer().getOneTimeLog("�ɳ����16") < 1){
			cm.getPlayer().setOneTimeLog("�ɳ����16");//�����ü�¼
			cm.gainItem(4001126, 500);//500����Ҷ
			cm.gainMeso(10000000 );//������
            cm.gainItem(2000005, 200);//����ҩˮ
			cm.gainDY(30000)//����3000��
            cm.gainNX(30000);
			cm.worldMessage(6,"�����³ɳ��������ϲ��ң�["+cm.getName()+"] �ﵽ220������ȡ��220�����³ɳ������");
            cm.sendOk("��ȡ�ɹ���");
            cm.dispose();
		}else{
            cm.sendOk("���Ѿ���ȡ���ˣ�\r\n���ĵȼ�����220�����������������ռ�");
            cm.dispose();
}
        } else if (selection == 17) {//230���ɳ����
	if(cm.getPlayerStat("LVL") >= 230 && cm.getPlayer().getOneTimeLog("�ɳ����17") < 1){
			cm.getPlayer().setOneTimeLog("�ɳ����17");//�����ü�¼
			cm.gainItem(4001126, 1000);//500����Ҷ
			cm.gainMeso(10000000 );//������
            cm.gainItem(2000005, 200);//����ҩˮ
			cm.gainDY(30000)//����3000��
            cm.gainNX(30000);
			cm.worldMessage(6,"�����³ɳ��������ϲ��ң�["+cm.getName()+"] �ﵽ230������ȡ��230�����³ɳ������");
            cm.sendOk("��ȡ�ɹ���");
            cm.dispose();
		}else{
            cm.sendOk("���Ѿ���ȡ���ˣ�\r\n���ĵȼ�����230�����������������ռ�");
            cm.dispose();
}
        } else if (selection == 18) {//240���ɳ����
	if(cm.getPlayerStat("LVL") >= 240 && cm.getPlayer().getOneTimeLog("�ɳ����18") < 1){
			cm.getPlayer().setOneTimeLog("�ɳ����18");//�����ü�¼
			cm.gainItem(4001126, 1000);//500����Ҷ
			cm.gainMeso(10000000 );//������
            cm.gainItem(2000005, 200);//����ҩˮ
			cm.gainDY(30000)//����3000��
            cm.gainNX(30000);
			cm.worldMessage(6,"�����³ɳ��������ϲ��ң�["+cm.getName()+"] �ﵽ240������ȡ��240�����³ɳ������");
            cm.sendOk("��ȡ�ɹ���");
            cm.dispose();
		}else{
            cm.sendOk("���Ѿ���ȡ���ˣ�\r\n���ĵȼ�����240�����������������ռ�");
            cm.dispose();
}
        } else if (selection == 19) {//250���ɳ����
	if(cm.getPlayerStat("LVL") >= 250 && cm.getPlayer().getOneTimeLog("�ɳ����19") < 1){
			cm.getPlayer().setOneTimeLog("�ɳ����19");//�����ü�¼
			cm.gainItem(4001126, 1000);//500����Ҷ
			cm.gainMeso(10000000 );//������
            cm.gainItem(2000005, 200);//����ҩˮ
			cm.gainDY(100000)//����3000��
            cm.gainNX(100000);
			cm.worldMessage(6,"�����³ɳ��������ϲ��ң�["+cm.getName()+"] �ﵽ250������ȡ��250�����³ɳ������");
            cm.sendOk("��ȡ�ɹ���");
            cm.dispose();
		}else{
            cm.sendOk("���Ѿ���ȡ���ˣ�\r\n���ĵȼ�����250�����������������ռ�");
            cm.dispose();
}

//============================================
		}


		}
    }
//}


