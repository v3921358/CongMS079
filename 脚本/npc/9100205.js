var Board;
var Score = 0;
var GameType = -1, BoardSize = -1, NumCols = -1, NumRows = -1,
NumGemTypes = -1, Difficulty = -1, NextComboMultiplier = 0,
GameTime = 180000 /*3 Minutes in Milliseconds*/, GameStart = -1;
var GameTypes = [
["正常", "普通遊戲"],
["時間攻擊", "3分鐘獲得盡可能多的積分"]
//, ["Puzzle", "Solve puzzles"] // May add Puzzle mode later.
];
var BoardSizes = [
//尺寸 Name, Number of Rows, Number of Cols
["小", 6, 5],
["中", 7, 6],
["大", 8, 7],
["超級大", 10, 7]
];
var Difficulties = [
//Difficulty Name, Number of Gem Types
["很簡單", 4],
["簡單", 5],
["中", 6],
["困難", 7]
];
var GameDropProbability = 0.01 / 100;
var GameDrops = [
//ItemID, Drop Probability (Should add Up to 1)
[5200000, 0.25],[5200001, 0.20],[5200002, 0.15],
[4031701, 0.05],[4001013, 0.10],[2340000, 0.01]
];
var Gems = [
//Ore, Gem
[4020000, 4021000], [4020001, 4021001], [4020002, 4021002], [4020003, 4021003],
[4020004, 4021004], [4020005, 4021005], [4020006, 4021006], [4020007, 4021007],
[4020008, 4021008]
];
var Neighbors4 = [[0,1],[0,-1],[-1,0],[1,0]];
var Neighbors8 = [[-1, -1],[-1, 0],[-1, 1],[0, -1],[0, 1],[1, -1],[1, 0], [1, 1]];
var status;

