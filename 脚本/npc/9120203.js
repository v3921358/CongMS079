/* Konpei
	Showa
*/

var flash;
var status = 0;

function start() {
    flash = cm.haveItem(4000141);
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        cm.sendOk("我真的覺得你很任性，好吧！如果你決定要回到#r#m801000000##再讓我知道吧！");
        cm.dispose();
        return;
    }

    if (status == 1) {
        if (flash) {
            cm.sendNext("哇哦，你做到了！你知道嗎?我很高興他終於走了。");
        } else {
            cm.sendYesNo("你想要回到#r#m801000000##?");
        }
    } else if (status == 2) {
        if (flash) {
            cm.sendNext("我不得不承認你很強大！");
        } else {
            cm.warp(801000000, 0);
            cm.dispose();
        }
    } else if (status == 3) {
        cm.gainItem(4000141, -1);
        cm.gainItem(2000004, 200);
        cm.warp(801000000, 0);
        cm.dispose();
    }
}