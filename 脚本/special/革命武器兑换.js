var itemList = new Array(
//�һ�����Ʒid �һ������� ��Ҫ��ƷID ��Ҫ��Ʒ����
	Array(1302289,1,4001126,500),//������
	Array(1312165,1,4001126,500),//����ս��
	Array(1402210,1,4001126,500),//����˫�ֽ�
	Array(1412147,1,4001126,500),//����˫��ս��
	Array(1432178,1,4001126,500),//����֮ì
	Array(1442234,1,4001126,500),//������ǹ
	Array(1332238,1,4001126,500),//�����и���
	Array(1472226,1,4001126,500),//����ȭ��
	Array(1372188,1,4001126,500),//��������
	Array(1382101,1,4001126,500),//��������
	Array(1452216,1,4001126,500),//������
	Array(1462093,1,4001126,500),//������
	Array(1482179,1,4001126,500),//������ȭ
	Array(1492190,1,4001126,500) //������ܿ�
	
);
var sels;
var status = -1;

function start() {
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else if (mode == 0) {
        status--;
    } else {
        cm.dispose();
        return;
    }
    if (status == 0) {
        var msg = "";
        msg += "��ѡ����Ҫ�һ�����Ʒ:\r\n\r\n";
        msg += "#g------------------------------------------------------\r\n";
        for (var i = 0; i < itemList.length; i++) {
            msg += "#r#L" + i + "#";
            msg += "ʹ��#i" + itemList[i][2] + ":# �� " + itemList[i][3] + " �һ� #z" + itemList[i][0] + ":# �� " + itemList[i][1] + "#l\r\n";
        }
        cm.sendSimple("" + msg + "");
    } else if (status == 1) {
        sels = selection;
        if (!cm.canHold(itemList[sels][0])) {
            cm.sendNext("#r�����ռ䲻��");
            cm.dispose();
            return;
        }
        if (!cm.haveItem(itemList[sels][2], itemList[sels][3])) {
			cm.sendNext("#b����û��#r#i" + itemList[sels][2] + "##t" + itemList[sels][2] + "#x" + itemList[sels][3] + "");
            cm.dispose();
            return;
        }
        cm.sendYesNo("#b�Ƿ�Ҫ�һ�#r #i" + itemList[sels][0] + "# �� " + itemList[sels][1] + "? \r\n");
    } else if (status == 2) {
        cm.gainItem(itemList[sels][2], -itemList[sels][3]);
        cm.gainItem(itemList[sels][0], itemList[sels][1]);
        cm.sendNext("#b�Ѿ��һ��� #i" + itemList[sels][0] + "# �� "+itemList[sels][1]+"");
        cm.dispose();
    } else {
        //cm.sendNext("#r��������: mode : " + mode + " status : " + status);
        cm.dispose();
    }
}