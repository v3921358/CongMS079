var FY0 = "��                      ��";
var FY1 = "��       - ��Ҷ -       ��";
var FY2 = "�� �ű�����  �����ƽű� ��";
var FY3 = "�� ����֧�� �� ��Ϸ���� ��";
var FY4 = "�� �ף���ӡ�  ��ͼ���� ��";
var FY5 = "�� �Ӷܷ�����  �۵�½�� ��";
var FY6 = "��       �ٶ��ƹ�       ��";
var FY7 = "�� ΨһQQ:1848350048    ��";
var FY8 = "��                      ��";

var status = 0;

var itemList1 = [

	
[4007000, 10, 1, 1],
[4007001, 10, 1, 1],
[4007002, 10, 1, 1],
[4007003, 10, 1, 1],
[4007004, 10, 1, 1],
[4007005, 10, 1, 1],
[4007006, 10, 1, 1],
[4007007, 10, 1, 1],

[4170009, 10, 1, 1],
[4032392, 10, 1, 1],
[4032391, 1, 1, 1]




];
var useNx = 400;
var sel0 = -1;
function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (status == 0 && mode == 0) {
        cm.dispose();
        return;
    }
    if (mode == 1) {
        status++;
		cm.dispose();
    } else {
        status--;
		cm.dispose();
    }

    if (status == 0) {
    	var txt = "#d\t\t\t#b��ӭ�鿴�����������������б�#n#k\r\n\r\n";
		txt += "\t#rͨ������������ \r\n";
		cm.dispose();
		
		var txt2 = "";
		for (var i = 0; i < itemList1.length;  i++){
			txt2 += "#i"+itemList1[i][0]+":#";
			cm.dispose();
		}
    	cm.sendSimple(txt + txt2);
		cm.dispose();
    }  
	cm.dispose();
}

