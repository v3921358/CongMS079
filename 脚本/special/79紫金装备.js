var ���ͼ�� = "#fUI/UIWindow.img/QuestIcon/7/0#";
var weapon = new Array(

	Array(1003529,1003454),//ñ��+
	Array(1052457,1052202),//�·�
	Array(1072660,1072533),//Ь��
	Array(1082430,1082440),//����+
	Array(1102394,1102071),//����+
	//Array(1132151,1132151),//����
	Array(1302212,1302064),//�Ͻ�㵥�ֽ���+
	Array(1312114,1312056),//�Ͻ�㵥�ָ�
	Array(1402145,1402039),//�Ͻ��Ҷ˫�ֽ�+
	Array(1412102,1412027),//�Ͻ��Ҷ˫��ս��+
	Array(1432135,1432040),//�Ͻ��Ҷ֮ǹ+
	Array(1442173,1442024),//�Ͻ��Ҷì+
	Array(1332186,1332055),//�Ͻ��Ҷ������+
	Array(1472177,1472055),//�Ͻ��Ҷȭ��+
	Array(1372131,1372034),//�Ͻ��Ҷ��������+
	Array(1382160,1382039),//�Ͻ��Ҷ��������+
	Array(1452165,1452045),//�Ͻ��Ҷ��+
	Array(1462156,1462040),//�Ͻ��Ҷ��+
	Array(1482138,1482022),//�Ͻ��Ҷȭצ+
	Array(1492138,1492095)//�Ͻ��Ҷ��ܿ���+
	
	

	
	
	
);
//������������
var req = [
    [4310029, 1],//ʮ�ֱ�
    [4310034, 1],//�����
    [4001126, 1000],//��Ҷ
	[4000313, 200],//�ƽ��Ҷ
	[4000463, 10]
];
var rem = 50000000;
var gailv = 100;//����ٷ�֮����Ҫ����ٷֺ�ֻҪ���������������!!
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
		
		msg += " "+���ͼ��+":#r5ǧ��#k";
        msg += "\r\n";
        msg += "#g----------------------------------------------\r\n";
        for (var i = 0; i < weapon.length; i++) {
            msg += "#r#L" + i + "#";
            msg += "#i" + weapon[i][1] + ":##z" + weapon[i][1] + "# #b����#r #i" + weapon[i][0] + ":##z" + weapon[i][0] + "##l\r\n";
        }
        cm.sendSimple("#d��Ҫ�ϳ�װ����? \t\tĿǰ�ϳɸ���Ϊ: #r#e"+gailv+"%#d#n\r\n��ܰ��ʾ���ϳ�ʧ�ܲ��ϲ���������ѡ��" + msg + "");
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
		s1 = Math.floor(Math.random() * (100 - 1) + 1);
		if(s1 <= gailv){
			for (var i = 0; i < req.length; i++) {
				cm.gainItem(req[i][0], -req[i][1]);
			}
			cm.gainMeso(-rem);
			cm.gainItem(weapon[sels][1],-1);
			cm.gainItem(weapon[sels][0], 1);
			Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "���ϳ����ġ�" + " : " + "[" + cm.getChar().getName() + "]�ɹ��ϳ����Ͻ�װ������")); 
			cm.sendNext("#b�Ѿ��һ������� #i" + weapon[sels] + "#");
			cm.dispose();
		} else {
			for (var i = 0; i < req.length; i++) {
				cm.gainItem(req[i][0], -req[i][1]);
			}
			cm.gainMeso(-rem);
			cm.sendNext("#b�ϳ�ʧ��,��Ͷ��Ĳ�����ʧ��~!");
			cm.dispose();
		}
    } else {
        //cm.sendNext("#r��������: mode : " + mode + " status : " + status);
        cm.dispose();
    }
}