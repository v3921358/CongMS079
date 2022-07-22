var FY0 = "┏━━━━━━━━━━━┓";
var FY1 = "┃       - 枫叶 -       ┃";
var FY2 = "┃ 脚本仿制  　定制脚本 ┃";
var FY3 = "┃ 技术支持 　 游戏顾问 ┃";
var FY4 = "┃ ＷＺ添加　  地图制作 ┃";
var FY5 = "┃ 加盾防御　  售登陆器 ┃";
var FY6 = "┣━━━━━━━━━━━┫";
var FY7 = "┃ 唯一QQ:1848350048    ┃";
var FY8 = "┗━━━━━━━━━━━┛";

var xiaomi = {        
   服务端: "xiaomi",
   普卡经验: 100000, 普卡金币: 50000,
   BOSS卡经验: 1000000, BOSS卡金币: 100000
};

var xmxsz = new Array(



{ 区域: "野外BOSS", 卡片ID: [ 2388000,2388007,2388003,2388044,2388016,2388032,2388006,2388008,2388015,2388025,2388010,2388013,2388035,2388031,2388026,2388017,2388005,2388004,2388002,2388009,2388011,2388012,2388014,2388039,2388029,2388030 ] },
{ 区域: "超级BOSS", 卡片ID: [ 2388042,2388040,2388041,2388033,2388018,2388019,2388020,2388022,2388023,2388045,2388024,2388043 ] },
{ 区域: "明珠海港", 卡片ID: [ 2380000,2380001,2380004,2380005 ] },
{ 区域: "射手之村", 卡片ID: [ 2380007,2380013,2380002,2380006,2380009,2381002,2382053 ] },//
{ 区域: "魔法密林", 卡片ID: [ 2380003,2380008,2380011,2382029,2382040,2383029,2382018 ] },
{ 区域: "勇士部落", 卡片ID: [ 2381000,2381008,2381022,2382003,2382065,2383044,2381001,2381082,2381014,2381018,2382063,2383005,2383036,2384003,2384029 ] },
{ 区域: "废弃都市", 卡片ID: [ 2382002,2383019,2381006,2380010,2380012,2381003,2382019,2383008 ] },
{ 区域: "林中之城", 卡片ID: [ 2381007,2381016,2381083,2381024,2382039,2383012,2384006,2384001,2384036,2384020,2383030,2383039,2383043 ] },
{ 区域: "天空之城", 卡片ID: [ 2382020,2382064,2383020,2382011,2382012,2382013,2383021,2383022,2383023,2384030,2382052,2383003 ] },
{ 区域: "冰封雪域", 卡片ID: [ 2381012,2381032,2381037,2381038,2382006,2382023,2383013,2383031,2383040,2384009,2385004,2383033,2384016,2385009,2384007,2384035,2385006] },
{ 区域: "神秘矿山", 卡片ID: [ 2383037,2383038,2382047,2382058,2384026,2385021,2383017 ] },
{ 区域: "水下世界", 卡片ID: [ 2382059,2382056,2382044,2382035,2382043,2382027,2382007,2381035,2381026,2381029,2381021,2381017,2381009,2381013 ] },
{ 区域: "海底深渊", 卡片ID: [ 2386000,2385013,2386003,2386007,2386012,2386014 ] },
{ 区域: "玩具之城", 卡片ID: [ 2381034,2382004,2382015,2382038,2382025,2382048,2382062,2382017,2382041,2382032,2383002,2382049 ] },
{ 区域: "时间裂缝", 卡片ID: [ 2384000,2384012,2384032,2385003,2385010,2385015,2386002,2386009,2384004,2384014,2384019,2385000,2385012,2385020,2386004,2386010,2387001,2387000 ] },
{ 区域: "地球本部", 卡片ID: [ 2382050,2382061,2383000,2382042,2382055,2382067 ] },
{ 区域: "武陵桃园", 卡片ID: [ 2382051,2382060,2382045,2382070,2382071,2384002,2383041,2383032,2384013,2383035,2383047] },
{ 区域: "百草药堂", 卡片ID: [ 2383006,2383010,2383025,2383027,2384008,2384017,2384025 ] },
{ 区域: "童话小镇", 卡片ID: [ 2382068,2383014,2383024,2383034,2383046 ] },
{ 区域: "东方神州", 卡片ID: [ 2381045,2381044,2381046,2382080,2381047,2382077,2382078,2382079] },
{ 区域: "神木森林", 卡片ID: [ 2384024,2384033,2384028,2385002,2385016,2385017,2385018,2385007,2385011,2385014,2385019] },
{ 区域: "龙之巢穴", 卡片ID: [ 2385022,2386001,2386016,2386011,2386005,2386006,2386008,2386013,2386015,2387002,2387003,2387004,2386017] }



);

