var status = -1;

function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        cm.sendNext("噢噢~ 你還想待在這裡的話，我隨時都在哦 >_^");
        cm.safeDispose();
    }

    if (status == 0) {
        cm.sendYesNo("嗨~~你在這裡沒事做了嗎? 你想要現在回去 #b自由市場#k? 我隨時都能送你回去。你想要現在回去嗎？");
    } else if (status == 1) {
        cm.warp(910000000);
        cm.dispose();
    }
}
