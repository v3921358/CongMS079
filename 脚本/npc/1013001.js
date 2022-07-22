var status = 0;

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 1)
        status++;
    else if (mode == 0)
        status--;
    else {
        cm.dispose();
        return;
    }
    if (status == 0) {
        cm.sendNext("终于找到了，符合契约条件的人…");
    } else if (status == 1) {
        cm.sendNextPrev("履行契約的条件…");
    } else if (status == 2) {
        cm.warp(100030100);
        cm.dispose();
    }
}