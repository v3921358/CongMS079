var status = -1;
var typed = 0;
var isSuccess = 0;
var giftItems = Array(2432353, 5062002, 5062500);
var needItems = Array(
	Array(
		4032579,// - ���۽������ - �����˵����������ۣ�����������˿��ĵĽ���Ƥ�����������ս��ӵ���Ʒ��
		4032580,// - �������� - ʱ��͸�����޻��������⣬��˵���˻����˾������档���������ս��ӵ���Ʒ��
		4032581// - �񾲰ײ� - �����ڰ������еİײˣ���˵���������˱����侲�����������ս��ӵ���Ʒ��
	),
	Array(
		4032582,// - ���ٴ���Ƥ - ��ҩ�ݾ����Ĵ���Ƥ����˵���Ը��˴������ཡ�������������մ������Ʒ��
		4032583,// - �������� - ��������Ը�������ڣ���˵���Ը��˴������ร�������������մ������Ʒ��
		4032584// - ���˶��� - �ý��������Ķ��ͣ���˵���Ը��˴���������ˡ����������մ������Ʒ��
	),
	Array(
		4032585,// - ����Ŵ�� - ��������ؼ�Ŵ�ף���˵���԰����ǵ�����ϵ��һ�𡣽���������������Ʒ��
		4032586,// - �����Ǿ� - �������۵������Ǿ�����˵���������ǵĹ�ϵ������ܰ������������������Ʒ��
		4032587// - �����ⴸ - ���´��е���ⴸ����˵����������Ѱ�����ע����Ե�֡�����������������Ʒ��
	)
)
function start() {
    action(1, 0, 0);
}

function action(mode, type, selection) {
	if (mode == 1) {
		status++;
	} else {
		if (status >= 0) {
			cm.dispose();
			return;
		}
		status--;
	}
	if (status == 0) {
		var text = "���Ӻö�����������Щ�������ҳ����һ����Ӧ�еı���ġ�\r\n�����������������е���\r\n";
		text+="#b#L0##v4032592# �������ӿ��Ի�� #r#z2432353##k#l\r\n";
		text+="#b#L1##v4032593# ����������Ի�� #r#z5062002##k#l\r\n";
		text+="#b#L2##v4032594# ���������Ի�� #r#z5062500##k#l\r\n";
		cm.sendSimple(text);
	} else if (status == 1){
		typed = selection;
		var text = "����Ҫ���²������������\r\n\r\n";
		needItem = needItems[selection];
		for(var key in needItem) {
			isEnough = "#r(��)#k";
			if (cm.haveItem(needItem[key])) {
				isEnough = "#g(��)#k";
				isSuccess++;
			}
			text+=isEnough+" #k��Ҫ10��#b#v"+needItem[key]+"##t"+needItem[key]+"#\r\n";
		}
		if (isSuccess == 3) {
			text+="\r\n#g(��)#k��ʾ�㹻��#r(��)#k��ʾ���㣬#d#e�Ƿ����������#n#k\r\n";
			cm.sendYesNo(text);
		} else {
			status = -10;
			text+="\r\n#g(��)#k��ʾ�㹻��#r(��)#k��ʾ���㣬#d#e��Ĳ��Ϻ�����Ŷ��#n#k\r\n";
			cm.sendSimple(text);
		}
	} else if (status == 2) {
		isSuccess = 0;
		needItem = needItems[typed];
		for(var key in needItem) {
			cm.gainItem(needItem[key], -10);
		}
		giftId = giftItems[typed];
		cm.gainItem(giftId, 1);
		status = -10;
		cm.sendSimple("����ζ����Ϊ����������1��#b#v"+giftId+"##t"+giftId+"##k�����#b����һ����#k����������");
	}
}