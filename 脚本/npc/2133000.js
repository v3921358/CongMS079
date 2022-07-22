/* ==================
 脚本类型:  毒物副本	    
 脚本作者： 林一   
 联系方式： 1500663066
 =====================
 */
importPackage(Packages.handling.word);
importPackage(Packages.client.inventory);
var ca = java.util.Calendar.getInstance();
var year = ca.get(java.util.Calendar.YEAR); //获得年份
var month = ca.get(java.util.Calendar.MONTH) + 1; //获得月份
var day = ca.get(java.util.Calendar.DATE); //获取日
var hour = ca.get(java.util.Calendar.HOUR_OF_DAY); //获得小时
var minute = ca.get(java.util.Calendar.MINUTE); //获得分钟
var second = ca.get(java.util.Calendar.SECOND); //获得秒
var weekday = ca.get(java.util.Calendar.DAY_OF_WEEK);
var 小烟花 ="#fMap/MapHelper/weather/squib/squib4/1#";
var 中条猫 ="#fUI/ChatBalloon/37/n#";
var status = -1;
var fbmc = "毒雾森林-(毒雾副本)";//副本名称
var minLevel = 70;
var maxLevel = 250;
var minPartySize = 2;
var maxPartySize = 6;
var cishuxianzhi = 10;//限制次数
var 积分兑换 = 50;//判断积分兑换次数
var 限制兑换次数 = 999;//判断积分兑换次数
var maxjinbi = 50000;//判断征集令金币
var eventname = "Ellin";//副本配置文件
var status = -1;

