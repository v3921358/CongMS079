var 红色小爱心1 ="#fEffect/CharacterEff/1112905/0/1#";
var 浅黄小爱心 ="#fMap/MapHelper/weather/balloon/5#";
var 黑色小爱心 ="#fMap/MapHelper/weather/sweetHeart/0#";
var 黑色小爱心1 ="#fMap/MapHelper/weather/sweetHeart/1#";
var 黑色小爱心2 ="#fMap/MapHelper/weather/sweetHeart/2#";
var 黑色小爱心3 ="#fMap/MapHelper/weather/sweetHeart/3#";
var 淡黄小爱心 ="#fMap/MapHelper/weather/sweetHeart/5#";
var 蝴蝶 = "#fEffect/CharacterEff/1051366/1/0#"; // 蓝色蝴蝶
var 草莓 = "#fUI/GuildMark/Mark/Plant/00003000/1#"; // 红色草莓
var 草莓1 = "#fUI/GuildMark/Mark/Plant/00003000/10#"; // 淡蓝色草莓
var 草莓2 = "#fUI/GuildMark/Mark/Plant/00003000/11#"; // 紫色草莓
var 草莓3 = "#fUI/GuildMark/Mark/Plant/00003000/15#"; // 白色草莓
var 草莓4 = "#fUI/GuildMark/Mark/Plant/00003000/3#"; // 黄色草莓
var 草莓5 = "#fUI/GuildMark/Mark/Plant/00003000/8#"; // 绿色草莓
function start() {
	
	var texts = "\t\t\t\t #v3992025##e段段中心#k#n#v3992025#\r\n\r\n";
		//texts += "#L11# "+浅黄小爱心+" 返回拍卖菜单#l\r\n\r\n";
		
		
		//texts += "#L1#"+蝴蝶+"装备砸卷次数提升#l\r\n\r\n";
		texts += "#L109#"+蝴蝶+"装备解锁#l   \r\n\r\n";
		texts += "#L10999#"+蝴蝶+"破功中心#l   \r\n\r\n";
		texts += "#L106#"+蝴蝶+"终极BOSS#l\r\n\r\n";
		//texts += "#L1099#"+蝴蝶+"四转技能#l\r\n\r\n";
		//\t\t#L522#"+蝴蝶+"时装觉醒系统#k#l
		
		
		//texts += "#L10#"+蝴蝶+"面具强化升级#l\t\t\t#L0#"+蝴蝶+"HP/MP洗血洗蓝#l\r\n\r\n";
		//texts += "#L1#"+蝴蝶+"装备砸卷次数提升#l\t\t#L8#"+蝴蝶+"道具材料强化#l\r\n\r\n";
		//texts += "#L6#"+蝴蝶+"惊人混沌卷强化#l\t\t  #L5##k"+蝴蝶+"特殊装备强化#l\r\n\r\n";
		//texts += "#L522#"+蝴蝶+"时装觉醒系统#k#l\r\n\r\n";
		
		
		
		//texts += "#L3#"+蝴蝶+"进阶星之力强化升星#l\t  #L2#"+蝴蝶+"星之力强化升星#l\r\n\r\n";
		//texts += "#L18#"+浅黄小爱心+"多功能强化系统#l #L20#"+蝴蝶+"魔化星之力系统#l\r\n";
		//texts += "#L4#购买道具[强化星之力]#kX1000   "+蝴蝶+"价格: #r40000 #k点券#l\r\n";
		//texts += "#L7#购买道具[强化星之力]#kX1000   "+蝴蝶+"价格: #r40000 #k点券#l\r\n";
		//texts += "#L9#购买道具[魔化星之力]#kX1000   "+蝴蝶+"价格: #r40000 #k点券#l#l\r\n";
		
		cm.sendSimple(texts);
}

function action(mode, type, selection) {
	cm.dispose();
	if (selection == 0) {	//洗血洗蓝
		cm.openNpc(9310059, "快速洗血");
		
	}else if (selection == 3368) {	
		cm.openNpc(9310059, "扩增技能");
		
	}else if (selection == 106) {	
		cm.openNpc(9310059, "终极BOSS");
		
	}
	else if (selection == 1) {	
		cm.openNpc(9310059, 10);
		
	}else if (selection == 1893) {	
		cm.openNpc(9310059, 1893);
		
	}
	else if (selection == 1894) {	
		cm.openNpc(9900004, 1894);
		
	}
	else if (selection == 109) {	
		cm.openNpc(9310059, 109);
		
	}
	else if (selection == 1099) {	
		cm.openNpc(9900004, "四转技能");
		
	}
	else if (selection == 10999) {	
		cm.openNpc(9900004, "破功中心");
		
	}else if (selection == 3){
		cm.openNpc(9310059,3);
		
	}else if (selection == 4){
		if(cm.getPlayer().getNX() < 40000) {
			cm.sendOk("点券不足");
			cm.dispose();
		}else {
			cm.gainNX(-40000);
			cm.gainItem(3992025,1000);
			cm.sendOk("购买成功！");
			cm.dispose();
		}
		
	}else if (selection == 5){
		cm.openNpc(9310059,4);
		
	}else if (selection == 522){
		cm.openNpc(9310059,522);
		
	}else if (selection ==6){
		cm.openNpc(9310059,5);
		
	}else if (selection == 7){
		if (cm.getPlayer().getNX() < 40000) {
			cm.sendOk("点券不足");
			cm.dispose();
		}else {
			cm.gainNX(-40000);
			cm.gainItem(3992025,1000);
			cm.sendOk("购买成功！");
			cm.dispose();
		}
		
	}else if (selection ==8){
		cm.openNpc(9310059,6);
		
	}else if (selection ==18){
		cm.openNpc(9310059,2132);
		
	}else if (selection == 9){
		if(cm.getPlayer().getNX() < 40000) {
			cm.sendOk("点卷不足");
			cm.dispose();
		}else {
			cm.gainNX(-40000);
			cm.gainItem(3992010,1000);
			cm.sendOk("购买成功！");
			cm.dispose();
		}
		
	}else if (selection == 10) {	
		cm.openNpc(9310059, "装备强化");
		
	}else if (selection == 11) {	
		cm.openNpc(9310059, "拍卖功能");
		
}
}