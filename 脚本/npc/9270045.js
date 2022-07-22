function start() {
    status = -1;

    action(1, 0, 0);
}
function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    }
    else {
        if (status >= 0 && mode == 0) {

            cm.sendOk("感谢你的光临！");
            cm.dispose();
            return;
        }
        if (mode == 1) {
            status++;
        }
        else {
            status--;
        }
        if (status == 0) {
            var tex2 = "";
            var text = "";
            for (i = 0; i < 10; i++) {
                text += "";
            }
			
            text += "#e#d你好勇士！能来到这里说明你很有实力.[千年树精王]就在上面睡觉了，打那个小球球会激怒它的~如果你愿意就带着队友征服它吧！\r\n\r\n"//3
            
            text += "#L2##e#r离开此地图.#l\r\n"//3
           
            cm.sendSimple(text);
        } else if (selection == 1) {
		cm.openNpc(9270045, 1);
        } else if (selection == 2) {
			cm.warp(910000000); 
			cm.dispose(); 
        } 
    }
}


