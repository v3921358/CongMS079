
var FY0 = "��������������������������";
var FY1 = "��       - ��Ҷ -       ��";
var FY2 = "�� �ű�����  �����ƽű� ��";
var FY3 = "�� ����֧�� �� ��Ϸ���� ��";
var FY4 = "�� �ף���ӡ�  ��ͼ���� ��";
var FY5 = "�ǩ�����������������������";
var FY6 = "�� ΨһQQ:1848350048    ��";
var FY7 = "��������������������������";

var xmxsz = new Array(
{ ��Ʒ: 5210000, ʱ��: 3,  ���: 0,    log: "ÿ�������ȡ˫��" },
{ ��Ʒ: 5360000, ʱ��: 3,  ���: 100 , log: "ÿ�չ���˫��"},
{ ��Ʒ: 5211060, ʱ��: 2,  ���: 150 , log: "ÿ�չ�������"}

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
        msg += "��ѡ������Ҫ�������Ʒ:\r\n\r\n";
        
        for (var i = 0; i < xmxsz.length; i++) {
            msg += "#r#L" + i + "#";
            msg += "#v" + xmxsz[i].��Ʒ + "##t" + xmxsz[i].��Ʒ + "# ʱ�ޣ�" + xmxsz[i].ʱ�� + "Сʱ  ��Ҫ ��� " + xmxsz[i].��� + "#l\r\n";
        }
        cm.sendSimple("" + msg + "");
    } else if (status == 1) {
        sels = selection;
        if (cm.getBossLog(xmxsz[sels].log) >= 1) {
			cm.sendNext("#rÿ��ֻ��һ��Ŷ��");
            cm.dispose();
            return;
		}
		
		if (!cm.canHold(xmxsz[sels].��Ʒ)) {
            cm.sendNext("#r�����ռ䲻��");
            cm.dispose();
            return;
        }
        if (cm.getPlayer().getCSPoints(1) < xmxsz[sels].���) {
			cm.sendNext("����㣬�޷��һ���");
            cm.dispose();
            return;
        }
        cm.sendYesNo("#b�Ƿ�Ҫ�һ�#r #v" + xmxsz[sels].��Ʒ + "##t" + xmxsz[sels].��Ʒ + "#? \r\n");
    } else if (status == 2) {
		cm.setBossLog(xmxsz[sels].log);
        cm.getPlayer().modifyCSPoints(1, -xmxsz[sels].���, true);
        cm.gainItem(xmxsz[sels].��Ʒ,1,xmxsz[sels].ʱ��);
        cm.sendNext("#b�ɹ��һ���Ʒ");
        cm.dispose();
    } else {
        cm.sendNext("#r��������: mode : " + mode + " status : " + status);
        cm.dispose();
    }
}