/* ==================
 脚本类型: 在线奖励	    
 脚本作者：枫叶   
 联系方式：1848350048
 =====================
 */
//------------------------------------------------------------------------

var chosenMap = -1;
var monsters = 0;//练级地图金币基础
var towns = 0;//城市传送基础金币
var bosses = 0;
var fuben = 0;

//------------------------------------------------------------------------

var bossmaps = Array(
		
					
		Array(230040420,380000,"鱼王BOSS                  #r（消耗38万金币）#b"), 
		Array(220080000,380000,"闹钟BOSS                  #r（消耗38万金币）#b"), 
		Array(211042300,380000,"扎昆BOSS                  #r（消耗38万金币）#b"),
        Array(702070400,380000,"妖僧BOSS                  #r（消耗38万金币）#b"),
        Array(541020700,380000,"树精BOSS                  #r（消耗38万金币）#b"),		
        Array(105100100,380000,"巨魔蝙蝠                  #r（消耗38万金币）#b"),			
		Array(240040700,380000,"暗黑龙王                  #r（消耗38万金币）#b"),
        Array(270000100,380000,"品克缤BOSS                #r（消耗38万金币）#b")		
		);

//------------------------------------------------------------------------

var monstermaps = Array(
		Array(104040000,500,"射手训练场#r（500金币）#b　　 　　适合 1 ~ 15 级玩家"),
		Array(101010100,580,"大木林#r（580金币）#b 　　　   　 适合 8 ~ 15 级玩家"),
        Array(103000101,680,"地铁<第1地区>#r（680金币）#b　  　适合 20 ~ 25 级玩家"),
		Array(220010500,780,"露台大厅#r（780金币）#b           适合 25 ~ 30 级玩家"),
		Array(101030001,880,"野猪的领土Ⅱ#r（880金币）#b　 　  适合 25 ~ 35 级玩家"),
		Array(103000105,980,"地铁<第4地区>#r（980金币）#b　  　适合 35 ~ 50 级玩家"), 
		Array(220040000,1180,"时间之路1#r（1180金币）#b　 　　　适合 45 ~ 60 级玩家"),
		Array(105040306,1280,"巨人之林#r（1280金币）#b　　 　 　适合 50 ~ 65 级玩家"),	
		Array(250010304,2280,"流浪熊的地盘#r（2280金币）#b 　 　适合 55 ~ 75 级玩家"),
		Array(251010402,2380,"海盗团老巢2#r（2380金币）#b　　 　适合 65 ~ 75 级玩家"),	 
		Array(541010010,2580,"幽灵船2#r（2580金币）#b　　　 　　适合 60 ~ 80 级玩家"),
		Array(600020300,2680,"狼蛛洞穴#r（2680金币）#b　　　  　适合 80 ~ 90 级玩家"),
		Array(240010500,2780,"山羊峡谷#r（2780金币）#b　　  　　适合 85 ~ 100 级玩家"), 
		Array(230040100,2880,"深海峡谷2#r（2880金币）#b　　 　　适合 90 ~ 100 级玩家"),
		Array(551030100,2980,"阴森世界入口#r（2980金币）#b　　　适合 95 ~ 120 级玩家"),
		Array(240030102,3080,"消失的树林#r（3080金币）#b　  　　适合 100 ~ 120 级玩家"),
		Array(240040511,3280,"被遗忘的龙之巢#r（3280金币）#b  　适合 105 ~ 130 级玩家"),		  
		Array(541020000,3580,"乌鲁城入口#r（3580金币）#b　　  　适合 105 ~ 150 级玩家")
		); 

//------------------------------------------------------------------------

