/* Konpei
 Showa - Nightmarish Last Days
 */

var flash;

function start() {
    flash = cm.haveItem(4000141);

    if (!flash) {
        cm.sendNext("一旦您打死老闆的話，您必須拿著老大的道具來做為證據，什麼?您要離開這個房間嗎??");
    } else {
        cm.sendNext("嘿，嘿！這裡是很危險的您還是趕快離開吧！")
    }
}

function action(mode, type, selection) {
    if (mode == 1) {
        if (!flash) {
            cm.warp(801040000, 0);
            cm.dispose();
        } else {
            cm.warp(801040101, 0);
            cm.dispose();
        }
    } else {
        cm.sendOk("我真的很佩服你的任性!好吧，如果你想要回到昭和村的時候再讓我知道吧！");
    }
}