importPackage(Packages.client);
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
            txt = "����ÿ����������NPC��������.\r\n\r\n";

            if (cm.getBossLog("��������") == 2){// cm.getPS()  ����˼�� ��ȡ����ֵ�������0 �͵ó���û�п�ʼ���� �����������е�һ������!
                txt += "#L1##b�ռ�30����Ģ����#v4000012#�����ң�[��������"+cm.getLevel()*1500+" ] [�������"+cm.getLevel()*3000+" ]#l";
                cm.sendSimple(txt);
            }else{
				if (cm.getBossLog("��������") > 2){ 
                txt += "���Ѿ���ɹ���Ȼ���˵����֣�����������һ����.!\r\n��ڶ���������ɱ����ڣ�";
				}
                cm.sendOk(txt);
                cm.dispose();
            }

        } else if (selection == 1) {
            if (cm.haveItem(4000012,30)){
                cm.setBossLog("��������");//�ܻ�CD  ����˼�� ��������̵�һ����ʱ������� ����ֵ+1��������޷����ظ�����һ���ˡ�ֻ���賿12��ˢ�²��У�
		
                cm.gainItem(4000012, -30);
                cm.gainMeso(cm.getLevel()*3000);//��ȡ����
                cm.gainExp(cm.getLevel()*1500);
				cm.gainItem(4000313,15);
				cm.gainDY(2000);//������þ�66666�� 
                cm.sendOk("���̵��������![��������"+cm.getLevel()*1500+" ] [�������"+cm.getLevel()*3000+" ] �ƽ��Ҷ15�š�����ȯ2000��\r\n\r\n���Ѿ���ɹ���Ȼ���˵����֣�����������һ����.");
                cm.dispose();
            }else{
                cm.sendOk("�ռ�100����Ģ����#v4000012#������!");
                cm.dispose();
            }
        }
    }
}
