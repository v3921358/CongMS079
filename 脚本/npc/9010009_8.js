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

				if (cm.getBossLog("��������") == 4){
                txt += "#L1##b�ռ�30������#v4000008#�����ң� [��������"+cm.getLevel()*2500+" ] [�������"+cm.getLevel()*5000+" ]#l";
                cm.sendSimple(txt);
            }else{
				if (cm.getBossLog("��������") > 4){ 
                txt += "���Ѿ���ɹ���Ȼ���˵����֣�����������һ����.!\r\n��ڶ���������ɱ����ڣ�";
				}
                cm.sendOk(txt);
                cm.dispose();
            }

        } else if (selection == 1) {
            if (cm.haveItem(4000008,30)){
                cm.setBossLog("��������");//�ܻ�CD  ����˼�� ��������̵�һ����ʱ������� ����ֵ+1��������޷����ظ�����һ���ˡ�ֻ���賿12��ˢ�²��У�
		
                cm.gainItem(4000008, -30);
                cm.gainMeso(cm.getLevel()*5000);//��ȡ����
                cm.gainExp(cm.getLevel()*2500);
				cm.gainItem(4000313,25);
				//cm.gainItem(5211047,1,3); //�󶨵�˫������3��
				//cm.gainItem(5360014,1,3); //�󶨵�˫������3��
				cm.gainDY(3000);//������þ�66666�� 
                cm.sendOk("���̵��������![��������"+cm.getLevel()*2500+" ] [�������"+cm.getLevel()*5000+" ] �ƽ��Ҷ25�š�����ȯ3000�㡢˫��������Сʱһ�š�˫�����ʿ���Сʱһ��\r\n\r\n���Ѿ���ɹ���Ȼ���˵����֣�����������һ����.");
                cm.dispose();
            }else{
                cm.sendOk("�ռ�30������#v4000008#������!");
                cm.dispose();
            }
        }
    }
}
