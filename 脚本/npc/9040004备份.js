
/*
//=====================================================================����
var ��ɫ��ͷ = "#fEffect/CharacterEff/1112908/0/1#";  //�ʹ�3
var ս����= "#fUI/GuildMark/Mark/Etc/00009003/15#"
var ������������ ="#fUI/GuildMark/Mark/Etc/00009023/1#"
var ��ʯ�������� ="#fUI/GuildMark/Mark/Etc/00009023/13#"
var ��� ="#fUI/GuildMark/Mark/Pattern/00004014/1#"
var ���� ="#fUI/GuildMark/Mark/Pattern/00004014/5#"
var ð�ձ� ="#fUI/GuildMark/Mark/Pattern/00004014/2#"
var ���д�� ="#fUI/GuildMark/Mark/Pattern/00004014/3#"
var ������� ="#fUI/GuildMark/Mark/Pattern/00004014/4#"
var ������� ="#fUI/GuildMark/Mark/Plant/00003004/1#" 
var ����ֵ="#fUI/GuildMark/Mark/Etc/00009001/14#"
var ÿ�ճ�ֵ="#fUI/GuildMark/Mark/Etc/00009022/10#"
var �ۼƳ�ֵ="#fUI/GuildMark/Mark/Etc/00009022/1#"
var �ڹ� = "#fUI/GuildMark.img/Mark/Etc/00009013/16#";
var ��Ȧ = "#fUI/Gateway.img/WorldSelect/select/3#";
var �ڹ� = "#fUI/GuildMark.img/Mark/Etc/00009004/16#";
var ��� = "#fUI/GuildMark.img/Mark/Etc/00009023/14#";
var �ڱ� = "#fUI/GuildMark.img/Mark/Pattern/00004020/16#";
var �ڻ� = "#fUI/GuildMark.img/Mark/Etc/00009018/16#";
var �ڵ� = "#fUI/GuildMark.img/Mark/Etc/00009016/16#";
var ��Ȧ = "#fUI/Gateway.img/WorldSelect/select/3#";
var �ڹ� = "#fUI/GuildMark.img/Mark/Etc/00009004/16#";
var ��� = "#fUI/GuildMark.img/Mark/Etc/00009023/14#";
var ��Ҷ = "#fUI/ITC.img/Base/Tab/Enable/0#";
var ��� = "#fUI/Basic.img/BtCoin/normal/0#";
var ���� = "#fUI/CN_Chat.img/roomList/Vip#";
var ͼ�� = "#fUI/Login.img/WorldSelect/world/t1#";
var �ָ��� = "#fUI/Login.img/WorldSelect/channel/chgauge#";
var ��� = "#fUI/UIWindow.img/UserInfo/bossPetCrown#";
var С���� = "#fUI/UIWindow.img/UserList/Party/icon0#";
var �ʹ� = "#fUI/UIWindow.img/UserList/Guild/GuildRank/icon0#";
var ���� = "#fUI/UIWindow.img/Quest/reward#";
var ���� = "#fUI/UIWindow.img/MonsterCarnival/icon2#";
var ��Q�� = "#fUI/UIWindow.img/QuestAlarm/BtQ/ani/0#";
var ����ͷ = "#fUI/UIWindow.img/itemSearch/BtRight/normal/0#";
var ��R = "#fUI/UIWindow.img/Minigame/Common/readyOn#";
var ���� = "#fUI/UIWindow.img/Delivery/icon4#";
var ѫ�� = "#fUI/UIWindow.img/MonsterBook/fullMark#";
var �췽 = "#fUI/UIWindow.img/AriantMatch/characterIcon/0#";
var ���� = "#fUI/UIWindow.img/AriantMatch/characterIcon/1#";
var �̷� = "#fUI/UIWindow.img/AriantMatch/characterIcon/2#";
var �Ʒ� = "#fUI/UIWindow.img/AriantMatch/characterIcon/3#";
var �Ϸ� = "#fUI/UIWindow.img/AriantMatch/characterIcon/4#";
var �ȷ� = "#fUI/UIWindow.img/AriantMatch/characterIcon/5#";
var ������ = "#fUI/UIWindow.img/counsel/Loading/3#";
var ������ = "#fUI/ChatBalloon.img/miniroom/Omok#";
var б��� = "#fUI/ChatBalloon.img/miniroom/PersonalShop#";
var ��è = "#fUI/ChatBalloon.img/pet/1/nw#";
var ë�� = "#fUI/ChatBalloon.img/pet/12/nw#";
var ���� = "#fUI/ChatBalloon.img/pet/19/head#";
var ������ = "#fUI/ChatBalloon.img/pet/28/head#";
var ����ͷ = "#fUI/ChatBalloon.img/pet/19/nw#";
var ����ͷ = "#fUI/ChatBalloon.img/pet/19/ne#";
var ������ = "#fUI/ChatBalloon.img/pet/28/nw#";
var ������ = "#fUI/ChatBalloon.img/pet/28/ne#";
var ������ = "#fUI/ChatBalloon.img/pet/28/arrow#";
var ������ͷ = "#fUI/ChatBalloon.img/pet/28/se#";
var ������ͷ = "#fUI/ChatBalloon.img/pet/28/sw#";
var ���� = "#fUI/Basic.img/VScr6/enabled/thumb0#";
var ��V = "#fUI/Login.img/Title/check2/1#";
var ������ = "#fUI/Login.img/CharSelect/icon/up#";
var ��N = "#fUI/Login.img/CharSelect/icon/new#";
var ���� = "#fUI/GuildMark.img/Mark/Pattern/00004001/11#";
var ���� = "#fUI/GuildMark.img/Mark/Pattern/00004007/14#";
var ���� = "#fUI/GuildMark.img/Mark/Pattern/00004014/11#";

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
            return;
        }
        if (mode == 1) {
            status++;
        }
        else {
            status--;
        }
        if (status == 0) {
			//if (cm.getvip() == 1) {
             //   cs = 5;
            //} else if (cm.getvip() == 2) {
            //    cs = 10;
            //} else if (cm.getvip() == 3) {
            //    cs = 15;
            //} else if (cm.getvip() == 4) {
            //    cs = 20;
            //} else {
             //   cs = 0;
			//	}
            var tex2 = "";
            var text = "";
            for (i = 0; i < 10; i++) {
                text += "";
            }
			//��ʾ��ƷIDͼƬ�õĴ�����  #v����д��ID#
		
		    text += "                     #e#r" + ���� + "�����" + ���� + "\r\n"//3
			text += "\t\t\t #L10#"+��Ҷ+"#r���н���"+��Ҷ+"#l\r\n\r\n"//3
			text += "\t#k#L6#"+�ʹ�+"ս������"+�ʹ�+"#l  #L7#"+�ʹ�+"ɱ������"+�ʹ�+"#l\r\n"//3
			text += "\t#L2#"+�ʹ�+"��������"+�ʹ�+"#l  #L3#"+�ʹ�+"�������"+�ʹ�+"#l\r\n"//3
			text += "\t#L9#"+�ʹ�+"��ֵ����"+�ʹ�+"#l  #L5#"+�ʹ�+"��������"+�ʹ�+"#l\r\n"//3
			
			text += "\t\t\t\t\t\t\t\t#L456##n" + ��ɫ��ͷ + " #e������ҳ#l\r\n\r\n"
			//text += "#L6#"+�ڱ�+"ս������#l\t"//3
			//text += "\r\n"+���+""+���+""+���+""+���+""+�ڹ�+""+���+""+���+""+���+""+���+""+���+""+���+""+���+""+���+""+���+""+���+""+���+""+�ڹ�+""+���+""+���+""+���+""+���+"\r\n";
			//text += "#L7#"+������������+"ÿ����������#l\t"//3
			//text += "#L8#"+��ʯ��������+"�ۼ���������#l\t"//3
			
			//text += "\t\t\t  #L9#"+�ڱ�+"���㸣��#l\r\n"//3
			 cm.sendSimple(text);
        } else if (status == 1) {
			if(selection == 1){
				cm.displayLevelRanks();
				cm.dispose();
				return;
			}else if(selection == 2){
				cm.dispose();
				cm.�������а�();
				return;
			}else if(selection == 3){
				cm.dispose();
				cm.�������();
				return;
			}else if(selection == 4){
				cm.dispose();
				cm.�������();
				return;
			}else if(selection == 5){
				cm.dispose();
				cm.�������а�();
				return;
			}else if(selection == 6){
				cm.dispose();
				cm.����ս�������а�();
				return;
			}else if(selection == 7){
				cm.dispose();
			a = cm.����������а�();
            cm.sendOk(a);
			cm.dispose();
				return;
			}else if(selection == 9){
				cm.dispose();
				cm.ÿ�ճ�ֵ_���а�();
			}else if(selection == 10){
				cm.dispose();
				cm.openNpc(9900004,96540);
			}
			
			}
        } 
}

*/


