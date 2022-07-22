var FY0 = "┏━━━━━━━━━━━┓";
var FY1 = "┃       - 枫叶 -       ┃";
var FY2 = "┃ 脚本仿制  　定制脚本 ┃";
var FY3 = "┃ 技术支持 　 游戏顾问 ┃";
var FY4 = "┃ ＷＺ添加　  地图制作 ┃";
var FY5 = "┃ 加盾防御　  售登陆器 ┃";
var FY6 = "┣━━━━━━━━━━━┫";
var FY7 = "┃ 唯一QQ:1848350048    ┃";
var FY8 = "┗━━━━━━━━━━━┛";
 importPackage(java.lang);
importPackage(Packages.tools);
importPackage(Packages.client);
importPackage(Packages.server);
importPackage(Packages.tools.packet);
	
importPackage(Packages.handling.word);
importPackage(Packages.client.inventory);
var 彩虹 ="#fEffect/ItemEff/1071085/effect/walk1/2#";
var ca = java.util.Calendar.getInstance();
var 皇冠白 ="#fUI/GuildMark/Mark/Etc/00009004/16#";
var year = ca.get(java.util.Calendar.YEAR); //获得年份
var month = ca.get(java.util.Calendar.MONTH) + 1; //获得月份
var day = ca.get(java.util.Calendar.DATE); //获取日
var hour = ca.get(java.util.Calendar.HOUR_OF_DAY); //获得小时
var minute = ca.get(java.util.Calendar.MINUTE); //获得分钟
var second = ca.get(java.util.Calendar.SECOND); //获得秒
var weekday = ca.get(java.util.Calendar.DAY_OF_WEEK);

var cishuxianzhi = 10;//限制次数
var 最小等级 = 10;
var 最高等级 = 250;
var 最少人数 = 1;
var 最多人数 = 6;

var status = -1;
function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        if (status == 0) {
            cm.对话结束();
			
            return;
        }
        status--;
    }
    if (cm.getMapId() == 910010100) {
        cm.sendOk("恭喜您完成了月杪任务，点击左边的达尔米可以领取奖励完成任务！");
        cm.对话结束();
	 } else if (status == 0) {
	 cm.sendYesNo("\r\n\t\t     "+彩虹+"#e#d月 妙 副 本 #k#n  #r"+彩虹+" #k\r\n\r\n副本进入要求如下：\r\n①人数限制:#r " + 最少人数 + " #b- #r" + 最多人数 + "#k队员\t②等级限制：#r " + 最小等级 + " #b- #r" + 最高等级 + "级 #k\r\n每天只能挑战:#k#b"+ cishuxianzhi +"#k次 你今天已进入:#b"+ cm.getPlayer().getBossLog("YueMiaoCiShu") +"#k次#k\r\n副本奖励:\r\n "+cm.显示物品(4001101)+" "+cm.显示物品(1002798)+" \r\n "+cm.显示物品(4001126)+"  "+cm.显示物品(4000313)+" "+cm.显示物品(4170000)+"");
    } else if (status == 1) {
        if (cm.getParty() == null) {
            cm.sendSimple("请组队后再找我把。");
			cm.dispose();
			return;
        } else if (!cm.isLeader()) {
            cm.sendSimple("如果你想尝试，请告诉 #b组队队长#k 跟我说话.#b#l");
			cm.dispose();
			return;
        }else if(cm.getPartyBosslog("YueMiaoCiShu",(cishuxianzhi)) == false) {//判断组队是否2次
	            cm.sendOk("队伍中队友挑战次数已经用完"+ cishuxianzhi +"次！");
                cm.dispose();
				return;
	    }else if( cm.getPlayer().getBossLog("YueMiaoCiShu") >= cishuxianzhi) {
	            cm.sendOk("您好,限定每天只能挑战"+ cishuxianzhi +"次！");
                cm.dispose();
				return;
	    } else {
            var party = cm.getParty().getMembers();
            var mapId = cm.getMapId();
            var next = true;
            var levelValid = 0;
            var inMap = 0;
            var it = party.iterator();

            while (it.hasNext()) {
                var cPlayer = it.next();
                if ((cPlayer.getLevel() >= 最小等级) && (cPlayer.getLevel() <= 最高等级)) {
                    levelValid += 1;
                } else {
                    next = false;
                }
                if (cPlayer.getMapid() == mapId) {
                    inMap += (cPlayer.getJobId() == 900 ? 6 : 1);
					
                }
				
            }
            if (party.size() > 最多人数 || inMap < 最少人数) {
                next = false;
				
            }
            if (next) {
                var em = cm.getEventManager("HenesysPQ");
                if (em == null) {
                    cm.sendSimple("PQ遇到了一个错误。请联系GM，与截图.#b#l");
                } else {
                    var prop = em.getProperty("state");
                    if (prop.equals("0") || prop == null) {
                        for (var i = 4001095; i < 4001099; i++) {
                            cm.givePartyItems(i, 0, true);
                        }
                        for (var i = 4001100; i < 4001101; i++) {
                            cm.givePartyItems(i, 0, true);
                        }
						
                        em.startInstance(cm.getParty(), cm.getMap());
						cm.setPartyBosslog("YueMiaoCiShu");//给团队次数
                        cm.对话结束();
						
                        return;
                    } else {
                        cm.sendSimple("另一方已进入 #r月秒任务#k 在这里。请尝试另一个频道，或者等待当前的任务完成.#b#");
                    }
                }
            } else {
                cm.sendOk("副本: #b月妙#k\r\n人数: #b" + 最少人数 + " - " + 最多人数 + "#k\r\n等级: #b" + 最小等级 + " - " + 最高等级 + "#k");
				
                cm.对话结束();
            }
        }
    }
}
