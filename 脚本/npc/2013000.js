/* ==================
 脚本类型:  天空副本NPC	    
 脚本作者： 麒麟端——林
 联系方式QQ： 1500663066
 =====================
 */
importPackage(Packages.handling.word);
importPackage(Packages.client.inventory);
var 小烟花 ="#fMap/MapHelper/weather/squib/squib4/1#";
var 中条猫 ="#fUI/ChatBalloon/37/n#";
var ca = java.util.Calendar.getInstance();
var year = ca.get(java.util.Calendar.YEAR); //获得年份
var month = ca.get(java.util.Calendar.MONTH) + 1; //获得月份
var day = ca.get(java.util.Calendar.DATE); //获取日
var hour = ca.get(java.util.Calendar.HOUR_OF_DAY); //获得小时
var minute = ca.get(java.util.Calendar.MINUTE); //获得分钟
var second = ca.get(java.util.Calendar.SECOND); //获得秒
var weekday = ca.get(java.util.Calendar.DAY_OF_WEEK);
 
var status = 0;
//副本名称
var fbmc = "通天塔-(女神副本)";
var eventname = "OrbisPQ";//副本配置文件
var maxjinbi = 50000;//判断征集令金币
var minLevel = 50;
var maxLevel = 250;//等级设置
var cishuxianzhi = 10;//限制次数
var minPartySize = 3;
var maxPartySize = 6;//人数设置
var 积分兑换 = 30;//判断积分兑换次数
var 限制兑换次数 = 999;//判断积分兑换次数


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
			if (cm.getMapId() == 920010000) { 
		cm.sendOk("我们必须拯救他 需要20个云的碎片然后丢到中间的光环之中,切忌千万不要一个一个丢!");
		cm.dispose();
		return;
	}
            var tex2 = "";
            var text = "";
            for (i = 0; i < 10; i++) {
                text += "";
            }
            text += "#k\t\t\t\t"+小烟花+"#e#d《天 空 副 本》"+小烟花+" #k\r\n\r\""+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+"    副本进入要求如下：\r\n①人数限制:#r " + minPartySize + " #b- #r" + maxPartySize + "#k队员\t②等级限制：#r " + minLevel + " #b- #r" + maxLevel + "级 #k\r\n"
			text += "#k副本专属积分:#r"+get积分("天空积分")+"#k分   每天只能挑战:#b"+ cishuxianzhi +"#k次 \r\n你今天已进入:#b"+ cm.getPlayer().getBossLog("nvshengcs") +"#k次#k\r\n"
            text += "#L1##r【开始组队副本】#l      \r\n"
			text += "#L3##r【兑换副本装备】("+积分兑换+"专属积分)#v1112915##l\r\n\r\n";
			text += "#L68##r【查看副本奖励】#l      "
			
            cm.sendSimple(text);
        } else if (selection == 68) {//副本奖励
				cm.dispose();
				cm.openNpc(2013000, 68);
        }
		else if (selection == 1) {
	for (var i = 4001044; i < 4001064; i++) {
		cm.removeAll(i); 
	}
	if (cm.getParty() == null) { //判断又没有组队
	    cm.sendSimple("你貌似没有达到要求...:\r\n\r\n#r玩家成员要求: " + minPartySize + " , 每个人的等级必须在 " + minLevel + " 到 等级 " + maxLevel + "或您没有组队.");
		cm.dispose();
	} else if (!cm.isLeader()) { //判断队长
	    cm.sendSimple("如果你想做任务，请#b队长#k 跟我谈.");
		cm.dispose();
	} else if(cm.getPartyBosslog("nvshengcs",(cishuxianzhi)) == false) {//判断组队次数
	            cm.sendOk("队伍中队友挑战次数已经用完"+ cishuxianzhi +"次！");
                cm.dispose();
				return;
	}else if( cm.getPlayer().getBossLog("nvshengcs") >= cishuxianzhi) {
	            cm.sendOk("您好,限定每天只能挑战"+ cishuxianzhi +"次！");
                cm.dispose();
				return;
	} else {
	    // Check if all 队员 are within PQ levels
	    var party = cm.getParty().getMembers();
	    var mapId = cm.getMapId();
	    var next = true;
	    var levelValid = 0;
	    var inMap = 0;
	    var it = party.iterator();

	    while (it.hasNext()) {
		var cPlayer = it.next();
		if ((cPlayer.getLevel() >= minLevel) && (cPlayer.getLevel() <= maxLevel)) {
		    levelValid += 1;
		} else {
		    next = false;
		}
		if (cPlayer.getMapid() == mapId) {
		    inMap += (cPlayer.getJobId() == 900 ? 6 : 1);
		}
	    }
	    if (party.size() > maxPartySize || inMap < minPartySize) {
		next = false;
	    }
	    if (next) {
		var em = cm.getEventManager("OrbisPQ");
		if (em == null) {
		    cm.sendSimple("找不到脚本请联络GM#b\r\n");
		} else {
		    var prop = em.getProperty("state");
		    if (prop.equals("0") || prop == null) {
			em.startInstance(cm.getParty(), cm.getMap());
			cm.setPartyBosslog("nvshengcs");//给团队次数
			if (weekday != 7) { //（周六可以领奖可以领奖）
	//						cm.getPlayer().Atime(); //竞速开始
						} else {
							cm.getPlayer().dropMessage(5, "周六这一天都是领奖期间，不能参与竞速");
						}
						
			cm.dispose();
			return;
		    } else {
			cm.sendSimple("其他队伍已经在里面做 #r组队任务了#k 请尝试换频道或者等其他队伍完成。");
			cm.dispose();
		    }
		}
	    } else {
		cm.sendSimple("你的队伍貌似没有达到要求...:\r\n\r\n#r要求: " + minPartySize + " 玩家成员, 每个人的等级必须在 " + minLevel + " 到 等级 " + maxLevel + ".");
	    }
		cm.dispose();
	}
	} else if (selection == 5) {
		cm.getPlayer().showtimePLC("通关天空");
		cm.dispose();
	} else if (selection == 6) {

		if (weekday == 7 && hour <= 23) { //（周六可以领奖可以领奖）
			cm.openNpc(9900004, "天空排行周六领奖");
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
		} else if(cm.getPlayer().getOneTimeLog("nvsheng1") >= 限制兑换次数){//判断永久记录
		cm.sendOk("你已经领取过了,无法在重复领取!");
        cm.dispose();
		} else if(get积分("天空积分") < 积分兑换){//判断永久记录
		cm.sendOk("天空副本积分不足"+积分兑换+"分,当前积分:"+get积分("天空积分")+"分!");
        status = -1;
		} else {
		gain积分("天空积分",-积分兑换);
        cm.getPlayer().setOneTimeLog("nvsheng1");//给永久记录
		//cm.gainItem(1112915, 1, true);//物品代码,数量,随机属性
		cm.gainItem(1112915, 8, 8, 8, 8, 0, 0, 8, 8, 0, 0, 0, 0, 0, 0);//天空耳环
		cm.sendOk("恭喜你,成功的兑换天空副本专属道具成功，请检背包!!");
        cm.worldMessage(6,"恭喜玩家：["+cm.getName()+"]成功使用副本积分换购了(天空副本)专属装备！");
	    status = -1;
		
					}						
	}
    }
	}
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
