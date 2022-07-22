package merchant;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import database.DBConPool;
import tools.FileoutputUtil;
import client.MapleCharacter;
import java.util.LinkedList;
import java.util.List;

public class merchant_main
{
    private static merchant_main instance;
    private List<goods_model> goods_list;
    private List<eqs_model> eq_list;
    private boolean close;
    
    public merchant_main() {
        this.goods_list = new LinkedList<goods_model>();
        this.eq_list = new LinkedList<eqs_model>();
        this.close = false;
    }
    
    public static merchant_main getInstance() {
        return merchant_main.instance;
    }
    
    public void add_good(final MapleCharacter chr, final int good_id, final int good_num, final int good_price, final long createData) {
        this.goods_list.add(new goods_model(chr.getAccountID(), good_id, good_num, good_price, chr.getName(), createData));
        FileoutputUtil.logToFile("日志/商人交易系统/上架物品_goods.txt", " 上架物品ID： " + good_id + " 上架数量： " + good_num + " 价格： " + good_price + "");
    }
    
    public void add_eq(final MapleCharacter chr, final String owner, final int upgradeslots, final int level, final int str, final int dex, final int int_, final int luk, final int hp, final int mp, final int watk, final int matk, final int wdef, final int mdef, final int acc, final int avoid, final int hands, final int speed, final int jump, final byte viciousHammer, final int itemEXP, final int durability, final byte enhance, final short potential1, final short potential2, final short potential3, final short hpR, final short mpR, final int good_id, final int good_price) {
        final eqs_model add_eq = new eqs_model(owner, upgradeslots, level, str, dex, int_, luk, hp, mp, watk, matk, wdef, mdef, acc, avoid, hands, speed, jump, viciousHammer, itemEXP, durability, enhance, potential1, potential2, potential3, hpR, mpR, chr.getAccountID(), good_id, good_price, chr.getName(), System.currentTimeMillis(), 1);
        this.eq_list.add(add_eq);
        FileoutputUtil.logToFile("日志/商人交易系统/上架物品_eqs.txt", add_eq.toString());
    }
    
    public List<goods_model> getGoods_list() {
        return this.goods_list;
    }
    
    public List<eqs_model> getEqs_list() {
        return this.eq_list;
    }
    
