var Ǧ��ͼ�� = "#fUI/UIWindow.img/PvP/btWrite/mouseOver/0#";
var ������ = "#fUI/StatusBar/BtClaim/normal/0#";
var ����1 = "#fEffect/CharacterEff/1082565/0/0#";
var ����2 = "#fEffect/CharacterEff/1082565/2/0#";
var ����3 = "#fEffect/CharacterEff/1082565/4/0#";
var �̵���Ʒ = Array(
			Array(4001129,10,20, "��Ҷ"), 
			Array(4001129,1,20, "���ˮ��"),
			Array(4001129,1,5, "����ʯ"),
			Array(4001129,1,5, ""),	
			Array(4001129,1,10, "")
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
                   str1 += "#L"+i+"##v"+�̵���Ʒ[i][0]+"##z"+�̵���Ʒ[i][0]+"#�� #b"+�̵���Ʒ[i][1]+"#d  �۸�:#r"+�̵���Ʒ[i][2]+"#d "+�̵���Ʒ[i][3]+"#l\r\n";
            }
            cm.sendSimple("��ã������� - �ƹ�ֵ�̵� - \r\n ��Ŀǰ���ƹ�ֵ:#r"+cm.��ȡ�ƹ�ֵ()+"#n#d  \r\n\r\n"+str1);//#L2#"+����2+"#r�ƹ�ϵͳ���� - �����ֺ���ϸ˵��
        } else if (status == 1) {
			if(cm.��ȡ�ƹ�ֵ() >= �̵���Ʒ[selection][2]){
				cm.�����ƹ�ֵ(-�̵���Ʒ[selection][2]);
				cm.gainItem(�̵���Ʒ[selection][0],�̵���Ʒ[selection][1]); 
				cm.sendOk("����ɹ�~!");
				cm.����(1,"["+ cm.getPlayer().getName() + "] ���ƹ�ֵ�̵��й�����Ʒ!");
                cm.dispose();
			} else {
				cm.sendOk("�ƹ�ֵ����~!");
                cm.dispose();
			}
		}
	}
}


