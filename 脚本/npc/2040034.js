/* ==================
 脚本类型:  玩具副本	    
 脚本作者： 颜林 
 联系方式： 1500663066
 =====================
 */
importPackage(java.lang);
importPackage(Packages.tools);
importPackage(Packages.client);
importPackage(Packages.server);
importPackage(Packages.tools.packet);
	
importPackage(Packages.handling.word);
importPackage(Packages.client.inventory);
var ca = java.util.Calendar.getInstance();
var 皇冠白 ="#fUI/GuildMark/Mark/Etc/00009004/16#";
var year = ca.get(java.util.Calendar.YEAR); //获得年份
var month = ca.get(java.util.Calendar.MONTH) + 1; //获得月份
var day = ca.get(java.util.Calendar.DATE); //获取日
var hour = ca.get(java.util.Calendar.HOUR_OF_DAY); //获得小时
var minute = ca.get(java.util.Calendar.MINUTE); //获得分钟
var second = ca.get(java.util.Calendar.SECOND); //获得秒
var weekday = ca.get(java.util.Calendar.DAY_OF_WEEK);

var status = 0;
var fbmc = "玩具城-(玩具塔101副本)";//副本名称
var eventname = "LudiPQ";//副本配置文件
var maxjinbi = 100000;//判断征集令金币
var minLevel = 35;//最低等级
var maxLevel = 250;//最高等级
var cishuxianzhi = 10;//限制次数
var minPartySize = 3;//最低人数
var maxPartySize = 6;//最高人数
var 积分兑换 = 30;//判断积分兑换次数
var 限制兑换次数 = 999;//判断积分兑换次数
var open = true;//false true//其他设置
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
            var text = "";
            for (i = 0; i < 10; i++) {
                text += "";
            }
            text += "#k\t\t\t\t"+皇冠白+" #r#e#w玩 具 副 本#n#k "+皇冠白+"#k\r\n\r\n副本进入要求如下：\r\n①人数限制:#r " + minPartySize + " #b- #r" + maxPartySize + "#k队员\t②等级限制：#r " + minLevel + " #b- #r" + maxLevel + "级 #k\r\n"
			text += "#k副本专属积分:#r"+get积分("玩具积分")+"#k分   每天只能挑战:#b"+ cishuxianzhi +"#k次 你今天已进入:#b"+ cm.getPlayer().getBossLog("wanjucs") +"#k次#k\r\n\r\n"
            text += "#L1##r【开始组队副本】#l      \r\n"
			text += "#L3##r【兑换副本装备】("+积分兑换+"专属积分)#v1022073##l\r\n\r\n"
			text += "#L68##r【查看副本奖励】#l   "
            cm.sendSimple(text);
        } else if (selection == 1) {
	   cm.removeAll(4001022);
	   cm.removeAll(4001023);
	   
	   var next = true;
	   var size = 0;
	   var it = cm.getPlayer().getParty().getMembers().iterator();
			while (it.hasNext()) {
				var cPlayer = it.next();
				var ccPlayer = cm.getPlayer().getMap().getCharacterById(cPlayer.getId());
				if (ccPlayer == null) {
					next = false;
					break;
				}
				size++;
			}
	   if (next && size >= 1) {
		
       if (!cm.isLeader()) { //判断队长
	   cm.sendSimple("如果你想做任务，请 #b队长#k 跟我谈."); 
	   cm.dispose();
	} else if (cm.getParty() == null) { //判断有没有组队
	   cm.sendSimple("你貌似没有达到要求...:\r\n\r\n#r玩家成员最少要求: " + minPartySize + " , 每个人的等级必须在 " + minLevel + " 到 等级 " + maxLevel + ".");
	   cm.dispose();
	}else if(cm.getPartyBosslog("wanjucs",(cishuxianzhi)) == false) {//判断组队是否2次
	            cm.sendOk("队伍中队友挑战次数已经用完"+ cishuxianzhi +"次！");
                cm.dispose();
				return;
	}else if( cm.getPlayer().getBossLog("wanjucs") >= cishuxianzhi) {
	            cm.sendOk("您好,限定每天只能挑战"+ cishuxianzhi +"次！");
                cm.dispose();
				return;
	}else {

	    var party = cm.getParty().getMembers();//获取成员
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
		var em = cm.getEventManager("LudiPQ");
		if (em == null) {
		    cm.sendSimple("找不到脚本请联络GM#b\r\n");//#L0#我要兑换有裂痕的眼镜#l
		} else {
		    var prop = em.getProperty("state");
		    if (prop.equals("0") || prop == null) {
			em.startInstance(cm.getParty(), cm.getMap());
		cm.getMap(922010401).resetFully();
		cm.getMap(922010402).resetFully();
		cm.getMap(922010403).resetFully();
		cm.getMap(922010404).resetFully();
		cm.getMap(922010405).resetFully();
		cm.setPartyBosslog("wanjucs");//给团队次数
			cm.removeAll(4001022);
			cm.removeAll(4001023);
			if (weekday != 7) { //（周六可以领奖可以领奖）
						//	cm.getPlayer().Atime(); //竞速开始
						} else {
							cm.getPlayer().dropMessage(5, "周六这一天都是领奖期间，不能参与竞速");
						}
			cm.dispose();
			return;
		    } else {
			cm.sendSimple("其他队伍已经在里面做 #r组队任务了#k 请尝试换频道或者等其他队伍完成。");//#b\r\n#L0#我要兑换有裂痕的眼镜#l
		    cm.dispose();
			}
		}
	    } else {
		cm.sendSimple("你的队伍貌似没有达到要求...:\r\n\r\n#r要求最少: " + minPartySize + " 玩家成员, 每个人的等级必须在 " + minLevel + " 到 等级 " + maxLevel + ".");//#b\r\n#L0#我要兑换有裂痕的眼镜#l
		cm.dispose();
	    }
	cm.dispose();
    }	
       } else {
				cm.sendOk("组队成员必须全部在这里。");
				cm.dispose();
			}	
	} else if (selection == 5) {
		cm.getPlayer().showtimePLC("通关玩具");
		cm.dispose();
	}else if (selection == 68) {//副本奖励
				cm.dispose();
				cm.openNpc(2040034, 68);
        }
	else if (selection == 6) {

		if (weekday == 7 && hour <= 23) { //（周六可以领奖可以领奖）
			cm.openNpc(9900004, "玩具排行周六领奖");
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
        } else if (selection == 3) {//玩具积分
		if (cm.getInventory(1).isFull(0)){//判断第一个也就是装备栏的装备栏是否有一个空格
		cm.sendOk("#b请保证装备栏位至少有1个空格,否则无法兑换.");
		cm.dispose();
		} else if(cm.getPlayer().getOneTimeLog("wanju1") >= 限制兑换次数){//判断永久记录
		cm.sendOk("你已经领取过了,无法在重复领取!");
        cm.dispose();
		} else if(get积分("玩具积分") < 积分兑换){//判断永久记录
		cm.sendOk("玩具副本积分不足"+积分兑换+"分,当前积分:"+get积分("玩具积分")+"分!");
        status = -1;
		} else {
		gain积分("玩具积分",-积分兑换);
        cm.getPlayer().setOneTimeLog("wanju1");//给永久记录
		cm.gainItem(1022073, 7, 7, 7, 7, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0);//玩具眼镜
		cm.sendOk("兑换玩具副本专属道具成功，请检背包!");
        cm.worldMessage(6,"恭喜玩家：["+cm.getName()+"]成功使用副本积分换购了(玩具副本)专属装备！");
	    status = -1;
	}
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


