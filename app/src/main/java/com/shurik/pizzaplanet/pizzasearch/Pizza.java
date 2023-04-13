package com.shurik.pizzaplanet.pizzasearch;

public class Pizza {
    private String pizzaName;
    private String pizzaComposition;
    private String imageUrl;
    private String price;

<<<<<<< HEAD
    public Pizza(String pizzaName, String pizzaComposition, String imageUrl, String price) {
        this.pizzaName = pizzaName;
        this.pizzaComposition = pizzaComposition;
        this.imageUrl = imageUrl;
        this.price = price;
=======
    public Pizza(String html){
        Document doc = Jsoup.parse(html);
        Element pizzaElem = doc.selectFirst("div.business-full-items-grouped-view__title");

        if (pizzaElem != null) {
            String text = pizzaElem.text();
            if (text.contains("Пицца")) {
                Elements categoryElems = pizzaElem.nextElementSiblings();
                for (Element categoryElem : categoryElems) {
                    if (categoryElem.hasClass("business-full-items-grouped-view__category")) {
                        Elements itemElems = categoryElem.nextElementSiblings();
                        for (Element itemElem : itemElems) {
                            if (itemElem.hasClass("related-item-list-view__pizzaName")) {
                                pizzaName.add(itemElem.text());
                            } else if (itemElem.hasClass("related-item-list-view__price")) {
                                price.add(itemElem.text());
                            } else if (itemElem.hasClass("image__bg")) {
                                Element imgElem = itemElem.selectFirst("img");
                                if (imgElem != null) {
                                    imageUrl.add(imgElem.attr("imageUrl"));
                                }
                            } else if (itemElem.hasClass("related-item-photo-view__description")) {
                                pizzaComposition.add(itemElem.attr("pizzaName"));
                            } else if (itemElem.hasClass("business-full-items-grouped-view__category")) {
                                break;
                            }
                        }
                    }
                }
            }
        }
>>>>>>> 9b192ac6bf9c40e0b35050a46ead225e0f34095f
    }
}
