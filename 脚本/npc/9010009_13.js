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
            txt = "����ÿ����������NPC����ʮ��.\r\n\r\n";

            if (cm.getBossLog("��������") == 9){// cm.getPS()  ����˼�� ��ȡ����ֵ�������0 �͵ó���û�п�ʼ���� �����������е�һ������!
                txt += "#L1##b�ռ�1������������#v4001083#�����ң�\r\n\r\n#r������: [��������"+cm.getLevel()*10000+" ] [�������"+cm.getLevel()*10000+" ]#l#k";
                cm.sendSimple(txt);

					
            }else{
				if (cm.getBossLog("��������") > 9){ 
                txt += "���Ѿ���ɹ���Ȼ���˵�ʮ�֣�����������һ����.!\r\n��ڶ���������ɱ����ڣ�";
				}
                cm.sendOk(txt);
				
                cm.dispose();
            }
		
		
        } else if (selection == 1) {
            if (cm.haveItem(4001083,1)){
                cm.setBossLog("��������");//�ܻ�CD  ����˼�� ��������̵�һ����ʱ������� ����ֵ+1��������޷����ظ�����һ���ˡ�ֻ���賿12��ˢ�²��У�
		
                cm.gainItem(4001083, -1);
                cm.gainMeso(cm.getLevel()*10000);//��ȡ����
                cm.gainExp(cm.getLevel()*10000);
				//cm.gainItem(5211047,1,3); //˫������
				//cm.gainItem(5360014,1,3); //˫������
				cm.gainItem(4000313,50);//�ƽ��Ҷ
				//cm.gainvip(+3);
				cm.gainNX(3000);
                cm.sendOk("���̵�ʮ�����![��������"+cm.getLevel()*10000+" ] [�������"+cm.getLevel()*10000+" ]���ƽ��Ҷ*50����ȯ3000�㡢˫��������Сʱһ�š�˫�����ʿ���Сʱһ��\r\n\r\n���Ѿ���ɹ���Ȼ���˵�ʮ�֣�����������һ����.");
                cm.dispose();
            }else{
                cm.sendOk("�ռ�1������������#v4001083#�����ң�");
                cm.dispose();
            }
        }
    }


}