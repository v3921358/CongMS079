function enter(pi) {
	if (pi.getQuestStatus(6241) == 1 || pi.getQuestStatus(6243) == 1) {
		if (pi.getJob() == 312) {
			if (pi.haveItem(4001113)) {
				if (pi.getPlayerCount(924000100) > 0) {
					pi.playerMessage("有其他人在裡面挑戰請稍後再嘗試。");
					return false;
				}
				var em = pi.getEventManager("s4nest");
				if (em == null) {
					pi.playerMessage("發現未知的錯誤請會報給管理員。");
				} else {
					em.startInstance(pi.getPlayer());
					return true;
				}
			} else {
				pi.playerMessage("由於沒有鳳凰的蛋所以無法進入。");
			}
		} else if (pi.getJob() == 322) {
			if (pi.haveItem(4001114)) {
				if (pi.getPlayerCount(924000100) > 0) {
					pi.playerMessage("有其他人在裡面挑戰請稍後再嘗試。");
					return false;
				}
				var em = pi.getEventManager("s4nest");
				if (em == null) {
					pi.playerMessage("發現未知的錯誤請會報給管理員。");
				} else {
					em.startInstance(pi.getPlayer());
					return true;
				}
			} else {
				pi.playerMessage("由於沒有菲瑞爾的蛋所以無法進入。");
			}
		}
	} else {
		pi.playerMessage("你不能進入這個地方。");
	}
	return false;
}
