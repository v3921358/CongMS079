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
            txt = "����ÿ����������NPC���ڶ���.\r\n\r\n";

            if (cm.getBossLog("��������") == 1){// cm.getPS()  ����˼�� ��ȡ����ֵ�������0 �͵ó���û�п�ʼ���� �����������е�һ������!
                txt += "#L1##b�ռ�30����ˮ����#v4000010#�����ң�[��������"+cm.getLevel()*1000+" ] [�������"+cm.getLevel()*2000+" ]#l";
                cm.sendSimple(txt);
            }else{
				if (cm.getBossLog("��������") > 1){ 
                txt += "���Ѿ���ɹ���Ȼ���˵ڶ��֣�����������һ����.!\r\n��ڶ���������ɱ����ڣ�";
				}
                cm.sendOk(txt);
                cm.dispose();
            }

        } else if (selection == 1) {
            if (cm.haveItem(4000010,30)){
				cm.setBossLog("��������");//�ܻ�CD
                cm.gainItem(4000010, -30);
                cm.gainMeso(cm.getLevel()*2000);//��ȡ����
                cm.gainExp(cm.getLevel()*1000);
				cm.gainItem(4000313,10);
				cm.gainDY(1500);//������þ�66666�� 
                cm.sendOk("���̵ڶ������![��������"+cm.getLevel()*1000+" ] [�������"+cm.getLevel()*2000+" ]�ƽ��Ҷ10�š�����ȯ1500��\r\n\r\n���Ѿ���ɹ���Ȼ���˵ڶ��֣�����������һ����.");
                cm.dispose();
            }else{
                cm.sendOk("�ռ�30����ˮ����#v4000010#�����ң�");
                cm.dispose();
            }
        }
    }
}