    public void save_data() {
        if (this.isClose()) {
            return;
        }
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            PreparedStatement ps = con.prepareStatement("Truncate Table merchant");
            ps.executeUpdate();
            for (int i = 0; i < this.goods_list.size(); ++i) {
                if (((goods_model)this.goods_list.get(i)).getGood_num() != 0) {
                    ps = con.prepareStatement("insert into merchant (acc_id, good_id, good_num, good_price, characters_name, createdata) VALUES (?, ?, ?, ?, ?, ?)");
                    ps.setInt(1, ((goods_model)this.goods_list.get(i)).getAcc_id());
                    ps.setInt(2, ((goods_model)this.goods_list.get(i)).getGood_id());
                    ps.setInt(3, ((goods_model)this.goods_list.get(i)).getGood_num());
                    ps.setInt(4, ((goods_model)this.goods_list.get(i)).getGood_price());
                    ps.setString(5, ((goods_model)this.goods_list.get(i)).getCharacters_name());
                    ps.setLong(6, ((goods_model)this.goods_list.get(i)).getCreateData());
                    ps.executeUpdate();
                }
            }
            ps.close();
            this.goods_list.clear();
            PreparedStatement ps2 = con.prepareStatement("Truncate Table merchantEquip");
            ps2.executeUpdate();
            for (int j = 0; j < this.eq_list.size(); ++j) {
                if (((eqs_model)this.eq_list.get(j)).getGood_num() != 0) {
                    ps2 = con.prepareStatement("insert into merchantEquip (owner, upgradeslots, level, str, dex, int_, luk, hp, mp, watk, matk, wdef, mdef, acc, avoid, hands, speed, jump, ViciousHammer, itemEXP, durability, enhance, potential1, potential2, potential3, hpR, mpR, acc_id, good_id, good_price, characters_name, createData, good_num) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                    ps2.setString(1, ((eqs_model)this.eq_list.get(j)).getOwner());
                    ps2.setInt(2, ((eqs_model)this.eq_list.get(j)).getUpgradeslots());
                    ps2.setInt(3, ((eqs_model)this.eq_list.get(j)).getLevel());
                    ps2.setInt(4, ((eqs_model)this.eq_list.get(j)).getStr());
                    ps2.setInt(5, ((eqs_model)this.eq_list.get(j)).getDex());
                    ps2.setInt(6, ((eqs_model)this.eq_list.get(j)).getInt_());
                    ps2.setInt(7, ((eqs_model)this.eq_list.get(j)).getLuk());
                    ps2.setInt(8, ((eqs_model)this.eq_list.get(j)).getHp());
                    ps2.setInt(9, ((eqs_model)this.eq_list.get(j)).getMp());
                    ps2.setInt(10, ((eqs_model)this.eq_list.get(j)).getWatk());
                    ps2.setInt(11, ((eqs_model)this.eq_list.get(j)).getMatk());
                    ps2.setInt(12, ((eqs_model)this.eq_list.get(j)).getWdef());
                    ps2.setInt(13, ((eqs_model)this.eq_list.get(j)).getMdef());
                    ps2.setInt(14, ((eqs_model)this.eq_list.get(j)).getAcc());
                    ps2.setInt(15, ((eqs_model)this.eq_list.get(j)).getAvoid());
                    ps2.setInt(16, ((eqs_model)this.eq_list.get(j)).getHands());
                    ps2.setInt(17, ((eqs_model)this.eq_list.get(j)).getSpeed());
                    ps2.setInt(18, ((eqs_model)this.eq_list.get(j)).getJump());
                    ps2.setByte(19, ((eqs_model)this.eq_list.get(j)).getViciousHammer());
                    ps2.setInt(20, ((eqs_model)this.eq_list.get(j)).getItemEXP());
                    ps2.setInt(21, ((eqs_model)this.eq_list.get(j)).getDurability());
                    ps2.setByte(22, ((eqs_model)this.eq_list.get(j)).getEnhance());
                    ps2.setShort(23, ((eqs_model)this.eq_list.get(j)).getPotential1());
                    ps2.setShort(24, ((eqs_model)this.eq_list.get(j)).getPotential2());
                    ps2.setShort(25, ((eqs_model)this.eq_list.get(j)).getPotential3());
                    ps2.setShort(26, ((eqs_model)this.eq_list.get(j)).getHpR());
                    ps2.setShort(27, ((eqs_model)this.eq_list.get(j)).getMpR());
                    ps2.setInt(28, ((eqs_model)this.eq_list.get(j)).getAcc_id());
                    ps2.setInt(29, ((eqs_model)this.eq_list.get(j)).getGood_id());
                    ps2.setInt(30, ((eqs_model)this.eq_list.get(j)).getGood_price());
                    ps2.setString(31, ((eqs_model)this.eq_list.get(j)).getCharacters_name());
                    ps2.setLong(32, ((eqs_model)this.eq_list.get(j)).getCreateData());
                    ps2.setInt(33, ((eqs_model)this.eq_list.get(j)).getGood_num());
                    ps2.executeUpdate();
                }
            }
            ps2.close();
            this.eq_list.clear();
            this.setClose(true);
        }
        catch (SQLException ex) {
            System.err.println("数据库操作错误:" + (Object)ex);
        }
    }
    
