/* ==================
 �ű�����: ��ɫ��������ɾ����ɫ��ת���֣����ӽ�ɫ��
 �ű����ߣ�����
 ��ϵ��ʽ��12384161
 =====================
 */

var ��ɫ��ͷ = "#fUI/UIWindow/Quest/icon2/7#";
var С�� = "#fEffect/CharacterEff/1112960/3/0#";  //а��С�� ��С��

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
            var text = "\t#d#e�����кܶ����벻���Ľ�ɫ��ع��ܣ��Ͻ����԰ɣ�#k#l\r\n\r\n";
            
            text += "#L1#" +С��+ " #bɾ����ɫ#k#l   ";
            text += "#L2#" +С��+ " #b���ӽ�ɫ#k#l\r\n\r\n"
            text += "#L3#" +С��+ " #b��ɫ����#k#l   ";
			text += "#L4#" +С��+ " #b�ص�����#k#l\r\n\r\n"
            text += "\r\n\r\n"
            cm.sendSimple(text);
        } else if(status == 1){
            switch(selection){
                //cm.getPlayer().dropMessage(5, selection);
                
                case 1:
                    cm.dispose();
                    cm.openNpc(3003332, "ɾ����ɫ");
                    break;
                case 2:
                    cm.dispose();
                    cm.openNpc(3003332, "���ӽ�ɫ");
                    break;
                case 3:
                    cm.dispose();
                    cm.openNpc(3003332, "��ɫ����");
                    break;
               case 4:
                    cm.dispose();
                    cm.openNpc(3003332, "�ص�����");
                    break;
                
                default:
                    cm.sendOk("δ֪��������ϵ����Ա��");
                    cm.dispose();
                    break;
            }
        }
    }
}
