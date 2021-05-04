package store.config;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "userID"
})
@XmlRootElement(name = "getOrdersRequest")
public class GetOrdersRequest {
    @XmlElement(required = true)
    private int userID;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
