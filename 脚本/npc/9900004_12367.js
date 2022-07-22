var ca = java.util.Calendar.getInstance();
var year = ca.get(java.util.Calendar.YEAR); //������
var month = ca.get(java.util.Calendar.MONTH) + 1; //����·�
var day = ca.get(java.util.Calendar.DATE); //��ȡ��
var hour = ca.get(java.util.Calendar.HOUR_OF_DAY); //���Сʱ
var second = ca.get(java.util.Calendar.SECOND); //�����
var weekday = ca.get(java.util.Calendar.DAY_OF_WEEK);//�ж�����
var status = -1;

// �ͽ���Ʒ������
var sjwpArr = [
    4000000,4000001,4000002,4000003,4000004,4000005,4000006,4000007,4000008,4000009,
    4000010,4000011,4000012,4000013,4000014,4000015,4000016,4000017,4000018,4000019,
    4000020,4000021,4000022,4000023,4000024,4000025,4000026,4000027,4000028,4000029,
    4000030,4000031,4000032,4000033,4000034,4000035,4000036,4000037,4000039,
    4000041,4000042,4000043,4000044,4000045,4000046,4000048,4000049,
    4000050,4000051,4000052,4000053,4000054,4000055,4000056,4000057,4000058,4000059,
    4000060,4000061,4000062,4000063,4000064,4000065,4000068,4000069,
    4000070,4000071,4000072,4000073,4000074,4000075,4000076,4000077,4000078,4000079,
    4000080,4000082,4000083,4000084,4000085,4000086,4000087,4000088,4000089,
    4000090,4000091,4000092,4000093,4000094,4000095,4000096,4000097,4000098,4000099
];
// ������Ʒ������
var itemSet = [4000313,4020000,4020001,4020002,4020003,4020004,4020005,4020006,4020007,4020008,4004000,4004001,4004002,4004003,4004004,4010000,4010001,4010002,4010003,4010004,4010005,4010006,5150040,4001136,5201004,5201005,4001245,3010000,3010001,3010002,3010003,3010004,3010005,3010006,3010007,3010008,3010009,3010010,3010012,3010013,3010014,3010016,3010017,3010018,2040807,2040709,2040710,2040711,2040806,2044103,2044203,2040006,4310149,2040303,2040403,2040506,2040507,2040603,2043003,2043103,2043703,2043803,2044003,2044303,2044403,2044503,2044603,2044703,2044815,2044908,2460005,2614000,4310036,4310023,4310024,1302030,1332025,1382012,1432012,1442024,1452022,1462019,1472032,1422014,1412011,1482020,1492020];

var rwflag;
var rwstr;

// �ͽ���Ʒ��Ҫ��������
var sjwpgs = 300;

var wpindex;
var wpid;
var wpgs;


function start() {
    action(1, 0, 0);
}

