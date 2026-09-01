public class Ad {
   private int id;
   private String title;
   private int price;

   public Ad(int id, String title, int price){
      this.id = id;
      this.title = title;
      this.price = price;
   }

   public String getTitle() {
      return title;
   }

   public void setPrice (int price) {
      if (price < 0) {
         System.out.println("Value is negative");
      } else {
         this.price = price;
      }
   }

   @Override
   public String toString() {
      return "id: " + id + ", title: " + title + ", price: " + price;
   }
}
