
var CY0 = "������������������������������������������������������������������";
var CY1 = "��       - ���� -       ��";
var CY2 = "�� �ű�����  �����ƽű� ��";
var CY3 = "�� ����֧�� �� ��Ϸ���� ��";
var CY4 = "�� �ף���ӡ�  ��ͼ���� ��";
var CY5 = "�� �Ӷܷ�����  �۵�½�� ��";
var CY6 = "�� ���ο�����  ���ο��� ��";
var CY7 = "�ǩ���������������������������������������������������������������";
var CY8 = "��   ΨһQQ:12384161    ��";
var CY9 = "������������������������������������������������������������������";
var ��Ҷ = "#fUI/ITC.img/Base/Tab/Enable/0#";
var ѡ���� = "#fUI/UIWindow/Quest/select#";
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
			
            text = "\t\t\t\t#e#r"+ ��Ҷ +" ������� "+ ��Ҷ +"#k#n\r\n\r\n"
			text += "���,���Ǹ���������,���������ȡÿ�ո���,��ȡVIP����!\r\n"//3
            text += "#L1##b#v1142609#��ȡÿ�����VIP��\r\n"
			text += "#L2##b#v1142617#��ȡÿ�����VIP��\r\n"
			text += "#L3##b#v1142623#��ȡÿ�����VIP��#l\r\n\r\n"
            text += "#r"+ѡ����+"�һ�VIP���¢٢ڢ�#k#n#l\r\n"
            text += "#L6##b#v4000423#����һ�VIP��#v1142609#   ���ۼ�����������ѻ�ȡ��\r\n"
			text += "#L4##b#v4000425# ����һ�VIP��#v1142617#   ���ۼ�����������ѻ�ȡ��\r\n"
            text += "#L5##b#v4000424#����һ�VIP��#v1142623#   ���ۼ�����������ѻ�ȡ��\r\n"

            cm.sendSimple(text);
        } else if (selection == 1) {
			cm.dispose();
		cm.openNpc(9900004, 777);
        }

		else if (selection == 2) {
			cm.dispose();
		cm.openNpc(9900004, 888);
		}
		else if (selection == 9) {
			cm.dispose();
		cm.openNpc(9900004, "��Ԫ��");
		}
		else if (selection == 10) {
			cm.dispose();
		cm.openNpc(9900004, "����Ԫ��");
		}

		else if (selection == 3) {
			cm.dispose();
		cm.openNpc(9900004, 9999);
        }

else if (selection == 6) {
	cm.dispose();
		cm.openNpc(9900004, 1238);
        }  
else if (selection == 4) {
	cm.dispose();
		cm.openNpc(9900004, 1236);
        }  
else if (selection == 5) {
	cm.dispose();
		cm.openNpc(9900004, 1237);
        } 
		else if (selection == 7) {
	cm.dispose();
		cm.openNpc(9900004, "��ֵ���4");
        } 
		else if (selection == 8) {
	cm.dispose();
		cm.openNpc(9900004, "��ֵ���5");
        } 
		
		 
    }
}