function Header() {
    return "#b============#k #rG #gE #bM #dz #k#b============#k\r\n\r\n";
}
function MenuScreen() {
    return Header() +
    "請選擇一個選項:\r\n\r\n" +
    "#L0#開始遊戲#l\r\n" +
    "#L1#遊戲說明#l";
}
function SendGameInstructions() {
    cm.sendNext(Header() +
        "#e遊戲說明:#n\r\n" +
        "1. 通過點擊3個或更多的礦石/寶石塊來消滅礦石/寶石來獲得積分.\r\n" +
        "2. 每個礦石得到5點摧毀+每礦石5％以上的連擊數超過3.\r\n" +
        "3.有一個機會，你點擊的礦石將變成一個寶石。 這些是" +
        "值得30分的組合。 你也可以點擊這些來獲得20分.\r\n" +
        "4. 特殊項目可能會出現。 你可以使用這些只需點擊它們:\r\n" +
        "            #v5021013# - 摧毀周圍8個礦石/寶石（不影響特殊物品).\r\n" +
        "            #v4031051# - 把周圍的8個礦石變成寶石.\r\n" +
        "            #v4031311# - 添加2秒鐘到定時器. (只有時間攻擊模式)\r\n" +
        "            #v3990021##v3990023# - 紅色（水平摧毀所有的礦石/寶石）, 綠色（把所有的礦石水平地變成寶石）\r\n" +
        "            #v3991008##v3991034# - 紅色（垂直消滅所有的礦石/寶石），綠色（垂直把所有的礦石變成寶石）\r\n" +
        "            #v3990020# - 垂直和水平地銷毀所有的礦石/寶石\r\n" +
        "      被銷毀的礦石值得10分。 被毀壞的寶石每個價值25分。 特殊物品和掉落物品不會被銷毀.\r\n" +
        "5. 真實的遊戲內物品可能會出現。 點擊它們來獲取它們:\r\n" +
        "            #v5200000##v5200001##v5200002##v4031701##v4001013##v2340000#\r\n" +
        "\r\nGood Luck ~"
        );
}
function SendGameTypeSelectionMenu() {
    var SendStr = Header();
    for (var i = 0;i < GameTypes.length;i++) {
        SendStr += "#L"+i+"#"+GameTypes[i][0]+" - "+GameTypes[i][1]+"#l\r\n";
    }
    cm.sendSimple(SendStr);
}
function SendBoardSizeSelectionMenu() {
    var SendStr = Header();
    for (var i = 0;i < BoardSizes.length;i++) {
        SendStr += "#L"+i+"#"+BoardSizes[i][0]+" - "+BoardSizes[i][1]+"x"+BoardSizes[i][2]+"#l\r\n";
    }
    cm.sendSimple(SendStr);
}
function SendDifficultySelectionMenu() {
    var SendStr = Header();
    for (var i = 0;i < Difficulties.length;i++) {
        SendStr += "#L"+i+"#"+Difficulties[i][0]+" - "+Difficulties[i][1]+" Gem Types#l\r\n";
    }
    cm.sendSimple(SendStr);
}
function InitializeBoard() {
    NumRows = BoardSizes[BoardSize][1];
    NumCols = BoardSizes[BoardSize][2];
    Board = new Array(NumRows);
    for (var i = 0;i < Board.length;i++) {
        Board[i] = new Array(NumCols);
    }
}
function InitializeGems() {
    NumGemTypes = Difficulties[Difficulty][1];
    Shuffle(Gems);
    for (var r = 0;r < Board.length;r++) {
        for (var c = 0;c < Board[r].length;c++) {
            Board[r][c] = GetRandomOre();
        }
    }
}
function GetRandomOre() {
    return Gems[Math.floor(Math.random() * NumGemTypes)][0];
}
function GetScoreString() {
    return "#b#e遊戲分數:#n#k " + AddCommas(Score.toFixed(2)) + "\r\n";
}
function GetGameSettingsDescriptionString() {
    return "#d#e遊戲設置:#n#k " + GameTypes[GameType][0]
    + " - 尺寸: " + BoardSizes[BoardSize][0] + "("+ BoardSizes[BoardSize][1]+"x" + BoardSizes[BoardSize][2]
    + ") - 困難: " + Difficulties[Difficulty][0] + "\r\n";
}
function AddCommas(nStr) {
    nStr += '';
    x = nStr.split('.');
    x1 = x[0];
    x2 = x.length > 1 ? '.' + x[1] : '';
    var rgx = /(\d+)(\d{3})/;
    while (rgx.test(x1)) {
        x1 = x1.replace(rgx, '$1' + ',' + '$2');
    }
    return x1 + x2;
}
function GetGameEndBoardString() {
    var SendStr = "";
    for (var r = 0;r < Board.length;r++) {
        for (var c = 0;c < Board[r].length;c++) {
            SendStr += "#v"+Board[r][c]+"#";
        }
        SendStr += "\r\n";
    }
    SendStr += "\r\n";
    return SendStr;
}
function GetBoardSelectionString() {
    var SendStr = "";
    var Count = 0;
    for (var r = 0;r < Board.length;r++) {
        for (var c = 0;c < Board[r].length;c++) {
            SendStr += "#L"+(Count++)+"##v"+Board[r][c]+"##l";
        }
        SendStr += "\r\n";
    }
    SendStr += "\r\n";
    return SendStr;
}
function ContainsGem(Combo, GemGroup) {
    for (var i = 0;i < Combo.length;i++) {
        if (Board[Combo[i][0]][Combo[i][1]] == GemGroup[1]) {
            return true;
        }
    }
    return false;
}
function DoMove(Move) {
    var ItemID = Board[Move[0]][Move[1]];
    var GemGroup = GetGemGroup(ItemID);
    var Combo = new Array();
    var IsSpecialItem = false;
    Combo.push([Move[0],Move[1]]);
    if (GemGroup != null) {
        //Normal Gem
        GetNeighborGems(Move[0],Move[1],GemGroup,Combo);
        if (Combo.length < 3) {
            if (!ContainsGem(Combo, GemGroup)) {
                return false;
            }
        }
        AddScoreForCombination(Combo, GemGroup);
    } else if (IsDropItem(ItemID)) {
        cm.gainItem(ItemID, 1);
    } else {
        //Special Item
        UseSpecialItem(ItemID, Move[0], Move[1], Combo);
        IsSpecialItem = true;
    }
    RemoveAndGenerateNewGems(Combo, Move[0], Move[1], GemGroup, IsSpecialItem);
    return true;
}

