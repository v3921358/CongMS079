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
            txt = "����ÿ����������NPC����һ��.\r\n\r\n";

            if (cm.getBossLog("��������") == 0){// cm.getPS()  ����˼�� ��ȡ����ֵ�������0 �͵ó���û�п�ʼ���� �����������е�һ������!
                txt += "#L1##b�ռ�30������ţ��#v4000016#�����ң�[��������"+cm.getLevel()*500+" ] [�������"+cm.getLevel()*1000+" ]  #l";
                cm.sendSimple(txt);
            }else{
				if (cm.getBossLog("��������") > 0){ 
                txt += "���Ѿ���ɹ��˵�һ�֣�������һ��.!\r\n��ڶ���������ɵ�һ�֣�";
				}
                cm.sendOk(txt);
                cm.dispose();
            }

        } else if (selection == 1) {
            if (cm.haveItem(4000016,30)){
                //cm.gainPS(1);//cm.gainPS(1);  ����˼�� ��������̵�һ����ʱ������� ����ֵ+1��������޷����ظ�����һ���ˡ�ֻ���賿12��ˢ�²��У�
				cm.setBossLog("��������");//�ܻ�CD
                cm.gainItem(4000016, -30);
				cm.gainItem(4000313,5);//�ƽ��Ҷ
                cm.gainMeso(cm.getLevel()*1000);//��ȡ����
                cm.gainExp(cm.getLevel()*500);
				cm.gainDY(1000);//������þ�66666�� 
                cm.sendOk("���̵�һ�����![��������"+cm.getLevel()*500+" ] [�������"+cm.getLevel()*1000+" ]���ƽ��Ҷ*5������ȯ1000��\r\n\r\nȻ����ȥ������һ��.");
                cm.dispose();
            }else{
                cm.sendOk("�ռ�30������ţ��#v4000016#�����ң�");
                cm.dispose();
            }
        }
    }
}