/*
��Ҷð�յ�(079)��Ϸ�����
 �ű������а�
 */
importPackage(Packages.database);
var Z = "#fUI/GuildMark.img/Mark/Letter/00005025/1#";
var Y = "#fUI/GuildMark.img/Mark/Letter/00005024/3#";
var X = "#fUI/GuildMark.img/Mark/Letter/00005023/1#";
var D = "#fUI/GuildMark.img/Mark/Letter/00005003/1#";
var M = "#fUI/GuildMark.img/Mark/Letter/00005012/1#";
var A = "#fUI/GuildMark.img/Mark/Letter/00005000/1#";
var P = "#fUI/GuildMark.img/Mark/Letter/00005015/1#";
var Z = "#fUI/GuildMark.img/Mark/Letter/00005025/9#";
var ��ͷ = "#fUI/Basic/BtHide3/mouseOver/0#";
var �� = "#fUI/GuildMark.img/Mark/Etc/00009001/13#";
var ��1 = "#fUI/GuildMark.img/Mark/Etc/00009001/8#";
var Ģ�� = "#fUI/UIWindow.img/Minigame/Common/mark#";
var ��ɫ��ͷ = "#fEffect/CharacterEff/1112908/0/1#";  //�ʹ�3
var ս����= "#fUI/GuildMark/Mark/Etc/00009003/15#"
var ������������ ="#fUI/GuildMark/Mark/Etc/00009023/1#"
var ��ʯ�������� ="#fUI/GuildMark/Mark/Etc/00009023/13#"
var ��� ="#fUI/GuildMark/Mark/Pattern/00004014/1#"
var ���� ="#fUI/GuildMark/Mark/Pattern/00004014/5#"
var ð�ձ� ="#fUI/GuildMark/Mark/Pattern/00004014/2#"
var ���д�� ="#fUI/GuildMark/Mark/Pattern/00004014/3#"
var ������� ="#fUI/GuildMark/Mark/Pattern/00004014/4#"
var ������� ="#fUI/GuildMark/Mark/Plant/00003004/1#" 
var ����ֵ="#fUI/GuildMark/Mark/Etc/00009001/14#"
var ÿ�ճ�ֵ="#fUI/GuildMark/Mark/Etc/00009022/10#"
var �ۼƳ�ֵ="#fUI/GuildMark/Mark/Etc/00009022/1#"
var �ڹ� = "#fUI/GuildMark.img/Mark/Etc/00009013/16#";
var ��Ȧ = "#fUI/Gateway.img/WorldSelect/select/3#";
var �ڹ� = "#fUI/GuildMark.img/Mark/Etc/00009004/16#";
var ��� = "#fUI/GuildMark.img/Mark/Etc/00009023/14#";
var �ڱ� = "#fUI/GuildMark.img/Mark/Pattern/00004020/16#";
var �ڻ� = "#fUI/GuildMark.img/Mark/Etc/00009018/16#";
var �ڵ� = "#fUI/GuildMark.img/Mark/Etc/00009016/16#";
var ��Ȧ = "#fUI/Gateway.img/WorldSelect/select/3#";
var �ڹ� = "#fUI/GuildMark.img/Mark/Etc/00009004/16#";
var ��� = "#fUI/GuildMark.img/Mark/Etc/00009023/14#";
var ��Ҷ = "#fUI/ITC.img/Base/Tab/Enable/0#";
var ��� = "#fUI/Basic.img/BtCoin/normal/0#";
var ���� = "#fUI/CN_Chat.img/roomList/Vip#";
var ͼ�� = "#fUI/Login.img/WorldSelect/world/t1#";
var �ָ��� = "#fUI/Login.img/WorldSelect/channel/chgauge#";
var ��� = "#fUI/UIWindow.img/UserInfo/bossPetCrown#";
var С���� = "#fUI/UIWindow.img/UserList/Party/icon0#";
var �ʹ� = "#fUI/UIWindow.img/UserList/Guild/GuildRank/icon0#";
var ���� = "#fUI/UIWindow.img/Quest/reward#";
var ���� = "#fUI/UIWindow.img/MonsterCarnival/icon2#";
var ��Q�� = "#fUI/UIWindow.img/QuestAlarm/BtQ/ani/0#";
var ����ͷ = "#fUI/UIWindow.img/itemSearch/BtRight/normal/0#";
var ��R = "#fUI/UIWindow.img/Minigame/Common/readyOn#";
var ���� = "#fUI/UIWindow.img/Delivery/icon4#";
var ѫ�� = "#fUI/UIWindow.img/MonsterBook/fullMark#";
var �췽 = "#fUI/UIWindow.img/AriantMatch/characterIcon/0#";
var ���� = "#fUI/UIWindow.img/AriantMatch/characterIcon/1#";
var �̷� = "#fUI/UIWindow.img/AriantMatch/characterIcon/2#";
var �Ʒ� = "#fUI/UIWindow.img/AriantMatch/characterIcon/3#";
var �Ϸ� = "#fUI/UIWindow.img/AriantMatch/characterIcon/4#";
var �ȷ� = "#fUI/UIWindow.img/AriantMatch/characterIcon/5#";
var ������ = "#fUI/UIWindow.img/counsel/Loading/3#";
var ������ = "#fUI/ChatBalloon.img/miniroom/Omok#";
var б��� = "#fUI/ChatBalloon.img/miniroom/PersonalShop#";
var ��è = "#fUI/ChatBalloon.img/pet/1/nw#";
var ë�� = "#fUI/ChatBalloon.img/pet/12/nw#";
var ���� = "#fUI/ChatBalloon.img/pet/19/head#";
var ������ = "#fUI/ChatBalloon.img/pet/28/head#";
var ����ͷ = "#fUI/ChatBalloon.img/pet/19/nw#";
var ����ͷ = "#fUI/ChatBalloon.img/pet/19/ne#";
var ������ = "#fUI/ChatBalloon.img/pet/28/nw#";
var ������ = "#fUI/ChatBalloon.img/pet/28/ne#";
var ������ = "#fUI/ChatBalloon.img/pet/28/arrow#";
var ������ͷ = "#fUI/ChatBalloon.img/pet/28/se#";
var ������ͷ = "#fUI/ChatBalloon.img/pet/28/sw#";
var ���� = "#fUI/Basic.img/VScr6/enabled/thumb0#";
var ��V = "#fUI/Login.img/Title/check2/1#";
var ������ = "#fUI/Login.img/CharSelect/icon/up#";
var ��N = "#fUI/Login.img/CharSelect/icon/new#";
var ���� = "#fUI/GuildMark.img/Mark/Pattern/00004001/11#";
var ���� = "#fUI/GuildMark.img/Mark/Pattern/00004007/14#";
var ���� = "#fUI/GuildMark.img/Mark/Pattern/00004014/11#";


