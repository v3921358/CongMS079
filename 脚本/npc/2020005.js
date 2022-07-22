/**
 -- Odin JavaScript --------------------------------------------------------------------------------
 Alcaster - El Nath Market (211000100)
 -- By ---------------------------------------------------------------------------------------------
 Unknown/Information/xQuasar
 -- Version Info -----------------------------------------------------------------------------------
 1.3 - Fixed up completely [xQuasar]
 1.2 - Add a missing text part [Information]
 1.1 - Recoded to official [Information]
 1.0 - First Version by Unknown
 ---------------------------------------------------------------------------------------------------
 **/

var selected;
var amount;
var totalcost;
var item = new Array(2050003, 2050004, 4006000, 4006001);
var cost = new Array(300, 400, 5000, 5000);
var msg = new Array("聖水", "萬能療傷", "魔法石", "召喚石");
var status = -1;

function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        if (status == 2) {
            cm.sendNext("你需要的話再來找我。.");
            cm.safeDispose();
            return;
        }
        status--;
    }

    if (status == 0) {
        if (cm.getQuestStatus(3035) == 2) {
            var selStr;
            for (var i = 0; i < item.length; i++) {
                selStr += "\r\n#L" + i + "# #b#t" + item[i] + "# (價格: " + cost[i] + " 楓幣)#k#l";
            }
            cm.sendSimple("謝謝你購買 #b#t4031056##k " + selStr);
        } else {
            cm.sendNext("如果你幫助我，作為獎勵我會把我最棒的物品賣給你。");
            cm.safeDispose();
        }
    } else if (status == 1) {
        selected = selection;
        cm.sendGetNumber("#b#t" + item[selected] + "##k 真的是你需要的道具？這個道具 " + msg[selected] + ". 它可能不是獲取最簡單的項目，但我會給你一個很好的協議就可以了。它會花費你 #b" + cost[selected] + " 楓幣#k 你想購買多少？？", 0, 1, 100);
    } else if (status == 2) {
        amount = selection;
        totalcost = cost[selected] * amount;
        if (amount == 0) {
            cm.sendOk("如果你不打算買任何東西的話，我也沒有什麼可賣。");
            cm.dispose();
        }
        cm.sendYesNo("你真的想要買 #r" + amount + " #t" + item[selected] + "##k? 費用是 " + cost[selected] + " 楓幣 每個 #t" + item[selected] + "#, 總共費用是 #r" + totalcost + " 楓幣#k");
    } else if (status == 3) {
        if (cm.getMeso() < totalcost || !cm.canHold(item[selected])) {
            cm.sendOk("你確定你的楓幣足夠嗎，如果沒有至少也要有 #r" + totalcost + "#k 楓幣.");
            cm.dispose();
            return;
        }
        cm.sendNext("謝謝。如果你發現自己需要的物品的道路，確保這裡所下降。我可能已經得到了舊歷年，但我仍然可以輕鬆的魔法物品。.");
        cm.gainMeso(-totalcost);
        cm.gainItem(item[selected], amount);
        cm.safeDispose();
    }
}