//自定义单卡片的奖励
var xmdiykp = new Array(

{ 卡片ID: 2388043, 卡片经验: 5000000, 卡片金币: 1000000 },
{ 卡片ID: 2388024, 卡片经验: 3000000, 卡片金币: 500000 },
{ 卡片ID: 2388045, 卡片经验: 2000000, 卡片金币: 200000 }








);


//区域奖励详细参数设置  类型0=金币,类型1=点卷,类型2=抵用，数量是通用 直接物品ID自动识别装备和其他物品，
var xmqyjl = new Array(
{ 类型: 4000000, 数量: 300, 区域: "野外BOSS" },
{ 类型: 4000016, 数量: 100, 区域: "野外BOSS" },
{ 类型: 4000019, 数量: 50, 区域: "野外BOSS" },
{ 类型: 4000010, 数量: 10, 区域: "野外BOSS" },
{ 类型: 0, 数量: 10000000, 区域: "野外BOSS" },
{ 类型: 1, 数量: 30000, 区域: "野外BOSS" },
{ 类型: 2, 数量: 50000, 区域: "野外BOSS" },


{ 类型: 4000000, 数量: 500, 区域: "超级BOSS" },
{ 类型: 4000016, 数量: 200, 区域: "超级BOSS" },
{ 类型: 4000019, 数量: 100, 区域: "超级BOSS" },
{ 类型: 4000010, 数量: 50, 区域: "超级BOSS" },
{ 类型: 0, 数量: 30000000, 区域: "超级BOSS" },
{ 类型: 1, 数量: 50000, 区域: "超级BOSS" },
{ 类型: 2, 数量: 100000, 区域: "超级BOSS" },


{ 类型: 4000000, 数量: 20, 区域: "明珠海港" },
{ 类型: 4000016, 数量: 10, 区域: "明珠海港" },
{ 类型: 4000019, 数量: 5, 区域: "明珠海港" },
{ 类型: 4000010, 数量: 2, 区域: "明珠海港" },
{ 类型: 0, 数量: 1000000, 区域: "明珠海港" },
{ 类型: 1, 数量: 5000, 区域: "明珠海港" },
{ 类型: 2, 数量: 10000, 区域: "明珠海港" },

{ 类型: 4000000, 数量: 30, 区域: "射手之村" },
{ 类型: 4000016, 数量: 15, 区域: "射手之村" },
{ 类型: 4000019, 数量: 10, 区域: "射手之村" },
{ 类型: 4000010, 数量: 5, 区域: "射手之村" },
{ 类型: 0, 数量: 1000000, 区域: "射手之村" },
{ 类型: 1, 数量: 5000, 区域: "射手之村" },
{ 类型: 2, 数量: 10000, 区域: "射手之村" },


{ 类型: 4000000, 数量: 30, 区域: "魔法密林" },
{ 类型: 4000016, 数量: 15, 区域: "魔法密林" },
{ 类型: 4000019, 数量: 10, 区域: "魔法密林" },
{ 类型: 4000010, 数量: 5, 区域: "魔法密林" },
{ 类型: 0, 数量: 1000000, 区域: "魔法密林" },
{ 类型: 1, 数量: 5000, 区域: "魔法密林" },
{ 类型: 2, 数量: 10000, 区域: "魔法密林" },


{ 类型: 4000000, 数量: 30, 区域: "勇士部落" },
{ 类型: 4000016, 数量: 15, 区域: "勇士部落" },
{ 类型: 4000019, 数量: 10, 区域: "勇士部落" },
{ 类型: 4000010, 数量: 5, 区域: "勇士部落" },
{ 类型: 0, 数量: 1000000, 区域: "勇士部落" },
{ 类型: 1, 数量: 5000, 区域: "勇士部落" },
{ 类型: 2, 数量: 10000, 区域: "勇士部落" },

{ 类型: 4000000, 数量: 30, 区域: "废弃都市" },
{ 类型: 4000016, 数量: 15, 区域: "废弃都市" },
{ 类型: 4000019, 数量: 10, 区域: "废弃都市" },
{ 类型: 4000010, 数量: 5, 区域: "废弃都市" },
{ 类型: 0, 数量: 1000000, 区域: "废弃都市" },
{ 类型: 1, 数量: 5000, 区域: "废弃都市" },
{ 类型: 2, 数量: 10000, 区域: "废弃都市" },


{ 类型: 4000000, 数量: 30, 区域: "林中之城" },
{ 类型: 4000016, 数量: 15, 区域: "林中之城" },
{ 类型: 4000019, 数量: 10, 区域: "林中之城" },
{ 类型: 4000010, 数量: 5, 区域: "林中之城" },
{ 类型: 0, 数量: 1000000, 区域: "林中之城" },
{ 类型: 1, 数量: 5000, 区域: "林中之城" },
{ 类型: 2, 数量: 10000, 区域: "林中之城" },

{ 类型: 4000000, 数量: 30, 区域: "天空之城" },
{ 类型: 4000016, 数量: 15, 区域: "天空之城" },
{ 类型: 4000019, 数量: 10, 区域: "天空之城" },
{ 类型: 4000010, 数量: 5, 区域: "天空之城" },
{ 类型: 0, 数量: 1000000, 区域: "天空之城" },
{ 类型: 1, 数量: 5000, 区域: "天空之城" },
{ 类型: 2, 数量: 10000, 区域: "天空之城" },



{ 类型: 4000000, 数量: 30, 区域: "冰封雪域" },
{ 类型: 4000016, 数量: 15, 区域: "冰封雪域" },
{ 类型: 4000019, 数量: 10, 区域: "冰封雪域" },
{ 类型: 4000010, 数量: 5, 区域: "冰封雪域" },
{ 类型: 0, 数量: 1000000, 区域: "冰封雪域" },
{ 类型: 1, 数量: 5000, 区域: "冰封雪域" },
{ 类型: 2, 数量: 10000, 区域: "冰封雪域" },


{ 类型: 4000000, 数量: 30, 区域: "神秘矿山" },
{ 类型: 4000016, 数量: 15, 区域: "神秘矿山" },
{ 类型: 4000019, 数量: 10, 区域: "神秘矿山" },
{ 类型: 4000010, 数量: 5, 区域: "神秘矿山" },
{ 类型: 0, 数量: 1000000, 区域: "神秘矿山" },
{ 类型: 1, 数量: 5000, 区域: "神秘矿山" },
{ 类型: 2, 数量: 10000, 区域: "神秘矿山" },

{ 类型: 4000000, 数量: 30, 区域: "水下世界" },
{ 类型: 4000016, 数量: 15, 区域: "水下世界" },
{ 类型: 4000019, 数量: 10, 区域: "水下世界" },
{ 类型: 4000010, 数量: 5, 区域: "水下世界" },
{ 类型: 0, 数量: 1000000, 区域: "水下世界" },
{ 类型: 1, 数量: 5000, 区域: "水下世界" },
{ 类型: 2, 数量: 10000, 区域: "水下世界" },


{ 类型: 4000000, 数量: 30, 区域: "海底深渊" },
{ 类型: 4000016, 数量: 15, 区域: "海底深渊" },
{ 类型: 4000019, 数量: 10, 区域: "海底深渊" },
{ 类型: 4000010, 数量: 5, 区域: "海底深渊" },
{ 类型: 0, 数量: 1000000, 区域: "海底深渊" },
{ 类型: 1, 数量: 5000, 区域: "海底深渊" },
{ 类型: 2, 数量: 10000, 区域: "海底深渊" },


{ 类型: 4000000, 数量: 30, 区域: "玩具之城" },
{ 类型: 4000016, 数量: 15, 区域: "玩具之城" },
{ 类型: 4000019, 数量: 10, 区域: "玩具之城" },
{ 类型: 4000010, 数量: 5, 区域: "玩具之城" },
{ 类型: 0, 数量: 1000000, 区域: "玩具之城" },
{ 类型: 1, 数量: 5000, 区域: "玩具之城" },
{ 类型: 2, 数量: 10000, 区域: "玩具之城" },


{ 类型: 4000000, 数量: 30, 区域: "时间裂缝" },
{ 类型: 4000016, 数量: 15, 区域: "时间裂缝" },
{ 类型: 4000019, 数量: 10, 区域: "时间裂缝" },
{ 类型: 4000010, 数量: 5, 区域: "时间裂缝" },
{ 类型: 0, 数量: 1000000, 区域: "时间裂缝" },
{ 类型: 1, 数量: 5000, 区域: "时间裂缝" },
{ 类型: 2, 数量: 10000, 区域: "时间裂缝" },


{ 类型: 4000000, 数量: 30, 区域: "地球本部" },
{ 类型: 4000016, 数量: 15, 区域: "地球本部" },
{ 类型: 4000019, 数量: 10, 区域: "地球本部" },
{ 类型: 4000010, 数量: 5, 区域: "地球本部" },
{ 类型: 0, 数量: 1000000, 区域: "地球本部" },
{ 类型: 1, 数量: 5000, 区域: "地球本部" },
{ 类型: 2, 数量: 10000, 区域: "地球本部" },

{ 类型: 4000000, 数量: 30, 区域: "武陵桃园" },
{ 类型: 4000016, 数量: 15, 区域: "武陵桃园" },
{ 类型: 4000019, 数量: 10, 区域: "武陵桃园" },
{ 类型: 4000010, 数量: 5, 区域: "武陵桃园" },
{ 类型: 0, 数量: 1000000, 区域: "武陵桃园" },
{ 类型: 1, 数量: 5000, 区域: "武陵桃园" },
{ 类型: 2, 数量: 10000, 区域: "武陵桃园" },


{ 类型: 4000000, 数量: 30, 区域: "百草药堂" },
{ 类型: 4000016, 数量: 15, 区域: "百草药堂" },
{ 类型: 4000019, 数量: 10, 区域: "百草药堂" },
{ 类型: 4000010, 数量: 5, 区域: "百草药堂" },
{ 类型: 0, 数量: 1000000, 区域: "百草药堂" },
{ 类型: 1, 数量: 5000, 区域: "百草药堂" },
{ 类型: 2, 数量: 10000, 区域: "百草药堂" },


{ 类型: 4000000, 数量: 30, 区域: "童话小镇" },
{ 类型: 4000016, 数量: 15, 区域: "童话小镇" },
{ 类型: 4000019, 数量: 10, 区域: "童话小镇" },
{ 类型: 4000010, 数量: 5, 区域: "童话小镇" },
{ 类型: 0, 数量: 1000000, 区域: "童话小镇" },
{ 类型: 1, 数量: 5000, 区域: "童话小镇" },
{ 类型: 2, 数量: 10000, 区域: "童话小镇" },   

{ 类型: 4000000, 数量: 30, 区域: "东方神州" },
{ 类型: 4000016, 数量: 15, 区域: "东方神州" },
{ 类型: 4000019, 数量: 10, 区域: "东方神州" },
{ 类型: 4000010, 数量: 5, 区域: "东方神州" },
{ 类型: 0, 数量: 1000000, 区域: "东方神州" },
{ 类型: 1, 数量: 5000, 区域: "东方神州" },
{ 类型: 2, 数量: 10000, 区域: "东方神州" },   



{ 类型: 4000000, 数量: 30, 区域: "神木森林" },
{ 类型: 4000016, 数量: 15, 区域: "神木森林" },
{ 类型: 4000019, 数量: 10, 区域: "神木森林" },
{ 类型: 4000010, 数量: 5, 区域: "神木森林" },
{ 类型: 0, 数量: 1000000, 区域: "神木森林" },
{ 类型: 1, 数量: 5000, 区域: "神木森林" },
{ 类型: 2, 数量: 10000, 区域: "神木森林" },   


{ 类型: 4000000, 数量: 30, 区域: "龙之巢穴" },
{ 类型: 4000016, 数量: 15, 区域: "龙之巢穴" },
{ 类型: 4000019, 数量: 10, 区域: "龙之巢穴" },
{ 类型: 4000010, 数量: 5, 区域: "龙之巢穴" },
{ 类型: 0, 数量: 1000000, 区域: "龙之巢穴" },
{ 类型: 1, 数量: 5000, 区域: "龙之巢穴" },
{ 类型: 2, 数量: 10000, 区域: "龙之巢穴" },   





//-----------------此行代码请勿改动，留作备用格式

{ 类型: 4000000, 数量: 100, 区域: "测试区域" },
{ 类型: 0, 数量: 555, 区域: "测试区域" },
{ 类型: 1, 数量: 666, 区域: "测试区域" },
{ 类型: 2, 数量: 777, 区域: "测试区域" }


);


