function action(mode, type, selection) {
    if (cm.getQuestStatus(6410) == 1) {
        var ddz = cm.getEventManager("ProtectRichard");
		if (cm.getParty() == null) {
			cm.sendOk("請組隊。");
		} else {
        if (ddz == null) {
            cm.sendOk("未知的錯誤");
        } else {
            var prop = ddz.getProperty("state");
            if (prop == null || prop.equals("0")) {
                ddz.startInstance(cm.getParty(), cm.getMap());
            } else {
                cm.sendOk("別人挑戰了，請稍後重試了一下.");
            }
        }
		}
    } else if (cm.getMapId() == 925010400) {
		cm.warp(120000104,0);
	}
    cm.dispose();
}