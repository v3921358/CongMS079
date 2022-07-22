/*
	Weaver - Ludibrium : Ludibrium Pet Walkway (220000006)
**/

var status = 0;

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (status == 0 && mode == 0) {
	cm.dispose();
	return;
    } else if (status >= 1 && mode == 0) {
	cm.sendNext("需要的時候可以來找我。");
	cm.dispose();
	return;
    }
    if (mode == 1)
	status++;
    else
	status--;
    if (status == 0) {
	cm.sendYesNo("這是在路上，你可以去與你的寵物散步。你可以走動的，或者你可以訓練你的寵物要經過這裡的障礙。如果你不是太密切的與您的寵物然而，這可能會出現問題，他不會聽從你的命令一樣多......那麼，你有什麼感想？想培養你的寵物？");
    } else if (status == 1) {
	if (cm.haveItem(4031128)) {
	    cm.sendNext("拿到這一封信，跳躍過那些障礙把這封信給我弟弟他會給你獎勵...");
	    cm.dispose();
	} else {
	    cm.gainItem(4031128, 1);
	    cm.sendOk("好運!");
	    cm.dispose();
	}
    }
}