var wctj = 5;//普通卡片数量
var bosskp = 1;//BS卡数量

function 区域奖励喇叭() {
	cm.worldMessage(2,"【怪物集卡奖励】" + " : " + "" + cm.getPlayer().getName() + " 领取了【"+DHDCLX+"】 区域的丰厚奖励！大家祝贺他吧！");
}

function 单卡片奖励喇叭() {
	cm.worldMessage(2,"【怪物集卡奖励】" + " : " + "" + cm.getPlayer().getName() + " 领取了【"+cm.getItemName(全单卡记录)+"】 获得了丰厚的奖励！");
}





var wctjnb = wctj;
var qyjllq = false;
var DHDCLX;



var 表情高兴 = "#fUI/GuildBBS/GuildBBS/Emoticon/Basic/2#";
var status = -1;
var selection;
var 彩虹 ="#fEffect/ItemEff/1071085/effect/walk1/2#";
var 积分 = new Array(1,2);
var 随机积分 = 积分[Math.floor(Math.random() * 积分.length)];
var xmml1 = 0;
var xmml2 = 0;
var xmml3 = 0;
var 奖励经验 = 0;
var 奖励金币 = 0;
var BOSS奖励 = 0;

var 全单卡记录 = 0;

var M9 = "#fEffect/CharacterEff/1112905/0/1#";//小红心
var 爱心 = "#fEffect/CharacterEff/1022223/4/0#";
var 奖励 = "#fUI/UIWindow.img/Quest/reward#";
var 金币 = "#fUI/UIWindow.img/Item/BtCoin/normal/0#";
var 点券图标 = "#fUI/CashShop/CashItem/0#";
var 草莓5 = "#fUI/GuildMark/Mark/Plant/00003000/8#";
var 小黄星 = "#fItem/Etc/0427/04270001/Icon9/0#";
var zt1 = "#fUI/GuildMark/Mark/Animal/00002011/3#";
var zt2 = "#fUI/GuildMark/Mark/Animal/00002011/8#";
var 雪娃娃 = "#fCharacter/Cape/01102288/info/icon#";
var 躺熊 = "#fItem/Cash/0502/05021008/info/iconRaw#";
var 经验值 = "#fUI/UIWindow.img/QuestIcon/8/0#";
function start() {   
	status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 1) {
		
        status++;
    } else if (mode == 0) {
        cm.dispose();
        return;
    } else {
        cm.dispose();
        return;
    }
    if (status == 0) {
		MapleItemInformationProvider = Packages.server.MapleItemInformationProvider;
    var text = "";
	    text += "#b┣━━━━━━ " + 雪娃娃 + " #e怪物·#r卡片#n " + 雪娃娃 + " ━━━━━━┫#k\r\n"
		text += "  " + M9 + "[收集怪物卡片获取丰厚奖励哦,卡片可储存和交易]\r\n"
        text += "  " + M9 + "[双击背包里的卡片即可注入完成收集记录]\r\n"
		
		text += "#b#L120001#"+小黄星+"#b进度"+qulcbjdxx()+"[#r里程碑]#l\r\n"
		
		for (var a = 0; a < xmxsz.length; a++) {
			ZJF = "";
			if (a < 9){
				ZJF = "0";
			}
			// if (!getquyuljzt(xmxsz[a].区域)) {
				text += "#b#L"+a+"#["+ZJF+""+(a+1)+"]#r·#b区域 【#r"+xmxsz[a].区域+"#b】- - - [集卡详情和奖励]#l\r\n"
			// }
			
			
		}
		
		cm.sendSimple(text);
        
    } else if (status == 1) {
		
		xmml1 = selection;
        if (selection == 99999) {
			        												
			cm.sendOk("成功！");
			cm.dispose();
	    } else if (selection == 120001) {
			var text = "";
			tj = 0;
			for (var a = 0; a < xmxsz.length; a++) {
				if (getquyuljzt(xmxsz[a].区域)) {
					tj++;
					text += "  #k["+xmxsz[a].区域+"]#k "
					if (tj % 4 == 0){
						text += "\r\n"
					}
					
				}
			}
			nbxx = "#r┏━━━━━━━━━怪物卡片#b·里程碑━━━━━━━━┓\r\n";
			
			bfbtj = 100/xmxsz.length*tj;
			nbxx += "   " + M9 + "#d完成区域：["+tj+"/"+xmxsz.length+"] #B"+bfbtj+"[%]# ["+parseInt(bfbtj)+"%]#k#n\r\n\r\n"
			if (tj==xmxsz.length) {
				if (cm.getBossRank("里程碑奖励", 2) > 0) {
					text += "#b#L10012#重置里程碑#l"
				} else {
					text += "#b#L10011#领取里程碑完成奖励#l"
				}
				
			}
			text += "\r\n\r\n"
			text += "#b┗━━━━━━#r━━━━━━━━━━━━━━━━━━━┛"
			cm.sendOk(nbxx+text);
			if (tj<xmxsz.length) {
				cm.dispose();
			}
			
		} else {
			var text = "";
			text += 躺熊+"#k[#r"+xmxsz[xmml1].区域+"#b]区域收集状态 - - - - - - - - - - - - -\r\n"
			
			
			
			var kpxx = "";
			for (var a = 0; a < xmxsz[xmml1].卡片ID.length; a++) {
				id = xmxsz[xmml1].卡片ID[a];
				kpsl = getLevelByCard(id);
				wctj = wctjnb;
				BOSS奖励 = wctjnb;
				
				if (id >= 2388000 && id < 2388070){
					wctj = bosskp;
					BOSS奖励 = 1;
				}
				if (kpsl<wctj){
					qyjllq = true;
				}
				
				sflq = (getdankalog(id)>0)?"#r已领奖":"";
				wczt = (kpsl>=wctj)?"#b已完成":"#r未完成";
				kpxx += "#L"+a+"##k"+qukptb(id)+"#b#z"+id+"# #r怪物卡：[#b"+kpsl+"/#r"+wctj+"#k] ["+wczt+"#k]"+sflq+"#l\r\n"
			}
			qyjlzt = (getquyuljzt(xmxsz[xmml1].区域)>0)?"#r已领":"#b领取";
			
			
			text += "                       #L10002#"+小黄星+"【区域奖励专区】["+qyjlzt+"#b] "+小黄星+"#l\r\n\r\n"
			// text += "状态："+qyjllq+"\r\n"
			
			text += kpxx
			
			cm.sendSimple(text);
			// cm.dispose();
			
		}			 
		
    } else if (status == 2) {
		xmml2 = selection;
		if (xmml2 == 10002) {
			DHDCLX = xmxsz[xmml1].区域;

			var selStr = ""+奖励+" #e#b[#r"+DHDCLX+"#b] 奖励#n\r\n";
			
			for (var i = 0; i < xmqyjl.length; i++) { //需要固定材料
				if (xmqyjl[i].区域 == DHDCLX) {
					if (xmqyjl[i].类型 == 0) { //金币
						selStr += ""+金币+"#r 金币 X " + xmqyjl[i].数量 + "\r\n";
					} else if (xmqyjl[i].类型 == 1) { //点卷
						selStr += ""+点券图标+"#r 点卷 X " + xmqyjl[i].数量 + "\r\n";
					} else if (xmqyjl[i].类型 == 2) { //抵用券
						selStr += ""+点券图标+"#r 抵用卷 X " + xmqyjl[i].数量 + "\r\n";
					} else { //物品

						
							selStr += "#r#i" + xmqyjl[i].类型 + "##z" + xmqyjl[i].类型 + "##k X " + xmqyjl[i].数量 + "\r\n";
						
						

					}

				}
			}
			cm.sendYesNo(selStr);
		
		} else if (xmml2 < 999) {
			id = xmxsz[xmml1].卡片ID[xmml2];
			全单卡记录 = id;
			奖励经验 = xiaomi.普卡经验;
			奖励金币 = xiaomi.普卡金币;
			if ( BOSS奖励 == 1 ) {
				奖励经验 = xiaomi.BOSS卡经验;
				奖励金币 = xiaomi.BOSS卡金币;
			}
			for (var i = 0; i < xmdiykp.length; i++) {
				if (xmdiykp[i].卡片ID == id) {
					奖励经验 = xmdiykp[i].卡片经验;
					奖励金币 = xmdiykp[i].卡片金币;
					break;
				}
				
			}
			// { 卡片ID: 2388000, 卡片经验: 500000, 卡片金币: 500000 },
			
			var selStr = ""+qukptb(id)+""+奖励+"\r\n";
			selStr += ""+经验值+"#rX " + 奖励经验 + "\r\n";
			selStr += ""+金币+"#r 金币 X " + 奖励金币 + "\r\n";
			
			cm.sendYesNo(selStr);
		
		} else if (xmml2 == 10011) {//里程碑奖励
			if (cm.getBossRank("里程碑奖励", 2) > 0) {
				cm.sendOk("你已经领取过里程碑奖励了");
			} else {
				cm.setBossRankCount("里程碑奖励");
				cm.gainItem(1332066,2,2,2,2,0,0,10,10,0,0,0,0,0,0,120);//新手刮胡刀
				cm.sendOk("恭喜你，成功领取里程碑奖励");
				cm.全服黄色喇叭("[公告事项] : 恭喜玩家 "+cm.getPlayer().getName()+" 成功领取了里程碑奖励。")
			}
			cm.dispose();
		} else if (xmml2 == 10012) {//重置里程碑
			if (cm.haveItem(4000000, 999)) {//重置所需道具
				if (cm.getBossRank("重置里程碑", 2) >= 10) {
					cm.sendOk("只能重置10次里程碑奖励哦");
				} else {
					
					cm.setBossRankCount("重置里程碑")
					//重置怪物卡
					cm.getPlayer().getMonsterBook().clearMonsterBook(cm.getPlayer().getId());
					//清空领取记录
					cm.setBossRankCount("里程碑奖励", -1);
					//清空卡片、区域领取记录
					cm.sql_Update("delete from xmwnjl where characterid = ? and bossid like '怪物卡片%'", cm.getPlayer().getId());
					cm.gainItem(4000000, -999)
					cm.sendOk("恭喜你重置成功");
				}
			} else {
				cm.sendOk("重置里程碑需要999个#v4000000#");
			}
			cm.dispose();
		} else {
			cm.sendOk("系统错误，请联系管理员处理！\r\n错误代码：status xmml2 "+xmml2);
			cm.dispose();
			return;
			
		}

	} else if (status == 3) {
		xmml3 = selection;
		if (xmml1 == 999999) {
			
		} else if (xmml1 == 10002) {
		
		} else if (xmml2 == 10002) {
			
			//--------------------奖励领取开始
		if (cm.getInventory(1).isFull(4) || cm.getInventory(2).isFull(4) || cm.getInventory(3).isFull(4) || cm.getInventory(4).isFull(4)) {
			cm.sendOk("#b请保证全体背包5个格子,否则无法兑换.");
			cm.dispose();
			return;
		
		} else if (getquyuljzt(DHDCLX)) {
			cm.sendOk("啊？我没记错的话你已经领过奖励了吧！["+DHDCLX+"]区域");
			cm.dispose();
			return;
		} else if (qyjllq) { //
			cm.sendOk("抱歉，你貌似没有满足["+DHDCLX+"]区域的奖励条件");
			cm.dispose();
			return;
		} else {
			//----------------------------------------------------------------------
			lbtw = "【怪物集卡】"
			lbxx = "" + cm.getPlayer().getName() + " 领取了 【"+DHDCLX+"】区域的集卡奖励"
			for (var i = 0; i < xmqyjl.length; i++) {			
				if (xmqyjl[i].区域 == DHDCLX) {
					
					//------------------------------------------------------
					if (xmqyjl[i].类型 == 0) { //金币
						cm.gainMeso(+xmqyjl[i].数量);
						// cm.worldMessage(6,lbtw+ " : " + "["+cm.getName()+"]领取了 【"+DHDCLX+"】区域的："+xmqyjl[i].数量+" 金币奖励！");
					} else if (xmqyjl[i].类型 == 1) { //点卷
						cm.getPlayer().modifyCSPoints(1, +xmqyjl[i].数量, true); //点券
						// cm.worldMessage(12,lbtw+ " : " + "["+cm.getName()+"]领取了 【"+DHDCLX+"】区域的："+xmqyjl[i].数量+" 点卷奖励！");
					} else if (xmqyjl[i].类型 == 2) { //抵用券
						cm.getPlayer().modifyCSPoints(2, +xmqyjl[i].数量, true); //抵用券
						// cm.worldMessage(12,lbtw+ " : " + "["+cm.getName()+"]领取了 【"+DHDCLX+"】区域的："+xmqyjl[i].数量+" 抵用奖励！");
					} else { //物品
						
							cm.gainItem(xmqyjl[i].类型, xmqyjl[i].数量);
							// cm.xiaomilabaxn(xmqyjl[i].类型,lbtw,lbxx,14);
						

					}

				}
			}
			// cm.worldMessage(9,"【怪物集卡奖励】" + " : " + "" + cm.getPlayer().getName() + " 领取了【"+DHDCLX+"】 区域的丰厚奖励！大家祝贺他吧！");
			区域奖励喇叭();
			setquyuljzt(DHDCLX);
			cm.sendOk("恭喜你，成功领取了丰厚奖励！");
			cm.dispose();

		
			
			
		}
		//领取奖励结束
		} else if (xmml2 < 999) {
			if (全单卡记录 == 0) {
				cm.sendOk("什么鬼？系统貌似又错误了，请联系管理员解决！");
				cm.dispose();
				return;
			}
			
			if (getLevelByCard(id) < wctj ) {
				cm.sendOk("开玩笑吧？你根本就没完成吧？");
				cm.dispose();
				return;
			}
			
			if (getdankalog(全单卡记录) > 0 ) {
				cm.sendOk("哎哟我去！我记住你已经领取过了！");
				cm.dispose();
				return;
			}
			setdankalog(全单卡记录);
			cm.gainExp(+奖励经验);
			cm.gainMeso(+奖励金币);
			// cm.worldMessage(2,"【怪物集卡奖励】" + " : " + "" + cm.getPlayer().getName() + " 领取了【"+cm.getItemName(全单卡记录)+"】 获得了丰厚的奖励！");
			
			单卡片奖励喇叭();
			cm.sendOk("成功领取奖励咯！");
			
			cm.dispose();
			return;
			
			
			
		}
		
	}
}



