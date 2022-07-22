var CY0 = "┣━━━━━━━━━━━━━━ ━━━━━━━━━━━━━━━━┫";
var CY1 = "┣       - 创意 -       ┫";
var CY2 = "┣ 玩法仿制  　定制脚本 ┫";
var CY3 = "┣ 技术支持 　 游戏顾问 ┫";
var CY4 = "┣ ＷＺ添加　  地图制作 ┫";
var CY5 = "┣ 加盾防御　  售登陆器 ┫";
var CY7 = "┣ 手游开服    端游开服 ┫";
var CY8 = "┣━━━━━━━━━━━━━━ ━━━━━━━━━━━━━━━━┫";
var CY9 = "┣   唯一QQ:3066318387  ┫";
var CY0 = "┣━━━━━━━━━━━━━━ ━━━━━━━━━━━━━━━━┫";
var ttt ="#fUI/UIWindow.img/Quest/icon9/0#";
var xxx ="#fUI/UIWindow.img/Quest/icon8/0#";
var sss ="#fUI/UIWindow.img/QuestIcon/3/0#";
var 卡拉奇 = "#fItem/Cash/0502/05021005/info/iconRaw#";  //

var status = 0;
var nx = 500000;//赋值50w
var jilv = 0;
var costa;
var xx = -1;

	function start() {
		status = -1;
		action(1, 0, 0);
		}
	function GetRandomNum(Min,Max){  
		var Range = Max - Min;  
		var Rand = Math.random();  
		return(Min + Math.round(Rand * Range));  
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
		if (mode == 1)
		status++;
		else
		status--;





	if (status == 0) {
		
   	    var add = "\t\t\t\t #e#r"+卡拉奇+"金币开心中心"+卡拉奇+"#k\r\n\r\n";

		add += " "+xxx+"-增加投入请点#e#b[加注]#n#k\r\n ";

		add += ""+xxx+"-加倍赔率由左到右赔率递增,奖金增加概率降低.\r\n ";

		add += ""+xxx+"-当前投入押金:#b<#e#r 金币娱乐 #n#b>#b<#e#r "+nx+" 金币#n#b >#k\r\n";

		add += "#L0#"+ttt+"-[#r加注#k]#l\r\n\r\n";

		add += "#L1#"+ttt+"-[#b1:1倍赔率#k]#l";

		add += "#L2#"+ttt+"-[#b1:2倍赔率#k]#l";

		add += "#L3#"+ttt+"-[#b1:3倍赔率#k]#l";

		cm.sendSimple (add);

	} else if (status == 1) {

	if (selection == 0){
		cm.sendOk("#b成功投入#r10W金币#b,请点确定后查看.");
		nx = nx + 100000
		status = -1; 

	} else if (selection == 1){

   	    var add = "#b<#e#r 金币娱乐 #n#b>\r\n\r\n";

		add += ""+ttt+"-您选择的是[#r赔率1:1#b].\r\n";

		add += ""+ttt+"-您的投入为[#r"+nx+"个金币#b].\r\n";

		add += ""+ttt+"-如果胜利将获取[#r除本金外"+nx*1+"金币#b]的奖励.\r\n";

		add += ""+ttt+"-点击[#r是#b]开始,点击[#r不是#b]放弃.";

		cm.sendYesNo (add); 

		jilv = 1; 

		xx=0

	} else if (selection == 2){

   	    var add = "#b<#e#r 金币娱乐 #n#b>\r\n\r\n";

		add += ""+ttt+"-您选择的是[#r赔率1:2#b].\r\n";

		add += ""+ttt+"-您的投入为[#r"+nx+"个金币#b].\r\n";

		add += ""+ttt+"-如果胜利将获取[#r除本金外"+nx*2+"金币#b]的奖励.\r\n";

		add += ""+ttt+"-点击[#r是#b]开始,点击[#r不是#b]放弃.";

		cm.sendYesNo (add); 

		jilv = 2; 

		xx=0

	} else if (selection == 3){

   	    var add = "#b<#e#r 金币娱乐 #n#b>\r\n\r\n";

		add += ""+ttt+"-您选择的是[#r赔率1:3#b].\r\n";

		add += ""+ttt+"-您的押注为[#r"+nx+"个金币#b].\r\n";

		add += ""+ttt+"-如果胜利将获取[#r除本金外"+nx*3+"金币#b]的奖励.\r\n";

		add += ""+ttt+"-点击[#r是#b]开始,点击[#r不是#b]放弃.";

		cm.sendYesNo (add); 

		jilv = 3; 

		xx=0

		}

	} else if (status == 2) {

	if (xx == 0){
		if (jilv == 0){
		} else if (jilv != 0){
		if (cm.getPlayer().getMeso() < nx) {//判断抵用卷多少
		cm.sendOk("#b您的金币不足,不能参加.....");
		status = -1; 
		}else {
		jiaru = GetRandomNum(0,jilv);
		if (jiaru == 0) {
		nx = nx * jilv
		cm.gainMeso(nx);
		cm.sendOk("#b恭喜,您已经大获全胜...");
		cm.worldMessage(5,"[豆豆屋-绿水灵机]:在金币娱乐中胜利了,【"+cm.getName()+"】获得了["+nx+"金币]");
        status = -1; 
		} else {
		cm.gainMeso(-nx);
		cm.sendOk("#b悲剧啊.你输了....");
		//cm.worldMessage(5,"[豆豆屋-绿水灵机]:在金币娱乐中失败了,【"+cm.getName()+"】失去了["+nx+"金币]");
		status = -1; 
		}
		}
		}





		









		}					
		}
		}
		}