var townmaps = Array(
		//Array(910000000,520,"自由市场#r             （消耗520金币）#b"), 
		//Array(701000210,0,"大擂台"), 
		Array(1000000,100,"彩虹岛新手村#r         （消耗1百金币）#b"), 
		Array(104000000,500,"明珠港#r               （消耗5百金币）#b"), 
		Array(100000000,800,"射手村#r               （消耗8百金币）#b"), 
		Array(101000000,800,"魔法密林#r             （消耗8百金币）#b"), 
		Array(102000000,800,"勇士部落#r             （消耗8百金币）#b"), 
		Array(103000000,800,"废弃都市#r             （消耗8百金币）#b"), 
		Array(120000000,800,"诺特勒斯号码头#r       （消耗8百金币）#b"),
		Array(105040300,1000,"林中之城#r             （消耗1千金币）#b"),
		Array(140000000,1000,"里恩#r                 （消耗1千金币）#b"),
		Array(200000000,1000,"天空之城#r             （消耗1千金币）#b"),
		Array(211000000,5000,"冰峰雪域#r             （消耗5千金币）#b"), 
		Array(230000000,1000,"水下世界#r             （消耗1千金币）#b"),  
		Array(222000000,1000,"童话村#r               （消耗1千金币）#b"), 
		Array(220000000,5000,"玩具城#r               （消耗5千金币）#b"),
		Array(701000000,5000,"东方神州#r             （消耗5千金币）#b"),
		Array(250000000,5000,"武陵#r                 （消耗5千金币）#b"), 
		Array(702000000,1000,"少林寺#r               （消耗1千金币）#b"), 
		Array(500000000,500,"泰国#r                 （消耗5百金币）#b"),
		Array(260000000,500,"阿里安特#r             （消耗5百金币）#b"),  
		Array(600000000,500,"新叶城#r               （消耗5百金币）#b"), 
		Array(240000000,5000,"神木村#r               （消耗5千金币）#b"),  
		Array(261000000,1000,"马加提亚#r             （消耗1千金币）#b"), 
		Array(221000000,1000,"地球防御本部#r         （消耗1千金币）#b"), 
		Array(251000000,1000,"百草堂#r               （消耗1千金币）#b"),
		Array(701000200,10000,"上海豫园#r             （消耗1万金币）#b"),
		Array(550000000,10000,"吉隆大都市#r           （消耗1万金币）#b"),
		Array(130000000,1000,"圣地#r                 （消耗1千金币）#b"),
		Array(551000000,1000,"甘榜村#r               （消耗1千金币）#b"),
		Array(801000000,1000,"昭和村#r               （消耗1千金币）#b"), 
		Array(540010000,1000,"新加坡机场#r           （消耗1千金币）#b"),
		Array(541000000,1000,"新加坡码头#r           （消耗1千金币）#b"),
		Array(300000000,1000,"艾林森林#r             （消耗1千金币）#b"), 
		Array(270000100,10000,"时间神殿#r             （消耗1万金币）#b"), 
		Array(702100000,10000,"藏经阁#r               （消耗1万金币）#b"), 
		Array(800000000,10000,"古代神社#r             （消耗1万金币）#b"), 
		Array(130000200,10000,"圣地岔路#r             （消耗1万金币）#b"),
		Array(925020000,1000,"武陵道场入口#r         （消耗1千金币）#b"),
		Array(930000010,1000,"森林入口#r             （消耗1千金币）#b"),	
		Array(702090101,1000,"英语村#r               （消耗1千金币）#b")
		//Array(700000000,10000,"红鸾宫#r               （消耗1万金币）#b")
		//Array(749020000,0,"国庆蛋糕地图")
		);

//------------------------------------------------------------------------

var fubenmaps = Array(
        //Array(109080000,0,"打椰子"),
        //Array(109080010,0,"冰地"),
        Array(109040001,0,"高地跳跳                #r（获得技能币）#k"),
		//Array(109030001,0,"上楼         "),
		//Array(109060000,0,"滚雪球"),
		Array(109010000,0,"#b寻宝                    #r（获得技能币）#k"),
		Array(105040316,10,"#b森林跳跳                #r（获得技能币）#k "),	
		Array(103000900,10,"#b地铁跳跳                #r（获得技能币）#k "),    
		//Array(280020000,10,"火山跳跳"), 
		Array(101000100,10,"#b忍苦跳跳                #r（获得技能币）#k ") 											
		);






//------------------------------------------------------------------------

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

   	    var add = "\t\t"+彩虹+"  #e#d 万 能 传 送 #k#n  #r  "+彩虹+"#b#k#n\r\r\n"+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+"\r\n　　　　　　　　　#r#k\r\n";
		
		add += "#L0##d#e#v1102747#城市地图#v1102747#\t\t#l";//"+熊猫+"    "+红爱心+"

		add += "#L1##d#e#v1312039#练级地图#v1312039##l\r\n\r\n";
		
		add += "#L10##d#e#v1001017#副本地图#v1001017##l"; 
		
		
		add += "         #L4##d#e#v1102319#跳跳地图#v1102319##l\r\n\r\n";
        if(cm.getPlayer().getLevel() > 119 ){		
		add += "\t\t\t#L3##d#e#v4000259#<BOSS地图>#v4000259##l\r\n\r\n";
		add += "\t\t\t#L11##r#e#v4000259#<BOSS重返>#v4000259##l"; 
		}
		cm.sendSimple (add);    

