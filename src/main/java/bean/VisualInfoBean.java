package bean;

public class VisualInfoBean {
    public int record_id;
    public String country_name;
    public String year;
    public String first_index;
    public String second_index;
    public String third_index;
    public double data_value;

    public VisualInfoBean() {

    }

    public VisualInfoBean(int record_id, String country_name, String year, String first_index, String second_index,
            String third_index, double data_value) {
        this.record_id = record_id;
        this.country_name = country_name;
        this.year = year;
        this.first_index = first_index;
        this.second_index = second_index;
        this.third_index = third_index;
        this.data_value = data_value;
    }

    @Override
    public String toString() {
        return String.format(
                "{record_id:%d, country_name:%s, year:%s, first_index:%s, second_index:%s, third_index:%s, data_value:%f}",
                record_id, country_name, year, first_index, second_index, third_index, data_value);
    }
}