function getLevelByCard(id) {
	return cm.getPlayer().getMonsterBook().getLevelByCard(id);
	
}

function qukptb(kpid) {   	
	return "#fItem/Consume/0238.img/0"+kpid+"/info/iconRaw#";
}


function qulcbjdxx() {
	
	tj = 0;
	for (var a = 0; a < xmxsz.length; a++) {
		if (getquyuljzt(xmxsz[a].区域)) {
			tj++;		
			}
		}
	bfbtj = 100/xmxsz.length*tj;
	nbxx = "#B"+bfbtj+"[%]# ["+parseInt(bfbtj)+"%]["+tj+"/"+xmxsz.length+"]"
	
	
	return nbxx;
}

function getquyuljzt(qyid) {
	return getxmwnjlc("怪物卡片区域领奖_"+qyid) > 0;
}

function setquyuljzt(qyid) {
	setxmwnjlc("怪物卡片区域领奖_"+qyid,1);
}



function getdankalog(qyid) {
	return getxmwnjlc("怪物卡片单卡奖励_"+qyid) > 0;
}

function setdankalog(qyid) {
	setxmwnjlc("怪物卡片单卡奖励_"+qyid,1);
}




function getxmwnjlc(bossid) {
	var xmsjfh = 0;
	characterid = cm.getPlayer().getId();
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "SELECT * FROM xmwnjl WHERE characterid = "+characterid+" AND bossid = '"+bossid+"' ;";
	var pstmt = conn.prepareStatement(sql);
	var result = pstmt.executeQuery();		
	if (result.next()) {
	xmsjfh = result.getInt("count");
	
	} 
	result.close();
	pstmt.close();
	return xmsjfh;
}


