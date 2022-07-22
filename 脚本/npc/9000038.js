/* ==================
 脚本类型: NPC
 脚本作者：-枫叶
 联系方式：1848350048
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
var year = ca.get(java.util.Calendar.YEAR); //获得年份
var month = ca.get(java.util.Calendar.MONTH) + 1; //获得月份
var day = ca.get(java.util.Calendar.DATE);//获取日
var hour = ca.get(java.util.Calendar.HOUR_OF_DAY); //获得小时
var minute = ca.get(java.util.Calendar.MINUTE);//获得分钟
var second = ca.get(java.util.Calendar.SECOND); //获得秒
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
            text = "\t#r#e"+ 红星 + ""+ 大红星 + ""+ 红点 + "当天在"+"" + cm.getChannelServer().getServerName() + ""+ 红蓝点 + "#k冒#d险#b" + cm.getGamePoints() + "分钟"+ 蓝点 + ""+ 大蓝星 + ""+ 蓝星 + "#k  \r\n";
			text += ""+ 黄条左 + ""+ 黄条上 + ""+ 黄条上 + ""+ 黄条上 + ""+ 黄条上 + ""+ 黄条上 + ""+ 黄条上 + ""+ 黄条上 + "" + 英文标识 + ""+ 黄条上 + ""+ 黄条上 + ""+ 黄条上 + ""+ 黄条上 + ""+ 黄条上 + ""+ 黄条上 + ""+ 黄条上 + ""+ 黄条右 + "#k\r\n\r\n";
			text += "                #d哈喽，我是咪咪商人\r\n";
			text += "  #d我需要足够的回忆币，才能想起传说中的神话故事\r\n";
			text += "#d你能帮我收集回忆币吗？做为回报，我会给你神话耳环";
			
            text +="#L1##e#d"+爱心+""+爱心+""+爱心+"#i1032205:#"+爱心+""+爱心+""+爱心+"#l    #L2##e#d"+爱心+""+爱心+""+爱心+"#i1032206:#"+爱心+""+爱心+""+爱心+"#l\r\n\r\n"
			
			
			text +="#L3##e#d"+爱心+""+爱心+""+爱心+"#i1032207:#"+爱心+""+爱心+""+爱心+"#l    #L4##e#d"+爱心+""+爱心+""+爱心+"#i1032208:#"+爱心+""+爱心+""+爱心+"#l\r\n\r\n"
            
            text +="#L5##e#d"+爱心+""+爱心+""+爱心+"#i1032209:#"+爱心+""+爱心+""+爱心+"#l    #L6##e#d"+爱心+""+爱心+""+爱心+"#i1032219:#"+爱心+""+爱心+""+爱心+"#l\r\n\r\n"
			
			text += ""+ 黄条下左 + ""+ 黄条下 + ""+ 黄条下 + ""+ 黄条下 + ""+ 黄条下 + ""+ 黄条下 + ""+ 黄条下 + ""+ 黄条下 + ""+ 黄条下 + ""+ 黄条下 + ""+ 黄条下 + ""+ 黄条下 + ""+ 黄条下 + ""+ 黄条下 + ""+ 黄条下 + ""+ 黄条下 + ""+ 黄条下 + ""+ 黄条下 + ""+ 黄条下 + ""+ 黄条下 + ""+ 黄条下 + ""+ 黄条下 + ""+ 黄条下右 + "#k  ";			
			
			

            cm.sendOk(text);
        }  
else if (selection == 1) {
            if (!cm.checkNumSpace(1, 1)) {
			cm.sendOk("背包装备栏空间不足1格");
			cm.dispose();
			return;
		    }
			else if(cm.getMeso() < 10000000) {
            cm.sendOk("抱歉您的金币不足1000万，请凑足了再来！");
            cm.dispose();
			}
			
			else if(cm.haveItem(4310071,300) ){
				cm.gainMeso(-10000000);
				cm.gainItem(4310071,-300);
				cm.gainItem(1032205,1);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『二十七关』" + " : " + "[" + cm.getChar().getName() + "]获得了神话耳环！")); 
				cm.dispose();
			}else{
				cm.sendOk("\t回忆币数量不足300个，购买不了。");
				cm.dispose();
			}
			 
}
else if (selection == 2) {
            if (!cm.checkNumSpace(1, 1)) {
			cm.sendOk("背包装备栏空间不足1格");
			cm.dispose();
			return;
		    }
			else if(cm.getMeso() < 20000000) {
            cm.sendOk("抱歉您的金币不足2000万，请凑足了再来！");
            cm.dispose();
			return;
			}
			else if(!cm.haveItem(1032205,1) ){
            cm.sendOk("抱歉您没有#i1032205:#，请先获取了它，再来提升为神话戒指I！");
            cm.dispose();
			return;
			}
			
			else if(cm.haveItem(4310071,600) ){
				cm.gainMeso(-20000000);
				cm.gainItem(4310071,-600);
				cm.gainItem(1032205,-1);
				cm.gainItem(1032206,1);
				
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『二十七关』" + " : " + "[" + cm.getChar().getName() + "]获得了神话耳环！")); 
				cm.dispose();
			}else{
				cm.sendOk("\t回忆币数量不足600个，购买不了。");
				cm.dispose();
			}
			 
}
else if (selection == 3) {
            if (!cm.checkNumSpace(1, 1)) {
			cm.sendOk("背包装备栏空间不足1格");
			cm.dispose();
			return;
		    }
			else if(cm.getMeso() < 40000000) {
            cm.sendOk("抱歉您的金币不足4000万，请凑足了再来！");
            cm.dispose();
			return;
			}
			else if(!cm.haveItem(1032206,1) ){
            cm.sendOk("抱歉您没有#i1032206:#，请先获取了它，再来提升为神话戒指II！");
            cm.dispose();
			return;
			}
			
			else if(cm.haveItem(4310071,1000) ){
				cm.gainMeso(-40000000);
				cm.gainItem(4310071,-1000);
				cm.gainItem(1032206,-1);
				cm.gainItem(1032207,1);
				
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『二十七关』" + " : " + "[" + cm.getChar().getName() + "]获得了神话耳环！")); 
				cm.dispose();
			}else{
				cm.sendOk("\t回忆币数量不足1000个，购买不了。");
				cm.dispose();
			}
			 
}
else if (selection == 4) {
            if (!cm.checkNumSpace(1, 1)) {
			cm.sendOk("背包装备栏空间不足1格");
			cm.dispose();
			return;
		    }
			else if(cm.getMeso() < 80000000) {
            cm.sendOk("抱歉您的金币不足8000万，请凑足了再来！");
            cm.dispose();
			return;
			}
			else if(!cm.haveItem(1032207,1) ){
            cm.sendOk("抱歉您没有#i1032207:#，请先获取了它，再来提升为神话戒指III");
            cm.dispose();
			return;
			}
			
			else if(cm.haveItem(4310071,2000) ){
				cm.gainMeso(-80000000);
				cm.gainItem(4310071,-2000);
				cm.gainItem(1032207,-1);
				cm.gainItem(1032208,1);
				
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『二十七关』" + " : " + "[" + cm.getChar().getName() + "]获得了神话耳环！")); 
				cm.dispose();
			}else{
				cm.sendOk("\t回忆币数量不足2000个，购买不了。");
				cm.dispose();
			}
			 
}
else if (selection == 5) {
            if (!cm.checkNumSpace(1, 1)) {
			cm.sendOk("背包装备栏空间不足1格");
			cm.dispose();
			return;
		    }
			else if(cm.getMeso() < 200000000) {
            cm.sendOk("抱歉您的金币不足2亿，请凑足了再来！");
            cm.dispose();
			return;
			}
			else if(!cm.haveItem(1032208,1) ){
            cm.sendOk("抱歉您没有#i1032208:#，请先获取了它，再来提升为神话戒指IV");
            cm.dispose();
			return;
			}
			
			else if(cm.haveItem(4310071,5000) ){
				cm.gainMeso(-200000000);
				cm.gainItem(4310071,-5000);
				cm.gainItem(1032208,-1);
				cm.gainItem(1032209,1);
				
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『二十七关』" + " : " + "[" + cm.getChar().getName() + "]获得了神话耳环！")); 
				cm.dispose();
			}else{
				cm.sendOk("\t回忆币数量不足5000个，购买不了。");
				cm.dispose();
			}
			 
}
else if (selection == 6) {
            if (!cm.checkNumSpace(1, 1)) {
			cm.sendOk("背包装备栏空间不足1格");
			cm.dispose();
			return;
		    }
			else if(cm.getMeso() < 500000000) {
            cm.sendOk("抱歉您的金币不足5亿，请凑足了再来！");
            cm.dispose();
			return;
			}
			else if(!cm.haveItem(1032209,1) ){
            cm.sendOk("抱歉您没有#i1032209:#，请先获取了它，再来提升为神话戒指★");
            cm.dispose();
			return;
			}
			
			else if(cm.haveItem(4310071,9999) ){
				cm.gainMeso(-500000000);
				cm.gainItem(4310071,-9999);
				cm.gainItem(1032209,-1);
				cm.gainItem(1032219,1);
				
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『二十七关』" + " : " + "[" + cm.getChar().getName() + "]获得了神话耳环！")); 
				cm.dispose();
			}else{
				cm.sendOk("\t回忆币数量不足9999个，购买不了。");
				cm.dispose();
			}
			 
}
   
}}
var 爱心 = "#fEffect/CharacterEff/1022223/4/0#";
var 礼包物品 = "#v1302000#";
var add = "#fEffect/CharacterEff/1112903/0/0#";//红桃心
var aaa = "#fUI/UIWindow.img/Quest/icon9/0#";//红色右箭头
var zzz = "#fUI/UIWindow.img/Quest/icon8/0#";//蓝色右箭头
var sss = "#fUI/UIWindow.img/QuestIcon/3/0#";//选择道具
var 正方箭头 = "#fUI/Basic/BtHide3/mouseOver/0#";
var 感叹号 = "#fUI/UIWindow/Quest/icon0#";
var 美化new = "#fUI/UIWindow/Quest/icon5/1#";
var 红色箭头 = "#fEffect/CharacterEff/1112908/0/1#";  //彩光3
var ttt1 = "#fEffect/CharacterEff/1062114/1/0#";  //爱心
var 蓝色角点 = "#fUI/UIWindow.img/PvP/Scroll/enabled/next2#";
var 正方箭头 = "#fUI/Basic/BtHide3/mouseOver/0#";
var 爱心 = "#fEffect/CharacterEff/1022223/4/0#";
var 粉爱心 = "#fItem/Etc/0427/04270005/Icon8/1#";  //
var 菊花 = "#fUI/PredictHarmony/card/19#";//卡片效果菊花
var 笑 = "#fUI/GuildBBS/GuildBBS/Emoticon/Basic/0#";//笑脸
var 金枫叶 ="#fMap/MapHelper/weather/maple/2#";
var 红枫叶 ="#fMap/MapHelper/weather/maple/1#";
var 巫女 ="#fMap/MapHelper/weather/witch/0#";//巫女
var 气球 ="#fMap/MapHelper/weather/balloon/4#";//气球
var 射箭 ="#fMap/MapHelper/weather/LoveEffect2/4/0#";//射箭
var 玫瑰 ="#fMap/MapHelper/weather/rose/0#";//玫瑰花
var 烟花 ="#fMap/MapHelper/weather/squib/squib1/3#";//烟花

var 大粉红爱心 = "#fItem/Etc/0427/04270001/Icon8/4#";  //
var 小粉红爱心 = "#fItem/Etc/0427/04270001/Icon8/5#";  //
var 小黄星 = "#fItem/Etc/0427/04270001/Icon9/0#";  //
var 大黄星 = "#fItem/Etc/0427/04270001/Icon9/1#";  //
var 小水滴 = "#fItem/Etc/0427/04270001/Icon10/5#";  //
var 大水滴 = "#fItem/Etc/0427/04270001/Icon10/4#";  //
var tz = "#fEffect/CharacterEff/1082565/4/0#";  //粉兔子
var tz1 = "#fEffect/CharacterEff/1082565/0/0#";  //橙兔子
var tz2 = "#fEffect/CharacterEff/1082565/2/0#";  //蓝兔子
var 邪恶小兔 = "#fEffect/CharacterEff/1112960/3/0#";  //邪恶小兔 【小】
var 邪恶小兔2 = "#fEffect/CharacterEff/1112960/3/1#";  //邪恶小兔 【大】
var 花草 ="#fEffect/SetEff/208/effect/walk2/4#";
var 花草1 ="#fEffect/SetEff/208/effect/walk2/3#";
var 小花 ="#fMap/MapHelper/weather/birthday/2#";
var 桃花 ="#fMap/MapHelper/weather/rose/4#";
var 银杏叶 ="#fMap/MapHelper/weather/maple/3#";
var 小烟花 ="#fMap/MapHelper/weather/squib/squib4/1#";
var 星星 ="#fMap/MapHelper/weather/witch/3#";
var 礼包物品 = "#v1302000#";
var x1 = "1302000,+1";// 物品ID,数量
var x2;
var x3;
var x4;
var 红印 = "#fEffect/Direction1.img/effect/aran/finishLogo1/0/3#";
var 红蓝点 = "#fEffect/CharacterEff.img/1032054/0/0#";
var 蓝星 = "#fEffect/CharacterEff.img/1052203/1/0#";
var 红星 = "#fEffect/CharacterEff.img/1052203/2/0#";
var 大蓝星 = "#fEffect/CharacterEff.img/1022223/2/0#";
var 大红星 = "#fEffect/CharacterEff.img/1022223/1/0#";
var 蓝点 = "#fEffect/CharacterEff.img/1022223/6/0#";
var 红点 = "#fEffect/CharacterEff.img/1022223/7/0#";
var 超蓝星 = "#fEffect/CharacterEff.img/1051296/1/0#";
var 标志 = "#fUI/StatusBar.img/base/iconMemo#";
var 英文标识 = "#fEffect/SetEff.img/161/effect/6#";
var 进行中 = "#fUI/UIWindow.img/counsel/Loading/0#";
var 爱心 = "#fEffect/CharacterEff/1022223/4/0#";
var 红色箭头 = "#fUI/UIWindow/Quest/icon6/7#";
var 蓝色角点 = "#fUI/UIWindow.img/PvP/Scroll/enabled/next2#";
var 枫叶 = "#fUI/ITC.img/Base/Tab/Enable/0#";
var 熊1 = "#fUI/GuildMark/Mark/Animal/00002011/1#";
var 熊2 = "#fUI/GuildMark/Mark/Animal/00002011/2#";
var 熊3 = "#fUI/GuildMark/Mark/Animal/00002011/3#";
var 熊4 = "#fUI/GuildMark/Mark/Animal/00002011/4#";
var 熊5 = "#fUI/GuildMark/Mark/Animal/00002011/5#";
var 熊6 = "#fUI/GuildMark/Mark/Animal/00002011/6#";
var 黑标 = "#fUI/GuildMark.img/Mark/Pattern/00004020/16#";
var 黑火 = "#fUI/GuildMark.img/Mark/Etc/00009018/16#";
var 黑点 = "#fUI/GuildMark.img/Mark/Etc/00009016/16#";
var 黑果 = "#fUI/GuildMark.img/Mark/Etc/00009013/16#";
var 红圈 = "#fUI/Gateway.img/WorldSelect/select/3#";
var 黑冠 = "#fUI/GuildMark.img/Mark/Etc/00009004/16#";
var 红冠 = "#fUI/GuildMark.img/Mark/Etc/00009023/14#";
var 枫叶 = "#fUI/ITC.img/Base/Tab/Enable/0#";
var 金币 = "#fUI/Basic.img/BtCoin/normal/0#";
var 星星 = "#fUI/CN_Chat.img/roomList/Vip#";
var 图标 = "#fUI/Login.img/WorldSelect/world/t1#";
var 分割线 = "#fUI/CashShop.img/CSDiscount/Line#";
var 金冠 = "#fUI/UIWindow.img/UserInfo/bossPetCrown#";
var 小星星 = "#fUI/UIWindow.img/UserList/Party/icon0#";
var 皇冠 = "#fUI/UIWindow.img/SkillMacro/Macroicon/4/iconMouseOver#";
var 奖励 = "#fUI/UIWindow.img/Quest/reward#";
var 蓝包 = "#fUI/UIWindow.img/MonsterCarnival/icon2#";
var 绿Q标 = "#fUI/UIWindow.img/QuestAlarm/BtQ/ani/0#";
var 蓝箭头 = "#fUI/UIWindow.img/itemSearch/BtRight/normal/0#";
var 蓝R = "#fUI/UIWindow.img/Minigame/Common/readyOn#";
var 箱子 = "#fUI/UIWindow.img/Delivery/icon4#";
var 勋章 = "#fUI/UIWindow.img/MonsterBook/fullMark#";
var 红方 = "#fUI/UIWindow.img/AriantMatch/characterIcon/0#";
var 蓝方 = "#fUI/UIWindow.img/AriantMatch/characterIcon/1#";
var 绿方 = "#fUI/UIWindow.img/AriantMatch/characterIcon/2#";
var 黄方 = "#fUI/UIWindow.img/AriantMatch/characterIcon/3#";
var 紫方 = "#fUI/UIWindow.img/AriantMatch/characterIcon/4#";
var 橙方 = "#fUI/UIWindow.img/AriantMatch/characterIcon/5#";
var 进行中 = "#fUI/UIWindow.img/counsel/Loading/3#";
var 五子棋 = "#fUI/ChatBalloon.img/miniroom/Omok#";
var 斜金币 = "#fUI/ChatBalloon.img/miniroom/PersonalShop#";
var 熊猫 = "#fUI/ChatBalloon.img/pet/1/nw#";
var 毛球 = "#fUI/ChatBalloon.img/pet/12/nw#";
var 蓝条 = "#fUI/ChatBalloon.img/pet/18/head#";
var 蓝条条 = "#fUI/ChatBalloon.img/pet/25/n#";
var 蓝左头 = "#fUI/ChatBalloon.img/pet/18/nw#";
var 蓝右头 = "#fUI/ChatBalloon.img/pet/18/ne#";
var 蓝左下 = "#fUI/ChatBalloon.img/pet/28/nw#";
var 蓝右下 = "#fUI/ChatBalloon.img/pet/28/ne#";
var 蓝下条 = "#fUI/ChatBalloon.img/pet/28/arrow#";
var 蓝右下头 = "#fUI/ChatBalloon.img/pet/28/se#";
var 蓝左下头 = "#fUI/ChatBalloon.img/pet/28/sw#";
var 黄条上 = "#fUI/ChatBalloon.img/pet/25/head#";
var 黄条下 = "#fUI/ChatBalloon.img/pet/25/s#";
var 黄条下左 = "#fUI/ChatBalloon.img/pet/25/sw#";
var 黄条下右 = "#fUI/ChatBalloon.img/pet/25/se#";
var 黄条左 = "#fUI/ChatBalloon.img/pet/25/nw#";
var 黄条右 = "#fUI/ChatBalloon.img/pet/25/ne#";
var 邮箱 = "#fUI/Basic.img/VScr6/enabled/thumb0#";
var 红V = "#fUI/Login.img/Title/check2/1#";
var 蓝升级 = "#fUI/Login.img/CharSelect/icon/up#";
var 黄N = "#fUI/Login.img/CharSelect/icon/new#";
var 蓝星星 = "#fUI/GuildMark.img/Mark/Pattern/00004001/11#";
var 爱琴 = "#fUI/GuildMark.img/Mark/Pattern/00004007/14#";
var 蓝镖 = "#fUI/GuildMark.img/Mark/Pattern/00004014/11#";