/* ==================
 �ű�����: NPC
 �ű����ߣ�-��Ҷ
 ��ϵ��ʽ��1848350048
 =====================
 */
importPackage(java.lang);
importPackage(java.util);
importPackage(Packages.tools);
importPackage(Packages.server.quest);
importPackage(Packages.client);
importPackage(Packages.scripting);
importPackage(Packages.handling.channel);
importPackage(Packages.handling);
importPackage(Packages.handling.word);
var ca = java.util.Calendar.getInstance();
var year = ca.get(java.util.Calendar.YEAR); //������
var month = ca.get(java.util.Calendar.MONTH) + 1; //����·�
var day = ca.get(java.util.Calendar.DATE);//��ȡ��
var hour = ca.get(java.util.Calendar.HOUR_OF_DAY); //���Сʱ
var minute = ca.get(java.util.Calendar.MINUTE);//��÷���
var second = ca.get(java.util.Calendar.SECOND); //�����
var weekday = ca.get(java.util.Calendar.DAY_OF_WEEK);
function start() {
    status = -1;

    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    } else {
        
        if (mode == 1) {
            status++;
        } else {
            status--;
        }
        if (status == 0) {
			var tex2 = ""
            var text = "";
            for (i = 0; i < 10; i++) {
                text += "";
            }
            text = "\t#r#e"+ ���� + ""+ ����� + ""+ ��� + "������"+"" + cm.getChannelServer().getServerName() + ""+ ������ + "#kð#d��#b" + cm.getGamePoints() + "����"+ ���� + ""+ ������ + ""+ ���� + "#k  \r\n";
			text += ""+ ������ + ""+ ������ + ""+ ������ + ""+ ������ + ""+ ������ + ""+ ������ + ""+ ������ + ""+ ������ + "" + Ӣ�ı�ʶ + ""+ ������ + ""+ ������ + ""+ ������ + ""+ ������ + ""+ ������ + ""+ ������ + ""+ ������ + ""+ ������ + "#k\r\n\r\n";
			text += "                #d��ඣ�������������\r\n";
			text += "  #d����Ҫ�㹻�Ļ���ң���������˵�е��񻰹���\r\n";
			text += "#d���ܰ����ռ����������Ϊ�ر����һ�����񻰶���";
			
            text +="#L1##e#d"+����+""+����+""+����+"#i1032205:#"+����+""+����+""+����+"#l    #L2##e#d"+����+""+����+""+����+"#i1032206:#"+����+""+����+""+����+"#l\r\n\r\n"
			
			
			text +="#L3##e#d"+����+""+����+""+����+"#i1032207:#"+����+""+����+""+����+"#l    #L4##e#d"+����+""+����+""+����+"#i1032208:#"+����+""+����+""+����+"#l\r\n\r\n"
            
            text +="#L5##e#d"+����+""+����+""+����+"#i1032209:#"+����+""+����+""+����+"#l    #L6##e#d"+����+""+����+""+����+"#i1032219:#"+����+""+����+""+����+"#l\r\n\r\n"
			
			text += ""+ �������� + ""+ ������ + ""+ ������ + ""+ ������ + ""+ ������ + ""+ ������ + ""+ ������ + ""+ ������ + ""+ ������ + ""+ ������ + ""+ ������ + ""+ ������ + ""+ ������ + ""+ ������ + ""+ ������ + ""+ ������ + ""+ ������ + ""+ ������ + ""+ ������ + ""+ ������ + ""+ ������ + ""+ ������ + ""+ �������� + "#k  ";			
			
			

            cm.sendOk(text);
        }  
else if (selection == 1) {
            if (!cm.checkNumSpace(1, 1)) {
			cm.sendOk("����װ�����ռ䲻��1��");
			cm.dispose();
			return;
		    }
			else if(cm.getMeso() < 10000000) {
            cm.sendOk("��Ǹ���Ľ�Ҳ���1000���������������");
            cm.dispose();
			}
			
			else if(cm.haveItem(4310071,300) ){
				cm.gainMeso(-10000000);
				cm.gainItem(4310071,-300);
				cm.gainItem(1032205,1);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "����ʮ�߹ء�" + " : " + "[" + cm.getChar().getName() + "]������񻰶�����")); 
				cm.dispose();
			}else{
				cm.sendOk("\t�������������300���������ˡ�");
				cm.dispose();
			}
			 
}
else if (selection == 2) {
            if (!cm.checkNumSpace(1, 1)) {
			cm.sendOk("����װ�����ռ䲻��1��");
			cm.dispose();
			return;
		    }
			else if(cm.getMeso() < 20000000) {
            cm.sendOk("��Ǹ���Ľ�Ҳ���2000���������������");
            cm.dispose();
			return;
			}
			else if(!cm.haveItem(1032205,1) ){
            cm.sendOk("��Ǹ��û��#i1032205:#�����Ȼ�ȡ��������������Ϊ�񻰽�ָI��");
            cm.dispose();
			return;
			}
			
			else if(cm.haveItem(4310071,600) ){
				cm.gainMeso(-20000000);
				cm.gainItem(4310071,-600);
				cm.gainItem(1032205,-1);
				cm.gainItem(1032206,1);
				
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "����ʮ�߹ء�" + " : " + "[" + cm.getChar().getName() + "]������񻰶�����")); 
				cm.dispose();
			}else{
				cm.sendOk("\t�������������600���������ˡ�");
				cm.dispose();
			}
			 
}
else if (selection == 3) {
            if (!cm.checkNumSpace(1, 1)) {
			cm.sendOk("����װ�����ռ䲻��1��");
			cm.dispose();
			return;
		    }
			else if(cm.getMeso() < 40000000) {
            cm.sendOk("��Ǹ���Ľ�Ҳ���4000���������������");
            cm.dispose();
			return;
			}
			else if(!cm.haveItem(1032206,1) ){
            cm.sendOk("��Ǹ��û��#i1032206:#�����Ȼ�ȡ��������������Ϊ�񻰽�ָII��");
            cm.dispose();
			return;
			}
			
			else if(cm.haveItem(4310071,1000) ){
				cm.gainMeso(-40000000);
				cm.gainItem(4310071,-1000);
				cm.gainItem(1032206,-1);
				cm.gainItem(1032207,1);
				
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "����ʮ�߹ء�" + " : " + "[" + cm.getChar().getName() + "]������񻰶�����")); 
				cm.dispose();
			}else{
				cm.sendOk("\t�������������1000���������ˡ�");
				cm.dispose();
			}
			 
}
else if (selection == 4) {
            if (!cm.checkNumSpace(1, 1)) {
			cm.sendOk("����װ�����ռ䲻��1��");
			cm.dispose();
			return;
		    }
			else if(cm.getMeso() < 80000000) {
            cm.sendOk("��Ǹ���Ľ�Ҳ���8000���������������");
            cm.dispose();
			return;
			}
			else if(!cm.haveItem(1032207,1) ){
            cm.sendOk("��Ǹ��û��#i1032207:#�����Ȼ�ȡ��������������Ϊ�񻰽�ָIII");
            cm.dispose();
			return;
			}
			
			else if(cm.haveItem(4310071,2000) ){
				cm.gainMeso(-80000000);
				cm.gainItem(4310071,-2000);
				cm.gainItem(1032207,-1);
				cm.gainItem(1032208,1);
				
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "����ʮ�߹ء�" + " : " + "[" + cm.getChar().getName() + "]������񻰶�����")); 
				cm.dispose();
			}else{
				cm.sendOk("\t�������������2000���������ˡ�");
				cm.dispose();
			}
			 
}
else if (selection == 5) {
            if (!cm.checkNumSpace(1, 1)) {
			cm.sendOk("����װ�����ռ䲻��1��");
			cm.dispose();
			return;
		    }
			else if(cm.getMeso() < 200000000) {
            cm.sendOk("��Ǹ���Ľ�Ҳ���2�ڣ��������������");
            cm.dispose();
			return;
			}
			else if(!cm.haveItem(1032208,1) ){
            cm.sendOk("��Ǹ��û��#i1032208:#�����Ȼ�ȡ��������������Ϊ�񻰽�ָIV");
            cm.dispose();
			return;
			}
			
			else if(cm.haveItem(4310071,5000) ){
				cm.gainMeso(-200000000);
				cm.gainItem(4310071,-5000);
				cm.gainItem(1032208,-1);
				cm.gainItem(1032209,1);
				
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "����ʮ�߹ء�" + " : " + "[" + cm.getChar().getName() + "]������񻰶�����")); 
				cm.dispose();
			}else{
				cm.sendOk("\t�������������5000���������ˡ�");
				cm.dispose();
			}
			 
}
else if (selection == 6) {
            if (!cm.checkNumSpace(1, 1)) {
			cm.sendOk("����װ�����ռ䲻��1��");
			cm.dispose();
			return;
		    }
			else if(cm.getMeso() < 500000000) {
            cm.sendOk("��Ǹ���Ľ�Ҳ���5�ڣ��������������");
            cm.dispose();
			return;
			}
			else if(!cm.haveItem(1032209,1) ){
            cm.sendOk("��Ǹ��û��#i1032209:#�����Ȼ�ȡ��������������Ϊ�񻰽�ָ��");
            cm.dispose();
			return;
			}
			
			else if(cm.haveItem(4310071,9999) ){
				cm.gainMeso(-500000000);
				cm.gainItem(4310071,-9999);
				cm.gainItem(1032209,-1);
				cm.gainItem(1032219,1);
				
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "����ʮ�߹ء�" + " : " + "[" + cm.getChar().getName() + "]������񻰶�����")); 
				cm.dispose();
			}else{
				cm.sendOk("\t�������������9999���������ˡ�");
				cm.dispose();
			}
			 
}
   
}}
var ���� = "#fEffect/CharacterEff/1022223/4/0#";
var �����Ʒ = "#v1302000#";
var add = "#fEffect/CharacterEff/1112903/0/0#";//������
var aaa = "#fUI/UIWindow.img/Quest/icon9/0#";//��ɫ�Ҽ�ͷ
var zzz = "#fUI/UIWindow.img/Quest/icon8/0#";//��ɫ�Ҽ�ͷ
var sss = "#fUI/UIWindow.img/QuestIcon/3/0#";//ѡ�����
var ������ͷ = "#fUI/Basic/BtHide3/mouseOver/0#";
var ��̾�� = "#fUI/UIWindow/Quest/icon0#";
var ����new = "#fUI/UIWindow/Quest/icon5/1#";
var ��ɫ��ͷ = "#fEffect/CharacterEff/1112908/0/1#";  //�ʹ�3
var ttt1 = "#fEffect/CharacterEff/1062114/1/0#";  //����
var ��ɫ�ǵ� = "#fUI/UIWindow.img/PvP/Scroll/enabled/next2#";
var ������ͷ = "#fUI/Basic/BtHide3/mouseOver/0#";
var ���� = "#fEffect/CharacterEff/1022223/4/0#";
var �۰��� = "#fItem/Etc/0427/04270005/Icon8/1#";  //
var �ջ� = "#fUI/PredictHarmony/card/19#";//��ƬЧ���ջ�
var Ц = "#fUI/GuildBBS/GuildBBS/Emoticon/Basic/0#";//Ц��
var ���Ҷ ="#fMap/MapHelper/weather/maple/2#";
var ���Ҷ ="#fMap/MapHelper/weather/maple/1#";
var ��Ů ="#fMap/MapHelper/weather/witch/0#";//��Ů
var ���� ="#fMap/MapHelper/weather/balloon/4#";//����
var ��� ="#fMap/MapHelper/weather/LoveEffect2/4/0#";//���
var õ�� ="#fMap/MapHelper/weather/rose/0#";//õ�廨
var �̻� ="#fMap/MapHelper/weather/squib/squib1/3#";//�̻�

