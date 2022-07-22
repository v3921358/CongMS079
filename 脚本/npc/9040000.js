/* 
 * Shuang, Victoria Road: Excavation Site<Camp> (101030104)
 * Start of Guild Quest
 */

var status;
var GQItems = Array(1032033, 4001024, 4001025, 4001026, 4001027, 4001028, 4001031, 4001032, 4001033, 4001034, 4001035, 4001037);

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 0 && status == 0) {
        cm.dispose();
        return;
    }
    if (mode == 1) {
        status++;
    } else {
        status--;
    }

    if (status == 0) {
        if (cm.getPlayer().hasEquipped(1032033)) {
            cm.sendOk("请脫下#t1032033#。");
            cm.dispose();
        } else {
            cm.sendSimple("你想做什么? #b\r\n#L0#开始一个公会守义战#l\r\n#L1#加入公会的公会守义战#l");
        }

    } else if (status == 1) {
        if (selection == 0) { //Start
            if (cm.getPlayerStat("GID") == 0 || cm.getPlayerStat("GRANK") >= 3) { //no guild or not guild master/jr. master
                cm.sendNext("只有公会长和副公会长才能启动公会守义战。");
                cm.dispose();
            } else {
                var em = cm.getEventManager("GuildQuest");
                if (em == null) {
                    cm.sendOk("公会守义战尚未实施建设。");
                } else {
                    var prop = em.getProperty("started");

                    if ((prop.equals("false") || prop == null) && em.getInstance("GuildQuest") == null) {
                        for (var i = 0; i < GQItems.length; i++) {
                            cm.removeAll(GQItems[i]);
                        }
                        em.startInstance(cm.getPlayer(), cm.getPlayer().getName());
                        em.setProperty("state", "0");
                        cm.guildMessage("公會已經開始公會守護戰 <頻道:" + cm.getClient().getChannel() + "> 請公會成員盡速來到遺跡發掘對營地參加公會守護戰。");
                    } else {
                        cm.sendOk("已經有一個公會正在實施公會守護戰。")
                    }
                }
                cm.dispose();
            }
        } else if (selection == 1) { //entering existing GQ
            if (cm.getPlayerStat("GID") == 0) { //no guild or not guild master/jr. master
                cm.sendNext("你尚未沒有加入一個公會。");
                cm.dispose();
            } else {
                var em = cm.getEventManager("GuildQuest");
                if (em == null) {
                    cm.sendOk("公會守護戰尚未實施建設。");
                } else {
                    var eim = em.getInstance("GuildQuest");

                    if (eim == null) {
                        cm.sendOk("你的公会尚未开启公会守义战。");
                    } else {
                        //			if (em.getProperty("guildid") != null && !em.getProperty//("guildid").equalsIgnoreCase("" + cm.getPlayerStat("GID"))) {
                        //			if (cm.getPlayer().isGM()) {
                        //			    cm.sendOk("This instance is not your guild. Instance Guild: "  + //em.getProperty("guildid") + ", Your Guild: " + cm.getPlayerStat("GID"));
                        //			} else {
                        //			    cm.sendOk("This instance is not your guild.");
                        //			}
                        //			} else 
                        if (em.getProperty("started").equals("false")) {
                            for (var i = 0; i < GQItems.length; i++) {
                                cm.removeAll(GQItems[i]);
                            }
                            eim.registerPlayer(cm.getPlayer());
                        } else {
                            cm.sendOk("我很抱歉，但公會已經沒有你，請稍後再嘗試！");
                        }
                    }
                }
                cm.dispose();
            }
        }
    }
}