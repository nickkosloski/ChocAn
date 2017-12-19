package Models;

public class ProviderWeek {
    private String providerId;
    private Integer totalConsultations;
    private Double totalFee;

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public Integer getTotalConsultations() {
        return totalConsultations;
    }

    public void setTotalConsultations(Integer totalConsultations) {
        this.totalConsultations = totalConsultations;
    }

    public Double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Double totalFee) {
        this.totalFee = totalFee;
    }
}
