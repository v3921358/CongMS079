function enter(pi) {
    if (pi.getQuestStatus(20021) == 0) {
	pi.playerSummonHint(true);
	pi.summonMsg("欢迎來到我的世界! 娇贵的小宝贝!我的名字是 提酷, 我会是你的指导老师！ 我会在这里回答你的问题，并指导你直到等级10级，成为骑士团之前如果你有任何疑问，可以点击我！");
//	pi.forceCompleteQuest(20100);
	pi.forceCompleteQuest(20021);
    }
}