function enter(pi) {
    if (pi.getQuestStatus(6110) == 1) {
	 if (pi.getParty() != null) {
	     if (!pi.isLeader()) {
		 pi.playerMessage("請找隊伍隊長來找我。");
	     } else {
		 if (pi.getParty().getMembers().size < 2) {
		    pi.playerMessage("隊伍人數必須大於兩個以上。");
		 } else {
		      if (!pi.isAllPartyMembersAllowedJob(1)) {
			  pi.playerMessage("隊伍裡有人職業不符合。");
		      } else {
			  var em = pi.getEventManager("4jrush");
			  if (em == null) {
			      pi.playerMessage("尚未找到副本，請聯繫管理員。");
			  } else {
			      em.startInstance(pi.getParty(), pi.getMap());
			      return true;
			  }
		      }
		 }
	     }
	 } else {
	     pi.playerMessage(5, "尚未組隊，請組隊後再來找我。");
	 }
    } else {
	pi.playerMessage("由於太過強大您無法進入。");
    }
    return false;
}