var cost = 20000;

function start() {
    status = -1;
    em = cm.getEventManager("AirPlane");
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if(mode == 0 && status == 0) {
	cm.dispose();
	return;
    }
    if (mode == 1) {
	status++;
    }
    if (mode == 0 && menu == 0) {
	cm.sendNext("我在這裡很長一段時間。請改變主意再來跟我說話.");
	cm.dispose();
    }
    if (mode == 0 && menu == 1) {
	cm.sendOk("我在這裡很長一段時間。請改變主意再來跟我說話..");
	cm.dispose();
    }
    if (status == 0) {
	cm.sendSimple("嗨~ 我是 #p"+cm.getNpc()+"# 來自新加坡機場. 我會幫助你回去 #m103000000# 立刻! 需要幫忙??\r\n#L0##b我想購買飛機票到 #m103000000##k#l\r\n#L1##b出發.#k#l");
    } else if(status == 1) {
	menu = selection;
	if (menu == 0) {
	    cm.sendYesNo("飛機票兩萬楓幣是否要購買??");
	} else if (menu == 1) {
	    cm.sendYesNo("你是否要走了?? 一旦走了你將失去一張飛機票, \r\n感謝您選擇Wizet航空公司!");
	}
  } else if(status == 2) {
	if(menu == 0) {
	    if(!cm.canHold(4031732) || cm.getMeso() < cost) {
		cm.sendOk("你確定你有 #b"+cost+" 楓幣#k? 如果是這樣的話，我勸您檢查其他欄，看看是否滿了!.");
	    } else {
		cm.gainMeso(-cost);
		cm.gainItem(4031732, 1);
	    }
	    cm.dispose();
	} else if(menu == 1) {
	  if(em == null) {
		cm.sendNext("腳本錯誤請回報GM!");
		cm.dispose();
	  } else if(!cm.haveItem(4031732)) {
		cm.sendNext("請先購買飛機票謝謝~");
		cm.dispose();
	} else if (em.getProperty("entry") != null && em.getProperty("entry").equals("true")) {
		cm.sendYesNo("是否要搭飛機??");
		} else if( em.getProperty("entry") != null && em.getProperty("entry").equals("false") && em.getProperty("docked") != null && em.getProperty("docked").equals("true")) {
		cm.sendNext("這架飛機正準備起飛。我很抱歉，但你必須得在接下來的旅程。乘坐時間表可通過在迎來售票展台");
		cm.dispose();
	    } else {
		cm.sendNext("請耐心等待幾分鐘，正在整理裡面中！");
		cm.dispose();
	    }
	}
  } else if (status == 3) {
		cm.gainItem(4031732,-1);
		cm.warp(540010001);
		cm.dispose();
	    }
	}
	
