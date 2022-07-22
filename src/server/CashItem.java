package server;

public class CashItem
{
    private int ItemId;
    private int Count;
    private int Price;
    private int SN;
    private int Period;
    private int Gender;
    private int Class;
    private boolean OnSale;
    
    public CashItem(final int sn, final int itemId, final int count, final int price, final int period, final int gender, final int Class, final boolean sale) {
        this.SN = sn;
        this.ItemId = itemId;
        this.Count = count;
        this.Price = price;
        this.Period = period;
        this.Gender = gender;
        this.Class = Class;
        this.OnSale = sale;
    }
    
    public void setId(final int ItemId) {
        this.ItemId = ItemId;
    }
    
    public int getId() {
        return this.ItemId;
    }
    
    public void setCount(final int Count) {
        this.Count = Count;
    }
    
    public int getCount() {
        return this.Count;
    }
    
    public void setPrice(final int Price) {
        this.Price = Price;
    }
    
    public int getPrice() {
        return this.Price;
    }
    
    public void setSN(final int SN) {
        this.SN = SN;
    }
    
    public int getSN() {
        return this.SN;
    }
    
    public void setPeriod(final int Period) {
        this.Period = Period;
    }
    
    public int getPeriod() {
        return this.Period;
    }
    
    public void setGender(final int Gender) {
        this.Gender = Gender;
    }
    
    public int getGender() {
        return this.Gender;
    }
    
    public void setOnSale(final boolean OnSale) {
        this.OnSale = OnSale;
    }
    
    public void setClass(final int Class) {
        this.Class = Class;
    }
    
    public int getclass() {
        return this.Class;
    }
    
    public boolean onSale() {
        return this.OnSale;
    }
    
    public boolean genderEquals(final int g) {
        return g == this.Gender || this.Gender == 2;
    }
}
