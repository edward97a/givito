package model;
import model.AdCategory;

public class Ad {
   private int id;
   private String title;
   private int price;
   private AdCategory adcategory;

   public Ad(int id, String title, int price, AdCategory category){
      this.id = id;
      this.title = title;
      setCategory(category);
      setPrice(price);
   }

   public Ad(){

   }

    public Ad(int maxIdcount, String title, int price) {
      this.id = maxIdcount;
      this.title = title;
      this.price = price;

    }

    public AdCategory getCategory() {
      return adcategory;
   }

   public void setCategory(AdCategory adcategory) {
      this.adcategory = adcategory;
   }

   public String getTitle() {

      return title;
   }

   public int getId() {
      return id;
   }

//   public void setId(int id){
//      this.id = id;
//   }

   public int getPrice() {

      return price;
   }

   public void setPrice (int price) {
      if (price < 0) {
         System.out.println("Value is negative");
      } else {
         this.price = price;
      }
   }

   public void setTitle(String title) {
      this.title = title;
   }

   @Override
   public String toString() {

      return "id: " + id + ", title: " + title + ", price: " + price + "$";
   }
}