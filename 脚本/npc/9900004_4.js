/* ==================
 �ű�����: ���߽���	    
 �ű����ߣ���Ҷ   
 ��ϵ��ʽ��1848350048
 =====================
 */
var ca = java.util.Calendar.getInstance();
var year = ca.get(java.util.Calendar.YEAR); //������
var month = ca.get(java.util.Calendar.MONTH) + 1; //����·�
var day = ca.get(java.util.Calendar.DATE);//��ȡ��
var hour = ca.get(java.util.Calendar.HOUR_OF_DAY); //���Сʱ
var minute = ca.get(java.util.Calendar.MINUTE);//��÷���
var second = ca.get(java.util.Calendar.SECOND); //�����
var weekday = ca.get(java.util.Calendar.DAY_OF_WEEK);

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
        var 
		selStr = "\t\t"+�ʺ�+"  #e#d �� �� �� �� #k#n  #r  "+�ʺ�+"#b#k#n\r\r\n"+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+"\r\n\r\n";
        //selStr += "        ��ȯ��#r"+cm.getPlayer().getCSPoints(1)+"#d\t����ȯ��#r"+cm.getPlayer().getCSPoints(2)+"#d\t��ֵ����:#r"+cm.getmoneyb()+"#k\r\n#e#d             ����ǰʱ��"+hour+":"+minute+":"+second+"#k��\r\n";
        //selStr += "   #L78# �ܿ�����#l #r#L2# ÿ��ǩ��#l\r\n\r\n";
        //selStr += "   #L4# ÿ�ս���#l#k #r#L6# ��Ϸ����#l #r#L7# ���ֳ齱#l\r\n\r\n";
        selStr += "#e#r#L3# "+�����+"ÿ������#l     #r#L77# "+�����+"��������#l\r\n\r\n";// #L1511# "+�����+"��������#l #L1521# "+�����+"ÿ������#l\r\n\r\n";//#L1# "+С��+"ÿ��ǩ��#l#L15# ����ǩ��#l 
		// #L76# "+�����+"��������#l #L7##b "+�����+"�������#l \r\n\r\n";
		//selStr += "#e#b#L1555# "+�����+"�ȼ�����#l\r\n\r\n";
		//selStr += "#e#b#L1511# "+�����+"��������#l\r\n\r\n";
		//#L15# "+�����+"����ǩ��#l
		//selStr += "   #r#L78# 30����#l #L79# 70����#l #r#L80# 120����#l\r\n\r\n";
		selStr += "  \t\t";
		cm.sendSimple(selStr);
    } else if (status == 1) {
        switch (selection) {
        case 0:
		    cm.dispose();
            cm.openNpc(9900004,10);
            //cm.dispose();
            break;
		case 1:
            cm.dispose();
            cm.openNpc(9900004,6004);
            break;
        case 2:
            cm.dispose();
            cm.openNpc(9900004,25);
            break;
        case 3:
            cm.dispose();
            cm.openNpc(9270048,0);
	    
            break;
        case 4:
            cm.dispose();
            cm.openNpc(9900004,4);
            break;
			        case 1521:
            cm.dispose();
            cm.openNpc(9900004,1521);
            break;
        case 5:
            cm.dispose();
            cm.openNpc(9900004,6);
            break;
        case 6:
            cm.dispose();
            cm.openNpc(9900004,7);
            break;
        case 7:
            cm.dispose();
            cm.openNpc(9900004,6005);
            break;
			        case 151:
            cm.dispose();
            cm.openNpc(9300000,3);
            break;
		    case 1511:
            cm.dispose();
            cm.openNpc(9300000,1511);
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
            cm.openNpc(9900004,8000);
            break;
		case 76:
            cm.dispose();
            cm.openNpc(9310072,99);
            break;
        case 77:
            cm.dispose();
            cm.openNpc(9010009);
            break;
			case 7787:
            cm.dispose();
            cm.openNpc(9900004,"��ϵͳ");
            break;
	    case 78:
            cm.dispose();
            cm.openNpc(9900004,9999);
            break;
	    case 79:
            cm.dispose();
            cm.openNpc(9900004,9998);
            break;
	    case 80:
            cm.dispose();
            cm.openNpc(9900004,9997);
            break;
			case 1555:
		    cm.dispose();
            cm.openNpc(9900004,"�ȼ�����");
            //cm.dispose();
            break;
		}
    }
}
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
var �ʺ� ="#fEffect/ItemEff/1071085/effect/walk1/2#";
var ����è ="#fUI/ChatBalloon/37/n#";
var è�� =  "#fUI/ChatBalloon/37/ne#";
var è�� =  "#fUI/ChatBalloon/37/nw#";
var �� =    "#fUI/ChatBalloon/37/e#";
var �� =    "#fUI/ChatBalloon/37/w#";
var ����è ="#fUI/ChatBalloon/37/s#";
var è���� ="#fUI/ChatBalloon/37/se#";
var è���� ="#fUI/ChatBalloon/37/sw#";