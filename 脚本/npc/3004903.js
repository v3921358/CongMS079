


var ca = java.util.Calendar.getInstance();
var year = ca.get(java.util.Calendar.YEAR); //������
var month = ca.get(java.util.Calendar.MONTH) + 1; //����·�
var day = ca.get(java.util.Calendar.DATE);//��ȡ��
var hour = ca.get(java.util.Calendar.HOUR_OF_DAY); //���Сʱ
var minute = ca.get(java.util.Calendar.MINUTE);//��÷���
var second = ca.get(java.util.Calendar.SECOND); //�����
var weekday = ca.get(java.util.Calendar.DAY_OF_WEEK);
var ������ͷ = "#fUI/Basic/BtHide3/mouseOver/0#";
var ���� = "#fEffect/CharacterEff/1112905/0/1#";
var ��� = "#fUI/UIWindow.img/Item/BtCoin/normal/0#";
var tz = "#fEffect/CharacterEff/1082565/4/0#";  //���ӷ�
var ����new = "#fUI/UIWindow/Quest/icon5/1#";

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
        var selStr = "\t\t   #e#v2550014#װ��ǿ��������������#v2550014##k#n\r\n";// 
		selStr += ""+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+"\r\n"
		
		selStr +="#L1#[#v2049401#]#e#rǿ��+1#k#l #L2#[#v2049401#]#e#rǿ��+2#k#l #L3#[#v2049401#]#e#rǿ��+3#k#l\r\n\r\n";


		selStr += ""+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+"\r\n"
		cm.sendSimple(selStr);
    } else if (status == 1) {
        switch (selection) {
        
		case 1:
            cm.dispose();
            cm.openNpc(3004903,"�Ҿ����");
            break;
		case 2:
            cm.dispose();
            cm.openNpc(3004903,"�Ҿ����2");
            break;
		case 3:
            cm.dispose();
            cm.openNpc(3004903,"�Ҿ����3");
            break;
        
		}
    }
}

