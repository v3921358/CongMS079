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
        var selStr = ""+ ��ˮ�� +"�װ��Ļ�ӭ����ͯ��ð�յ���������"+ ��ˮ�� +""+ ���� +""+ ����1 +"\r\n";
        selStr += " ��ȯ��#r"+cm.getPlayer().getCSPoints(1)+"#d\t����ȯ��#r"+cm.getPlayer().getCSPoints(2)+"#d\t��ֵ����:#r"+cm.getmoneyb()+"#k\r\n#e#d             ����ǰʱ��"+hour+":"+minute+":"+second+"��#k\r\n";
        selStr += "   #r#L0#"+ �һ� +"��ȯ�̳�#l#k #r#L1#"+ �һ� +"��Ϸ����#l#r#L4#"+ �һ� +"˫������#l\r\n\r\n";
        selStr += "   #L3#"+ �һ� +"�ʼ�����#l#k #r#L2#"+ �һ� +"��ȡUFO/Ⱥ��/���/����#l \r\n\r\n";
        //selStr += "   #b#L10# װ������#l#k #g#L8# ��ʱ�̳�#l#k #L9# ��ȯ�һ�#l\r\n\r\n";
       // selStr += "   #L15# ���϶һ�#l#k #L11# ���Ӷһ�#l #r#L12# ��ȡ��ȯ#l\r\n\r\n";
	//selStr += "   #b#L14# ��ֵ��վ#l#k #r#L13# �ٷ���վ#l "; //#r#L2# ��������#l
		cm.sendSimple(selStr);
    } else if (status == 1) {
        switch (selection) {
        case 0:
            cm.dispose();
            cm.openNpc(9900004,6001);
            break;
		case 1:
            cm.dispose();
            cm.openNpc(1052013,0);

            //cm.openNpc(9900004,7);
            break;
        case 2:
            cm.dispose();
            cm.openNpc(9900004,6002);
            break;
        case 3:
            cm.dispose();
            cm.openNpc(9900004,6003);
	    //cm.openShop(103);
            break;
        case 4:
            cm.dispose();
            cm.openNpc(1052013,0);
            break;
        case 5:
            cm.dispose();
            cm.openNpc(9900004,6);
            break;
        case 6:
            cm.dispose();
            cm.openNpc(9900004,9);
            break;
        case 7:
            cm.dispose();
            cm.openNpc(9900004,8);
            break;
        case 8:
            cm.dispose();
           cm.openShop(6);
            break;
        case 9:
            cm.dispose();
            cm.openNpc(9310071,0);
            break;
        case 10:
            cm.dispose();
            cm.openNpc(9310060,0);
            break;
        case 11:
            cm.dispose();
            cm.openNpc(9000017,2);
            break;
		case 14:
            cm.dispose();
            cm.openWeb("http://wpa.qq.com/msgrd?v=3&uin=450411012&site=qq&menu=yes");
			break;
	    case 12:
            cm.dispose();
            cm.openNpc(9900004,20);
			 break;
		case 13:
            cm.dispose();
            cm.openWeb("http://www.shikongmxd.top/");
			 break;
		case 15:
            cm.dispose();
            cm.openNpc(2020000,0);
            break;
		case 16:
            cm.dispose();
            cm.openNpc(9310054,0);
            break;
        case 17:
            cm.dispose();
            cm.openNpc(9000036,0);
            break;
		}
    }
}
