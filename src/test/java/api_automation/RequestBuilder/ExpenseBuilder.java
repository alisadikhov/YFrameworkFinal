package api_automation.RequestBuilder;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class ExpenseBuilder {
    @JsonPropertyOrder
    private String Name;
    private String GiftRecipient;
    private String ExpenseDateTime;
    private String amount;

    public String getName() {

        return Name;
    }

    public void setName(String name) {

        Name = name;
    }

    public String getGiftRecipient() {
        return GiftRecipient;
    }

    public void setGiftRecipient(String giftRecipient) {

        GiftRecipient = giftRecipient;
    }

    public String getExpenseDateTime() {

        return ExpenseDateTime;
    }

    public void setExpenseDateTime(String expenseDateTime) {

        ExpenseDateTime = expenseDateTime;
    }

    public String getAmount() {

        return amount;
    }

    public void setAmount(String amount) {

        this.amount = amount;
    }


}
