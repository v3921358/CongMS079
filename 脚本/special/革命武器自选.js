status = -1;
var itemList = Array(
1302289,//������
1312165,//����ս��
1402210,//����˫�ֽ�
1412147,//����˫��ս��
1432178,//����֮ì
1442234,//����ǹ

1332238,//�����и���
1472226,//����ȭ��

1372188,//��������
1382121,//��������

1452216,//������
1462093,//������

1482179,//������ȭ
1492190//������ܿ�

);

function start() {
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
       if (mode == 0 && status == 0) {
			im.dispose();
			return;
		}
        status--;
    }
    if (status == 0) {
        var text = "";
		for(var i=0; i<itemList.length; i++) {
			text+="#L"+i+"##v"+itemList[i]+"##z"+itemList[i]+"##l\r\n";
		}
		cm.sendSimple("��ѡ������Ҫ��������\r\n#r"+text);
    } else if(status == 1) {
		var itemid = itemList[selection];
		var itemnum = Math.floor(Math.random()*1+1);
		//var item = im.gainGachaponItem(itemid, itemnum, "����������ѡ", 3);
		//cm.gainItem(2430026, -1);
		cm.sendOk("��ϲ���������"+itemnum+"��#b#z"+itemid+"#");
		cm.safeDispose();
	}
}
