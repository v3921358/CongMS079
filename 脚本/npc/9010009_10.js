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

            if (cm.getBossLog("��������") == 6){// cm.getPS()  ����˼�� ��ȡ����ֵ�������0 �͵ó���û�п�ʼ���� �����������е�һ������!
                txt += "#L1##b�ռ�50���ǹ⾫����ǿ�#v4000059#�����ң�[��������"+cm.getLevel()*3500+" ] [�������"+cm.getLevel()*7000+" ]#l";
                cm.sendSimple(txt);
            }else{
				if (cm.getBossLog("��������") > 6){ 
                txt += "���Ѿ���ɹ���Ȼ���˵����֣�����������һ����.!\r\n��ڶ���������ɱ����ڣ�";
				}
                cm.sendOk(txt);
                cm.dispose();
            }

        } else if (selection == 1) {
            if (cm.haveItem(4000059,50)){
                cm.setBossLog("��������");//�ܻ�CD  ����˼�� ��������̵�һ����ʱ������� ����ֵ+1��������޷����ظ�����һ���ˡ�ֻ���賿12��ˢ�²��У�
		
                cm.gainItem(4000059, -50);
                cm.gainMeso(cm.getLevel()*7000);//��ȡ����
                cm.gainExp(cm.getLevel()*3500);
				cm.gainItem(4000313,35);
				cm.gainItem(4001129,3);
				cm.gainNX(1500);
                cm.sendOk("���̵��������![��������"+cm.getLevel()*3500+" ] [�������"+cm.getLevel()*7000+" ]�ƽ��Ҷ35�š�ð�ռ����3������ȯ1500��\r\n\r\n���Ѿ���ɹ���Ȼ���˵����֣�����������һ����.");
                cm.dispose();
            }else{
                cm.sendOk("�ռ�100���ǹ⾫����ǿ�#v4000059#������!");
                cm.dispose();
            }
        }
    }
}
