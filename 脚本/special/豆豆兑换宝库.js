var Ǧ��ͼ�� = "#fUI/UIWindow.img/PvP/btWrite/mouseOver/0#";
var ������ = "#fUI/StatusBar/BtClaim/normal/0#";
var ����1 = "#fEffect/CharacterEff/1082565/0/0#";
var ����2 = "#fEffect/CharacterEff/1082565/2/0#";
var ����3 = "#fEffect/CharacterEff/1082565/4/0#";
var �̵���Ʒ = Array(
			//Array(5062000,1,50, ""), 
			//Array(5062000,10,480, ""),
			//Array(2049401,1,30, ""),
			//Array(2049401,10,280, ""),	
			Array(2340000,1,10, "")
			);
function start() {
    status = -1;

    action(1, 0, 0);
}
function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    }
    else {
        if (status >= 0 && mode == 0) {

            cm.sendOk("��л��Ĺ��٣�");
			cm.dispose();
	//cm.Lunpan();
           // cm.Guaguale();
            return;
        }
        if (mode == 1) {
            status++;
        }
        else {
            status--;
        }
        if (status == 0) {
			var str1 = "";	
			for (var i = 0; i < �̵���Ʒ.length; i++){
                   str1 += "#L"+i+"##v"+�̵���Ʒ[i][0]+"##z"+�̵���Ʒ[i][0]+"# ������#b"+�̵���Ʒ[i][1]+"��#d  �۸�:#r"+�̵���Ʒ[i][2]+"#d "+�̵���Ʒ[i][3]+"#l\r\n";
            }
            cm.sendSimple("��ã������� - �����һ����� - \r\n ��Ŀǰ�Ķ���ֵ:#r"+cm.getBeans()+"#n#d  \r\n\r\n"+str1);//#L2#"+����2+"#r�ƹ�ϵͳ���� - �����ֺ���ϸ˵��
        } else if (status == 1) {
			if(cm.getBeans() >= �̵���Ʒ[selection][2]){
				cm.gainBeans(-�̵���Ʒ[selection][2]);
				cm.gainItem(�̵���Ʒ[selection][0],�̵���Ʒ[selection][1]); 
				cm.sendOk("����ɹ�~!");
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "���һ��̳ǡ�" + " : " + "[" + cm.getChar().getName() + "]�ɹ��ö����һ���������ߣ���")); 

				//cm.����(1,"["+ cm.getPlayer().getName() + "] ���ƹ�ֵ�̵��й�����Ʒ!");
                cm.dispose();
			} else {
				cm.sendOk("���Ķ���ֵ���㣬��ȥ�����г��һ��ݵ���~!");
                cm.dispose();
			}
		}
	}
}


