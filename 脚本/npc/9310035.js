/*
	NPC Name: 		Vikin
	Map(s): 		Victoria Road: Lith Harbor (104000000)
*/
var status = 0;

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (status >= 0 && mode == 0) {
	cm.dispose();
	return;
    }
    if (mode == 1)
	status++;
    else
	status--;
    if (status == 0) {
	cm.sendOk("您好施主！出家人没有美发哦");
	cm.dispose();
    }
}