/*
�ʺ�QILIN�����
QQ 1500663066
*/
var ���� = "#fItem/Cash/0502/05021001/info/iconRaw#";  //
var ���ڽ����� = "#fUI/UIWindow/Quest/Tab/enabled/1#";
var ��� = "#fUI/UIWindow/Quest/Tab/enabled/2#";
var ���ڽ������� = "#fUI/UIWindow/MonsterCarnival/icon1#";
var ��ɺ� = "#fUI/UIWindow/MonsterCarnival/icon0#";
var ���� = "#fEffect/CharacterEff/1022223/4/0#";
var ��ɫ��ͷ = "#fUI/UIWindow/Quest/icon6/7#"
var ������ = "#fUI/UIWindow/Quest/icon3/6#";
var ��ɫ��ͷ = "#fUI/UIWindow/Quest/icon2/7#";
var ��ɫ�ǵ� = "#fUI/UIWindow.img/PvP/Scroll/enabled/next2#";
var ���ڽ����� = "#fUI/UIWindow/Quest/Tab/enabled/1#";
var ��� = "#fUI/UIWindow/Quest/Tab/enabled/2#";
var ���ڽ������� = "#fUI/UIWindow/MonsterCarnival/icon1#";
var ��ɺ� = "#fUI/UIWindow/MonsterCarnival/icon0#";
var ���� = "#fEffect/CharacterEff/1051295/0/0#";
var �ٷ� = "#fEffect/CharacterEff/1003252/0/0#";
var Сѩ�� = "#fEffect/CharacterEff/1003393/0/0#";
var ���� = "#fEffect/CharacterEff/1032063/0/0#";
var ��̾�� = "#fUI/UIWindow/Quest/icon0#";
var ��֮��½ = "#fUI/Login/WorldSelect/BtGateway/0#";
var �ƶ�������NPC�� = "#fUI/UIWindow/Quest/BtGotoNpc/normal/0#";
var ������� = "#fUI/UIWindow/Quest/summary#";
var ���� = "#fUI/UIWindow/Quest/reward#";
var ������� = "#fUI/GuildBBS/GuildBBS/Emoticon/Basic/2#";
var С��� = "#fUI/UIWindow.img/Item/BtCoin/normal/0#";
var ��ȯͼ�� = "#fUI/CashShop/CashItem/0#";
var ����ֵ = "#fUI/UIWindow/QuestIcon/8/0#";
var ��ȯ = "#fUI/CashShop/CashItem/0#";
var ��ͷ = "#fUI/Basic/BtHide3/mouseOver/0#";
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
            var tex2 = "";
            var text = "";
            for (i = 0; i < 10; i++) {
                text += "";
            }
			text += "\t\t"+�������+" #e �� �� �� �� �� �� �� �� #n "+�������+"\r\n\r\n"
			
			if(cm.getPlayer().getOneTimeLog("��������") == 0){
					text += "\t"+���ڽ�����+"#L1#�������������һ��(#r�ɿ�ʼ#k)#n#l\r\n\r\n"//3
					//text += "\t#L27#"+��ͷ+"����ͨ��(#z4000002#+100#z4000017#+100)��Ҫ200��ȯ#n#l\r\n\r\n"
				} else if(cm.getPlayer().getOneTimeLog("��������") > 0){
					text += "\t\t\t\t   �������������һ��#n#l"+���+"#k\r\n"//3
			}
			
			if(cm.getPlayer().getOneTimeLog("��������") == 1 && cm.getLevel() > 9){
					text += "\t"+���ڽ�����+"#L2#������������ڶ���(#r�ɿ�ʼ#k)#n#l\r\n\r\n"//3
					//text += "\t#L28#"+��ͷ+"����ͨ��(#z4000015#+100#z4000008#+100)��Ҫ300��ȯ#n#l\r\n\r\n"
				} else if(cm.getPlayer().getOneTimeLog("��������") > 1 && cm.getLevel() > 9){
					text += "\t\t\t\t   ������������ڶ���#n#l"+���+"#k\r\n"//3
				} else {
					text += "\t\t\t(#rlv.10#k)������������ڶ���#l\r\n"//3
			}
			
			if(cm.getPlayer().getOneTimeLog("��������") == 2 && cm.getLevel() > 19){
					text += "\t"+���ڽ�����+"#L3#�����������������(#r�ɿ�ʼ#k)#n#l\r\n\r\n"//3
					//text += "\t#L29#"+��ͷ+"����ͨ��(#z4170000#+10)��Ҫ400��ȯ#n#l\r\n\r\n"
				} else if(cm.getPlayer().getOneTimeLog("��������") > 2 && cm.getLevel() > 19){
					text += "\t\t\t\t   �����������������#n#l"+���+"#k\r\n"//3
				} else {
					text += "\t\t\t(#rlv.20#k)�����������������#l\r\n"//3
			}
			
			if(cm.getPlayer().getOneTimeLog("��������") == 3 && cm.getLevel() > 29){
					text += "\t"+���ڽ�����+"#L4#��������������Ĺ�(#r�ɿ�ʼ#k)#n#l\r\n\r\n"//3
					//text += "\t#L30#"+��ͷ+"����ͨ��(#z4000106#+150#z4000107#+150)��Ҫ500��ȯ#n#l\r\n\r\n"
				} else if(cm.getPlayer().getOneTimeLog("��������") > 3 && cm.getLevel() > 29){
					text += "\t\t\t\t   ��������������Ĺ�#n#l"+���+"#k\r\n"//3
				} else {
					text += "\t\t\t(#rlv.30#k)��������������Ĺ�#l\r\n"//3
			}
			
			if(cm.getPlayer().getOneTimeLog("��������") == 4 && cm.getLevel() > 39){
					text += "\t"+���ڽ�����+"#L5#����������������(#r�ɿ�ʼ#k)#n#l\r\n\r\n"//3
					//text += "\t#L31#"+��ͷ+"����ͨ��(#z4000170#+150#z4000169#+150)��Ҫ600��ȯ#n#l\r\n\r\n"
				} else if(cm.getPlayer().getOneTimeLog("��������") > 4 && cm.getLevel() > 39){
					text += "\t\t\t\t   ����������������#n#l"+���+"#k\r\n"//3
				} else {
					text += "\t\t\t(#rlv.40#k)����������������#l\r\n"//3
			}
			
			if(cm.getPlayer().getOneTimeLog("��������") == 5 && cm.getLevel() > 49){
					text += "\t"+���ڽ�����+"#L6#�����������������(#r�ɿ�ʼ#k)#n#l\r\n\r\n"//3
					//text += "\t#L32#"+��ͷ+"����ͨ��(#z4000276#+200#z4000277#+200)��Ҫ700��ȯ#n#l\r\n\r\n"
				} else if(cm.getPlayer().getOneTimeLog("��������") > 5 && cm.getLevel() > 49){
					text += "\t\t\t\t   �����������������#n#l"+���+"#k\r\n"//3
				} else {
					text += "\t\t\t(#rlv.50#k)�����������������#l\r\n"//3
			}
			
			
			if(cm.getPlayer().getOneTimeLog("��������") == 6 && cm.getLevel() > 59){
					text += "\t"+���ڽ�����+"#L7#��������������߹�(#r�ɿ�ʼ#k)#n#l\r\n\r\n"//3
					//text += "\t#L33#"+��ͷ+"����ͨ��(#z4000115#+400)��Ҫ800��ȯ#n#l\r\n\r\n"
				} else if(cm.getPlayer().getOneTimeLog("��������") > 6 && cm.getLevel() > 59){
					text += "\t\t\t\t   ��������������߹�#n#l"+���+"#k\r\n"//3
				} else {
					text += "\t\t\t(#rlv.60#k)��������������߹�#l\r\n"//3
			}
			if(cm.getPlayer().getOneTimeLog("��������") == 7 && cm.getLevel() > 69){
					text += "\t"+���ڽ�����+"#L8#������������ڰ˹�(#r�ɿ�ʼ#k)#n#l\r\n\r\n"//3
					//text += "\t#L34#"+��ͷ+"����ͨ��(#z4000088#+200#z4000086#+200)��Ҫ900��ȯ#n#l\r\n\r\n"
				} else if(cm.getPlayer().getOneTimeLog("��������") > 7 && cm.getLevel() > 69){
					text += "\t\t\t\t   ������������ڰ˹�#n#l"+���+"#k\r\n"//3
				} else {
					text += "\t\t\t(#rlv.70#k)������������ڰ˹�#l\r\n"//3
			}
			
			
			if(cm.getPlayer().getOneTimeLog("��������") == 8 && cm.getLevel() > 79){
					text += "\t"+���ڽ�����+"#L9#������������ھŹ�(#r�ɿ�ʼ#k)#n#l\r\n\r\n"//3
					//text += "\t#L35#"+��ͷ+"����ͨ��(#z4000177#+300#z4000025#+300)��Ҫ1000��ȯ#n#l\r\n\r\n"
				} else if(cm.getPlayer().getOneTimeLog("��������") > 8 && cm.getLevel() > 79){
					text += "\t\t\t\t   ������������ھŹ�#n#l"+���+"#k\r\n"//3
				} else {
					text += "\t\t\t(#rlv.80#k)������������ھŹ�#l\r\n"//3
			}
			
			if(cm.getPlayer().getOneTimeLog("��������") == 9 && cm.getLevel() > 89){
					text += "\t"+���ڽ�����+"#L10#�������������ʮ��(#r�ɿ�ʼ#k)#n#l\r\n\r\n"//3
					//text += "\t#L36#"+��ͷ+"����ͨ��(#z4000289#+300#z4000299#+300)��Ҫ1100��ȯ#n#l\r\n\r\n"
				} else if(cm.getPlayer().getOneTimeLog("��������") > 9 && cm.getLevel() > 89){
					text += "\t\t\t\t   �������������ʮ��#n#l"+���+"#k\r\n"//3
				} else {
					text += "\t\t\t(#rlv.90#k)�������������ʮ��#l\r\n"//3
			}
			
			if(cm.getPlayer().getOneTimeLog("��������") == 10 && cm.getLevel() > 99){
					text += "\t"+���ڽ�����+"#L11#�������������ʮһ��(#r�ɿ�ʼ#k)#n#l\r\n\r\n"
					//text += "\t#L37#"+��ͷ+"����ͨ��(#z4000032#+300#z4000034#+300)��Ҫ1200��ȯ#n#l\r\n\r\n"
				} else if(cm.getPlayer().getOneTimeLog("��������") > 10 && cm.getLevel() > 99){
					text += "\t\t\t\t   �������������ʮһ��#n#l"+���+"#k\r\n"//3
				} else {
					text += "\t\t\t(#rlv.100#k)�������������ʮһ��#l\r\n"//3
			}
			
			if(cm.getPlayer().getOneTimeLog("��������") == 11 && cm.getLevel() > 109){
					text += "\t"+���ڽ�����+"#L12#�������������ʮ����(#r�ɿ�ʼ#k)#n#l\r\n\r\n"
					//text += "\t#L38#"+��ͷ+"����ͨ��(#z4000040#+16#z4000176#+16)��Ҫ1300��ȯ#n#l\r\n\r\n"
				} else if(cm.getPlayer().getOneTimeLog("��������") > 11 && cm.getLevel() > 109){
					text += "\t\t\t\t   �������������ʮ����#n#l"+���+"#k\r\n"//3
				} else {
					text += "\t\t\t(#rlv.110#k)�������������ʮ����#l\r\n"//3
			}
			
			if(cm.getPlayer().getOneTimeLog("��������") == 12 && cm.getLevel() > 119){
					text += "\t"+���ڽ�����+"#L13#�������������ʮ����(#r�ɿ�ʼ#k)#n#l\r\n\r\n"//3
					//text += "\t#L39#"+��ͷ+"����ͨ��(#z4000020#+300#z4000178#+300)��Ҫ1400��ȯ#n#l\r\n\r\n"
				} else if(cm.getPlayer().getOneTimeLog("��������") > 12 && cm.getLevel() > 119){
					text += "\t\t\t\t   �������������ʮ����#n#l"+���+"#k\r\n"//3
				} else {
					text += "\t\t\t(#rlv.120#k)�������������ʮ����#l\r\n"//3
			}

			if(cm.getPlayer().getOneTimeLog("��������") == 13 && cm.getLevel() > 129){
				text += "\t"+���ڽ�����+"#L14#�������������ʮ�Ĺ�(#r�ɿ�ʼ#k)#n#l\r\n\r\n"//3
				//text += "\t#L40#"+��ͷ+"����ͨ��(#z4000023#+300#z4000030#+300)��Ҫ1500��ȯ#n#l\r\n\r\n"
			} else if(cm.getPlayer().getOneTimeLog("��������") > 13 && cm.getLevel() > 129){
				text += "\t\t\t\t   �������������ʮ�Ĺ�#n#l"+���+"#k\r\n"//3
			} else {
				text += "\t\t\t(#rlv.130#k)�������������ʮ�Ĺ�#l\r\n"//3
			}

			if(cm.getPlayer().getOneTimeLog("��������") == 14 && cm.getLevel() > 139){
				text += "\t"+���ڽ�����+"#L15#�������������ʮ���(#r�ɿ�ʼ#k)#n#l\r\n\r\n"//3
				//text += "\t#L41#"+��ͷ+"����ͨ��(#z4000206#+350#z4000207#+350)��Ҫ1600��ȯ#n#l\r\n\r\n"
			} else if(cm.getPlayer().getOneTimeLog("��������") > 14 && cm.getLevel() > 139){
				text += "\t\t\t\t   �������������ʮ���#n#l"+���+"#k\r\n"//3
			} else {
				text += "\t\t\t(#rlv.140#k)�������������ʮ���#l\r\n"//3
			}

			if(cm.getPlayer().getOneTimeLog("��������") == 15 && cm.getLevel() > 149){
				text += "\t"+���ڽ�����+"#L16#�������������ʮ����(#r�ɿ�ʼ#k)#n#l\r\n\r\n"//3
				//text += "\t#L42#"+��ͷ+"����ͨ��(#z4000029#+400#z4000031#+400)��Ҫ1700��ȯ#n#l\r\n\r\n"
			} else if(cm.getPlayer().getOneTimeLog("��������") > 15 && cm.getLevel() > 149){
				text += "\t\t\t\t   �������������ʮ����#n#l"+���+"#k\r\n"//3
			} else {
				text += "\t\t\t(#rlv.150#k)�������������ʮ����#l\r\n"//3
			}

			if(cm.getPlayer().getOneTimeLog("��������") == 16 && cm.getLevel() > 159){
				text += "\t"+���ڽ�����+"#L17#�������������ʮ�߹�(#r�ɿ�ʼ#k)#n#l\r\n\r\n"//3
				//text += "\t#L43#"+��ͷ+"����ͨ��(#z4000070#+220#z4000071#+220#z4000072#+220)��Ҫ1800��ȯ#n#l\r\n\r\n"
			} else if(cm.getPlayer().getOneTimeLog("��������") > 16 && cm.getLevel() > 159){
				text += "\t\t\t\t   �������������ʮ�߹�#n#l"+���+"#k\r\n"//3
			} else {
				text += "\t\t\t(#rlv.160#k)�������������ʮ�߹�#l\r\n"//3
			}

			if(cm.getPlayer().getOneTimeLog("��������") == 17 && cm.getLevel() > 169){
				text += "\t"+���ڽ�����+"#L18#�������������ʮ�˹�(#r�ɿ�ʼ#k)#n#l\r\n\r\n"//3
				//text += "\t#L44#"+��ͷ+"����ͨ��(#z4000060#+450#z4000061#+450)��Ҫ1900��ȯ#n#l\r\n\r\n"
			} else if(cm.getPlayer().getOneTimeLog("��������") > 17 && cm.getLevel() > 169){
				text += "\t\t\t\t   �������������ʮ�˹�#n#l"+���+"#k\r\n"//3
			} else {
				text += "\t\t\t(#rlv.170#k)�������������ʮ�˹�#l\r\n"//3
			}

			if(cm.getPlayer().getOneTimeLog("��������") == 18 && cm.getLevel() > 179){
				text += "\t"+���ڽ�����+"#L19#�������������ʮ�Ź�(#r�ɿ�ʼ#k)#n#l\r\n\r\n"//3
				//text += "\t#L45#"+��ͷ+"����ͨ��(#z4000051#+450#z4000052#+450)��Ҫ2000��ȯ#n#l\r\n\r\n"
			} else if(cm.getPlayer().getOneTimeLog("��������") > 18 && cm.getLevel() > 179){
				text += "\t\t\t\t   �������������ʮ�Ź�#n#l"+���+"#k\r\n"//3
			} else {
				text += "\t\t\t(#rlv.180#k)�������������ʮ�Ź�#l\r\n"//3
			}
			
			if(cm.getPlayer().getOneTimeLog("��������") == 19 && cm.getLevel() > 189){
				text += "\t"+���ڽ�����+"#L20#������������ڶ�ʮ��(#r�ɿ�ʼ#k)#n#l\r\n\r\n"//3
				//text += "\t#L46#"+��ͷ+"����ͨ��(#z4000049#+200#z4000050#+200)��Ҫ2100��ȯ#n#l\r\n\r\n"
			} else if(cm.getPlayer().getOneTimeLog("��������") > 19 && cm.getLevel() > 189){
				text += "\t\t\t\t   ������������ڶ�ʮ��#n#l"+���+"#k\r\n"//3
			} else {
				text += "\t\t\t(#rlv.190#k)������������ڶ�ʮ��#l\r\n"//3
			}

			if(cm.getPlayer().getOneTimeLog("��������") == 20 && cm.getLevel() > 199){
				text += "\t"+���ڽ�����+"#L21#������������ڶ�ʮһ��(#r�ɿ�ʼ#k)#n#l\r\n\r\n"//3
				//text += "\t#L47#"+��ͷ+"����ͨ��(#z4000069#+450#z4000076#+450)��Ҫ2200��ȯ#n#l\r\n\r\n"
			} else if(cm.getPlayer().getOneTimeLog("��������") > 20 && cm.getLevel() > 199){
				text += "\t\t\t\t   ������������ڶ�ʮһ��#n#l"+���+"#k\r\n"//3
			} else {
				text += "\t\t\t(#rlv.200#k)������������ڶ�ʮһ��#l\r\n"//3
			}

			if(cm.getPlayer().getOneTimeLog("��������") == 21 && cm.getLevel() > 209){
				text += "\t"+���ڽ�����+"#L22#������������ڶ�ʮ����(#r�ɿ�ʼ#k)#n#l\r\n\r\n"//3
				//text += "\t#L48#"+��ͷ+"����ͨ��(#z4000284#+500#z4000285#+500)��Ҫ2300��ȯ#n#l\r\n\r\n"
			} else if(cm.getPlayer().getOneTimeLog("��������") > 21 && cm.getLevel() > 209){
				text += "\t\t\t\t   ������������ڶ�ʮ����#n#l"+���+"#k\r\n"//3
			} else {
				text += "\t\t\t(#rlv.210#k)������������ڶ�ʮ����#l\r\n"//3
			}

			if(cm.getPlayer().getOneTimeLog("��������") == 22 && cm.getLevel() > 219){
				text += "\t"+���ڽ�����+"#L23#������������ڶ�ʮ����(#r�ɿ�ʼ#k)#n#l\r\n\r\n"//3
				//text += "\t#L49#"+��ͷ+"����ͨ��(#z4000171#+600#z4000172#+600)��Ҫ2400��ȯ#n#l\r\n\r\n"
			} else if(cm.getPlayer().getOneTimeLog("��������") > 22 && cm.getLevel() > 219){
				text += "\t\t\t\t   ������������ڶ�ʮ����#n#l"+���+"#k\r\n"//3
			} else {
				text += "\t\t\t(#rlv.220#k)������������ڶ�ʮ����#l\r\n"//3
			}

			if(cm.getPlayer().getOneTimeLog("��������") == 23 && cm.getLevel() > 229){
				text += "\t"+���ڽ�����+"#L24#������������ڶ�ʮ�Ĺ�(#r�ɿ�ʼ#k)#n#l\r\n\r\n"//3
				//text += "\t#L50#"+��ͷ+"����ͨ��(#z4000160#+600#z4000161#+600)��Ҫ2500��ȯ#n#l\r\n\r\n"
			} else if(cm.getPlayer().getOneTimeLog("��������") > 23 && cm.getLevel() > 229){
				text += "\t\t\t\t   ������������ڶ�ʮ�Ĺ�#n#l"+���+"#k\r\n"//3
			} else {
				text += "\t\t\t(#rlv.230#k)������������ڶ�ʮ�Ĺ�#l\r\n"//3
			}

			if(cm.getPlayer().getOneTimeLog("��������") == 24 && cm.getLevel() > 239){
				text += "\t"+���ڽ�����+"#L25#������������ڶ�ʮ���(#r�ɿ�ʼ#k)#n#l\r\n\r\n"//3
				//text += "\t#L51#"+��ͷ+"����ͨ��(#z4000118#+700#z4000119#+700)��Ҫ2600��ȯ#n#l\r\n\r\n"
			} else if(cm.getPlayer().getOneTimeLog("��������") > 24 && cm.getLevel() > 239){
				text += "\t\t\t\t   ������������ڶ�ʮ���#n#l"+���+"#k\r\n"//3
			} else {
				text += "\t\t\t(#rlv.240#k)������������ڶ�ʮ���#l\r\n"//3
			}

			if(cm.getPlayer().getOneTimeLog("��������") == 25 && cm.getLevel() > 249){
				text += "\t"+���ڽ�����+"#L26#������������ڶ�ʮ����(#r�ɿ�ʼ#k)#n#l\r\n\r\n"//3
				//text += "\t#L52#"+��ͷ+"����ͨ��(ȫ������+50)��Ҫ8888��ȯ#n#l\r\n\r\n"
			} else if(cm.getPlayer().getOneTimeLog("��������") > 25 && cm.getLevel() > 249){
				text += "\t\t\t\t   ������������ڶ�ʮ����#n#l"+���+"#k\r\n"//3
				text += "#r\t\t\t\t   ��ϲ��������о�����������!#n#l#k\r\n"//3
				text += "#r\t\t\t\t\t\t\t�������#n#l#k\r\n"//3
			} else {
				text += "\t\t\t(#rlv.250#k)������������ڶ�ʮ����#l\r\n"//3
			}
	
            cm.sendSimple(text);
			}else if (selection == 27) {
			if (cm.getPlayer().getCSPoints(1) > 200){
				if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("�����ռ䲻��2��");
			cm.dispose();
			return;
		}
				cm.gainNX(-200);//�Ӽ���ȯ
                cm.gainItem(4000002,100);
				cm.gainItem(4000017,100);
                cm.dispose();
			}else{
                cm.sendOk("200��ȯ���㣬���򲻵�Ŷ��");
                cm.dispose();
			}
        } else if (selection == 1) {
			if (cm.haveItem(4000002,100) && cm.haveItem(4000017,100)){
				if (!cm.checkNumSpace(0, 3)) {
			cm.sendOk("�����ռ䲻��3��");
			cm.dispose();
			return;
		}
				cm.gainItem(4000002, -100);//��Ʒ
				cm.gainItem(4000017, -100);//��Ʒ
				cm.gainMeso(+40000); //�Ӽ����
				cm.gainItem(4250200,5);//�����Ʒʯ��ʯ�µ�
				cm.gainItem(4250400,5);//�����Ʒ��ˮ���µ�
				cm.gainItem(1142001,1);//�����Ʒ��ӿ���ѫ��
				cm.gainExp(5000);//����ֵ
				//cm.gainDY(400);//�����˵��þ�
				cm.worldMessage(6,"��ϲ���:["+cm.getName()+"]����˾������������һ�أ���ý���:����ֵ,���,���ߵȽ�Ʒ�������!");
				cm.getPlayer().setOneTimeLog("��������");
				cm.sendOk("��ϲ������˾������������һ��,����ڶ��ؽ���Ҳ�Ǻܷ��Ŷ��");
				cm.dispose();
		}else{

			cm.sendOk(""+�������+"\r\n���ǵó�������ð�յ���ʱ����?���ǴӲʺ絺һ��һ�εĹ���,����������Ժ�,���ǲ���ǰ��,������һ����#r(��ĺ���)#k�ĵط�,���㵽����Ѱ����һƬ����!\r\n#e�ռ�����:#k#n\r\n#v4000002#[#c4000002#]/100��#v4000017#[#c4000017#]/100��\r\n"+����+"\r\n"+����ֵ+"5000  \r\n"+С���+" ���ֵ*40000\r\n #v4250200#*5 #v4250400#*5 #v1142001#*1");
			cm.dispose();
			}
		}else if (selection == 28) {
			if (cm.getPlayer().getCSPoints(1) > 300){
				if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("�����ռ䲻��2��");
			cm.dispose();
			return;
		}
				cm.gainNX(-300);//�Ӽ���ȯ
                cm.gainItem(4000015,100);
				cm.gainItem(4000008,100);
                cm.dispose();
			}else{
                cm.sendOk("300��ȯ���㣬���򲻵�Ŷ��");
                cm.dispose();
			}
        } else if (selection == 2) {
		if (cm.haveItem(4000015,100) && cm.haveItem(4000008,100)){
			if (!cm.checkNumSpace(0, 5)) {
			cm.sendOk("�����ռ䲻��5��");
			cm.dispose();
			return;
		}
			cm.gainItem(4000015, -100);//������Ʒ
			cm.gainItem(4000008, -100);//������Ʒ
			cm.gainMeso(+60000); //�Ӽ����
			cm.gainItem(4250500,5);//�����Ʒ������ʯ�µ�
			cm.gainItem(1112444,1);//�����Ʒ�������ָ
			cm.gainItem(4310150,5);//�����Ʒ��ҵ��
			cm.gainExp(cm.getLevel()*20000);//����ֵ
			cm.gainDY(300);//�����˵��þ�
			cm.getPlayer().setOneTimeLog("��������");
			cm.worldMessage(6,"���:["+cm.getName()+"]����˾�����������ڶ���,��ý���:����ֵ,���þ�,���,���ߵȽ�Ʒ�������!");
			cm.sendOk("��ϲ������˾�����������ڶ��أ���������ؽ���Ҳ�Ǻܷ��Ŷ��");
			cm.dispose();
		    }else{

			cm.sendOk(""+�������+"\r\n����ĳ���,������ͣ����������,���Ե��ô��ż���!\r\n�뵽һ����#r�����϶���#k�ĵط�\r\n#e�ռ�����:#k#n\r\n#v4000015#[#c4000015#]100��#v4000008#[#c4000008#]100��\r\n"+����+"\r\n"+����ֵ+"*20000  (���ŵȼ�����Exp)\r\n"+��ȯ+" ���þ�*300\r\n"+С���+" ���ֵ*60000\r\n #v4250500#*5 #v4310150#*5 #v1112444#*1");
			cm.dispose();
	    }
		}else if (selection == 29) {
			if (cm.getPlayer().getCSPoints(1) > 400){
				if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("�����ռ䲻��2��");
			cm.dispose();
			return;
		}
				cm.gainNX(-400);//�Ӽ���ȯ
                cm.gainItem(4170000,10);
                cm.dispose();
			}else{
                cm.sendOk("400��ȯ���㣬���򲻵�Ŷ��");
                cm.dispose();
			}
        } else if (selection == 3) {
		if (cm.haveItem(4170000,10)){
			if (!cm.checkNumSpace(0, 5)) {
			cm.sendOk("�����ռ䲻��5��");
			cm.dispose();
			return;
		}
			
			cm.gainItem(4170000,-10);
			cm.gainMeso(+80000); //�Ӽ����
			cm.gainItem(4250700,5);//�����Ʒ��ĸ���µ�
			cm.gainItem(4250300,5);//�����Ʒ����ʯ�µ�
			cm.gainItem(1112742,1);//�����Ʒ�Ͻ��Ҷ��ָ
			cm.gainItem(1050018,1);//��ɫɣ�÷�
			cm.gainExp(cm.getLevel()*20000);//����ֵ
			cm.gainDY(350);//�����˵��þ�
			cm.getPlayer().setOneTimeLog("��������");
			cm.worldMessage(6,"���:["+cm.getName()+"]����˾����������������,��ý���:����ֵ,���þ�,���,���ߵȽ�Ʒ�������!");
			cm.sendOk("��ϲ������˾���������������أ�������Ĺؽ���Ҳ�Ǻܷ��Ŷ��");
			cm.dispose();	
		}else{

		 cm.sendOk(""+�������+"\r\n�㻹�ǵ�һ������Ѵ򸱱�������?\r\n�뵽һ����#r��������Ӹ������ĵط�#k\r\n#e�ռ����ϣ�#k#n\r\n#v4170000#[#c4170000#]10��\r\n"+����+"\r\n"+����ֵ+"*20000  (���ŵȼ�����Exp)\r\n"+��ȯ+" ���þ�*350\r\n"+С���+" ���ֵ*80000\r\n #v4250700#*5 #v4250300#*5 #v1112742#*1 #v1050018#*1");
		 cm.dispose();
		
	    }
		}else if (selection == 30) {
			if (cm.getPlayer().getCSPoints(1) > 500){
				if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("�����ռ䲻��2��");
			cm.dispose();
			return;
		}
				cm.gainNX(-500);//�Ӽ���ȯ
                cm.gainItem(4000106,150);
				cm.gainItem(4000107,150);
                cm.dispose();
			}else{
                cm.sendOk("500��ȯ���㣬���򲻵�Ŷ��");
                cm.dispose();
		}
        } else if (selection == 4) {
			
		if (cm.haveItem(4000106,150) && cm.haveItem(4000107,150)){
			if (!cm.checkNumSpace(0, 5)) {
			cm.sendOk("�����ռ䲻��5��");
			cm.dispose();
			return;
		}
			cm.gainItem(4000106, -150);//�����Ʒ
			cm.gainItem(4000107, -150);//�����Ʒ
			cm.gainMeso(+100000); //�Ӽ����
			cm.gainItem(4250100,5);//�����Ʒ����ʯ�µ�
			cm.gainItem(4250600,5);//�����Ʒ�ƾ��µ�
			cm.gainItem(1132000,1);//�����Ʒ��ɫ����
			cm.gainItem(4001126,10);//�����Ʒ��Ҷ
			cm.gainExp(cm.getLevel()*20000);//����ֵ
			cm.gainDY(400);//�����˵��þ�
			cm.getPlayer().setOneTimeLog("��������");
			cm.worldMessage(6,"���:["+cm.getName()+"]����˾�������������Ĺ�,��ý���:����ֵ,���þ�,���,���ߵȽ�Ʒ�������!");
			cm.sendOk("��ϲ������˾�������������Ĺأ��������ؽ���Ҳ�Ǻܷ��Ŷ��");
			cm.dispose();
		}else{
			cm.sendOk(""+�������+"\r\n�㻹�ǵ�����߳�һ�����������?\r\n�뵽һ����#r��¶̨�������ĵط�#k\r\n#e�ռ����ϣ�#k#n\r\n#v4000106#[#c4000106#]150��\r\n#v4000107#[#c4000107#]150��\r\n"+����+"\r\n"+����ֵ+"*20000  (���ŵȼ�����Exp)\r\n"+��ȯ+" ���þ�*1000\r\n"+С���+" ���ֵ*100000\r\n #v4250100#*5 #v4250600#*5\r\n #v1132000#*1  ����:#v4001126#*10");
			cm.dispose();
	    }
		}else if (selection == 31) {
			if (cm.getPlayer().getCSPoints(1) > 600){
				if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("�����ռ䲻��2��");
			cm.dispose();
			return;
		}
				cm.gainNX(-600);//�Ӽ���ȯ
                cm.gainItem(4000170,150);
				cm.gainItem(4000169,150);
                cm.dispose();
			}else{
                cm.sendOk("600��ȯ���㣬���򲻵�Ŷ��");
                cm.dispose();
		}
        } else if (selection == 5) {
			
		if (cm.haveItem(4000170,150) && cm.haveItem(4000169,150)){
			if (!cm.checkNumSpace(0, 5)) {
			cm.sendOk("�����ռ䲻��5��");
			cm.dispose();
			return;
		}
			cm.gainItem(4000170, -150);//�����Ʒ
			cm.gainItem(4000169, -150);//�����Ʒ
			cm.gainItem(4250000,5);//�����Ʒ��ʯ�µ�
			cm.gainItem(4251300,5);//�����Ʒ��ˮ���µ�
			cm.gainItem(4000463,5);//�����Ʒ��������
			cm.gainItem(1112743,1);//�����Ʒר���Ͻ��Ҷ��ָ
			cm.gainMeso(+120000); //�Ӽ����
			cm.gainExp(cm.getLevel()*20000);//����ֵ
			cm.gainDY(450);//�����˵��þ�
			cm.getPlayer().setOneTimeLog("��������");
			cm.worldMessage(6,"���:["+cm.getName()+"]����˾���������������,��ý���:����ֵ,���þ�,���,���ߵȽ�Ʒ�������!");
			cm.sendOk("��ϲ������˾��������������أ���������ؽ���Ҳ�Ǻܷ��Ŷ��");
			cm.dispose();
		}else{
			cm.sendOk(""+�������+"\r\n����߼���,�Ƿ�Ҳ���Ϊ������!\r\n�뵽һ����#r��ͯ����-��ɽ����ռ����ĵط�#k\r\n#e�ռ����ϣ�#k#n\r\n#v4000170#[#c4000170#]150��#v4000169#[#c4000169#]150��\r\n"+����+"\r\n"+����ֵ+"*20000  (���ŵȼ�����Exp)\r\n"+��ȯ+" ���þ�*450\r\n"+С���+" ���ֵ*120000\r\n #v4250000#*5 #v4251300#*5\r\n #v1112743#*1\t #v4000463#*5");
			cm.dispose();
	    }
				}else if (selection == 32) {
			if (cm.getPlayer().getCSPoints(1) > 700){
				if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("�����ռ䲻��2��");
			cm.dispose();
			return;
		}
				cm.gainNX(-700);//�Ӽ���ȯ
                cm.gainItem(4000276,200);
				cm.gainItem(4000277,200);
                cm.dispose();
			}else{
                cm.sendOk("700��ȯ���㣬���򲻵�Ŷ��");
                cm.dispose();
		}
        } else if (selection == 6) {
			if (cm.haveItem(4000276 ,200) && cm.haveItem(4000277 ,200)){
				if (!cm.checkNumSpace(0, 5)) {
			cm.sendOk("�����ռ䲻��5��");
			cm.dispose();
			return;
		}
			cm.gainItem(4000276, -200);//�����Ʒ
			cm.gainItem(4000277, -200);//�����Ʒ
			cm.gainMeso(+140000); //�Ӽ����
			cm.gainItem(4250201,2);//�����Ʒʯ��ʯ�е�
			cm.gainItem(4250401,2);//�����Ʒ��ˮ���е�
			cm.gainItem(4004000,20);//�����Ʒ����ĸ��
			cm.gainItem(4004001,20);//�����Ʒ�ǻ�ĸ��
			cm.gainItem(1142005,1);//�����Ʒ��˵�е�ð�ռ�ѫ��
			cm.gainExp(cm.getLevel()*20000);//����ֵ
			cm.gainDY(500);//�����˵��þ�
			cm.getPlayer().setOneTimeLog("��������");
			cm.worldMessage(6,"���:["+cm.getName()+"]����˾����������������,��ý���:����ֵ,���þ�,���,���ߵȽ�Ʒ�������!");
			cm.sendOk("��ϲ������˾���������������أ�������߹ؽ���Ҳ�Ǻܷ��Ŷ��");
			cm.dispose();
		}else{
			cm.sendOk(""+�������+"\r\n����ȥ���������Һ�����˵�����к���������!\r\n�뵽һ����#r��ͨ������ڣ��ĵط�#k\r\n#e�ռ����ϣ�#k#n\r\n#v4000276#[#c4000276#]200��#v4000277#[#c4000277#]200��\r\n"+����+"\r\n"+����ֵ+"*20000  (���ŵȼ�����Exp)\r\n"+��ȯ+" ���þ�*400\r\n"+С���+" ���ֵ*140000\r\n #v4250201#*2 #v4250401#*2\r\n #v4004001#*20 #v4004000#*20\r\n #v1142005#*1");
			cm.dispose();
	    }
		}else if (selection == 33) {
			if (cm.getPlayer().getCSPoints(1) > 800){
				if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("�����ռ䲻��2��");
			cm.dispose();
			return;
		}
				cm.gainNX(-800);//�Ӽ���ȯ
                cm.gainItem(4000115,400);
                cm.dispose();
			}else{
                cm.sendOk("800��ȯ���㣬���򲻵�Ŷ��");
                cm.dispose();
		}
        } else if (selection == 7) {
			if (cm.haveItem(4000115 ,400)){
				if (!cm.checkNumSpace(0, 5)) {
			cm.sendOk("�����ռ䲻��5��");
			cm.dispose();
			return;
		}
			cm.gainItem(4000115, -400);//�����Ʒ
			cm.gainMeso(+160000); //�Ӽ����
			cm.gainItem(1132139,1);//����������
			cm.gainItem(4250501,2);//���Ϻ�����ʯ�е�
			cm.gainItem(4250700,2);//������ĸ�̱�ʯ�е�
			cm.gainItem(4004002,20);//��������ĸ��
			cm.gainItem(4004003,20);//��������ĸ��
			cm.gainExp(cm.getLevel()*20000);//����ֵ
			cm.gainDY(550);//�����˵��þ�
			cm.getPlayer().setOneTimeLog("��������");
			cm.worldMessage(6,"���:["+cm.getName()+"]����˾�������������߹�,��ý���:����ֵ,���þ�,���,���ߵȽ�Ʒ�������!");
			cm.sendOk("��ϲ������˾�������������߹أ�����ڰ˹ؽ���Ҳ�Ǻܷ��Ŷ��");
			cm.dispose();
		}else{
			cm.sendOk(""+�������+"\r\n�㻹�ǵ�ȥ�������ӵ�·?����ֻ�Ǿ�������!\r\n�뵽һ����#r��ʱ��֮·4���ĵط�#k\r\n#e�ռ����ϣ�#k#n\r\n#v4000115#[#c4000115#]400��\r\n"+����+"\r\n"+����ֵ+"*20000  (���ŵȼ�����Exp)\r\n"+��ȯ+" ���þ�*550\r\n"+С���+" ���ֵ*160000\r\n #v1132139#*1 #v4250501#*2\r\n #v4250701#*2 #v4004003#*20 #v4004002#*20");
			cm.dispose();
	    }
		}else if (selection == 34) {
			if (cm.getPlayer().getCSPoints(1) > 900){
				if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("�����ռ䲻��2��");
			cm.dispose();
			return;
		}
				cm.gainNX(-900);//�Ӽ���ȯ
                cm.gainItem(4000088,250);
				cm.gainItem(4000086,250);
                cm.dispose();
			}else{
                cm.sendOk("900��ȯ���㣬���򲻵�Ŷ��");
                cm.dispose();
		}
        } else if (selection == 8) {
			if (cm.haveItem(4000088 ,250) && cm.haveItem(4000086 ,250)){
				if (!cm.checkNumSpace(0, 5)) {
			cm.sendOk("�����ռ䲻��5��");
			cm.dispose();
			return;
		}
			cm.gainItem(4000086, -250);//�۳�����
			cm.gainItem(4000088, -250);//�۳�����
			cm.gainMeso(+180000); //�Ӽ����
			cm.gainItem(1112795,1);//�𱴿��ķ�Ҷ��ָ
			cm.gainItem(4250301,2);//����ʯ�е�
			cm.gainItem(4250101,2);//����ʯ�е�
			cm.gainItem(4004004,10);//�ڰ�ˮ��ĸ��
			cm.gainItem(3010094,1);//ƯƯ������
			cm.gainExp(cm.getLevel()*20000);//����ֵ
			cm.gainDY(600);//�����˵��þ�
			cm.getPlayer().setOneTimeLog("��������");
			cm.worldMessage(6,"���:["+cm.getName()+"]����˾�����������ڰ˹�,��ý���:����ֵ,���þ�,���,���ߵȽ�Ʒ�������!");
			cm.sendOk("��ϲ������˾�����������ڰ˹أ�����ھŹؽ���Ҳ�Ǻܷ��Ŷ��");
			cm.dispose();
		}else{
			cm.sendOk(""+�������+"\r\n��һ���������ص���С����С��ѩ��!\r\n�뵽һ����#r��ѩ�򸽽����ĵط�#k\r\n#e�ռ����ϣ�#k#n\r\n#v4000088#[#c4000088#]250��\r\n#v4000086#[#c4000086#]250��\r\n"+����+"\r\n"+����ֵ+"*20000  (���ŵȼ�����Exp)\r\n"+��ȯ+" ���þ�*600\r\n"+С���+" ���ֵ*180000\r\n #v1112795#*1 #v4250301#*2 #v4250101#*2 #v4004004#*10 #v3010094#*1");
			cm.dispose();
	    }
	    }else if (selection == 35) {
			if (cm.getPlayer().getCSPoints(1) > 1000){
				if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("�����ռ䲻��2��");
			cm.dispose();
			return;
		}
				cm.gainNX(-1000);//�Ӽ���ȯ
                cm.gainItem(4000177,300);
				cm.gainItem(4000025,300);
                cm.dispose();
			}else{
                cm.sendOk("1000��ȯ���㣬���򲻵�Ŷ��");
                cm.dispose();
		}
		} else if (selection == 9) {
			if (cm.haveItem(4000177 ,300) && cm.haveItem(4000025 ,300)){
				if (!cm.checkNumSpace(0, 5)) {
			cm.sendOk("�����ռ䲻��5��");
			cm.dispose();
			return;
		}
			cm.gainItem(4000177, -300);//�����Ʒ
			cm.gainItem(4000025, -300);//�����Ʒ
			cm.gainMeso(+200000); //�Ӽ����
			cm.gainItem(4250601,2);//�ƾ���
			cm.gainItem(4250001,2);//��ʯ��
			cm.gainItem(1142298,1);//ð�յ�8�����ѫ��
			cm.gainItem(4310150,2);//��ҵ��
			cm.gainItem(4000313,10);//���ױ�
			cm.gainExp(cm.getLevel()*20000);//����ֵ
			cm.gainDY(650);//�����˵��þ�
			cm.getPlayer().setOneTimeLog("��������");
			cm.worldMessage(6,"���:["+cm.getName()+"]����˾�����������ھŹ�,��ý���:����ֵ,���þ�,���,���ߵȽ�Ʒ�������!");
			cm.sendOk("��ϲ������˾�����������ھŹأ������ʮ�ؽ���Ҳ�Ǻܷ��Ŷ��");
			cm.dispose();
		}else{
			cm.sendOk(""+�������+"\r\n�㻹�ǵ�����֮����������ʯͷ����?\r\n�뵽һ����#r������֮�֣��ĵط�#k\r\n#e�ռ����ϣ�#k#n\r\n#v4000177#[#c4000177#]300��#v4000025#[#c4000025#]300��\r\n"+����+"\r\n"+����ֵ+" X20000  (���ŵȼ�����Exp)\r\n"+��ȯ+" ���þ� X650\r\n"+С���+" ���ֵ *200000\r\n #v4250601#*2 #v4250001#*2 #v4310150#*2 #v4000313#*10 #v1142298#*1");
			cm.dispose();
	    }
		}else if (selection == 36) {
			if (cm.getPlayer().getCSPoints(1) > 1100){
				if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("�����ռ䲻��2��");
			cm.dispose();
			return;
		}
				cm.gainNX(-1100);//�Ӽ���ȯ
                cm.gainItem(4000289,300);
				cm.gainItem(4000299,300);
                cm.dispose();
			}else{
                cm.sendOk("1100��ȯ���㣬���򲻵�Ŷ��");
                cm.dispose();
		}
		} else if (selection == 10) {
			if (cm.haveItem(4000289 ,300) && cm.haveItem(4000299 ,300) ){
				if (!cm.checkNumSpace(0, 5)) {
			cm.sendOk("�����ռ䲻��5��");
			cm.dispose();
			return;
		}
			cm.gainItem(4000289, -300);
			cm.gainItem(4000299, -300);
			cm.gainItem(4251301,2);//��ˮ����
			cm.gainItem(4000463,5);//��������
			cm.gainItem(3010110,1);//���ʴ��������
			cm.gainItem(4310150,4);//��ҵ��
			cm.gainItem(4000313,15);//���ױ�
			cm.gainMeso(+220000); //�Ӽ����
			cm.gainExp(cm.getLevel()*20000);//����ֵ
			cm.gainDY(700);//�����˵��þ�
			cm.getPlayer().setOneTimeLog("��������");
			cm.worldMessage(6,"���:["+cm.getName()+"]����˾������������ʮ��,��ý���:����ֵ,���þ�,���,���ߵȽ�Ʒ�������!");
			cm.sendOk("��ϲ������˾������������ʮ�أ������ʮһ�ؽ���Ҳ�Ǻܷ��Ŷ��");
			cm.dispose();
		}else{
			cm.sendOk(""+�������+"\r\n�����������è��һ���Ĺ���!\r\n�뵽һ����#r������ɭ�֣��ĵط�#k\r\n#e�ռ����ϣ�#k#n\r\n#v4000289#[#c4000289#]300��#v4000299#[#c4000299#]300��\r\n"+����+"\r\n"+����ֵ+" X20000  (���ŵȼ�����Exp)\r\n"+��ȯ+" ���þ� X700\r\n"+С���+" ���ֵ X220000\r\n #v4251301#*2 #v4000463#*5 #v4310150#*4 #v4000313#*15 #v3010110#*1");
			cm.dispose();
	    }
		}else if (selection == 37) {
			if (cm.getPlayer().getCSPoints(1) > 1200){
				if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("�����ռ䲻��2��");
			cm.dispose();
			return;
		}
				cm.gainNX(-1200);//�Ӽ���ȯ
                cm.gainItem(4000032,300);
				cm.gainItem(4000034,300);
                cm.dispose();
			}else{
                cm.sendOk("1200��ȯ���㣬���򲻵�Ŷ��");
                cm.dispose();
		}		
		} else if (selection == 11) {
			if (cm.haveItem(4000032 ,300) && cm.haveItem(4000034 ,300) ){
				if (!cm.checkNumSpace(0, 5)) {
			cm.sendOk("�����ռ䲻��5��");
			cm.dispose();
			return;
		}
			cm.gainItem(4000032, -300);
			cm.gainItem(4000034, -300);
			cm.gainMeso(+240000); //�Ӽ����
			cm.gainItem(4250202,1);//ʯ��ʯ��
			cm.gainItem(4250402,1);//��ˮ����
			cm.gainItem(1132151,1);//�Ͻ��Ҷ����
			cm.gainItem(4310150,6);//��ҵ��
			cm.gainItem(4000313,20);//���ױ�
			cm.gainExp(cm.getLevel()*20000);//����ֵ
			cm.gainDY(750);//�����˵��þ�
			cm.getPlayer().setOneTimeLog("��������");
			cm.worldMessage(6,"���:["+cm.getName()+"]����˾������������ʮһ��,��ý���:����ֵ,���þ�,���,���ߵȽ�Ʒ�������!");
			cm.sendOk("��ϲ������˾������������ʮһ�أ������ʮ���ؽ���Ҳ�Ǻܷ��Ŷ��");
			cm.dispose();
		}else{
			cm.sendOk(""+�������+"\r\n�㻹�ǵ�һ�������ȥ��������и��������и���ˮ���Ķ�����?\r\n�뵽һ����#r������آ�#k\r\n#e�ռ����ϣ�#k#n\r\n#v4000032#[#c4000032#]300��#v4000034#[#c4000034#]300��\r\n"+����+"\r\n"+����ֵ+" X20000  (���ŵȼ�����Exp)\r\n"+��ȯ+" ���þ� X750\r\n"+С���+" ���ֵ X240000\r\n #v4250202#*1 #v4250402#*1 #v4000313#*20 #v4310150#*6 #v1132151#*1");
			cm.dispose();
	    }
	    }else if (selection == 38) {
			if (cm.getPlayer().getCSPoints(1) > 1300){
				if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("�����ռ䲻��2��");
			cm.dispose();
			return;
		}
				cm.gainNX(-1300);//�Ӽ���ȯ
                cm.gainItem(4000040,16);
				cm.gainItem(4000176,16);
                cm.dispose();
			}else{
                cm.sendOk("1300��ȯ���㣬���򲻵�Ŷ��");
                cm.dispose();
		}
		} else if (selection == 12) {
			if (cm.haveItem(4000040 ,16) && cm.haveItem(4000176 ,16)){
				if (!cm.checkNumSpace(0, 5)) {
			cm.sendOk("�����ռ䲻��5��");
			cm.dispose();
			return;
		}
			cm.gainItem(4000040, -16);//�����Ʒ
			cm.gainItem(4000176, -16);//�����Ʒ
			cm.gainItem(4250502,1);//������ʯ��
			cm.gainItem(4250702,1);//��ĸ�̸�
			cm.gainItem(1142367,1);//�ɿ�������ĩ�ر�ѫ��
			cm.gainItem(4000463,10);//��������
			cm.gainItem(4000038,2);//��
			cm.gainMeso(+300000); //�Ӽ����
			cm.gainExp(cm.getLevel()*20000);//����ֵ
			cm.gainDY(800);//�����˵��þ�
			cm.getPlayer().setOneTimeLog("��������");
			cm.worldMessage(6,"���:["+cm.getName()+"]����˾������������ʮ����,��ý���:����ֵ,���þ�,���,���ߵȽ�Ʒ�������!");
			cm.sendOk("��ϲ������˾������������ʮ���أ������ʮ���ؽ���Ҳ�Ǻܷ��Ŷ��");
			cm.dispose();
		}else{
			cm.sendOk(""+�������+"\r\n��һ������Ұ��BOSS��ʱ������������԰��\r\n��ȥ���#r��Ұ��BOSSĢ������#k\r\n#e�ռ����ϣ�#k#n\r\n#v4000176#[#c4000176#]16��#v4000040#[#c4000040#]16��\r\n"+����+"\r\n"+����ֵ+" X20000  (���ŵȼ�����Exp)\r\n"+��ȯ+" ���þ� X800\r\n"+С���+" ���ֵ X300000\r\n #v4250502#*1 #v4250702#*1 #v4000463#*10 #v4000038#*2 #v1142367#*1");
			cm.dispose();
		}
		}else if (selection == 39) {
			if (cm.getPlayer().getCSPoints(1) > 1400){
				if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("�����ռ䲻��2��");
			cm.dispose();
			return;
		}
				cm.gainNX(-1400);//�Ӽ���ȯ
                cm.gainItem(4000020,300);
				cm.gainItem(4000178,300);
                cm.dispose();
			}else{
                cm.sendOk("1400��ȯ���㣬���򲻵�Ŷ��");
                cm.dispose();
		}
	} else if (selection == 13) {
		if (cm.haveItem(4000020 ,300) && cm.haveItem(4000178 ,300)){
			if (!cm.checkNumSpace(0, 5)) {
			cm.sendOk("�����ռ䲻��5��");
			cm.dispose();
			return;
		}
		cm.gainItem(4000020, -300);//�����Ʒ
		cm.gainItem(4000178, -300);//�����Ʒ
		cm.gainItem(4250302,1);//����ʯ��
		cm.gainItem(4250102,1);//����ʯ��
		cm.gainItem(1002758,1);//ǿ�����Ҷͷ��
		//cm.gainItem(2290096,1);//ð�յ���ʿ20
		cm.gainItem(4000038,3);//��
		cm.gainMeso(+330000); //�Ӽ����
		cm.gainExp(cm.getLevel()*20000);//����ֵ
		cm.gainDY(850);//�����˵��þ�
		cm.getPlayer().setOneTimeLog("��������");
		cm.worldMessage(6,"���:["+cm.getName()+"]����˾������������ʮ����,��ý���:����ֵ,���þ�,���,���ߵȽ�Ʒ�������!");
		cm.sendOk("��ϲ������˾������������ʮ���أ������ʮ�Ĺؽ���Ҳ�Ǻܷ��Ŷ��");
		cm.dispose();
	}else{
		cm.sendOk(""+�������+"\r\n���Խ�������һȺҰ����ּ���/�ǳ���ǿ!\r\n�뵽һ����#r����֮�ڹ�֮�أ��ĵط�#k\r\n#e�ռ����ϣ�#k#n\r\n#v4000020#[#c4000020#]300��#v4000178#[#c4000178#]300��\r\n"+����+"\r\n"+����ֵ+" X20000  (���ŵȼ�����Exp)\r\n"+��ȯ+" ���þ� X850\r\n"+С���+" ���ֵ *330000\r\n #v4250302#*1 #v4250102#*1 #v4000038#*3 #v1002758#*1 ");
		cm.dispose();
	}
	}else if (selection == 40) {
			if (cm.getPlayer().getCSPoints(1) > 1500){
				if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("�����ռ䲻��2��");
			cm.dispose();
			return;
		}
				cm.gainNX(-1500);//�Ӽ���ȯ
                cm.gainItem(4000023,300);
				cm.gainItem(4000030,300);
                cm.dispose();
			}else{
                cm.sendOk("1500��ȯ���㣬���򲻵�Ŷ��");
                cm.dispose();
		}
} else if (selection == 14) {
	if (cm.haveItem(4000023 ,300) && cm.haveItem(4000030 ,300)){
		if (!cm.checkNumSpace(0, 5)) {
			cm.sendOk("�����ռ䲻��5��");
			cm.dispose();
			return;
		}
	cm.gainItem(4000023, -300);//�����Ʒ
	cm.gainItem(4000030, -300);//�����Ʒ
	cm.gainItem(4250602,1);//�ƾ���
	cm.gainItem(4250002,1);//��ʯ��
	cm.gainItem(1142898,1);//��ʦ����ͽѫ��
	cm.gainItem(4032391,10);//����ľ�����Ƭ 1
	cm.gainItem(4032392,10);//����ľ�����Ƭ2
	cm.gainMeso(+360000); //�Ӽ����
	cm.gainExp(cm.getLevel()*20000);//����ֵ
	cm.gainDY(900);//�����˵��þ�
	cm.getPlayer().setOneTimeLog("��������");
	cm.worldMessage(6,"���:["+cm.getName()+"]����˾������������ʮ�Ĺ�,��ý���:����ֵ,���þ�,���,���ߵȽ�Ʒ�������!");
	cm.sendOk("��ϲ������˾������������ʮ�Ĺأ������ʮ��ؽ���Ҳ�Ǻܷ��Ŷ��");
	cm.dispose();
}else{
	cm.sendOk(""+�������+"\r\n����һ�����Թ�һ���ĵ�ͼ,�ߵ�ͷ������,���:��ɫ����֮��!\r\n�뵽һ����#r����Ѩ���ĵط�#k\r\n#e�ռ����ϣ�#k#n\r\n#v4000023#[#c4000023#]300��#v4000030#[#c4000030#]300��\r\n"+����+"\r\n"+����ֵ+" X20000  (���ŵȼ�����Exp)\r\n"+��ȯ+" ���þ� *900\r\n"+С���+" ���ֵ X360000\r\n #v4250602#*1 #v4250002#*1 #v4032391#*10 #v4032392#*10 #v1142898#*1");
	cm.dispose();
}
}else if (selection == 41) {
			if (cm.getPlayer().getCSPoints(1) > 1600){
				if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("�����ռ䲻��2��");
			cm.dispose();
			return;
		}
				cm.gainNX(-1600);//�Ӽ���ȯ
                cm.gainItem(4000206,350);
				cm.gainItem(4000207,350);
                cm.dispose();
			}else{
                cm.sendOk("1600��ȯ���㣬���򲻵�Ŷ��");
                cm.dispose();
		}
} else if (selection == 15) {
	if (cm.haveItem(4000206 ,350) && cm.haveItem(4000207 ,350)){
		if (!cm.checkNumSpace(0, 5)) {
			cm.sendOk("�����ռ䲻��5��");
			cm.dispose();
			return;
		}
	cm.gainItem(4000206, -350);//�����Ʒ
	cm.gainItem(4000207, -350);//�����Ʒ
	cm.gainMeso(+400000); //�Ӽ����
	cm.gainItem(4251302,1);//��ˮ����
	cm.gainItem(1022236,1);//��ʦ�ͽ�����̫���۾�
	//cm.gainItem(2290125,1);//[���ֲ�]ð�յ���ʿ30
	cm.gainItem(4005000,3);//����ˮ��
	cm.gainItem(4005001,10);//�ǻ�ˮ��
	cm.gainExp(cm.getLevel()*20000);//����ֵ
	cm.gainDY(950);//�����˵��þ�
	cm.getPlayer().setOneTimeLog("��������");
	cm.worldMessage(6,"���:["+cm.getName()+"]����˾������������ʮ���,��ý���:����ֵ,���þ�,���,���ߵȽ�Ʒ�������!");
	cm.sendOk("��ϲ������˾������������ʮ��أ������ʮ���ؽ���Ҳ�Ǻܷ��Ŷ��");
	cm.dispose();
}else{
	cm.sendOk(""+�������+"\r\n���Խ�����������,��һ�ο����Ƿ��е������־�!\r\n�뵽һ����#r����1��Ӫ���ĵط�#k\r\n#e�ռ����ϣ�#k#n\r\n#v4000206#[#c4000206#]350��#v4000207#[#c4000207#]350��\r\n"+����+"\r\n"+����ֵ+" X20000  (���ŵȼ�����Exp)\r\n"+��ȯ+" ���þ� X950\r\n"+С���+" ���ֵ X400000\r\n #v4251302#*1 #v1022236#*1 #v4005000#*3 #v4005001#*3 ");
	cm.dispose();
}
}else if (selection == 42) {
			if (cm.getPlayer().getCSPoints(1) > 1700){
				if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("�����ռ䲻��2��");
			cm.dispose();
			return;
		}
				cm.gainNX(-1700);//�Ӽ���ȯ
                cm.gainItem(4000029,400);
				cm.gainItem(4000031,400);
                cm.dispose();
			}else{
                cm.sendOk("1700��ȯ���㣬���򲻵�Ŷ��");
                cm.dispose();
		}
} else if (selection == 16) {
	if (cm.haveItem(4000029 ,400) && cm.haveItem(4000031 ,400)){
		if (!cm.checkNumSpace(0, 5)) {
			cm.sendOk("�����ռ䲻��5��");
			cm.dispose();
			return;
		}
	cm.gainItem(4000029, -400);//�����Ʒ
	cm.gainItem(4000031, -400);//�����Ʒ
	cm.gainMeso(+440000); //�Ӽ����
	cm.gainItem(3015172,1);//������������
	cm.gainItem(4032391,10);//����ľ�����Ƭ 1 
	cm.gainItem(4032392,10);//����ľ�����Ƭ2 
	cm.gainItem(4005002,3);//����ˮ��
	cm.gainItem(4005003,3);//����ˮ��
	cm.gainExp(cm.getLevel()*20000);//����ֵ
	cm.gainDY(1000);//�����˵��þ�
	cm.getPlayer().setOneTimeLog("��������");
	cm.worldMessage(6,"���:["+cm.getName()+"]����˾������������ʮ����,��ý���:����ֵ,���þ�,���,���ߵȽ�Ʒ�������!");
	cm.sendOk("��ϲ������˾������������ʮ���أ������ʮ�߹ؽ���Ҳ�Ǻܷ��Ŷ��");
	cm.dispose();
}else{
	cm.sendOk(""+�������+"\r\n���Խ�������һȺ�Ļ��������/������!\r\n�뵽һ����#r������ɭ�֢򣩵ĵط�#k\r\n#e�ռ����ϣ�#k#n\r\n#v4000029#[#c4000029#]400��#v4000031#[#c4000031#]400��\r\n"+����+"\r\n"+����ֵ+" X20000  (���ŵȼ�����Exp)\r\n"+��ȯ+" ���þ� X1000\r\n"+С���+" ���ֵ X440000\r\n #v4005002#*3 #v4005003#*3 #v3015172#*1 #v4032391#*10 #v4032392#*10");
	cm.dispose();
}
}else if (selection == 43) {
			if (cm.getPlayer().getCSPoints(1) > 1800){
				if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("�����ռ䲻��2��");
			cm.dispose();
			return;
		}
				cm.gainNX(-1800);//�Ӽ���ȯ
                cm.gainItem(4000070,1000);
				cm.gainItem(4000071,1000);
				cm.gainItem(4000072,1000);
                cm.dispose();
			}else{
                cm.sendOk("1800��ȯ���㣬���򲻵�Ŷ��");
                cm.dispose();
		}
} else if (selection == 17) {
	if (cm.haveItem(4000070 ,220) && cm.haveItem(4000071 ,220) && cm.haveItem(4000072 ,220) ){
		if (!cm.checkNumSpace(0, 5)) {
			cm.sendOk("�����ռ䲻��5��");
			cm.dispose();
			return;
		}
	cm.gainItem(4000070, -220);//�����Ʒ
	cm.gainItem(4000071, -220);//�����Ʒ
	cm.gainItem(4000072, -220);//�����Ʒ
	cm.gainMeso(+480000); //�Ӽ����
	cm.gainItem(4000038,5);//��
	cm.gainItem(4310150,10);//��ҵ��
	cm.gainItem(4000313,100);//���ױ�
	cm.gainItem(4005004,3);//�ڰ�ˮ��
	cm.gainItem(4001126,100);//��Ҷ
	cm.gainExp(cm.getLevel()*20000);//����ֵ
	cm.gainDY(1100);//�����˵��þ�
	cm.getPlayer().setOneTimeLog("��������");
	cm.worldMessage(6,"���:["+cm.getName()+"]����˾������������ʮ�߹�,��ý���:����ֵ,���þ�,���,���ߵȽ�Ʒ�������!");
	cm.sendOk("��ϲ������˾������������ʮ�߹أ������ʮ�˹ؽ���Ҳ�Ǻܷ��Ŷ��");
	cm.dispose();
}else{
	cm.sendOk(""+�������+"\r\n�������ص���һȺ�Ŀɰ�С��ʨ\r\n�뵽һ����#r�����¥�ݢ񣩵ĵط�#k\r\n#e�ռ����ϣ�#k#n\r\n#v4000070#[#c4000070#]220��#v4000071#[#c4000071#]220��\r\n#v4000072#[#c4000072#]220��\r\n"+����+"\r\n"+����ֵ+" X20000  (���ŵȼ�����Exp)\r\n"+��ȯ+" ���þ� *1100\r\n"+С���+" ���ֵ X480000\r\n #v4005004#*3 #v4000038#*5 #v4000313#*100 #v4001126#*100 #v4310150#*10");
	cm.dispose();
}
}else if (selection == 44) {
			if (cm.getPlayer().getCSPoints(1) > 1900){
				if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("�����ռ䲻��2��");
			cm.dispose();
			return;
		}
				cm.gainNX(-1900);//�Ӽ���ȯ
                cm.gainItem(4000060,450);
				cm.gainItem(4000061,450);
                cm.dispose();
			}else{
                cm.sendOk("1900��ȯ���㣬���򲻵�Ŷ��");
                cm.dispose();
		}
} else if (selection == 18) {
	if (cm.haveItem(4000060 ,450) && cm.haveItem(4000061 ,450) ){
		if (!cm.checkNumSpace(0, 5)) {
			cm.sendOk("�����ռ䲻��5��");
			cm.dispose();
			return;
		}
	cm.gainItem(4000060, -450);
	cm.gainItem(4000061, -450);
	cm.gainItem(4250201,5);//ʯ��ʯ�е�
	cm.gainItem(4310150,5);//��ˮ����
	cm.gainItem(4250701,5);//������ʯ��
	cm.gainItem(4250301,5);//��ĸ����
	cm.gainItem(4250101,5);//����ʯ
	cm.gainMeso(+520000); //�Ӽ����
	cm.gainExp(cm.getLevel()*20000);//����ֵ
	cm.gainDY(1200);//�����˵��þ�
	cm.getPlayer().setOneTimeLog("��������");
	cm.worldMessage(6,"���:["+cm.getName()+"]����˾������������ʮ�˹�,��ý���:����ֵ,���þ�,���,���ߵȽ�Ʒ�������!");
	cm.sendOk("��ϲ������˾������������ʮ�˹أ�����ڶ�ʮ�ؽ���Ҳ�Ǻܷ��Ŷ��");
	cm.dispose();
}else{
	cm.sendOk(""+�������+"\r\n�������ص���һȺ���չ⾫��\r\n�뵽һ����#r���Ʋʹ�԰�����ĵط�#k\r\n#e�ռ����ϣ�#k#n\r\n#v4000060#[#c4000060#]450��\r\n#v4000061#[#c4000061#]450��\r\n"+����+"\r\n"+����ֵ+" X20000  (���ŵȼ�����Exp)\r\n"+��ȯ+" ���þ� X1200\r\n"+С���+" ���ֵ X520000\r\n #v4250201#*5 #v4250401#*5 #v4250501#*5#v4250701#*5#v4250301#*5");
	cm.dispose();
}
}else if (selection == 45) {
			if (cm.getPlayer().getCSPoints(1) > 2000){
				if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("�����ռ䲻��2��");
			cm.dispose();
			return;
		}
				cm.gainNX(-2000);//�Ӽ���ȯ
                cm.gainItem(4000051,450);
				cm.gainItem(4000052,450);
                cm.dispose();
			}else{
                cm.sendOk("2000��ȯ���㣬���򲻵�Ŷ��");
                cm.dispose();
		}
} else if (selection == 19) {
	if (cm.haveItem(4000051 ,450) && cm.haveItem(4000052 ,450) ){
		if (!cm.checkNumSpace(0, 5)) {
			cm.sendOk("�����ռ䲻��5��");
			cm.dispose();
			return;
		}
	cm.gainItem(4000051, -450);
	cm.gainItem(4000052, -450);
	cm.gainItem(4250601,5);//����ʯ
	cm.gainItem(4250001,5);//�ƾ�
	cm.gainItem(4251301,5);//��ʯ
    cm.gainItem(4251301,5);//��ˮ��
    cm.gainItem(4032391,20);//����ľ�����Ƭ 1
    cm.gainItem(4032392,20);//����ľ�����Ƭ2
	cm.gainMeso(+550000); //�Ӽ����
	cm.gainExp(cm.getLevel()*20000);//����ֵ
	cm.gainDY(1300);//�����˵��þ�
	cm.getPlayer().setOneTimeLog("��������");
	cm.worldMessage(6,"���:["+cm.getName()+"]����˾������������ʮ�Ź�,��ý���:����ֵ,���þ�,���,���ߵȽ�Ʒ�������!");
	cm.sendOk("��ϲ������˾������������ʮ�Źأ�����ڶ�ʮ�ؽ���Ҳ�Ǻܷ��Ŷ��");
	cm.dispose();
}else{
	cm.sendOk(""+�������+"\r\n�������ص���һȺ�����׶��Ұ��\r\n�뵽һ����#r�������ƽԭ�񣩵ĵط�#k\r\n#e�ռ����ϣ�#k#n\r\n#v4000051#[#c4000051#]450��#v4000052#[#c4000052#]450��\r\n"+����+"\r\n"+����ֵ+" X20000  (���ŵȼ�����Exp)\r\n"+��ȯ+" ���þ� X1300\r\n"+С���+" ���ֵ X550000\r\n #v4250101#*5 #v4250601#*5 #v4250001#*5 #v4251301#*5 #v4032391#*20 #v4032392#*20");
	cm.dispose();
}
}else if (selection == 46) {
			if (cm.getPlayer().getCSPoints(1) > 2100){
				if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("�����ռ䲻��2��");
			cm.dispose();
			return;
		}
				cm.gainNX(-2100);//�Ӽ���ȯ
                cm.gainItem(4000049,200);
				cm.gainItem(4000050,200);
                cm.dispose();
			}else{
                cm.sendOk("2100��ȯ���㣬���򲻵�Ŷ��");
                cm.dispose();
		}
} else if (selection == 20) {
	if (cm.haveItem(4000049 ,200) && cm.haveItem(4000050 ,200) ){
		if (!cm.checkNumSpace(0, 5)) {
			cm.sendOk("�����ռ䲻��5��");
			cm.dispose();
			return;
		}
	cm.gainItem(4000049, -200);
	cm.gainItem(4000050, -200);
	cm.gainItem(4250202,2);//ʯ��ʯ��
	cm.gainItem(4250402,2);//��ˮ��
	cm.gainItem(4250502,2);//������
	cm.gainItem(4250702,2);//��ĸ��
	cm.gainItem(4250302,2);//����
	cm.gainItem(4250102,2);//����ʯ
	cm.gainMeso(+580000); //�Ӽ����
	cm.gainExp(cm.getLevel()*20000);//����ֵ
	cm.gainDY(1400);//�����˵��þ�
	cm.getPlayer().setOneTimeLog("��������");
	cm.worldMessage(6,"���:["+cm.getName()+"]����˾�����������ڶ�ʮ��,��ý���:����ֵ,���þ�,���,���ߵȽ�Ʒ�������!");
	cm.sendOk("��ϲ������˾�����������ڶ�ʮ�أ�����ڶ�ʮһ�ؽ���Ҳ�Ǻܷ��Ŷ��");
	cm.dispose();
}else{
	cm.sendOk(""+�������+"\r\n�������ص���һȺ�ɰ����׵İ�ѩ��\r\n�뵽һ����#r����ѩϿ�Ȣ򣩵ĵط�#k\r\n#e�ռ����ϣ�#k#n\r\n#v4000049#[#c4000049#]200��#v4000050#[#c4000050#]200��\r\n"+����+"\r\n"+����ֵ+" X20000  (���ŵȼ�����Exp)\r\n"+��ȯ+" ���þ� X1400\r\n"+С���+" ���ֵ X580000\r\n #v4250202#*2 #v4250402#*2 #v4250502#*2 #v4250702#*2 #v4250102#*2 #v4250302#*2");
	cm.dispose();
}
}else if (selection == 47) {
			if (cm.getPlayer().getCSPoints(1) > 2200){
				if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("�����ռ䲻��2��");
			cm.dispose();
			return;
		}
				cm.gainNX(-2200);//�Ӽ���ȯ
                cm.gainItem(4000069,450);
				cm.gainItem(4000076,450);
                cm.dispose();
			}else{
                cm.sendOk("2200��ȯ���㣬���򲻵�Ŷ��");
                cm.dispose();
		}
} else if (selection == 21) {
	if (cm.haveItem(4000069 ,450) && cm.haveItem(4000076 ,450) ){
		if (!cm.checkNumSpace(0, 5)) {
			cm.sendOk("�����ռ䲻��5��");
			cm.dispose();
			return;
		}
	cm.gainItem(4000069, -450);
	cm.gainItem(4000076, -450);
	cm.gainItem(4250602,2);//�ƾ���
	cm.gainItem(4250002,2);//��ʯ
	cm.gainItem(4251302,2);//��ˮ��
	cm.gainItem(4032391,50);//����ľ�����Ƭ 1
	cm.gainItem(4032392,50);//����ľ�����Ƭ2
	cm.gainItem(1072872,1);//��Ь��
	cm.gainMeso(+620000); //�Ӽ����
	cm.gainExp(cm.getLevel()*20000);//����ֵ
	cm.gainDY(1500);//�����˵��þ�
	cm.getPlayer().setOneTimeLog("��������");
	cm.worldMessage(6,"���:["+cm.getName()+"]����˾�����������ڶ�ʮһ��,��ý���:����ֵ,���þ�,���,���ߵȽ�Ʒ�������!");
	cm.sendOk("��ϲ������˾�����������ڶ�ʮһ�أ�����ڶ�ʮ���ؽ���Ҳ�Ǻܷ��Ŷ��");
	cm.dispose();
}else{
	cm.sendOk(""+�������+"\r\n�������ص���һȺ�ձ���ʬ����������\r\n�뵽һ����#r���Ͽ����󣩵ĵط�#k\r\n#e�ռ����ϣ�#k#n\r\n#v4000069#[#c4000069#]450��#v4000076#[#c4000076#]450��\r\n"+����+"\r\n"+����ֵ+" X20000  (���ŵȼ�����Exp)\r\n"+��ȯ+" ���þ� X1500\r\n"+С���+" ���ֵ X620000\r\n #v4250602#*2 #v4250002#*2 #v4251302#*2 #v4032391#*50 #v4032392#*50 #v1072872#*1");
	cm.dispose();
}
}else if (selection == 48) {
			if (cm.getPlayer().getCSPoints(1) > 2300){
				if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("�����ռ䲻��2��");
			cm.dispose();
			return;
		}
				cm.gainNX(-2300);//�Ӽ���ȯ
                cm.gainItem(4000284,500);
				cm.gainItem(4000285,500);
                cm.dispose();
			}else{
                cm.sendOk("2300��ȯ���㣬���򲻵�Ŷ��");
                cm.dispose();
		}
} else if (selection == 22) {
	if (cm.haveItem(4000284 ,500) && cm.haveItem(4000285 ,500) ){
		if (!cm.checkNumSpace(0, 5)) {
			cm.sendOk("�����ռ䲻��5��");
			cm.dispose();
			return;
		}
	cm.gainItem(4000284, -500);
	cm.gainItem(4000285, -500);
	cm.gainItem(2340000,2);//ף����
	cm.gainItem(2049117,2);//�����
	cm.gainItem(3010142,1);//ˮ�������
	cm.gainItem(1102163,1);//��������
	cm.gainItem(4251200,1);//�µ����ˮ��
	cm.gainItem(4000463,20);//��������
	cm.gainMeso(+660000); //�Ӽ����
	cm.gainExp(cm.getLevel()*20000);//����ֵ
	cm.gainDY(1600);//�����˵��þ�
	cm.getPlayer().setOneTimeLog("��������");
	cm.worldMessage(6,"���:["+cm.getName()+"]����˾�����������ڶ�ʮ����,��ý���:����ֵ,���þ�,���,���ߵȽ�Ʒ�������!");
	cm.sendOk("��ϲ������˾�����������ڶ�ʮ���أ�����ڶ�ʮ���ؽ���Ҳ�Ǻܷ��Ŷ��");
	cm.dispose();
}else{
	cm.sendOk(""+�������+"\r\n���������һȺ���ཱུ���\r\n�뵽һ����#r��Ұ���ܵĵ���3���ĵط�#k\r\n#e�ռ����ϣ�#k#n\r\n#v4000284#[#c4000284#]500��#v4000285#[#c4000285#]500��\r\n"+����+"\r\n"+����ֵ+" X20000  (���ŵȼ�����Exp)\r\n"+��ȯ+" ���þ� X1600\r\n"+С���+" ���ֵ X660000\r\n #v2340000#*2 #v2049117#*2 #v1102163#*1 #v4251200#*1 #v3010142#*1 #v4000463#*20");
	cm.dispose();
}
}else if (selection == 49) {
			if (cm.getPlayer().getCSPoints(1) > 2400){
				if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("�����ռ䲻��2��");
			cm.dispose();
			return;
		}
				cm.gainNX(-2400);//�Ӽ���ȯ
                cm.gainItem(4000171,600);
				cm.gainItem(4000172,600);
                cm.dispose();
			}else{
                cm.sendOk("2400��ȯ���㣬���򲻵�Ŷ��");
                cm.dispose();
		}
} else if (selection == 23) {
	if (cm.haveItem(4000171 ,600) && cm.haveItem(4000172 ,600) ){
		if (!cm.checkNumSpace(0, 5)) {
			cm.sendOk("�����ռ䲻��5��");
			cm.dispose();
			return;
		}
	cm.gainItem(4000171, -600);
	cm.gainItem(4000172, -600);
	cm.gainItem(2340000,2);//ף����
	cm.gainItem(2049117,2);//�����
	cm.gainItem(3010144,1);//��Ϧ����
	cm.gainItem(1032099,1);//���Ѷ���
	cm.gainItem(4251200,1);//�µ����ˮ��
	cm.gainItem(4310150,10);//��ҵ��
	cm.gainMeso(+700000); //�Ӽ����
	cm.gainExp(cm.getLevel()*20000);//����ֵ
	cm.gainDY(1700);//�����˵��þ�
	cm.getPlayer().setOneTimeLog("��������");
	cm.worldMessage(6,"���:["+cm.getName()+"]����˾�����������ڶ�ʮ����,��ý���:����ֵ,���þ�,���,���ߵȽ�Ʒ�������!");
	cm.sendOk("��ϲ������˾�����������ڶ�ʮ���أ�����ڶ�ʮ�Ĺؽ���Ҳ�Ǻܷ��Ŷ��");
	cm.dispose();
}else{
	cm.sendOk(""+�������+"\r\n����ͯ�����һȺǿ׳�ϻ�����β����\r\n�뵽һ����#r����ɽϿ�ȣ��ĵط�#k\r\n#e�ռ����ϣ�#k#n\r\n#v4000171#[#c4000171#]600��#v4000172#[#c4000172#]600��\r\n"+����+"\r\n"+����ֵ+" X20000  (���ŵȼ�����Exp)\r\n"+��ȯ+" ���þ� X1700\r\n"+С���+" ���ֵ X700000\r\n #v2340000#*2 #v2049117#*2 #v1032099#*1 #v3010144#*1 #v4251200#*1 #v4310150#*10");
	cm.dispose();
}
}else if (selection == 50) {
			if (cm.getPlayer().getCSPoints(1) > 2500){
				if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("�����ռ䲻��2��");
			cm.dispose();
			return;
		}
				cm.gainNX(-2500);//�Ӽ���ȯ
                cm.gainItem(4000160,600);
				cm.gainItem(4000161,600);
                cm.dispose();
			}else{
                cm.sendOk("2500��ȯ���㣬���򲻵�Ŷ��");
                cm.dispose();
		}
} else if (selection == 24) {
	if (cm.haveItem(4000160 ,600) && cm.haveItem(4000161 ,600) ){
		if (!cm.checkNumSpace(0, 5)) {
			cm.sendOk("�����ռ䲻��5��");
			cm.dispose();
			return;
		}
	cm.gainItem(4000160, -600);
	cm.gainItem(4000161, -600);
	cm.gainItem(2340000,2);//ף����
	cm.gainItem(2049117,2);//�����
	cm.gainItem(3010448,1);//����ԡ����
	cm.gainItem(1072872,1);//��ʮ�ֽ�ָ
	cm.gainItem(4251200,1);//�µ����ˮ��
	cm.gainItem(4000038,5);//��
	cm.gainMeso(+750000); //�Ӽ����
	cm.gainExp(cm.getLevel()*20000);//����ֵ
	cm.gainDY(1800);//�����˵��þ�
	cm.getPlayer().setOneTimeLog("��������");
	cm.worldMessage(6,"���:["+cm.getName()+"]����˾�����������ڶ�ʮ�Ĺ�,��ý���:����ֵ,���þ�,���,���ߵȽ�Ʒ�������!");
	cm.sendOk("��ϲ������˾�����������ڶ�ʮ�Ĺأ�����ڶ�ʮ��ؽ���Ҳ�Ǻܷ��Ŷ��");
	cm.dispose();
}else{
	cm.sendOk(""+�������+"\r\n����ˮ�������һȺ������ĺ������������С����\r\n�뵽һ����#r�����εش����ĵط�#k\r\n#e�ռ����ϣ�#k#n\r\n#v4000160#[#c4000160#]600��#v4000161#[#c4000161#]600��\r\n"+����+"\r\n"+����ֵ+" X20000  (���ŵȼ�����Exp)\r\n"+��ȯ+" ���þ� X1800\r\n"+С���+" ���ֵ X750000\r\n #v2340000#*2 #v2049117#*2 #v3010448#*1 #v1072872#*1 #v4251200#*1 #v4000038#*5");
	cm.dispose();
}
}else if (selection == 51) {
			if (cm.getPlayer().getCSPoints(1) > 2600){
				if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("�����ռ䲻��2��");
			cm.dispose();
			return;
		}
				cm.gainNX(-2600);//�Ӽ���ȯ
                cm.gainItem(4000118,700);
				cm.gainItem(4000119,700);
                cm.dispose();
			}else{
                cm.sendOk("2600��ȯ���㣬���򲻵�Ŷ��");
                cm.dispose();
		}
} else if (selection == 25) {
	if (cm.haveItem(4000118 ,700) && cm.haveItem(4000119 ,700) ){
		if (!cm.checkNumSpace(0, 5)) {
			cm.sendOk("�����ռ䲻��5��");
			cm.dispose();
			return;
		}
	cm.gainItem(4000118, -700);
	cm.gainItem(4000119, -700);
	cm.gainItem(2340000,2);//ף����
	cm.gainItem(2049117,2);//�����
	cm.gainItem(3010449,1);//Ȭ��10������
	cm.gainItem(1132086,1);//����������ս������
	cm.gainItem(4251201,1);//�е����ˮ��
	cm.gainItem(4000038,5);//��
	cm.gainMeso(+800000); //�Ӽ����
	cm.gainExp(cm.getLevel()*20000);//����ֵ
	cm.gainDY(2000);//�����˵��þ�
	cm.getPlayer().setOneTimeLog("��������");
	cm.worldMessage(6,"���:["+cm.getName()+"]����˾�����������ڶ�ʮ���,��ý���:����ֵ,���þ�,���,���ߵȽ�Ʒ�������!");
    cm.sendOk("��ϲ������˾�����������ڶ�ʮ��أ�����ڶ�ʮ���ؽ���Ҳ�Ǻܷ��Ŷ��");
	cm.dispose();
}else{
	cm.sendOk(""+�������+"\r\n���Ե������������һȺ�����̵�������,������һ��Я�ֻ�������!["+cm.getName()+"]/#e����#k#n\r\n�뵽һ����#r���������ԭ���ĵط�#k\r\n#e�ռ����ϣ�#k#n\r\n#v4000118#[#c4000118#]700��#v4000119#[#c4000119#]700��\r\n"+����+"\r\n"+����ֵ+" X20000  (���ŵȼ�����Exp)\r\n"+��ȯ+" ���þ� X2000\r\n"+С���+" ���ֵ X800000\r\n #v2340000#*2 #v2049117#*2 #v3010449#*1 #v1132086#*1 #v4251201#*1 #v4000038#*5");
	cm.dispose();
}
}else if (selection == 52) {
			if (cm.getPlayer().getCSPoints(1) > 8888){
				if (!cm.checkNumSpace(0, 8)) {
			cm.sendOk("�����ռ䲻��8��");
			cm.dispose();
			return;
		}
				cm.gainNX(-8888);//�Ӽ���ȯ
                cm.gainItem(4000235,50);
				cm.gainItem(4000243,50);
				cm.gainItem(4000460,50);
				cm.gainItem(4000461,50);
				cm.gainItem(4001084,50);
				cm.gainItem(4001083,50);
				cm.gainItem(4001085,50);
				cm.gainItem(4000462,50);
                cm.dispose();
			}else{
                cm.sendOk("8888��ȯ���㣬���򲻵�Ŷ��");
                cm.dispose();
		}		
		} else if (selection == 26) {
			if (cm.haveItem(4000235 ,50) && cm.haveItem(4000243 ,50) && cm.haveItem(4000460 ,50) && cm.haveItem(4000461 ,50) && cm.haveItem(4000462 ,50) && cm.haveItem(4001083 ,50) && cm.haveItem(4001084 ,50) && cm.haveItem(4001085 ,50)){
				if (!cm.checkNumSpace(0, 8)) {
			cm.sendOk("�����ռ䲻��8��");
			cm.dispose();
			return;
		}
			cm.gainItem(4000235, -50);//����Ʒ
			cm.gainItem(4000243, -50);//����Ʒ
			cm.gainItem(4000460, -50);//����Ʒ
			cm.gainItem(4000461, -50);//����Ʒ
			cm.gainItem(4001083, -50);//����Ʒ
			cm.gainItem(4001084, -50);//����Ʒ
			cm.gainItem(4001085, -50);//����Ʒ
			cm.gainItem(4000462, -50);//����Ʒ
			cm.gainItem(2340000,5);//ף����
			cm.gainItem(2049117,5);//�����
			cm.gainItem(3010938,1);//����Ѫ��Ů������
			cm.gainItem(1142788,1);//�����ľ���ѫ��
			cm.gainItem(4251202,1);//�ߵ����ˮ��
			cm.gainItem(4000038,10);//��
			cm.gainItem(4310150,20);//��ҵ��
			cm.gainItem(4000463,50);//��������
			cm.gainMeso(+5000000); //�Ӽ����
			cm.gainExp(cm.getLevel()*20000);//����ֵ
	        cm.gainDY(6666);//�����˵��þ�
			cm.getPlayer().setOneTimeLog("��������");
			cm.worldMessage(6,"���:["+cm.getName()+"]����˾�����������ڶ�ʮ����,��ý���:����ֵ,���þ�,���,���ߵȽ�Ʒ�������!");
			cm.sendOk("��ϲ������˾�����������ڶ�ʮ���أ���ϲ��������йؿ���");
			cm.dispose();
		}else{
			cm.sendOk(""+�������+"\r\n������������һ����,���������������ð�յ�,����û��������ȥ�߹����ѻ���·!\r\n#e����Boss����:#k#n\r\n#r�������,��ӥ,���,����������,�׿�,����,����,������#k\r\n#e�ռ����ϣ�#k#n\r\n#v4000235#[#c4000235#]50�� #v4000243#[#c4000243#]50�� #v4000460#[#c4000460#]50��\r\n#v4000461#[#c4000461#]50�� #v4000462#[#c4000462#]50�� #v4001083#[#c4001083#]50��\r\n#v4001084#[#c4001084#]50�� #v4001085#[#c4001085#]50��\r\n"+����+"\r\n"+����ֵ+" X20000  (���ŵȼ�����Exp)\r\n"+��ȯ+" ���þ� X6666\r\n"+С���+" ���ֵ X5000000\r\n #v2340000#*5 #v2049117#*5 #v3010938#*1 #v1142788#*1 #v4251202#*1 #v4000038#*10 #v4310150#*20 #v4000463#*50");
			cm.dispose();
			}	
		}
    }
}
