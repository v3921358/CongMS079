/*
Papulatus Reactor: Performs the Papulatus commands
*/

function act(){
    rm.mapMessage(5, "即由<时空裂缝的碎片>填补了時空的裂缝。");
    rm.changeMusic("Bgm09/TimeAttack");
    rm.spawnMonster(8500000, -410, -400);
    rm.getMap(220080000).setReactorState();
	rm.getMap().Papfight();
}