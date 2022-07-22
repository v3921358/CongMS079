/* 
 * @Author Lerk
 * 
 * Bulletin Board, Victoria Road: Excavation Site<Camp> (101030104) AND Sharenian: Excavation Site (990000000)
 * 
 * Start of Guild Quest
 */


function action(mode, type, selection) {
    cm.sendOk("<通知> \r\n做为家族的一员,你是否拥有充足的勇气和信任?然后领取家族任务,挑战自己!\r\n\r\n#b参与要求:#k\r\n1。家族必须包含至少6人! \r\n2。由家族的管理者带队! \r\n3。提前结束方式：参与的数量低于6或如果领导人决定提前结束。");
    cm.safeDispose();
}