var acc = "#fEffect/CharacterEff/1112903/0/0#";//红桃心
var aaa = "#fUI/UIWindow.img/Quest/icon9/0#";//红色右箭头
var zzz = "#fUI/UIWindow.img/Quest/icon8/0#";//蓝色右箭头
var sss = "#fUI/UIWindow.img/QuestIcon/3/0#";//选择道具
var 蓝色角点 = "#fUI/UIWindow.img/PvP/Scroll/enabled/next2#";
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

//------------------------------------------------------------------------

var chosenMap = -1;
var monsters = 0;
var towns = 0;
var bosses = 0;
var fuben = 0;

//------------------------------------------------------------------------



//---------------------------------------------------------------------------
function start() {
    status = -1;
    action(1, 0, 0);
}
function action(mode, type, selection) {
    if (mode == -1) {
        cm.sendOk("#b好的,下次再见.");
        cm.dispose();
    } else {
        if (status >= 0 && mode == 0) {
            cm.sendOk("#b好的,下次再见.");
            cm.dispose();
            return;
        }
        if (mode == 1) {
            status++;
        } else {
            status--;
        }

//------------------------------------------------------------------------

        if (status == 0) {

            var add =  " \t\t#d#e#b#v4030001# 欢迎来到#r" + cm.getServerName() + " 合成中心 #e#b#v4030001##b#k#n\r\r\n";
				add += ""+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+"\r\n\r\n";
				add += "#e  #r全免费,#d只要肯花时间,那你就是最牛逼的靓仔/靓妹#n\r\n\r\n"
                        //add +="\t#d#e您今天已在线了：#r" + cm.getGamePoints() + "#k分钟,请注意休息哦~!#k#n\r\n";
						
               
				
				
				
				add += "#L1##r - - - <<<制作绝世武器>>> - - -【概率100%】#l\r\n";
				add += "#L2##r - - - <<<制作绝世套装>>> - - -【概率100%】#l\r\n";
				add += "#L3##r - - - <<<制作红绝武器>>> - - -【概率100%】#l\r\n";
				
				

            cm.sendSimple(add);

//------------------------------------------------------------------------

        } else if (status == 1) {


	    if (selection == 1) {
		cm.dispose();
        cm.openNpc(9310054, "150FFN概率合成");
	
            }
		if (selection == 2) {
		cm.dispose();
        cm.openNpc(9310054, "150高贵概率合成");
	
            }
		if (selection == 3) {
		cm.dispose();
        cm.openNpc(9310054, "红绝武器");
	
            }
	    
	    
			

		
             
        }
    }
}