function RemoveGemAndShiftDown(r, c) {
    for (var i = r;i > 0;i--) {
        Board[i][c] = Board[i-1][c];
    }
    return [0, c];
}
function RemoveAndGenerateNewGems(Combo, r, c, GemGroup, IsSpecialItem) {
    var DropItemProb = (IsSpecialItem ? 0 : GameDropProbability + ((Combo.length > 5) ? (GameDropProbability / 10) * (Combo.length - 5) : 0));
    var SpecialItemProb = (IsSpecialItem ? 0 : 0.005 + (Combo.length > 3 ? 0.01 * (Combo.length - 3) : 0.0) + DropItemProb);
    for (var i = 0;i < Combo.length;i++) {
        var TurnToGemProb = 0.20 + ((Combo.length > 3) ? 0.2 * (Combo.length - 3) : 0.0);
        if (Combo[i][0] == r && Combo[i][1] == c && GemGroup != null
            && GemGroup[0] == Board[r][c] && Math.random() < TurnToGemProb) {
            Board[r][c] = GemGroup[1];
        } else {
            var NewGemLocation = RemoveGemAndShiftDown(Combo[i][0],Combo[i][1]);
            for (var j = i + 1; j < Combo.length;j++) {
                if (Combo[j][1] == Combo[i][1] && Combo[j][0] < Combo[i][0]) {
                    Combo[j][0]++;
                }
            }
            if (c == Combo[i][1] && r < Combo[i][0]) {
                r++;
            }
            var Ret = RandomOreGemDrop(NewGemLocation[0], NewGemLocation[1], DropItemProb, SpecialItemProb);
            //Only allow 1 item drop and 1 Special Item drop per combo.
            if (Ret == 1) {
                SpecialItemProb = 0;
            } else if (Ret == 2) {
                DropItemProb = 0; 
            }
        }
    }
}
function UseSpecialItem(ItemID, r, c, ToBeRemoved) {
    Score += 10;
    var Targets = new Array();
    switch (ItemID) {
        case 5021013:
        case 4031051:
            for (var i = 0;i < Neighbors8.length;i++) {
                var NewR = r + Neighbors8[i][0];
                var NewC = c + Neighbors8[i][1];
                Targets.push([NewR, NewC]);
            }
            break;
        case 3990021:
        case 3990023:
            for (i = 0;i < Board[r].length;i++) {
                if (i != c) {
                    Targets.push([r, i]);
                }
            }
            break;
        case 3991008:
        case 3991034:
            for (i = 0;i < Board.length;i++) {
                if (i != r) {
                    Targets.push([i, c]);
                }
            }
            break;
        case 3990020:
            for (i = 0;i < Board[r].length;i++) {
                if (i != c) {
                    Targets.push([r, i]);
                }
            }
            for (i = 0;i < Board.length;i++) {
                if (i != r) {
                    Targets.push([i, c]);
                }
            }
            break;
    }
    if (ItemID == 4031311) {
        GameTime += 2000;
    }
    switch (ItemID) {
        case 4031051:
        case 3990023:
        case 3991034:
            for (i = 0;i < Targets.length;i++) {
                if (ValidPosition(Targets[i][0], Targets[i][1])) {
                    var GemGroup = GetGemGroup(Board[Targets[i][0]][Targets[i][1]]);
                    if (GemGroup != null) {
                        if (GemGroup[0] == Board[Targets[i][0]][Targets[i][1]]) {
                            Board[Targets[i][0]][Targets[i][1]] = GemGroup[1];
                        }
                    }
                }
            }
            break;
        case 5021013:
        case 3990021:
        case 3991008:
        case 3990020:
            for (i = 0;i < Targets.length;i++) {
                if (ValidPosition(Targets[i][0], Targets[i][1])) {
                    GemGroup = GetGemGroup(Board[Targets[i][0]][Targets[i][1]]);
                    if (GemGroup != null) {
                        if (GemGroup[0] == Board[Targets[i][0]][Targets[i][1]]) {
                            Score += 10;
                        } else if (GemGroup[1] == Board[Targets[i][0]][Targets[i][1]]) {
                            Score += 25;
                        }
                        ToBeRemoved.push([Targets[i][0], Targets[i][1]]);
                    }
                }
            }
            break;
    }
}
function IsDropItem(ItemID) {
    var Len = GameDrops.length;
    while (Len--) {
        if (GameDrops[Len][0] == ItemID) {
            return true;
        }
    }
    return false;
}

function IsOreOrGem(ItemID) {
    for (var i = 0;i < Gems.length;i++) {
        for (var j = 0;j < Gems[i].length;i++) {
            if (Gems[i][j] == ItemID)
                return true;
        }
    }
    return false;
}

function GetDropItem() {
    var R = Math.random();
    var tot = 0;
    for (var i = 0;i < GameDrops.length;i++) {
        tot += GameDrops[i][1];
        if (R < tot) {
            return GameDrops[i][0];
        }
    }
    return 5200000; //Just in case.
}
function RandomOreGemDrop(r, c, DropItemProb, SpecialItemProb) {
    var R = Math.random();
    if (R < DropItemProb) {
        //Generate a Drop
        Board[r][c] = GetDropItem();
        return 2;
    } else if (R < SpecialItemProb) {
        //Generate Special Item
        Board[r][c] = GetRandomSpecialItem();
        return 1;
    } else {
        //Normal Ore
        Board[r][c] = GetRandomOre();
        return 0;
    }
}