var ��ۺ찮�� = "#fItem/Etc/0427/04270001/Icon8/4#";  //
var С�ۺ찮�� = "#fItem/Etc/0427/04270001/Icon8/5#";  //
var С���� = "#fItem/Etc/0427/04270001/Icon9/0#";  //
var ����� = "#fItem/Etc/0427/04270001/Icon9/1#";  //
var Сˮ�� = "#fItem/Etc/0427/04270001/Icon10/5#";  //
var ��ˮ�� = "#fItem/Etc/0427/04270001/Icon10/4#";  //
var tz = "#fEffect/CharacterEff/1082565/4/0#";  //������
var tz1 = "#fEffect/CharacterEff/1082565/0/0#";  //������
var tz2 = "#fEffect/CharacterEff/1082565/2/0#";  //������
var а��С�� = "#fEffect/CharacterEff/1112960/3/0#";  //а��С�� ��С��
var а��С��2 = "#fEffect/CharacterEff/1112960/3/1#";  //а��С�� ����
var ���� ="#fEffect/SetEff/208/effect/walk2/4#";
var ����1 ="#fEffect/SetEff/208/effect/walk2/3#";
var С�� ="#fMap/MapHelper/weather/birthday/2#";
var �һ� ="#fMap/MapHelper/weather/rose/4#";
var ����Ҷ ="#fMap/MapHelper/weather/maple/3#";
var С�̻� ="#fMap/MapHelper/weather/squib/squib4/1#";
var ���� ="#fMap/MapHelper/weather/witch/3#";
var �����Ʒ = "#v1302000#";
var x1 = "1302000,+1";// ��ƷID,����
var x2;
var x3;
var x4;
var ��ӡ = "#fEffect/Direction1.img/effect/aran/finishLogo1/0/3#";
var ������ = "#fEffect/CharacterEff.img/1032054/0/0#";
var ���� = "#fEffect/CharacterEff.img/1052203/1/0#";
var ���� = "#fEffect/CharacterEff.img/1052203/2/0#";
var ������ = "#fEffect/CharacterEff.img/1022223/2/0#";
var ����� = "#fEffect/CharacterEff.img/1022223/1/0#";
var ���� = "#fEffect/CharacterEff.img/1022223/6/0#";
var ��� = "#fEffect/CharacterEff.img/1022223/7/0#";
var ������ = "#fEffect/CharacterEff.img/1051296/1/0#";
var ��־ = "#fUI/StatusBar.img/base/iconMemo#";
var Ӣ�ı�ʶ = "#fEffect/SetEff.img/161/effect/6#";
var ������ = "#fUI/UIWindow.img/counsel/Loading/0#";
var ���� = "#fEffect/CharacterEff/1022223/4/0#";
var ��ɫ��ͷ = "#fUI/UIWindow/Quest/icon6/7#";
var ��ɫ�ǵ� = "#fUI/UIWindow.img/PvP/Scroll/enabled/next2#";
var ��Ҷ = "#fUI/ITC.img/Base/Tab/Enable/0#";
var ��1 = "#fUI/GuildMark/Mark/Animal/00002011/1#";
var ��2 = "#fUI/GuildMark/Mark/Animal/00002011/2#";
var ��3 = "#fUI/GuildMark/Mark/Animal/00002011/3#";
var ��4 = "#fUI/GuildMark/Mark/Animal/00002011/4#";
var ��5 = "#fUI/GuildMark/Mark/Animal/00002011/5#";
var ��6 = "#fUI/GuildMark/Mark/Animal/00002011/6#";
var �ڱ� = "#fUI/GuildMark.img/Mark/Pattern/00004020/16#";
var �ڻ� = "#fUI/GuildMark.img/Mark/Etc/00009018/16#";
var �ڵ� = "#fUI/GuildMark.img/Mark/Etc/00009016/16#";
var �ڹ� = "#fUI/GuildMark.img/Mark/Etc/00009013/16#";
var ��Ȧ = "#fUI/Gateway.img/WorldSelect/select/3#";
var �ڹ� = "#fUI/GuildMark.img/Mark/Etc/00009004/16#";
var ��� = "#fUI/GuildMark.img/Mark/Etc/00009023/14#";
var ��Ҷ = "#fUI/ITC.img/Base/Tab/Enable/0#";
var ��� = "#fUI/Basic.img/BtCoin/normal/0#";
var ���� = "#fUI/CN_Chat.img/roomList/Vip#";
var ͼ�� = "#fUI/Login.img/WorldSelect/world/t1#";
var �ָ��� = "#fUI/CashShop.img/CSDiscount/Line#";
var ��� = "#fUI/UIWindow.img/UserInfo/bossPetCrown#";
var С���� = "#fUI/UIWindow.img/UserList/Party/icon0#";
var �ʹ� = "#fUI/UIWindow.img/SkillMacro/Macroicon/4/iconMouseOver#";
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
var ���� = "#fUI/ChatBalloon.img/pet/18/head#";
var ������ = "#fUI/ChatBalloon.img/pet/25/n#";
var ����ͷ = "#fUI/ChatBalloon.img/pet/18/nw#";
var ����ͷ = "#fUI/ChatBalloon.img/pet/18/ne#";
var ������ = "#fUI/ChatBalloon.img/pet/28/nw#";
var ������ = "#fUI/ChatBalloon.img/pet/28/ne#";
var ������ = "#fUI/ChatBalloon.img/pet/28/arrow#";
var ������ͷ = "#fUI/ChatBalloon.img/pet/28/se#";
var ������ͷ = "#fUI/ChatBalloon.img/pet/28/sw#";
var ������ = "#fUI/ChatBalloon.img/pet/25/head#";
var ������ = "#fUI/ChatBalloon.img/pet/25/s#";
var �������� = "#fUI/ChatBalloon.img/pet/25/sw#";
var �������� = "#fUI/ChatBalloon.img/pet/25/se#";
var ������ = "#fUI/ChatBalloon.img/pet/25/nw#";
var ������ = "#fUI/ChatBalloon.img/pet/25/ne#";
var ���� = "#fUI/Basic.img/VScr6/enabled/thumb0#";
var ��V = "#fUI/Login.img/Title/check2/1#";
var ������ = "#fUI/Login.img/CharSelect/icon/up#";
var ��N = "#fUI/Login.img/CharSelect/icon/new#";
var ������ = "#fUI/GuildMark.img/Mark/Pattern/00004001/11#";
var ���� = "#fUI/GuildMark.img/Mark/Pattern/00004007/14#";
var ���� = "#fUI/GuildMark.img/Mark/Pattern/00004014/11#";