
var status = 0;
var ��ˮ�� = 4021008;
var ��ɫ��ͷ = "#fUI/UIWindow/Quest/icon2/7#";
var ��ɫ��ͷ = "#fUI/UIWindow/Quest/icon6/7#";
var Բ�� = "#fUI/UIWindow/Quest/icon3/6#";
var ����new = "#fUI/UIWindow/Quest/icon5/1#";
var ��̾�� = "#fUI/UIWindow/Quest/icon0#";
var ������ͷ = "#fUI/Basic/BtHide3/mouseOver/0#";
var �Ҹ� = "#k��ܰ��ʾ���κηǷ��������ҷ�Ŵ���.��ɱ��������.";
function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    } else {
        if (status >= 0 && mode == 0) {
            cm.dispose();
            return;
        }
        if (mode == 1)
            status++;
        else
            status--;
        if (status == 0) {
		if(cm.getPlayer().getGuild() == null){
			cm.sendOk("�㻹û�м�����!");
			cm.dispose();
		} else {
			if(cm.getBossLog('����ǩ��') < 1){
				cm.gainItem(4001266,1);
                 // Packages.handling.world.World.Broadcast.broadcastMessage(Packages.MaplePacketCreator.serverNotice(12,cm.getC().getChannel(),"[ÿ��ǩ��]" + " : " + " [" + cm.getPlayer().getName() + "]����ǩ���ɹ���",true).getBytes()); 

				cm.setBossLog('����ǩ��');
				cm.sendOk("ǩ���ɹ������#v4001266#һö!");
				cm.dispose();
			} else {
				cm.sendOk("���Ѿ�ǩ������!");
				cm.dispose();
			}

		}


        } else if (status == 1) {
			cm.sendOk("����������Ү");
			cm.dispose();
			
        }
    }
}
