var ���ͼ�� = "#fUI/UIWindow.img/QuestIcon/7/0#";
var weapon = new Array(

	Array(1004492,1003946),//ñ��+
	Array(1052929,1052647),//�·�
	Array(1073057,1072853),//Ь��
	Array(1082647,1082540),//����+
	Array(1102828,1102612),//����+
	//Array(1132151,1132151),//����
	//1012524,//����Ʒ
	Array(1302336,1302289),//�Ͻ�㵥�ֽ���+
	Array(1312201,1312165),//�Ͻ�㵥�ָ�
	Array(1402253,1402210),//�Ͻ��Ҷ˫�ֽ�+
	Array(1412180,1412147),//�Ͻ��Ҷ˫��ս��+
	Array(1432216,1432178),//�Ͻ��Ҷ֮ǹ+
	Array(1442270,1442234),//�Ͻ��Ҷì+
	Array(1332277,1332238),//�Ͻ��Ҷ��+
	Array(1472263,1472226),//�Ͻ��Ҷȭ��+
	Array(1372225,1372188),//�Ͻ��Ҷ��������+
	Array(1382263,1382222),//�Ͻ��Ҷ��������+
	Array(1452255,1452216),//�Ͻ��Ҷ��+
	Array(1462241,1462093),//�Ͻ��Ҷ��+
	Array(1482218,1482179),//�Ͻ��Ҷȭצ+
	Array(1492233,1492190)//�Ͻ��Ҷ��ܿ���+
	
	

	
	
	
);
//������������
var req = [
    [4310029, 4],//ʮ�ֱ�
    [4310034, 4],//�����
    [4001126, 8888],//��Ҷ
	[4000313, 500],//�ƽ��Ҷ
	[4310143, 10],//boss��
	[4251201, 1],//�е����
	[4000463, 88]
];
var rem = 100000000;
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
		
		msg += " "+���ͼ��+":#r1��#k";
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
			Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "���ϳ����ġ�" + " : " + "[" + cm.getChar().getName() + "]�ɹ��ϳ��˼�ƷѰ��װ������")); 
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