/*
 名字: 		安琪莉可
 地圖: 		婚禮村
 描述: 		結婚禮物管理人
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
            cm.sendSimple("需要我幫忙嗎？\r\n#b#L0##b我想查看結婚禮物。#l#k");
        }
    } else if (status == 1) {
        cm.sendNext("您的結婚禮物好像剛剛已經被拿走了！？");
        cm.dispose();
    }
}