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
            text += "#e#d��ȡÿ��ǩ�����������·���ɫѡ����ȡǩ�������\r\n\r\n"
            text += "#L1##r��ȡÿ��ǩ�����#l\r\n\r\n"//3
            cm.sendSimple(text);
        } else if (selection == 1) {
			/*if(!cm.beibao(1,3)){
            cm.sendOk("װ�������಻��3���ո�");
            cm.dispose();
			}else if(!cm.beibao(2,2)){
            cm.sendOk("���������಻��2���ո�");
            cm.dispose();
			}else if(!cm.beibao(3,1)){
            cm.sendOk("���������಻��1���ո�");
            cm.dispose();
			}else if(!cm.beibao(4,1)){
            cm.sendOk("���������಻��1���ո�");
            cm.dispose();
			}else if(!cm.beibao(5,1)){
            cm.sendOk("�ֽ������಻��1���ո�");
            cm.dispose();
			}else */		
			if(cm.getPlayer().getBossLog("ÿ��ǩ��") >= 1){//�жϴ���
			cm.sendOk("���Ѿ���ȡ��ÿ�������");
            cm.dispose();
			}else{
			//cm.gainItem(5151001, 1);//Ⱦɫ
			//cm.gainItem(5152001, 1);//����
			//cm.gainItem(5153000, 1);//����
			//cm.gainMeso(999999);
			//cm.gainMeso(50000);   //���
			cm.gainItem(5040000,2);//����ɡ������Ա��װ
			cm.gainItem(5390002,2);//С�����ƶ�ñ��
			//cm.gainItem(5131000,1);//������
			cm.gainItem(4032398,1);//С�����ƶ��׷�
			cm.gainItem(5220000,5);//�𼦹�ñ
			cm.gainItem(5150040,1);//�ʼ���ȯ
			//cm.gainItem(5000053,1);//���������
			//cm.gainItem(2022468,1);//ϡ����߳齱����
			//cm.gainItem(2000002,200);//��ɫҩˮ
			//cm.gainItem(2000003,200);//��ɫҩˮ
			//cm.gainDY(8888);
            cm.sendOk("��ȡ�ɹ���");
			cm.getPlayer().setBossLog("ÿ��ǩ��");
			//cm.setBossLog("mrlb");//�������
			cm.worldMessage(6,"��ң�["+cm.getName()+"]��ȡ��ÿ��ǩ�������");
            cm.dispose();
			}
		}
    }
}


