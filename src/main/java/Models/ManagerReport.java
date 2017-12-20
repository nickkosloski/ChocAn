package Models;

import java.util.List;

public class ManagerReport {
    private List<ProviderWeek> providerWeekList;
    private Integer totalWeeklyConsultations;
    private Double totalWeeklyFee;

    public ManagerReport(){
        totalWeeklyConsultations = 0;
        totalWeeklyFee = 0.0;
    }

    public Integer getProviderTotal(){
        return providerWeekList.size();
    }

    public List<ProviderWeek> getProviderWeekList() {
        return providerWeekList;
    }

    public void setProviderWeekList(List<ProviderWeek> providerWeekList) {
        this.providerWeekList = providerWeekList;
    }

    public Integer getTotalWeeklyConsultations() {
        for(ProviderWeek providerEntry : providerWeekList ){
            totalWeeklyConsultations += providerEntry.getTotalConsultations();
        }
        return totalWeeklyConsultations;
    }

    public Double getTotalWeeklyFee() {
        for(ProviderWeek providerEntry : providerWeekList ){
            totalWeeklyFee += providerEntry.getTotalFee();
        }
        return totalWeeklyFee;
    }

}
