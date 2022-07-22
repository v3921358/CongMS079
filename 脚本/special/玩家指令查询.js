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

		text += "                   #k"+皇冠白+" #r#e#w" + cm.getServerName() + "#n#k "+皇冠白+"\r\n\r\n"
		text += ""+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+"\r\n\r\n"
		text += "#b\t@帮助/@help#k  -  #d<查看可以使用的指令>\r\n"
		text += "#b\t@解卡/@ea#k  -  #d<解卡异常状态，查看系统信息>\r\n"
		text += "#b\t@怪物/@Mob#k  -  #d<查看身旁怪物信息>\r\n"
		text += "#b\t@万能/@npc#k  -  #d<打开拍卖菜单>\r\n"
		text += "#b\t@解卡组队  -  #d<解卡异常组队状态，使用后重启就好了>\r\n"
		text += "#b\t@fm#k  -  #d<回到自由市场，部分地图无法使用>\r\n"	
		text += "#b\t@cgm#k  -  #d<发信息给在线管理员>\r\n"
		

			
			
			
			
			
            cm.sendOk(text);
			
		//cm.全服黄色喇叭("[萌新驾到] : " + " : " + " 玩家  [" + cm.getChar().getName() + "] 加入了 [ " + cm.getChannelServer().getServerName() + " ] 大家祝贺他（她）！！！"); 
			
		    cm.dispose();
		}
    }
}
