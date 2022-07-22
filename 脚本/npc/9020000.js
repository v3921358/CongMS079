var FY0 = "┃                      ┃";
var FY1 = "┃       - 枫叶 -       ┃";
var FY2 = "┃ 脚本仿制  　定制脚本 ┃";
var FY3 = "┃ 技术支持 　 游戏顾问 ┃";
var FY4 = "┃ ＷＺ添加　  地图制作 ┃";
var FY5 = "┃ 加盾防御　  售登陆器 ┃";
var FY6 = "┃       百度推广       ┃";
var FY7 = "┃ 唯一QQ:1848350048    ┃";
var FY8 = "┃                      ┃";
importPackage(Packages.handling.word);
importPackage(Packages.client.inventory);
var 彩虹 ="#fEffect/ItemEff/1071085/effect/walk1/2#";
var ca = java.util.Calendar.getInstance();
var year = ca.get(java.util.Calendar.YEAR); //获得年份
var month = ca.get(java.util.Calendar.MONTH) + 1; //获得月份
var day = ca.get(java.util.Calendar.DATE); //获取日
var hour = ca.get(java.util.Calendar.HOUR_OF_DAY); //获得小时
var minute = ca.get(java.util.Calendar.MINUTE); //获得分钟
var second = ca.get(java.util.Calendar.SECOND); //获得秒
var weekday = ca.get(java.util.Calendar.DAY_OF_WEEK);

