var itemList = new Array(
//�һ�����Ʒid �һ������� ��Ҫ��ƷID ��Ҫ��Ʒ����
	Array(1302212,1,4001126,500),//�Ͻ��Ҷ��
	Array(1312114,1,4001126,500),//�Ͻ��Ҷ��
	Array(1402145,1,4001126,500),//�Ͻ��Ҷ˫�ֽ�
	Array(1412102,1,4001126,500),//�Ͻ��Ҷ˫��ս��
	Array(1432135,1,4001126,500),//�Ͻ��Ҷ֮ǹ
	Array(1442173,1,4001126,500),//�Ͻ��Ҷì
	Array(1332186,1,4001126,500),//�Ͻ��Ҷ������
	Array(1472177,1,4001126,500),//�Ͻ��Ҷȭ��
	Array(1372131,1,4001126,500),//�Ͻ��Ҷ��������
	Array(1382160,1,4001126,500),//�Ͻ��Ҷ��������
	Array(1452165,1,4001126,500),//�Ͻ��Ҷ��
	Array(1462156,1,4001126,500),//�Ͻ��Ҷ��
	Array(1482138,1,4001126,500),//�Ͻ��Ҷ��ȭ
	Array(1492138,1,4001126,500) //�Ͻ��Ҷ��ܿ�
	
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
            msg += "ʹ�� #i" + itemList[i][2] + ":# �� " + itemList[i][3] +" �һ� #z" + itemList[i][0] + ":# �� " + itemList[i][1] + "#l\r\n";
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