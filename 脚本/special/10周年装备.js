var weapon = [

//10�����ɫ����
	1003864, 
	1052613,  
	1102563,
	1012377,
	//1122253,//10�����ɫ��׹
	1132229,
	
	


//10�����ɫ����

	1302278,
	1312156,
	1332228,
	1372180,
	1382212,
	1402200,
	1412138,
	1432170,
	1442226,
	1452208,
	1462196,
	1472217,
	1482171,
	1492182

];
var req = [
    //[4002000 , 20],//��ɫ��Ʊ
    //[4031456 , 30],//��Ҷ��
    [4000016, 200],//��ɫ��ţ��
    //[4000487, 2],//��Ӱ��
    [4001126, 300],//��Ҷ
    [4000313, 50]//�ƽ��Ҷ
	//[4000040, 1],//Ģ����ѿ��
	//[4001002, 1],//С˵��
	//[4310030, 20],//�˶����
	//[4031546, 1],//С���
	
    //[4310027, 5],
];
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
        msg += "\r\n\r\n";
        for (var ii = 0; ii < req.length; ii++) {
            msg += "#i" + req[ii][0] + "##z" + req[ii][0] + "#x" + req[ii][1];
            if (ii % 3 == 0) {
                msg += "\r\n";
            }
        }
        msg += "\r\n";
        msg += "#g----------------------------------------------\r\n";
        for (var i = 0; i < weapon.length; i++) {
            msg += "#r#L" + i + "#";
            msg += "#i" + weapon[i] + "##z" + weapon[i] + "##l\r\n";
        }
        cm.sendSimple("#b#e���ã�����#r10������װ#b��Ҫ���²��ϣ�û�в��Ͽɲ���Ŷ\r\n\r\n" + msg + "");
    } else if (status == 1) {
        sels = selection;
        if (!cm.canHold(weapon[sels])) {
            cm.sendNext("#r�����ռ䲻��");
            cm.dispose();
            return;
        }
        for (var i = 0; i < req.length; i++) {
            if (!cm.haveItem(req[i][0], req[i][1])) {
                cm.sendNext("#b������û��#r�㹻�Ĳ���#k�������ռ�����ȥ�ɣ�");
                cm.dispose();
                return;
            }
        }
        cm.sendYesNo("#b�Ƿ�Ҫ�һ�#r10����ϵ��#r #i" + weapon[sels] + "#? \r\n");
    } else if (status == 2) {
        for (var i = 0; i < req.length; i++) {
            cm.gainItem(req[i][0], -req[i][1]);
        }
        cm.gainItem(weapon[sels], 1);
		Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "���ϳ����ġ�" + " : " + "[" + cm.getChar().getName() + "]�ɹ��ϳ���10����װ������")); 
        cm.sendNext("#b�Ѿ��һ����ˣ���ǰ�������鿴 #i" + weapon[sels] + "#");
        cm.dispose();
    } else {
        //cm.sendNext("#r��������: mode : " + mode + " status : " + status);
        cm.dispose();
    }
}