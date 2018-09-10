/**
 * -------------------------------------------------------------------------------
 * This file is part of IngeniousThings Sigfox-Api.
 *
 * IngeniousThings Sigfox-Api is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * IngeniousThings Sigfox-Api is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 * -------------------------------------------------------------------------------
 * Author : Paul Pinault aka disk91
 * See https://www.disk91.com
 * ----
 * More information about IngeniousThings : https://www.ingeniousthings.fr
 * ----
 * Commercial license of this software can be obtained contacting ingeniousthings
 * -------------------------------------------------------------------------------
 */
package fr.ingeniousthings.sigfox.apiv2.models;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@Api(tags = "productCertificateWithPacResponse", description = "Defines a product certificate type entity")
public class SigfoxApiv2CertificateProductWithPacResponse {

    @ApiModelProperty(
            notes = "Id of the certificate in hexadecimal form",
            example = "57309674171c857460043087",
            required = false
    )
    protected String id;

    @ApiModelProperty(
            notes = "External Id of the certificate",
            example = "??",
            required = false
    )
    protected String externalId;

    @ApiModelProperty(
            notes = "Name of the certificate",
            example = "M_0004_AC59_02",
            required = false
    )
    protected String name;

    @ApiModelProperty(
            notes = "Status of the certificate<br/>" +
                    "<ul>" +
                    "<li>0=ONGOING</li>" +
                    "<li>1=FINALIZED</li>" +
                    "</ul>",
            example = "1",
            required = false
    )
    protected int status;

    @ApiModelProperty(
            notes = "Certificate’s key built from code and index",
            example = "????",
            required = false
    )
    protected String certificateKey;

    @ApiModelProperty(
            notes = "certificateCode",
            example = "????",
            required = false
    )
    protected int certificateCode;

    @ApiModelProperty(
            notes = "Certificate’s index",
            example = "0",
            required = false
    )
    protected int certificateIndex;

    @ApiModelProperty(
            notes = "Id of the manufacturer in hexadecimal form",
            example = "????",
            required = false
    )
    protected String manufacturerId;

    @ApiModelProperty(
            notes = "Name of the manufacturer",
            example = "????",
            required = false
    )
    protected String manufacturerName;

    @ApiModelProperty(
            notes = "Manufacturer’s code",
            example = "0",
            required = false
    )
    protected int manufacturerCode;

    @ApiModelProperty(
            notes = "Version of the certificate",
            example = "????",
            required = false
    )
    protected String version;

    @ApiModelProperty(
            notes = "Date of qualification (in milliseconds since the Unix Epoch)",
            example = "1536413740000",
            required = false
    )
    protected long qualificationTime;

    @ApiModelProperty(
            notes = "Report number",
            example = "???",
            required = false
    )
    protected String reportNumber;

    @ApiModelProperty(
            notes = "Description of the certificate",
            example = "???",
            required = false
    )
    protected String description;

    @ApiModelProperty(
            notes = "List of modes of the certificate<br>" +
                    "<ul>" +
                    "<li>1=DOWNLINK</li>" +
                    "<li>2=MONARCH</li>" +
                    "</ul>",
            example = "1,2",
            required = false
    )
    protected int modes[];

    @ApiModelProperty(
            notes = "List of allowed RC<br/>" +
                    "<ul>" +
                    "<li>0=RC1</li>" +
                    "<li>1=RC2</li>" +
                    "<li>2=RC3</li>" +
                    "<li>3=RC101</li>" +
                    "<li>4=RC4</li>" +
                    "<li>5=RC5</li>" +
                    "<li>6=RC6</li>" +
                    "</ul>",
            example = "1,2,3",
            required = false
    )
    protected int standards[];

    @Api(tags = "rcConfiguration", description = "Details per Rc for output")
    public class RcConfiguration {

