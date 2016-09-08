package edu.orangecoastcollege.vnguyen629cs273.tipcalculator;

/**
 * Calculates the tab of a restaurant bill after summing together the total amount
 * and the completed tip.
 * @author Vincent Nguyen
 */
public class RestaurantBill {
    private double mAmount;
    private double mTipPercent;
    private double mTipAmount;
    private double mTotalAmount;

    public RestaurantBill()
    {
        mAmount = 0.00;
        mTipPercent = 0.00;
        mTipAmount = 0.00;
        mTotalAmount = 0.00;
    }

    public RestaurantBill(double mAmount, double mTipPercent)
    {
        this.mAmount = mAmount;
        this.mTipPercent = mTipPercent;
        recalculateAmount();
    }

    public double getAmount()
    {
        return mAmount;
    }

    public double getTipPercent()
    {
        return mTipPercent;
    }

    public double getTipAmount()
    {
        return mTipAmount;
    }

    public double getTotalAmount()
    {
        return mTotalAmount;
    }

    public void setAmount(double newAmount)
    {
        mAmount = newAmount;
        recalculateAmount();
    }

    public void setTipPercent(double newTipPercent)
    {
        mTipPercent = newTipPercent;
        recalculateAmount();
    }

    private void recalculateAmount()
    {
        mTipAmount = mAmount * mTipPercent;
        mTotalAmount += mTipAmount;
    }
}
