var bl = 200; // ���ٵ��һ���Ʒ
var wpid = 3994048; // ���һ�����Ʒ��id
var wpsx = 720; // Сʱ
var mrjlid = 2050004; // ÿ�ս�����id
var mrjlbl = 100; // ÿ�ս����ı���
var ca = java.util.Calendar.getInstance();
var bosslog = '�����һ�'; // bosslog��״ֵ̬
var year = ca.get(java.util.Calendar.YEAR); //������
var month = ca.get(java.util.Calendar.MONTH) + 1; //����·�
var day = ca.get(java.util.Calendar.DATE); //��ȡ��
var hour = ca.get(java.util.Calendar.HOUR_OF_DAY); //���Сʱ
var minute = ca.get(java.util.Calendar.MINUTE); //��÷���
var second = ca.get(java.util.Calendar.SECOND); //�����
var weekday = ca.get(java.util.Calendar.DAY_OF_WEEK);//�ж�����

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
            var text = "";
            text += "������������ȡ���ġ�\r\n\r\n";
            text += "������#r20:30-20:40��\r\n\r\n";
            text += "��#d��ǰ������ʱ��: #r" + hour + " #b�� #r" + minute + " #b�֡�\r\n\r\n";
            text += "#L1#��ȡ������������:#v4001129#*1 #v4001126#*100 #v4000313#*30 \r\n\r\n";
            cm.sendSimple(text);
        }
        else if (status == 1) {
            if (selection == 1) {
              if(cm.getBossLog('���Ļ')<1 && weekday == 5 &&hour == 20 && minute >= 30 && minute <= 40){
			 cm.setBossLog('���Ļ');
			 cm.gainItem(4001129,1);
			 cm.gainItem(4001126,100);//���ױ�
			 cm.gainItem(4000313,30);//�����

            cm.sendOk("��ȡ����");
			cm.dispose();
		
			}else{
            cm.sendOk("#r��ǰ������ʱ��: #r" + hour + " #b�� #r" + minute + " #b�ֻδ����\r\n�����Ѿ���ȡ��һ��!");
			cm.dispose();
        
			}
            } else if (selection == 2) {
                if (cm.getPlayer().getBossLog("bosslog")>0) {
                    cm.sendOk("�����Ѿ���ȡ��������������!");
                    cm.dispose();
                    return ;
                }
           
                //cm.gainItem(2460005, +10);
				  //cm.gainItem(261400, +10);
				   //cm.gainItem(2531000, +10);
				 
                cm.getPlayer().setBossLog("bosslog"); //��һ�������¼
				cm.warp(910000000);
                cm.sendOk("���ս�����ȡ�ɹ�");
				cm.����(4,"���["+cm.getPlayer().getName()+"]��ɽ�����������");
                cm.dispose();
            }
            else {
                cm.dispose();
            }
        }
        else {
            cm.dispose();
        }
    }
}