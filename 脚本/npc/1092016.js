/* Author: aaroncsn (MapleSea Like)
	NPC Name: 		Glittering Stone
	Map(s): 		Nautilus:Power Source Chamber(120000301)
	Description: 		Unknown
*/

function start() {
    if(cm.getQuestStatus(2166) == 1) {
        cm.sendNext("這是一個美麗的，閃亮的岩石。我能感覺到它周圍的神秘力量。");
		cm.forceCompleteQuest(2166);
    } else
        cm.sendNext("我用我的手摸了摸發亮的岩石，我感到一種神秘的力量流進我的身體。");
    cm.dispose();
}