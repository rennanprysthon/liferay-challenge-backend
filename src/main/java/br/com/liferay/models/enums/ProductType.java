package br.com.liferay.models.enums;

public enum ProductType {
    BOOK("BO"),
    FOOD("FO"),
    MEDICAL("ME"),
    OTHER("OT");

    private String type;

    ProductType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static ProductType returnProductType(String type) {
        for(ProductType productType: ProductType.values()) {
            if (productType.getType().equals(type))
                return productType;
        }
        return OTHER;
    }
}
