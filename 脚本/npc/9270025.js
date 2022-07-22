/* 	Xan
	Lian Hua Hua Skin Care
*/
var status = -1;
var skin = [0, 1, 2, 3, 4];

function action(mode, type, selection) {
    if (mode == 0) {
	cm.dispose();
	return;
    } else {
	status++;
    }

    if (status == 0) {
	cm.sendNext("嗨，歡迎來到新加坡，我是#p9270025# 如果您有#b#t5153010##k 我就可以免費幫您整形一次。");
    } else if (status == 1){
	if (cm.setRandomAvatar(5153010, skin) == 1) {
	    cm.sendOk("享受你新的造型吧！");
	} else {
	    cm.sendOk("由於沒有#b#t5153010##k 所以我不能幫忙。");
	}
	cm.safeDispose();
    }
}