package merchant;

public class goods_model
{
    private int acc_id;
    private int good_id;
    private int good_num;
    private int good_price;
    private String characters_name;
    private long createData;
    
    public goods_model(final int acc_id, final int good_id, final int good_num, final int good_price, final String characters_name, final long createData) {
        this.acc_id = acc_id;
        this.good_id = good_id;
        this.good_num = good_num;
        this.good_price = good_price;
        this.characters_name = characters_name;
        this.createData = createData;
    }
    
    public int getGood_id() {
        return this.good_id;
    }
    
    public void setGood_id(final int good_id) {
        this.good_id = good_id;
    }
    
    public int getGood_num() {
        return this.good_num;
    }
    
    public void setGood_num(final int good_num) {
        this.good_num = good_num;
    }
    
    public boolean gainGood_num(final int good_num) {
        final int update_num = this.good_num + good_num;
        if (update_num < 0) {
            return false;
        }
        this.good_num = update_num;
        return true;
    }
    
    public int getGood_price() {
        return this.good_price;
    }
    
    public void setGood_price(final int good_price) {
        this.good_price = good_price;
    }
    
    public int getAcc_id() {
        return this.acc_id;
    }
    
    public void setAcc_id(final int acc_id) {
        this.acc_id = acc_id;
    }
    
    public String getCharacters_name() {
        return this.characters_name;
    }
    
    public void setCharacters_name(final String characters_name) {
        this.characters_name = characters_name;
    }
    
    public long getCreateData() {
        return this.createData;
    }
    
    public void setCreateData(final long createData) {
        this.createData = createData;
    }
}
