/*
 �ű����ܣ��г�����Ա
 */

var a = 0;
var icon = "#fUI/Basic.img/BtMin2/normal/0#";
var iconEvent = "#fUI/UIToolTip.img/Item/Equip/Star/Star#";
var ttt4 ="#fUI/UIWindow/Quest/icon5/1#";//"+ttt4+"//����New
var icon2 = "#fEffect/CharacterEff/1082565/2/0#";

var List = Array(
		Array(iconEvent + " #r�������", 1, 1, 9310058),
		Array(iconEvent + " #rħ������", 2, 1, 9310382),
		Array(iconEvent + " #r���Ի���", 3, 1, 9310382)
		//Array(iconEvent + " #b����ϴ��", 1, 1, 9000174),
		//Array(iconEvent + " #b�����ƹ�", 1000, 1),
		//Array(iconEvent + " #b����Ǳ��", 1001, 1),
		//Array(iconEvent + " #rʵ�����#k", 5, 1, 9000069),
		//Array(iconEvent + " #b�����װ#k", 1, 1, 9000069),
		//Array(iconEvent + " #bϡ������#k", 2, 1, 9000069),
		//Array(iconEvent + " #b�ϳ�װ��#k", 4, 1, 9000069),
		//Array(iconEvent + " #b��Ʒ����#k", 3, 1, 9000069),
		//Array(iconEvent + " #bƷ����װ#k", 999, 1, 9310144),
		//Array(iconEvent + " #bƤ���˺�#k", 12, 1, 9900004),
		//Array(iconEvent + " #bְҵ�ֲ�#k", 10, 1, 9900004),
		//Array(iconEvent + " #bְҵ����#k", 11, 1, 9900004)
		//Array(iconEvent + " #b������", 500, 1, 9900004)
		//Array(iconEvent + " #rů��Ů��#k", 777, 1, 9310144)
)
var text;
//�Ƿ������֣�ģʽ�����

function start() {
    a = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    } else {
        if (mode == 1)
            a++;
        else
            a--;
        if (a == -1) {
            cm.dispose();
        } else if (a == 0) {
            text = "";
            for (var i = 0; i < 2; i++) {
                ListFor(i);
            }
			text += "\r\n\r\n\r\n#e#g\t\t\t  "+icon2+" ף����Ϸ��� "+ icon2 +"#n#k\r\n";
            cm.sendSimple(text)
        } else if (a == 1) {
            var mode_ = List[selection][1];
            cm.dispose();
			var npcid = 9310058;
			if (List[selection][3] != null)
				npcid = List[selection][3];
            cm.openNpc(npcid, mode_);
        }//a
    }//mode
}//f


function ListFor(type) {
    switch (type) {
        case 1://�������
            text += "#e#g������������������#k#r ����ר�� #k#g������������������#n#k\r\n\r\n#b������ʾ�����������ӣ��˸���Խ�ڷܻ�ø���Խ��\r\n\r\n��Ҫ˵û�и������Ϳ����ǲ����ڿ���\r\n\r\n";
            break;
    }
    var x = 0;
    for (var i = 0; i < List.length; i++) {
        if (List[i][2] == type) {
            if (x == 2) {
                text += "  #L" + i + "#" + List[i][0] + "#l\r\n";
                x = 0;
            } else {
                text += "  #L" + i + "#" + List[i][0] + "#l";
                x++;
            }
        }
    }
    text += "#e";
}