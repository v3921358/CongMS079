 /*  �ű�����

  QQ:346452946 */



var ca = java.util.Calendar.getInstance();
var year = ca.get(java.util.Calendar.YEAR); //������
var month = ca.get(java.util.Calendar.MONTH) + 1; //����·�
var day = ca.get(java.util.Calendar.DATE);//��ȡ��
var hour = ca.get(java.util.Calendar.HOUR_OF_DAY); //���Сʱ
var minute = ca.get(java.util.Calendar.MINUTE);//��÷���
var second = ca.get(java.util.Calendar.SECOND); //�����
var weekday = ca.get(java.util.Calendar.DAY_OF_WEEK);
var ������ͷ = "#fUI/Basic/BtHide3/mouseOver/0#";
var ���� = "#fEffect/CharacterEff/1022223/4/0#";
var ��ۺ찮�� = "#fItem/Etc/0427/04270001/Icon8/4#";  //
var С�ۺ찮�� = "#fItem/Etc/0427/04270001/Icon8/5#";  //
var С���� = "#fItem/Etc/0427/04270001/Icon9/0#";  //
var ����� = "#fItem/Etc/0427/04270001/Icon9/1#";  //
var Сˮ�� = "#fItem/Etc/0427/04270001/Icon10/5#";  //
var ��ˮ�� = "#fItem/Etc/0427/04270001/Icon10/4#";  //
var tz = "#fEffect/CharacterEff/1082565/4/0#";  //������
var tz1 = "#fEffect/CharacterEff/1082565/0/0#";  //������
var tz2 = "#fEffect/CharacterEff/1082565/2/0#";  //������
var а��С�� = "#fEffect/CharacterEff/1112960/3/0#";  //а��С�� ��С��
var а��С��2 = "#fEffect/CharacterEff/1112960/3/1#";  //а��С�� ����
var ���� ="#fEffect/SetEff/208/effect/walk2/4#";
var ����1 ="#fEffect/SetEff/208/effect/walk2/3#";
var С�� ="#fMap/MapHelper/weather/birthday/2#";
var �һ� ="#fMap/MapHelper/weather/rose/4#";
var ���Ҷ ="#fMap/MapHelper/weather/maple/2#";
var ���Ҷ ="#fMap/MapHelper/weather/maple/1#";
var ����Ҷ ="#fMap/MapHelper/weather/maple/3#";
var С�̻� ="#fMap/MapHelper/weather/squib/squib4/1#";
var ���� ="#fMap/MapHelper/weather/witch/3#";
//var tz = "#fEffect/CharacterEff/1082565/4/0#";  //���ӷ�

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (status == 0 && mode == 0) {
        cm.dispose();
        return;
    }
    if (mode == 1) {
        status++;
    } else {
        status--;
    }
    if (cm.getMapId() == 180000001) {
            cm.sendOk("���ź�������ΪΥ���û����򱻽�ֹ��Ϸ���������������ϵ����Ա.");
            cm.dispose();
        }  
    else if (status == 0) {
        //var selStr = " ������������#e#rĿǰ���¸���2016 12 21#n#k������������\r\t   �װ���[#b#e#h ##n#k],��ӭ������������\r\n";
	   // var selStr = ""+����+""+����+""+����+""+����+""+����+""+����+""+����+""+����+""+����+""+����+""+����+""+����+""+����+""+����+""+����+""+����+"#k\r\n";
		selStr = "               #d ������ͻ���ƹ�����npc \r\n";
                selStr += "#d   ��ǰ��׷���ƹ��˺���#e#r" + cm.����ƹ�() + "��#k#n #k#n #l\r\n\r\n";
		selStr += "#d#L1#��Ҫ1��#v4001129# ͻ�ƶ�1���ƹ��˺� #l\r\n\r\n";
		selStr += "#d#L2#��Ҫ1��#v4001129# ͻ�ƶ�10���ƹ��˺� #l\r\n\r\n";
		//selStr += "#d#L2#��Ҫ500��#v4310028# �ϳ�1W�ƹ�ʯͷ #l\r\n";

		cm.sendSimple(selStr);
    } else if (status == 1) {
        switch (selection) {
        case 1:
            if (cm.haveItem(4001129,1)){
				//cm.gainItem(4001254,-1);//10W
				cm.gainItem(4001129,-1);//1W
				cm.����ƹ�();
				cm.����ƹ�(1);
				cm.sendOk("��ϲͻ�Ƴɹ�");
                 cm.sendYesNo("���ƹ�ϵͳ��["+cm.getName()+"]������1���ƹ��ȼ�!");
				cm.dispose();
			}else {
				cm.sendOk("��û��ʮ�ƹ�ʯ���Ҳ��ܰ����ƹ���");
				cm.dispose();
				break;
			}
	case 2:
            if (cm.haveItem(4001254,1)){
				cm.gainItem(4001254,-1);//10W
				//cm.gainItem(4001129,1);//1W
				cm.����ƹ�();
				cm.����ƹ�(10);
				cm.sendOk("��ϲͻ�Ƴɹ�");
                 cm.sendYesNo("���ƹ�ϵͳ��["+cm.getName()+"]������1���ƹ��ȼ�!");
				cm.dispose();
			}else {
				cm.sendOk("��û��ʮ�ƹ�ʯ���Ҳ��ܰ����ƹ���");
				cm.dispose();
				break;
		    }
		}
    }
}
