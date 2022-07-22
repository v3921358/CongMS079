var 聊天 = "#fUI/StatusBar/BtChat/normal/0#";
var 爱心 = "#fEffect/CharacterEff/1022223/4/0#";
var 红色箭头 = "#fUI/UIWindow/Quest/icon6/7#";
var 正方形 = "#fUI/UIWindow/Quest/icon3/6#";
var 蓝色箭头 = "#fUI/UIWindow/Quest/icon2/7#";
var 蓝色角点 = "#fUI/UIWindow.img/PvP/Scroll/enabled/next2#";
var 正在进行中 = "#fUI/UIWindow/Quest/Tab/enabled/1#";
var 完成 = "#fUI/UIWindow/Quest/Tab/enabled/2#";
var 正在进行中蓝 = "#fUI/UIWindow/MonsterCarnival/icon1#";
var 完成红 = "#fUI/UIWindow/MonsterCarnival/icon0#";
var 礼包物品 = "#v1302000#";
var x1 = "1302000,+1";// 物品ID,数量
var x2;
var x3;
var x4;
var 蓝色箭头 = "#fUI/UIWindow/Quest/icon2/7#";
var 红色箭头 = "#fUI/UIWindow/Quest/icon6/7#";
var 圆形 = "#fUI/UIWindow/Quest/icon3/6#";
var 美化new = "#fUI/UIWindow/Quest/icon2/7#";
var 美化ne = "#fUI/UIWindow/Quest/icon6/7#";
var 感叹号 = "#fUI/UIWindow/Quest/icon0#";
var 正方箭头 = "#fUI/Basic/BtHide3/mouseOver/0#";
var 中条猫 ="#fUI/ChatBalloon/37/n#";
var 猫右 =  "#fUI/ChatBalloon/37/ne#";
var 猫左 =  "#fUI/ChatBalloon/37/nw#";
var 右 =    "#fUI/ChatBalloon/37/e#";
var 左 =    "#fUI/ChatBalloon/37/w#";
var 下条猫 ="#fUI/ChatBalloon/37/s#";
var 猫下右 ="#fUI/ChatBalloon/37/se#";
var 猫下左 ="#fUI/ChatBalloon/37/sw#";
var 皇冠白 ="#fUI/GuildMark/Mark/Etc/00009004/16#";
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

            cm.sendOk("感谢你的光临！");
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
            var text = ""
            for (i = 0; i < 10; i++) {
                text += "";
            }
			//text += "\t\t\t  #e欢迎来到#r" + cm.getChannelServer().getServerName() + " #k!#n\r\n"
            //text += "#b特别注意：萌新请看群文件的新人指引说明，这个很重要\r\n\r\n"//3
			//text += "#r1.本服福利多，长期更新活动，适合休闲玩耍#l\r\n\r\n"//3
           // text += "#r2.本服禁止使用外挂，否则帐号或角色就会被永久封禁，请萌新必须引起注意！#l\r\n\r\n"//3
           // text += "#r3.新手礼包已经发放到您的背包，请注意查收！#l\r\n\r\n"//3
           // text += "#r4.本服充值比例：1RMB=1充值币=1000点券#l\r\n\r\n"//3
           // text += "#r5.修复副本+任务+部分功能，增加大量特色功能，PK系统、全面改进！#l\r\n\r\n"//3 
           // text += "#r6.欢迎加入我们的冒险岛079群一起讨论：321768088#l\r\n\r\n"//3
			//text += "#r7.本服怪物比其它服增加多一倍，详情看拍卖练级传送#l\r\n\r\n"//3
		text += "                   #k"+皇冠白+" #r#e#w" + cm.getServerName() + "#n#k "+皇冠白+"\r\n\r\n"
		text += ""+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+"\r\n\r\n"
		text += "#b\t\t一直以来，我一直想做一个能唤起我们回忆的冒险岛\r\n"//3
		text += "#b\t\t经过了很多尝试，试过微变，也试过巨变，这一\r\n"//3	
		text += "#b\t\t路走来，讲道理，那是各种磕磕绊绊，最终确定自\r\n"//3	
		text += "#b\t己来啃079这个硬骨头，目前已经完美修复，呵呵，这的\r\n"//3	
		text += "#b\t确很困难，这螃蟹不容易吃。\r\n"//3	
		text += "#b\t\t赞助方面大家随意，赞助仅仅为了服务器的日常\r\n"//3	
		text += "#b\t\运营，欢迎大家白嫖，来玩就是给面子，大家有什么意\r\n"//3	
		text += "#b\t见尽管告诉我，我会虚心接受。\r\n"//3	
		text += "#b\t\t目前武器装备以及发型已经更新到官方版本的160，\r\n"//3	
		text += "#b\t后续会继续更新，以及源码的简体化，感谢大家的信任\r\n"//3			
		text += "#b\t\t\t\t\t\t\t\t\t\t\t童话\r\n"//3
			
			
			
			
			
            cm.sendOk(text);
			//cm.喇叭(2,"欢迎新人[" + cm.getPlayer().getName() + "]！！大家祝贺吧！！！~又一名新人加入了蓝蜗牛冒险岛~！"); 
			
		//cm.全服黄色喇叭("[萌新驾到] : " + " : " + " 玩家  [" + cm.getChar().getName() + "] 加入了 [ " + cm.getChannelServer().getServerName() + " ] 大家祝贺他（她）！！！"); 
			
		//cm.喇叭(5,"哇塞，火星撞地球了！欢迎新人 [ " + cm.getPlayer().getName() + " ] 加入了 [ " + cm.getChannelServer().getServerName() + " ] ！！！大家祝贺他(她) ！！！");    

		//cm.getPlayer().dropMessage(9,"哇塞，火星撞地球了！欢迎新人 [ " + cm.getPlayer().getName() + " ] 加入了 [ " + cm.getChannelServer().getServerName() + " ] ！！！大家祝贺他(她) ！！！");  			
		    cm.dispose();
		}
    }
}
