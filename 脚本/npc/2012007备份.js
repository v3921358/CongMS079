/* Rinz the assistant
	Orbis Random Hair/Hair Color Change.
*/
var 中条蓝 ="#fUI/ChatBalloon/tutorial/w#";
var 中条猫 ="#fUI/ChatBalloon/37/n#";
var 猫右 =  "#fUI/ChatBalloon/37/ne#";
var 猫左 =  "#fUI/ChatBalloon/37/nw#";
var 右 =    "#fUI/ChatBalloon/37/e#";
var 左 =    "#fUI/ChatBalloon/37/w#";
var 下条猫 ="#fUI/ChatBalloon/37/s#";
var 猫下右 ="#fUI/ChatBalloon/37/se#";
var 猫下左 ="#fUI/ChatBalloon/37/sw#";
var 皇冠白 ="#fUI/GuildMark/Mark/Etc/00009004/16#";
var status = -1;
var beauty = 0;
var hair_Colo_new;
function start() {
    action(1, 0, 0);
}
function action(mode, type, selection) {
    if (mode == 0) {
	cm.dispose();
	return;
    } else {
	status++;
    }
    if (status == 0) {
	cm.sendSimple("                    #k"+皇冠白+" #r#e#w记忆冒险岛#n#k "+皇冠白+"\r\n\r\n    "+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+"\r\n	\r\n\t\t\t\t#e#d#L0#随机更换当前#r发型#d#l \r\n\r\n\t\t\t  #L1#随机更换当前#r发色");
    } else if (status == 1) {
	if (selection == 0) {
	    var hair = cm.getPlayerStat("HAIR");
	    hair_Colo_new = [];
	    beauty = 1;
	    if (cm.getPlayerStat("GENDER") == 0) {
			
		hair_Colo_new = [30020,30030,30040,30050,30060,30100,30110,30120,30130,30140,30150,30160,30170,30180,30190,30200,30210,30220,30230,30240,30250,30260,30270,30280,30290,30300,30310,30320,30330,30340,30350,30360,30370,30400,30410,30420,30430,30440,30450,30460,30470,30480,30490,30510,30520,30530,30540,30550,30560,30600,30610,30620,30630,30640,30650,30660,30670,30680,30700,30710,30720,30730,30740,30750,30760,30770,30780,30790,30800,30810,30820,30830,30840,30850,30860,30870,30880,30890,30900,30910,30920,30930,30940,30950,30990,33030,33040,33050,33060,33070,33080,33090,33100,33110,33120,33130,33140,33150,33160,33170,33200,33210,33220,33230,33240,33250,33260];
	    } else {
			
		hair_Colo_new = [31010,31020,31030,31040,31050,31060,31070,31080,31090,31100,31110,31120,31130,31140,31150,31160,31170,31180,31190,31200,31210,31220,31230,31240,31250,31260,31270,31280,31290,31300,31310,31320,31330,31340,31350,31400,31410,31420,31430,31440,31450,31460,31470,31480,31490,31510,31520,31530,31540,31550,31560,31610,31620,31630,31640,31650,31660,31670,31680,31690,31700,31710,31720,31730,31740,31750,31760,31770,31780,31790,31800,31810,31820,31830,31840,31850,31860,31870,31880,31890,31910,31920,31930,31940,31950,31990,34010,34040,34050,34060,34070,34080,34090,34100,34110,34120,34130,34140,34150,34160,34170,34180,34200,34210,34220,34230,34240,34250,34260];
	    }
	    for (var i = 0; i < hair_Colo_new.length; i++) {
		hair_Colo_new[i] = hair_Colo_new[i] + (hair % 10);
	    }
	    cm.sendYesNo("是否随机转换发型？？");
		cm.gainItem(5150004, -1);
		
	} else if (selection == 1) {
	    var currenthaircolo = Math.floor((cm.getPlayerStat("HAIR") / 10)) * 10;
	    hair_Colo_new = [];
	    beauty = 2;
	    for (var i = 0; i < 8; i++) {
		hair_Colo_new[i] = currenthaircolo + i;
	    }
	    cm.sendYesNo("是否要随机转换#r发色#k ？？");
	}
    } else if (status == 2){
	if (beauty == 1) {
	    if (cm.setRandomAvatar(5150004, hair_Colo_new) == 1) {
		cm.sendOk("享受!");
	    } else {
		cm.sendOk("您貌似沒有#b#t5150004##k..");
	    }
	} else {
	    if (cm.setRandomAvatar(5151004, hair_Colo_new) == 1) {
		cm.sendOk("享受!");
	    } else {
		cm.sendOk("您貌似沒有#b#t5151004##k..");
	    }
	}
	cm.dispose();
    }
}