    public void load_data() {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final PreparedStatement ps = con.prepareStatement("select * from merchant");
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                this.goods_list.add(new goods_model(rs.getInt("acc_id"), rs.getInt("good_id"), rs.getInt("good_num"), rs.getInt("good_price"), rs.getString("characters_name"), rs.getLong("createdata")));
            }
            rs.close();
            ps.close();
            final PreparedStatement ps2 = con.prepareStatement("select * from merchantEquip");
            final ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()) {
                this.eq_list.add(new eqs_model(rs2.getString("owner"), rs2.getInt("upgradeslots"), rs2.getInt("level"), rs2.getInt("str"), rs2.getInt("dex"), rs2.getInt("int_"), rs2.getInt("luk"), rs2.getInt("hp"), rs2.getInt("mp"), rs2.getInt("watk"), rs2.getInt("matk"), rs2.getInt("wdef"), rs2.getInt("mdef"), rs2.getInt("acc"), rs2.getInt("avoid"), rs2.getInt("hands"), rs2.getInt("speed"), rs2.getInt("jump"), rs2.getByte("ViciousHammer"), rs2.getInt("itemEXP"), rs2.getInt("durability"), rs2.getByte("enhance"), rs2.getShort("potential1"), rs2.getShort("potential2"), rs2.getShort("potential3"), rs2.getShort("hpR"), rs2.getShort("mpR"), rs2.getInt("acc_id"), rs2.getInt("good_id"), rs2.getInt("good_price"), rs2.getString("characters_name"), rs2.getLong("createData"), rs2.getInt("good_num")));
            }
            rs2.close();
            ps2.close();
        }
        catch (SQLException ex) {
            System.err.println("数据库操作错误:" + (Object)ex);
        }
    }
    
    public boolean isClose() {
        return this.close;
    }
    
    public void setClose(final boolean close) {
        this.close = close;
    }
    
    public List<Integer> getOnlygoods_list() {
        final List<goods_model> w_list = this.goods_list;
        final ArrayList only_list = new ArrayList();
        for (int i = 0; i < w_list.size(); ++i) {
            if (((goods_model)w_list.get(i)).getGood_num() <= 0) {
                w_list.remove(i);
            }
        }
        for (int i = 0; i < w_list.size() - 1; ++i) {
            for (int j = i + 1; j < w_list.size(); ++j) {
                if (((goods_model)w_list.get(i)).getGood_id() > ((goods_model)w_list.get(j)).getGood_id()) {
                    final goods_model temp = (goods_model)w_list.get(i);
                    w_list.set(i, w_list.get(j));
                    w_list.set(j, temp);
                }
            }
        }
        int t_tmp = 0;
        for (int k = 0; k < w_list.size(); ++k) {
            if (((goods_model)w_list.get(k)).getGood_id() != t_tmp) {
                only_list.add(Integer.valueOf(((goods_model)w_list.get(k)).getGood_id()));
                t_tmp = ((goods_model)w_list.get(k)).getGood_id();
            }
        }
        return (List<Integer>)only_list;
    }
    
    public List<Integer> getOnlyeq_list() {
        final List<eqs_model> w_list = this.eq_list;
        final ArrayList only_list = new ArrayList();
        for (int i = 0; i < w_list.size(); ++i) {
            if (((eqs_model)w_list.get(i)).getGood_num() <= 0) {
                w_list.remove(i);
            }
        }
        for (int i = 0; i < w_list.size() - 1; ++i) {
            for (int j = i + 1; j < w_list.size(); ++j) {
                if (((eqs_model)w_list.get(i)).getGood_id() > ((eqs_model)w_list.get(j)).getGood_id()) {
                    final eqs_model temp = (eqs_model)w_list.get(i);
                    w_list.set(i, w_list.get(j));
                    w_list.set(j, temp);
                }
            }
        }
        int t_tmp = 0;
        for (int k = 0; k < w_list.size(); ++k) {
            if (((eqs_model)w_list.get(k)).getGood_id() != t_tmp) {
                only_list.add(Integer.valueOf(((eqs_model)w_list.get(k)).getGood_id()));
                t_tmp = ((eqs_model)w_list.get(k)).getGood_id();
            }
        }
        return (List<Integer>)only_list;
    }
    
    static {
        merchant_main.instance = new merchant_main();
    }
}