var status;
var fbmc = "废弃都市-(废弃副本)";//副本名称
var minLevel = 15;//最低等级
var maxLevel = 255;//最高等级
var minPartySize = 3;//最低人数
var maxPartySize = 6;//最高人数
var cishuxianzhi = 15;//限制次数
var maxjinbi = 50000;//判断征集令金币
var 积分兑换 = 38;//判断积分兑换次数
var 限制兑换次数 = 999;//判断积分兑换次数
var eventname = "KerningPQ";//副本配置文件

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 1)
        status++;
    else {
        cm.dispose();
        return;
    }
    if (status == 0) {
            var tex2 = "";
            var text = "";
            for (i = 0; i < 10; i++) {
                text += "";
            }
			//显示物品ID图片用的代码是  #v这里写入ID#
			
			
            text += "#k\t\t\t"+彩虹+"#e#d废 弃 副 本 #k#n  #r"+彩虹+" #k\r\n\r\n副本进入要求如下：\r\n①人数限制:#r " + minPartySize + " #b- #r" + maxPartySize + "#k队员\t②等级限制：#r " + minLevel + " #b- #r" + maxLevel + "级 #k\r\n"
			text += "#k副本专属积分:#r"+get积分("废弃积分")+"#k分   每天只能挑战:#b"+ cishuxianzhi +"#k次 你今天已进入:#b"+ cm.getPlayer().getBossLog("feiqics") +"#k次#k\r\n\r\n"
            text += "#L1##r【开始组队副本】#l      \r\n"
			text += "#L3##r【兑换副本装备】("+积分兑换+"废弃积分)#v1112793##l\r\n\r\n"
			text += "#L68##r【查看副本奖励】#l      "
			//text += "#L5##r【毫秒竞速排行】#l      #L6##r【周六排行领取】#l "
            cm.sendSimple(text);
	} else if (selection == 68) {//副本奖励
				cm.dispose();
				cm.openNpc(9020000, 68);
        }
	
	else if (selection == 1) {
        if (cm.getParty() == null) {
            cm.sendOk("你没有队伍无法进入！");
            cm.dispose();
			return;
        } else if (!cm.isLeader()) { 
            cm.sendOk("请让你的队长和我说话~");
            cm.dispose();
			return;
        } else {
            var party = cm.getParty().getMembers();
            var inMap = cm.partyMembersInMap();
            var levelValid = 0;
            for (var i = 0; i < party.size(); i++) {
                if (party.get(i).getLevel() >= minLevel && party.get(i).getLevel() <= maxLevel)
                    levelValid++;
            }
            if (inMap < minPartySize || inMap > maxPartySize) {
                cm.sendOk("你的队伍人数不足"+minPartySize+"人.请把你的队伍人员召集到废弃都市在进入副本.");
                cm.dispose();
				return;
            } else if (levelValid != inMap) {
                cm.sendOk("请确保你的队伍里所有人员都在本地图，且最小等级在 "+minLevel+" 和 "+maxLevel+"之间.");
                cm.dispose();
				return;
			}else if(cm.getPartyBosslog("feiqics",(cishuxianzhi)) == false) {//判断组队是否2次
	            cm.sendOk("队伍中队友挑战次数已经用完"+ cishuxianzhi +"次！");
                cm.dispose();
				return;
			}else if( cm.getPlayer().getBossLog("feiqics") >= cishuxianzhi) {
	            cm.sendOk("您好,限定每天只能挑战"+ cishuxianzhi +"次！");
                cm.dispose();
				return;
            } else {
                var em = cm.getEventManager("KerningPQ");
                if (em == null) {
                    cm.sendOk("这台电脑是当前不可用.");
                //} else if (em.getProperty("KPQOpen").equals("true")) {
                } else {
        if (cm.getPlayerCount(103000800) <= 0 && cm.getPlayerCount(103000801) <= 0 && cm.getPlayerCount(103000802) <= 0 && cm.getPlayerCount(103000803) <= 0 && cm.getPlayerCount(103000804) <= 0) {
		/*var papuMap = cm.getMap(103000804);
         cm.getMap(103000804).resetFully();
        cm.spawnMobOnMap(9300002,1,297,-2188,103000804);
        cm.spawnMobOnMap(9300002,1,433,-2192,103000804);
        cm.spawnMobOnMap(9300002,1,132,-2193,103000804);
		cm.spawnMobOnMap(9300000,1,-18,-1480,103000804);
		cm.spawnMobOnMap(9300000,1,80,-1486,103000804);
		cm.spawnMobOnMap(9300000,1,391,-1488,103000804);
		cm.spawnMobOnMap(9300000,1,247,-1485,103000804);
		cm.spawnMobOnMap(9300000,1,-111,-1475,103000804);
		cm.spawnMobOnMap(9300000,1,299,-1485,103000804);
		cm.spawnMobOnMap(9300003,1,162,-451,103000804);
        //var papuMap = pi.getMap(103000804);
		//pi.getPlayer().setbosslog(1);
        //pi.playPortalSE();
*/
			//}
				//em.startInstance(cm.getPlayer().getParty(), cm.getPlayer().getMap());
                em.startInstance(cm.getParty(), cm.getPlayer().getMap());
				cm.setPartyBosslog("feiqics");//给团队次数
				if (weekday != 7) { //（周六可以领奖可以领奖）
						//	cm.getPlayer().Atime(); //竞速开始
						} else {
							cm.getPlayer().dropMessage(5, "周六这一天都是领奖期间，不能参与竞速");
						}
				
				
				
		} else {
                            cm.sendOk("请稍等...任务正在进行中.");
                        }

                }
                cm.dispose();
            }
        }
		} else if (selection == 5) {
		cm.getPlayer().showtimePLC("通关废弃");
		cm.dispose();
	} else if (selection == 6) {

		if (weekday == 7 && hour <= 23) { //（周六可以领奖可以领奖）
			cm.openNpc(9900004, "废弃排行周六领奖");
		} else {;
			cm.sendOk("（周六 (0-23)点 可以领奖）");
			cm.dispose();
			return;
		}
	
	} else if (selection == 2) {
            if (cm.getMeso() >= maxjinbi){//判断多少金币
                cm.gainMeso(- maxjinbi );//扣除多少金币
				cm.laba(cm.getPlayer().getName() + " [征集令]" + " : " + "[" + fbmc + "]需要勇士一起完成",11);
                cm.dispose();
                }else{
                    cm.sendOk("你的冒险币不足" + maxjinbi + "。无法发送征集令");
                    cm.dispose();
    }
	} else if (selection == 3) {
		if (cm.getInventory(1).isFull(0)){//判断第一个也就是装备栏的装备栏是否有一个空格
		cm.sendOk("#b请保证装备栏位至少有1个空格,否则无法兑换.");
		cm.dispose();
		} else if(cm.getPlayer().getOneTimeLog("feiqi1") >= 限制兑换次数){//判断永久记录
		cm.sendOk("你已经领取过了,无法在重复领取!");
        cm.dispose();
		} else if(get积分("废弃积分") < 积分兑换){//判断永久记录
		cm.sendOk("废弃副本积分不足"+积分兑换+"分,当前积分:"+get积分("废弃积分")+"分!");
        status = -1;
		} else {
		gain积分("废弃积分",-积分兑换);
        cm.getPlayer().setOneTimeLog("feiqi1");//给永久记录
		cm.gainItem(1112793, 1, true);//物品代码,数量,随机属性
		//cm.gainItem(1112793, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);//快乐戒指
		cm.sendOk("兑换废弃副本专属道具成功，请检背包!");
        cm.worldMessage(6,"恭喜玩家：["+cm.getName()+"]成功使用副本积分换购了(废弃副本)专属装备！!");
	    status = -1;
	}
}}

