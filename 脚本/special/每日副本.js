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
		selStr = "  \t\t"+�ʺ�+"  #e#d ÿ �� �� �� #k#n  #r  "+�ʺ�+"#b#k#n\r\r\n"+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+"\r\n\r\n";
        


        selStr += "#L1# "+С��+"��������#l    #L2# "+С��+"���м���#l    #L3# "+С��+"���߼���#l\r\n\r\n";//#r#L2# �м�����#l #L3# �񼶸���#l
		


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
            cm.openNpc(3004974,"��������");
            break;
        case 2:
            cm.dispose();
            cm.openNpc(3004974,"�м�����");
            break;
		case 3:
            cm.dispose();
            cm.openNpc(3004974,"�߸�һ����");
            break;
       
	    
		}
    }
}
var ���� ="#fEffect/SetEff/208/effect/walk2/4#";
var ����1 ="#fEffect/SetEff/208/effect/walk2/3#";
var С�� ="#fMap/MapHelper/weather/birthday/2#";
var �һ� ="#fMap/MapHelper/weather/rose/4#";
var ���Ҷ ="#fMap/MapHelper/weather/maple/2#";
var ���Ҷ ="#fMap/MapHelper/weather/maple/1#";
var �ʺ� ="#fEffect/ItemEff/1071085/effect/walk1/2#";
var ����è ="#fUI/ChatBalloon/37/n#";
var è�� =  "#fUI/ChatBalloon/37/ne#";
var è�� =  "#fUI/ChatBalloon/37/nw#";
var �� =    "#fUI/ChatBalloon/37/e#";
var �� =    "#fUI/ChatBalloon/37/w#";
var ����è ="#fUI/ChatBalloon/37/s#";
var è���� ="#fUI/ChatBalloon/37/se#";
var è���� ="#fUI/ChatBalloon/37/sw#";