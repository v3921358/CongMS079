/*
 * Ruin of Krexel II
 *  - Reactor to summon Krexel
 */

function act() {
    rm.changeMusic("Bgm09/TimeAttack");
    rm.spawnMonster(9420520);
    rm.mapMessage(5, "如你所愿的，召唤了克雷塞尔。");
}