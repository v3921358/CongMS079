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

            if (cm.getBossLog("��������") == 5){// cm.getPS()  ����˼�� ��ȡ����ֵ�������0 �͵ó���û�п�ʼ���� �����������е�һ������!
                txt += "#L1##b�ռ�30���������֮β#v4000007#�����ң�[��������"+cm.getLevel()*3000+" ] [�������"+cm.getLevel()*6000+" ]#l";
                cm.sendSimple(txt);
            }else{
				if (cm.getBossLog("��������") > 5){ 
                txt += "���Ѿ���ɹ���Ȼ���˵����֣�����������һ����.!\r\n��ڶ���������ɱ����ڣ�";
				}
                cm.sendOk(txt);
                cm.dispose();
            }

        } else if (selection == 1) {
            if (cm.haveItem(4000007,30)){
                cm.setBossLog("��������");//�ܻ�CD  ����˼�� ��������̵�һ����ʱ������� ����ֵ+1��������޷����ظ�����һ���ˡ�ֻ���賿12��ˢ�²��У�
		
                cm.gainItem(4000007, -30);
                cm.gainMeso(cm.getLevel()*6000);//��ȡ����
                cm.gainExp(cm.getLevel()*3000);
				cm.gainNX(1000);
				cm.gainItem(4000313,30);
				//cm.gainDY(3000);//������þ�66666�� 
                cm.sendOk("���̵��������![��������"+cm.getLevel()*3000+" ] [�������"+cm.getLevel()*6000+" ]�ƽ��Ҷ30�š���ȯ1000��\r\n\r\n���Ѿ���ɹ���Ȼ���˵����֣�����������һ����.");
                cm.dispose();
            }else{
                cm.sendOk("�ռ�30���������֮β#v4000007#������!");
                cm.dispose();
            }
        }
    }
}
