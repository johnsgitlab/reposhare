package com.jpw.raptor.scrape.yahoo.fields;

/**
 * Created by John on 10/10/2017.
 */
public class SectorWeighting {

    private Realestate realestate;
    private ConsumerCyclical consumerCyclical;
    private BasicMaterials basicMaterials;
    private ConsumerDefensive consumerDefensive;
    private Technology technology;
    private CommunicationServices communicationServices;
    private FinancialServices financialServices;
    private Utilities utilities;
    private Industrials industrials;
    private Energy energy;
    private Healthcare healthcare;

    public Realestate getRealestate() {
        return realestate;
    }
    public void setRealestate(Realestate v) { this.realestate = v; }

    public ConsumerCyclical getConsumerCyclical() {
        return consumerCyclical;
    }
    public void setConsumerCyclical(ConsumerCyclical v) {
        this.consumerCyclical = v;
    }

    public BasicMaterials getBasicMaterials() {
        return basicMaterials;
    }
    public void setBasicMaterials(BasicMaterials v) {
        this.basicMaterials = v;
    }

    public ConsumerDefensive getConsumerDefensive() {
        return consumerDefensive;
    }
    public void setConsumerDefensive(ConsumerDefensive v) {
        this.consumerDefensive = v;
    }

    public Technology getTechnology() {
        return technology;
    }
    public void setTechnology(Technology v) {
        this.technology = v;
    }

    public CommunicationServices getCommunicationServices() {
        return communicationServices;
    }
    public void setCommunicationServices(CommunicationServices v) {
        this.communicationServices = v;
    }

    public FinancialServices getFinancialServices() {
        return financialServices;
    }
    public void setFinancialServices(FinancialServices v) {
        this.financialServices = v;
    }

    public Utilities getUtilities() {
        return utilities;
    }
    public void setUtilities(Utilities v) {
        this.utilities = v;
    }

    public Industrials getIndustrials() {
        return industrials;
    }
    public void setIndustrials(Industrials v) {
        this.industrials = v;
    }

    public Energy getEnergy() {
        return energy;
    }
    public void setEnergy(Energy v) {
        this.energy = v;
    }

    public Healthcare getHealthcare() {return healthcare;}
    public void setHealthcare(Healthcare v) {
        this.healthcare = v;
    }
}

