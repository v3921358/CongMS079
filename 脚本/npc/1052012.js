/* ==================
 �ű�����: ����
 ��Ȩ���𻪷�Ҷ�Ŷ�     
 ��ϵ�ۿۣ�1848350048
 =====================
 */
var ���� = "#fUI/CashShop/CSDiscount/bonus#";
var ����ֵ = "#fUI/UIWindow/QuestIcon/8/0#";
var ��� = "#fUI/UIWindow/QuestIcon/7/0#";
var ��ɫ��ͷ = "#fEffect/CharacterEff/1112908/0/1#";  
var ����� = "#fEffect/CharacterEff.img/1022223/1/0#";
var ���ڽ����� = "#fUI/UIWindow/Quest/Tab/enabled/1#";
var ë�� = "#fUI/ChatBalloon.img/pet/12/nw#";
var ��ȯ = "#fUI/CashShop/CashItem/0#";
var ��� = "#fUI/UIWindow/Quest/Tab/enabled/2#";
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

            if (cm.getBossLog('ÿ������') == 4) {
                txt = "\t\t\t\t#e"+ë��+"ÿ�����̵��廷"+ë��+"#k#n\r\n";
				txt += "�ռ�������Ʒ��\r\n";
                txt += "#v4000164# ����֮��[#r#c4000164##k/25] #v4000165# �������� [#r#c4000165##k/25] "+���ڽ�����+"\r\n";
                txt += "#L2#"+��ɫ��ͷ+"���ٴ�������ͼ(ɳ��) ��Ҫ X 2����#l\r\n\r\n";
				txt += ""+����+"\r\n";
				txt += "#v4000313#  X 5 #v4000463#  X 2\r\n";
				txt += ""+����ֵ+" X 10000 (���ŵȼ�����Exp)\r\n";
				txt += "#L1##b"+��ɫ��ͷ+"�ύ�������#l\r\n";
                cm.sendSimple(txt);
            }else{
				if (cm.getBossLog('ÿ������') < 4) {
                txt += "\t\t\t\t"+���ڽ�����+"\r\n";
				txt += "��ͼ��֪��\r\n";
                txt += "��ʿ���� - NPC �ֿ����Ա ������\r\n";
				txt += "�ӵ��Ļ�����������ɺ��������ҽӵ��廷��������Ŷ��\r\n";
                cm.sendOk(txt);
                cm.dispose();
				}else{
			    txt += "���Ѿ������ÿ�����̵��廷 "+���+"\r\n\r\n";
				txt += "\t\t\t\t#e"+ë��+"ÿ�����̵�����"+ë��+"#k#n\r\n\r\n";
				txt += "��������ͼ��\r\n";
                txt += "����֮�� - NPC �ֿ����Ա ������\r\n";
				txt += "�ռ�������Ʒ��\r\n";
				txt += "#v4000173# #z4000173# [#r#c4000173##k/50]\r\n";
                cm.sendOk(txt);
                cm.dispose();
				}
            }

        }else if (status == 2) {
			if (cm.getMeso() >= 20000){
				cm.gainMeso(-20000);
                cm.warp(230020200);
                cm.dispose();
			}else{
                cm.sendOk("2���Ҳ���");
                cm.dispose();
			}
		} else if (selection == 1) {
            if (cm.haveItem(4000164,25) && cm.haveItem(4000165,25)){
					if (!cm.checkNumSpace(0, 3)) {
			cm.sendOk("�����ռ䲻��3��");
			cm.dispose();
			return;
		}
                cm.setBossLog('ÿ������');
                cm.gainItem(4000164, -25);
				cm.gainItem(4000165, -25);
				cm.gainItem(4000463,2);//��������
				cm.gainItem(4000313,5);//�ƽ��Ҷ
                cm.gainExp(cm.getLevel()*10000);
				cm.worldMessage(6,"��ң���"+cm.getName()+"��ÿ�����̵��Ļ�-��ɡ�");
                cm.sendOk("���̵��廷��ɡ�\r\nȻ����ȥ����֮�� - NPC �ֿ����Ա ������/���е�������");
                cm.dispose();
            }else{
                cm.sendOk("���ռ�������Ʒ�����빻�����������ύ������ȡ�������");
                cm.dispose();
            }
        }else if (selection == 2) {
                cm.sendYesNo("���ٴ�����������Ʒ�����ͼ����ɳ�ǣ�  ��Ҫ����2����Ŷ��");
        }
    }
}
