/*
	名字: 		傑伊克
	地圖: 		婚禮村小鎮
	描述: 		結婚戒指交換
*/

var status = -1;

function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        cm.dispose();
        return;
    }
    if (status == 0) {
        if (cm.getPlayer().getMarriageId() <= 0) {
            cm.sendOk("你好像還沒結婚呢，婚都沒結就想要結婚戒指？你還是先找個心愛的人，結完婚再來吧~");
            cm.dispose();
        } else {
            cm.sendSimple("你好啊~ 我聞到了一股甜蜜蜜的新婚味道哦~ 哎喲，怎麼還戴著訂婚戒指啊？結了婚就要換漂亮的結婚戒指才行嘛！你願意的話，我可以給你們換，怎麼樣？\r\n\r\n#L0# 把訂婚戒指換成結婚戒指。#l");
        }
    } else if (status == 1) {
        cm.sendNext("結婚戒指也可以裝備的，一定要試試看哦~");
        cm.dispose();
    }
}