/* Gina
	Ludibrium Skin Change.
*/
var status = -1;
var skin = Array(0, 1, 2, 3, 4);

function action(mode, type, selection) {
    if (mode == 0) {
	cm.dispose();
	return;
    } else {
	status++;
    }
    if (status == 0) {
	cm.sendNext("喔，嗨! 歡迎來到玩具城護膚中心! 你想要變性感嗎?? 多麼美麗, 雪白的皮膚?? 如果你有 #b#t5153002##k, 你可以跟我們談談你想要變得怎麼樣~");
    } else if (status == 1) {
	cm.askAvatar("選擇一個想要的。", skin);
    } else if (status == 2){
	if (cm.setAvatar(5153002, skin[selection]) == 1) {
	    cm.sendOk("享受!");
	} else {
	    cm.sendOk("痾貌似沒有#t5153002#");
	}
	cm.safeDispose();
    }
}