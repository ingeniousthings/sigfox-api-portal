package fr.ingeniousthings.sigfox.api.elements;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Format :
 *
 *  {
 *      "general":{
 *          "contract":"Sample contract",
 *          "from":1388530800000,
 *          "to":1388703600000,
 *          "billingMode":1,
 *          "timezone":"Europe/Paris",
 *          "fixedPrice":"0.00",
 *          "communicationPrice":"0.00",
 *          "totalPrice":"0.00",
 *          "maxNumberOfDevices":9000,
 *          "activatedDevices":0,
 *          "subscriptionLevel":"First",
 *          "unitPricePerYear":"12.00",
 *          "fixedPartPercentage":"0.00%"
 *      },
 *      "details":[
 *          {
 *              "date":1388530800000,
 *              "cumulatedActivatedDevices":0,
 *              "activatedDeviceOnPeriod":0,
 *              "messagesSentOnPeriod":0,
 *              "dailyCost":0.0
 *          },
 *          {
 *              "date":1388617200000,
 *              "cumulatedActivatedDevices":0,
 *              "activatedDeviceOnPeriod":0,
 *              "messagesSentOnPeriod":0,
 *              "dailyCost":0.0
 *           }
 *          ],
 *      "communicationPrice":{
 *          "unitPrice":"12.00",
 *          "contractDuration":365,
 *          "terminated":false,
 *          "duration":1,
 *          "communicationPartPercentage":1.0000
 *      }
 *  }
 *
 */

public class SigfoxApiRatingDetailed {


    public class SigfoxApiRatingDetailGeneral {

        @ApiModelProperty(
                notes = "The BSS order’s name",
                required = true
        )
        public String contract;

        @ApiModelProperty(
                notes = "The quarter start date",
                required = true
        )
        public long from;

        @ApiModelProperty(
                notes = "The date parameter (???)",
                required = true
        )
        public long to;

        @ApiModelProperty(
                notes = "Billing Mode (1 : > 50Ku, 2 < 50ku)",
                required = true
        )
        public int billingMode;

        @ApiModelProperty(
                notes = "Timezone",
                required = true
        )
        public String timezone;

        @ApiModelProperty(
                notes = "Fixed price format 0.00..",
                required = true
        )
        public String fixedPrice;

        @ApiModelProperty(
                notes = "Communication price format 0.00",
                required = true
        )
        public String communicationPrice;

        @ApiModelProperty(
                notes = "Total price format 0.00",
                required = true
        )
        public String totalPrice;

        @ApiModelProperty(
                notes = "Maximum number of devices",
                required = true
        )
        public int maxNumberOfDevices;

        @ApiModelProperty(
                notes = "Activated devices",
                required = true
        )
        public int activatedDevices;

        @ApiModelProperty(
                notes = "Subscription level",
                required = true
        )
        public String subscriptionLevel;

        @ApiModelProperty(
                notes = "Unit price per year",
                required = true
        )
        public String unitPricePerYear;

        // La on se le dit : le gars qui a créé l'API voue une haine farouche au developpement informatique...
        @ApiModelProperty(
                notes = "Fixed part percentage format '00.00%'",
                required = true
        )
        public String fixedPartPercentage;

    }

    public class SigfoxApiRatingDetailDetails {

        @ApiModelProperty(
                notes = "Date",
                required = true
        )
        public long date;

        @ApiModelProperty(
                notes = "Cumulated activated devices",
                required = true
        )
        public int cumulatedActivatedDevices;

        @ApiModelProperty(
                notes = "Number of devices activated on the period",
                required = true
        )
        public int activatedDeviceOnPeriod;

        @ApiModelProperty(
                notes = "Number of messages sent on the period",
                required = true
        )
        public int messagesSentOnPeriod;

        @ApiModelProperty(
                notes = "Daily cost. The daily cost is rounded, so there can be small differencies" +
                        " between the rating communication price and the sum of daily prices",
                required = true
        )
        public double dailyCost;


    }

    public class SigfoxApiRatingDetailComPrice {

        @ApiModelProperty(
                notes = "Unit price per year",
                required = true
        )
        public String unitPrice;

        @ApiModelProperty(
                notes = "BSS Order duration in days",
                required = true
        )
        public int contractDuration;

        @ApiModelProperty(
                notes = "Indicates if the BSS order is terminated",
                required = true
        )
        public boolean terminated;

        @ApiModelProperty(
                notes = "BSS Order duration in years",
                required = true
        )
        public int duration;

        @ApiModelProperty(
                notes = "Communication part percentage",
                required = true
        )
        public double communicationPartPercentage;


    }

    @ApiModelProperty(
            notes = "General information section",
            required = true
    )
    protected SigfoxApiRatingDetailGeneral general;

    @ApiModelProperty(
            notes = "Rating detail per day",
            required = true
    )
    protected List<SigfoxApiRatingDetailDetails> details;

    @ApiModelProperty(
            notes = "Compute the communication price",
            required = true
    )
    protected SigfoxApiRatingDetailComPrice communicationPrice;




    // ==================================================================
    // Getters & Setters
    // ==================================================================


    public SigfoxApiRatingDetailGeneral getGeneral() {
        return general;
    }

    public void setGeneral(SigfoxApiRatingDetailGeneral general) {
        this.general = general;
    }

    public List<SigfoxApiRatingDetailDetails> getDetails() {
        return details;
    }

    public void setDetails(List<SigfoxApiRatingDetailDetails> details) {
        this.details = details;
    }

    public SigfoxApiRatingDetailComPrice getCommunicationPrice() {
        return communicationPrice;
    }

    public void setCommunicationPrice(SigfoxApiRatingDetailComPrice communicationPrice) {
        this.communicationPrice = communicationPrice;
    }
}