//------------------------------------------------------------------------
				
	} else if (status == 1) {

	if (selection == 0){
		var selStr = "#d　　　　　　　　　#v1102747#城市地图#v1102747##k#b";
		for (var i = 0; i < townmaps.length; i++) {
		selStr += "\r\n#L" + i + "#" + townmaps[i][2] + "";
		}
		cm.sendSimple(selStr);
		towns = 1;
		}

	if (selection == 1) {
		var selStr = "#d　　　　  　　#v1312039#多倍怪物练级地图#v1312039##k#b\r\n";
		for (var i = 0; i < monstermaps.length; i++) {
		selStr += "\r\n#L" + i + "#" + monstermaps[i][2] + "";
		}
		cm.sendSimple(selStr);
		monsters = 1;
		}

	if (selection == 2) {
		cm.dispose();
		cm.warp(701000210, 0);
		}
		
	if (selection == 10) {
		cm.dispose();
            cm.openNpc(9900004,6006);
		}	

    if (selection == 11 ){
		cm.dispose();
		cm.openNpc(3003332,  "BOSS重返");
	}
	if (selection == 3) {
		var selStr = "#k\r\n#d　　　　　　　　　#d#e#v4000259#BOSS地图#v4000259##k#b";
		for (var i = 0; i < bossmaps.length; i++) {
		selStr += "\r\n#L" + i + "#" + bossmaps[i][2] + "";
		}
		cm.sendSimple(selStr);
		bosses = 1;
		}

	if (selection == 4) {
		var selStr = "#d　　　　　　　　　#d#e#v1102319#跳跳地图#v1102319##k#b";
		for (var i = 0; i < fubenmaps.length; i++) {
		selStr += "\r\n#L" + i + "#" + fubenmaps[i][2] + "";
		}
		cm.sendSimple(selStr);
		fuben = 1;
		}


//------------------------------------------------------------------------

	} else if (status == 2) {

	if (towns == 1) {
		cm.sendYesNo("你确定要去 " + townmaps[selection][2] + "?");
		chosenMap = selection;
		towns = 2;

	} else if (monsters == 1) {
		cm.sendYesNo("你确定要去 " + monstermaps[selection][2] + "?");
		chosenMap = selection;
		monsters = 2;

	} else if (bosses == 1) {
		cm.sendYesNo("你确定要去 " + bossmaps[selection][2] + "?");
		chosenMap = selection;
		bosses = 2;

	} else if (fuben == 1) {
		cm.sendYesNo("你确定要去 " + fubenmaps[selection][2] + "?");
		chosenMap = selection;
		fuben = 2;

		}

//----------------------------------------------------------------------

	} else if (status == 3) {

	if (towns == 2) {
		if(cm.getMeso()>=townmaps[chosenMap][1]){
		cm.warp(townmaps[chosenMap][0], 0);
		cm.gainMeso(-townmaps[chosenMap][1]);
		}else{
		cm.sendOk("你没有足够的金币哦!");
		}
		cm.dispose();

	} else if (monsters == 2) {
		if(cm.getMeso()>=monstermaps[chosenMap][1]){
		cm.warp(monstermaps[chosenMap][0], 0);
		cm.gainMeso(-monstermaps[chosenMap][1]);
		}else{
		cm.sendOk("你没有足够的金币哦!");
		}
		cm.dispose();

	} else if (bosses == 2) {
		if(cm.getMeso()>=bossmaps[chosenMap][1]){
		cm.warp(bossmaps[chosenMap][0], 0);
		cm.gainMeso(-bossmaps[chosenMap][1]);
		}else{
		cm.sendOk("你没有足够的金币哦!");
		}
		cm.dispose();

	} else if (fuben == 2) {
		if(cm.getMeso()>=fubenmaps[chosenMap][1]){
		cm.warp(fubenmaps[chosenMap][0], 0);
		cm.gainMeso(-fubenmaps[chosenMap][1]);
		}else{
		cm.sendOk("你没有足够的金币哦!");
		}
		cm.dispose();

                }

//------------------------------------------------------------------------

		}
		}
		}
var acc = "#fEffect/CharacterEff/1112903/0/0#";//红桃心
var aaa = "#fUI/UIWindow.img/Quest/icon9/0#";//红色右箭头
var zzz = "#fUI/UIWindow.img/Quest/icon8/0#";//蓝色右箭头
var sss = "#fUI/UIWindow.img/QuestIcon/3/0#";//选择道具
var 蓝色角点 = "#fUI/UIWindow.img/PvP/Scroll/enabled/next2#";
var 蓝色箭头 = "#fUI/UIWindow/Quest/icon2/7#";

var 熊猫 = "#fCharacter/Cape/01102747/icon/#";

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
var 蓝色角点 = "#fUI/UIWindow.img/PvP/Scroll/enabled/next2#";
var 草莓 = "#fUI/GuildMark/Mark/Plant/00003000/1#"; // 红色草莓
var 草莓1 = "#fUI/GuildMark/Mark/Plant/00003000/10#"; // 淡蓝色草莓
var 草莓2 = "#fUI/GuildMark/Mark/Plant/00003000/11#"; // 紫色草莓
var 草莓3 = "#fUI/GuildMark/Mark/Plant/00003000/15#"; // 白色草莓
var 草莓4 = "#fUI/GuildMark/Mark/Plant/00003000/3#"; // 黄色草莓
var 草莓5 = "#fUI/GuildMark/Mark/Plant/00003000/8#"; // 绿色草莓
var 小黄星 = "#fItem/Etc/0427/04270001/Icon9/0#";  //
var 彩虹 ="#fEffect/ItemEff/1071085/effect/walk1/2#";
var 大黄星 = "#fItem/Etc/0427/04270001/Icon9/1#";  //
var 小兔 = "#fEffect/CharacterEff/1112960/3/0#";  //邪恶小兔 【小】
var 小水滴 = "#fItem/Etc/0427/04270001/Icon10/5#";  //
var 大水滴 = "#fItem/Etc/0427/04270001/Icon10/4#";  //
var 红爱心 ="#fEffect/CharacterEff/1112905/0/1#";
var 金币图标 = "#fUI/UIWindow.img/QuestIcon/7/0#";