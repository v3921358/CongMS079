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
            txt = "����ÿ����������NPC���ھ���.\r\n\r\n";	

            if (cm.getBossLog("��������") == 8){// cm.getPS()  ����˼�� ��ȡ����ֵ�������0 �͵ó���û�п�ʼ���� �����������е�һ������!
                txt += "#L1##b�ռ�20����Ƥ#v4000030#�����ң�[��������"+cm.getLevel()*4500+" ] [�������"+cm.getLevel()*9000+" ]#l";
                cm.sendSimple(txt);		
			
            }else{
				if (cm.getBossLog("��������") > 8){ 
                txt += "���Ѿ���ɹ���Ȼ���˵ھ��֣�����������һ����.!\r\n��ڶ���������ɱ����ڣ�";
				}
                cm.sendOk(txt);
                cm.dispose();
            }

        } else if (selection == 1) {
            if (cm.haveItem(4000030,20)){
                cm.setBossLog("��������");//�ܻ�CD  ����˼�� ��������̵�һ����ʱ������� ����ֵ+1��������޷����ظ�����һ���ˡ�ֻ���賿12��ˢ�²��У�
		
                cm.gainItem(4000030, -20);
                cm.gainMeso(cm.getLevel()*9000);//��ȡ����
                cm.gainExp(cm.getLevel()*4500);
				cm.gainNX(2500);
				cm.gainItem(4000313,45);
				cm.gainItem(4001226,3);
                cm.sendOk("���̵ھ������![��������"+cm.getLevel()*4500+" ] [�������"+cm.getLevel()*9000+" ]��ȯ2500�㡢�ƽ��Ҷ45�š�����֮��3��\r\n\r\n���Ѿ���ɹ���Ȼ���˵ھ��֣�����������һ����.");
                cm.dispose();
            }else{
                cm.sendOk("�ռ�20����Ƥ#v4000030#������!");
                cm.dispose();
            }
        }
    }

}