/*
#v5021013# - 摧毀周圍8個礦石/寶石（不影響特殊物品). (25 points / destroyed item)\r\n" +
#v4031051# - 把周圍的8個礦石變成寶石.\r\n" +
#v4031311# - 添加2秒鐘到定時器. (只有時間攻擊模式)\r\n" +
#v3990021##v3990023# - Red(Destroy all Ores/Gems Horizontally - 25 points / destroyed item), 綠色（把所有的礦石水平地變成寶石）\r\n" +
#v3991008##v3991034# - Red(Destroy all Ores/Gems Vertically - 25 points / destroyed item), Green(Turn all Ores into Gems Vertically)\r\n" +
#v3990020# - 垂直和水平地銷毀所有的礦石/寶石 (25 points / destroyed item)\r\n" +
 */
function GetRandomSpecialItem() {
    var Prob = Math.random();
    if (GameType == 1 && Math.random() < 0.25) {
        return 4031311;
    }
    if (Prob < 0.10) {
        return 3990020;
    } else if (Prob < 0.25) {
        return 3991008;
    } else if (Prob < 0.35) {
        return 3991034;
    } else if (Prob < 0.50) {
        return 3990021;
    } else if (Prob < 0.60) {
        return 3990023;
    } else if (Prob < 0.70) {
        return 4031051;
    } else {
        return 5021013;
    }
}
function AddScoreForCombination(Combo, GemGroup) {
    var Multiplier = 1.0 + (Combo.length > 3 ? 0 : 0.05 * (Combo.length - 3))
    + NextComboMultiplier;
    if (Combo.length == 3) {
        NextComboMultiplier = 0;
    } else if (Combo.length == 4) {
        NextComboMultiplier += 0.01;
    } else if (Combo.length == 5) {
        NextComboMultiplier += 0.015;
    } else if (Combo.length > 5) {
        NextComboMultiplier += 0.02;
    }
    var Points = 0;
    for (var i = 0;i < Combo.length;i++) {
        var ItemID = Board[Combo[i][0]][Combo[i][1]];
        if (GemGroup[0] == ItemID) {
            Points += 5;
        } else if (GemGroup[1] == ItemID) {
            if (Combo.length >= 3) {
                Points += 30;
            } else {
                Points += 20;
            }
        }
    }
    Score += (Points * Multiplier);
}
function NoMoreMoves() {
    for (var i = 0;i < Board.length;i++) {
        for (var j = 0;j < Board[i].length;j++) {
            var GemGroup = GetGemGroup(Board[i][j]);
            if (GemGroup == null) { //Special Item
                return false;
            } else {
                var GetCount = new Array();
                GetCount.push([i,j]);
                GetNeighborGems(i, j, GemGroup, GetCount);
                if (GetCount.length > 2) {
                    return false;
                }
            }
        }
    }
    return true;
}
function GetNeighborGems(r, c, GemGroup, ToBeRemoved) {
    var CheckNeighbors = new Array();
    for (var i = 0;i < Neighbors4.length;i++) {
        var NewR = r + Neighbors4[i][0];
        var NewC = c + Neighbors4[i][1]
        if (ValidPosition(NewR, NewC) && Contains(Board[NewR][NewC], GemGroup)) {
            if (PushIfNotPresent([NewR, NewC], ToBeRemoved)) {
                CheckNeighbors.push([NewR, NewC]);
            }
        }
    }
    for (i = 0;i < CheckNeighbors.length;i++) {
        GetNeighborGems(CheckNeighbors[i][0], CheckNeighbors[i][1], GemGroup, ToBeRemoved);
    }
}
function ValidPosition(r, c) {
    return r >= 0 && r < NumRows && c >= 0 && c < NumCols;
}
function Contains(ItemID, GemGroup) {
    var i = GemGroup.length;
    while (i--) {
        if (GemGroup[i] == ItemID) {
            return true;
        }
    }
    return false;
}
function PushIfNotPresent(Move, Combo) {
    var i = Combo.length;
    while (i--) {
        var Comp = Combo[i];
        if (Comp[0] == Move[0] && Comp[1] == Move[1]) {
            return false;
        }
    }
    Combo.push(Move);
    return true;
}
function GetPositionFromSelection(sel) {
    return [(sel / NumCols) | 0, (sel % NumCols) | 0];
}
function GetGemGroup(ItemID) {
    for (var i = 0; i < Gems.length;i++) {
        for (var j = 0;j < Gems[i].length;j++) {
            if (Gems[i][j] == ItemID) {
                return Gems[i];
            }
        }
    }
    return null;
}
function Shuffle(arr) {
    var i = arr.length;
    if (i == 0)
        return;
    while (--i) {
        var j = Math.floor( Math.random() * ( i + 1 ) );
        var tempi = arr[i];
        var tempj = arr[j];
        arr[i] = tempj;
        arr[j] = tempi;
    }
}
function GetTimeLeftString() {
    var Ret = "Time Left: ";
    var Now = (new Date()).getTime();
    var Diff = GameTime + GameStart - Now;
    var MilliSec = (Diff % 1000) | 0;
    Diff /= 1000;
    var Min = (Diff / 60) | 0;
    var Sec = (Diff % 60) | 0;
    Ret += ((Min < 10) ? "0" + Min : Min) + ":" + ((Sec < 10) ? "0" + Sec : Sec)
    + "." + ((MilliSec < 100) ? "0" + ((MilliSec < 10) ? "0" + MilliSec : MilliSec) : MilliSec);
    Ret += " seconds\r\n";
    return Ret;
}
function GetGameTime(End) {
    var Diff = End - GameStart;
    var MilliSec = (Diff % 1000) | 0;
    Diff /= 1000;
    var Min = (Diff / 60) | 0;
    var Sec = (Diff % 60) | 0;
    return ((Min < 10) ? "0" + Min : Min) + ":" + ((Sec < 10) ? "0" + Sec : Sec)
    + "." + ((MilliSec < 100) ? "0" + ((MilliSec < 10) ? "0" + MilliSec : MilliSec) : MilliSec);

}
function start() {
    status = -1;
    action(1, 0, 0);
}
function CheckSelection(s, low, high) {
    return s < low && s > high;
}
function action(mode, type, selection) {
    if (mode == -1 || mode == 0)
        cm.dispose();
    else {

        if (mode == 1) {
            if (status != 6) {
                status++;
            }
        } else {
            status--;
        }

        if (status == 0) {
            cm.sendSimple(MenuScreen());
        } else if (status == 1) {
            if (selection == 0) {
                action(1, 0, 0); //Move to Next
            } else if (selection == 1) {
                SendGameInstructions();
            } else { //Packet Edits
                cm.dispose();
            }
        } else if (status == 2) {
            //Choose Game Type
            SendGameTypeSelectionMenu();
        } else if (status == 3) {
            if (CheckSelection(selection, 0, GameTypes.length - 1)) {
                cm.dispose();
                return;
            }
            GameType = selection;
            //Choose 尺寸
            SendBoardSizeSelectionMenu();
        } else if (status == 4) {
            if (CheckSelection(selection, 0, BoardSizes.length - 1)) {
                cm.dispose();
                return;
            }
            BoardSize = selection;
            //Choose Difficulty
            SendDifficultySelectionMenu();
        } else if (status == 5) {
            if (CheckSelection(selection, 0, Difficulties.length - 1)) {
                cm.dispose();
                return;
            }
            Difficulty = selection;
            InitializeBoard();
            InitializeGems();
            //No Header while in game, it's just annoying
            cm.sendSimple(GetScoreString() + GetBoardSelectionString() + GetGameSettingsDescriptionString());
        } else if (status == 6) {
            if (CheckSelection(selection, 0, NumCols * NumRows - 1)) {
                cm.dispose();
                return;
            }
            if (GameStart == -1) {
                GameStart = (new Date()).getTime();
            }
            if (GameType == 1 && GameStart + GameTime < (new Date()).getTime()) {
                //Time Over
                status++;
                action(1, 0, 0);
                return;
            }
            var Move = GetPositionFromSelection(selection);
            var ValidMove = DoMove(Move);
            if (NoMoreMoves()) {
                status++;
                action(1, 0, 0);
            } else {
                cm.sendSimple(
                    (ValidMove ? "" : "無效移動。 您必須點擊一組3個或更多的礦石/寶石.\r\n")
                    + GetScoreString()+ (GameType == 1 ? GetTimeLeftString() : "")
                    + GetBoardSelectionString() + GetGameSettingsDescriptionString());
            }
        } else if (status == 8) {
            var SendStr = Header();
            SendStr += "#e#r遊戲結束#k#n\r\n";
            SendStr += GetScoreString();
            SendStr += GetGameSettingsDescriptionString();
            SendStr += "遊戲時間: " + GetGameTime((new Date()).getTime()) + "\r\n";
            SendStr += GetGameEndBoardString();
            cm.sendOk(SendStr);
        } else {
            cm.dispose();
        }
    }
}  