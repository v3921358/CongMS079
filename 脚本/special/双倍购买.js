
var С�̻� ="#fMap/MapHelper/weather/squib/squib4/1#";
var qilin = new Array(
{ ��Ʒ: 5211047, ʱ��: 2,  ���: 0,    log: "���˫����" },
{ ��Ʒ: 5211047, ʱ��: 3,  ���: 5000,    log: "����˫����" },
{ ��Ʒ: 5360015, ʱ��: 3,  ���: 5000 , log: "����˫����"},
{ ��Ʒ: 5211060, ʱ��: 3,  ���: 9800 , log: "����������"}


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
	
        msg += "\t\t"+�ʺ�+"  #e#d ˫ �� �� ȡ #k#n  #r  "+�ʺ�+"#b#k#n\r\r\n"+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+"\r\n�ˣ���ѡ������Ҫ��ȡ�Ŀ�Ƭ����ȡ��������Ч�ӱ�\r\n";
        //{ ��Ʒ: 1122017, ʱ��: 24*7,  ���: 200000 , log: "�������׹"}
        for (var i = 0; i < qilin.length; i++) {
            msg += "#r#L" + i + "#";
            msg += "#v" + qilin[i].��Ʒ + "" +  "# ʱ�ޣ�" + qilin[i].ʱ�� + "Сʱ  " + qilin[i].log + "  ��Ҫ ��� " + qilin[i].��� + "#l\r\n";
        }
        cm.sendSimple("" + msg + "");
    } 
	else if (status == 1) {
        sels = selection;
        if (cm.getBossLog(qilin[sels].log) >= 1) {
			cm.sendNext("#rÿ��ֻ��һ��Ŷ��");
            cm.dispose();
            return;
		}
		
		if (!cm.canHold(qilin[sels].��Ʒ)) {
            cm.sendNext("#r�����ռ䲻��");
            cm.dispose();
            return;
        }
        if (cm.getPlayer().getCSPoints(1) < qilin[sels].���) {
			cm.sendNext("����㣬�޷��һ���");
            cm.dispose();
            return;
        }
        cm.sendYesNo("#b�Ƿ�Ҫ�һ�#r #v" + qilin[sels].��Ʒ + "##t" + qilin[sels].��Ʒ + "#? \r\n");
    } else if (status == 2) {
		cm.setBossLog(qilin[sels].log);
        cm.getPlayer().modifyCSPoints(1, -qilin[sels].���, true);
        cm.gainItem(qilin[sels].��Ʒ,1,qilin[sels].ʱ��);
		cm.getPlayer().getStat().recalcLocalStats();
        cm.sendNext("#b�ɹ��һ���Ʒ");
        cm.dispose();
    } else {
        cm.sendNext("#r��������: mode : " + mode + " status : " + status);
        cm.dispose();
    }
}
var ���� ="#fEffect/SetEff/208/effect/walk2/4#";
var ����1 ="#fEffect/SetEff/208/effect/walk2/3#";
var С�� ="#fMap/MapHelper/weather/birthday/2#";
var �һ� ="#fMap/MapHelper/weather/rose/4#";
var ���Ҷ ="#fMap/MapHelper/weather/maple/2#";
var ���Ҷ ="#fMap/MapHelper/weather/maple/1#";
var �ʺ� ="#fEffect/ItemEff/1071085/effect/walk1/2#";
var ����è ="#fUI/ChatBalloon/37/n#";
var è�� =  "#fUI/ChatBalloon/37/ne#";
var è�� =  "#fUI/ChatBalloon/37/nw#";
var �� =    "#fUI/ChatBalloon/37/e#";
var �� =    "#fUI/ChatBalloon/37/w#";
var ����è ="#fUI/ChatBalloon/37/s#";
var è���� ="#fUI/ChatBalloon/37/se#";
var è���� ="#fUI/ChatBalloon/37/sw#";