function action(mode, type, selection) {

    if (mode == 1) {
        status++;
    } else if (mode == 0 && status != 0) {
        status--;
    } else {
        cm.dispose();
        return;
    }

    if (status == 0) {
        var tj = cx();
        var tjzt = tj == 1 ? "���ύ" : "δ�ύ";

        rwflag = cxrwsc();
        rwstr = '';
        if (rwflag == 0) {
            rwstr = scrw();
        }
        else {
            rwstr = rwflag;
        }
        var rwstrs = rwstr.split("|");
        wpid = rwstrs[1];
        wpgs = rwstrs[2];

        var text = "\t\t\t\t  #e#r#v4000038#�ͽ��ʦ#v4000038##k#n\r\n";
		text += "#e#rÿСʱ�Զ�ˢ���ͽ�������Ʒ!\r\n\r\n";
		text += "#e#bÿСʱ��һλ�ύ����ҿɻ�ý���!\r\n\r\n";
		text += "#e#kÿСʱ������Ʒȫ����Ϊͬһ��Ʒ!\r\n";
		text += "#e#kÿ�������쿪��!\r\n";
        text += "��ǰ�ͽ������ύ״̬Ϊ��#r" + tjzt + "\r\n";

        text += "��ǰ��Ҫ��Ʒ#v"+ wpid + "#������" + wpgs + "\r\n";
        text += "#L1##e#b��Ҫ�ύ�ͽ�����\r\n����:#v4001126#50�� #v4000313#10��#l\r\n";
        cm.sendSimple(text);
    }
    else if (status == 1) {
        if (selection == 1) {
            var tj = cx();
            if (tj == 1) {
                cm.sendOk("#e#k��Сʱ�Ѿ������ύ�����´�����");
                cm.dispose();
				} else if (weekday != 6) {
            cm.sendOk("���첻�������޷��������");
            cm.dispose();
            } else {
                rwflag = cxrwsc();
                rwstr = '';
                rwstr = rwflag;
                var rwstrs = rwstr.split("|");
                wpid = rwstrs[1];
                wpgs = rwstrs[2];

                // �ж���Ʒ������
                if (cm.haveItem(wpid, wpgs) == false) {
                    cm.sendOk("��û�д����㹻����Ʒ");
                    cm.dispose();
                    return;
                }
                // �۳���Ʒ �����ͽ�����
                cm.gainItem(wpid, -wpgs);
                tjrw();

                // ����
                cm.gainItem(4001126, 50);
			    cm.gainItem(4000313,10);//�����
				cm.getPlayer().setOneTimeLog('����');
				cm.getPlayer().setOneTimeLog('����');
                cm.sendOk("��ϲ��ɹ����ύ�ͽ�����");
				cm.worldMessage(6,"��ϲ��ң�["+cm.getName()+"]��һλ�ύ�˱�ʱ���ͽ�����,������Ҷ*50 �ƽ��Ҷ*10��");
                cm.dispose();
            }
        }
        else {
            cm.dispose();
        }
    }
}

function cx() {
    var h = cm.getHour();
    var conn = Packages.database.DatabaseConnection.getConnection();
    var sql = "SELECT * FROM bosslog WHERE characterid = 0 AND bossid = '" + h + "tj';";
    var pstmt = conn.prepareStatement(sql);
    var result = pstmt.executeQuery();
    var i = 0;
    while (result.next()) {
        i++
    }
    if (i > 0) {
        return 1;
    }
    return 0;
}

function cxrwsc() {
    var h = cm.getHour();
    var conn = Packages.database.DatabaseConnection.getConnection();
    var sql = "SELECT * FROM bosslog WHERE characterid = 0 AND bossid LIKE '" + h + "rw%';";
    var pstmt = conn.prepareStatement(sql);
    var result = pstmt.executeQuery();
    var i = 0;
    var str = '';
    while (result.next()) {
        i++;
        str = result.getString("bossid");
    }
    if (i > 0) {
        return str;
    }
    return 0;

}

function scrw() {
    var h = cm.getHour();
    wpindex = Math.floor(Math.random()*(sjwpArr.length));
    wpid = sjwpArr[wpindex];
    wpgs = Math.floor(Math.random()*sjwpgs) + 1;
    var str = h + "rw|" + wpid + "|" + wpgs;
    var conn = Packages.database.DatabaseConnection.getConnection();
    var sql = "INSERT INTO bosslog(characterid, bossid) VALUES (0, '" + str +"');";
    var pstmt = conn.prepareStatement(sql);
    var result = pstmt.executeUpdate();
    return str;
}

function tjrw() {
    var h = cm.getHour();
    var str = h + "tj";
    var conn = Packages.database.DatabaseConnection.getConnection();
    var sql = "INSERT INTO bosslog(characterid, bossid) VALUES (0, '" + str +"');";
    var pstmt = conn.prepareStatement(sql);
    var result = pstmt.executeUpdate();
    return 1;
}