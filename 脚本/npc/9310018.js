/* Romi
	Orbis Skin Change.
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
	cm.sendNext("嗨, 我是#p9310018# 如果你有一張 #b#t5153003##k, 我可以幫你美容皮膚！");
    } else if (status == 1) {
	cm.askAvatar("選擇一個你想要美容的皮膚~", skin);
    } else if (status == 2){
	if (cm.setAvatar(5153003, skin[selection]) == 1) {
	    cm.sendOk("享受!");
	} else {
	    cm.sendOk("您貌似沒有#b#t5153003##k..");
	}
	cm.safeDispose();
    }
}