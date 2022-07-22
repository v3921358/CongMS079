var status = -1;
var ca = java.util.Calendar.getInstance();
var weekday = ca.get(java.util.Calendar.DAY_OF_WEEK);
var pm = -1;
var gifts = [
    [100, 3 * 10000 * 10000],
    [70, 2 * 10000 * 10000],
    [50, 10000 * 10000]
];

function start() {
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 0) {
        cm.dispose();
        return;
    }
    status++;
    if (status == 0) {
        var text = "欢迎来到家族排行\r\n";
        text += "#L0#本周排行#l\t\t";
        text += "#L1#上周排行#l\r\n";
        text += "\t\t#L3#奖励说明#l";
        cm.sendSimple(text);
    } else if (status == 1) {
        if (selection == 3) {
            var txt = "#d┏━━━━━━━━━━家族排名奖励━━━━━━━━┓#k\r\n\r\n"
            for (var i in gifts) {
                txt += "第" + (i + 1) + "名    元宝x" + gifts[i][0] + "  金币" + gifts[i][1] + "\r\n";
            }
            txt += "#d┗━━━━━━━━━━━━━━━━━━━━━━━━━┛#k";
            cm.sendOk(txt);
            cm.dispose();
        } else if (selection == 1 || selection == 0) {
            var sql = "select g.guildid, g.name, l.value from guilds g, ranklist l where g.guildid = l.rid and l.rtype = 1 and WEEKOFYEAR(rtime) = WEEKOFYEAR(NOW()) - ? order by l.value desc";
            var list = cm.sql_Select(sql, selection);
            var text = "\t\t\t\t#e#d★ 家族排行 ★#k#n\r\n\r\n";
            var i = 1;
            for (var ii in list) {
                if (cm.getGuildId() == parseInt(list[ii].get("guildid"))) {
                    pm = i;
                }
                if (i == 1) {
                    text += "#r";
                } else if (i == 2) {
                    text += "#g";
                } else if (i == 3) {
                    text += "#b";
                }
                text += "\t " + i + "\t\t ";
                text += list[ii].get("name");
                for (var j = 16 - list[ii].get("name").getBytes().length; j > 0; j--) {
                    text += " ";
                }
                text += "\t " + list[ii].get("value");
                for (var j = 16 - list[ii].get("value").getBytes().length; j > 0; j--) {
                    text += " ";
                }
                text += "\t #k";
                text += "\r\n";
                i++;
            }
            if (pm > 0 && pm < 4 && selection == 1) {
                text += "\r\n#r#e你的家族上周排名第" + pm + "，#r#L99#领取上周奖励#l";
            } else {
                cm.dispose();
            }
            cm.sendOk(text);
        }
    } else if (status == 2) {
        if (selection == 99) {
            if (getlog() > 0) {
                cm.sendOk("你本周已经领取过此奖励了");
            } else if (pm > 0 && pm < 4) {
                //给金币
                cm.gainMeso(gifts[pm-1][1]);
                //给元宝
				cm.setmoneyb(gifts[pm-1][0]);
                cm.sendOk("恭喜你领取成功");
                cm.setBossLog("家族排行奖励");
            }
        }
        cm.dispose();
    }
}

function getlog() {
    var sql = cm.sql_Select("select * from bosslog where characterid = ? bossid = ? and WEEKOFYEAR(time) = WEEKOFYEAR(NOW())", cm.getPlayer().getId(), "家族排行奖励");
    if (sql.length > 0) {
        return 1;
    } else {
        return 0;
    }
}

