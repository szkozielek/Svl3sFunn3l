package pl.szkozielek.sales_funnel.dto;

public class FilterDTO
{
    private String filter;

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        if(filter != null){
            this.filter = filter.replaceAll("[^a-zA-Z]", "");
        }
    }
}