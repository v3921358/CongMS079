var CY0 = "�ǩ��������������������������� ����������������������������������";
var CY1 = "��       - ���� -       ��";
var CY2 = "�� �淨����  �����ƽű� ��";
var CY3 = "�� ����֧�� �� ��Ϸ���� ��";
var CY4 = "�� �ף���ӡ�  ��ͼ���� ��";
var CY5 = "�� �Ӷܷ�����  �۵�½�� ��";
var CY7 = "�� ���ο���    ���ο��� ��";
var CY8 = "�ǩ��������������������������� ����������������������������������";
var CY9 = "��   ΨһQQ:3066318387  ��";
var CY0 = "�ǩ��������������������������� ����������������������������������";
var rankRewards = [

    [41, 50, [
        [2, "���", 1000000],
    ]],
    [31, 40, [
        [2, "���", 5000000],
        [2, "����ȯ", 1],
    ]],
    [21, 30, [
        [2, "���", 10000000],
        [2, "����ȯ", 1],
    ]],
    [11, 20, [
        [2, "���", 50000000],
        [2, "����ȯ", 1],
        [2, "��ȯ", 5000],
        [3, "ʥ������", 3992025, 1000],
    ]],
    [4, 10, [
        [2, "���", 80000000],
        [2, "��ȯ", 8000],
        [3, "ʥ������", 3992025, 2000],
        [3, "�", 4011008, 1],
        [3, "��ʯ", 4011007, 1],
    ]],
    [3, 3, [
        [2, "���", 1],
        [2, "��ȯ", 1],
        [3, "����� ������", 2022573, 1],
	    [3, "��ֽ��", 4031065, 1],
        [3, "�����", 4000463, 10],
    ]],
    [2, 2, [
        [2, "���", 1],
        [2, "��ȯ", 1],
        [2, "����ȯ", 1],
        [3, "����� �ڶ���", 2022572, 1],
        [3, "��ֽ��", 4031065, 1],
        [3, "�����", 4000463, 15],
    ]],
    [1, 1, [
        [2, "���", 1],
        [2, "��ȯ", 1],
        [2, "����ȯ", 1],
        [3, "����� ��һ��", 2022571, 1],
        [3, "��ֽ��", 4031065, 1],
        [3, "�����", 4000463, 20],
    ]],
];
//-----------------------------------------------------
var status = -1; //ģ��״̬
var chr = null;
var say = "";
var rank = 0;
var rewardMsg = ""; //������ʾ
var ������ͷ = "#fUI/Basic/BtHide3/mouseOver/0#";
var _rew;
function start() {
    chr = cm["getPlayer"]();
    rank = chr["getBossLog"]("�Լ�Rank");
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
        return;
    }
    if (status == 0 && mode == 0) {
        cm.dispose();
        return;
    }
    if (mode == 1) {
        status++;
    } else {
        status--;
    }

    if (status == 0) {
         _rew = _fundReward();
        if (_rew == null) {
            cm.sendOk("��Ǹ�����������������ȡ������");
            cm.dispose();
            return;
        }

        say = _getTitle("�Լ����");

        say += "#b��ϲ�����ϴγԼ������л���˵�#r [ " + rank + " ] #b��\r\n";

        say += "�㽫������½�����\r\n";
        rewardMsg = "";
        for (var i = 0; i < _rew[2].length; i++) {
            if (_rew[2][i][0] == 1) {
                rewardMsg += _rew[2][i][1] + " x " + _rew[2][i][4];
            } else if (_rew[2][i][0] == 2) {
                rewardMsg += _rew[2][i][1] + " x " + _rew[2][i][2];
            } else if (_rew[2][i][0] == 3) {
                rewardMsg += _rew[2][i][1] + " x " + _rew[2][i][3];
            }
            if (i + 1 < _rew[2].length) {
                rewardMsg += "  #k\r\n";
            }
        }
        /*if (rank == 1) {//xunzhang
            say += "[#v1142288#]#r#t1142288##k\r\n";
        } else if (rank == 2) {
            say += "[#v1142287#]#r#t1142288##k\r\n";
        } else if (rank == 3) {
            say += "[#v1142286#]#r#t1142288##k\r\n";
        }*/
        say += rewardMsg;
        say += "\r\n";


        cm.sendYesNo(say);
    } else if (status == 1) {
        var u1 = _fundReward();
        if (u1 == null) {
            cm["sendOk"]("��Ǹ���޷���ȡ���������Ϣ��");
            cm["dispose"]();
            return;
        }
        var typeCount = 0;
        var isOk = true;
        for (var i = 0; i < _rew[2].length; i++) {
            if (_rew[2][i][0] == 3) {
                typeCount += 1;
                if (isOk) {
                    if (!cm.canHold(_rew[2][i][2], _rew[2][i][3])) {
                        isOk = false;
                    }
                }
            }

        }

        if (!isOk) {
            cm.sendOk("#r#e��Ǹ����ı�����������#bװ�������ġ�������#rÿ�����ٱ���#k" + typeCount + "#r��λ��");
            cm.dispose();
            return;
        }

        for (var i = 0; i < _rew[2].length; i++) {
            if (_rew[2][i][0] == 1) {
                chr.setBossLog(_rew[2][i][2], _rew[2][i][3], _rew[2][i][4]);
            } else if (_rew[2][i][0] == 2) {
                if (_rew[2][i][1] == "���") {
                    chr.gainMeso(_rew[2][i][2], true);
                } else if (_rew[2][i][1] == "��ȯ") {
                    chr.modifyCSPoints(1, _rew[2][i][2], true);
                } else if (_rew[2][i][1] == "Ԫ��") {
                    cm.gainzb(_rew[2][i][2]);
                } else if (_rew[2][i][1] == "����ȯ") {
                    chr.modifyCSPoints(2, _rew[2][i][2], true);
                }
            } else if (_rew[2][i][0] == 3) {
                chr.gainItem(_rew[2][i][2], _rew[2][i][3]);
            }
        }
        /*if (rank == 1) {//xunzhang 
            cm["gainItem"](1142288, 50, 50, 50, 50, 888, 888, 50, 50, 50, 50, 0, 0, 0, 0, 72);
        } else if (rank == 2) {
            cm["gainItem"](1142287, 40, 40, 40, 40, 666, 666, 40, 40, 40, 40, 0, 0, 0, 0, 72);
        } else if (rank == 3) {
            cm["gainItem"](1142286, 30, 30, 30, 30, 444, 444, 40, 40, 40, 40, 0, 0, 0, 0, 72);
        }*/
        //��������
        chr.resetBossLog("�Լ�Rank");
        //���ĵ���
        //cm.gainItem(2022670,-1);
        //������ʾ
        say = "���Լ��������ϲ" + chr.getName() + "���˳Լ����,������" + rank + ",���" + rewardMsg;
        cm.worldMessage(6, say);
        cm.dispose();
    } else {
        cm.dispose();
    }
}

var ul_cloud = "#fItem/Etc/0403/04031309/info/iconRaw#"; //
function _getTitle(t) {
    return " " + ul_cloud + ul_cloud + ul_cloud + ul_cloud + "#r#e��" + t + "��#k#n" + ul_cloud + ul_cloud + ul_cloud + ul_cloud + "\r\n\r\n";
}


function _fundReward() {
    if (rank <= 0) {
        return null;
    }
    for (var rRlaW1 = 0; rRlaW1 < rankRewards["length"]; rRlaW1++) {
        if (rank >= rankRewards[rRlaW1][0] && rank <= rankRewards[rRlaW1][1]) {
            return rankRewards[rRlaW1];
        }
    }
}