function action(mode, type, selection) {
    if (mode == 1) {
	status++;
    } else {
	if (status == 0) {
	    cm.dispose();
	}
	status--;
    }
    if (status == 0) {
	    cm.givePartyItems(4001161, 0, true);
	    cm.givePartyItems(4001162, 0, true);
	    cm.givePartyItems(4001163, 0, true);
	    cm.givePartyItems(4001169, 0, true);
	    cm.givePartyItems(2270004, 0, true);
            var tex2 = "";
            var text = "";
            for (i = 0; i < 10; i++) {
                text += "";
            }
            text += "#k\t\t\t\t#e#d   《毒 物 副 本》 #k\r\n\r\""+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+"#k\r\n副本进入要求如下：\r\n①人数限制:#r " + minPartySize + " #b- #r" + maxPartySize + "#k队员\t②等级限制：#r " + minLevel + " #b- #r" + maxLevel + "级 #k\r\n"
			text += "#k副本专属积分:#r"+get积分("毒物积分")+"#k分   每天只能挑战:#b"+ cishuxianzhi +"#k次\r\n您今天已进入:#b"+ cm.getPlayer().getBossLog("Ellincs") +"#k次#k\r\n\r\n"
            
			text += "#L1##r【开始组队副本】#l      \r\n"
			text += "#L77##r【兑换副本装备】("+积分兑换+"专属积分)#v1012146##l\r\n\r\n"
			text += "#L68##r【查看副本奖励】#l      "
			//text += "#L3##r挑战满50次领取#v1032060##z1032060##l\r\n\r\n"
			//text += "#L4##r挑战满100次领取#v1032061##z1032061##l\r\n\r\n"
			//text += "#L5##r【毫秒竞速排行】#l      #L6##r【周六排行领取】#l "
    cm.sendSimple(text);
    } else if (selection == 68) {//副本奖励
				cm.dispose();
				cm.openNpc(2133000, 68);
        }
	else if (status == 1) {
        if (selection == 1) {
			
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
		   
		   cm.removeAll(4001161);
	       cm.removeAll(4001162);
		   cm.removeAll(4001163);
	       cm.removeAll(4001164);
		    } else {
				cm.sendOk("组队成员必须全部在这里。");
				cm.dispose();
			}	
	    if (cm.getPlayer().getParty() == null || !cm.isLeader()) {
		cm.sendOk("找您的队长来和我谈话。");
		cm.dispose();
		}else if(cm.getPartyBosslog("Ellincs",(cishuxianzhi)) == false) {//判断组队是否2次
	            cm.sendOk("队伍中队友挑战次数已经用完"+cishuxianzhi+"次！");
               cm.dispose();
				return;
			}else if( cm.getPlayer().getBossLog("Ellincs") >= cishuxianzhi) {
	            cm.sendOk("您好,限定每天只能挑战"+ cishuxianzhi +"次！");
                cm.dispose();
				return;
	    } else {
		var party = cm.getPlayer().getParty().getMembers();
		var mapId = cm.getPlayer().getMapId();
		var next = true;
		var size = 0;
		var it = party.iterator();
		while (it.hasNext()) {
			var cPlayer = it.next();
			var ccPlayer = cm.getPlayer().getMap().getCharacterById(cPlayer.getId());
			if (ccPlayer == null || ccPlayer.getLevel() < minLevel || ccPlayer.getLevel() > maxLevel) {
				next = false;
				break;
			}
			size += (ccPlayer.isGM() ? 4 : 1);
		}	
		if (next && size >= minPartySize) {
			var em = cm.getEventManager("Ellin");
			if (em == null) {
				cm.sendOk("当前副本有问题,请联络管理员.");
				cm.dispose();
			} else {
				var prop = em.getProperty("state");
                if (prop.equals("0") || prop == null) {
					em.startInstance(cm.getParty(), cm.getMap());
					cm.setPartyBosslog("Ellincs");//给团队次数
					if (weekday != 7) { //（周六可以领奖可以领奖）
						//	cm.getPlayer().Atime(); //竞速开始
						} else {
							cm.getPlayer().dropMessage(5, "周六这一天都是领奖期间，不能参与竞速");
						}
						
						
					cm.dispose();
					return;
				} else {
					cm.sendOk("里面已经有人了,请你稍后在进入看看,或者更换频道");
					cm.dispose();
				}

			}
		} else {
			cm.sendOk("你的队伍#b成员#k需要#b" +minPartySize+ "个#k以上等级" + minLevel + "~" + maxLevel + "的队员才能进入!");
			cm.dispose();
		}
	    }
		} else if (selection == 5) {
		cm.getPlayer().showtimePLC("通关毒物");
		cm.dispose();
	} else if (selection == 6) {

		if (weekday == 7 && hour <= 23) { //（周六可以领奖可以领奖）
			cm.openNpc(9900004, "毒物排行周六领奖");
		} else {;
			cm.sendOk("（周六 (0-23)点 可以领奖）");
			cm.dispose();
			return;
		}
		} else if (selection == 2){
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
		} else if(cm.getPlayer().getOneTimeLog("Ellin1") >= 1){//判断永久记录
		cm.sendOk("你已经领取过了,无法在重复领取!");
        cm.dispose();
		} else if(cm.getPlayer().getOneTimeLog("Ellin") < 50){//判断永久记录
		cm.sendOk("你还没有成功挑战够50次,当前已经挑战成功了:"+cm.getPlayer().getOneTimeLog("Ellin")+"次!");
        status = -1;
		} else {
        cm.getPlayer().setOneTimeLog("Ellin1");//给永久记录
		cm.gainItem(1032060, 1, true);//物品代码,数量,随机属性
		cm.sendOk("恭喜你,成功的领取了#v1032060##z1032060#!");
        cm.worldMessage(6,"恭喜玩家：["+cm.getName()+"]在毒物副本中成功的兑换了阿尔泰耳环!");
	    status = -1;
	}}
	else if (selection == 77) {
		if (cm.getInventory(1).isFull(0)){//判断第一个也就是装备栏的装备栏是否有一个空格
		cm.sendOk("#b请保证装备栏位至少有1个空格,否则无法兑换.");
		cm.dispose();
		} else if(cm.getPlayer().getOneTimeLog("nvsheng1") >= 限制兑换次数){//判断永久记录
		cm.sendOk("你已经领取过了,无法在重复领取!");
        cm.dispose();
		} else if(get积分("毒物积分") < 积分兑换){//判断永久记录
		cm.sendOk("毒物副本积分不足"+积分兑换+"分,当前积分:"+get积分("毒物积分")+"分!");
        status = -1;
		} else {
		gain积分("毒物积分",-积分兑换);
        cm.getPlayer().setOneTimeLog("nvsheng1");//给永久记录
		cm.gainItem(1012146, 10, 10, 10, 10, 10, 10, 5, 5, 0, 0, 0, 0, 0, 0);//毒物鼻子
		cm.sendOk("恭喜你,成功的兑换毒物副本专属道具成功，请检背包!!");
        cm.worldMessage(6,"恭喜玩家：["+cm.getName()+"]成功使用副本积分换购了(毒物副本)专属装备！");
	    status = -1;
		
					}						
	}
	
	
	else if (selection == 4) {
		if (cm.getInventory(1).isFull(0)){//判断第一个也就是装备栏的装备栏是否有一个空格
		cm.sendOk("#b请保证装备栏位至少有1个空格,否则无法兑换.");
		cm.dispose();
		} else if(cm.getPlayer().getOneTimeLog("Ellin2") >= 1){//判断永久记录
		cm.sendOk("你已经领取过了,无法在重复领取!");
        cm.dispose();
		} else if(cm.getPlayer().getOneTimeLog("Ellin") < 100){//判断永久记录
		cm.sendOk("你还没有成功挑战够100次,当前已经挑战成功了:"+cm.getPlayer().getOneTimeLog("Ellin")+"次!");
        status = -1;
		} else {
        cm.getPlayer().setOneTimeLog("Ellin2");//给永久记录
		cm.gainItem(1032061, 1, true);//物品代码,数量,随机属性
		cm.sendOk("恭喜你,成功的领取了#v1032061##z1032061#!");
        cm.worldMessage(6,"恭喜玩家：["+cm.getName()+"]在毒物副本中成功的兑换了发光的阿尔泰耳环!");
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