        @ApiModelProperty(
                notes = "Id of the RC<br/>" +
                        "<ul>" +
                        "<li>0=RC1</li>" +
                        "<li>1=RC2</li>" +
                        "<li>2=RC3</li>" +
                        "<li>3=RC101</li>" +
                        "<li>4=RC4</li>" +
                        "<li>5=RC5</li>" +
                        "<li>6=RC6</li>" +
                        "</ul>",
                example = "0",
                required = false
        )
        protected int id;

        @ApiModelProperty(
                notes = "Uplink class<br/>" +
                        "<ul>" +
                        "<li>0=0U</li>" +
                        "<li>1=1U</li>" +
                        "<li>2=2U</li>" +
                        "<li>3=3U</li>" +
                        "</ul>",
                example = "0",
                required = false
        )
        protected int uplinkClass;

        @ApiModelProperty(
                notes = "Maximum radiated power EIRP",
                example = "14.0",
                required = false
        )
        protected double maxEirp;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUplinkClass() {
            return uplinkClass;
        }

        public void setUplinkClass(int uplinkClass) {
            this.uplinkClass = uplinkClass;
        }

        public double getMaxEirp() {
            return maxEirp;
        }

        public void setMaxEirp(double maxEirp) {
            this.maxEirp = maxEirp;
        }
    }
    @ApiModelProperty(
            notes = "List of allowed RC and their specific configurations",
            required = false
    )
    protected List<RcConfiguration> standardCfgs;

    @ApiModelProperty(
            notes = "Input sensitivity",
            example = "140",
            required = false
    )
    protected int inputSensitivity;

    @ApiModelProperty(
            notes = "True if the payload will be encrypted",
            example = "false",
            required = false
    )
    protected boolean encryptionPayload;

    @ApiModelProperty(
            notes = "DevKit Flag",
            example = "true",
            required = false
    )
    protected boolean devKit;



    // ============================================================
    // Generated Getters & Setters
    // ============================================================


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCertificateKey() {
        return certificateKey;
    }

    public void setCertificateKey(String certificateKey) {
        this.certificateKey = certificateKey;
    }

    public int getCertificateCode() {
        return certificateCode;
    }

    public void setCertificateCode(int certificateCode) {
        this.certificateCode = certificateCode;
    }

    public int getCertificateIndex() {
        return certificateIndex;
    }

    public void setCertificateIndex(int certificateIndex) {
        this.certificateIndex = certificateIndex;
    }

    public String getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(String manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public int getManufacturerCode() {
        return manufacturerCode;
    }

    public void setManufacturerCode(int manufacturerCode) {
        this.manufacturerCode = manufacturerCode;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public long getQualificationTime() {
        return qualificationTime;
    }

    public void setQualificationTime(long qualificationTime) {
        this.qualificationTime = qualificationTime;
    }

    public String getReportNumber() {
        return reportNumber;
    }

    public void setReportNumber(String reportNumber) {
        this.reportNumber = reportNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int[] getModes() {
        return modes;
    }

    public void setModes(int[] modes) {
        this.modes = modes;
    }

    public int[] getStandards() {
        return standards;
    }

    public void setStandards(int[] standards) {
        this.standards = standards;
    }

    public List<RcConfiguration> getStandardCfgs() {
        return standardCfgs;
    }

    public void setStandardCfgs(List<RcConfiguration> standardCfgs) {
        this.standardCfgs = standardCfgs;
    }

    public int getInputSensitivity() {
        return inputSensitivity;
    }

    public void setInputSensitivity(int inputSensitivity) {
        this.inputSensitivity = inputSensitivity;
    }

    public boolean isEncryptionPayload() {
        return encryptionPayload;
    }

    public void setEncryptionPayload(boolean encryptionPayload) {
        this.encryptionPayload = encryptionPayload;
    }

    public boolean isDevKit() {
        return devKit;
    }

    public void setDevKit(boolean devKit) {
        this.devKit = devKit;
    }
}