function setxmwnjlc(wnjllog,cs) {
	var accid = cm.getPlayer().getId();
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "SELECT * FROM xmwnjl WHERE bossid = '"+wnjllog+"' AND characterid = "+accid+" ;";
	var pstmt = conn.prepareStatement(sql);
	var result = pstmt.executeQuery();	
	
	if (result.next()) {
		result.close();
	    var conn = Packages.database.DatabaseConnection.getConnection();
	    var sql = "UPDATE xmwnjl SET count = "+cs+"  WHERE bossid = '"+wnjllog+"' AND characterid = "+accid+" ;";
	    var pstmt = conn.prepareStatement(sql);
	    pstmt.executeUpdate();
		pstmt.close();		
	} else {
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "insert into xmwnjl (time,bossid,count,characterid) values (CURRENT_TIMESTAMP(),?,?,?);";          
    var psu = conn.prepareStatement(sql);
	psu.setString(1,wnjllog);
	psu.setInt(2,cs);
	psu.setInt(3,accid);
    psu.executeUpdate();	
	psu.close();	
	}	
}

function gainxmwnjlc(wnjllog,cs) {
	var accid = cm.getPlayer().getId();
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "SELECT * FROM xmwnjl WHERE bossid = '"+wnjllog+"' AND characterid = "+accid+" ;";
	var pstmt = conn.prepareStatement(sql);
	var result = pstmt.executeQuery();	
	
	if (result.next()) {
		result.close();
	    var conn = Packages.database.DatabaseConnection.getConnection();
	    var sql = "UPDATE xmwnjl SET count = count+"+cs+"  WHERE bossid = '"+wnjllog+"' AND characterid = "+accid+" ;";
	    var pstmt = conn.prepareStatement(sql);
	    pstmt.executeUpdate();
		pstmt.close();		
	} else {
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "insert into xmwnjl (time,bossid,count,characterid) values (CURRENT_TIMESTAMP(),?,?,?);";          
    var psu = conn.prepareStatement(sql);
	psu.setString(1,wnjllog);
	psu.setInt(2,cs);
	psu.setInt(3,accid);
    psu.executeUpdate();	
	psu.close();	
	}	
}