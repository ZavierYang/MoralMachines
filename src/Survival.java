/**
  To store the survival statistics.
  @author ChingYuan
 **/

public class Survival {
    private String category;
    private float times;
    
    /**
      @param category set the category's name.
     **/
    public Survival(String category){
        this.category = category;
        times = 1;
    }
    
    /**
      @return get category's name.
     **/
    public String getCategory() {
        return category;
    }

    /**
      @return get category's appearance frequency.
     **/
    public float getTimes() {
        return times;
    }
    
    /**
      @param times set the category's appearance frequency.
     **/
    public void setTimes(float times) {
        this.times = times;
    }
    
    /**
      Add the category's appearance frequency.
     **/
    public void add(){
        setTimes(getTimes() + 1);
    }
}