function start() {
    status = -1;
    action(1, 0, 0);
}
function action(mode, type, selection) {
    if (status == 0 && mode == 0) {
        cm.dispose();
        return;
    }
    if (mode == 1) {
        status++;
    } else {
        status--;
    }
    cm.���˴浵();
    var MC = cm.getServerName();
    var ���˰񿪹� = cm.GetPiot("���˰񿪹�", "1");

    if (status == 0) {
        var selStr = "\t\t\t#e#r < " + ���� + " �� ҫ �� �� �� " + ���� + " >#k#n\r\n\r\n";
			selStr += "\r\n"+���+""+���+""+���+""+���+""+�ڹ�+""+���+""+���+""+���+""+���+""+���+""+���+""+���+""+���+""+���+""+���+""+���+""+�ڹ�+""+���+""+���+""+���+""+���+"\r\n";

        if (cm.GetPiot("���˰񿪹�", "1") <= 0) {
            selStr += "#L5##b"+�ʹ�+"��ҵȼ����а�"+�ʹ�+"#l";
			selStr += "#L6##b"+�ʹ�+"����������а�"+�ʹ�+"#l\r\n";
			
            selStr += "#L2##b"+�ʹ�+"��ҲƸ����а�"+�ʹ�+"#l";
            //selStr += "\t\t\t\t#L13##b����ʱ�����а�#l\r\n";
			//selStr += "\t\t\t\t#L4##b�����������а�#l\r\n";
            selStr += "#L0##b"+�ʹ�+"�����������а�"+�ʹ�+"#l\r\n";
			
            selStr += "#L19##b"+�ʹ�+"�����������а�"+�ʹ�+"#l";
            //selStr += "\t\t\t\t#L25##b���ֶ������а�#l\r\n";
			selStr += "#L20##b"+�ʹ�+"ս����ֵ���а�"+�ʹ�+"#l\r\n";	
			//selStr += "\t\t\t\t#L51##bɱ���������а�#l\r\n";
			//selStr += "\t\t\t\t#L53##b����������а�#l\r\n";
            //selStr += "\t\t\t\t#L52##b����������а�#l\r\n";
            //selStr += "\t\t\t\t#L21##b�������������#l\r\n";
            //selStr += "\t\t\t\t#L22##b�����������#l\r\n\r\n";
			
			selStr += "\r\n\r\n"+���+""+���+""+���+""+���+""+�ڹ�+""+���+""+���+""+���+""+���+""+���+""+���+""+���+""+���+""+���+""+���+""+���+""+�ڹ�+""+���+""+���+""+���+""+���+"\r\n";

			
        } else {
            selStr += "\r\nά���С�����";
            selStr += "\t\t\t\t#L666666##b���ؽ���#l";
        }
        if (cm.getPlayer().getGMLevel() == 6) {
            if (cm.GetPiot("���˰񿪹�", "1") <= 0) {
                selStr += "\r\n\t\t\t\t#L1000##b���˰�#g[������]#r[GM]#k#l";
            }
            if (cm.GetPiot("���˰񿪹�", "1") >= 1) {
                selStr += "\r\n\t\t\t\t#L1001##b���˰�#r[�ر���]#r[GM]#k#l";
            }
        }
        cm.sendSimple(selStr);
    } else if (status == 1) {
        switch (selection) {
            //��ҵȼ����а�
            case 5111:
                var text = " ��������������������< #e#r��  ��#k#n >����������������������#n\r\n";
                text += "   #r��ʾ��#bÿ���賿(ά��)1:00,ˢ�µȼ�ǰ20������ҡ�#k\r\n\r\n";
                var rankinfo_list = cm.getBossRankCountTop10("�ȼ�����");
                if (rankinfo_list != null) {
                    for (var i = 0; i < rankinfo_list.size(); i++) {
                        if (i == 20) {
                            break;
                        }
                        var info = rankinfo_list.get(i);
                        //��ʾ����
                        text += i == 0 ? "#r" : i == 1 ? "#b" : i == 2 ? "#d" : "";
                        text += "  \t\t\t\t#eTop." + (i + 1) + "#k#n\r\n";
                        //t�������
                        text += "  \t\t\t\t�������:#b" + cm.��ɫIDȡ����(info.getCid()) + "#k\r\n";
                        //t��ҵȼ�
                        text += "  \t\t\t\t��ҵȼ�:#b" + info.getCount() + "#k\r\n";
                        //t���ְҵ
                        text += "  \t\t\t\t���ְҵ:#b" + cm.ְҵ(info.getPoints()) + "#k\r\n";
                        //t��������
                        if (info.getCname() == 0) {
                            text += "  \t\t\t\t��������:#bû�м������#k\r\n";
                        } else {
                            text += "  \t\t\t\t��������:#b" + cm.��ȡ��������(info.getCname()) + "#k\r\n";
                        }
                        text += "\r\n";
                    }
                }
                text += " ����������������������������������������������������#n\r\n\r\n";
                cm.sendOkS(text, 3);
                cm.dispose();
                break;

                //ÿ��ǩ������
            case 1:
                var text = " ��������������������< #e#rǩ  ��#k#n >����������������������#n\r\n";
                text += "   #r��ʾ��#bÿ����Сzǩ���ļ�¼���а�#k\r\n\r\n";
                var rankinfo_list = cm.getBossRankCountTop("ǩ��");
                if (rankinfo_list != null) {
                    for (var i = 0; i < rankinfo_list.size(); i++) {
                        if (i == 20) {
                            break;
                        }
                        var info = rankinfo_list.get(i);

                        text += i == 0 ? "#r" : i == 1 ? "#b" : i == 2 ? "#b" : "";
                        text += "\tTop." + (i + 1) + "\t\t";
                        // ������ֿո�
                        text += info.getCname();
                        for (var j = 16 - info.getCname().getBytes().length; j > 0; j--) {
                            text += " ";
                        }
                        text += "\t\t#k#nǩ��#r #e" + info.getCount();
                        text += "#k#n ��\t\t#k";
                        text += "";
                    }
                }
                text += "\r\n\r\n\r\n\r\n ����������������������������������������������������#n\r\n\r\n";
                cm.sendOkS(text, 3);
                cm.dispose();
                break;
                //ս��������
            case 20:
                var text = " ��������������������< #e#rս����#k#n >����������������������#n\r\n";
                text += "   #r��ʾ��#bս�����Ǹ�������������Ե��ۺϼ���ġ�#k\r\n\r\n";
                var rankinfo_list = cm.getBossRankCountTop("ս����ͳ��");
                if (rankinfo_list != null) {
                    for (var i = 0; i < rankinfo_list.size(); i++) {
                        if (i == 20) {
                            break;
                        }
                        var info = rankinfo_list.get(i);
                        text += i == 0 ? "#r" : i == 1 ? "#b" : i == 2 ? "#b" : "";
                        text += "\tTop." + (i + 1) + ".\t\t";
                        // ������ֿո�
                        text += info.getCname();
                        for (var j = 16 - info.getCname().getBytes().length; j > 0; j--) {
                            text += " ";
                        }
                        text += "\t\tս����:" + info.getCount();
                        text += "\t#k";
                        text += "";
                    }
                }
                text += "\r\n\r\n\r\n\r\n ����������������������������������������������������#n\r\n\r\n";
                cm.sendOkS(text, 3);
                cm.dispose();
                break;
				
				

				
				
				
				case 51:
				this.db = Packages.database.DatabaseConnection.getConnection();
				//���ݿ��ɫ��ɱ�������ֶ� SG 
			var sql = "select name,SG,gender from characters where gm<=0 order by SG desc limit 10;";
			var pstmt = db.prepareStatement(sql);
			var list = pstmt.executeQuery();
			var text = "\t\t\t\t#e#d�� ɱ���������а� ��#k#n\r\n\r\n";
			text += "\t#e����#n\t#e����ǳ�#n\t\t  #e����#n\t\t#e�����ƺ�#n\r\n";
			for (var i = 1; i <= 10; i++) {
				if (!list.next()) {
					break;
				}
				if (i == 1) {
					text += "#r";
				} else if (i == 2) {
					text += "#g";
				} else if (i == 3) {
					text += "#b";
				}
				text += "\t " + i + "\t\t ";
				
				// ������ֿո�
				text += list.getString("name");
				for (var j = 16 - list.getString("name").getBytes().length; j > 0; j--) {
					text += " ";
				}

				// ����ɫ��ɱ�������ֶ�
				text += "\t " + list.getInt("SG");
				var famevalues = list.getInt("SG");
				var famelength = 0;
				while (famevalues > 0) {
					famevalues = Math.floor(famevalues/10);
					famelength += 1;
				}
				for (var j = 8 - famelength; j > 0; j--) {
					text += " ";
				}

				if (i == 1) {
					if (list.getInt("gender") == 0) {
						text += " ������ż���#k";
					} else {
						text += " ��ɱ��ħ�ʡ�#k";
					}
				} else if (i == 2) {
					text += " ��ɱ��ħ����#k";
				} else if (i == 3) {
					text += "��ɱ��ħ����#k";
				}
				text += "\r\n";
			}
			list.close();
			pstmt.close();
			cm.sendOkS(text, 3);
			cm.dispose();
				
				
			break;	
				
			
			case 52:
				this.db = Packages.database.DatabaseConnection.getConnection();
				//���ݿ��ɫ��ɱ�������ֶ� SG 
			var sql = "select name,��������,gender from characters where gm<=0 order by �������� desc limit 10;";
			var pstmt = db.prepareStatement(sql);
			var list = pstmt.executeQuery();
			var text = "\t\t\t\t\t#e#d�� �������а� ��#k#n\r\n\r\n";
			text += "\t#e����#n\t#e����ǳ�#n\t\t  #e����#n\t\t#e�����ƺ�#n\r\n";
			for (var i = 1; i <= 10; i++) {
				if (!list.next()) {
					break;
				}
				if (i == 1) {
					text += "#r";
				} else if (i == 2) {
					text += "#r";
				} else if (i == 3) {
					text += "#r";
				} else if (i >= 4) {
					text += "#b";
				}
				text += "\t " + i + "\t\t ";
				
				// ������ֿո�
				text += list.getString("name");
				for (var j = 16 - list.getString("name").getBytes().length; j > 0; j--) {
					text += " ";
				}

				// ����ɫ��ɱ�������ֶ�
				text += "\t " + list.getInt("��������");
				var famevalues = list.getInt("��������");
				var famelength = 0;
				while (famevalues > 0) {
					famevalues = Math.floor(famevalues/10);
					famelength += 1;
				}
				for (var j = 8 - famelength; j > 0; j--) {
					text += " ";
				}

				if (i == 1) {
					if (list.getInt("gender") == 0) {
						text += " ������ż���#k";
					} else {
						text += "������ھ���#k";
					}
				} else if (i == 2) {
					text += "�������ˡ�#k";
				} else if (i == 3) {
					text += "�����С�ס�#k";
				} else if (i >= 4) {
					text += "�����׳ա�#k";
				}
				text += "\r\n";
			}
			list.close();
			pstmt.close();
			cm.sendOkS(text, 3);
			cm.dispose();
				
				
			break;	


			
			case 53:
				this.db = Packages.database.DatabaseConnection.getConnection();
				//���ݿ��ɫ��ɱ�������ֶ� SG 
			var sql = "select name,�������,gender from characters where gm<=0 order by ������� desc limit 10;";
			var pstmt = db.prepareStatement(sql);
			var list = pstmt.executeQuery();
			var text = "\t\t\t\t\t#e#d�� ����������а� ��#k#n\r\n\r\n";
			text += "\t#e����#n\t#e����ǳ�#n\t\t  #e����#n\t\t#e�����ƺ�#n\r\n";
			for (var i = 1; i <= 10; i++) {
				if (!list.next()) {
					break;
				}
				if (i == 1) {
					text += "#r";
				} else if (i == 2) {
					text += "#r";
				} else if (i == 3) {
					text += "#r";
				} else if (i >= 4) {
					text += "#b";
				}
				text += "\t " + i + "\t\t ";
				
				// ������ֿո�
				text += list.getString("name");
				for (var j = 16 - list.getString("name").getBytes().length; j > 0; j--) {
					text += " ";
				}

				// ����ɫ��ɱ�������ֶ�
				text += "\t " + list.getInt("�������");
				var famevalues = list.getInt("�������");
				var famelength = 0;
				while (famevalues > 0) {
					famevalues = Math.floor(famevalues/10);
					famelength += 1;
				}
				for (var j = 8 - famelength; j > 0; j--) {
					text += " ";
				}

				if (i == 1) {
					if (list.getInt("gender") == 0) {
						text += " ������ż���#k";
					} else {
						text += "������ż���#k";
					}
				} else if (i == 2) {
					text += "��������ˡ�#k";
				} else if (i == 3) {
					text += "���������֡�#k";
				} else if (i >= 4) {
					text += "������׳ա�#k";
				}
				text += "\r\n";
			}
			list.close();
			pstmt.close();
			cm.sendOkS(text, 3);
			cm.dispose();
				
				
			break;	

			
			
				

			case 4:
				selStr = "\t\t\t#e#r< �� �� �� �� �� �� �� >#k#n\r\n\r\n";
				selStr += ""+cm.�����������а�()+"#k\r\n\r\n";
                cm.sendOkS(selStr,3);
				cm.dispose();
                break;  

			case 6:
				selStr = "\t\t\t\t#e#r< �� �� �� �� �� >#k#n\r\n\r\n";
				selStr += "   �������۷��������а񣬶��ǵ��Ͼ��г���ʵ�����ˡ�#k\r\n\r\n";
				selStr += ""+cm.�������а�()+"\r\n";
                cm.sendOkS(selStr,3);
				cm.dispose();
                break;


            case 1000:
                cm.GainPiot("���˰񿪹�", "1", -���˰񿪹�);
                cm.GainPiot("���˰񿪹�", "1", 1);
                cm.dispose();
                cm.openNpc(9900004, 7);
                break;
            case 1001:
                cm.GainPiot("���˰񿪹�", "1", -���˰񿪹�);
                cm.dispose();
                cm.openNpc(9900004, 7);
                break
            case 21:
                cm.dispose();
                cm.openNpc(9040004, 1);
                break;
            case 22:
                cm.dispose();
                cm.openNpc(9040004, 2);
                break;
            case 666666:
                cm.dispose();
                cm.openNpc(9900004, 0);
                break;
            case 0:
                cm.displayGuildRanks();
                cm.dispose();
                break;
            case 9:
                cm.MapleMSpvpdeaths();
                cm.dispose();
                break;
            case 25:
                cm.�������а�();
                cm.dispose();
                break;
            case 26:
                cm.ɱ�����а�();
                cm.dispose();
			case 27:
                cm.ս�������а�();
                cm.dispose();
                break;

			case 28:
                cm.����������а�();
                cm.dispose();
                break;
            case 19:
                cm.�������а�();
                cm.dispose();
                break;
            case 23:
                cm.�������а�();
                cm.dispose();
                break;
            case 13:
                cm.������ʱ�����а�();
                cm.dispose();
                break;
            case 10:
                cm.MapleMSpvpkills();
                cm.dispose();
                break;
            case 5:
                cm.showlvl();
                cm.dispose()
                break;

            case 2:
                cm.showmeso();
                cm.dispose();
                break;
        }
    }
}
function getname(id) {
    var con1 = DatabaseConnection.getConnection();
    ps1 = con1.prepareStatement("SELECT name FROM characters WHERE id = ?");
    ps1.setInt(1, id);
    var rs1 = ps1.executeQuery();
    var name;
    if (rs1.next()) {
        name = rs1.getString("name");
    } else {
        name = "����";
    }
    rs1.close();
    ps1.close();
    return name;
}


 
