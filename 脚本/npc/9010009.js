var CY0 = "��������������������������������������������������������������������";
var CY1 = "��       - ���� -       ��";
var CY2 = "�� �ű�����  �����ƽű� ��";
var CY3 = "�� ����֧�� �� ��Ϸ���� ��";
var CY4 = "�� �ף���ӡ�  ��ͼ���� ��";
var CY5 = "�� �Ӷܷ�����  �۵�½�� ��";
var CY6 = "�ǩ�����������������������������������������������������������������";
var CY7 = "��   ΨһQQ:12384161    ��";
var CY8 = "��������������������������������������������������������������������";
var ���� = "#fUI/CashShop/CSDiscount/bonus#";
var ����ֵ = "#fUI/UIWindow/QuestIcon/8/0#";
var ��� = "#fUI/UIWindow/QuestIcon/7/0#";
var ��ɫ��ͷ = "#fEffect/CharacterEff/1112908/0/1#";  //�ʹ�3
var ����� = "#fEffect/CharacterEff.img/1022223/1/0#";
var ���ڽ����� = "#fUI/UIWindow/Quest/Tab/enabled/1#";
var ë�� = "#fUI/ChatBalloon.img/pet/12/nw#";
var ��ȯ = "#fUI/CashShop/CashItem/0#";
var ��� = "#fUI/UIWindow/Quest/Tab/enabled/2#";
var ���� = "#fUI/Basic/BtClaim/normal/0#";
var ��Ʒ���ۿ� = "#fUI/CashShop/GuideWords/0#";
var status = 0;
function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    } else {
        if (mode == 0) {
            cm.dispose();
            return;
        }
        if (mode == 1)
            status++;
        if (status == 0) {
            var txt = "";
            if (cm.getBossLog('ÿ������') < 1) {
            //if (cm.getPS() == 0){// cm.getPS()  ����˼�� ��ȡ����ֵ�������0 �͵ó���û�п�ʼ���� �����������е�һ������!
				txt = "\t\t\t\t#e"+ë��+"ÿ�����̵�һ��"+ë��+"#k#n\r\n";
                txt += "�ռ�������Ʒ��\r\n";
				txt += "#v4000002# #z4000002# [#r#c4000002##k/30] #v4000017# #z4000017# [#r#c4000017##k/20] "+���ڽ�����+"\r\n";
				txt += "#L2#"+��ɫ��ͷ+"���ٴ�������ͼ(��ĺ���) ��Ҫ X 1����#l\r\n\r\n";
                txt += ""+����+"\r\n";
				txt += "#v4000313#  X 5\r\n";
				txt += ""+����ֵ+" X 10000 (���ŵȼ�����Exp)\r\n";
				//txt += ""+��ȯ+" ���þ� X 100\r\n";
				txt += "#L1##b"+��ɫ��ͷ+"�ύ�������#l\r\n";
                cm.sendSimple(txt);
            }else if(cm.getBossLog('ÿ������') == 1){
                txt += "���Ѿ������ÿ�����̵�һ�� "+���+"\r\n";
                txt += "\t\t\t\t#e"+ë��+"ÿ�����̵ڶ���"+ë��+"#k#n\r\n\r\n";
				txt += "�ڶ�����ͼ��\r\n";
				txt += "���ִ� - NPC ������\r\n";// 9209000
                txt += "�ռ�������Ʒ��\r\n";
				txt += "#v4000013# #z4000013# [#r#c4000013##k/50]\r\n";
                cm.sendOk(txt);
                cm.dispose();
            }else if(cm.getBossLog('ÿ������') == 2){
                txt += "���Ѿ������ÿ�����̵ڶ��� "+���+"\r\n";
                txt += "\t\t\t\t#e"+ë��+"ÿ�����̵�����"+ë��+"#k#n\r\n\r\n";
				txt += "��������ͼ��\r\n";
                txt += "ħ������ - NPC ���������׵�!\r\n";// 1032002
                txt += "#v4000190# #z4000190# [#r#c4000190##k/25] #v4000191# #z4000191# [#r#c4000191##k/25]\r\n\r\n";
                cm.sendOk(txt);
                cm.dispose();
            }else if(cm.getBossLog('ÿ������') == 3){
                txt += "���Ѿ������ÿ�����̵����� "+���+"\r\n";
                txt += "\t\t\t\t#e"+ë��+"ÿ�����̵��Ļ�"+ë��+"#k#n\r\n\r\n";
				txt += "���Ļ���ͼ��\r\n";
                txt += "��ʿ���� - �ֿ����Ա ������!\r\n";// 1022005
                txt += "#v4000043# #z4000043# [#r#c4000043##k/50]\r\n";
                cm.sendOk(txt);
                cm.dispose();
            }else if(cm.getBossLog('ÿ������') == 4){
                txt += "���Ѿ������ÿ�����̵��Ļ� "+���+"\r\n";
                txt += "\t\t\t\t#e"+ë��+"ÿ�����̵��廷"+ë��+"#k#n\r\n\r\n";
				txt += "���廷��ͼ��\r\n";
                txt += "�������� - NPC ��������\r\n";// 1052012
                txt += "#v4000164# #z4000164# [#r#c4000164##k/25] #v4000165# #z4000165# [#r#c4000165##k/25]\r\n";
                cm.sendOk(txt);
                cm.dispose();
            }else if(cm.getBossLog('ÿ������') == 5){
                txt += "���Ѿ������ÿ�����̵��廷 "+���+"\r\n";
                txt += "\t\t\t\t#e"+ë��+"ÿ�����̵�����"+ë��+"#k#n\r\n\r\n";
				txt += "��������ͼ��\r\n";
                txt += "����֮�� - NPC �ֿ����Ա ������\r\n";// 1061008
                txt += "#v4000173# #z4000173# [#r#c4000173##k/50]\r\n";
                cm.sendOk(txt);
                cm.dispose();
            }else if(cm.getBossLog('ÿ������') == 6){
                txt += "���Ѿ������ÿ�����̵����� "+���+"\r\n";
                txt += "\t\t\t\t#e"+ë��+"ÿ�����̵��߻�"+ë��+"#k#n\r\n\r\n";
				txt += "���߻���ͼ��\r\n";
                txt += "���ֹ�԰ - NPC �ֿ����Ա ������\r\n";// 1012009
                txt += "#v4000379# #z4000379# [#r#c4000379##k/100]\r\n";
                cm.sendOk(txt);
                cm.dispose();
            }else if(cm.getBossLog('ÿ������') == 7){
                txt += "���Ѿ������ÿ�����̵��߻� "+���+"\r\n";
                txt += "\t\t\t\t#e"+ë��+"ÿ�����̵ڰ˻�"+ë��+"#k#n\r\n\r\n";
				txt += "�ڰ˻���ͼ��\r\n";
                txt += "��� - NPC �ֿ����Ա ��˹��\r\n";// 1200000
                txt += "#v2020015# #z2020015# [#r#c2020015##k/30] #v2020014# #z2020014# [#r#c2020014##k/30]\r\n";
                cm.sendOk(txt);
                cm.dispose();
            }else if(cm.getBossLog('ÿ������') == 8){
                txt += "���Ѿ������ÿ�����̵ڰ˻� "+���+"\r\n";
                txt += "\t\t\t\t#e"+ë��+"ÿ�����̵ھŻ�"+ë��+"#k#n\r\n\r\n";
				txt += "�ھŻ���ͼ��\r\n";
                txt += "��߳� - NPC �ֿ����Ա ����\r\n";// 2041008
                txt += "#v4000265# #z4000265# [#r#c4000265##k/50]\r\n";
                cm.sendOk(txt);
                cm.dispose();
            }else if(cm.getBossLog('ÿ������') == 9){
                txt += "���Ѿ������ÿ�����̵ھŻ� "+���+"\r\n";
                txt += "\t\t\t\t#e"+ë��+"ÿ�����̵�ʮ��"+ë��+"#k#n\r\n\r\n";
				txt += "�ھŻ���ͼ��\r\n";
                txt += "��ľ�� - NPC �ֿ����Ա ��˹��!\r\n";// 2080005
                txt += "#v4001084# #z4001084# [#r#c4001084##k/1]\r\n";
				txt += "#v4000175# #z4000175# [#r#c4000175##k/1]\r\n";
				txt += "#r"+����+"����ʮ�������������Ŷ��#k#n\r\n";
                cm.sendOk(txt);
                cm.dispose();
            }else{
                txt += "���Ѿ������ÿ�����̵�ʮ�� "+���+"\r\n\r\n";
				txt += "\t\t\t\t#e"+ë��+"�������"+ë��+"#k#n\r\n\r\n";
				txt += ""+��Ʒ���ۿ�+"\r\n";
				txt += "��ϲ����ɽ���������������\r\n";
				txt += "������ս���һ���������㣡\r\n";
                cm.sendOk(txt);
                cm.dispose();
			}
        }else if (status == 2) {
			if (cm.getMeso() >= 10000){//��� ���ж�����   ���˲��������ġ�
				cm.gainMeso(-10000);	//�Ӽ���ȯ
                cm.warp(104010001);
                cm.dispose();
			}else{
                cm.sendOk("��Ҳ���1��");
                cm.dispose();
			}
		} else if (selection == 1) {
			if (!cm.checkNumSpace(4, 3)) {
			cm.sendOk("����������,�ռ䲻��3��");
			cm.dispose();
			return;
		}
            if (cm.haveItem(4000002,30) && cm.haveItem(4000017,20)){
                //cm.gainPS(1);//cm.gainPS(1);  ����˼�� ��������̵�һ����ʱ������� ����ֵ+1��������޷����ظ�����һ���ˡ�ֻ���賿12��ˢ�²��У�
				cm.setBossLog('ÿ������');
                cm.gainItem(4000002, -30);
                cm.gainItem(4000017, -20);
				//cm.gainDY(100);
				//cm.gainMeso(+350000); //�Ӽ����
				cm.gainItem(4000313,5);//�ƽ��Ҷ
                cm.gainExp(cm.getLevel()*10000);
				var shi = cm.getHour();
				var fen = cm.getMin();
				var miao = cm.getSec();
				cm.worldMessage(6,"��ң���"+cm.getName()+"��ÿ�����̵�һ��-��ɡ�");
                cm.sendOk("���̵�һ����ɡ�\r\nȻ����ȥ���ִ� - NPC ������/���еڶ�����");
                cm.dispose();
            }else{
                cm.sendOk("���ռ�������Ʒ�����빻�����������ύ������ȡ�������");
                cm.dispose();
            }
        }else if (selection == 2) {
                cm.sendYesNo("���ٴ�����������Ʒ�����ͼ������ĺ�����  ��Ҫ����1����Ŷ��");
        }
    }
}
