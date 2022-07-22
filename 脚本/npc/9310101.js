/*

 脚本：拍卖主菜单
 */


function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (status == 0 && mode == 0) {
        cm.dispose();
        return;
    }
    if (mode == 1) {
        status++;
    } else {
        status--;
    }
	//开始
    if (status == 0) {
		
        var selStr = "\r\n";
		
			selStr += " 1.豆豆可以通过在商城购买#b豆豆票#k，然后在豆豆机兑换豆豆，每位玩家一天只能兑换#b10#k次豆豆，每次兑换的豆豆数量不固定。\r\n";
			selStr += " 2.豆豆可以在#b豆豆交换机#k处购买礼物。\r\n";
			selStr += " 3.每次打豆豆会消耗#b5颗豆豆#k。\r\n";
			selStr += " 4.背包里查看豆豆并不会实时更新，需要小退一下才会显示正确，但是实际数量在购买豆豆礼物，打豆豆后是会变化的。\r\n";

        cm.sendSimple(selStr);
    } else if (status == 1) {
        switch (selection) {
            case 1:
                cm.dispose();
                cm.openNpc(9900004, 1);
                break;
            
			default:
                cm.dispose();
                break;
        }
    }
}