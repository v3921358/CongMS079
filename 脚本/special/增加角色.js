/* ==================
 �ű�����:  ����	    
 �ű���Ȩ����Ϸ����
 ��ϵ�ۿۣ�12384161
 =====================
 */

var inputName;
var needMoneyb = 20;

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
            //if(!cm.haveItem(2350001, 1)){
            /*if(cm.getmoneyb() < needMoneyb){
                cm.sendOk("���˻���û��" + needMoneyb + "Ԫ������ȷ�Ϻ�������");
                cm.dispose();
                return;
            }else{*/
                 //cm.sendYesNo("#b��ȷ���Ƿ�Ҫʹ��" + needMoneyb + "Ԫ��Ϊ�����˻�������һ����ɫλ?");
				 cm.sendYesNo("#b��ȷ���Ƿ�ҪΪ�����˻�������һ����ɫλ?");
            //}
        } else if(status == 1){
            var ret = cm.addRoleNumber();
            //cm.getPlayer().dropMessage(5, ret);
            if(ret == 0){
                cm.sendOk("#b����ǰ�˺��Ѿ���չ����ǰ���������õ�����ɫ������");
            }else if(ret == -1){
                cm.sendOk("#r��ǰϵͳ��������ϵ����Ա��\r\n");
            }else if(ret > 3){
                //cm.gainItem(2350001, -1);
                //cm.setmoneyb(-needMoneyb);
                //cm.����(2, "��ҡ�" + cm.getPlayer().getName() + "��ʹ��" +needMoneyb+ "Ԫ���ɹ�������һ����ɫλ��");
                cm.sendOk("#b�ɹ�����һ����ɫλ������ǰ�ܹ��ɴ�����" + ret + "������ɫ��");
            }
            cm.dispose();
        }
    }
}
