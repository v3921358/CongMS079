var weapon = new Array(
//�ϳ���ƷID,ǰһ��װ��ID
	Array(1062165,1052498),
	Array(1042183,1052498),
	Array(1062166,1052499),
	Array(1042184,1052499),
	Array(1062167,1052500),
	Array(1042185,1052500),
	Array(1062168,1052501),
	Array(1042186,1052501),
	Array(1062169,1052502),
	Array(1042187,1052502)
);
//������������
var req = [
    [1122000, 1],
    [4031217, 1],
    [4251202, 5],
    [4250902, 5],
	[4250802, 5],
	[4251102, 5],
	[4251002, 5],
	[4000463, 100]
];
var rem = 1000000000;
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
        msg += "\r\n#d��Ҫ:#b ";
        msg += "\r\n";
        for (var ii = 0; ii < req.length; ii++) {
            msg += "#i" + req[ii][0] + ":##z" + req[ii][0] + "#x" + req[ii][1];
            if (ii % 2 == 0 && ii !=0) {
                msg += "\r\n";
            }
        }
		msg += "\t\t";
		msg += rem+"���";
        msg += "\r\n";
        msg += "#g----------------------------------------------\r\n";
        for (var i = 0; i < weapon.length; i++) {
            msg += "#r#L" + i + "#";
            msg += "#i" + weapon[i][1] + ":##z" + weapon[i][1] + "# #b����#r #i" + weapon[i][0] + ":##z" + weapon[i][0] + "##l\r\n";
        }
        cm.sendSimple("#d��Ҫ�һ�װ����?\r\n" + msg + "");
    } else if (status == 1) {
        sels = selection;
        if (!cm.canHold(weapon[sels][0])) {
            cm.sendNext("#r�����ռ䲻��");
            cm.dispose();
            return;
        }
        for (var i = 0; i < req.length; i++) {
            if (!cm.haveItem(req[i][0], req[i][1])) {
                cm.sendNext("#b����û��#r#i" + req[i][0] + ":##z" + req[i][0] + "#x" + req[i][1] + "");
                cm.dispose();
                return;
            }
        }
		if (!cm.haveItem(weapon[sels][1],1)) {
            cm.sendNext("#b����û��#r#i" + weapon[sels][1] + ":##z" + weapon[sels][1] + "#");
            cm.dispose();
            return;
        }
		if(cm.getMeso() < rem){
			cm.sendNext("#b����û��#r"+rem+"���");
            cm.dispose();
            return;
		}
        cm.sendYesNo("#b�Ƿ�Ҫ�һ�װ��#r #i" + weapon[sels] + ":#? \r\n");
    } else if (status == 2) {
        for (var i = 0; i < req.length; i++) {
            cm.gainItem(req[i][0], -req[i][1]);
        }
		cm.gainMeso(-rem);
		cm.gainItem(weapon[sels][1],-1);
        cm.gainItem(weapon[sels][0], 1);
        cm.sendNext("#b�Ѿ��һ������� #i" + weapon[sels] + "#");
        cm.dispose();
    } else {
        cm.sendNext("#r��������: mode : " + mode + " status : " + status);
        cm.dispose();
    }
}