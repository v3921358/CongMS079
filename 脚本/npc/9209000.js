/* ==================
 �ű�����: ����
 ��Ȩ���𻪷�Ҷ�Ŷ�     
 ��ϵ�ۿۣ�1848350048
 =====================
 */
var ���� = "#fUI/CashShop/CSDiscount/bonus#";
var ����ֵ = "#fUI/UIWindow/QuestIcon/8/0#";
var ��� = "#fUI/UIWindow/QuestIcon/7/0#";
var ��ɫ��ͷ = "#fEffect/CharacterEff/1112908/0/1#";  //�ʹ�3
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
            if (cm.getBossLog('ÿ������') == 1) {
                txt = "\t\t\t\t#e"+ë��+"ÿ�����̵ڶ���"+ë��+"#k#n\r\n";
				txt += "�ռ�������Ʒ��\r\n";
                txt += "#v4000013# #z4000013# [#r#c4000013##k/50] "+���ڽ�����+"\r\n";
				txt += "#L2#"+��ɫ��ͷ+"���ٴ�������ͼ(��ɭ��I) ��Ҫ X 1����#l\r\n\r\n";
				txt += ""+����+"\r\n";
				txt += "#v4000313#  X 5\r\n";
				txt += ""+����ֵ+" X 10000 (���ŵȼ�����Exp)\r\n";
				//txt += ""+��ȯ+" ���þ� X 200\r\n";
				txt += "#L1##b"+��ɫ��ͷ+"�ύ�������#l\r\n";
                cm.sendSimple(txt);
            }else{
				
				if (cm.getBossLog('ÿ������') < 1) {
				txt += "\t\t\t\t"+���ڽ�����+"\r\n";
                txt += "�κε�ͼ��\r\n";
				txt += "NPC - ���� �ͻ�Ա \r\n";
				txt += "�ӵ�һ������������ɺ��������ҽӵڶ�����������Ŷ��\r\n";
                cm.sendOk(txt);
                cm.dispose();
				}else{
				txt += "���Ѿ������ÿ�����̵ڶ��� "+���+"\r\n";
			    txt += "\t\t\t\t#e"+ë��+"ÿ�����̵�����"+ë��+"#k#n\r\n\r\n";
				txt += "��������ͼ��\r\n";
                txt += "ħ������ - NPC ���������� �׵�\r\n";
				txt += "�ռ�������Ʒ��\r\n";
				txt += "#v4000190# #z4000190#[#r#c4000190##k/25] #v4000191# #z4000191#[#r#c4000191##k/25]\r\n";
                cm.sendOk(txt);
                cm.dispose();
				}
				
				
            }

        }else if (status == 2) {
			if (cm.getMeso() >= 10000){
				cm.gainMeso(-10000);
                cm.warp(105040100);
                cm.dispose();
			}else{
                cm.sendOk("��Ҳ���1��");
                cm.dispose();
			}
		}  else if (selection == 1) {
            if (cm.haveItem(4000013,50)){
				if (!cm.checkNumSpace(0, 3)) {
			cm.sendOk("�����ռ䲻��3��");
			cm.dispose();
			return;
		}
				cm.setBossLog('ÿ������');
                cm.gainItem(4000013, -50);
				cm.gainItem(4000313,5);//�ƽ��Ҷ
                cm.gainExp(cm.getLevel()*10000);
				cm.worldMessage(6,"��ң���"+cm.getName()+"��ÿ�����̵ڶ���-��ɡ�");
                cm.sendOk("���̵ڶ�����ɡ�\r\nȻ����ȥħ������ - NPC ���������� �׵�/���е�������");
                cm.dispose();
            }else{
                cm.sendOk("���ռ�������Ʒ�����빻�����������ύ������ȡ�������");
                cm.dispose();
            }
        }else if (selection == 2) {
                cm.sendYesNo("���ٴ�����������Ʒ�����ͼ������ɭ��I��  ��Ҫ����1����Ŷ��");
        }
    }
}
