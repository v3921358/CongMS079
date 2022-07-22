/* ==================
 脚本类型:  海盗副本NPC	    
 脚本作者： 颜林   
 联系方式： 1500663066
 =====================
 */
var 中条猫 ="#fUI/ChatBalloon/37/n#";
var status = 0;
var fbmc = "百草堂-(海盗副本)";//副本名称
var minLevel = 80;//最低等级
var maxLevel = 250;//最高等级
var minPartySize = 2;//最少人数
var maxPartySize = 6;//最多人数
var cishuxianzhi = 10;//限制次数
var maxjinbi = 50000;//判断征集令金币
var 积分兑换 = 50;//判断积分兑换数量
var 限制兑换次数 = 999;//判断积分兑换次数
var eventname = "Pirate";//副本配置文件


function checkMap() {
    var map = [925100000, 925100100, 925100200, 925100201, 925100202, 925100300, 925100301, 925100302, 925100400, 925100400, 925100500];
    for(var i = 0 ; i < map.length; i++) {
        if(cm.getPlayerCount(map[i]))
            return false;
    }
    return true;
}

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
			text += "#k\t\t\t\t#e#d   《海 盗 副 本》 #k\r\n\r\""+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+"#k\r\n"
            text += "#k\r\n副本进入要求如下：\r\n①人数限制:#r " + minPartySize + " #b- #r" + maxPartySize + "#k队员\t②等级限制：#r " + minLevel + " #b- #r" + maxLevel + "级 #k\r\n"
			text += "#k副本积分:#r"+get积分("海盗积分")+"#k分 每天只能挑战:#b"+ cishuxianzhi +"#k次 你今天已进入:#b"+ cm.getPlayer().getBossLog("haidaocs") +"#k次#k\r\n"
            
			text += "#L1##r【开始组队副本】#l      \r\n"
			text += "#L3##r【兑换副本装备】("+积分兑换+"副本积分)#v1002571##l\r\n\r\n"
			text += "#L68##r【查看副本奖励】#l      "
			//text += "#L3##r挑战满10次领取#v1002571##z1002571##l\r\n\r\n"
            cm.sendSimple(text);
	} else if (selection == 1) {
	
	
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
		   cm.removeAll(4001117);
	       cm.removeAll(4031437);
	       cm.removeAll(4001120);
	       cm.removeAll(4001121);
	       cm.removeAll(4001260);
	       cm.removeAll(4001122);
		   } else {
				cm.sendOk("组队成员必须全部在这里。");
				cm.dispose();
			}	
	
    if (cm.getPlayer().getParty() == null || !cm.isLeader()) {
        cm.sendOk("请找队长来找我。");
		cm.dispose();
	}else if(cm.getPartyBosslog("haidaocs",(cishuxianzhi)) == false) {//判断组队是否2次
	            cm.sendOk("队伍中队友挑战次数已经用完"+ cishuxianzhi +"次！");
                cm.dispose();
				return;
	}else if( cm.getPlayer().getBossLog("haidaocs") >= cishuxianzhi) {
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
            if (ccPlayer == null || ccPlayer.getLevel() <  minLevel || ccPlayer.getLevel() > maxLevel) {
                next = false;
                break;
            }
            size += (ccPlayer.isGM() ? 2 : 1);
        }
        if (next && size >= minPartySize) {
            if(checkMap()) {
                var em = cm.getEventManager("Pirate");
                if (em == null) {
                    cm.sendOk("找不到脚本，请联系GM！！");
					cm.dispose();
                } else {
                    em.startInstance(cm.getPlayer().getParty(), cm.getPlayer().getMap());
					cm.setPartyBosslog("haidaocs");//给团队次数
					cm.dispose();
                }
            } else {
                cm.sendOk("目前有人在打啰～");
				cm.dispose();
            }
        }else {
            cm.sendOk("需要" + minPartySize + "至" + maxPartySize + "个人 等级必须是" + minLevel+ "到" + maxLevel + "级");
			cm.dispose();
        }
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
		} else if(cm.getPlayer().getOneTimeLog("haidaocs") >= 限制兑换次数){//判断永久记录
		cm.sendOk("你已经领取过了,无法在重复领取!");
        cm.dispose();
		} else if(get积分("海盗积分") < 积分兑换){//判断永久记录
		cm.sendOk("海盗副本积分不足"+积分兑换+"分,当前积分:"+get积分("海盗积分")+"分!");
        status = -1;
		}else {
        cm.getPlayer().setOneTimeLog("haidaocs");//给永久记录
		gain积分("海盗积分",-积分兑换);
		cm.gainItem(1002571, 20, 20, 20, 20, 20, 20, 10, 10, 0, 0, 0, 0, 0, 0);//id, 力量, 敏捷, 运气, 智力, hp, mp, 物攻, 魔攻, 物防, 魔防, 回避, 命中, 跳跃, 移动速度
		cm.sendOk("恭喜你,成功的领取了#v1002571##z1002571#!");
        cm.worldMessage(6,"恭喜玩家：["+cm.getName()+"]在海盗副本中成功的兑换了海盗船长帽!");
	    status = -1;
		
					}						
	}else if (selection == 68) {
				cm.dispose();
				cm.openNpc(2094000, 68);			
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