function get积分(a) {
	var jf = 0;
		switch (a) {
		case "废弃积分":
			jf = Number(cm.getQuestRecord(844440).getCustomData());
			break;
		case "玩具积分":
			jf = Number(cm.getQuestRecord(844441).getCustomData());
			break;
		case "天空积分":
			jf = Number(cm.getQuestRecord(844442).getCustomData());
			break;
		case "海盗积分":
			jf = Number(cm.getQuestRecord(844443).getCustomData());
			break;
		case "毒物积分":
			jf = Number(cm.getQuestRecord(844444).getCustomData());
			break;
		case "月妙积分":
			jf = Number(cm.getQuestRecord(844445).getCustomData());
			break;
		case "狗男积分":
			jf = Number(cm.getQuestRecord(844446).getCustomData());
			break;
		case "副本积分":
			jf = Number(cm.getQuestRecord(844447).getCustomData());
			break;
		}

		return jf;
}

function gain积分(a,b) {
	var jf = 0;
		switch (a) {
		case "废弃积分":
			jf = Number(cm.getQuestRecord(844440).getCustomData());
			cm.getQuestRecord(844440).setCustomData("" + (jf+b));
			cm.getPlayer().saveToDB(false, false);
			break;
		case "玩具积分":
			jf = Number(cm.getQuestRecord(844441).getCustomData());
			cm.getQuestRecord(844441).setCustomData("" + (jf+b));
			cm.getPlayer().saveToDB(false, false);
			break;
		case "天空积分":
			jf = Number(cm.getQuestRecord(844442).getCustomData());
			cm.getQuestRecord(844442).setCustomData("" + (jf+b));
			cm.getPlayer().saveToDB(false, false);
			break;
		case "海盗积分":
			jf = Number(cm.getQuestRecord(844443).getCustomData());
			cm.getQuestRecord(844443).setCustomData("" + (jf+b));
			cm.getPlayer().saveToDB(false, false);
			break;
		case "毒物积分":
			jf = Number(cm.getQuestRecord(844444).getCustomData());
			cm.getQuestRecord(844444).setCustomData("" + (jf+b));
			cm.getPlayer().saveToDB(false, false);
			break;
		case "月妙积分":
			jf = Number(cm.getQuestRecord(844445).getCustomData());
			cm.getQuestRecord(844445).setCustomData("" + (jf+b));
			cm.getPlayer().saveToDB(false, false);
			break;
		case "狗男积分":
			jf = Number(cm.getQuestRecord(844446).getCustomData());
			cm.getQuestRecord(844446).setCustomData("" + (jf+b));
			cm.getPlayer().saveToDB(false, false);
			break;
		case "副本积分":
			jf = Number(cm.getQuestRecord(844447).getCustomData());
			cm.getQuestRecord(844447).setCustomData("" + (jf+b));
			cm.getPlayer().saveToDB(false, false);
			break;
		}

}